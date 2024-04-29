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
import com.organicfoods.paging.Pageble;
import com.organicfoods.paging.impl.PageRequest;
import com.organicfoods.service.ICategoryService;
import com.organicfoods.service.IProductService;
import com.organicfoods.sorting.Sorter;
import com.organicfoods.util.FormUtil;

@WebServlet(urlPatterns = {"/admin-products"})
public class ProductsController extends HttpServlet{

	private static final long serialVersionUID = 8368588073868012528L;

	@Inject
	private IProductService productService;
	
	@Inject
	private ICategoryService categoryService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductModel model = FormUtil.mapValueToModel(ProductModel.class, req);
		String view = "/views/admin/products/list.jsp";
		if(model.getType() != null && model.getType().equals(SystemConstant.LIST_PRODUCTS)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getItemsPerPage(), new Sorter(model.getSortName(),model.getSortBy()));
			model.setTotalItems(productService.countProducts());
			model.setTotalPages();
			model.setListResults(productService.findByOffsetAndLimit(pageble));
			req.setAttribute(SystemConstant.TOTAL, productService.countProducts());
		}
		else if(model.getType() != null && model.getType().equals(SystemConstant.EDIT)) {
			if(model.getId() != null) {
				model = productService.findById(model.getId());
			}
			else {
				
			}
			req.setAttribute("categories", categoryService.findAll());
			view = "/views/admin/products/edit.jsp";
		}
		else if(model.getType() != null && model.getType().equals(SystemConstant.SEARCH)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getItemsPerPage(), new Sorter(model.getSortName(),model.getSortBy()));
			model.setTotalItems(productService.countProductsByCode(model.getKeyword()));
			model.setTotalPages();
			model.setListResults(productService.findByCode(pageble,model.getKeyword()));
			req.setAttribute(SystemConstant.TOTAL, productService.countProductsByCode(model.getKeyword()));
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
