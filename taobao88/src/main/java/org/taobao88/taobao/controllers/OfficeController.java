package org.taobao88.taobao.controllers;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.taobao88.taobao.enterprise.dao.OrdersStatusesDAO;
import org.taobao88.taobao.enterprise.dao.PackagesStatusesDAO;
import org.taobao88.taobao.enterprise.dao.PagesContentDAO;
import org.taobao88.taobao.enterprise.dao.PostServiceDAO;
import org.taobao88.taobao.enterprise.dao.StatusesDAO;
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
    @Autowired private PriceService priceService;
    @Autowired private PostServiceDAO postServiceDAO;
    @Autowired private StatusesDAO statusesDAO;
    @Autowired private OrdersStatusesDAO ordersStatusesDAO;
    @Autowired private PackagesStatusesDAO packagesStatusesDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	 public String userOffice(HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession(true);
        session.setAttribute("TIME",getCurrentDate());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();

        int idUser = userDAO.getId(name).get(0).getIdUser();
        UserT usert = userDAO.findUserById(idUser);

        session.setAttribute("user", usert);
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
        
//		mailService.sendOrderMessage(from, to, "order test", user, goods);
		request.getSession().setAttribute("basket", new ArrayList<Goods>());
		request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
		return "redirect:/privateOffice/toPackages";
	}
	
//    @RequestMapping(value="/FromOrder", method = RequestMethod.POST)
//    public String privateOfficeFromOrder(HttpServletRequest request) throws UnsupportedEncodingException {
//        HttpSession session = request.getSession(true);
//        List<OrderT> newOrders = new ArrayList<OrderT>();
//
//        session.setAttribute("TIME",getCurrentDate());
//
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String name = user.getUsername();
//
//        int idUser = userDAO.getId(name).get(0).getIdUser();
//
//        session.setAttribute("currentIdUser",idUser);
//        session.setAttribute("HREFGOODS",(String)request.getParameter("HREFGOODS"));
//
//        String NULL = (String) session.getAttribute("HREFGOODS");
//        String NULLALL = null;
//
//        if(NULLALL != NULL){
//            Goods goods = getObjectGoods(request);
//            newOrders = allOrdersForOneRequest(goods.getAmountGoods(), goods, idUser);
//            session.setAttribute("HREFGOODS","null");
//        }
//        List<OrderT> orders = orderDAO.getOrdersOnStartPage((int) request.getSession().getAttribute("currentIdUser"));
//		double totalPrice = 0;
//		for (OrderT o : orders) {
//			o.setGoods(goodsDAO.findEmployeeById(o.getIdGoods()));
//			totalPrice += o.getFullPrice();
//		}
//		request.setAttribute("totalPrice", totalPrice);
//		request.setAttribute("orders", orders);
//        request.setAttribute("newOrders",newOrders);
//        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
//        request.getSession().setAttribute("basket", orders.size());
//        
//        clearOrderInSession(request);
//        return "redirect:/basket";
//    }
	
	@RequestMapping(value="/FromOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String fromOrder(@RequestParam ("HREFGOODS") String hrefGoods,
							@RequestParam ("NAMEGOODS") String nameGoods,
							@RequestParam ("AMOUNTGOODS") int amountGoods,
							@RequestParam ("PRICEGOODS") double priceGoods,
							@RequestParam ("CHINAGOODS") String chinaGoods,
							@RequestParam ("WEIGHTGOODS") double weightGoods,
							@RequestParam ("COLORGOODS") String colorGoods,
							@RequestParam ("SIZEGOODS") String sizeGoods,
							@RequestParam ("COMPLEXGOODS") String complexGoods,
					        HttpServletRequest request) {
		
		try {
			int userId = (int) request.getSession().getAttribute("currentIdUser");
		
			Goods goods = new Goods();
			goods.setHrefGoods(hrefGoods);
			goods.setNameGoods(nameGoods);
			goods.setAmountGoods(amountGoods);
			goods.setPriceGoods(priceGoods);
			goods.setChinaGoods(chinaGoods);
			goods.setWeightGoods(weightGoods);
			goods.setColorGoods(colorGoods);
			goods.setSizeGoods(sizeGoods);
			goods.setComplexGoods(complexGoods);
			Object photoGoods = request.getParameter("PHOTOGOODS");
			if (photoGoods != null) {
				goods.setPhotoGoods("true");
			} else {
				goods.setPhotoGoods("false");
			}
			goods.setIdGoods(goodsDAO.saveGoods(goods));
				
			OrderStatus orderStatus = getOrderStatus();
			orderStatusDAO.saveStatus(orderStatus);
		
			OrderT order = new OrderT();
			order.setIdGoods(goods.getIdGoods());
			order.setDateOrder(new Timestamp(new Date().getTime()));
			order.setApprove("false");
			order.setIdUser(userId);
			order.setIdOrderStatus(orderStatus.getIdOrderStatus());
			order.setFullPrice(priceService.getOrderPrice(order));
			orderDAO.addOrder(order);
		
			clearOrderInSession(request);
			request.getSession().setAttribute("basket", orderDAO.getOrdersOnStartPage(userId).size());
			return "{\"success\":true,\"message\":\"order_accepted\"}";
		} catch (Exception e) {
			return "{\"success\":false,\"message\":\"order_fail\"}";
		}
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
        return newPackages;
    }
    
    @RequestMapping(value="/toOrder", method = RequestMethod.GET)
    public String toOrder(@RequestParam ("price") double price,
    					  @RequestParam ("countryId") int countryId,
    					  @RequestParam ("serviceId") int serviceId,
    					  HttpServletRequest request){
        HttpSession session = request.getSession(true);
        List<PackageT> newPackages = new ArrayList<PackageT>();

        int idUser = (int) session.getAttribute("currentIdUser");
        String[] idOrders = request.getParameterValues("idOrder");

        double packageWeight = 0;
        List<OrderT> orderTList = new ArrayList<OrderT>();
        for(int i=0 ; i < idOrders.length; i++) {
        	OrderT o = orderDAO.findOrderById(Integer.parseInt(idOrders[i]));
        	o.setGoods(goodsDAO.findEmployeeById(o.getIdGoods()));
            orderTList.add(o);
            packageWeight += o.getGoods().getWeightGoods() * o.getGoods().getAmountGoods();
        }
        
        PackageT packageT = new PackageT();
        packageT.setApprove("false");
        packageT.setIdPackageStatus(packageStatusDAO.savePackageStatus(getPackageStatus()));
        packageT.setFullPrice(price);
        packageT.setDatePackage(getCurrentDate());
        packageT.setWeight(packageWeight/1000);
        packageT.setPostService(postServiceDAO.findById(serviceId));
        packageT.setIdPackage(packageService.addPackage(packageT));
        
        Status status = statusesDAO.findById(1);
        PackagesStatuses ps = new PackagesStatuses();
        ps.setPackageT(packageT);
        ps.setStatus(status);
        ps.setCreatedAt(new Timestamp(new Date().getTime()));
        packagesStatusesDAO.add(ps);
        
        for (OrderT o : orderTList) {
        	OrdersStatuses os = new OrdersStatuses();
    		os.setStatus(status);
    		os.setOrderT(o);
    		os.setCreatedAt(ps.getCreatedAt());
    		ordersStatusesDAO.add(os);
    		
        	o.setApprove("true");
        	o.setPackageT(packageT);
        	orderDAO.updateOrder(o);
        }
        
        newPackages.add(packageT);
        
        sendMessage(userDAO.findUserById(orderTList.get(0).getIdUser()), packageT, packageT.getIdPackage());
        List<OrderT> orders = orderDAO.getOrdersOnStartPage(idUser);
        request.getSession().setAttribute("basket", orders.size());
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
        request.setAttribute("allStatuses", statusesDAO.getAll());
        request.setAttribute("recomendations", recomendationService.getFirstFourRecomendations(recType));
        request.setAttribute("packages",packageTs);
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        return "packages";
    }

    @RequestMapping(value="/showPackage", method = RequestMethod.GET)
    public ModelAndView showPackage(HttpServletRequest request){
    	UserT user = userDAO.findUserById((int)request.getSession().getAttribute("currentIdUser"));
    	
        int idPackage = Integer.parseInt(request.getParameter("idPackage"));
        PackageT packageT = packageService.findPackageById(idPackage);
        packageT.setStatus(packageStatusDAO.findPackageStatusById(packageT.getIdPackageStatus()));
        for (OrderT o : packageT.getOrders()) {
        	o.setGoods(goodsDAO.findEmployeeById(o.getIdGoods()));
        }
                
        request.setAttribute("allStatuses", statusesDAO.getAll());
        request.setAttribute("packageT", packageT);
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        request.setAttribute("balance", balanceService.getBalance(user));
        return new ModelAndView("showPackage");
    }


    @RequestMapping(value="/toOrderStatus", method = RequestMethod.GET)
     public ModelAndView toStatus(HttpServletRequest request){
    	UserT user = userDAO.findUserById((int)request.getSession().getAttribute("currentIdUser"));
        int idOrder = Integer.parseInt(request.getParameter("idOrder"));

        OrderT orderT = orderDAO.findOrderById(idOrder);
        Goods goods = goodsDAO.findEmployeeById(orderT.getIdGoods());
        OrderStatus orderStatus = orderStatusDAO.findStatusById(orderT.getIdOrderStatus());
        
        request.setAttribute("allStatuses", statusesDAO.getAll());
        request.setAttribute("orderT", orderT);
        request.setAttribute("goods", goods);
        request.setAttribute("status",orderStatus);
        request.setAttribute("balance", balanceService.getBalance(user));
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
        	ordersStatusesDAO.deleteAllByOrder(orderT);
        	orderDAO.deleteOrder(orderT.getIdOrder());
        	orderStatusDAO.deleteOrderStatus(orderT.getIdOrderStatus());
        	
        	goodsDAO.deleteGood(orderT.getIdGoods());
        }

        List<OrderT> orders = orderDAO.getOrdersOnStartPage(idUser);
        request.getSession().setAttribute("basket", orders.size());
        
        if (request.getParameter("idPackage") != null) {
        	PackageT packageT = packageService.findPackageById(Integer.parseInt(request.getParameter("idPackage")));
        	
        	if (packageT.getOrders().size() == 0) {
        		packagesStatusesDAO.deleteAllByPackage(packageT);
        		messagesService.deleteMessagesByPackage(packageT);
        		packageService.deletePackage(packageT.getIdPackage());
        		packageStatusDAO.deletePackageStatus(packageT.getIdPackageStatus());
        		return "redirect:/privateOffice/toPackages";
        	} else {
        		List<OrderT> orderList = new ArrayList<OrderT>();
        	
        		double priceWithoutDelivery = 0;
        		double packageWeight = 0;
        		for(OrderT o : packageT.getOrders()) {
        			priceWithoutDelivery += o.getFullPrice();
        			packageWeight += o.getGoods().getWeightGoods() * o.getGoods().getAmountGoods();
        			orderList.add(o);
        		}
        	
        		packageT.setFullPrice(priceService.getDeliveryPrice(orderList, userDAO.findUserById(idUser), priceWithoutDelivery, packageT.getPostService()));
        		packageT.setWeight(packageWeight / 1000);
        		packageService.updatePackage(packageT);
        		return "redirect:/privateOffice/showPackage?idPackage=" + packageT.getIdPackage();
        	}
        } else {
            return "redirect:/basket";
        }
    }

    @RequestMapping(value="/changeOrder", method = RequestMethod.GET)
    public ModelAndView changeOrder(HttpServletRequest request){
        int idOrder = Integer.parseInt(request.getParameter("idOrderForChange"));
        OrderT orderT = orderDAO.findOrderById(idOrder);
        orderT.setGoods(goodsDAO.findEmployeeById(orderT.getIdGoods()));

        request.setAttribute("orderT",orderT);
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        return new ModelAndView("changeOrder");
    }

    @RequestMapping(value="/updateOrder", method = RequestMethod.POST)
    public String updateOrder(HttpServletRequest request) throws UnsupportedEncodingException {
        request.getSession().setAttribute("TIME",getCurrentDate());

        Goods goods = goodsDAO.findEmployeeById(Integer.parseInt(request.getParameter("goodsId")));
        goods.setHrefGoods(request.getParameter("HREFGOODS"));
        goods.setAmountGoods(Integer.parseInt(request.getParameter("AMOUNTGOODS")));
        goods.setWeightGoods(Double.parseDouble(request.getParameter("WEIGHTGOODS")));
        goods.setPriceGoods(Double.parseDouble(request.getParameter("PRICEGOODS")));
        goods.setColorGoods(request.getParameter("COLORGOODS"));
        goods.setSizeGoods(request.getParameter("SIZEGOODS"));
        goods.setNameGoods(request.getParameter("NAMEGOODS"));
        goods.setChinaGoods(request.getParameter("CHINAGOODS"));
        goods.setComplexGoods(request.getParameter("COMPLEXGOODS"));
        Object photoGoods = request.getParameter("PHOTOGOODS");
		if (photoGoods != null) {
			goods.setPhotoGoods("true");
		} else {
			goods.setPhotoGoods("false");
		}        
        goodsDAO.updateEmployee(goods);
        
        OrderT order = orderDAO.findByGoods(goods);
        order.setIdGoods(goods.getIdGoods());
        order.setFullPrice(priceService.getOrderPrice(order));
        orderDAO.updateOrder(order);
        
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
    	return "messages/messagesUser";
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
