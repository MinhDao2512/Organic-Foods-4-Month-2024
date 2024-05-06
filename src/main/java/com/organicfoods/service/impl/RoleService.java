package com.organicfoods.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.organicfoods.dao.IRoleDAO;
import com.organicfoods.model.RoleModel;
import com.organicfoods.service.IRoleService;

public class RoleService implements IRoleService{

	@Inject
	private IRoleDAO roleDAO;
	
	@Override
	public List<RoleModel> findAll() {
		return roleDAO.findAll();
	}

	@Override
	public RoleModel findByCode(String code) {
		return roleDAO.findByCode(code);
	}

}
