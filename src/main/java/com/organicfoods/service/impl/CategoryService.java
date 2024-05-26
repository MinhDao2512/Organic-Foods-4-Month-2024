package com.organicfoods.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.organicfoods.dao.ICategoryDAO;
import com.organicfoods.model.CategoryModel;
import com.organicfoods.service.ICategoryService;

public class CategoryService implements ICategoryService{

	@Inject
	private ICategoryDAO categoryDAO;
	
	@Override
	public List<CategoryModel> findAll() {
		return categoryDAO.findAll();
	}

	@Override
	public CategoryModel findByCategoryCode(String code) {
		return categoryDAO.findByCategoryCode(code);
	}

	@Override
	public CategoryModel findById(Long id) {
		return categoryDAO.findById(id);
	}

}
