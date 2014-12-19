package org.taobao88.taobao.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.taobao88.taobao.enterprise.entity.Images;
import org.taobao88.taobao.enterprise.entity.Recomendation;
import org.taobao88.taobao.enterprise.service.ImagesService;
import org.taobao88.taobao.enterprise.service.RecomendationService;

@Controller
@RequestMapping("/admin/pageRedactor")
public class PageRedactorController extends MainController {
	
	@Autowired private RecomendationService recomendationService;
	@Autowired private ImagesService imagesService;
			
	@RequestMapping(method = RequestMethod.GET)
	public String pageRedactor() {
		return "redirect:/admin/pageRedactor/sideMenu";
	}

	@RequestMapping(value = "/deleteImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String deleteImage(HttpServletRequest request) {
		try {
			Images image = imagesService.getImagesById(Integer.parseInt(request.getParameter("imageId")));
			deleteImage(image);
			imagesService.deleteImage(image);
			return "{\"success\":true,\"message\":\"image_deleted\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"success\":false,\"message\":\"deleting_error\"}";
		}
	}
	
	@RequestMapping(value = "/makeImageAsMain", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String makeImageAsMain(@RequestParam ("imageId") int imageId,
												@RequestParam ("recId") int recId) {
		
		Recomendation rec = recomendationService.getRecomendationById(recId);
		Images img = imagesService.getImagesById(imageId);
		if (img != null && rec != null) {
			rec.setPhoto(img.getImageName());
			recomendationService.updateRecomendation(rec);
			return "{\"success\":true, \"message\":\"image_changed\"}";
		}
		return "{\"success\":false, \"message\":\"image_or_rec_is_null\"}";
	}
}
