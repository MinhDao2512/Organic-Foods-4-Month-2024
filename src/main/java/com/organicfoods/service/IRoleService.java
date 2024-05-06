package com.organicfoods.service;

import java.util.List;

import com.organicfoods.model.RoleModel;

public interface IRoleService {
	List<RoleModel> findAll();
	RoleModel findByCode(String code);
}
