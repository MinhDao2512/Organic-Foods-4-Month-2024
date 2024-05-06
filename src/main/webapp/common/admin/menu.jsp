<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/common/taglib.jsp" %>
<!DOCTYPE html>
<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<c:url value = '/admin-trang-chu'/>">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Admin home</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Products
    </div>

    <!-- Nav Item - Tables -->
    <li class="nav-item">
        <a class="nav-link" href="<c:url value = '/admin-products?page=1&itemsPerPage=5&sortName=title&sortBy=asc&type=list_products'/>">
            <i class="fas fa-fw fa-table"></i>
            <span>Products Table</span></a>
        <c:if test="${USERMODEL.roleCode == 'ADMIN'}">
	        <a class="nav-link" href="<c:url value = '/admin-user?page=1&itemsPerPage=5&sortName=fullname&sortBy=asc&type=list_users'/>">
	            <i class="fas fa-fw fa-table"></i>
	            <span>Users Table</span></a>
        </c:if>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
<!-- End of Sidebar -->