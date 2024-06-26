package com.organicfoods.controller.admin;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.organicfoods.constant.SystemConstant;
import com.organicfoods.model.UserModel;
import com.organicfoods.paging.Pageble;
import com.organicfoods.paging.impl.PageRequest;
import com.organicfoods.service.IRoleService;
import com.organicfoods.service.IUserService;
import com.organicfoods.sorting.Sorter;
import com.organicfoods.util.FormUtil;

@WebServlet(urlPatterns = {"/admin-user"})
public class UserController extends HttpServlet{

	private static final long serialVersionUID = -5524029303514305869L;
	
	@Inject
	private IUserService userService;
	
	@Inject
	private IRoleService roleService;
	
	ResourceBundle bundle = ResourceBundle.getBundle("message");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserModel model = FormUtil.mapValueToModel(UserModel.class, req);
		Pageble pageble = new PageRequest(model.getPage(), model.getItemsPerPage(), new Sorter(model.getSortName(),model.getSortBy()));
		String view = "/views/admin/users/list.jsp";
		if(model.getType() != null && model.getType().equals(SystemConstant.LIST_USERS)) {
			model.setTotalItems(userService.countUsers());
			model.setTotalPages();
			model.setListResults(userService.findByOffsetAndLimit(pageble));
		}
		else if(model.getType() != null && model.getType().equals(SystemConstant.SEARCH)) {
			model.setTotalItems(userService.countUsersByUsername(model.getKeyword()));
			model.setTotalPages();
			model.setListResults(userService.findByUsername(pageble,model.getKeyword()));
		}
		else if(model.getType() != null && model.getType().equals(SystemConstant.EDIT)) {
			if(model.getAlert() != null && model.getMessage() != null) {
				req.setAttribute(SystemConstant.ALERT, model.getAlert());
				req.setAttribute(SystemConstant.MESSAGE, bundle.getString(model.getMessage()));
			}
			if(model.getId() != null) {
				model = userService.findById(model.getId());
			}
			else {
				
			}
			req.setAttribute("roles", roleService.findAll());
			view = "/views/admin/users/edit.jsp";
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
