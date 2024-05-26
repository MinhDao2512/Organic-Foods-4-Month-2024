package com.organicfoods.controller.web;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.organicfoods.constant.SystemConstant;
import com.organicfoods.model.BillDetailsModel;
import com.organicfoods.model.BillModel;
import com.organicfoods.model.UserModel;
import com.organicfoods.service.IBillDetailsService;
import com.organicfoods.service.IBillService;
import com.organicfoods.util.EmailUtil;
import com.organicfoods.util.EmailUtilExecutor;
import com.organicfoods.util.FormUtil;
import com.organicfoods.util.SessionUtil;

@WebServlet(urlPatterns = {"/checkout"})
public class BillController extends HttpServlet{

	private static final long serialVersionUID = 5967258294603132040L;

	@Inject
	private IBillService billService;
	
	@Inject
	private IBillDetailsService billDetailsService;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		BillModel bill = FormUtil.mapValueToModel(BillModel.class, req);
		UserModel user = (UserModel)SessionUtil.getInstance().getValue(req, SystemConstant.USERMODEL);
		HashMap<Long,BillDetailsModel> cart = (HashMap<Long, BillDetailsModel>)SessionUtil.getInstance().getValue(req, SystemConstant.CART);
		bill.setUserId(user.getId());
		bill.setCreatedBy(user.getUserName());
		if(bill.getName() != "" && bill.getPhone() != "" && bill.getShippingAddress() != "" && bill.getStatus() != "") {
			Long billId = billService.insertBillModel(bill);
			for(BillDetailsModel billDetail : cart.values()) {
				billDetailsService.updateBillId(billDetail.getId(), billId);
			}
			cart.clear();
			SessionUtil.getInstance().putValue(req, SystemConstant.CART, cart);
			SessionUtil.getInstance().putValue(req, SystemConstant.TOTALBILL, 0D);
			
			final String email = user.getEmail();
			EmailUtilExecutor.getEmailExecutor().submit(() -> {
				EmailUtil.getInstance().sendTo(email, "Thông báo đơn hàng", "Cảm ơn bạn đã đặt hàng!");
			});
			
			resp.sendRedirect(req.getContextPath() + "/trang-chu?action=pages_checkout&alert=success&message=success_checkout");
		}
		else {
			resp.sendRedirect(req.getContextPath() + "/trang-chu?action=pages_checkout&alert=danger&message=danger_checkout");
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
	
}
