package com.organicfoods.testutils;

import com.organicfoods.dao.impl.CategoryDAO;
import com.organicfoods.model.CategoryModel;

public class TestUtil {
	public static void main(String[] args) {
		CategoryModel category = new CategoryModel();
		category.setCode("trai-cay");
		category.setName("Trái cây");
		CategoryDAO test = new CategoryDAO();
		System.out.println(test.insertCategoryModel(category));
	}
}
