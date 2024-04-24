package com.organicfoods.service;

import com.organicfoods.model.UserModel;

public interface IUserService {
	UserModel findByUsernameAndPasswordAndStatus(String userName, String password, Integer status);
	UserModel findByUsernameOrEmailOrPhone(String userName, String email, String phone);
	Long insertUserModel(UserModel userModel);
	UserModel findById(Long id);
}
