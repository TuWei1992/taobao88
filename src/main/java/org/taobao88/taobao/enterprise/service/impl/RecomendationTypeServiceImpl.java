package org.taobao88.taobao.enterprise.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taobao88.taobao.enterprise.dao.RecomendationTypeDAO;
import org.taobao88.taobao.enterprise.entity.RecomendationType;
import org.taobao88.taobao.enterprise.service.RecomendationTypeService;

@Repository("recomendationTypeService")
public class RecomendationTypeServiceImpl implements RecomendationTypeService {

	@Autowired
	RecomendationTypeDAO recomendationTypeDAO;
	
	@Override
	public Map<Integer, RecomendationType> getRecomendationTypes() {
		List<RecomendationType> types = recomendationTypeDAO.getRecomendationTypes();
		Map<Integer, RecomendationType> typesMap = new HashMap<>();
		for (RecomendationType type : types) {
			typesMap.put(type.getTypeId(), type);
		}
		return typesMap;
	}

	@Override
	public RecomendationType getTypeById(int id) {
		return recomendationTypeDAO.getTypeById(id);
	}

	@Override
	public List<RecomendationType> getRecomendationTypesAsList() {
		return recomendationTypeDAO.getRecomendationTypes();
	}

}
