package org.taobao88.taobao.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.taobao88.taobao.beans.OrderBEAN;
import org.taobao88.taobao.enterprise.DAO.GoodsDAO;
import org.taobao88.taobao.enterprise.DAO.OrderDAO;
import org.taobao88.taobao.enterprise.DAO.OrderStatusDAO;
import org.taobao88.taobao.enterprise.entity.Goods;
import org.taobao88.taobao.enterprise.entity.OrderT;
import org.taobao88.taobao.enterprise.entity.Recomendation;
import org.taobao88.taobao.enterprise.entity.RecomendationType;
import org.taobao88.taobao.enterprise.service.*;

@Controller
@SessionAttributes("basket")
@RequestMapping(value = "/")
public class IndexController extends MainController {

	@Autowired private SideMenuService sideMenuService;
	@Autowired private RecomendationService recomendationService;
	@Autowired private RecomendationTypeService recomendationTypeService;
	@Autowired private BrandsService brandsService;
	@Autowired private TopMenuService topMenuService;
	@Autowired private GoodsService goodsService;
	@Autowired private PriceService priceService;
	@Autowired private UserService userService;
	@Autowired private OrderService orderService;
	@Autowired private OrderDAO orderDAO;
	@Autowired private OrderStatusDAO orderStatusDAO;
	@Autowired private GoodsDAO goodsDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(HttpServletRequest request,
					  Model model){
		
		Map<Integer, RecomendationType> recomendationTypes = recomendationTypeService.getRecomendationTypes();
		List<OrderT> basket = new ArrayList<OrderT>();
		
		model.addAttribute("sideMenu", sideMenuService.getSideMenu());
		model.addAttribute("recomendations", recomendationService.getSortedRecomendations(recomendationTypes.get(0)));
		model.addAttribute("slider", recomendationService.getAllRecomendations(recomendationTypes.get(1)));
		model.addAttribute("banner", recomendationService.getAllRecomendations(recomendationTypes.get(5)));
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		request.getSession().setAttribute("basket", basket.size());
		return "index";
	}
	
	@RequestMapping(value = "brands", method = RequestMethod.GET)
	public String brands(Model model) {
		Map<Integer, RecomendationType> recomendationTypes = recomendationTypeService.getRecomendationTypes();
		model.addAttribute("sideMenu", sideMenuService.getSideMenu());
		model.addAttribute("brands", brandsService.getSortedBrands());
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		model.addAttribute("banner", recomendationService.getAllRecomendations(recomendationTypes.get(5)));
		return "index";
	}
	
	@RequestMapping(value = "discount", method = RequestMethod.GET)
	public String discount(Model model) {
		Map<Integer, RecomendationType> recomendationTypes = recomendationTypeService.getRecomendationTypes();
		model.addAttribute("sideMenu", sideMenuService.getSideMenu());
		model.addAttribute("discount", recomendationService.getSortedRecomendations(recomendationTypes.get(2)));
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		model.addAttribute("banner", recomendationService.getAllRecomendations(recomendationTypes.get(5)));
		return "index";
	}
	
	@RequestMapping(value = "free", method = RequestMethod.GET)
	public String free(Model model) {
		Map<Integer, RecomendationType> recomendationTypes = recomendationTypeService.getRecomendationTypes();
		model.addAttribute("sideMenu", sideMenuService.getSideMenu());
		model.addAttribute("free", recomendationService.getSortedRecomendations(recomendationTypes.get(3)));
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		model.addAttribute("banner", recomendationService.getAllRecomendations(recomendationTypes.get(5)));
		return "index";
	}
	
	@RequestMapping(value = "comments", method = RequestMethod.GET)
	public String comments(Model model) {
		Map<Integer, RecomendationType> recomendationTypes = recomendationTypeService.getRecomendationTypes();
		model.addAttribute("sideMenu", sideMenuService.getSideMenu());
		model.addAttribute("comments", recomendationService.getSortedRecomendations(recomendationTypes.get(4)));
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		model.addAttribute("banner", recomendationService.getAllRecomendations(recomendationTypes.get(5)));
		return "index";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "basket", method = RequestMethod.GET)
	public String basket(HttpServletRequest request, Model model) {
		List<Goods> basket = (List<Goods>) request.getSession().getAttribute("basket");
		double totalPrice = 0;
		for (Goods good : basket) {
			totalPrice += good.getPriceGoods();
		}
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("basket", request.getSession().getAttribute("basket"));
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		RecomendationType recType = recomendationTypeService.getTypeById(0);
		model.addAttribute("recomendations", recomendationService.getFirstFourRecomendations(recType));
		return "basket";
	}
	
	@RequestMapping(value = "item", method = RequestMethod.GET)
	public String item(@RequestParam ("id") int id, Model model) {
		model.addAttribute("item", recomendationService.getRecomendationById(id));
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		return "item";
	}
	
	@RequestMapping(value = "m", method = RequestMethod.GET)
	public String showMenu(@RequestParam ("i") int id, 
						   Model model) {
		model.addAttribute("topMenu", topMenuService.getTopMenuById(id));
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		return "_template";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addToBasket", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addToBasket(@RequestParam ("id") int id,
										   HttpServletRequest request,
										   Model model) throws UnsupportedEncodingException {
		
		Recomendation rec = recomendationService.getRecomendationById(id);
		int goodsId = goodsService.saveGoods(goodsService.convertFromRecomendationToGoods(rec));
		int userId = (int) request.getSession().getAttribute("currentIdUser");
		
		Goods goods = goodsService.findEmployeeById(goodsId);
		List<OrderT> newOrders = allOrdersForOneRequest(goods.getAmountGoods(), goods, userId);
		List<OrderT> orders = orderDAO.getOrdersOnStartPage(userId);
        List<OrderBEAN> orderBEANList = getOrders(orders, goodsDAO.getAll(orders), orderStatusDAO.getOrdersStatuses(orders));
        Collections.sort(orderBEANList, Collections.reverseOrder());
        orderBEANList = getListForFirstPageOrder(orderBEANList,request);
        
        request.setAttribute("orders", orderBEANList);
        request.setAttribute("newOrders",newOrders);
//		prepareOrdersFromGoods(goodsService.findEmployeeById(goodsId), userService.findUserById(userId));
		
//		List<Goods> basket = (List<Goods>) request.getSession().getAttribute("basket");
//		if (basket == null || basket.isEmpty()) {
//			basket = new ArrayList<Goods>();
//		} else {
//			for (Goods good : basket) {
//				if (good.getRecomendation().getId() == id) {
//					return "already_in_basket";
//				}
//			}
//		}
//		basket.add(goodsService.findEmployeeById(goodsId));
		model.addAttribute("basket", orders.size());
			
		return orders.size() + "";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "removeFromBasket", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody void removeFromBasket(@RequestParam ("id") int id, 
			                                   HttpServletRequest request, 
			                                   Model model) {
		List<Goods> basket = (List<Goods>) request.getSession().getAttribute("basket");
		Iterator<Goods> iterator = basket.iterator();
		while(iterator.hasNext()) {
			Goods good = iterator.next();
			if (good.getIdGoods() == id) {
				iterator.remove();
				OrderT order = orderDAO.findByGoods(good);
				orderDAO.deleteOrder(order.getIdOrder());
	        	orderStatusDAO.deleteOrderStatus(order.getIdOrderStatus());
	        	goodsDAO.deleteGood(order.getIdGoods());
			}
		}
		model.addAttribute("basket", basket.size());
	}
	
	@RequestMapping(value = "fill", method = RequestMethod.GET)
	public String fill(@RequestParam ("id") int id, HttpServletRequest request, Model model) {
		Goods good = goodsService.findEmployeeById(id);
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		model.addAttribute("good", good);		
		return "fill";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "fillUpdate", method = RequestMethod.POST)
	public String fillUpdate(@RequestParam ("idGoods") int id, 
							 @RequestParam ("color") String color, 
							 @RequestParam ("size") String size,
							 @RequestParam ("count") int count,
							 HttpServletRequest request) throws UnsupportedEncodingException {
		
		boolean photoGoods = false;
		if (request.getAttribute("photoGoods") != null) {
			photoGoods = true;
		}
		
		Goods good = goodsService.findEmployeeById(id);
		List<Goods> basket = (List<Goods>) request.getSession().getAttribute("basket");
		basket.remove(good);
		good.setColorGoods(color);
		good.setSizeGoods(size);
		good.setAmountGoods(count);
		good.setPriceGoods(priceService.getPriceOfOrder(good.getAmountGoods(), good.getRecomendation().getPrice()));
		if (photoGoods) {
			good.setPhotoGoods("true");
		} else {
			good.setPhotoGoods("false");
		}
		goodsService.updateEmployee(good);
		
		basket.add(goodsService.findEmployeeById(id));
		request.getSession().setAttribute("basket", basket.size());
		return "redirect:basket";
	}
}

