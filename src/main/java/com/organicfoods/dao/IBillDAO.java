package com.organicfoods.dao;

import com.organicfoods.model.BillModel;

public interface IBillDAO extends GenericDAO<BillModel>{
	Long insertBillModel(BillModel billModel);
}
