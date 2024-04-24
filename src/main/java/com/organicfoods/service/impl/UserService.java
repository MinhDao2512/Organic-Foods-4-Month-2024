package com.organicfoods.service.impl;

import javax.inject.Inject;

import com.organicfoods.dao.IUserDAO;
import com.organicfoods.model.UserModel;
import com.organicfoods.service.IUserService;

public class UserService implements IUserService{
	
	@Inject
	private IUserDAO userDAO;

	@Override
	public UserModel findByUsernameAndPasswordAndStatus(String userName, String password, Integer status) {
		return userDAO.findByUsernameAndPasswordAndStatus(userName, password, status);
	}

	@Override
	public UserModel findByUsernameOrEmailOrPhone(String userName, String email, String phone) {
		return userDAO.findByUsernameOrEmailOrPhone(userName, email, phone);
	}

	@Override
	public Long insertUserModel(UserModel userModel) {
		return userDAO.insertUserModel(userModel);
	}

	@Override
	public UserModel findById(Long id) {
		return userDAO.findById(id);
	}
	
}
