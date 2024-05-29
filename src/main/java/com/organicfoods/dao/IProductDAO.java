package com.organicfoods.dao;

import java.util.List;

import com.organicfoods.model.ProductModel;
import com.organicfoods.paging.Pageble;

public interface IProductDAO extends GenericDAO<ProductModel>{
	Long insertProduct(ProductModel product);
	ProductModel findById(Long id);
	Boolean updateProduct(ProductModel product);
	Boolean deleteProduct(Long id);
	List<ProductModel> findAll();
	List<ProductModel> findByOffsetAndLimit(Pageble pageble);
	Integer countProducts();
	List<ProductModel> findByCode(Pageble pageble, String code);
	Integer countProductsByCode(String code);
	List<ProductModel> findByCreatedBy(Pageble pageble, String userName);
	List<ProductModel> findBySeller(String userName);
	Integer countProductsByCreatedBy(String userName);
	Boolean findByCodeAndCreatedBy(String code, String createdBy);
	List<ProductModel> findByKeyword(String keyword);
}
