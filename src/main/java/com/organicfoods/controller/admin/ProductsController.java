package com.organicfoods.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.organicfoods.constant.SystemConstant;
import com.organicfoods.model.ProductModel;
import com.organicfoods.service.IProductService;
import com.organicfoods.util.FormUtil;

@WebServlet(urlPatterns = {"/admin-products"})
public class ProductsController extends HttpServlet{

	private static final long serialVersionUID = 8368588073868012528L;

	@Inject
	private IProductService productService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductModel model = FormUtil.mapValueToModel(ProductModel.class, req);
		String view = "";
		if(model.getType() != null && model.getType().equals("list")) {
			model.setTotalItems(productService.countProducts());
			model.setTotalPages();
			model.setOffset();
			model.setListResults(productService.findByOffsetAndLimit(model.getOffset(),model.getItemsPerPage(),model.getSortName(),
					model.getSortBy()));
			view = "/views/admin/products/list.jsp";
			req.setAttribute("total", productService.countProducts());
		}
		else if(model.getType() != null && model.getType().equals("search")) {
			model.setTotalItems(productService.countProductsByCode(model.getKeyword()));
			model.setTotalPages();
			model.setOffset();
			model.setListResults(productService.findByCode(model.getOffset(),model.getItemsPerPage(),model.getKeyword()));
			view = "/views/admin/products/search.jsp";
			req.setAttribute("total", productService.countProductsByCode(model.getKeyword()));
		}
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
