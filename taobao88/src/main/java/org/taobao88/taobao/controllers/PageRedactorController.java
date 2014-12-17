package org.taobao88.taobao.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.taobao88.taobao.controllers.validators.PageRedactorValidator;
import org.taobao88.taobao.enterprise.dao.PagesContentDAO;
import org.taobao88.taobao.enterprise.entity.Brands;
import org.taobao88.taobao.enterprise.entity.Images;
import org.taobao88.taobao.enterprise.entity.PageContent;
import org.taobao88.taobao.enterprise.entity.Recomendation;
import org.taobao88.taobao.enterprise.entity.RecomendationType;
import org.taobao88.taobao.enterprise.entity.SideMenu;
import org.taobao88.taobao.enterprise.entity.TopMenu;
import org.taobao88.taobao.enterprise.service.BrandsService;
import org.taobao88.taobao.enterprise.service.ColorsService;
import org.taobao88.taobao.enterprise.service.ImagesService;
import org.taobao88.taobao.enterprise.service.RecomendationService;
import org.taobao88.taobao.enterprise.service.RecomendationTypeService;
import org.taobao88.taobao.enterprise.service.SideMenuService;
import org.taobao88.taobao.enterprise.service.SizesService;
import org.taobao88.taobao.enterprise.service.TopMenuService;

@Controller
@RequestMapping("/admin/pageRedactor")
public class PageRedactorController extends MainController {
	@Autowired
	private RecomendationTypeService recomendationTypeService;
	@Autowired
	private RecomendationService recomendationService;
	@Autowired
	private SideMenuService sideMenuService;
	@Autowired
	private BrandsService brandsService;
	@Autowired
	private TopMenuService topMenuService;
	@Autowired
	private ImagesService imagesService;
	@Autowired
	private ColorsService colorsService;
	@Autowired
	private SizesService sizesService;
	@Autowired
	private PagesContentDAO pagesContentDAO;
	
	private PageRedactorValidator validator;

	@RequestMapping(method = RequestMethod.GET)
	public String pageRedactor(Model model) {
		Map<Integer, RecomendationType> types = recomendationTypeService
				.getRecomendationTypes();
		model.addAttribute("side_menu", sideMenuService.getSideMenu());
		model.addAttribute("recomendations",
				recomendationService.getAllRecomendations(types.get(0)));
		model.addAttribute("slider",
				recomendationService.getAllRecomendations(types.get(1)));
		model.addAttribute("banner",
				recomendationService.getAllRecomendations(types.get(5)));
		model.addAttribute("discount",
				recomendationService.getAllRecomendations(types.get(2)));
		model.addAttribute("free_ship",
				recomendationService.getAllRecomendations(types.get(3)));
		model.addAttribute("comments",
				recomendationService.getAllRecomendations(types.get(4)));
		model.addAttribute("brands", brandsService.getAllBrands());
		model.addAttribute("privateOffice",
				pagesContentDAO.findContentByPageName("privateOffice"));
		List<RecomendationType> typesList = recomendationTypeService
				.getRecomendationTypesAsList();
		Iterator<RecomendationType> iterator = typesList.iterator();
		while (iterator.hasNext()) {
			RecomendationType type = iterator.next();
			if (type.getTypeId() == 1 || type.getTypeId() == 4) {
				iterator.remove();
			}
		}
		model.addAttribute("recomendationTypes", typesList);
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		return "pageRedactor";
	}

	@RequestMapping(value = "/createMenu", method = RequestMethod.POST)
	public String createMenu(@RequestParam("menuHref") String menuHref,
			@RequestParam("menuName") String menuName,
			@RequestParam("menuOrder") int menuOrder,
			@RequestParam("parentId") int parentId, Model model) {
		SideMenu sideMenu = new SideMenu();
		sideMenu.setMenuHref(menuHref);
		sideMenu.setMenuName(menuName);
		sideMenu.setMenuOrder(menuOrder);
		sideMenu.setParentId(parentId);
		sideMenuService.addSideMenu(sideMenu);
		return "redirect:/admin/pageRedactor";
	}

	@RequestMapping(value = "/deleteMenu", method = RequestMethod.GET)
	public String deleteMenu(@RequestParam("id") int id, Model model) {
		sideMenuService.deleteSideMenu(sideMenuService.getSideMenuById(id));
		return "redirect:/admin/pageRedactor";
	}

	@RequestMapping(value = "/updateMenu", method = RequestMethod.GET)
	public String updateMenu(@RequestParam("id") int id, Model model) {
		model.addAttribute("menu", sideMenuService.getSideMenuById(id));
		return "sideMenuUpdate";
	}

	@RequestMapping(value = "/updateMenu/doUpdate", method = RequestMethod.POST)
	public String doUpdateSideMenu(@RequestParam("id") int id,
			@RequestParam("menuName") String menuName,
			@RequestParam("menuHref") String menuHref,
			@RequestParam("menuOrder") int menuOrder) {
		SideMenu sideMenu = sideMenuService.getSideMenuById(id);
		sideMenu.setMenuName(menuName);
		sideMenu.setMenuHref(menuHref);
		sideMenu.setMenuOrder(menuOrder);
		sideMenuService.updateSideMenu(sideMenu);
		return "redirect:/admin/pageRedactor";
	}

	@RequestMapping(value = "/createRecomendation", method = RequestMethod.GET)
	public String createRecomendation(Model model) {
		List<RecomendationType> typesList = recomendationTypeService
				.getRecomendationTypesAsList();
		Iterator<RecomendationType> iterator = typesList.iterator();
		while (iterator.hasNext()) {
			RecomendationType type = iterator.next();
			if (type.getTypeId() == 1 || type.getTypeId() == 4) {
				iterator.remove();
			}
		}
		model.addAttribute("recomendationTypes", typesList);
		return "recomendations/create";
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
			model.addAttribute("recomendationTypes", getRecomendationTypeList());
			return "recomendations/create";
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
			return "redirect:/admin/pageRedactor";
		} catch (Exception e) {
			model.addAttribute("unknown_error", true);
			model.addAttribute("recomendationTypes", getRecomendationTypeList());
			return "recomendations/create";
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
		return "redirect:/admin/pageRedactor";
	}

	@RequestMapping(value = "/updateRecomendation", method = RequestMethod.GET)
	public String updateRecomendation(@RequestParam("id") int id, Model model) {
		Recomendation rec = recomendationService.getRecomendationById(id);
		model.addAttribute("rec", rec);
		return "recomendations/update";
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
			return "recomendations/update";
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
			return "redirect:/admin/pageRedactor";
		} catch (Exception e) {
			model.addAttribute("unknown_error", true);
			return "recomendations/update";
		}
	}

	@RequestMapping(value = "/createSlider", method = RequestMethod.POST)
	public String createSlider(@RequestParam("rDesc") String desc,
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
		return "redirect:/admin/pageRedactor";
	}

	@RequestMapping(value = "/createBanner", method = RequestMethod.POST)
	public String createBanner(@RequestParam("rDesc") String desc,
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
		return "redirect:/admin/pageRedactor";
	}

	@RequestMapping(value = "/updateBanner", method = RequestMethod.GET)
	public String updateBanner(@RequestParam("id") int id, Model model) {
		Recomendation rec = recomendationService.getRecomendationById(id);
		model.addAttribute("rec", rec);
		return "bannerUpdate";
	}

	@RequestMapping(value = "/doUpdateBanner", method = RequestMethod.POST)
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
		return "redirect:/admin/pageRedactor";
	}

	@RequestMapping(value = "/createBrand", method = RequestMethod.POST)
	public String createBrand(@RequestParam("bDesc") String desc,
			@RequestParam("bPhoto") MultipartFile file) {
		Brands brand = new Brands();
		brand.setBrandName(desc);
		Images image = new Images();
		image.setImageName(file.getOriginalFilename());
		image.setImageId(imagesService.addImage(image));
		brand.setBrandImage(image.getImageId());
		brand.setImage(image);
		saveUploadedFile(file);
		brandsService.addBrand(brand);
		return "redirect:/admin/pageRedactor";
	}

	@RequestMapping(value = "/deleteBrand", method = RequestMethod.GET)
	public String deleteBrand(@RequestParam("id") int id) {
		Brands brand = brandsService.getBrandById(id);
		deleteImage(brand.getImage());
		brandsService.deleteBrand(brand);
		return "redirect:/admin/pageRedactor";
	}

	@RequestMapping(value = "/updateBrand", method = RequestMethod.POST)
	public String updateBrand(@RequestParam("bDesc") String desc,
			@RequestParam("id") int id) {
		return "redirect:/admin/pageRedactor";
	}

	@RequestMapping(value = "/createTopMenu", method = RequestMethod.GET)
	public String createTopMenu(Model model) {
		return "top_menus/create";
	}

	@RequestMapping(value = "/createTopMenu/doCreate", method = RequestMethod.POST)
	public String doCreateTopMenu(@RequestParam("menuName") String menuName,
			@RequestParam("menuDescription") String menuDescription,
			@RequestParam("menuOrder") int menuOrder) {
		TopMenu topMenu = new TopMenu();
		topMenu.setMenuName(menuName);
		topMenu.setMenuDescription(menuDescription);
		topMenu.setMenuOrder(menuOrder);
		topMenuService.addTopMenu(topMenu);
		return "redirect:/admin/pageRedactor";
	}

	@RequestMapping(value = "/updateTopMenu", method = RequestMethod.GET)
	public String updateTopMenu(@RequestParam("id") int id, Model model) {
		TopMenu topMenu = topMenuService.getTopMenuById(id);
		model.addAttribute("topMenu", topMenu);
		return "top_menus/update";
	}

	@RequestMapping(value = "/updateTopMenu/doUpdate", method = RequestMethod.POST)
	public String doUpdateTopMenu(@RequestParam("id") int id,
			@RequestParam("menuName") String menuName,
			@RequestParam("menuDescription") String menuDescription,
			@RequestParam("menuOrder") int menuOrder) {
		TopMenu topMenu = topMenuService.getTopMenuById(id);
		topMenu.setMenuName(menuName);
		topMenu.setMenuDescription(menuDescription);
		topMenu.setMenuOrder(menuOrder);
		topMenuService.updateTopMenu(topMenu);
		return "redirect:/admin/pageRedactor";
	}

	@RequestMapping(value = "/deleteTopMenu", method = RequestMethod.GET)
	public String deleteTopMenu(@RequestParam("id") int id) {
		TopMenu topMenu = topMenuService.getTopMenuById(id);
		topMenuService.deleteTopMenu(topMenu);
		return "redirect:/admin/pageRedactor";
	}

	@RequestMapping(value = "/viewTopMenu", method = RequestMethod.GET)
	public String viewTopMenu(@RequestParam("id") int id, Model model) {
		model.addAttribute("topMenu", topMenuService.getTopMenuById(id));
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		return "_template";
	}

	@RequestMapping(value = "/previewTopMenu", method = RequestMethod.POST)
	public String previewTopMenu(
			@RequestParam("menuDescription") String menuDescription, Model model) {
		TopMenu topMenu = new TopMenu();
		topMenu.setMenuDescription(menuDescription);
		model.addAttribute("topMenu", topMenu);
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		return "_template";
	}

	@RequestMapping(value = "/updatedPageContent", method = RequestMethod.POST)
	public String updatedPageContent(@RequestParam("page") String page,
			@RequestParam("content") String content) {
		PageContent pageContent = pagesContentDAO.findContentByPageName(page);
		pageContent.setContent(content);
		pagesContentDAO.updatePageContent(pageContent);
		return "redirect:/admin/pageRedactor";
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

	private void createRecomendation(MultipartFile file, Recomendation rec) {
		saveUploadedFile(file);
	}

	private void createRecomendation(MultipartFile[] files, Recomendation rec) {
		Set<Images> images = new HashSet<Images>();
		for (MultipartFile file : files) {
			if (file.getSize() > 0) {
				saveUploadedFile(file);
				Images image = new Images();
				image.setImageName(file.getOriginalFilename());
				images.add(image);
			}
		}
		if (rec.getImages() == null) {
			rec.setImages(images);
		} else {
			rec.getImages().addAll(images);
		}
	}

	private void saveUploadedFile(MultipartFile file) {
		try {
			byte[] bytes = file.getBytes();
			String rootPath = System.getProperty("catalina.home");
			File dir = new File(rootPath + "/webapps/images");
			if (!dir.exists())
				dir.mkdir();
			File serverFile = new File(dir.getAbsolutePath() + "/"
					+ file.getOriginalFilename());
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteImage(Images image) {
		String root = System.getProperty("catalina.home");
		File imgDir = new File(root + "/webapps/images");
		if (imgDir.exists()) {
			File img = new File(imgDir.getAbsolutePath() + "/"
					+ image.getImageName());
			if (img.exists()) {
				img.delete();
			}
		}
	}
	
	private List<RecomendationType> getRecomendationTypeList() {
		List<RecomendationType> typesList = recomendationTypeService.getRecomendationTypesAsList();
		Iterator<RecomendationType> iterator = typesList.iterator();
		while (iterator.hasNext()) {
			RecomendationType type = iterator.next();
			if (type.getTypeId() == 1 || type.getTypeId() == 4) {
				iterator.remove();
			}
		}
		return typesList;
	}
	
	private String toJSArray(Object [] errors) {
		String jsArray = "[";
		for (int i = 0; i < errors.length; i++) {
			if (i == (errors.length - 1)) {
				jsArray += "'" + errors[i].toString() + "']";
			} else {
				jsArray += "'" + errors[i].toString() + "',";
			}
		}
		return jsArray;
	}
}
