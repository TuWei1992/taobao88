package org.taobao88.taobao.controllers;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import org.taobao88.taobao.enterprise.dao.CountryRegCityDAO;
import org.taobao88.taobao.enterprise.dao.OrdersStatusesDAO;
import org.taobao88.taobao.enterprise.dao.PackagesStatusesDAO;
import org.taobao88.taobao.enterprise.dao.PagesContentDAO;
import org.taobao88.taobao.enterprise.dao.PaymentMethodDAO;
import org.taobao88.taobao.enterprise.dao.PostServiceDAO;
import org.taobao88.taobao.enterprise.dao.ShippingAddressDAO;
import org.taobao88.taobao.enterprise.dao.StatusesDAO;
import org.taobao88.taobao.enterprise.entity.*;
import org.taobao88.taobao.enterprise.service.*;

import freemarker.template.Configuration;

@Controller
@RequestMapping("/privateOffice")
public class OfficeController extends  MainController{

	private static final Logger logger = Logger.getLogger(OfficeController.class);
    @Autowired private OrderService orderDAO;
    @Autowired private UserService userDAO;
    @Autowired private GoodsService goodsDAO;
    @Autowired private OrderStatusService orderStatusDAO;
    @Autowired private PackageService packageService;
    @Autowired private PackageStatusService packageStatusDAO;
    @Autowired private CountryRegCityDAO countryRegCityDAO;
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
    @Autowired private ShippingAddressDAO shippingAddressDAO;
    @Autowired private PaymentMethodDAO paymentMethodDAO;
	
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
        request.getSession().setAttribute("messages_count", messagesService.getUnreadedMessagesCount(idUser));
        
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
		request.getSession().setAttribute("basket", new ArrayList<Goods>());
		request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
		request.getSession().setAttribute("messages_count", messagesService.getUnreadedMessagesCount(user.getIdUser()));
		return "redirect:/privateOffice/toPackages";
	}
		
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
			List<String> failFields = new ArrayList<>();
		
			Goods goods = new Goods();
			if (hrefGoods.isEmpty() || hrefGoods == null) { failFields.add("HREFGOODS"); } 
			if (nameGoods.isEmpty() || nameGoods == null) {	failFields.add("NAMEGOODS"); } 
			if (amountGoods < 1) { failFields.add("AMOUNTGOODS"); }
			if (priceGoods < 1) { failFields.add("PRICEGOODS");	}
			if (chinaGoods.isEmpty() || chinaGoods == null) { failFields.add("CHINAGOODS"); }
			if (weightGoods < 1) { failFields.add("WEIGHTGOODS"); }
			if (colorGoods.isEmpty() || colorGoods == null) { failFields.add("COLORGOODS"); }
			if (sizeGoods.isEmpty() || sizeGoods == null) { failFields.add("SIZEGOODS"); }
			
			if (failFields.size() > 0) {
				return "{\"success\":false,\"message\":\"order_fail\",\"fail_fields\":\"" + Arrays.asList(failFields) + "\"}";
			} else {
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
			}
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
    
    @RequestMapping(value="/toOrder", method = RequestMethod.POST)
    public String toOrder(@RequestParam ("price") int price,
    					  @RequestParam ("countryId") int countryId,
    					  @RequestParam ("serviceId") int serviceId,
    					  HttpServletRequest request){

        List<PackageT> newPackages = new ArrayList<PackageT>();
        try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

        int idUser = (int) request.getSession().getAttribute("currentIdUser");
        UserT user = userService.findUserById(idUser);
        String[] idOrders = request.getParameterValues("idOrder");

        double packageWeight = 0;
        List<OrderT> orderTList = new ArrayList<OrderT>();
        Set<OrderT> ordersSet = new HashSet<OrderT>();
        for(int i=0 ; i < idOrders.length; i++) {
        	OrderT o = orderDAO.findOrderById(Integer.parseInt(idOrders[i]));
        	o.setGoods(goodsDAO.findEmployeeById(o.getIdGoods()));
            orderTList.add(o);
            ordersSet.add(o);
            packageWeight += o.getGoods().getWeightGoods() * o.getGoods().getAmountGoods();
        }
        
        String registrationAddress = request.getParameter("registration_address");
        ShippingAddress shippingAddress = new ShippingAddress();
        if (registrationAddress == null) {
        	shippingAddress.setCity(request.getParameter("city"));
        	shippingAddress.setRegion(request.getParameter("region"));
        	shippingAddress.setPostIndex(request.getParameter("post_index"));
        	shippingAddress.setAddress(request.getParameter("address"));
        } else {
        	shippingAddress.setCity(countryRegCityDAO.findCityById(Integer.parseInt(user.getCityUser())).getNameCity());
        	shippingAddress.setRegion(countryRegCityDAO.findRegionById(Integer.parseInt(user.getRegionUser())).getNameRegion());
        	shippingAddress.setPostIndex(user.getIndexUserT());
        	shippingAddress.setAddress(user.getStreetUser() + " " + user.getHouseUser() + "-" + user.getRoomUser());
        }
        Country country = countryRegCityDAO.getCountryByID(countryId);
    	shippingAddress.setCountry(country);
    	shippingAddressDAO.add(shippingAddress);
        
        PackageT packageT = new PackageT();
        packageT.setApprove("false");
        packageT.setIdPackageStatus(packageStatusDAO.savePackageStatus(getPackageStatus()));
        packageT.setFullPrice(price);
        packageT.setDatePackage(getCurrentDate());
        packageT.setWeight(packageWeight/1000);
        packageT.setPostService(postServiceDAO.findById(serviceId));
        packageT.setShippingAddress(shippingAddress);
        packageT.setIdPackage(packageService.addPackage(packageT));
        
        packageT.setOrders(ordersSet);
        
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
        
        Map<String, Object> templateModel = new HashMap<>();
		templateModel.put("packageT", packageT);
		mailService.sendMessageByFreemarkerTemplate((Configuration) request.getServletContext().getAttribute("freemarker_cfg"), templateModel, user.getGmail(), "Посылка успешно сформирована", "order.ftl");
		        
		request.getSession().setAttribute("messages_count", messagesService.getUnreadedMessagesCount(idUser));
        request.getSession().setAttribute("basket", orderDAO.getOrdersOnStartPage(idUser).size());
        return "redirect:/privateOffice/toPackages";
    }

    @RequestMapping(value="/toPackages", method = RequestMethod.GET)
    public String toPackages(HttpServletRequest request){
        int idUser = (int) request.getSession().getAttribute("currentIdUser");

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
        request.getSession().setAttribute("messages_count", messagesService.getUnreadedMessagesCount(idUser));
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
        request.getSession().setAttribute("messages_count", messagesService.getUnreadedMessagesCount(user.getIdUser()));
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
        request.getSession().setAttribute("messages_count", messagesService.getUnreadedMessagesCount(user.getIdUser()));

        return new ModelAndView("orderStatus");
    }

    @RequestMapping(value="/deleteOrder", method = RequestMethod.GET)
    public String deleteOrderStatus(HttpServletRequest request) {
    	
    	try {
    		HttpSession session = request.getSession(true);

    		int idUser = (int) session.getAttribute("currentIdUser");
    		int idOrder = Integer.parseInt(request.getParameter("idOrderForDelete"));
                
    		OrderT orderT = orderDAO.findOrderById(idOrder);
    		if(orderT != null) {
    			ordersStatusesDAO.deleteAllByOrder(orderT);
    			orderDAO.deleteOrder(orderT.getIdOrder());
    			orderStatusDAO.deleteOrderStatus(orderT.getIdOrderStatus());
        	
    			goodsDAO.delete(goodsDAO.findEmployeeById(orderT.getIdGoods()));
    		}

    		List<OrderT> orders = orderDAO.getOrdersOnStartPage(idUser);
    		request.getSession().setAttribute("basket", orders.size());
        
    		if (request.getParameter("idPackage") != null) {
    			PackageT packageT = packageService.findPackageById(Integer.parseInt(request.getParameter("idPackage")));
    			
    			List<OrderT> packageOrdersList = orderDAO.getOrdersForPackages(packageT.getIdPackage());
    			Set<OrderT> packageOrdersSet = new HashSet<>();
    			for (OrderT o : packageOrdersList) {
    				packageOrdersSet.add(o);
    			}
    			packageT.setOrders(packageOrdersSet);
        	
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
    					o.setGoods(goodsDAO.findEmployeeById(o.getIdGoods()));
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
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e);
    		return null;
    	}
    }

    @RequestMapping(value="/changeOrder", method = RequestMethod.GET)
    public ModelAndView changeOrder(HttpServletRequest request){
    	int idUser = (int) request.getSession().getAttribute("currentIdUser");
        int idOrder = Integer.parseInt(request.getParameter("idOrderForChange"));
        OrderT orderT = orderDAO.findOrderById(idOrder);
        orderT.setGoods(goodsDAO.findEmployeeById(orderT.getIdGoods()));

        request.setAttribute("orderT",orderT);
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        request.getSession().setAttribute("messages_count", messagesService.getUnreadedMessagesCount(idUser));
        return new ModelAndView("changeOrder");
    }

    @RequestMapping(value="/updateOrder", method = RequestMethod.POST)
    public String updateOrder(HttpServletRequest request) throws UnsupportedEncodingException {
        request.getSession().setAttribute("TIME",getCurrentDate());

        Goods goods = goodsDAO.findEmployeeById(Integer.parseInt(request.getParameter("goodsId")));
        OrderT order = orderDAO.findByGoods(goods);
                
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
        
        order.setGoods(goods);
        orderDAO.updateOrder(order);
        order.setFullPrice(priceService.getOrderPrice(order));
        orderDAO.updateOrder(order);
        
        if (order.getPackageT() != null) {
        	order.setChanged(1);
        	orderDAO.updateOrder(order);
        	
        	ordersStatusesDAO.deleteAllByOrder(order);
        	OrdersStatuses os = new OrdersStatuses();
    		os.setStatus(statusesDAO.findById(1));
    		os.setOrderT(order);
    		os.setCreatedAt(new Timestamp(new Date().getTime()));
    		ordersStatusesDAO.add(os);      	
        	
        	PackageT packageT = packageService.findPackageById(order.getPackageT().getIdPackage());
        	List<OrderT> orders = orderDAO.getOrdersByPackages(packageT.getIdPackage());
        	int priceWithoutDelivery = 0;
        	for (OrderT o : orders) {
        		o.setGoods(goodsDAO.findEmployeeById(o.getIdGoods()));
        		priceWithoutDelivery += o.getFullPrice();
        	}
        	PostService service = packageT.getPostService();
        	int userId = (int) request.getSession().getAttribute("currentIdUser");
    		UserT user = userDAO.findUserById(userId);
    		int deliveryPrice = priceService.getDeliveryPrice(orders, user, priceWithoutDelivery, service);
    		packageT.setFullPrice(deliveryPrice);
    		packageT.setWeight(priceService.calculatePackageWeight(orders));
    		packageService.updatePackage(packageT);
        	
        	return "redirect:/privateOffice/showPackage?idPackage=" + order.getPackageT().getIdPackage();
        }
        return "redirect:/basket";
    }

    @RequestMapping(value="/ordersHistory", method = RequestMethod.GET)
    public String adminHistoryPage(HttpServletRequest request) {
    	
        int idUser = (int) request.getSession().getAttribute("currentIdUser");
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
        request.getSession().setAttribute("messages_count", messagesService.getUnreadedMessagesCount(idUser));
        return "historyUser";
    }

    @RequestMapping(value="/accountSettings", method = RequestMethod.GET)
    public String userSettings(HttpServletRequest request) {
        
    	int idUser = (int) request.getSession().getAttribute("currentIdUser");
        String bool = "false";
        request.getSession().setAttribute("changeSettingsBool",bool);

        request.setAttribute("user",userDAO.findUserById(idUser));

        request.setAttribute("countries",countryRegCityDAO.getAllCountry());
        request.setAttribute("regions",countryRegCityDAO.getAllRegion());
        request.setAttribute("cities", countryRegCityDAO.getAllCity());
        request.setAttribute("changeSettingsBool", bool);
        request.setAttribute("adminSettingsBool","false");
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        request.getSession().setAttribute("messages_count", messagesService.getUnreadedMessagesCount(idUser));
        return "userSettings";
    }

    @RequestMapping(value="/updateSettings", method = RequestMethod.POST)
    public String updateSettings(HttpServletRequest request) throws UnsupportedEncodingException {

        int idUser = (int) request.getSession().getAttribute("currentIdUser");
        String bool = "true";
        request.getSession().setAttribute("changeSettingsBool",bool);
        request.setAttribute("adminSettingsBool","false");

            UserT userT = userDAO.findUserById(idUser);
            request.setAttribute("user",userT);

            request.setAttribute("countries",countryRegCityDAO.getAllCountry());
            request.setAttribute("regions",countryRegCityDAO.getRegionsByID(Integer.parseInt(userT.getCountryUser())));
            request.setAttribute("cities", countryRegCityDAO.getCitiessByID(Integer.parseInt(userT.getRegionUser())));


        request.setAttribute("changeSettingsBool", bool);
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        request.getSession().setAttribute("messages_count", messagesService.getUnreadedMessagesCount(idUser));
        return "userSettings";
    }

    @RequestMapping(value="/saveSettings", method = RequestMethod.POST)
    public String saveUser(HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession(true);

        UserT userT = new UserT();
        userT = userDAO.findUserById((int)session.getAttribute("currentIdUser"));

        userDAO.updateUser(getParamsUserSettings(request, userT));
        request.getSession().setAttribute("messages_count", messagesService.getUnreadedMessagesCount(userT.getIdUser()));

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
        int idUser = (int) request.getSession().getAttribute("currentIdUser");
        
        request.setAttribute("user",userDAO.findUserById(idUser));
        request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
        request.getSession().setAttribute("messages_count", messagesService.getUnreadedMessagesCount(idUser));
        return "changePassword";
    }

    @RequestMapping(value="/savePassword", method = RequestMethod.POST)
    public String savePassword(HttpServletRequest request) throws UnsupportedEncodingException {
    	int idUser = (int) request.getSession().getAttribute("currentIdUser");

        UserT userT = new UserT();
        userT = userDAO.findUserById(idUser);
        request.getSession().setAttribute("messages_count", messagesService.getUnreadedMessagesCount(idUser));
        
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
    	model.addAttribute("paymentMethods", paymentMethodDAO.getAll());
    	
    	int userId = (int) request.getSession().getAttribute("currentIdUser");
    	UserT userT = userService.findUserById(userId);
    	model.addAttribute("balance", balanceService.getBalance(userT));
    	request.getSession().setAttribute("messages_count", messagesService.getUnreadedMessagesCount(userId));
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
    	messagesService.markMessagesAsReaded(user.getIdUser());
    	
    	request.getSession().setAttribute("messages_count", messagesService.getUnreadedMessagesCount(user.getIdUser()));
    	return "messages/messagesUser";
    }
        
    @RequestMapping(value = "showBalance", method = RequestMethod.GET)
    public String showBalance(Model model, HttpServletRequest request) {	
    	int userId = (int) request.getSession().getAttribute("currentIdUser");
    	UserT userT = userService.findUserById(userId);
    	
    	request.getSession().setAttribute("messages_count", messagesService.getUnreadedMessagesCount(userId));
    	model.addAttribute("paymentMethods", paymentMethodDAO.getAll());
    	request.setAttribute("balance", balanceService.getBalance(userT));
    	request.setAttribute("topMenuList", topMenuService.getFullTopMenu());
    	
    	Object methodId = request.getParameter("method");
    	if (methodId != null) {
    		PaymentMethod method = paymentMethodDAO.findById(Integer.valueOf(methodId.toString()));
    		model.addAttribute("method", method);
    	}
    	
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
