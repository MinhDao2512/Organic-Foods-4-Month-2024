package com.organicfoods.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import com.organicfoods.dao.IUserDAO;
import com.organicfoods.mapper.impl.UserMapper;
import com.organicfoods.model.UserModel;
import com.organicfoods.paging.Pageble;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel findByUsernameAndPasswordAndStatus(String userName, String passWord, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user AS u ");
		sql.append("INNER JOIN role AS r ON u.roleid = r.id ");
		sql.append("WHERE username=? AND password=? AND status=?");
		List<UserModel> results = query(sql.toString(),new UserMapper(),userName, passWord, status);
		return results.isEmpty() ? null : results.get(0);
	}

	@Override
	public UserModel findByUsernameOrEmailOrPhone(String userName, String email, String phone) {
		String sql = "SELECT * FROM user WHERE username=? OR email=? OR phone=?";
		List<UserModel> results = query(sql,new UserMapper(),userName, email, phone);
		return results.isEmpty() ? null : results.get(0);
	}

	@Override
	public Long insertUserModel(UserModel userModel) {
		userModel.setStatus(1);
		userModel.setRoleId(2L);
		userModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		userModel.setCreatedBy(userModel.getUserName());
		StringBuilder  sql = new StringBuilder("INSERT INTO user(fullname,username,password,email,phone,status,roleid,createdDate,createdBy) ");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?)");
		return insert(sql.toString(), userModel.getFullName(), userModel.getUserName(), userModel.getPassWord(), userModel.getEmail(),
						userModel.getPhone(), userModel.getStatus(), userModel.getRoleId(), userModel.getCreatedDate(), 
						userModel.getCreatedBy());
	}

	@Override
	public UserModel findById(Long id) {
		String sql = "SELECT * FROM user AS u INNER JOIN role AS r ON u.roleid=r.id WHERE u.id=?";
		List<UserModel> results = query(sql, new UserMapper(), id);
		return results.isEmpty() ? null : results.get(0);
	}

	@Override
	public Integer countUsers() {
		String sql = "SELECT COUNT(*) FROM user";
		return count(sql);
	}

	@Override
	public List<UserModel> findByOffsetAndLimit(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user AS u ");
		sql.append("INNER JOIN role AS r ON u.roleid=r.id ");
		sql.append("ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy());
		sql.append(" LIMIT ?,?");
		return query(sql.toString(), new UserMapper(), pageble.getOffset(), pageble.getLimit());
	}

	@Override
	public List<UserModel> findByUsername(Pageble pageble, String keyword) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user AS u ");
		sql.append("INNER JOIN role AS r ON u.roleid=r.id WHERE username LIKE '%" + keyword + "%' ");
		sql.append("ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy());
		sql.append(" LIMIT ?,?");
		return query(sql.toString(), new UserMapper(), pageble.getOffset(), pageble.getLimit());
	}

	@Override
	public Integer countUsersByUsername(String keyword) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM user ");
		sql.append("WHERE username LIKE '%" + keyword + "%'");
		return count(sql.toString());
	}

	@Override
	public Boolean updateUserModel(UserModel userModel) {
		StringBuilder sql = new StringBuilder("UPDATE user ");
		sql.append("SET fullname=?, username=?, email=?, phone=?, address=?, roleid=?, modifiedby=?, modifieddate=? ");
		sql.append("WHERE id=?");
		return updateOrDelete(sql.toString(), userModel.getFullName(), userModel.getUserName(), userModel.getEmail(), 
								userModel.getPhone(), userModel.getAddress(), userModel.getRoleId(), userModel.getModifiedBy(), 
								userModel.getModifiedDate(), userModel.getId());
	}

	@Override
	public Boolean deleteUserModel(Long id) {
		String sql = "DELETE FROM user WHERE id=?";
		return updateOrDelete(sql, id);
	}

}
