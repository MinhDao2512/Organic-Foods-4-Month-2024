package com.organicfoods.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.organicfoods.mapper.RowMapper;
import com.organicfoods.model.RoleModel;

public class RoleMapper implements RowMapper<RoleModel>{

	@Override
	public RoleModel mapRow(ResultSet resultSet) {
		RoleModel role = new RoleModel();
		try {
			role.setId(resultSet.getLong("id"));
			role.setName(resultSet.getString("name"));
			role.setCode(resultSet.getString("code"));
			role.setCreatedDate(resultSet.getTimestamp("createddate"));
			role.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			role.setCreatedBy(resultSet.getString("createdby"));
			role.setModifiedBy(resultSet.getString("modifiedby"));
		} catch (SQLException e) {
			return null;
		}
		return null;
	}

}
