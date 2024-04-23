package com.organicfoods.dao;

import com.organicfoods.model.ImageModel;

public interface IImageDAO extends GenericDAO<ImageModel>{
	String getUrlByProductId(Long productId);
}
