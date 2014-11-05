package org.taobao88.taobao.enterprise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.taobao88.taobao.enterprise.DAO.BrandsDAO;
import org.taobao88.taobao.enterprise.entity.Brands;
import org.taobao88.taobao.enterprise.entity.Images;

@Repository("brandsService")
public class BrandsServiceImpl implements BrandsService {

	@Autowired
	private BrandsDAO brandsDAO;
	
	@Autowired
	private ImagesService imagesService;
	
	@Override
	public List<Brands> getAllBrands() {
		List<Brands> brands = brandsDAO.getAllBrands();
		List<Brands> brandsWithImg = new ArrayList<Brands>();
		for (Brands brand : brands) {
			Images image = imagesService.getImagesById(brand.getBrandImage());
			brand.setImage(image);
			brandsWithImg.add(brand);
		}
		return brandsWithImg;
	}

	@Override
	public List<List<Brands>> getSortedBrands() {
		List<Brands> all = getAllBrands();
		List<Brands> sorted = new ArrayList<Brands>();
		List<List<Brands>> result = new ArrayList<List<Brands>>();
		for (int i = 0; i < all.size(); i++) {
			sorted.add(all.get(i));
			if (sorted.size() % 3 == 0) {
				result.add(sorted);
				sorted = new ArrayList<Brands>();
			}
		}
		result.add(sorted);
		return result;
	}
	
	@Override
	public Brands getBrandById(int brandId) {
		Brands brand = brandsDAO.getBrandById(brandId);
		Images image = imagesService.getImagesById(brand.getBrandImage());
		brand.setImage(image);
		return brand;
	}

	@Override
	public Brands getBrandByName(String brandName) {
		Brands brand = brandsDAO.getBrandByName(brandName);
		Images image = imagesService.getImagesById(brand.getBrandImage());
		brand.setImage(image);
		return brandsDAO.getBrandByName(brandName);
	}

	@Override
	public void addBrand(Brands brand) {
		brandsDAO.addBrand(brand);
	}

	@Override
	public void deleteBrand(Brands brand) {
		brandsDAO.deleteBrand(brand);
	}

	@Override
	public void updateBrand(Brands brand) {
		brandsDAO.updateBrand(brand);
	}
}
