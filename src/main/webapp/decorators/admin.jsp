<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Home</title>
	
    <!-- Custom fonts for this template-->
    <link href="<c:url value = '/template/admin/vendor/fontawesome-free/css/all.min.css'/>" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value = '/template/admin/css/sb-admin-2.min.css'/>" rel="stylesheet">
    
    <!-- Link JQUERY twbsPagination -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="<c:url value = '/template/paging/jquery.twbsPagination.js'/>" type="text/javascript"></script>
    
    
</head>
<body>
	<div id = "wrapper">
		<!-- Sidebar -->
		<%@include file = "/common/admin/menu.jsp" %>
		<!-- End of Sidebar -->
		
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<!-- Topbar -->
				<%@include file = "/common/admin/header.jsp" %>
				<!-- End of Topbar -->
				
				<!-- Begin Page Content -->
				<decorator:body/>
				<!-- /.container-fluid -->
			</div>
			<!-- Footer -->
			<%@include file = "/common/admin/footer.jsp" %>
			<!-- End of Footer -->
		</div>
	
	</div>

	<!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
    
    <!-- Bootstrap core JavaScript-->
    <script src="<c:url value = '/template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>

    <!-- Core plugin JavaScript-->
    <script src="<c:url value = '/template/admin/vendor/jquery-easing/jquery.easing.min.js'/>"></script>

    <!-- Custom scripts for all pages-->
    <script src="<c:url value = '/template/admin/js/sb-admin-2.min.js'/>"></script>

    <!-- Page level plugins -->
    <script src="<c:url value = '/template/admin/vendor/chart.js/Chart.min.js'/>"></script>

    <!-- Page level custom scripts -->
    <script src="<c:url value = '/template/admin/js/demo/chart-area-demo.js'/>"></script>
    <script src="<c:url value = '/template/admin/js/demo/chart-pie-demo.js'/>"></script>

</body>
</html>