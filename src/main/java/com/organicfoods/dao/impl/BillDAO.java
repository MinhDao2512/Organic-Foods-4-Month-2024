package com.organicfoods.dao.impl;

import java.sql.Timestamp;

import com.organicfoods.dao.IBillDAO;
import com.organicfoods.model.BillModel;

public class BillDAO extends AbstractDAO<BillModel> implements IBillDAO{

	@Override
	public Long insertBillModel(BillModel billModel) {
		billModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		StringBuilder sql = new StringBuilder("INSERT INTO bill(name,phone,shippingaddress,userid,status,totalprice,createddate,createdby)");
		sql.append(" VALUES(?,?,?,?,?,?,?,?)");
		return insert(sql.toString(), billModel.getName(),billModel.getPhone(),billModel.getShippingAddress(),billModel.getUserId(),
						billModel.getStatus(),billModel.getTotalPrice(),billModel.getCreatedDate(),billModel.getCreatedBy());
	}

}
