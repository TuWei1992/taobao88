package org.taobao88.taobao.enterprise.service;

import java.util.List;
import java.util.Map;

import org.taobao88.taobao.enterprise.entity.RecomendationType;

public interface RecomendationTypeService {

	public Map<Integer, RecomendationType> getRecomendationTypes();
	
	public List<RecomendationType> getRecomendationTypesAsList();
	
	public RecomendationType getTypeById(int id);
	
}
