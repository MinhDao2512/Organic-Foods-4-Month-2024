package com.organicfoods.dao;

import java.util.List;

import com.organicfoods.model.BillDetailsModel;

public interface IBillDetailsDAO extends GenericDAO<BillDetailsModel>{
	Long insertBillDetail(BillDetailsModel billDetail);
	Boolean updateByProductIdAndCreatedBy(BillDetailsModel billDetail, Long productId, String createdBy);
	List<BillDetailsModel> findByCreatedBy(String createdBy); 
}
