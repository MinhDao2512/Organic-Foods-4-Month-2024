package com.organicfoods.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.organicfoods.dao.IBillDetailsDAO;
import com.organicfoods.model.BillDetailsModel;
import com.organicfoods.service.IBillDetailsService;

public class BillDetailsService implements IBillDetailsService{

	@Inject
	private IBillDetailsDAO billDetailsDAO;
	
	@Override
	public Long insertBillDetail(BillDetailsModel billDetail) {
		return billDetailsDAO.insertBillDetail(billDetail);
	}

	@Override
	public Boolean updateByProductIdAndCreatedBy(BillDetailsModel billDetail, Long productId, String createdBy) {
		return billDetailsDAO.updateByProductIdAndCreatedBy(billDetail, productId, createdBy);
	}

	@Override
	public List<BillDetailsModel> findByCreatedBy(String createBy) {
		return billDetailsDAO.findByCreatedBy(createBy);
	}

	@Override
	public List<BillDetailsModel> findByProductId(Long productId) {
		return billDetailsDAO.findByProductId(productId);
	}

	@Override
	public Boolean deleteBillDetails(Long id) {
		return billDetailsDAO.deleteBillDetails(id);
	}

	@Override
	public Boolean updateBillId(Long id, Long billId) {
		return billDetailsDAO.updateBillId(id, billId);
	}

	@Override
	public BillDetailsModel findById(Long id) {
		return billDetailsDAO.findById(id);
	}

	@Override
	public List<BillDetailsModel> findByBllId(Long billId) {
		return billDetailsDAO.findByBillId(billId);
	}

}
