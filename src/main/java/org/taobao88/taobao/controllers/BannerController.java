package org.taobao88.taobao.controllers;

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
	public String index(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
					    HttpServletRequest request, 
					    Model model) {
		Map<Integer, RecomendationType> types = recomendationTypeService.getRecomendationTypes();
		
		int totalCount = recomendationService.getRecomendationsCount(types.get(5));
		int totalPages = (int) totalCount / 54;
		if (totalCount % 54 != 0) {
			totalPages++;
		}
		
		model.addAttribute("curr_page", page);
		model.addAttribute("total_pages", totalPages == 0 ? 1 : totalPages);
		model.addAttribute("banners", recomendationService.getRecomendationsPartial(page, types.get(5)));
		model.addAttribute("banner_index", true);
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
					   @RequestParam ("id") int id, Model model) {
		model.addAttribute("banner", recomendationService.getRecomendationById(id));
		model.addAttribute("banner_view", true);
		model.addAttribute("curr_page", page);
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/createBanner", method = RequestMethod.GET)
	public String createBanner(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
							   Model model) {
		model.addAttribute("recomendation_type", 5);
		model.addAttribute("banner_create", true);
		model.addAttribute("curr_page", page);
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/createBanner/doCreate", method = RequestMethod.POST)
	public String doCreate(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
							@RequestParam("rDesc") String desc,
							   @RequestParam("rPrice") String price,
							   @RequestParam("rHref") String href,
							   @RequestParam("rPhoto") MultipartFile file,
							   @RequestParam("recType") int recType) {
		Recomendation rec = new Recomendation();
		rec.setDescription(desc);
		rec.setPrice(Double.parseDouble(price));
		rec.setHref(href);
		rec.setPhoto(saveUploadedFile(file));
		rec.setType(recomendationTypeService.getTypeById(recType));
		recomendationService.addRecomendation(rec);
		return "redirect:/admin/pageRedactor/banner?page=" + page;
	}

	@RequestMapping(value = "/updateBanner", method = RequestMethod.GET)
	public String updateBanner(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
							   @RequestParam("id") int id, Model model) {
		Recomendation rec = recomendationService.getRecomendationById(id);
		model.addAttribute("rec", rec);
		model.addAttribute("banner_update", true);
		model.addAttribute("curr_page", page);
		return "pageRedactor";
	}

	@RequestMapping(value = "/updateBanner/doUpdate", method = RequestMethod.POST)
	public String doUpdateBanner(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam("rDesc") String desc,
			@RequestParam("rPrice") String price,
			@RequestParam("rHref") String href, @RequestParam("id") int id,
			@RequestParam("rPhoto") MultipartFile file) {
		Recomendation rec = recomendationService.getRecomendationById(id);
		rec.setDescription(desc);
		rec.setPrice(Double.parseDouble(price));
		rec.setHref(href);
		if (file.getSize() > 0) {
			rec.setPhoto(saveUploadedFile(file));
		}
		recomendationService.updateRecomendation(rec);
		return "redirect:/admin/pageRedactor/banner?page=" + page;
	}
	
	@RequestMapping(value = "/deleteBanner", method = RequestMethod.GET)
	public String deleteBanner(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
								 @RequestParam("id") int id) {
		Recomendation rec = recomendationService.getRecomendationById(id);
		Set<Images> images = rec.getImages();
		if (images != null || !images.isEmpty()) {
			for (Images img : images) {
				deleteImage(img);
			}
		}
		recomendationService.deleteRecomendation(rec);
		return "redirect:/admin/pageRedactor/banner?page=" + page;
	}

}
