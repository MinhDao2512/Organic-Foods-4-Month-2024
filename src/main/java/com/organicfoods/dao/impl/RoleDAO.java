package com.organicfoods.dao.impl;

import java.util.List;

import com.organicfoods.dao.IRoleDAO;
import com.organicfoods.mapper.impl.RoleMapper;
import com.organicfoods.model.RoleModel;

public class RoleDAO extends AbstractDAO<RoleModel> implements IRoleDAO{

	@Override
	public List<RoleModel> findAll() {
		String sql = "SELECT * FROM role";
		return query(sql, new RoleMapper());
	}

	@Override
	public RoleModel findByCode(String code) {
		String sql = "SELECT * FROM role WHERE code = ?";
		List<RoleModel> results = query(sql, new RoleMapper(), code);
		return results.isEmpty() ? null : results.get(0);
	}

}
