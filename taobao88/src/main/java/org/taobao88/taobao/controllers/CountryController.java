package org.taobao88.taobao.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.taobao88.taobao.enterprise.dao.CountryRegCityDAO;
import org.taobao88.taobao.enterprise.entity.Country;

@Controller
@RequestMapping(value = "/admin/countries")
public class CountryController {

	@Autowired private CountryRegCityDAO countryRegCityDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		List<Country> countries = new ArrayList<>();
		for (Country c : countryRegCityDAO.getAllCountry()) {
			if (c.getIdCountry() != 1) {
				countries.add(c);
			}
		}
		model.addAttribute("countries", countries);		
		return "countries/index";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "countries/create";
	}
	
	
	@RequestMapping(value = "/doCreate", method = RequestMethod.POST)
	public String doCreate(@RequestParam ("nameCountry") String nameCountry,
						   Model model) {
		
		List<String> errors = new ArrayList<>();
		
		if (countryRegCityDAO.findByName(nameCountry) != null) {
			errors.add("country_already_exist");
			model.addAttribute("errors", errors);
			return "countries/create";
		}
		
		Country country = new Country();
		country.setNameCountry(nameCountry);
		country.setIdCity(0);
		countryRegCityDAO.addCountry(country);
		
		return "redirect:/admin/countries";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam ("id") int id,
						 Model model) {
		
		List<String> errors = new ArrayList<>();
		Country country = countryRegCityDAO.getCountryByID(id);
		if (country == null) {
			errors.add("cannot_delete_country");
			model.addAttribute("errors", errors);
			return "countries/index";
		}
		
		countryRegCityDAO.delete(country);
		
		return "redirect:/admin/countries";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam ("id") int id,
			 			 Model model) {
		
		List<String> errors = new ArrayList<>();
		Country country = countryRegCityDAO.getCountryByID(id);
		if (country == null) {
			errors.add("cannot_update_country");
			model.addAttribute("errors", errors);
			return "countries/index";
		}
		
		model.addAttribute("country", country);
		return "countries/update";
	}
	
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String doUpdate(@RequestParam ("id") int id,
						   @RequestParam ("nameCountry") String nameCountry,
			 			   Model model) {
		
		List<String> errors = new ArrayList<>();
		Country country = countryRegCityDAO.getCountryByID(id);
		if (country == null) {
			errors.add("cannot_update_country");
			model.addAttribute("errors", errors);
			return "countries/update";
		}
		
		country.setNameCountry(nameCountry);
		countryRegCityDAO.update(country);	
		return "redirect:/admin/countries";
	}
}
