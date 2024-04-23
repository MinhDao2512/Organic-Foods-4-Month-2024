package com.organicfoods.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.organicfoods.mapper.RowMapper;
import com.organicfoods.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel>{

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		CategoryModel category = new CategoryModel();
		try {
			category.setId(resultSet.getLong("id"));
			category.setName(resultSet.getString("name"));
			category.setCode(resultSet.getString("code"));
			category.setCreatedDate(resultSet.getTimestamp("createddate"));
			category.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			category.setCreatedBy(resultSet.getString("createdby"));
			category.setModifiedBy(resultSet.getString("modifiedby"));
			return category;
		} catch (SQLException e) {
			return null;
		}
	}
	
}
