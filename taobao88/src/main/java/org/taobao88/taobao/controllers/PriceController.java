package org.taobao88.taobao.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.taobao88.taobao.enterprise.dao.GoodsDAO;
import org.taobao88.taobao.enterprise.dao.OrderDAO;
import org.taobao88.taobao.enterprise.dao.PostServiceDAO;
import org.taobao88.taobao.enterprise.dao.UserDAO;
import org.taobao88.taobao.enterprise.entity.OrderT;
import org.taobao88.taobao.enterprise.entity.UserT;
import org.taobao88.taobao.enterprise.service.PriceService;

@Controller
@RequestMapping(value = "/price")
public class PriceController {

	@Autowired private PriceService priceService;
	@Autowired private OrderDAO orderDAO;
	@Autowired private UserDAO userDAO;
	@Autowired private GoodsDAO goodsDAO;
	@Autowired private PostServiceDAO postServiceDAO;
	
	@RequestMapping(value = "adjustPrice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody double addOrder(@RequestParam ("idOrder") int[] orderIds,
									     @RequestParam ("price") double price,
									     @RequestParam ("postServiceId") int postServiceId,
									     HttpServletRequest request) {
		
		List<OrderT> orders = new ArrayList<>();
		for (int id : orderIds) {
			OrderT o = orderDAO.findOrderById(id);
			o.setGoods(goodsDAO.findEmployeeById(o.getIdGoods()));
			orders.add(o);
		}
		
		int userId = (int) request.getSession().getAttribute("currentIdUser");
		UserT user = userDAO.findUserById(userId);
				
		return priceService.getDeliveryPrice(orders, user, price, postServiceDAO.findById(postServiceId));
	}
}