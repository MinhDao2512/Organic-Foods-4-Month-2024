<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <title>Fruitables-Vegetable</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet"> 

    <!-- Icon Font Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="<c:url value = '/template/web/lib/lightbox/css/lightbox.min.css'/>" rel="stylesheet">
    <link href="<c:url value = '/template/web/lib/owlcarousel/assets/owl.carousel.min.css'/>" rel="stylesheet">


    <!-- Customized Bootstrap Stylesheet -->
    <link href="<c:url value = '/template/web/css/bootstrap.min.css'/>" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="<c:url value = '/template/web/css/style.css'/>" rel="stylesheet">
    
</head>
<body>
	<!-- Spinner Start -->
    <div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
        <div class="spinner-grow text-primary" role="status"></div>
    </div>
    <!-- Spinner End -->
    
    <!-- NavBar -->
    <div class="container-fluid fixed-top">
    	<!-- Start Header -->
    	<%@include file = "/common/web/header.jsp"%>
    	<!-- Header End -->
    	<!-- Menu Start-->
    	<%@include file = "/common/web/menu.jsp"%>
    	<!-- End Menu -->
    </div>
    <!-- End NavBar -->
    
    <!-- Modal Search Start -->
    <%@include file = "/common/web/model_search.jsp" %>
    <!-- Modal Search End -->
    
    <!-- Start Body -->
    <decorator:body/>
    <!-- End Body -->
    
    <!-- Start Footer -->
    <div class="container-fluid bg-dark text-white-50 footer pt-5 mt-5">
    	<%@include file = "/common/web/footer.jsp" %>
    </div>
    <!-- End Footer -->
    
    <!-- Copyright Start -->
    <div class="container-fluid copyright bg-dark py-4">
    	<%@include file = "/common/web/copyright.jsp" %>
    </div>
    <!-- Copyright End -->
    
    <!-- Back to Top -->
    <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>
	
	
	<!-- JavaScript Libraries -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="<c:url value = '/template/web/lib/easing/easing.min.js'/>"></script>
    <script src="<c:url value = '/template/web/lib/waypoints/waypoints.min.js'/>"></script>
    <script src="<c:url value = '/template/web/lib/lightbox/js/lightbox.min.js'/>"></script>
    <script src="<c:url value = '/template/web/lib/owlcarousel/owl.carousel.min.js'/>"></script>
    
    <!-- Template Javascript -->
    <script src="<c:url value = '/template/web/js/main.js'/>"></script>
</body>
</html>