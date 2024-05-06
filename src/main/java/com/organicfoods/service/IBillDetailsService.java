package com.organicfoods.service;

import java.util.List;

import com.organicfoods.model.BillDetailsModel;

public interface IBillDetailsService {
	Long insertBillDetail(BillDetailsModel billDetail);
	Boolean updateByProductIdAndCreatedBy(BillDetailsModel billDetail, Long productId, String createdBy);
	List<BillDetailsModel> findByCreatedBy(String createBy); 
}
