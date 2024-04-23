package com.organicfoods.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.organicfoods.model.ProductModel;
import com.organicfoods.service.IProductService;
import com.organicfoods.util.HttpUtil;
 
@WebServlet(urlPatterns = {"/api-admin-product"})
public class ProductAPI extends HttpServlet{

	private static final long serialVersionUID = 4492882597268385832L;

	@Inject
	private IProductService productService;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ProductModel product = HttpUtil.toStrJSON(req.getReader()).toModel(ProductModel.class);
		Long id = productService.insertProduct(product);
		product = productService.findById(id);
		HttpUtil.toJSON(resp.getOutputStream(), product);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ProductModel product = HttpUtil.toStrJSON(req.getReader()).toModel(ProductModel.class);
		Long id = product.getId();
		productService.updateProduct(product);
		product = productService.findById(id);
		HttpUtil.toJSON(resp.getOutputStream(), product);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ProductModel product = HttpUtil.toStrJSON(req.getReader()).toModel(ProductModel.class);
		Long id = product.getId();
		productService.deleteProduct(id);
		product = productService.findById(id);
		HttpUtil.toJSON(resp.getOutputStream(), product);
	}
}
