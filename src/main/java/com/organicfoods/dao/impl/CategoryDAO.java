package com.organicfoods.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import com.organicfoods.dao.ICategoryDAO;
import com.organicfoods.mapper.impl.CategoryMapper;
import com.organicfoods.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO{

	@Override
	public List<CategoryModel> findAll() {
		String sql = "SELECT * FROM category";
		return query(sql,new CategoryMapper());
	}

	@Override
	public Boolean updateCategoryModel(CategoryModel category) {
		category.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		String sql = "UPDATE category SET name=?, code=?, modifieddate=? WHERE id=?";
		return updateOrDelete(sql, category.getName(), category.getCode(), category.getModifiedDate(), category.getId());
	}

	@Override
	public Boolean deleteCategoryModel(Long id) {
		String sql = "DELETE FROM category WHERE id = ?";
		return updateOrDelete(sql, id);
	}

	@Override
	public Long insertCategoryModel(CategoryModel category) {
		category.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		String sql = "INSERT INTO category(name,code,createddate) VALUES(?,?,?)";
		return insert(sql, category.getName(), category.getCode(), category.getCreatedDate());
	}

	@Override
	public CategoryModel findByCategoryCode(String code) {
		String sql = "SELECT * FROM category WHERE code = ?";
		List<CategoryModel> results = query(sql,new CategoryMapper(),code);
		return results.isEmpty() ? null : results.get(0);
	}

}
