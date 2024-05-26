package com.organicfoods.dao;

import java.util.List;

import com.organicfoods.model.CategoryModel;

public interface ICategoryDAO extends GenericDAO<CategoryModel>{
	List<CategoryModel> findAll();
	Boolean updateCategoryModel(CategoryModel category);
	Boolean deleteCategoryModel(Long id);
	Long insertCategoryModel(CategoryModel category);
	CategoryModel findByCategoryCode(String code);
	CategoryModel findById(Long id);
}
