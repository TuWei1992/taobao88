package org.taobao88.taobao.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.taobao88.taobao.controllers.validators.PageRedactorValidator;
import org.taobao88.taobao.enterprise.entity.Images;
import org.taobao88.taobao.enterprise.entity.Recomendation;
import org.taobao88.taobao.enterprise.entity.RecomendationType;
import org.taobao88.taobao.enterprise.service.ColorsService;
import org.taobao88.taobao.enterprise.service.ImagesService;
import org.taobao88.taobao.enterprise.service.RecomendationService;
import org.taobao88.taobao.enterprise.service.RecomendationTypeService;
import org.taobao88.taobao.enterprise.service.SizesService;

@Controller
@RequestMapping(value = "/admin/pageRedactor/freeShip")
public class FreeShippingController extends MainController {
	
	@Autowired private RecomendationTypeService recomendationTypeService;
	@Autowired private RecomendationService recomendationService;
	@Autowired private ImagesService imagesService;
	@Autowired private ColorsService colorsService;
	@Autowired private SizesService sizesService;
	private PageRedactorValidator validator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		Map<Integer, RecomendationType> types = recomendationTypeService.getRecomendationTypes();		
		model.addAttribute("free_ship", recomendationService.getAllRecomendations(types.get(3)));
		model.addAttribute("free_ship_index", true);
		return "pageRedactor";		
	}
	
	@RequestMapping(value = "/createRecomendation", method = RequestMethod.GET)
	public String createRecomendation(Model model) {
		model.addAttribute("recomendation_type", 3);
		model.addAttribute("free_ship_create", true);
		return "pageRedactor";
	}

	@RequestMapping(value = "/createRecomendation/doCreate", method = RequestMethod.POST)
	public String doCreate(@RequestParam("rPhoto") MultipartFile[] files,
			         		HttpServletRequest request,
			         		Model model) {
		
		validator = new PageRedactorValidator();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<String> errors = validator.validateCreateRecomendation(request, map);
		if (errors.size() != 0) {
			model.addAttribute("errors", toJSArray(errors.toArray()));
			model.addAttribute("free_ship_create", true);
			return "pageRedactor";
		}
		
		try {
			Recomendation rec = new Recomendation();
			rec.setDescription((String) map.get("rDesc"));
			rec.setLongDescription((String) map.get("rDescLong"));
			rec.setPrice((double) map.get("rPrice"));
			rec.setHref((String) map.get("rHref"));
			rec.setPhoto(files[0].getOriginalFilename());
			rec.setType(recomendationTypeService.getTypeById((int) map.get("rType")));
			rec.setColors(colorsService.prepareColorsFromString((String) map.get("rColor")));
			rec.setSizes(sizesService.prepareSizesFromString((String) map.get("rSize")));
			rec.setWeight((double) map.get("rWeight"));
			rec.setCount((int) map.get("rCount"));
			createRecomendation(files, rec);
			recomendationService.addRecomendation(rec);
			return "redirect:/admin/pageRedactor/freeShip";
		} catch (Exception e) {
			model.addAttribute("unknown_error", true);
			model.addAttribute("free_ship_create", true);
			return "pageRedactor";
		}
	}

	@RequestMapping(value = "/deleteRecomendation", method = RequestMethod.GET)
	public String deleteRecomendation(@RequestParam("id") int id) {
		Recomendation rec = recomendationService.getRecomendationById(id);
		Set<Images> images = rec.getImages();
		if (images != null || !images.isEmpty()) {
			for (Images img : images) {
				deleteImage(img);
			}
		}
		recomendationService.deleteRecomendation(rec);
		return "redirect:/admin/pageRedactor/freeShip";
	}

	@RequestMapping(value = "/updateRecomendation", method = RequestMethod.GET)
	public String updateRecomendation(@RequestParam("id") int id, Model model) {
		Recomendation rec = recomendationService.getRecomendationById(id);
		model.addAttribute("rec", rec);
		model.addAttribute("free_ship_update", true);
		return "pageRedactor";
	}

	@RequestMapping(value = "/updateRecomendation/doUpdate", method = RequestMethod.POST)
	public String doUpdate(@RequestParam("rPhoto") MultipartFile[] files,
						   @RequestParam("id") int id,
						   HttpServletRequest request,
						   Model model) {
		
		validator = new PageRedactorValidator();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<String> errors = validator.validateUpdateRecomendation(request, map);
		if (errors.size() != 0) {
			model.addAttribute("errors", toJSArray(errors.toArray()));
			Recomendation rec = recomendationService.getRecomendationById(id);
			model.addAttribute("rec", rec);
			model.addAttribute("free_ship_update", true);
			return "pageRedactor";
		}
		
		try {
			Recomendation rec = recomendationService.getRecomendationById(id);
			rec.setDescription((String) map.get("rDesc"));
			rec.setLongDescription((String) map.get("rDescLong"));
			rec.setPrice((double) map.get("rPrice"));
			rec.setHref((String) map.get("rHref"));
			rec.setColors(colorsService.prepareColorsFromString((String) map.get("rColor")));
			rec.setSizes(sizesService.prepareSizesFromString((String) map.get("rSize")));
			rec.setCount((int) map.get("rCount"));
			rec.setWeight((double) map.get("rWeight"));
			if (files.length > 0) {
				createRecomendation(files, rec);
			}
			recomendationService.updateRecomendation(rec);
			return "redirect:/admin/pageRedactor/freeShip";
		} catch (Exception e) {
			model.addAttribute("unknown_error", true);
			model.addAttribute("freeShip_update", true);
			return "pageRedactor";
		}
	}

}
