package com.organicfoods.dao;

import java.util.List;

import com.organicfoods.model.UserModel;
import com.organicfoods.paging.Pageble;

public interface IUserDAO extends GenericDAO<UserModel>{
	UserModel findByUsernameAndPasswordAndStatus(String userName, String passWord, Integer status);
	UserModel findByUsernameOrEmailOrPhone(String userName, String email, String phone);
	Long insertUserModel(UserModel userModel);
	UserModel findById(Long id);
	Integer countUsers();
	List<UserModel> findByOffsetAndLimit(Pageble pageble);
	List<UserModel> findByUsername(Pageble pageble,String keyword);
	Integer countUsersByUsername(String keyword);
	Boolean updateUserModel(UserModel userModel);
	Boolean deleteUserModel(Long id);
}
