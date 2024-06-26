package com.organicfoods.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.organicfoods.mapper.RowMapper;
import com.organicfoods.model.BillModel;

public class BillMapper implements RowMapper<BillModel>{

	@Override
	public BillModel mapRow(ResultSet resultSet) {
		BillModel bill = new BillModel();
		try {
			bill.setId(resultSet.getLong("id"));
			bill.setName(resultSet.getString("name"));
			bill.setPhone(resultSet.getString("phone"));
			bill.setShippingAddress(resultSet.getString("shippingaddress"));
			bill.setUserId(resultSet.getLong("userid"));
			bill.setStatus(resultSet.getString("status"));
			bill.setTotalPrice(resultSet.getDouble("totalprice"));
			bill.setCreatedDate(resultSet.getTimestamp("createddate"));
			bill.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			bill.setCreatedBy(resultSet.getString("createdby"));
			bill.setModifiedBy(resultSet.getString("modifiedby"));
			return bill;
		} catch (SQLException e) {
			return null;
		}
	}

}
