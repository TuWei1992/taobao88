package org.taobao88.taobao.controllers;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.taobao88.taobao.enterprise.entity.Images;
import org.taobao88.taobao.enterprise.entity.Recomendation;
import org.taobao88.taobao.enterprise.entity.RecomendationType;
import org.taobao88.taobao.enterprise.service.RecomendationService;
import org.taobao88.taobao.enterprise.service.RecomendationTypeService;

@Controller
@RequestMapping(value = "/admin/pageRedactor/banner")
public class BannerController extends MainController {
	
	@Autowired private RecomendationTypeService recomendationTypeService;
	@Autowired private RecomendationService recomendationService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		Map<Integer, RecomendationType> types = recomendationTypeService.getRecomendationTypes();		
		model.addAttribute("banner", recomendationService.getAllRecomendations(types.get(5)));
		model.addAttribute("banner_index", true);
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/createBanner", method = RequestMethod.GET)
	public String createBanner(Model model) {
		model.addAttribute("recomendation_type", 5);
		model.addAttribute("banner_create", true);
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/createBanner/doCreate", method = RequestMethod.POST)
	public String doCreate(@RequestParam("rDesc") String desc,
							   @RequestParam("rPrice") String price,
							   @RequestParam("rHref") String href,
							   @RequestParam("rPhoto") MultipartFile file,
							   @RequestParam("recType") int recType) {
		Recomendation rec = new Recomendation();
		rec.setDescription(desc);
		rec.setPrice(Double.parseDouble(price));
		rec.setHref(href);
		rec.setPhoto(file.getOriginalFilename());
		rec.setType(recomendationTypeService.getTypeById(recType));
		createRecomendation(file, rec);
		recomendationService.addRecomendation(rec);
		return "redirect:/admin/pageRedactor/banner";
	}

	@RequestMapping(value = "/updateBanner", method = RequestMethod.GET)
	public String updateBanner(@RequestParam("id") int id, Model model) {
		Recomendation rec = recomendationService.getRecomendationById(id);
		model.addAttribute("rec", rec);
		model.addAttribute("banner_update", true);
		return "pageRedactor";
	}

	@RequestMapping(value = "/updateBanner/doUpdate", method = RequestMethod.POST)
	public String doUpdateBanner(@RequestParam("rDesc") String desc,
			@RequestParam("rPrice") String price,
			@RequestParam("rHref") String href, @RequestParam("id") int id,
			@RequestParam("rPhoto") MultipartFile file) {
		Recomendation rec = recomendationService.getRecomendationById(id);
		rec.setDescription(desc);
		rec.setPrice(Double.parseDouble(price));
		rec.setHref(href);
		if (file.getSize() > 0) {
			rec.setPhoto(file.getOriginalFilename());
			saveUploadedFile(file);
		}
		recomendationService.updateRecomendation(rec);
		return "redirect:/admin/pageRedactor/banner";
	}
	
	@RequestMapping(value = "/deleteBanner", method = RequestMethod.GET)
	public String deleteBanner(@RequestParam("id") int id) {
		Recomendation rec = recomendationService.getRecomendationById(id);
		Set<Images> images = rec.getImages();
		if (images != null || !images.isEmpty()) {
			for (Images img : images) {
				deleteImage(img);
			}
		}
		recomendationService.deleteRecomendation(rec);
		return "redirect:/admin/pageRedactor/banner";
	}

}
