package com.organicfoods.controller.web.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.organicfoods.model.UserModel;
import com.organicfoods.service.IUserService;
import com.organicfoods.util.HttpUtil;

@WebServlet(urlPatterns = {"/api-web-user"})
public class UserAPI extends HttpServlet{
	
	private static final long serialVersionUID = -608220788421710227L;
	
	@Inject
	private IUserService userService;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserModel user = HttpUtil.toStrJSON(req.getReader()).toModel(UserModel.class);
		UserModel checkUser = userService.findByUsernameOrEmailOrPhone(user.getUserName(), user.getEmail(), user.getPhone());
		if(checkUser == null) {
			Long id = userService.insertUserModel(user);
			user = userService.findById(id);
		}
		HttpUtil.toJSON(resp.getOutputStream(), user);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}
}
