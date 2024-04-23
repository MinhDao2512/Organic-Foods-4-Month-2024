package com.organicfoods.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.organicfoods.mapper.RowMapper;
import com.organicfoods.model.CategoryModel;
import com.organicfoods.model.ProductModel;

public class ProductMapper implements RowMapper<ProductModel>{
	
	@Override
	public ProductModel mapRow(ResultSet resultSet) {
		ProductModel product = new ProductModel();
		try {
			product.setId(resultSet.getLong("id"));
			product.setCode(resultSet.getString("p.code"));
			product.setTitle(resultSet.getString("title"));
			product.setThumbnail(resultSet.getString("thumbnail"));
			product.setShortdescription(resultSet.getString("shortdescription"));
			product.setContent(resultSet.getString("content"));
			product.setPrice(resultSet.getDouble("price"));
			product.setOrigin(resultSet.getString("origin"));
			product.setQuantity(resultSet.getInt("quantity"));
			product.setCategoryId(resultSet.getLong("categoryid"));		
			try {
				CategoryModel categoryModel = new CategoryModel();
				categoryModel.setName(resultSet.getString("c.name"));
				categoryModel.setCode(resultSet.getString("c.code"));
				product.setCategory(categoryModel);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			product.setCreatedDate(resultSet.getTimestamp("createddate"));
			product.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			product.setCreatedBy(resultSet.getString("createdby"));
			product.setModifiedBy(resultSet.getString("modifiedby"));
			return product;
		} catch (SQLException e) {
			return null;
		}
	}

}
