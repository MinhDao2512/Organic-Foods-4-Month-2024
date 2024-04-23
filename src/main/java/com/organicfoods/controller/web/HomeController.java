package com.organicfoods.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.organicfoods.model.ProductModel;
import com.organicfoods.service.IProductService;

@WebServlet(urlPatterns = {"/trang-chu"})
public class HomeController extends HttpServlet{
	
	private static final long serialVersionUID = 6156812776407218423L;
	
	@Inject
	private IProductService productService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String view = "";
		if(action != null && action.equals("shop")) {
			ProductModel model = new ProductModel();
			model.setListResults(productService.findAll());
			req.setAttribute("model", model);
			view = "/views/web/shop.jsp";
		}
		else if(action != null && action.equals("pages_cart")) {
			view = "/views/web/pages/cart.jsp";
		}
		else if(action != null && action.equals("pages_checkout")) {
			view = "/views/web/pages/checkout.jsp";
		}
		else if(action != null && action.equals("pages_testimonial")) {
			view = "/views/web/pages/testimonial.jsp";
		}
		else if(action != null && action.equals("pages_404page")) {
			view = "/views/web/pages/404page.jsp";
		}
		else if(action != null && action.equals("contact")) {
			view = "/views/web/contact.jsp";
		}
		else {
			view = "/views/web/home.jsp";
		}
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action != null && action.equals("contact")) {
			resp.sendRedirect(req.getContextPath() + "/trang-chu?action=contact");
		}
	}
}