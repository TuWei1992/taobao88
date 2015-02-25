package org.taobao88.taobao.enterprise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taobao88.taobao.enterprise.dao.ImagesDAO;
import org.taobao88.taobao.enterprise.entity.Images;
import org.taobao88.taobao.enterprise.service.ImagesService;

@Repository("imagesService")
public class ImagesServiceImpl implements ImagesService {

	@Autowired
	private ImagesDAO imagesDAO;
	
	@Override
	public List<Images> getAllImages() {
		return imagesDAO.getAllImages();
	}

	@Override
	public Images getImagesById(int imageId) {
		return imagesDAO.getImagesById(imageId);
	}

	@Override
	public Images getImageByName(String imageName) {
		return imagesDAO.getImageByName(imageName);
	}

	@Override
	public int addImage(Images image) {
		return imagesDAO.addImage(image);
	}

	@Override
	public void deleteImage(Images image) {
		imagesDAO.deleteImage(image);
	}

	@Override
	public void updateImage(Images image) {
		imagesDAO.updateImage(image);
	}

}
