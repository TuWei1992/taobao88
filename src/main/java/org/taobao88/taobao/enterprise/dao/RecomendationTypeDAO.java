package org.taobao88.taobao.enterprise.dao;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.RecomendationType;

public interface RecomendationTypeDAO {
	
	public List<RecomendationType> getRecomendationTypes();
	
	public RecomendationType getTypeById(int id);

}
