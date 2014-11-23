package org.taobao88.taobao.enterprise.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taobao88.taobao.enterprise.dao.SizesDAO;
import org.taobao88.taobao.enterprise.entity.Sizes;
import org.taobao88.taobao.enterprise.service.SizesService;

@Repository("sizesService")
public class SizesServiceImpl implements SizesService {
	
	@Autowired
	private SizesDAO sizesDAO;
	
	@Override
	public Set<Sizes> prepareSizesFromString(String sizes) {
		Set<Sizes> sizesSet = new HashSet<Sizes>();
		String[] sizesArray = sizes.split(",");
		for (String sizeName : sizesArray) {
			if (!sizeName.isEmpty()) {
				Sizes size = sizesDAO.getSizesByName(sizeName.trim().toLowerCase());
				if (size == null) {
					size = new Sizes();
					size.setSizeName(sizeName.trim().toLowerCase());
				}
				sizesSet.add(size);
			}
		}
		return sizesSet;
	}

}
