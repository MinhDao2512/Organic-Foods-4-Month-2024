<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<section class="ftco-section">
	<div class="container">
		<c:if test="${not empty alert}">
			<div class="row justify-content-center">
					<div class="alert alert-${alert}" role="alert">
						<span>${message}</span>
					</div>
			</div>
		</c:if>
		<div class="row justify-content-center">
			<div class="col-md-6 text-center mb-5">
				<h2 class="heading-section">Đăng nhập</h2>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-md-6 col-lg-4">
				<div class="login-wrap p-0">
					<form action="<c:url value = '/dang-nhap?action=login'/>" class="signin-form" method="POST">
						<div class="form-group">
							<input type="text" id="userName" name="userName" class="form-control" placeholder="Tài khoản" required>
						</div>
						<div class="form-group">
							<input id="passWord" name="passWord" type="password" class="form-control" placeholder="Mật khẩu" required> <span
								toggle="#password-field"
								class="fa fa-fw fa-eye field-icon toggle-password"></span>
						</div>
						<div class="form-group">
							<button type="submit"
								class="form-control btn btn-primary submit px-3">Đăng nhập</button>
						</div>
						<div class="form-group d-md-flex">
							<div class="w-50">
								Thành viên mới?<a href="<c:url value='/dang-ky?action=register'/>"> Đăng ký</a>
							</div>
							<div class="w-50 text-md-right">
								<a href="#" style="color: #fff">Quên mật khẩu?</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>
