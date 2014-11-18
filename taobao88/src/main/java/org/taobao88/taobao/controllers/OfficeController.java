package org.taobao88.taobao.controllers;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.taobao88.taobao.enterprise.DAO.PagesContentDAO;
import org.taobao88.taobao.enterprise.entity.*;
import org.taobao88.taobao.enterprise.service.*;

@Controller
@RequestMapping("/privateOffice")
public class OfficeController extends  MainController{

    @Autowired private OrderService orderDAO;
    @Autowired private UserService userDAO;
    @Autowired private GoodsService goodsDAO;
    @Autowired private OrderStatusService orderStatusDAO;
    @Autowired private PackageService packageService;
    @Autowired private PackageStatusService packageStatusDAO;
    @Autowired private CountryRegCityService countryRegCityDAO;
    @Autowired private MailService mailService;
    @Autowired private UserService userService;
    @Autowired private TopMenuService topMenuService;
    @Autowired private RecomendationService recomendationService;
    @Autowired private MessagesService messagesService;
    @Autowired private BalanceService balanceService;
    @Autowired private PagesContentDAO pagesContentDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	 public String userOffice(HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession(true);
        session.setAttribute("TIME",getCurrentDate());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();

        int idUser = userDAO.getId(name).get(0).getIdUser();

        session.setAttribute("user", user);
        session.setAttribute("currentIdUser",idUser);
        List<OrderT> orders = orderDAO.getOrdersOnStartPage(idUser);

        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        request.getSession().setAttribute("basket", orders.size());
        
        RecomendationType recType = new RecomendationType();
        recType.setTypeId(5);
        request.setAttribute("banner", recomendationService.getAllRecomendations(recType));
        
        PageContent content = pagesContentDAO.findContentByPageName("privateOffice");
        request.setAttribute("content", content);
        
        return "privateOffice";
    }

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/fromBasket", method = RequestMethod.GET)
	public String privateOfficeFromBasket(HttpServletRequest request) throws UnsupportedEncodingException {
		
		List<Goods> goods = (List<Goods>) request.getSession().getAttribute("basket");
		UserT user = userDAO.findUserById((int) request.getSession().getAttribute("currentIdUser"));
		
		List<OrderT> newOrders = new ArrayList<OrderT>();
		for (Goods good : goods) {
			newOrders.addAll(prepareOrdersFromGoods(good, user));
		}
		preparePackage(user, newOrders);
		ResourceBundle getPath = ResourceBundle.getBundle("mail");
        String from = getPath.getString("mailAdmin");
        String to = getPath.getString("mailReceiver");
        
		mailService.sendOrderMessage(from, to, "order test", user, goods);
		request.getSession().setAttribute("basket", new ArrayList<Goods>());
		request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
		return "redirect:/privateOffice/toPackages";
	}
	
    @RequestMapping(value="/FromOrder", method = RequestMethod.POST)
    public String privateOfficeFromOrder(HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession(true);
        List<OrderT> newOrders = new ArrayList<OrderT>();

        session.setAttribute("TIME",getCurrentDate());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();

        int idUser = userDAO.getId(name).get(0).getIdUser();

        session.setAttribute("currentIdUser",idUser);
        session.setAttribute("HREFGOODS",(String)request.getParameter("HREFGOODS"));

        String NULL = (String) session.getAttribute("HREFGOODS");
        String NULLALL = null;

        if(NULLALL != NULL){
            Goods goods = getObjectGoods(request);
            newOrders = allOrdersForOneRequest(goods.getAmountGoods(), goods, idUser);
            session.setAttribute("HREFGOODS","null");
        }
        List<OrderT> orders = orderDAO.getOrdersOnStartPage((int) request.getSession().getAttribute("currentIdUser"));
		double totalPrice = 0;
		for (OrderT o : orders) {
			o.setGoods(goodsDAO.findEmployeeById(o.getIdGoods()));
			totalPrice += o.getFullPrice();
		}
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("orders", orders);
        request.setAttribute("newOrders",newOrders);
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        request.getSession().setAttribute("basket", orders.size());
        
        clearOrderInSession(request);
        
        RecomendationType recType = new RecomendationType();
        recType.setTypeId(5);
        request.setAttribute("banner", recomendationService.getAllRecomendations(recType));
        return "redirect:/basket";
    }

    private List<PackageT> preparePackage(UserT user, List<OrderT> orders) {
    	List<PackageT> newPackages = new ArrayList<PackageT>();
        int idPackageStatus = packageStatusDAO.savePackageStatus(getPackageStatus());

        PackageT packageT = new PackageT();
        packageT.setApprove("false");
        packageT.setIdPackageStatus(idPackageStatus);
        
        List<OrderT> orderTList = new ArrayList<OrderT>();
        for (OrderT order : orders) {
        	orderTList.add(orderDAO.findOrderById(order.getIdOrder()));
        }
        newPackages = allPackagesForOneRequest(orderTList);
        return newPackages;
    }
    
    @RequestMapping(value="/toOrder", method = RequestMethod.GET)
    public String toOrder(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        List<PackageT> newPackages = new ArrayList<PackageT>();

        int idPackageStatus = packageStatusDAO.savePackageStatus(getPackageStatus());

        PackageT packageT = new PackageT();
        packageT.setApprove("false");
        packageT.setIdPackageStatus(idPackageStatus);

        int idUser = (int) session.getAttribute("currentIdUser");

        int idPackage = packageService.addPackage(packageT);

        String[] idOrders = request.getParameterValues("idOrder");

        List<OrderT> orderTList = new ArrayList<OrderT>();
        for(int i=0 ; i < idOrders.length; i++) {
            orderTList.add(orderDAO.findOrderById(Integer.parseInt(idOrders[i])));
        }

        newPackages = allPackagesForOneRequest(orderTList);
        List<PackageT> packageTs = new ArrayList<PackageT>();
        List<OrderT> packages = orderDAO.getOrdersForPack(idUser);

        for(int i=0; i < packages.size(); i++){
            PackageT packageT1 = packageService.findPackageById(packages.get(i).getPackageT().getIdPackage());
            if(packageT1.getApprove().equals("false")) {
                packageTs.add(packageT1);
            }
        }
        Collections.sort(packageTs, Collections.reverseOrder());
        
        List<OrderT> orders = orderDAO.getOrdersOnStartPage(idUser);
        request.getSession().setAttribute("basket", orders.size());

        packageTs = getListForFirstPage(packageTs,request);
        request.setAttribute("packages",packageTs);
        request.setAttribute("newPackage",newPackages);
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        return "redirect:/privateOffice/toPackages";
    }

    @RequestMapping(value="/toPackages", method = RequestMethod.GET)
    public String toPackages(HttpServletRequest request){
        HttpSession session = request.getSession(true);

        int idUser = (int) session.getAttribute("currentIdUser");

        List<PackageT> packageTs = new ArrayList<PackageT>();
        ArrayList<OrderT> packages = (ArrayList<OrderT>) orderDAO.getOrdersForPack(idUser);
        
        for(int i=0; i<packages.size(); i++){
            PackageT packageT = packageService.findPackageById(packages.get(i).getPackageT().getIdPackage());
            if(packageT.getApprove().equals("false") && !packageTs.contains(packageT)) {
                packageTs.add(packageT);
            }
        }
        Collections.sort(packageTs, Collections.reverseOrder());
        for (PackageT p : packageTs) {
        	p.setStatus(packageStatusDAO.findPackageStatusById(p.getIdPackageStatus()));
        }
        RecomendationType recType = new RecomendationType();
        recType.setTypeId(0);
        request.setAttribute("recomendations", recomendationService.getFirstFourRecomendations(recType));
        request.setAttribute("packages",packageTs);
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        return "packages";
    }

    @RequestMapping(value="/showPackage", method = RequestMethod.GET)
    public ModelAndView showPackage(HttpServletRequest request){
//        int idUser = (int) session.getAttribute("currentIdUser");

        int idPackage = Integer.parseInt(request.getParameter("idPackage"));
        PackageT packageT = packageService.findPackageById(idPackage);
        packageT.setStatus(packageStatusDAO.findPackageStatusById(packageT.getIdPackageStatus()));
        
        request.setAttribute("packageT", packageT);
        request.setAttribute("orders",getOrders(orderDAO.getOrdersForPackages(idPackage),goodsDAO.getAll(orderDAO.getOrdersForPackages(idPackage)), orderStatusDAO.getOrdersStatuses(orderDAO.getOrdersByPackages(idPackage))));
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        return new ModelAndView("showPackage");
    }


    @RequestMapping(value="/toOrderStatus", method = RequestMethod.GET)
     public ModelAndView toStatus(HttpServletRequest request){

        int idOrder = Integer.parseInt(request.getParameter("idOrder"));

        OrderT orderT = orderDAO.findOrderById(idOrder);
        Goods goods = goodsDAO.findEmployeeById(orderT.getIdGoods());
        OrderStatus orderStatus = orderStatusDAO.findStatusById(orderT.getIdOrderStatus());
        
        request.setAttribute("orderT", orderT);
        request.setAttribute("goods", goods);
        request.setAttribute("status",orderStatus);
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());

        return new ModelAndView("orderStatus");
    }

    @RequestMapping(value="/deleteOrder", method = RequestMethod.GET)
    public String deleteOrderStatus(HttpServletRequest request){
        HttpSession session = request.getSession(true);

        int idUser = (int) session.getAttribute("currentIdUser");

        int idOrder = Integer.parseInt(request.getParameter("idOrderForDelete"));
        OrderT orderT = orderDAO.findOrderById(idOrder);
        if(orderT != null) {
        	orderDAO.deleteOrder(orderT.getIdOrder());
        	orderStatusDAO.deleteOrderStatus(orderT.getIdOrderStatus());
        	
        	goodsDAO.deleteGood(orderT.getIdGoods());
        }

        List<OrderT> orders = orderDAO.getOrdersOnStartPage(idUser);
        request.getSession().setAttribute("basket", orders.size());
        
        return "redirect:/basket";
    }

    @RequestMapping(value="/changeOrder", method = RequestMethod.GET)
    public ModelAndView changeOrder(HttpServletRequest request){
        int idOrder = Integer.parseInt(request.getParameter("idOrderForChange"));
        OrderT orderT = orderDAO.findOrderById(idOrder);
        Goods goods = goodsDAO.findEmployeeById(orderT.getIdGoods());

        request.setAttribute("order",orderT);
        request.setAttribute("goods",goods);
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        return new ModelAndView("changeOrder");
    }

    @RequestMapping(value="/updateOrder", method = RequestMethod.POST)
    public String updateOrder(HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession(true);

        session.setAttribute("TIME",getCurrentDate());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();

        int idUser = userDAO.getId(name).get(0).getIdUser();

        session.setAttribute("currentIdUser",idUser);

        Goods goods = goodsDAO.findEmployeeById(Integer.parseInt(request.getParameter("goodsId")));
        goods = getObjectGoodsForUpdate(request, goods);

        goodsDAO.updateEmployee(goods);

        List<OrderT> orders = orderDAO.getOrdersOnStartPage(idUser);

        request.setAttribute("orders", getOrders(orders, goodsDAO.getAll(orders), orderStatusDAO.getOrdersStatuses(orders)));
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        return "redirect:/basket";
    }

    @RequestMapping(value="/ordersHistory", method = RequestMethod.GET)
    public String adminHistoryPage(HttpServletRequest request) {
    	HttpSession session = request.getSession(true);
        int idUser = (int) session.getAttribute("currentIdUser");
        ArrayList<PackageT> packageTs = new ArrayList<PackageT>();
        ArrayList<OrderT> packages = (ArrayList<OrderT>) orderDAO.getOrdersForPack(idUser);

        for(int i=0;i<packages.size();i++){
            PackageT packageT = packageService.findPackageById
                    (packages.get(i).getPackageT().getIdPackage());
            if(packageT.getApprove().equals("true")) {
                packageTs.add(packageT);
            }
        }
        Collections.sort(packageTs,Collections.reverseOrder());
        request.setAttribute("packageTs",packageTs);
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        return "historyUser";
    }

    @RequestMapping(value="/accountSettings", method = RequestMethod.GET)
    public String userSettings(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String bool = "false";
        session.setAttribute("changeSettingsBool",bool);

        request.setAttribute("user",userDAO.findUserById((int)session.getAttribute("currentIdUser")));

        request.setAttribute("countries",countryRegCityDAO.getAllCountry());
        request.setAttribute("regions",countryRegCityDAO.getAllRegion());
        request.setAttribute("cities", countryRegCityDAO.getAllCity());
        request.setAttribute("changeSettingsBool", bool);
        request.setAttribute("adminSettingsBool","false");
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        return "userSettings";
    }

    @RequestMapping(value="/updateSettings", method = RequestMethod.POST)
    public String updateSettings(HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession(true);
        String bool = "true";
        session.setAttribute("changeSettingsBool",bool);
        request.setAttribute("adminSettingsBool","false");

            UserT userT = userDAO.findUserById((int)session.getAttribute("currentIdUser"));
            request.setAttribute("user",userT);

            request.setAttribute("countries",countryRegCityDAO.getAllCountry());
            request.setAttribute("regions",countryRegCityDAO.getRegionsByID(Integer.parseInt(userT.getCountryUser())));
            request.setAttribute("cities", countryRegCityDAO.getCitiessByID(Integer.parseInt(userT.getRegionUser())));


        request.setAttribute("changeSettingsBool", bool);
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        return "userSettings";
    }

    @RequestMapping(value="/saveSettings", method = RequestMethod.POST)
    public String saveUser(HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession(true);

        UserT userT = new UserT();
        userT = userDAO.findUserById((int)session.getAttribute("currentIdUser"));

        userDAO.updateUser(getParamsUserSettings(request, userT));

        if("null".equals((String)request.getParameter("stepSettings"))
                || "".equals((String)request.getParameter("stepSettings"))){
            System.out.println("->");
            String bool = "false";
            session.setAttribute("changeSettingsBool",bool);

            request.setAttribute("user",userDAO.findUserById((int)session.getAttribute("currentIdUser")));

            request.setAttribute("countries",countryRegCityDAO.getAllCountry());
            request.setAttribute("regions",countryRegCityDAO.getAllRegion());
            request.setAttribute("cities", countryRegCityDAO.getAllCity());
            request.setAttribute("changeSettingsBool", bool);
            request.setAttribute("adminSettingsBool","false");
            return "redirect:/privateOffice/accountSettings";

            } else{
            String bool = "true";
            session.setAttribute("changeSettingsBool",bool);
            request.setAttribute("adminSettingsBool","false");
            if(request.getParameter("stepSettings").equals("stepRegion")){
                userT = userDAO.findUserById((int)session.getAttribute("currentIdUser"));

                request.setAttribute("user",userT);

                request.setAttribute("countries",countryRegCityDAO.getAllCountry());
                request.setAttribute("regions",countryRegCityDAO.getRegionsByID(Integer.parseInt(userT.getCountryUser())));
                request.setAttribute("cities", countryRegCityDAO.getCitiessByID(Integer.parseInt(userT.getRegionUser())));

            }  if(request.getParameter("stepSettings").equals("stepCity")){
                userT = userDAO.findUserById((int)session.getAttribute("currentIdUser"));
                request.setAttribute("user",userT);

                request.setAttribute("countries",countryRegCityDAO.getAllCountry());
                request.setAttribute("regions",countryRegCityDAO.getRegionsByID(Integer.parseInt(userT.getCountryUser())));
                request.setAttribute("cities", countryRegCityDAO.getCitiessByID(Integer.parseInt(userT.getRegionUser())));
            }
            return "redirect:/privateOffice/accountSettings";
        }
    }

    @RequestMapping(value="/changePassword", method = RequestMethod.GET)
    public String changePassword(HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession(true);
        request.setAttribute("user",userDAO.findUserById((int)session.getAttribute("currentIdUser")));
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        return "changePassword";
    }

    @RequestMapping(value="/savePassword", method = RequestMethod.POST)
    public String savePassword(HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession(true);

        UserT userT = new UserT();
        userT = userDAO.findUserById((int)session.getAttribute("currentIdUser"));
        if(userT.getPassword().equals(request.getParameter("OLDPASSWORD"))){
            userT.setPassword(request.getParameter("NEWPASSWORD"));
            userDAO.updateUser(userT);
            request.setAttribute("rightOldPassword","true");
            setUserSettings(request);
        } else {
            request.setAttribute("rightOldPassword","false");
            return "changePassword";
        }
        return "redirect:/privateOffice/accountSettings";
    }

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String payment(HttpServletRequest request,
    					  @RequestParam ("idPackage") int id, 
    				      Model model) {
    	model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
    	model.addAttribute("packageT", packageService.findPackageById(id));
    	
    	int userId = (int) request.getSession().getAttribute("currentIdUser");
    	UserT userT = userService.findUserById(userId);
    	request.setAttribute("balance", balanceService.getBalance(userT));
    	return "pay";
    }
    
    @RequestMapping(value = "/showMessages", method = RequestMethod.GET)
    public String showMessages(HttpServletRequest request, Model model) {
    	UserT user = userService.findUserById((int) request.getSession().getAttribute("currentIdUser"));
//    	List<PackageT> packs = packageService.getPackagesByUser(user);
//    	for (PackageT p : packs) {
//    		p.setMessages(messagesService.findMessagesByPackage(p));
//    	}
    	List<Message> messages = messagesService.findAllUserMessages(user);
    	List<PackageT> packs = new ArrayList<>();
    	for (Message m : messages) {
    		PackageT p = m.getPackageT();
    		if (!packs.contains(p)) {
    			p.setMessages(messagesService.findMessagesByPackage(p));
    			packs.add(p);
    		}
    	}
     	model.addAttribute("packages", packs);
    	model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
    	return "messagesUser";
    }
    
    @RequestMapping(value = "sendMessage", method = RequestMethod.GET)
    public String sendMessage(@RequestParam ("toUser") int toUser,
    						  @RequestParam ("idpackage") int idpackage,
    						  HttpServletRequest request, Model model) {
    	UserT fromUser = null;
    	if (request.getSession().getAttribute("currentIdUser") == null) {
    		fromUser = userService.findUserById(1);
    	} else {
    		fromUser = userService.findUserById((int) request.getSession().getAttribute("currentIdUser"));
    	}
    	if (fromUser.getIdUser() == 1) {
    		model.addAttribute("fromUser", fromUser);
    		model.addAttribute("toUser", userService.findUserById(toUser));
    		model.addAttribute("packageT", packageService.findPackageById(idpackage));
    		return "messageAdmin";
    	} else {
    		return "messageUser";
    	}
    }
    
    @RequestMapping(value = "confirmMessage", method = RequestMethod.POST)
    public String confirmMessage(@RequestParam ("toUser") int toUser, 
    							 @RequestParam ("fromUser") int fromUser, 
    							 @RequestParam("idpackage") int idpackage, 
    							 @RequestParam ("message") String message, HttpServletRequest request, Model model) {
    	Message m = new Message();
    	m.setFromUser(userService.findUserById(fromUser));
		m.setToUser(userService.findUserById(toUser));
		m.setMessage(message);
		m.setPackageT(packageService.findPackageById(idpackage));
		m.setCreatedAt(new Timestamp(new Date().getTime()));
		m.setUpdatedAt(new Timestamp(new Date().getTime()));
		messagesService.createMessage(m);
    	if (fromUser == 1) {
    		return "redirect:/admin/showMessages";
    	} else {
    		return "redirect:/privateOffice/showMessages";
    	}
    }
    
    @RequestMapping(value = "showBalance", method = RequestMethod.GET)
    public String showBalance(HttpServletRequest request) {	
    	int userId = (int) request.getSession().getAttribute("currentIdUser");
    	UserT userT = userService.findUserById(userId);
    	request.setAttribute("balance", balanceService.getBalance(userT));
    	request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
    	return "pay";
    }
    
    private void setUserSettings(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String bool = "false";
        session.setAttribute("changeSettingsBool",bool);

        request.setAttribute("user",userDAO.findUserById((int)session.getAttribute("currentIdUser")));

        request.setAttribute("countries",countryRegCityDAO.getAllCountry());
        request.setAttribute("regions",countryRegCityDAO.getAllRegion());
        request.setAttribute("cities", countryRegCityDAO.getAllCity());
        request.setAttribute("changeSettingsBool", bool);
        request.setAttribute("adminSettingsBool","false");
    }
    
    private void clearOrderInSession(HttpServletRequest request) {
    	request.getSession().setAttribute("HREFGOODS", null);
    	request.getSession().setAttribute("NAMEGOODS", null);
    	request.getSession().setAttribute("AMOUNTGOODS", null);
    	request.getSession().setAttribute("PRICEGOODS", null);
    	request.getSession().setAttribute("CHINAGOODS", null);
    	request.getSession().setAttribute("WEIGHTGOODS", null);
    	request.getSession().setAttribute("COLORGOODS", null);
    	request.getSession().setAttribute("SIZEGOODS", null);
    	request.getSession().setAttribute("COMPLEXGOODS", null);
    	request.getSession().setAttribute("PHOTOGOODS", null);
    }

}
