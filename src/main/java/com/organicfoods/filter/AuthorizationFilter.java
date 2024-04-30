package com.organicfoods.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.organicfoods.constant.SystemConstant;
import com.organicfoods.model.UserModel;
import com.organicfoods.util.SessionUtil;

public class AuthorizationFilter implements Filter{

	private ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.setContext(filterConfig.getServletContext());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		if(uri.startsWith("/veggiesandfruits4month2024/admin")) {
			UserModel user = (UserModel)SessionUtil.getInstance().getValue(req, SystemConstant.USERMODEL);
			if(user != null) {
				if(user.getRoleCode().equals(SystemConstant.USER)) {
					resp.sendRedirect(req.getContextPath() + "/trang-chu?action=pages_404page");
				}
				else {
					chain.doFilter(request, response);
				}
			}
			else {
				resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login");
			}
		}
		else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

}
