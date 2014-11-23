package org.taobao88.taobao.enterprise.dao;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.Colors;

public interface ColorsDAO {

	public Colors getColorById(int id);
	
	public Colors getColorByName(String colorName);
	
	public List<Colors> getColors();
	
	public int addColor(Colors color);
	
	public void updateColor(Colors color);
	
	public void deleteColor(Colors color);
	
}
