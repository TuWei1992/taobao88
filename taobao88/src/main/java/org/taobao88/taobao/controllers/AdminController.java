package org.taobao88.taobao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.taobao88.taobao.enterprise.entity.*;
import org.taobao88.taobao.enterprise.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController extends MainController{

    @Autowired private OrderService orderDAO;
    @Autowired private UserService userDAO;
    @Autowired private GoodsService goodsDAO;
    @Autowired private OrderStatusService orderStatusDAO;
    @Autowired private PackageService packageDAO;
    @Autowired private PackageStatusService packageStatusDAO;
    @Autowired private UserService userService;
    @Autowired private MessagesService messagesService;
    @Autowired private BalanceService balanceService;
    
	@RequestMapping(method = RequestMethod.GET)
	public String adminPage(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("TIME",getCurrentDate());
        /*List<OrderT> resultOrderTs = new ArrayList<OrderT>();

        session.setAttribute("TIME",getCurrentDate());

        List<OrderT> orderTs = orderDAO.getOrdersForAdmin();

        for(int i=0;i<orderTs.size();i++){
            OrderStatus orderStatus = orderStatusDAO.findStatusById(orderTs.get(i).getIdOrderStatus());
            if(!orderStatus.getDone().equals("true")){
                resultOrderTs.add(orderTs.get(i));
            }
        }

        request.setAttribute("orders",resultOrderTs);

        return "admin";   */
        List<PackageT> packageTList = packageDAO.getPackagesForAdmin();
        packageTList = getListForFirstPage(packageTList,request);

        request.setAttribute("activePackages",packageTList);

        return "activePackages";
	}

    @RequestMapping(value="/orderHistory", method = RequestMethod.GET)
    public String adminHistoryPage(HttpServletRequest request) {
       /* List<OrderT> resultOrderTs = new ArrayList<OrderT>();

        List<OrderT> orderTs = orderDAO.getOrdersForAdmin();

        for(int i=0;i<orderTs.size();i++){
            OrderStatus orderStatus = orderStatusDAO.findStatusById(orderTs.get(i).getIdOrderStatus());
            if(!orderStatus.getDone().equals("false")){
                resultOrderTs.add(orderTs.get(i));
            }
        }

        request.setAttribute("ordersHistory",resultOrderTs); */

        List<PackageT> packageTList = packageDAO.getPackagesForAdminHistory();
        packageTList = getListForFirstPage(packageTList,request);
        request.setAttribute("activePackages",packageTList);

        return "history";
    }

    @RequestMapping(value="/toAdminStatus", method = RequestMethod.GET)
    public String adminStatus(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        List<OrderT> orderTs = orderDAO.getOrdersForAdmin();

        int idOrderStatus = Integer.parseInt(request.getParameter("idOrderStatus"));
        session.setAttribute("currentIdOrderStatus",idOrderStatus);

        OrderStatus orderStatus = orderStatusDAO.findStatusById(idOrderStatus);

        request.setAttribute("status",orderStatus);

        return "adminStatus";
    }

    @RequestMapping(value="/saveOrderStatus", method = RequestMethod.GET)
    public String saveStatus(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        List<OrderT> orderTList = (List<OrderT>) session.getAttribute("ordersInCurrentPackage");

        for(int i=0;i<orderTList.size();i++) {
            OrderStatus orderStatus = getStatus(request,orderStatusDAO.findStatusById(orderTList.get(i).getIdOrderStatus()));
            orderStatusDAO.updateOrderStatus(orderStatus);
        }

        int idPackage = (int)session.getAttribute("currentIdPackage");
        PackageStatus packageStatus = packageStatusDAO.findPackageStatusById(packageDAO.findPackageById(idPackage).getIdPackageStatus());
        packageStatus = checkOnReady(orderDAO.getOrdersForPackages(idPackage),packageStatus);

        session.setAttribute("ordersInCurrentPackage",orderDAO.getOrdersForPackages(idPackage));

        packageStatusDAO.updatePackageStatus(packageStatus);

        request.setAttribute("orders",getOrders(orderDAO.getOrdersForPackages(idPackage),goodsDAO.getAll(orderDAO.getOrdersForPackages(idPackage)), orderStatusDAO.getOrdersStatuses(orderDAO.getOrdersByPackages(idPackage))));
        request.setAttribute("statusPackage",packageStatus);
        request.setAttribute("activePackages",packageDAO.getPackagesForAdmin());

        if(orderDAO.getOrdersForPackages(idPackage) != null) {
            request.setAttribute("userOfCurrentPackage",userDAO.findUserById(orderDAO.getOrdersForPackages(idPackage).get(0).getIdUser()));
            session.setAttribute("currentUserOfPackage",userDAO.findUserById(orderDAO.getOrdersForPackages(idPackage).get(0).getIdUser()));
        }


        return "showPackageAdmin";
    }

    @RequestMapping(value="/orderInformation", method = RequestMethod.GET)
    public String orderInformation(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        int idOrder = Integer.parseInt(request.getParameter("idOrder"));
        OrderT orderT = orderDAO.findOrderById(idOrder);

        Goods goods = goodsDAO.findEmployeeById(orderT.getIdGoods());
        UserT userT = userDAO.findUserById(orderT.getIdUser());

        request.setAttribute("good",goods);
        request.setAttribute("user",userT);
        request.setAttribute("order",orderT);

        return "orderInformation";
    }

    @RequestMapping(value="/activePackages", method = RequestMethod.GET)
    public String activePackages(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        request.setAttribute("activePackages",packageDAO.getPackagesForAdmin());

        return "activePackages";
    }

    @RequestMapping(value="/showPackageAdmin", method = RequestMethod.GET)
    public String showPackageAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        //int idUser = (int) session.getAttribute("currentIdUser");

        int idPackage = Integer.parseInt(request.getParameter("idPackage"));

        session.setAttribute("currentIdPackage",idPackage);

        PackageStatus packageStatus = packageStatusDAO.findPackageStatusById(packageDAO.findPackageById(idPackage).getIdPackageStatus());
        packageStatus = checkOnReady(orderDAO.getOrdersForPackages(idPackage),packageStatus);

        session.setAttribute("ordersInCurrentPackage",orderDAO.getOrdersForPackages(idPackage));

        packageStatusDAO.updatePackageStatus(packageStatus);

        request.setAttribute("orders",getOrders(orderDAO.getOrdersForPackages(idPackage),goodsDAO.getAll(orderDAO.getOrdersForPackages(idPackage)), orderStatusDAO.getOrdersStatuses(orderDAO.getOrdersByPackages(idPackage))));
        request.setAttribute("statusPackage",packageStatus);
        request.setAttribute("activePackages",packageDAO.getPackagesForAdmin());
        if(orderDAO.getOrdersForPackages(idPackage) != null) {
            request.setAttribute("userOfCurrentPackage",userDAO.findUserById(orderDAO.getOrdersForPackages(idPackage).get(0).getIdUser()));
            session.setAttribute("currentUserOfPackage",userDAO.findUserById(orderDAO.getOrdersForPackages(idPackage).get(0).getIdUser()));
        }

        return "showPackageAdmin";
    }

    @RequestMapping(value="/userInformation", method = RequestMethod.GET)
    public String userInformation(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        request.setAttribute("changeSettingsBool","false");
        UserT userT = (UserT)session.getAttribute("currentUserOfPackage");
        request.setAttribute("user",userT);
        request.setAttribute("adminSettingsBool","true");

        return "adminSettings";
    }
    
    @RequestMapping(value = "showMessages", method = RequestMethod.GET)
    public String showMessages(HttpServletRequest request) {
//    	UserT user = userService.findUserById(1);
//    	List<Message> messages = messagesService.findAllUserMessages(user);
//    	Map<Integer, Set<Message>> map = messagesService.getMessagesByPackages(messages);
//    	request.setAttribute("mesMap", map);
//    	request.setAttribute("mes", messages);
    	List<PackageT> packs = packageDAO.getPackagesForAdmin();
    	for (PackageT p : packs) {
    		p.setMessages(messagesService.findMessagesByPackage(p));
    	}
    	request.setAttribute("packages", packs);
    	return "messagesAdmin";
    }
    
    @RequestMapping(value = "showBalances", method = RequestMethod.GET)
    public String showBalances(HttpServletRequest request) {
    	List<UserT> users = userService.getAll();
    	Map<Integer, Double> usersBalances = new HashMap<>();
    	for (UserT u : users) {
    		usersBalances.put(u.getIdUser(), balanceService.getBalance(u));
    	}
    	request.setAttribute("users", users);
    	request.setAttribute("balances", usersBalances);
    	return "balancesAdmin";
    }
    
    @RequestMapping(value = "adjustBalance", method = RequestMethod.POST)
    public void adjustBalance(@RequestParam ("user_id") int userId,
    						  @RequestParam ("amount") double amount,
    						  @RequestParam ("type") String type) {
    	BalanceOperation bo = new BalanceOperation();
    	bo.setUserT(userService.findUserById(userId));
    	bo.setCreatedAt(new Timestamp(new Date().getTime()));
    	bo.setUpdatedAt(new Timestamp(new Date().getTime()));
    	bo.setAmount(amount);
    	if (type.equals("inc")) {
    		bo.setReason("Increment by Admin");
    	} else {
    		bo.setReason("Decrement by Admin");
    	}
    	balanceService.adjustBalance(bo);
    }
    
    private OrderStatus getStatus(HttpServletRequest request, OrderStatus orderStatus){

        if(null !=request.getParameter("approve"+orderStatus.getIdOrderStatus()) ){
            orderStatus.setApprove(request.getParameter("approve"+orderStatus.getIdOrderStatus()));
        }if(null != request.getParameter("pay"+orderStatus.getIdOrderStatus())){
            orderStatus.setPay(request.getParameter("pay"+orderStatus.getIdOrderStatus()));
        }if(null != request.getParameter("ransom"+orderStatus.getIdOrderStatus())){
            orderStatus.setRansom(request.getParameter("ransom"+orderStatus.getIdOrderStatus()));
        }if(null != request.getParameter("ready"+orderStatus.getIdOrderStatus())){
            orderStatus.setReady(request.getParameter("ready"+orderStatus.getIdOrderStatus()));
        }if(null != request.getParameter("done"+orderStatus.getIdOrderStatus())){
            orderStatus.setDone(request.getParameter("done"+orderStatus.getIdOrderStatus()));
        }

        return orderStatus;
    }
}