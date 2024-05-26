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
import com.organicfoods.service.ICategoryService;
import com.organicfoods.service.IProductService;
import com.organicfoods.service.IUserService;
import com.organicfoods.util.EmailUtil;
import com.organicfoods.util.EmailUtilExecutor;
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
	
	@Inject
	private ICategoryService categoryService;
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserModel user = FormUtil.mapValueToModel(UserModel.class, req);
		String view = "";
		if(user.getAction() != null && user.getAction().equals(SystemConstant.SHOP)) {
			ProductModel model = new ProductModel();
			model.setListResults(productService.findAll());
			for(ProductModel product : model.getListResults()) {
				product.setCategoryCode(categoryService.findById(product.getCategoryId()).getName());
			}
			req.setAttribute(SystemConstant.MODEL, model);
			view = "/views/web/shop.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
		}
		else if(user.getAction() != null && user.getAction().equals(SystemConstant.SHOP_DETAIL)) {
			view = "/views/web/pages/shop-detail.jsp";
			Long productId = Long.parseLong(req.getParameter("productId"));
			ProductModel model = productService.findById(productId);
			model.setCategoryCode(categoryService.findById(model.getCategoryId()).getName());
			req.setAttribute(SystemConstant.MODEL, model);
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
		}
		else if(user.getAction() != null && user.getAction().equals(SystemConstant.PAGES_CART)) {
			view = "/views/web/pages/cart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
		}
		else if(user.getAction() != null && user.getAction().equals(SystemConstant.PAGES_CHECKOUT)) {
			if(user.getAlert() != null && user.getMessage() != null) {
				req.setAttribute(SystemConstant.ALERT, user.getAlert());
				req.setAttribute(SystemConstant.MESSAGE, resourceBundle.getString(user.getMessage()));
			}
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
			SessionUtil.getInstance().removeValue(req, SystemConstant.TOTALBILL);
			SessionUtil.getInstance().removeValue(req, SystemConstant.CART);
			resp.sendRedirect(req.getContextPath() + "/trang-chu");
		}
		else {
			UserModel userModel = (UserModel)SessionUtil.getInstance().getValue(req, SystemConstant.USERMODEL);
			HashMap<Long, BillDetailsModel> cartNew = new HashMap<Long, BillDetailsModel>();
			HashMap<Long, BillDetailsModel> cartOld = (HashMap<Long, BillDetailsModel>)SessionUtil.getInstance().getValue(req, SystemConstant.CART);
			if(userModel != null && cartNew.size() != cartOld.size()) {
				Double totalBill = 0D;
				for(BillDetailsModel item : billDetailsService.findByCreatedBy(userModel.getUserName())) {
					item.setProduct(productService.findById(item.getProductId()));
					totalBill += item.getTotalPrice();
					cartNew.put(item.getProductId(), item);
				}
				SessionUtil.getInstance().putValue(req, SystemConstant.CART, cartNew);
				SessionUtil.getInstance().putValue(req, SystemConstant.TOTALBILL, totalBill);
			}
			view = "/views/web/home.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		UserModel user = FormUtil.mapValueToModel(UserModel.class, req);
		System.out.println(user.getEmail());
		System.out.println(user.getAction());
		if(user.getAction() != null && user.getAction().equals(SystemConstant.CONTACT)) {
			final String email = user.getEmail();
			System.out.println(email);
			EmailUtilExecutor.getEmailExecutor().submit(() -> {
				EmailUtil.getInstance().sendTo(email, "Thông báo nhận phản hồi", "Cảm ơn bạn đã gửi phản hồi đến chúng tôi!");
			});
			resp.sendRedirect(req.getContextPath() + "/trang-chu");
		}
		else if(user.getAction() != null && user.getAction().equals(SystemConstant.REGISTER)) {
			UserModel checkUser = userService.findByUsernameOrEmailOrPhone(user.getUserName(), user.getEmail(), user.getPhone());
			if(checkUser == null) {
				Long id = userService.insertUserModel(user);
				user = userService.findById(id);
				
				final String email = user.getEmail();
				EmailUtilExecutor.getEmailExecutor().submit(() -> {
					EmailUtil.getInstance().sendTo(email, "Đăng ký tài khoản", "Tạo tài khoản thành công!");
				});
			
				resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&alert=success&message=message_success_register");
			}
			else {
				resp.sendRedirect(req.getContextPath() + "/dang-ky?action=register&alert=danger&message=message_exist");
			}
		}
		else if(user.getAction() != null && user.getAction().equals(SystemConstant.LOGIN)) {
			user = userService.findByUsernameAndPassword(user.getUserName(), user.getPassWord());
			if(user != null) {
				if(user.getStatus() == 1) {
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
					final String email = user.getEmail();
					EmailUtilExecutor.getEmailExecutor().submit(() -> {
						EmailUtil.getInstance().sendTo(email, "Thông tin tài khoản", "Tài khoản của bạn tạm thời bị khóa! Vui lòng liên hệ 1900 7474!");
					});
					
					resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&alert=warning&message=message_locked_account");
				}
			}
			else {
				resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&alert=danger&message=message_login_fail");
			}
		}
	}
}