package org.taobao88.taobao.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.taobao88.taobao.enterprise.entity.Recomendation;
import org.taobao88.taobao.enterprise.entity.RecomendationType;
import org.taobao88.taobao.enterprise.service.RecomendationService;
import org.taobao88.taobao.enterprise.service.RecomendationTypeService;

@Controller
@RequestMapping(value = "/admin/pageRedactor/slider")
public class SliderController extends MainController {
	
	@Autowired private RecomendationTypeService recomendationTypeService;
	@Autowired private RecomendationService recomendationService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		Map<Integer, RecomendationType> types = recomendationTypeService.getRecomendationTypes();
		model.addAttribute("slider", recomendationService.getAllRecomendations(types.get(1)));
		model.addAttribute("slider_index", true);
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/createSlider", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("slider_create", true);
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/createSlider/doCreate", method = RequestMethod.POST)
	public String createSlider(@RequestParam("rDesc") String desc,
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
		return "redirect:/admin/pageRedactor/slider";
	}
	
	@RequestMapping(value = "/deleteSlider", method = RequestMethod.GET)
	public String delete(@RequestParam("id") int id, Model model) {
		Recomendation rec = recomendationService.getRecomendationById(id);
		recomendationService.deleteRecomendation(rec);
		return "redirect:/admin/pageRedactor/slider";
	}
	
	@RequestMapping(value = "/updateSlider", method = RequestMethod.GET)
	public String update(@RequestParam("id") int id, Model model) {
		model.addAttribute("rec", recomendationService.getRecomendationById(id));
		model.addAttribute("slider_update", true);
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/updateSlider/doUpdate", method = RequestMethod.POST)
	public String doUpdateBanner(@RequestParam("rDesc") String desc,
								 @RequestParam("rPrice") String price,
								 @RequestParam("rHref") String href, 
								 @RequestParam("id") int id,
								 @RequestParam("rPhoto") MultipartFile file) {
		Recomendation rec = recomendationService.getRecomendationById(id);
		rec.setDescription(desc);
		rec.setPrice(Double.parseDouble(price));
		rec.setHref(href);
		if (file.getSize() > 0) {
			rec.setPhoto(saveUploadedFile(file));
		}
		recomendationService.updateRecomendation(rec);
		return "redirect:/admin/pageRedactor/slider";
	}
}
