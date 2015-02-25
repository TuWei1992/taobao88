package org.taobao88.taobao.enterprise.service;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.Images;

public interface ImagesService {

	public List<Images> getAllImages();
	
	public Images getImagesById(int imageId);
	
	public Images getImageByName(String imageName);
	
	public int addImage(Images image);
	
	public void deleteImage(Images image);
	
	public void updateImage(Images image);
	
}
