package com.organicfoods.service.impl;

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

}
