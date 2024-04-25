<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/common/taglib.jsp" %>
<!DOCTYPE html>
<div class="container px-0">
    <nav class="navbar navbar-light bg-white navbar-expand-xl">
        <a href="<c:url value = '/trang-chu'/>" class="navbar-brand"><h1 class="text-primary display-6">Fruitables</h1></a>
        <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="fa fa-bars text-primary"></span>
        </button>
        <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
            <div class="navbar-nav mx-auto">
                <a href="<c:url value = '/trang-chu'/>" class="nav-item nav-link active">Home</a>
                <a href="<c:url value = '/trang-chu?action=shop&status=active'/>" class="nav-item nav-link">Shop</a>
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                    <div class="dropdown-menu m-0 bg-secondary rounded-0">
                        <a href="<c:url value = '/trang-chu?action=pages_cart'/>" class="dropdown-item">Cart</a>
                        <a href="<c:url value = '/trang-chu?action=pages_checkout'/>" class="dropdown-item">Checkout</a>
                        <a href="<c:url value = '/trang-chu?action=pages_testimonial'/>" class="dropdown-item">Testimonial</a>
                    </div>
                </div>
                <a href="<c:url value = '/trang-chu?action=contact'/>" class="nav-item nav-link">Contact</a>
            </div>
            <div class="d-flex m-3 me-0">
                <button class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4" data-bs-toggle="modal" data-bs-target="#searchModal"><i class="fas fa-search text-primary"></i></button>
                <a href="#" class="position-relative me-4 my-auto">
                    <i class="fa fa-shopping-bag fa-2x"></i>
                    <span class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1" style="top: -5px; left: 15px; height: 20px; min-width: 20px;">3</span>
                </a>
                <c:if test="${not empty USERMODEL}">
                	<ul class="navbar-nav ml-auto">
		                <li class="nav-item dropdown no-arrow">
				            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
				                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${USERMODEL.role.code}</span>
				                <i class="fas fa-user fa-2x"></i>
				            </a>
				            
				            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
				                aria-labelledby="userDropdown">
				                <a class="dropdown-item" href="#">
				                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
				                    Profile
				                </a>
				                <a class="dropdown-item" href="#">
				                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
				                    Settings
				                </a>
				                <c:if test="${USERMODEL.role.code == 'ADMIN'}">
				                <a class="dropdown-item" href="<c:url value = '/admin-trang-chu'/>">
				                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
				                    Admin home
				                </a>
				                </c:if>
				                <div class="dropdown-divider"></div>
				                <a class="dropdown-item" href="<c:url value = '/thoat?action=logout'/>" data-toggle="modal" data-target="#logoutModal">
				                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
				                    Logout
				                </a>
				            </div>
				        </li>
			        </ul>
                </c:if>
                <c:if test="${empty USERMODEL}">
	                <a href="<c:url value = '/dang-nhap?action=login'/>" class="my-auto">
	                    <i class="fas fa-user fa-2x"></i>
	                </a>
                </c:if>
            </div>
        </div>
    </nav>
</div>