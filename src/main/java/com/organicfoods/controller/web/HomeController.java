package com.organicfoods.controller.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.organicfoods.constant.SystemConstant;
import com.organicfoods.model.BillDetailsModel;
import com.organicfoods.model.ProductModel;
import com.organicfoods.model.UserModel;
import com.organicfoods.service.IBillDetailsService;
import com.organicfoods.service.IProductService;
import com.organicfoods.service.IUserService;
import com.organicfoods.util.FormUtil;
import com.organicfoods.util.SessionUtil;

@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap", "/thoat","/dang-ky"})
public class HomeController extends HttpServlet{
	
	private static final long serialVersionUID = 6156812776407218423L;
	
	@Inject
	private IProductService productService;
	
	@Inject
	private IUserService userService;
	
	@Inject
	private IBillDetailsService billDetailsService;
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserModel user = FormUtil.mapValueToModel(UserModel.class, req);
		String view = "";
		if(user.getAction() != null && user.getAction().equals(SystemConstant.SHOP)) {
			ProductModel model = new ProductModel();
			model.setListResults(productService.findAll());
			req.setAttribute(SystemConstant.MODEL, model);
			view = "/views/web/shop.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
		}
		else if(user.getAction() != null && user.getAction().equals(SystemConstant.PAGES_CART)) {
			view = "/views/web/pages/cart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
		}
		else if(user.getAction() != null && user.getAction().equals(SystemConstant.PAGES_CHECKOUT)) {
			view = "/views/web/pages/checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
		}
		else if(user.getAction() != null && user.getAction().equals(SystemConstant.PAGES_TESTIMONIAL)) {
			view = "/views/web/pages/testimonial.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
		}
		else if(user.getAction() != null && user.getAction().equals(SystemConstant.PAGES_404PAGE)) {
			view = "/views/web/pages/404page.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
		}
		else if(user.getAction() != null && user.getAction().equals(SystemConstant.CONTACT)) {
			view = "/views/web/contact.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
		}
		else if(user.getAction() != null && user.getAction().equals(SystemConstant.REGISTER)) {
			if(user.getAlert() != null && user.getMessage() != null) {
				req.setAttribute("message", resourceBundle.getString(user.getMessage()));
				req.setAttribute("alert", user.getAlert());
			}
			view = "/views/register/register.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
		}
		else if(user.getAction() != null && user.getAction().equals(SystemConstant.LOGIN)) {
			if(user.getAlert() != null && user.getMessage() != null) {
				req.setAttribute("message", resourceBundle.getString(user.getMessage()));
				req.setAttribute("alert", user.getAlert());
			}
			view = "/views/login/login.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
		}
		else if(user.getAction() != null && user.getAction().equals(SystemConstant.LOGOUT)) {
			SessionUtil.getInstance().removeValue(req, SystemConstant.USERMODEL);
			SessionUtil.getInstance().removeValue(req, SystemConstant.CART);
			resp.sendRedirect(req.getContextPath() + "/trang-chu");
		}
		else {
			view = "/views/web/home.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserModel user = FormUtil.mapValueToModel(UserModel.class, req);
		if(user.getAction() != null && user.getAction().equals(SystemConstant.CONTACT)) {
			resp.sendRedirect(req.getContextPath() + "/trang-chu?action=contact");
		}
		else if(user.getAction() != null && user.getAction().equals(SystemConstant.LOGIN)) {
			user = userService.findByUsernameAndPasswordAndStatus(user.getUserName(), user.getPassWord(), 1);
			if(user != null) {
				SessionUtil.getInstance().putValue(req, SystemConstant.USERMODEL, user);
				HashMap<Long, BillDetailsModel> cart = new HashMap<Long, BillDetailsModel>();
 				
				List<BillDetailsModel> results = billDetailsService.findByCreatedBy(user.getUserName());
				if(results != null) {
					Double totalBill = 0D;
					Double shipping = 35000D;
					for(BillDetailsModel item : results) {
						item.setProduct(productService.findById(item.getProductId()));
						totalBill += item.getTotalPrice();
						cart.put(item.getProductId(), item);
					}
					SessionUtil.getInstance().putValue(req, SystemConstant.CART, cart);
					SessionUtil.getInstance().putValue(req, SystemConstant.TOTALBILL, totalBill);
					SessionUtil.getInstance().putValue(req, SystemConstant.SHIPPNG, shipping);
				}
				
				if(user.getRoleCode().equals(SystemConstant.USER)) {
					resp.sendRedirect(req.getContextPath() + "/trang-chu");
				}
				else {
					resp.sendRedirect(req.getContextPath() + "/admin-trang-chu");
				}
			}
			else {
				resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&alert=danger&message=message_success");
			}
		}
	}
}