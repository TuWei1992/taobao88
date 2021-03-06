package org.taobao88.taobao.enterprise.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taobao88.taobao.enterprise.dao.ImagesDAO;
import org.taobao88.taobao.enterprise.dao.RecomendationDAO;
import org.taobao88.taobao.enterprise.entity.Recomendation;
import org.taobao88.taobao.enterprise.entity.RecomendationType;
import org.taobao88.taobao.enterprise.service.RecomendationService;

@Repository("recomendationService")
public class RecomendationServiceImpl implements RecomendationService {

	@Autowired private RecomendationDAO recomendationDAO;
	@Autowired private ImagesDAO imagesDAO;
	
	@Override
	public void addRecomendation(Recomendation recomendation) {
		recomendationDAO.addRecomendation(recomendation);
	}

	@Override
	public void deleteRecomendation(Recomendation recomendation) {
		recomendationDAO.deleteRecomendation(recomendation);
	}

	@Override
	public void updateRecomendation(Recomendation recomendation) {
		recomendationDAO.updateRecomendation(recomendation);
	}

	@Override
	public Recomendation getRecomendationById(int id) {
		Recomendation rec = recomendationDAO.getRecomendationById(id);
//		rec.setImages(imagesDAO.getImagesByRecomendation(rec));
		return rec;
	}

	@Override
	public Recomendation getRecomendationByDescription(String desc) {
		return recomendationDAO.getRecomendationByDescription(desc);
	}

	@Override
	public List<List<Recomendation>> getSortedRecomendations(RecomendationType type) {
		List<Recomendation> all = recomendationDAO.getRandomRecomendations(type);
		List<Recomendation> sorted = new ArrayList<Recomendation>();
		List<List<Recomendation>> result = new ArrayList<List<Recomendation>>();
		for (int i = 0; i < all.size(); i++) {
			sorted.add(all.get(i));
			if (sorted.size() % 3 == 0) {
				result.add(sorted);
				sorted = new ArrayList<Recomendation>();
			}
		}
		result.add(sorted);
		return result;
	}

	@Override
	public List<Recomendation> getAllRecomendations(RecomendationType type) {
		return recomendationDAO.getRecomendations(type);
	}

	@Override
	public List<Recomendation> getFirstFourRecomendations(RecomendationType type) {
		return recomendationDAO.getFirstFourRecomendations(type);
	}

	@Override
	public List<Recomendation> getRecomendationsPartial(int page, RecomendationType type) {
		
		int start = 0;
		int end = 54;
		
		if (page == 2) {
			start = end;
		} else if (page > 2) {
			start = end * page;
		}
		
		return recomendationDAO.getRecomendationsPartials(start, end, type);
	}

	@Override
	public int getRecomendationsCount(RecomendationType type) {
		return recomendationDAO.getRecomendationsCount(type);
	}

	@Override
	public List<Recomendation> getRandomRecomendations(RecomendationType type) {
		return recomendationDAO.getRandomRecomendations(type);
	}

}
