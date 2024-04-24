package com.organicfoods.dao;

import com.organicfoods.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel>{
	UserModel findByUsernameAndPasswordAndStatus(String userName, String passWord, Integer status);
	UserModel findByUsernameOrEmailOrPhone(String userName, String email, String phone);
	Long insertUserModel(UserModel userModel);
	UserModel findById(Long id);
}
