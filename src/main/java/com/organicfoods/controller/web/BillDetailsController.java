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
import com.organicfoods.model.UserModel;
import com.organicfoods.service.IBillDetailsService;
import com.organicfoods.service.IProductService;
import com.organicfoods.util.FormUtil;
import com.organicfoods.util.SessionUtil;

@WebServlet(urlPatterns = {"/them-san-pham"})
public class BillDetailsController extends HttpServlet{

	private static final long serialVersionUID = 5997180679319518024L;
	
	@Inject
	private IBillDetailsService billDetailsService;
	
	@Inject
	private IProductService productService;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String priceStr = req.getParameter("price");
		String type = req.getParameter("type");
		UserModel user = (UserModel)SessionUtil.getInstance().getValue(req, SystemConstant.USERMODEL);
		Double totalBill = (Double)SessionUtil.getInstance().getValue(req, SystemConstant.TOTALBILL);
		
		BillDetailsModel billDetail = FormUtil.mapValueToModel(BillDetailsModel.class, req);
		billDetail.setCreatedBy(user.getUserName());
		HashMap<Long, BillDetailsModel> cart = (HashMap<Long, BillDetailsModel>)SessionUtil.getInstance().getValue(req, SystemConstant.CART);

		if(cart == null) {	
			cart = new HashMap<Long, BillDetailsModel>();
			billDetail.setTotalPrice(Double.parseDouble(priceStr));
			Long id = billDetailsService.insertBillDetail(billDetail);
			billDetail = billDetailsService.findById(id);
			billDetail.setProduct(productService.findById(billDetail.getProductId()));
			cart.put(billDetail.getProductId(), billDetail);
			SessionUtil.getInstance().putValue(req, SystemConstant.CART, cart);
			SessionUtil.getInstance().putValue(req, SystemConstant.TOTALBILL, billDetail.getTotalPrice());
			resp.sendRedirect(req.getContextPath() + "/trang-chu?action=shop");
		}
		else {
			if(cart.containsKey(billDetail.getProductId())) {
				cart.get(billDetail.getProductId()).incrementQuantity();
				cart.get(billDetail.getProductId()).updateTotalPrice(Double.parseDouble(priceStr));
				cart.get(billDetail.getProductId()).setModifiedBy(user.getUserName());
				billDetailsService.updateByProductIdAndCreatedBy(cart.get(billDetail.getProductId()), 
									billDetail.getProductId(), user.getUserName());
				SessionUtil.getInstance().putValue(req, SystemConstant.TOTALBILL, totalBill + Double.parseDouble(priceStr));
			}
			else {
				billDetail.setTotalPrice(Double.parseDouble(priceStr));
				Long id = billDetailsService.insertBillDetail(billDetail);
				billDetail = billDetailsService.findById(id);
				billDetail.setProduct(productService.findById(billDetail.getProductId()));
				cart.put(billDetail.getProductId(), billDetail);
				SessionUtil.getInstance().putValue(req, SystemConstant.TOTALBILL, totalBill + billDetail.getTotalPrice());
			}
			SessionUtil.getInstance().putValue(req, SystemConstant.CART, cart);
			if(type.equals("add")) {
				resp.sendRedirect(req.getContextPath() + "/trang-chu?action=shopDetail&productId=" + billDetail.getProductId());
			}
			else if(type.equals("buy")) {
				resp.sendRedirect(req.getContextPath() + "/trang-chu?action=pages_cart");
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
