package com.organicfoods.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.organicfoods.dao.GenericDAO;
import com.organicfoods.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T>{
	
	ResourceBundle bundle = ResourceBundle.getBundle("db");
	
	public Connection getConnection() {
		try {
			Class.forName(bundle.getString("driverName")); 
			String url = bundle.getString("url");
			String username = bundle.getString("username");
			String password = bundle.getString("password");
			return DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		} 
	}

	@Override
	public List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if(connection != null) {
			try {
				statement = connection.prepareStatement(sql);
				setParameters(statement,parameters);
				resultSet = statement.executeQuery();
				while(resultSet.next()) {
					results.add(rowMapper.mapRow(resultSet));
				}
				return results;
			} catch (SQLException e) {
				return null;
			} finally {
				try {
					if(connection != null) {
						connection.close();
					}
					if(statement != null) {
						statement.close();
					}
					if(resultSet != null) {
						resultSet.close();
					}
				} catch (SQLException e) {
					return null;
				}
			}
		}
		return null;
	}

	private void setParameters(PreparedStatement statement, Object[] parameters) {
		for(int i=0;i<parameters.length;i++) {
			Object parameter = parameters[i];
			int index = i+1;
			try {
				if(parameter instanceof String) {
					statement.setString(index, (String)parameter);
				}
				else if(parameter instanceof Long) {
					statement.setLong(index, (Long)parameter);
				}
				else if(parameter instanceof Double) {
					statement.setDouble(index, (Double)parameter);
				}
				else if(parameter instanceof Integer) {
					statement.setInt(index, (Integer)parameter);
				}
				else if(parameter instanceof Timestamp) {
					statement.setTimestamp(index, (Timestamp)parameter);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public Boolean updateOrDelete(String sql, Object... parameters) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		if(connection != null) {
			try {
				connection.setAutoCommit(false);
				statement = connection.prepareStatement(sql);
				setParameters(statement, parameters);
				statement.executeUpdate();
				connection.commit();
				return true;
			} catch (SQLException e) {
				if(connection != null) {
					try {
						connection.rollback();
					} catch (SQLException e1) {
						return false;
					}
				}
			} finally {
				try {
					if(connection != null) {
						connection.close();
					}
					if(statement != null) {
						statement.close();
					}
				} catch (SQLException e) {
					return false;
				}
			}
		}
		return false;
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Long id = null;
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if(connection != null) {
			try {
				connection.setAutoCommit(false);
				statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				setParameters(statement, parameters);
				statement.executeUpdate();
				connection.commit();
				resultSet = statement.getGeneratedKeys();
				if(resultSet.next()) {
					id = resultSet.getLong(1);
				}
				return id;
			} catch (SQLException e) {
				if(connection != null) {
					try {
						connection.rollback();
					} catch (SQLException e1) {
						return null;
					}
				}
			} finally {
				try {
					if(connection != null) {
						connection.close();
					}
					if(statement != null) {
						statement.close();
					}
					if(resultSet != null) {
						resultSet.close();
					}
				} catch (SQLException e) {
					return null;
				}
			}
		}
		return null;
	}

	@Override
	public Integer count(String sql, Object... parameters) {
		Integer cnt = null;
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if(connection != null) {
			try {
				statement = connection.prepareStatement(sql);
				setParameters(statement, parameters);
				resultSet = statement.executeQuery();
				if(resultSet.next()) {
					cnt = resultSet.getInt(1);
				}
				return cnt;
			} catch (SQLException e) {
				return null;
			} finally {
				try {
					if(connection != null) {
						connection.close();
					}
					if(statement != null) {
						statement.close();
					}
					if(resultSet != null) {
						resultSet.close();
					}
				} catch (SQLException e) {
					return null;
				}
			}
		}
		return null;
	}
	
}
