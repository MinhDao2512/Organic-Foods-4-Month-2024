<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/common/taglib.jsp" %>

<!DOCTYPE html>
<!-- Single Page Header start -->
<div class="container-fluid page-header py-5">
    <h1 class="text-center text-white display-6">Giỏ hàng</h1>
    <ol class="breadcrumb justify-content-center mb-0">
        <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
        <li class="breadcrumb-item"><a href="#">Dịch vụ</a></li>
        <li class="breadcrumb-item active text-white">Giỏ hàng</li>
    </ol>
</div>
<!-- Single Page Header End -->

<!-- Cart Page Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="table-responsive">
            <table class="table">
                <thead>
                  <tr>
                    <th scope="col">Sản phẩm</th>
                    <th scope="col">Tên</th>
                    <th scope="col">Giá</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Tổng</th>
                    <th scope="col">Xóa</th>
                  </tr>
                </thead>
                <tbody>
                	<c:forEach var="entry" items="${CART}">
	                    <tr>
	                        <th scope="row">
	                            <div class="d-flex align-items-center">
	                                <img src="${entry.value.product.thumbnail}" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="">
	                            </div>
	                        </th>
	                        <td>
	                            <p class="mb-0 mt-4">${entry.value.product.title}</p>
	                        </td>	
	                        <td>
	                            <p class="mb-0 mt-4">${entry.value.product.price}</p>
	                        </td>
	                        <td>
	                            <div class="input-group quantity mt-4" style="width: 100px;">
	                                <div class="input-group-btn">
	                                    <button type="button" class="btn btn-sm btn-minus rounded-circle bg-light border" >
	                                    	<i class="fa fa-minus"></i>
	                                    </button>
	                                </div>
	                                <input type="text" class="form-control form-control-sm text-center border-0" value="${entry.value.quantity}">
	                                <div class="input-group-btn">
	                                    <button type="button" class="btn btn-sm btn-plus rounded-circle bg-light border">
	                                        <i class="fa fa-plus"></i>
	                                    </button>
	                                </div>
	                            </div>
	                        </td>
	                        <td>
	                            <p class="mb-0 mt-4">${entry.value.totalPrice}</p>
	                        </td>
	                        <td>
	                            <button class="btn btn-md rounded-circle bg-light border mt-4" >
	                                <i class="fa fa-times text-danger"></i>
	                            </button>
	                        </td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="mt-5">
            <input type="text" class="border-0 border-bottom rounded me-5 py-3 mb-4" placeholder="Mã giảm giá">
            <button class="btn border-secondary rounded-pill px-4 py-3 text-primary" type="button">Áp dụng mã giảm giá</button>
        </div>
        <div class="row g-4 justify-content-end">
            <div class="col-8"></div>
            <div class="col-sm-8 col-md-7 col-lg-6 col-xl-4">
                <div class="bg-light rounded">
                    <div class="p-4">
                        <h1 class="display-6 mb-4">Giỏ hàng: <span class="fw-normal">Tổng</span></h1>
                        <div class="d-flex justify-content-between mb-4">
                            <h5 class="mb-0 me-4">Tổng giỏ hàng:</h5>
                            <p class="mb-0">${TOTALBILL}</p>
                        </div>
                        <div class="d-flex justify-content-between">
                            <h5 class="mb-0 me-4">Phí vận chuyển</h5>
                            <div class="">
                                <p class="mb-0">${SHIPPING}</p>
                            </div>
                        </div>
                        <p class="mb-0 text-end">Toàn quốc (VN)</p>
                    </div>
                    <div class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
                        <h5 class="mb-0 ps-4 me-4">Tổng thanh toán</h5>
                        <p class="mb-0 pe-4">${TOTALBILL + SHIPPING}</p>
                    </div>
                    <a href="<c:url value='/trang-chu?action=pages_checkout'/>" class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4">Mua hàng</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Cart Page End -->