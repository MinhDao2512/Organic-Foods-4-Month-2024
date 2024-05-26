package com.organicfoods.service;

import java.util.List;

import com.organicfoods.model.BillDetailsModel;

public interface IBillDetailsService {
	Long insertBillDetail(BillDetailsModel billDetail);
	Boolean updateByProductIdAndCreatedBy(BillDetailsModel billDetail, Long productId, String createdBy);
	List<BillDetailsModel> findByCreatedBy(String createBy); 
	List<BillDetailsModel> findByProductId(Long productId);
	List<BillDetailsModel> findByBllId(Long billId);
	Boolean deleteBillDetails(Long id);
	Boolean updateBillId(Long id, Long billId);
	BillDetailsModel findById(Long id);
}
