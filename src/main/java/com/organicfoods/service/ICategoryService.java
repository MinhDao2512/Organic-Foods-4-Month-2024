package com.organicfoods.service;

import java.util.List;

import com.organicfoods.model.CategoryModel;

public interface ICategoryService {
	List<CategoryModel> findAll();
	CategoryModel findByCategoryCode(String code);
}
