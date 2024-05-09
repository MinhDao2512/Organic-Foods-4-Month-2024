<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/common/taglib.jsp" %>

<c:url var="URLpattern" value="/admin-products"/>
<c:url var="APIurl" value='/api-admin-product'/>

<style>
	.left{
		float: left;
		color: #81c408;
	}
	
	.right{
		float: right;
		color: #81c408;
	}
	.radius_e{
		border-radius: 0.35rem;	
	}
</style>

<!DOCTYPE html>
<div class="container-fluid">
	<!-- DataTales Example -->
	<div class="card shadow mb-4">	        
	    <div class="card-header py-3">
	        <h6 class="right">Products table</h6>
	        
	        <form action="<c:url value='/admin-products?'/>" method = "GET" class="row align-items-center" id="formSearch">
				<div class="col-auto">
					<input type="text" value="${model.keyword}" name="keyword" id="keyword" class="form-control" placeholder="Enter code"/>
			        <input type="hidden" id="page" name="page" value=""/>
			        <input type="hidden" id="itemsPerPage" name="itemsPerPage" value=""/>
			        <input type="hidden" id="sortName" name="sortName" value=""/>
			        <input type="hidden" id="sortBy" name="sortBy" value=""/>
			        <input type="hidden" id="type" name="type" value=""/>
			    </div>	        
				
				<div class="col-auto">
					<button type="submit" class="btn btn-primary" id="btnSearch">Search</button>
				</div>
	        </form>
	        
			<h6 class="right">Total products: ${model.totalItems}</h6>

	    </div>
	    
	    <div class="card-body">
	        <div class="table-responsive">
	            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	                <thead>
	                    <tr>
	                    	<th><input type="checkbox" id="selectAll" name="selectAll" title="selectAll"/></th>
	                    	<th>Code</th>
	                        <th>Title</th>
	                        <th>Origin</th>
	                        <th>Price</th>
	                        <th>Category code</th>
	                        <th>Quantity</th>
	                        <th>Shop</th>
	                        <th>Edit</th>
	                    </tr>
	                </thead>
	                <tbody>
	                	<tr>
	                		<div class="left">
		                		Show
								<select class="form-select" id="mySelect" name="mySelect">
                                    <option value="5">5</option>
                                    <option value="10">10</option>
                                    <option value="25">25</option>
                                </select>
								products
							</div>	
							<div class="right">
								Search:
								<input class="radius_e" type="search" name="search" id="search"/>
								<button id="btnDelete" type="button" class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip" title='Xóa sản phẩm'>
									<span><i class="fas fa-trash-alt"></i></span>
								</button>
								
								<c:url var="editURL" value="/admin-products">
	                        		<c:param name="type" value="edit"/>
	                        	</c:url>
								<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
							   		title="Thêm sản phẩm" href="${editURL}"><i class="fa fa-plus" aria-hidden="true"></i>
								</a>
							</div>	  
	                	</tr> 
	                	</br> 
	                	</br>     
	                	<c:forEach var="item" items="${model.listResults}">
	                    <tr>
	                    	<td><input type="checkbox" id="checkbox_${item.id}" title="select" value="${item.id}"/></td>
	                    	<td>${item.code}</td>
	                        <td>${item.title}</td>
	                        <td>${item.origin}</td>
	                        <td>${item.price}</td>
	                        <td>${item.categoryCode}</td>
	                        <td>${item.quantity} kg</td>
	                        <td>${item.createdBy}</td>
	                        <td>
	                        	<c:url var="editURL" value="/admin-products">
	                        		<c:param name="type" value="edit"/>
	                        		<c:param name="id" value="${item.id}"/>
	                        	</c:url>
	                        	<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
							   		title="Cập nhật sản phẩm" href='${editURL}'><i class="fas fa-edit"></i>
								</a>
	                        </td>
	                    </tr>
	                    </c:forEach>
	                </tbody>
	            </table>
	            <nav class = "nav justify-content-end" aria-label="Page navigation" >
			        <ul class="pagination" id="pagination"></ul>
			    </nav>
	        </div>
	    </div>
	</div>
</div>

<script type="text/javascript">

$(function() {
    var selectedValue = ${model.itemsPerPage}; // Biến để lưu trữ giá trị được chọn của select box
	var totalItems = ${model.totalItems};
	var type = '${model.type}';
	var keyword = '${model.keyword}';
	
    function reloadData() {
        var currentPageSelected = ${model.page};
        var totalPagesSelected = Math.ceil(totalItems/selectedValue);
        if(totalPagesSelected == 1 || totalPagesSelected < currentPageSelected){
        	currentPageSelected = 1;
        }
        
        $.ajax({
            url: '${URLpattern}',
            type: 'GET',
            data: { page: currentPageSelected, itemsPerPage: selectedValue, sortName: 'title', sortBy: 'asc', type: type, keyword: keyword},
            success: function(data) {
                console.log('Data reloaded successfully');             
                $('body').html(data);
                initializePagination(totalPagesSelected,currentPageSelected);
            },
            error: function(xhr, status, error) {
                console.error('Error reloading data:', error);
            }
        });
    }

    $('#mySelect').change(function() {
        selectedValue = $(this).val();
        reloadData();
    });
    
    $('input[type="search"]').on('input', function() {
        var keyword = $(this).val().toLowerCase();
        $('#dataTable tbody tr').each(function() {
            var rowText = $(this).text().toLowerCase();
            if (rowText.indexOf(keyword) === -1) {
                $(this).hide();
            } else {
                $(this).show();
            }
        });
    });
    
    // Hàm khởi tạo lại thư viện twbsPagination
    function initializePagination(totalPages,currentPage) {
    	$('#mySelect option[value="' + selectedValue + '"]').prop('selected', true);
    	var keyword = '${model.keyword}';
    	var keywordSearch = sessionStorage.getItem('keywordSearch');
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 5,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (${model.page} != page) {
                    $('#sortName').val('title');
                    $('#sortBy').val('asc');
                    $('#page').val(page);
                    if(keyword !== ''){
                        $('#keyword').val(keyword);
                    }
                    $('#type').val(type);
                    $('#itemsPerPage').val(selectedValue);
                    $('#formSearch').submit();	
                }    
            }
        })
    }

    $('#btnSearch').click(function(){
        var keywordValue = $('#keyword').val();
        if (keywordValue.trim() !== '') { // Kiểm tra xem giá trị của keyword có giá trị không
            $('#type').val('search');
            $('#sortName').val('title');
            $('#sortBy').val('asc');
            $('#page').val(1);
           	$('#itemsPerPage').val(10);
        } else {
            $('#type').val('list');
        }
    });

	$('#btnDelete').click(function(){
		var data = {};
		var ids = $('tbody input[type=checkbox]:checked').map(function(){
			return $(this).val();
		}).get();
		data['ids'] = ids;
		deleteProducts(data);
	});

	function deleteProducts(data){
		$.ajax({
			url: '${APIurl}',
			type: 'DELETE',
			contentType: 'application/json',
			data: JSON.stringify(data),
			dataType: 'json',
			success: function(result){
				window.location.href = "${URLpattern}?type=list_products&page=1&itemsPerPage=5&sortName=title&sortBy=desc";
			},
			error: function(error){
				console.log(error);
			}
		});
    }
    // Khởi tạo thư viện twbsPagination khi trang được tải lần đầu tiên
    initializePagination(${model.totalPages},${model.page});

});
    
</script>