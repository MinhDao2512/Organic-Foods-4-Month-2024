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
                        <a href="<c:url value = '/trang-chu?action=pages_404page'/>" class="dropdown-item">404 Page</a>
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
                <a href="#" class="my-auto">
                    <i class="fas fa-user fa-2x"></i>
                </a>
            </div>
        </div>
    </nav>
</div>