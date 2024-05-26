package com.organicfoods.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.organicfoods.dao.IUserDAO;
import com.organicfoods.model.UserModel;
import com.organicfoods.paging.Pageble;
import com.organicfoods.service.IUserService;

public class UserService implements IUserService{
	
	@Inject
	private IUserDAO userDAO;

	@Override
	public UserModel findByUsernameAndPassword(String userName, String password) {
		return userDAO.findByUsernameAndPassword(userName, password);
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

	@Override
	public Integer countUsers() {
		return userDAO.countUsers();
	}

	@Override
	public List<UserModel> findByOffsetAndLimit(Pageble pageble) {
		return userDAO.findByOffsetAndLimit(pageble);
	}

	@Override
	public List<UserModel> findByUsername(Pageble pageble, String keyword) {
		return userDAO.findByUsername(pageble, keyword);
	}

	@Override
	public Integer countUsersByUsername(String keyword) {
		return userDAO.countUsersByUsername(keyword);
	}

	@Override
	public Boolean updateUserModel(UserModel userModel) {
		return userDAO.updateUserModel(userModel);
	}

	@Override
	public Boolean deleteUserModel(Long id) {
		return userDAO.deleteUserModel(id);
	}
	
}
