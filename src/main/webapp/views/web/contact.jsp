<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/common/taglib.jsp" %>
<!DOCTYPE html>
<!-- Single Page Header start -->
<div class="container-fluid page-header py-5">
    <h1 class="text-center text-white display-6">Liên hệ</h1>
    <ol class="breadcrumb justify-content-center mb-0">
        <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>	
        <li class="breadcrumb-item active text-white">Liên hệ</li>
    </ol>
</div>
<!-- Single Page Header End -->

<!-- Contact Start -->
<div class="container-fluid contact py-5">
    <div class="container py-5">
        <div class="p-5 bg-light rounded">
            <div class="row g-4">
                <div class="col-12">
                    <div class="text-center mx-auto" style="max-width: 700px;">
                        <h1 class="text-primary">Google Map</h1>
                        <p class="mb-4"></p>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="h-100 rounded">
                    
                        <iframe class="rounded w-100" 
                        style="height: 400px;" loading="lazy" referrerpolicy="no-referrer-when-downgrade" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.521610727813!2d106.78436437480603!3d10.847874889305224!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3175272a49301c55%3A0xde097a8add7a6926!2zOTcgxJAuIE1hbiBUaGnhu4duLCBIaeG7h3AgUGjDuiwgUXXhuq1uIDksIFRow6BuaCBwaOG7kSBI4buTIENow60gTWluaCwgVmnhu4d0IE5hbQ!5e0!3m2!1svi!2s!4v1714415473836!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                    </div>
                </div>
                <div class="col-lg-7">
                    <form action="<c:url value='/trang-chu?action=contact'/>" method="POST">
                        <input type="text" id="fullName" name="fullName" class="w-100 form-control border-0 py-3 mb-4" placeholder="Tên">
                        <input type="email" id="email" name="email" class="w-100 form-control border-0 py-3 mb-4" placeholder="Email">
                        <textarea id="message" name="message" class="w-100 form-control border-0 mb-4" rows="5" cols="10" placeholder="Phản hồi/Câu hỏi"></textarea>
                        <button type="submit" class="w-100 btn form-control border-secondary py-3 bg-white text-primary">Gửi</button>
                    </form>
                </div>
                <div class="col-lg-5">
                    <div class="d-flex p-4 rounded mb-4 bg-white">
                        <i class="fas fa-map-marker-alt fa-2x text-primary me-4"></i>
                        <div>
                            <h4>Địa chỉ</h4>
                            <p class="mb-2">97 Man Thien, TP Thu Duc, TP HCM</p>
                        </div>
                    </div>
                    <div class="d-flex p-4 rounded mb-4 bg-white">
                        <i class="fas fa-envelope fa-2x text-primary me-4"></i>
                        <div>
                            <h4>Email</h4>
                            <p class="mb-2">vusonghuong02@gmail.com</p>
                        </div>
                    </div>
                    <div class="d-flex p-4 rounded bg-white">
                        <i class="fa fa-phone-alt fa-2x text-primary me-4"></i>
                        <div>
                            <h4>Số điện thoại</h4>
                            <p class="mb-2">(+84) 392 711 168</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Contact End -->