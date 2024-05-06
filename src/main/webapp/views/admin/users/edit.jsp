<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-user" />
<c:url var="URLpattern" value="/admin-user"/>
<!DOCTYPE html>
<div>
	<form id="formEdit" style="margin: 20px;">	
		<div class="form-group row">
			<label class="col-sm-2 col-form-label"></label>
			<div class="col-sm-10"> 
			<div class="alert alert-${alert}" role="alert">
				<span>${message}</span>
			</div>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Role</label>
			<div class="col-sm-10">
				<c:if test="${not empty model.roleCode}">
					<select class="form-control" id="roleCode" name="roleCode">
						<option value="">Select role</option>
						<c:forEach var="item" items="${roles}">
							<option value="${item.code}"
								<c:if test="${item.code == model.roleCode}"> selected="selected"</c:if>>
								${item.code}</option>
						</c:forEach>
					</select>
				</c:if>
			</div>
		</div>
		<div class="form-group row">
			<label for="code" class="col-sm-2 col-form-label">Full name</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="fullName" name="fullName"
					placeholder="Full name" value="${model.fullName}">
			</div>
		</div>
		<div class="form-group row">
			<label for="title" class="col-sm-2 col-form-label">User name</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="userName" name="userName"
					placeholder="Username" value="${model.userName}">
			</div>
		</div>
		<div class="form-group row">
			<label for="thumbnail" class="col-sm-2 col-form-label">Email</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="email"
					name="email" placeholder="email"
					value="${model.email}">
			</div>
		</div>
		<div class="form-group row">
			<label for="shortdescription" class="col-sm-2 col-form-label">Phone</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="phone"
					name="phone" placeholder="Phone"
					value="${model.phone}">
			</div>
		</div>
		<div class="form-group row">
			<label for="content" class="col-sm-2 col-form-label">Address</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="address" name="address"
					placeholder="address" value="${model.address}">
			</div>
		</div>
		<div class="form-group row">
			<label for="quantity" class="col-sm-2 col-form-label"></label>
			<div class="col-sm-10">
				<c:if test="${not empty model.id}">
					<input type="button" class="btn btn-primary" value="Update User" id="btnAddOrUpdateProduct" />
				</c:if>
			</div>
		</div>
		<input type="hidden" id="id" name="id" value="${model.id}">
	</form>
</div>
<script>
	$('#btnAddOrUpdateProduct').click(function(e) {
		e.preventDefault();
		var data = {};
		var formData = $('#formEdit').serializeArray();
		$.each(formData, function(i, v) {
			data["" + v.name + ""] = v.value;
		});
		updateUser(data);
	});

	function updateUser(data) {
		$.ajax({
			url : '${APIurl}',
			type : 'PUT',
			contentType : 'application/json',
			data : JSON.stringify(data),
			dataType : 'json',
			success : function(result) {
				console.log(result);
				if(result.id !== null){
					window.location.href = "${URLpattern}?type=edit&id=${model.id}&alert=success&message=message_success";
				}
				else {
					window.location.href = "${URLpattern}?type=edit&id=${model.id}&alert=danger&message=message_danger";
				}
			},
			error : function(error) {
				console.log(error);
			}
		});
	}
</script>