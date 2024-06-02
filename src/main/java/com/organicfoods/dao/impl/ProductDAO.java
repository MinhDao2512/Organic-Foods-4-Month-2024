package com.organicfoods.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import com.organicfoods.dao.IProductDAO;
import com.organicfoods.mapper.impl.ProductMapper;
import com.organicfoods.model.ProductModel;
import com.organicfoods.paging.Pageble;

public class ProductDAO extends AbstractDAO<ProductModel> implements IProductDAO{

	@Override
	public Long insertProduct(ProductModel product) {
		product.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		StringBuilder sql = new StringBuilder("INSERT INTO product");
		sql.append("(code,title,thumbnail,shortdescription,content,price,origin,quantity,categoryid,createddate,createdby)");
		sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?)");
		return insert(sql.toString(),product.getCode(),product.getTitle(),product.getThumbnail(),product.getShortdescription(),
					product.getContent(),product.getPrice(),product.getOrigin(),product.getQuantity(),product.getCategoryId(),
					product.getCreatedDate(),product.getCreatedBy());
	}

	@Override
	public ProductModel findById(Long id) {
		String sql = "SELECT * FROM product AS p  INNER JOIN category AS c ON p.categoryid = c.id WHERE p.id = ?";
		List<ProductModel> results = query(sql,new ProductMapper(),id);
		return results.isEmpty() ? null : results.get(0);
	}

	@Override
	public Boolean updateProduct(ProductModel product) {
		product.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		StringBuilder sql = new StringBuilder("UPDATE product ");
		sql.append("SET code=?,title=?,thumbnail=?,shortdescription=?,content=?,price=?,origin=?,quantity=?,categoryid=?,modifieddate=?, modifiedBy=? ");
		sql.append("WHERE id=?");
		return updateOrDelete(sql.toString(),product.getCode(),product.getTitle(),product.getThumbnail(),product.getShortdescription(),
				product.getContent(),product.getPrice(),product.getOrigin(),product.getQuantity(),product.getCategoryId(),
				product.getModifiedDate(),product.getModifiedBy(),product.getId());
	}

	@Override
	public Boolean deleteProduct(Long id) {
		String sql = "DELETE FROM product WHERE id = ?";
		return updateOrDelete(sql, id);
	}

	@Override
	public List<ProductModel> findAll() {
		StringBuilder sql = new StringBuilder("SELECT * FROM product AS p WHERE p.quantity > 0");
		return query(sql.toString(),new ProductMapper());
	}

	@Override
	public List<ProductModel> findByOffsetAndLimit(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM product AS p INNER JOIN category AS c ON p.categoryid = c.id ");
		sql.append("ORDER BY p." + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy());
		sql.append(" LIMIT ?,?");
		return query(sql.toString(), new ProductMapper(), pageble.getOffset(), pageble.getLimit());
	}

	@Override
	public Integer countProducts() {
		String sql = "SELECT COUNT(*) FROM product";
		return count(sql);
	}

	@Override
	public List<ProductModel> findByCode(Pageble pageble, String code) {
		StringBuilder sql = new StringBuilder("SELECT * FROM product AS p INNER  JOIN category AS c ON p.categoryid = c.id ");
		sql.append("WHERE p.code LIKE '%" + code +"%' ");
		sql.append("ORDER BY p." + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy());
		sql.append(" LIMIT ?,?");
		return query(sql.toString(), new ProductMapper(), pageble.getOffset(), pageble.getLimit());
	}

	@Override
	public Integer countProductsByCode(String code) {
		StringBuilder  sql = new StringBuilder("SELECT COUNT(*) FROM product");
		sql.append(" WHERE code LIKE '%" + code + "%'");
		return count(sql.toString());
	}

	@Override
	public List<ProductModel> findByCreatedBy(Pageble pageble, String userName) {
		StringBuilder sql = new StringBuilder("SELECT * FROM product AS p INNER JOIN category AS c ON p.categoryid = c.id ");
		sql.append("WHERE p.createdby=? ");
		sql.append("ORDER BY p." + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy());
		sql.append(" LIMIT ?,?");
		return query(sql.toString(), new ProductMapper(), userName, pageble.getOffset(), pageble.getLimit());
	}

	@Override
	public Integer countProductsByCreatedBy(String userName) {
		String sql = "SELECT COUNT(*) FROM product WHERE createdby = ?";
		return count(sql, userName);
	}

	@Override
	public Boolean findByCodeAndCreatedBy(String code, String createdBy) {
		String sql = "SELECT * FROM product WHERE code=? AND createdby=?";
		List<ProductModel> result = query(sql, new ProductMapper(), code, createdBy);
		return result.isEmpty() ? false : true;
	}

	@Override
	public List<ProductModel> findBySeller(String userName) {
		String sql = "SELECT * FROM product AS p WHERE createdby=?";
		List<ProductModel> result = query(sql, new ProductMapper(), userName);
		return result.isEmpty() ? null : result;
	}

	@Override
	public List<ProductModel> findByKeyword(String keyword) {
		StringBuilder sql = new StringBuilder("SELECT * FROM product AS p WHERE p.title LIKE '%");
		sql.append(keyword);
		sql.append("%'");
		List<ProductModel> results = query(sql.toString(), new ProductMapper());
		return results;
	}
	
}
