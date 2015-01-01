package org.taobao88.taobao.enterprise.dao;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.Brands;

public interface BrandsDAO {
	
	public List<Brands> getAllBrands();
	
	public Brands getBrandById(int brandId);
	
	public Brands getBrandByName(String brandName);
	
	public void addBrand(Brands brand);
	
	public void deleteBrand(Brands brand);
	
	public void updateBrand(Brands brand);
	
	public int getBrandsCount();
	
	public List<Brands> getBrandsPartial(int start, int end);

}
