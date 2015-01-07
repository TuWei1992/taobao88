package org.taobao88.taobao.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.taobao88.taobao.controllers.validators.PageRedactorValidator;
import org.taobao88.taobao.enterprise.entity.Brands;
import org.taobao88.taobao.enterprise.entity.Images;
import org.taobao88.taobao.enterprise.service.BrandsService;
import org.taobao88.taobao.enterprise.service.ImagesService;

@Controller
@RequestMapping(value = "/admin/pageRedactor/brands")
public class BrandsController extends MainController {
	
	@Autowired private BrandsService brandsService;
	@Autowired private ImagesService imagesService;
	private PageRedactorValidator validator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(@RequestParam(value = "page", required = false, defaultValue = "1") int page, 
					    HttpServletRequest request, Model model) {
		
		int totalCount = brandsService.getBrandsCount();
		int totalPages = (int) totalCount / 54;
		if (totalCount % 54 != 0) {
			totalPages++;
		}
				
		model.addAttribute("curr_page", page);
		model.addAttribute("total_pages", totalPages == 0 ? 1 : totalPages);
		model.addAttribute("brands", brandsService.getBrandsPartial(page));
		model.addAttribute("brands_index", true);
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(@RequestParam(value = "page", required = false, defaultValue = "1") int page, 
					   @RequestParam ("id") int id, 
					   Model model) {
		model.addAttribute("brand", brandsService.getBrandById(id));
		model.addAttribute("brands_view", true);
		model.addAttribute("curr_page", page);
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/createBrand", method = RequestMethod.GET)
	public String create(@RequestParam(value = "page", required = false, defaultValue = "1") int page, 
			             Model model) {
		model.addAttribute("brands_create", true);
		model.addAttribute("curr_page", page);
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/createBrand/doCreate", method = RequestMethod.POST)
	public String doCreate(@RequestParam("bDesc") String desc,
						   @RequestParam("bPhoto") MultipartFile file,
						   @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		Brands brand = new Brands();
		brand.setBrandName(desc);
		Images image = new Images();
		image.setImageName(saveUploadedFile(file));
		image.setImageId(imagesService.addImage(image));
		brand.setBrandImage(image.getImageId());
		brand.setImage(image);
		brandsService.addBrand(brand);
		return "redirect:/admin/pageRedactor/brands?page=" + page;
	}

	@RequestMapping(value = "/deleteBrand", method = RequestMethod.GET)
	public String deleteBrand(@RequestParam("id") int id,
							  @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		Brands brand = brandsService.getBrandById(id);
		deleteImage(brand.getImage());
		brandsService.deleteBrand(brand);
		return "redirect:/admin/pageRedactor/brands?page=" + page;
	}

	@RequestMapping(value = "/updateBrand", method = RequestMethod.GET)
	public String updateBrand(@RequestParam("id") int id,
						      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
							  Model model) {
		Brands brand = brandsService.getBrandById(id);
		model.addAttribute("brand", brand);
		model.addAttribute("brands_update", true);
		model.addAttribute("curr_page", page);
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/updateBrand/doUpdate", method = RequestMethod.POST)
	public String doUpdateBrand(@RequestParam("id") int id,
							    @RequestParam("bPhoto") MultipartFile file,
							    @RequestParam(value = "page", required = false, defaultValue = "1") int page,
							    HttpServletRequest request,
							    Model model) {
		
		try {
			Brands brand = brandsService.getBrandById(id);
			validator = new PageRedactorValidator();
			Map<String, Object> map = new HashMap<String, Object>();
			
			List<String> errors = validator.validateUpdateBrand(request, map);
			if (errors.size() != 0) {
				model.addAttribute("errors", toJSArray(errors.toArray()));
				model.addAttribute("brands_update", true);
				model.addAttribute("curr_page", page);
				return "pageRedactor";
			}
			
			if (file.getSize() > 0) {
				Images image = new Images();
				image.setImageName(saveUploadedFile(file));
				image.setImageId(imagesService.addImage(image));
				brand.setBrandImage(image.getImageId());
				brand.setImage(image);
			}
			
			brand.setBrandName((String) map.get("bDesc")); 
			brandsService.updateBrand(brand);
			return "redirect:/admin/pageRedactor/brands?page=" + page;
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("unknown_error", true);
			model.addAttribute("brands_update", true);
			model.addAttribute("curr_page", page);
			return "pageRedactor";
		}		
	}

}
