package com.organicfoods.service;

import java.util.List;

import com.organicfoods.model.BillModel;

public interface IBillService {
	Long insertBillModel(BillModel billModel);
	List<BillModel> findByUserId(Long userId);
	Boolean deleteById(Long id);
}
