package com.organicfoods.controller.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.organicfoods.constant.SystemConstant;
import com.organicfoods.model.BillDetailsModel;
import com.organicfoods.model.UserModel;
import com.organicfoods.util.FormUtil;
import com.organicfoods.util.SessionUtil;

@WebServlet(urlPatterns = {"/them-san-pham"})
public class BillDetailsController extends HttpServlet{

	private static final long serialVersionUID = 5997180679319518024L;
		
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String priceStr = req.getParameter("price");
		UserModel user = (UserModel)SessionUtil.getInstance().getValue(req, SystemConstant.USERMODEL);
		BillDetailsModel billDetail = FormUtil.mapValueToModel(BillDetailsModel.class, req);
		billDetail.setCreatedBy(user.getUserName());
		
		HashMap<Long, BillDetailsModel> cart = (HashMap<Long, BillDetailsModel>)SessionUtil.getInstance().getValue(req, "CART");

		if(cart == null) {
			cart = new HashMap<Long, BillDetailsModel>();
			billDetail.setTotalPrice(Double.parseDouble(priceStr));
			cart.put(billDetail.getProductId(), billDetail);
			SessionUtil.getInstance().putValue(req, SystemConstant.CART, cart);
			resp.sendRedirect(req.getContextPath() + "/trang-chu?action=shop");
		}
		else {
			if(cart.containsKey(billDetail.getProductId())) {
				cart.get(billDetail.getProductId()).incrementQuantity();
				cart.get(billDetail.getProductId()).setTotalPrice(Double.parseDouble(priceStr));
				System.out.println(cart.get(billDetail.getProductId()).getTotalPrice());
			}
			else {
				billDetail.setTotalPrice(Double.parseDouble(priceStr));
				cart.put(billDetail.getProductId(), billDetail);
			}
			SessionUtil.getInstance().putValue(req, SystemConstant.CART, cart);
			resp.sendRedirect(req.getContextPath() + "/trang-chu?action=shop");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
