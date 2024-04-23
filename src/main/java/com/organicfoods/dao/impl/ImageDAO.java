package com.organicfoods.dao.impl;

import com.organicfoods.dao.IImageDAO;
import com.organicfoods.model.ImageModel;

public class ImageDAO extends AbstractDAO<ImageModel> implements IImageDAO{

	@Override
	public String getUrlByProductId(Long productId) {
		String sql = "SELECT url FROM image WHERE productid=?";
		return null;
	}

}
