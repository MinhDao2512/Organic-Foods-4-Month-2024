<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-product" />
<c:url var="URLpattern" value="/admin-products"/>
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
			<label class="col-sm-2 col-form-label">Category</label>
			<div class="col-sm-10">
				<c:if test="${empty model.categoryCode}">
					<select class="form-control" id="categoryCode" name="categoryCode">
						<option value="">Select category</option>
						<c:forEach var="item" items="${categories}">
							<option value="${item.code}">${item.name}</option>
						</c:forEach>
					</select>
				</c:if>
				<c:if test="${not empty model.categoryCode}">
					<select class="form-control" id="categoryCode" name="categoryCode">
						<option value="">Chọn thể loại sản phẩm</option>
						<c:forEach var="item" items="${categories}">
							<option value="${item.code}"
								<c:if test="${item.code == model.categoryCode}"> selected="selected"</c:if>>
								${item.name}</option>
						</c:forEach>
					</select>
				</c:if>
			</div>
		</div>
		<div class="form-group row">
			<label for="code" class="col-sm-2 col-form-label">Product
				Code</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="code" name="code"
					placeholder="Product Code" value="${model.code}">
			</div>
		</div>
		<div class="form-group row">
			<label for="title" class="col-sm-2 col-form-label">Title</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="title" name="title"
					placeholder="Title" value="${model.title}">
			</div>
		</div>
		<div class="form-group row">
			<label for="thumbnail" class="col-sm-2 col-form-label">Thumb
				nail(image)</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="thumbnail"
					name="thumbnail" placeholder="Link image"
					value="${model.thumbnail}">
			</div>
		</div>
		<div class="form-group row">
			<label for="shortdescription" class="col-sm-2 col-form-label">Short
				description</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="shortdescription"
					name="shortdescription" placeholder="Short description"
					value="${model.shortdescription}">
			</div>
		</div>
		<div class="form-group row">
			<label for="content" class="col-sm-2 col-form-label">Content</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="content" name="content"
					placeholder="Content" value="${model.content}">
			</div>
		</div>
		<div class="form-group row">
			<label for="price" class="col-sm-2 col-form-label">Price / Kg</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="price" name="price"
					placeholder="Price" value="${model.price}">
			</div>
		</div>
		<div class="form-group row">
			<label for="origin" class="col-sm-2 col-form-label">Origin</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="origin" name="origin"
					placeholder="Origin" value="${model.origin}">
			</div>
		</div>
		<div class="form-group row">
			<label for="quantity" class="col-sm-2 col-form-label">Quantity</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="quantity"
					name="quantity" placeholder="Quantity" value="${model.quantity}">
			</div>
		</div>
		<div class="form-group row">
			<label for="quantity" class="col-sm-2 col-form-label"></label>
			<div class="col-sm-10">
				<c:if test="${not empty model.id}">
					<input type="button" class="btn btn-primary" value="Update Product" id="btnAddOrUpdateProduct" />
				</c:if>
				<c:if test="${empty model.id}">
					<input type="button" class="btn btn-primary" value="Add Product" id="btnAddOrUpdateProduct" />
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
		var id = $('#id').val();
		if (id == "") {
			addProduct(data);
		} else {
			updateProduct(data);
		}
	});

	function updateProduct(data) {
		$.ajax({
			url : '${APIurl}',
			type : 'PUT',
			contentType : 'application/json',
			data : JSON.stringify(data),
			dataType : 'json',
			success : function(result) {
				console.log(result);
				if(result.id !== null){
					window.location.href = "${URLpattern}?type=edit&id=${model.id}&alert=success&message=success_update_product";
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

	function addProduct(data) {
		$.ajax({
			url : '${APIurl}',
			type : 'POST',
			contentType : 'application/json',
			data : JSON.stringify(data),
			dataType : 'json',
			success : function(result) {
				console.log(result);
				if(result.id !== null){
					window.location.href = "${URLpattern}?type=edit&alert=success&message=success_add_product";
				}
				else {
					window.location.href = "${URLpattern}?type=edit&alert=danger&message=message_danger";
				}
			},
			error : function(error) {
				console.log(error);
			}
		});
	}
</script>