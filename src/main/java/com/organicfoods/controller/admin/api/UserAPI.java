package com.organicfoods.controller.admin.api;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.organicfoods.constant.SystemConstant;
import com.organicfoods.model.BillDetailsModel;
import com.organicfoods.model.BillModel;
import com.organicfoods.model.ProductModel;
import com.organicfoods.model.UserModel;
import com.organicfoods.service.IBillDetailsService;
import com.organicfoods.service.IBillService;
import com.organicfoods.service.IProductService;
import com.organicfoods.service.IRoleService;
import com.organicfoods.service.IUserService;
import com.organicfoods.util.HttpUtil;
import com.organicfoods.util.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-user"})
public class UserAPI extends HttpServlet{

	private static final long serialVersionUID = -6190514053216639778L;
	
	@Inject
	private IUserService userService;
	
	@Inject
	private IRoleService roleService;
	
	@Inject
	private IProductService productService;
	
	@Inject
	private IBillDetailsService billDetailsService;
	
	@Inject
	private IBillService billService;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserModel user = HttpUtil.toStrJSON(req.getReader()).toModel(UserModel.class);
		UserModel checkUser = userService.findByUsernameAndPassword(user.getUserName(), user.getPassWord());
		if(checkUser == null) {
			user.setRoleId(roleService.findByCode(user.getRoleCode()).getId());
			Long id = userService.insertUserModel(user);
			user = userService.findById(id);
		}
		HttpUtil.toJSON(resp.getOutputStream(), user);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserModel admin = (UserModel)SessionUtil.getInstance().getValue(req, SystemConstant.USERMODEL);
		UserModel user = HttpUtil.toStrJSON(req.getReader()).toModel(UserModel.class);
		user.setRoleId(roleService.findByCode(user.getRoleCode()).getId());
		user.setModifiedBy(admin.getUserName());
		user.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		Long id = user.getId();
		userService.updateUserModel(user);
		user = userService.findById(id);
		HttpUtil.toJSON(resp.getOutputStream(), user);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserModel user = HttpUtil.toStrJSON(req.getReader()).toModel(UserModel.class);
		for(Long id : user.getIds()) {
			String userName = userService.findById(id).getUserName();
			List<ProductModel> products = productService.findBySeller(userName);
			List<BillModel> bills = billService.findByUserId(id);
			if(products != null) {
				for(ProductModel product : products) {
					List<BillDetailsModel> list = billDetailsService.findByProductId(product.getId());
					if(list != null) {
						for(BillDetailsModel billDetail : list) {
							billDetailsService.deleteBillDetails(billDetail.getId());
						}
					}
					productService.deleteProduct(product.getId());
				}
			}
			if(bills != null) {
				for(BillModel bill : bills) {
					List<BillDetailsModel> billDetails = billDetailsService.findByBllId(bill.getId());
					for(BillDetailsModel billDetail : billDetails) {
						billDetailsService.deleteBillDetails(billDetail.getId());
					}
					billService.deleteById(bill.getId());
				}
			}
			userService.deleteUserModel(id);
		}
		HttpUtil.toJSON(resp.getOutputStream(), null);
	}
}
