package org.taobao88.taobao.enterprise.service;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.Recomendation;
import org.taobao88.taobao.enterprise.entity.RecomendationType;

public interface RecomendationService {

	public void addRecomendation(Recomendation recomendation);
	
	public void deleteRecomendation(Recomendation recomendation);
	
	public void updateRecomendation(Recomendation recomendation);
	
	public List<List<Recomendation>> getSortedRecomendations(RecomendationType type);
	
	public List<Recomendation> getAllRecomendations(RecomendationType type);
	
	public List<Recomendation> getRecomendationsPartial(int page, RecomendationType type);
	
	public int getRecomendationsCount(RecomendationType type);
	
	public List<Recomendation> getFirstFourRecomendations(RecomendationType type);
	
	public Recomendation getRecomendationById(int id);
	
	public Recomendation getRecomendationByDescription(String desc);
	
	public List<Recomendation> getRandomRecomendations(RecomendationType type);
}
