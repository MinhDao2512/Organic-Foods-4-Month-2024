package com.organicfoods.dao;

import java.util.List;

import com.organicfoods.mapper.RowMapper;

public interface GenericDAO<T> {
	List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
	Boolean updateOrDelete(String sql, Object...parameters);
	Long insert(String sql, Object...parameters);
	Integer count(String sql, Object...parameters);
}
