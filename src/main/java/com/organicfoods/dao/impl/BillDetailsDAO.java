package com.organicfoods.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import com.organicfoods.dao.IBillDetailsDAO;
import com.organicfoods.mapper.impl.BillDetailsMapper;
import com.organicfoods.model.BillDetailsModel;

public class BillDetailsDAO extends AbstractDAO<BillDetailsModel> implements IBillDetailsDAO{

	@Override
	public Long insertBillDetail(BillDetailsModel billDetail) {
		billDetail.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		StringBuilder sql = new StringBuilder("INSERT INTO billdetails");
		sql.append("(productid,quantity,totalprice,createdby,createddate) ");
		sql.append("VALUES(?,?,?,?,?)");
		return insert(sql.toString(), billDetail.getProductId(),billDetail.getQuantity(),billDetail.getTotalPrice(),
						billDetail.getCreatedBy(),billDetail.getCreatedDate());
	}

	@Override
	public Boolean updateByProductIdAndCreatedBy(BillDetailsModel billDetail, Long productId, String createdBy) {
		billDetail.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		StringBuilder sql = new StringBuilder("UPDATE billdetails ");
		sql.append("SET quantity=?, totalprice=?, modifiedby=?, modifieddate=? ");
		sql.append("WHERE productid=? AND createdby=?");
		return updateOrDelete(sql.toString(), billDetail.getQuantity(), billDetail.getTotalPrice(), billDetail.getModifiedBy(),
								billDetail.getModifiedDate(), productId, createdBy);
	}

	@Override
	public List<BillDetailsModel> findByCreatedBy(String createdBy) {
		String sql = "SELECT * FROM billdetails WHERE createdby = ?";
		return query(sql, new BillDetailsMapper(), createdBy);
	}

	@Override
	public List<BillDetailsModel> findByProductId(Long productId) {
		String sql = "SELECT * from billdetails WHERE productid = ?";
		return query(sql, new BillDetailsMapper(), productId);
	}

	@Override
	public Boolean deleteBillDetails(Long id) {
		String sql = "DELETE FROM billdetails WHERE id=?";
		return updateOrDelete(sql, id);
	}

}
