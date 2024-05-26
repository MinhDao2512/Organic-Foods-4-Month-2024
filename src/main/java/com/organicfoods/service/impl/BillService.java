package com.organicfoods.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.organicfoods.dao.IBillDAO;
import com.organicfoods.model.BillModel;
import com.organicfoods.service.IBillService;

public class BillService implements IBillService{

	@Inject
	private IBillDAO billDAO;
	
	@Override
	public Long insertBillModel(BillModel billModel) {
		return billDAO.insertBillModel(billModel);
	}

	@Override
	public List<BillModel> findByUserId(Long userId) {
		return billDAO.findByUserId(userId);
	}

	@Override
	public Boolean deleteById(Long id) {
		return billDAO.deleteById(id);
	}

}
