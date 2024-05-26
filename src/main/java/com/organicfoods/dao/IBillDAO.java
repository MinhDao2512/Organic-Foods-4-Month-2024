package com.organicfoods.dao;

import java.util.List;

import com.organicfoods.model.BillModel;

public interface IBillDAO extends GenericDAO<BillModel>{
	Long insertBillModel(BillModel billModel);
	List<BillModel> findByUserId(Long userId);
	Boolean deleteById(Long id);
}
