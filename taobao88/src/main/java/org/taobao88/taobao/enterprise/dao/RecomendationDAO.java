package org.taobao88.taobao.enterprise.dao;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.Recomendation;
import org.taobao88.taobao.enterprise.entity.RecomendationType;

public interface RecomendationDAO {

	public void addRecomendation(Recomendation recomendation);
	
	public void deleteRecomendation(Recomendation recomendation);
	
	public void updateRecomendation(Recomendation recomendation);
	
	public List<Recomendation> getRecomendations(RecomendationType type);
	
	public List<Recomendation> getFirstFourRecomendations(RecomendationType type);
	
	public Recomendation getRecomendationById(int id);
	
	public Recomendation getRecomendationByDescription(String desc);
	
	public List<Recomendation> getRecomendationsPartials(int start, int end, RecomendationType type);
	
	public int getRecomendationsCount(RecomendationType type);
	
	public List<Recomendation> getRandomRecomendations(RecomendationType type);
}
