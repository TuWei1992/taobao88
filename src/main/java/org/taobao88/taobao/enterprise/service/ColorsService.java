package org.taobao88.taobao.enterprise.service;

import java.util.Set;

import org.taobao88.taobao.enterprise.entity.Colors;

public interface ColorsService {

	public Set<Colors> prepareColorsFromString(String colors);
	
}
