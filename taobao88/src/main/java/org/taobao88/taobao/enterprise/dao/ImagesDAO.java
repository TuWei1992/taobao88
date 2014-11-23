package org.taobao88.taobao.enterprise.dao;

import java.util.List;
import java.util.Set;

import org.taobao88.taobao.enterprise.entity.Images;
import org.taobao88.taobao.enterprise.entity.Recomendation;

public interface ImagesDAO {

	public List<Images> getAllImages();
	
	public Set<Images> getImagesByRecomendation(Recomendation recomendation);
	
	public Images getImagesById(int imageId);
	
	public Images getImageByName(String imageName);
	
	public int addImage(Images image);
	
	public void deleteImage(Images image);
	
	public void updateImage(Images image);
}
