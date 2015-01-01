package org.taobao88.taobao.enterprise.service;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.Brands;

public interface BrandsService {

	public List<Brands> getAllBrands();
	
	public List<List<Brands>> getSortedBrands();
	
	public Brands getBrandById(int brandId);
	
	public Brands getBrandByName(String brandName);
	
	public void addBrand(Brands brand);
	
	public void deleteBrand(Brands brand);
	
	public void updateBrand(Brands brand);
	
	public int getBrandsCount();
	
	public List<Brands> getBrandsPartial(int page);
	
}
