package com.organicfoods.dao;

import java.util.List;

import com.organicfoods.model.RoleModel;

public interface IRoleDAO extends GenericDAO<RoleModel>{
	List<RoleModel> findAll();
	RoleModel findByCode(String code);
}
