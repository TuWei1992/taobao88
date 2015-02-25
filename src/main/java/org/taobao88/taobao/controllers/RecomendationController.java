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
@RequestMapping(value = "/admin/pageRedactor/recomendation")
public class RecomendationController extends MainController {

	@Autowired private RecomendationTypeService recomendationTypeService;
	@Autowired private RecomendationService recomendationService;
	@Autowired private ImagesService imagesService;
	@Autowired private ColorsService colorsService;
	@Autowired private SizesService sizesService;
	private PageRedactorValidator validator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(@RequestParam(value = "page", required = false, defaultValue = "1") int page, 
					    Model model, 
					    HttpServletRequest request) {
		Map<Integer, RecomendationType> types = recomendationTypeService.getRecomendationTypes();
		
		int totalCount = recomendationService.getRecomendationsCount(types.get(0));
		int totalPages = (int) totalCount / 54;
		if (totalCount % 54 != 0) {
			totalPages++;
		}
				
		model.addAttribute("curr_page", page);
		model.addAttribute("total_pages", totalPages == 0 ? 1 : totalPages);
		
		model.addAttribute("recomendations", recomendationService.getRecomendationsPartial(page, types.get(0)));
		model.addAttribute("recomendation_index", true);
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(@RequestParam(value = "page", required = false, defaultValue = "1") int page, 
					   @RequestParam ("id") int id, Model model) {
		model.addAttribute("rec", recomendationService.getRecomendationById(id));
		model.addAttribute("recomendation_view", true);
		model.addAttribute("curr_page", page);
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/createRecomendation", method = RequestMethod.GET)
	public String createRecomendation(@RequestParam(value = "page", required = false, defaultValue = "1") int page, 
									  Model model) {
		model.addAttribute("recomendation_type", 0);
		model.addAttribute("recomendation_create", true);
		model.addAttribute("curr_page", page);
		return "pageRedactor";
	}

	@RequestMapping(value = "/createRecomendation/doCreate", method = RequestMethod.POST)
	public String doCreate(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
							@RequestParam("rPhoto") MultipartFile[] files,
			         		HttpServletRequest request,
			         		Model model) {
		
		validator = new PageRedactorValidator();
		
		List<String> errors = validator.validateCreateRecomendation(request);
		if (errors.size() != 0) {
			model.addAttribute("errors", toJSArray(errors.toArray()));
			model.addAttribute("recomendationTypes", getRecomendationTypeList());
			model.addAttribute("recomendation_create", true);
			model.addAttribute("curr_page", page);
			return "pageRedactor";
		}
		
		try {
			Recomendation rec = new Recomendation();
			rec.setDescription(validator.getString("rDesc"));
			rec.setLongDescription(validator.getString("rDescLong"));
			rec.setPrice(validator.getDouble("rPrice"));
			rec.setHref(validator.getString("rHref"));
			rec.setPhoto(saveUploadedFile(files[0]));
			rec.setType(recomendationTypeService.getTypeById(validator.getInt("rType")));
			rec.setColors(colorsService.prepareColorsFromString(validator.getString("rColor")));
			rec.setSizes(sizesService.prepareSizesFromString(validator.getString("rSize")));
			rec.setWeight(validator.getDouble("rWeight"));
			rec.setCount(validator.getInt("rCount"));
			createRecomendation(files, rec);
			recomendationService.addRecomendation(rec);
			return "redirect:/admin/pageRedactor/recomendation?page=" + page;
		} catch (Exception e) {
			model.addAttribute("unknown_error", true);
			model.addAttribute("recomendationTypes", getRecomendationTypeList());
			model.addAttribute("recomendation_create", true);
			model.addAttribute("curr_page", page);
			return "pageRedactor";
		}
	}

	@RequestMapping(value = "/deleteRecomendation", method = RequestMethod.GET)
	public String deleteRecomendation(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
									  @RequestParam("id") int id) {
		Recomendation rec = recomendationService.getRecomendationById(id);
		Set<Images> images = rec.getImages();
		if (images != null || !images.isEmpty()) {
			for (Images img : images) {
				deleteImage(img);
			}
		}
		recomendationService.deleteRecomendation(rec);
		return "redirect:/admin/pageRedactor/recomendation?page=" + page;
	}

	@RequestMapping(value = "/updateRecomendation", method = RequestMethod.GET)
	public String updateRecomendation(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
									  @RequestParam("id") int id, Model model) {
		Recomendation rec = recomendationService.getRecomendationById(id);
		model.addAttribute("rec", rec);
		model.addAttribute("recomendation_update", true);
		model.addAttribute("curr_page", page);
		return "pageRedactor";
	}

	@RequestMapping(value = "/updateRecomendation/doUpdate", method = RequestMethod.POST)
	public String doUpdate(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
						   @RequestParam("rPhoto") MultipartFile[] files,
						   @RequestParam("id") int id,
						   HttpServletRequest request,
						   Model model) {
		
		validator = new PageRedactorValidator();
		
		List<String> errors = validator.validateUpdateRecomendation(request);
		if (errors.size() != 0) {
			model.addAttribute("errors", toJSArray(errors.toArray()));
			Recomendation rec = recomendationService.getRecomendationById(id);
			model.addAttribute("rec", rec);
			model.addAttribute("recomendation_update", true);
			model.addAttribute("curr_page", page);
			return "pageRedactor";
		}
		
		try {
			Recomendation rec = recomendationService.getRecomendationById(id);
			rec.setDescription(validator.getString("rDesc"));
			rec.setLongDescription(validator.getString("rDescLong"));
			rec.setPrice(validator.getDouble("rPrice"));
			rec.setHref(validator.getString("rHref"));
			rec.setColors(colorsService.prepareColorsFromString(validator.getString("rColor")));
			rec.setSizes(sizesService.prepareSizesFromString(validator.getString("rSize")));
			rec.setCount(validator.getInt("rCount"));
			rec.setWeight(validator.getDouble("rWeight"));
			if (files.length > 0) {
				createRecomendation(files, rec);
			}
			recomendationService.updateRecomendation(rec);
			return "redirect:/admin/pageRedactor/recomendation?page=" + page;
		} catch (Exception e) {
			model.addAttribute("unknown_error", true);
			model.addAttribute("recomendation_update", true);
			model.addAttribute("curr_page", page);
			return "pageRedactor";
		}
	}
	
}
