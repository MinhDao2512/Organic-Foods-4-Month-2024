package com.organicfoods.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import com.organicfoods.dao.IProductDAO;
import com.organicfoods.mapper.impl.ProductMapper;
import com.organicfoods.model.ProductModel;

public class ProductDAO extends AbstractDAO<ProductModel> implements IProductDAO{

	@Override
	public Long insertProduct(ProductModel product) {
		product.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		StringBuilder sql = new StringBuilder("INSERT INTO product ");
		sql.append("(code,title,thumbnail,shortdescription,content,price,origin,quantity,categoryid,createddate)");
		sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?)");
		return insert(sql.toString(),product.getCode(),product.getTitle(),product.getThumbnail(),product.getShortdescription(),
					product.getContent(),product.getPrice(),product.getOrigin(),product.getQuantity(),product.getCategoryId(),
					product.getCreatedDate());
	}

	@Override
	public ProductModel findById(Long id) {
		String sql = "SELECT * FROM product AS p WHERE id = ?";
		List<ProductModel> results = query(sql,new ProductMapper(),id);
		return results.isEmpty() ? null : results.get(0);
	}

	@Override
	public Boolean updateProduct(ProductModel product) {
		product.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		StringBuilder sql = new StringBuilder("UPDATE product ");
		sql.append("SET code=?,title=?,thumbnail=?,shortdescription=?,content=?,price=?,origin=?,quantity=?,categoryid=?,modifieddate=? ");
		sql.append("WHERE id=?");
		return updateOrDelete(sql.toString(),product.getCode(),product.getTitle(),product.getThumbnail(),product.getShortdescription(),
				product.getContent(),product.getPrice(),product.getOrigin(),product.getQuantity(),product.getCategoryId(),product.getModifiedDate(),
				product.getId());
	}

	@Override
	public Boolean deleteProduct(Long id) {
		String sql = "DELETE FROM product WHERE id = ?";
		return updateOrDelete(sql, id);
	}

	@Override
	public List<ProductModel> findAll() {
		StringBuilder sql = new StringBuilder("SELECT * FROM product AS p");
		return query(sql.toString(),new ProductMapper());
	}

	@Override
	public List<ProductModel> findByOffsetAndLimit(Integer offset, Integer limit, String sortName, String sortBy) {
		StringBuilder sql = new StringBuilder("SELECT * FROM product AS p INNER JOIN category AS c ON p.categoryid = c.id ");
		sql.append("ORDER BY p." + sortName + " " + sortBy);
		sql.append(" LIMIT ?,?");
		return query(sql.toString(), new ProductMapper(), offset, limit);
	}

	@Override
	public Integer countProducts() {
		String sql = "SELECT COUNT(*) FROM product";
		return count(sql);
	}

	@Override
	public List<ProductModel> findByCode(Integer offset, Integer limit, String code) {
		StringBuilder sql = new StringBuilder("SELECT * FROM product AS p INNER  JOIN category AS c ON p.categoryid = c.id ");
		sql.append("WHERE p.code LIKE '%" + code +"%' ");
		sql.append("ORDER BY p.title ASC");
		sql.append(" LIMIT ?,?");
		return query(sql.toString(), new ProductMapper(), offset, limit);
	}

	@Override
	public Integer countProductsByCode(String code) {
		StringBuilder  sql = new StringBuilder("SELECT COUNT(*) FROM product");
		sql.append(" WHERE code LIKE '%" + code + "%'");
		return count(sql.toString());
	}
	
}
