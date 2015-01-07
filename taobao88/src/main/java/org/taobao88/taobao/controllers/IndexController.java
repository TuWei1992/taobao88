package org.taobao88.taobao.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
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
import org.taobao88.taobao.enterprise.dao.CountryRegCityDAO;
import org.taobao88.taobao.enterprise.dao.GoodsDAO;
import org.taobao88.taobao.enterprise.dao.ImagesDAO;
import org.taobao88.taobao.enterprise.dao.OrderDAO;
import org.taobao88.taobao.enterprise.dao.OrderStatusDAO;
import org.taobao88.taobao.enterprise.dao.OrdersStatusesDAO;
import org.taobao88.taobao.enterprise.dao.PostServiceDAO;
import org.taobao88.taobao.enterprise.dao.StatusesDAO;
import org.taobao88.taobao.enterprise.entity.Country;
import org.taobao88.taobao.enterprise.entity.Goods;
import org.taobao88.taobao.enterprise.entity.OrderStatus;
import org.taobao88.taobao.enterprise.entity.OrderT;
import org.taobao88.taobao.enterprise.entity.PostService;
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
	@Autowired private OrderStatusService orderStatusService;
	@Autowired private GoodsDAO goodsDAO;
	@Autowired private PostServiceDAO postServiceDAO;
	@Autowired private ImagesDAO imagesDAO;
	@Autowired private CountryRegCityDAO countryDAO;
	@Autowired private StatusesDAO statusesDAO;
	@Autowired private OrdersStatusesDAO ordersStatusesDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(HttpServletRequest request,
					  Model model){
		
		Map<Integer, RecomendationType> recomendationTypes = recomendationTypeService.getRecomendationTypes();
		List<OrderT> basket = new ArrayList<OrderT>();
		
		model.addAttribute("sideMenu", sideMenuService.getSideMenu("menu_order"));
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
		model.addAttribute("sideMenu", sideMenuService.getSideMenu("menu_order"));
		model.addAttribute("brands", brandsService.getSortedBrands());
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		model.addAttribute("banner", recomendationService.getAllRecomendations(recomendationTypes.get(5)));
		return "index";
	}
	
	@RequestMapping(value = "discount", method = RequestMethod.GET)
	public String discount(Model model) {
		Map<Integer, RecomendationType> recomendationTypes = recomendationTypeService.getRecomendationTypes();
		model.addAttribute("sideMenu", sideMenuService.getSideMenu("menu_order"));
		model.addAttribute("discount", recomendationService.getSortedRecomendations(recomendationTypes.get(2)));
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		model.addAttribute("banner", recomendationService.getAllRecomendations(recomendationTypes.get(5)));
		return "index";
	}
	
	@RequestMapping(value = "free", method = RequestMethod.GET)
	public String free(Model model) {
		Map<Integer, RecomendationType> recomendationTypes = recomendationTypeService.getRecomendationTypes();
		model.addAttribute("sideMenu", sideMenuService.getSideMenu("menu_order"));
		model.addAttribute("free", recomendationService.getSortedRecomendations(recomendationTypes.get(3)));
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		model.addAttribute("banner", recomendationService.getAllRecomendations(recomendationTypes.get(5)));
		return "index";
	}
	
	@RequestMapping(value = "comments", method = RequestMethod.GET)
	public String comments(Model model) {
		Map<Integer, RecomendationType> recomendationTypes = recomendationTypeService.getRecomendationTypes();
		model.addAttribute("sideMenu", sideMenuService.getSideMenu("menu_order"));
		model.addAttribute("comments", recomendationService.getSortedRecomendations(recomendationTypes.get(4)));
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		model.addAttribute("banner", recomendationService.getAllRecomendations(recomendationTypes.get(5)));
		return "index";
	}
	
	@RequestMapping(value = "basket", method = RequestMethod.GET)
	public String basket(HttpServletRequest request, Model model) {
		List<OrderT> orders = orderDAO.getOrdersOnStartPage((int) request.getSession().getAttribute("currentIdUser"));
		for (OrderT o : orders) {
			o.setGoods(goodsDAO.findEmployeeById(o.getIdGoods()));
		}
        model.addAttribute("orders", orders);
        model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		RecomendationType recType = recomendationTypeService.getTypeById(0);
		model.addAttribute("recomendations", recomendationService.getFirstFourRecomendations(recType));
		
		List<Country> countries = countryDAO.getAllCountry();
		List<Country> countriesToJsp = new ArrayList<>();
		List<PostService> allServices = postServiceDAO.getAll();
		for (Country c : countries) {
			if (c.getIdCountry() != 1) {
				countriesToJsp.add(c);
			}
		}
		Map<String, PostService> serviceMap = new HashMap<>();
		for (PostService p : allServices) {
			p.setImage(imagesDAO.getImagesById(p.getImageId()));
			serviceMap.put(p.getServiceName(), p);
		}
		
		model.addAttribute("postServices", serviceMap);
		model.addAttribute("countries", countriesToJsp);
		
		int basket = 0;
		if (!orders.isEmpty()) {
			basket = orders.size();
		}
		request.getSession().setAttribute("basket", basket);
		return "basket";
	}
	
	@RequestMapping(value = "item", method = RequestMethod.GET)
	public String item(@RequestParam ("id") int id, Model model) {
		Map<Integer, RecomendationType> recomendationTypes = recomendationTypeService.getRecomendationTypes();
		Recomendation item = recomendationService.getRecomendationById(id);
		model.addAttribute("item", item);
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		model.addAttribute("banner", recomendationService.getAllRecomendations(recomendationTypes.get(5)));
		return "item";
	}
	
	@RequestMapping(value = "m", method = RequestMethod.GET)
	public String showMenu(@RequestParam ("i") int id, 
						   Model model) {
		model.addAttribute("topMenu", topMenuService.getTopMenuById(id));
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		return "_template";
	}
	
	@RequestMapping(value = "addToBasket", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addToBasket(@RequestParam ("id") int id,
										   HttpServletRequest request,
										   Model model) throws UnsupportedEncodingException {
		
		Recomendation rec = recomendationService.getRecomendationById(id);
		int userId = (int) request.getSession().getAttribute("currentIdUser");
		
		Goods goods = goodsService.convertFromRecomendationToGoods(rec);
		goods.setIdGoods(goodsDAO.saveGoods(goods));
		OrderStatus orderStatus = getOrderStatus();
		orderStatus.setIdOrderStatus(orderStatusService.saveStatus(orderStatus));
				
		OrderT order = new OrderT();
		order.setApprove("false");
        order.setIdUser(userId);
        order.setIdGoods(goods.getIdGoods());
        order.setFullPrice(priceService.getOrderPrice(order));
        order.setIdOrderStatus(orderStatus.getIdOrderStatus());
        goods.setOrderT(order);
        orderDAO.addOrder(order);
        
		List<OrderT> orders = orderDAO.getOrdersOnStartPage(userId);
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
		Goods goods = goodsService.findEmployeeById(id);
		OrderT order = orderDAO.findByGoods(goods);
		order.setGoods(goods);
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		model.addAttribute("orderT", order);		
		return "fill";
	}
	
	@RequestMapping(value = "fillUpdate", method = RequestMethod.POST)
	public String fillUpdate(HttpServletRequest request) throws UnsupportedEncodingException {
		
		int userId = (int) request.getSession().getAttribute("currentIdUser");
		Goods goods = goodsService.findEmployeeById(Integer.parseInt(request.getParameter("idGoods")));
		String photoGoods = request.getParameter("photoGoods");
		if (photoGoods == null) {
			goods.setPhotoGoods("false");
		} else {
			goods.setPhotoGoods("true");
		}
		goods.setColorGoods(request.getParameter("color"));
		goods.setSizeGoods(request.getParameter("size"));
		goods.setAmountGoods(Integer.parseInt(request.getParameter("amount")));
		goodsService.updateEmployee(goods);
		
		OrderT order = orderDAO.findByGoods(goods);
		order.setFullPrice(priceService.getOrderPrice(order));
		orderDAO.updateOrder(order);
		
		List<OrderT> orders = orderDAO.getOrdersOnStartPage(userId);
		request.getSession().setAttribute("basket", orders.size());
		return "redirect:/basket";
	}
	
	@RequestMapping(value = "/storeOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String storeOrder(HttpServletRequest request) {
		
		Enumeration<String> attributes = request.getParameterNames();
		while (attributes.hasMoreElements()) {
			String parameter = attributes.nextElement();
			request.getSession().setAttribute(parameter, request.getParameter(parameter));
		}
		
		return null;
	}
}

