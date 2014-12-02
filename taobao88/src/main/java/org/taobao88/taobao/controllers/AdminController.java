package org.taobao88.taobao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.taobao88.taobao.enterprise.dao.OrdersStatusesDAO;
import org.taobao88.taobao.enterprise.dao.PackagesStatusesDAO;
import org.taobao88.taobao.enterprise.dao.StatusesDAO;
import org.taobao88.taobao.enterprise.entity.*;
import org.taobao88.taobao.enterprise.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired private StatusesDAO statusesDAO;
    @Autowired private OrdersStatusesDAO ordersStatusesDAO;
    @Autowired private PackagesStatusesDAO packagesStatusesDAO;
    
	@RequestMapping(method = RequestMethod.GET)
	public String adminPage(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("TIME",getCurrentDate());
       
        List<PackageT> packageTList = packageDAO.getPackagesForAdmin();
        packageTList = getListForFirstPage(packageTList,request);

        request.setAttribute("activePackages",packageTList);

        return "activePackages";
	}

    @RequestMapping(value="/orderHistory", method = RequestMethod.GET)
    public String adminHistoryPage(HttpServletRequest request) {
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
    public String saveStatus(@RequestParam ("idOrder") int[] idOrder,
    					     @RequestParam ("statusId") int[] statusId,
    					     @RequestParam ("idPackage") int idPackage,
    					     Model model,
    						 HttpServletRequest request) {
    	UserT user = null;
    	
    	for (int i = 0; i < idOrder.length; i++) {
    		OrderT order = orderDAO.findOrderById(idOrder[i]);
    		user = userDAO.findUserById(order.getIdUser());
    		Status status = statusesDAO.findById(statusId[i]);
    		boolean foundStatus = false;
    		for (OrdersStatuses os : order.getOrdersStatuses()) {
    			if (os != null) {
    				if (os.getStatus().getId() == status.getId()) {
    					foundStatus = true;
    					os.setCreatedAt(new Timestamp(new Date().getTime()));
    					ordersStatusesDAO.update(os);
    				}
    			}
    		}
    		if (!foundStatus) {
    			OrdersStatuses os = new OrdersStatuses();
    			os.setStatus(status);
    			os.setOrderT(order);
    			os.setCreatedAt(new Timestamp(new Date().getTime()));
    			ordersStatusesDAO.add(os);
    		}
    	}
    	
    	PackageT packageT = packageDAO.findPackageById(idPackage);
    	for (OrderT o : packageT.getOrders()) {
    		o.setGoods(goodsDAO.findEmployeeById(o.getIdGoods()));
    	}
    	
    	model.addAttribute("allStatuses", statusesDAO.getAll());
    	model.addAttribute("packageT", packageT);
    	model.addAttribute("userOfCurrentPackage", user);
        
        return "showPackageAdmin";
    }

    @RequestMapping(value="/orderInformation", method = RequestMethod.GET)
    public String orderInformation(HttpServletRequest request) {
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
        request.setAttribute("activePackages",packageDAO.getPackagesForAdmin());
        return "activePackages";
    }

    @RequestMapping(value="/showPackageAdmin", method = RequestMethod.GET)
    public String showPackageAdmin(HttpServletRequest request) {
        int idPackage = Integer.parseInt(request.getParameter("idPackage"));
        
        PackageT packageT = packageDAO.findPackageById(idPackage);
        UserT user = null;
        for (OrderT o : packageT.getOrders()) {
        	if (user == null) {
        		user = userDAO.findUserById(o.getIdUser());
        	}
        	o.setGoods(goodsDAO.findEmployeeById(o.getIdGoods()));
        }
        
        request.setAttribute("allStatuses", statusesDAO.getAll());
        request.setAttribute("packageT", packageT);
        request.setAttribute("userOfCurrentPackage", user);

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
    
    @RequestMapping(value = "packages/update", method = RequestMethod.GET)
    public String updatePackage(@RequestParam ("idPackage") int idPackage,
    							Model model) {
    	model.addAttribute("allStatuses", statusesDAO.getAll());
    	model.addAttribute("packageT", packageDAO.findPackageById(idPackage));    	
    	return "packages/update";
    }
    
    @RequestMapping(value = "packages/doUpdate", method = RequestMethod.POST)
    public String doUpdatePackage(HttpServletRequest request) {
    	
    	int idPackage = Integer.parseInt(request.getParameter("idPackage"));
    	double fullPrice = Double.parseDouble(request.getParameter("fullPrice"));
    	double weight = Double.parseDouble(request.getParameter("weight"));
    	String tracknumber = request.getParameter("tracknumber");
    	int statusId = Integer.parseInt(request.getParameter("statusId"));
    	Status status = statusesDAO.findById(statusId);
    	
    	PackageT p = packageDAO.findPackageById(idPackage);
    	p.setFullPrice(fullPrice);
    	p.setWeight(weight);
    	p.setTracknumber(tracknumber);
    	packageDAO.updatePackage(p);
    	
    	boolean foundStatus = false;
    	for (PackagesStatuses ps : p.getPackagesStatuses()) {
    		if (ps != null) {
    			if (ps.getStatus().getId() == status.getId()) {
    				foundStatus = true;
    				ps.setCreatedAt(new Timestamp(new Date().getTime()));
    				packagesStatusesDAO.update(ps);
    			}
    		}
    	}
    	if (!foundStatus) {
    		PackagesStatuses ps = new PackagesStatuses();
    		ps.setPackageT(p);
    		ps.setStatus(status);
    		ps.setCreatedAt(new Timestamp(new Date().getTime()));
    		packagesStatusesDAO.add(ps);
    	}
    	
    	return "redirect:/admin";
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
