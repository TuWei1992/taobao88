package org.taobao88.taobao.enterprise.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taobao88.taobao.enterprise.DAO.ColorsDAO;
import org.taobao88.taobao.enterprise.entity.Colors;

@Repository("colorsService")
public class ColorsServiceImpl implements ColorsService {
	
	@Autowired
	private ColorsDAO colorsDAO;
	
	@Override
	public Set<Colors> prepareColorsFromString(String colors) {
		Set<Colors> colorsSet = new HashSet<Colors>();
		String[] colorsArray = colors.split(",");
		for (String colorName : colorsArray) {
			if (!colorName.isEmpty()) {
				Colors color = colorsDAO.getColorByName(colorName.trim().toLowerCase());
				if (color == null) {
					color = new Colors();
					color.setColorName(colorName.trim().toLowerCase());
				}
				colorsSet.add(color);
			}
		}
		return colorsSet;
	}

}
