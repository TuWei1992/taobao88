package org.taobao88.taobao.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.taobao88.taobao.enterprise.dao.ImagesDAO;
import org.taobao88.taobao.enterprise.dao.PostRegionDAO;
import org.taobao88.taobao.enterprise.dao.PostServiceDAO;
import org.taobao88.taobao.enterprise.entity.Images;
import org.taobao88.taobao.enterprise.entity.PostService;

@Controller
@RequestMapping(value = "/admin/postServices")
public class PostServicesController {

	@Autowired private PostServiceDAO postServiceDAO;
	@Autowired private PostRegionDAO postRegionDAO;
	@Autowired private ImagesDAO imagesDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		List<PostService> services = postServiceDAO.getAll();
		for (PostService s : services) {
			s.setImage(imagesDAO.getImagesById(s.getImageId()));
		}
		model.addAttribute("postServices", services);
		return "post_services/index";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("postService", new PostService());
		model.addAttribute("postRegions", postRegionDAO.getAll());
		return "post_services/create";
	}
	
	@RequestMapping(value = "/doCreate", method = RequestMethod.POST)
	public String doCreate(@RequestParam ("serviceName") String serviceName,
//						   @RequestParam ("region") int regionId,
						   @RequestParam ("minskPrice") double minskPrice,
						   @RequestParam ("moscowPrice") double moscowPrice,
						   @RequestParam ("logo") MultipartFile logo) {
		
//		PostRegion region = postRegionDAO.findById(regionId);
		PostService service = new PostService();		
		saveUploadedFile(logo);
		
		Images img = new Images();
		img.setImageName(logo.getOriginalFilename());
		img.setImageId(imagesDAO.addImage(img));
		
//		service.setPostRegion(region);
		service.setServiceName(serviceName);
		service.setImageId(img.getImageId());
		postServiceDAO.create(service);
		
		return "redirect:/admin/postServices";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam ("id") int id,
						 Model model) {
		PostService service = postServiceDAO.findById(id);
		model.addAttribute("postService", service);
//		model.addAttribute("postRegions", postRegionDAO.getAll());
		return "post_services/update";
	}
	
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String doUpdate(@RequestParam ("id") int id,
						   @RequestParam ("serviceName") String serviceName,
						   @RequestParam ("minskPrice") double minskPrice,
						   @RequestParam ("moscowPrice") double moscowPrice,
						   @RequestParam ("logo") MultipartFile logo) {
		
		PostService service = postServiceDAO.findById(id);
		
		if (logo.getSize() > 0) {
			Images img = new Images();
			img.setImageName(logo.getOriginalFilename());
			img.setImageId(imagesDAO.addImage(img));
			service.setImageId(img.getImageId());
    		saveUploadedFile(logo);
    	}
		saveUploadedFile(logo);
		
		service.setServiceName(serviceName);
		postServiceDAO.update(service);
		
		return "redirect:/admin/postServices";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam ("id") int id,
			 			 Model model) {
		postServiceDAO.delete(postServiceDAO.findById(id));
		return "redirect:/admin/postServices";
	}
	
	private void saveUploadedFile(MultipartFile file) {
    	try{
			byte[] bytes = file.getBytes();
			String rootPath = System.getProperty("catalina.home");
			File dir = new File(rootPath + "/webapps/images");
			if (!dir.exists())
				dir.mkdir();

			File serverFile = new File(dir.getAbsolutePath() + "/" + file.getOriginalFilename());
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
}
