package com.organicfoods.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.organicfoods.dao.IProductDAO;
import com.organicfoods.model.ProductModel;
import com.organicfoods.paging.Pageble;
import com.organicfoods.service.IProductService;

public class ProductService implements IProductService{

	@Inject
	private IProductDAO productDAO;
	
	@Override
	public Long insertProduct(ProductModel product) {
		return productDAO.insertProduct(product);
	}

	@Override
	public ProductModel findById(Long id) {
		return productDAO.findById(id);
	}

	@Override
	public Boolean updateProduct(ProductModel product) {
		return productDAO.updateProduct(product);
	}

	@Override
	public Boolean deleteProduct(Long id) {
		return productDAO.deleteProduct(id);
	}

	@Override
	public List<ProductModel> findAll() {
		return productDAO.findAll();
	}

	@Override
	public List<ProductModel> findByOffsetAndLimit(Pageble pageble) {
		return productDAO.findByOffsetAndLimit(pageble);
	}

	@Override
	public Integer countProducts() {
		return productDAO.countProducts();
	}

	@Override
	public List<ProductModel> findByCode(Pageble pageble, String code) {
		return productDAO.findByCode(pageble, code);
	}

	@Override
	public Integer countProductsByCode(String code) {
		return productDAO.countProductsByCode(code);
	}

}
