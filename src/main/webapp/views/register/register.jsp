<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIUser" value="/api-web-user"></c:url>
<!DOCTYPE html>

<div class="container">
	<div class="signup-content">
		<div class="signup-form">
			<c:if test="${not empty alert}">
				<div class="alert alert-${alert}" role="alert">
					<span>${message}</span>
				</div>
			</c:if>
			<h2 class="form-title">Sign up</h2>
			
			<form id="formRegister" action="<c:url value = '/dang-ky?action=register'/>" method="POST">
				<div class="form-group">
					<label for="fullName"><i
						class="zmdi zmdi-account material-icons-name"></i></label> 
						<input type="text" name="fullName" id="fullName" placeholder="Full Name" />
				</div>
				<div class="form-group">
					<label for="userName"><i
						class="zmdi zmdi-account material-icons-name"></i></label> <input
						type="text" name="userName" id="userName" placeholder="Username" />
				</div>
				<div class="form-group">
					<label for="email"><i class="zmdi zmdi-email"></i></label> <input
						type="email" name="email" id="email" placeholder="Your Email" />
				</div>
				<div class="form-group">
					<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
						type="password" name="passWord" id="passWord" placeholder="Password" />
				</div>
				<div class="form-group">
					<label for="phone"><i class="zmdi zmdi-phone-in-talk"></i></label>
					<input type="text" name="phone" id="phone"
						placeholder="Phone" />
				</div>
				<div class="form-group">
					<label for="address"><i class="zmdi zmdi-pin"></i></label>
					<input type="text" name="address" id="address"
						placeholder="Address" />
				</div>
				<div class="form-group form-button">
					<input type = "submit" class = "form-submit" value = "Register" id = "btnRegister"/>
				</div>
			</form>
		</div>
		<div class="signup-image">
			<figure>
				<img src="<c:url value = '/template/register/images/signup-image.jpg'/>" alt="sing up image">
			</figure>
			<a href="<c:url value='/dang-nhap?action=login'/>" class="signup-image-link">I am already member</a>
		</div>
	</div>
</div>
<!--
<script>
	$('#btnRegister').click(function (e){
		e.preventDefault();
		var data = {};
		var formData = $('#formRegister').serializeArray();
		$.each(formData, function(i, v){
			data[""+v.name+""] = v.value;
		});
		addUser(data);
	});

	function addUser(data){
		$.ajax({
			url: '${APIUser}',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(data),
			dataType: 'json',
			success: function(result){
				console.log(result);
				if(result.status == 1){
					window.location.href = "<c:url value='/dang-nhap?action=login&alert=success&message=message_success_register'/>";
				}
				else{
					window.location.href = "<c:url value='/dang-ky?action=register&alert=danger&message=message_exist'/>";
				}
			},
			error: function(error){
				console.log(error);
			}
		});
	}	
</script>
  -->