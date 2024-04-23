package com.organicfoods.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.organicfoods.mapper.RowMapper;
import com.organicfoods.model.BillDetailsModel;

public class BillDetailsMapper implements RowMapper<BillDetailsModel>{

	@Override
	public BillDetailsModel mapRow(ResultSet resultSet) {
		BillDetailsModel billDetails = new BillDetailsModel();
		try {
			billDetails.setId(resultSet.getLong("id"));
			billDetails.setProductId(resultSet.getLong("productid"));
			billDetails.setQuantity(resultSet.getInt("quantity"));
			billDetails.setTotalPrice(resultSet.getDouble("totalprice"));
			billDetails.setBillId(resultSet.getLong("billid"));
			billDetails.setCreatedDate(resultSet.getTimestamp("createddate"));
			billDetails.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			billDetails.setCreatedBy(resultSet.getString("createdby"));
			billDetails.setModifiedBy(resultSet.getString("modifiedby"));
		} catch (SQLException e) {
			return null;
		}
		return null;
	}

}
