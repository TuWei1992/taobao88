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
import org.taobao88.taobao.enterprise.dao.CountryRegCityDAO;
import org.taobao88.taobao.enterprise.dao.ImagesDAO;
import org.taobao88.taobao.enterprise.dao.PostServicePriceDAO;
import org.taobao88.taobao.enterprise.dao.PostServiceDAO;
import org.taobao88.taobao.enterprise.entity.Country;
import org.taobao88.taobao.enterprise.entity.Images;
import org.taobao88.taobao.enterprise.entity.PostService;
import org.taobao88.taobao.enterprise.entity.PostServicePrice;

@Controller
@RequestMapping(value = "/admin/postServices")
public class PostServicesController {

	@Autowired private PostServiceDAO postServiceDAO;
	@Autowired private PostServicePriceDAO postRegionDAO;
	@Autowired private ImagesDAO imagesDAO;
	@Autowired private CountryRegCityDAO countryRegCityDAO;
	@Autowired private PostServicePriceDAO postServicePriceDAO;
	
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
		model.addAttribute("countries", countryRegCityDAO.getAllCountry());
		return "post_services/create";
	}
	
	@RequestMapping(value = "/doCreate", method = RequestMethod.POST)
	public String doCreate(@RequestParam ("serviceName") String serviceName,
						   @RequestParam ("countryId") int countryId,
						   @RequestParam ("weight") double[] weight,
						   @RequestParam ("price") double[] price,
						   @RequestParam ("logo") MultipartFile logo) {
		
		Country country = countryRegCityDAO.getCountryByID(countryId);
		PostService service = new PostService();		
		saveUploadedFile(logo);
		
		Images img = new Images();
		img.setImageName(logo.getOriginalFilename());
		img.setImageId(imagesDAO.addImage(img));
		
		service.setCountry(country);
		service.setServiceName(serviceName);
		service.setImageId(img.getImageId());
		service.setId(postServiceDAO.create(service));
		
    	for (int i = 0; i < weight.length; i++) {
			PostServicePrice p = new PostServicePrice();
			p.setWeight(weight[i]);
			p.setPrice(price[i]);
			p.setPostService(service);
			p.setId(postServicePriceDAO.create(p));
		}
		return "redirect:/admin/postServices";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam ("id") int id,
						 Model model) {
		PostService service = postServiceDAO.findById(id);
		model.addAttribute("postService", service);
		return "post_services/update";
	}
	
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String doUpdate(@RequestParam ("id") int id,
						   @RequestParam ("serviceName") String serviceName,
						   @RequestParam ("weight") double[] weight,
						   @RequestParam ("price") double[] price,
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
		
		for (int i = 0; i < weight.length; i++) {
			PostServicePrice p = postServicePriceDAO.findById(service.getPostServicesPrices().get(i).getId());
			p.setWeight(weight[i]);
			p.setPrice(price[i]);
			p.setPostService(service);
			postServicePriceDAO.update(p);
		}
		
		return "redirect:/admin/postServices";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam ("id") int id,
			 			 Model model) {
		PostService service = postServiceDAO.findById(id);
		for (PostServicePrice p : service.getPostServicesPrices()) {
			postServicePriceDAO.delete(p);
		}
		postServiceDAO.delete(service);
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
