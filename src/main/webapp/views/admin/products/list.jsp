<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/common/taglib.jsp" %>

<c:url var="URLpattern" value="/admin-products"></c:url>

<style>
	.left{
		float: left;
		color: #4e73df;
	}
	
	.right{
		float: right;
		color: #4e73df;
	}
</style>

<!DOCTYPE html>
<div class="container-fluid">
	<!-- DataTales Example -->
	<div class="card shadow mb-4">	        
	    <div class="card-header py-3">
	        <h6 class="right">Products Table</h6>
	        
	        <form action="<c:url value='/admin-products?'/>" method = "GET" class="row align-items-center" id="formSearch">
				<div class="col-auto">
					<input type="text" <c:if test="${keyword != null}"> value="<%= request.getParameter("keyword")%>" </c:if> name="keyword" id="keyword" class="form-control" placeholder="Enter code	"/>
			    	<input type="hidden" id="pageS" name="page" value="1"/>
			        <input type="hidden" id="itemsPerPageS" name="itemsPerPage" value="10"/>
			        <input type="hidden" id="typeS" name="type" value="search"/>
			    </div>	        
				
				<div class="col-auto">
					<button type="submit" class="btn btn-primary" id="btnSearch" name="btnSearch">Search</button>
				</div>
	        </form>
	        
			<h6 class="right">Total records: ${total}</h6>
	    </div>
	    
	    <form action="<c:url value = '/admin-products'/>" id="formSubmit" method="GET">
		    <div class="card-body">
		        <div class="table-responsive">
		            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
		                <thead>
		                    <tr>
		                    	<th><input type="checkbox" id="selectAll" name="selectAll" title="selectAll"/></th>
		                    	<th>Code</th>
		                        <th>Title</th>
		                        <th>Thumbnail</th>
		                        <th>Origin</th>
		                        <th>Price</th>
		                        <th>Category</th>
		                        <th>Quantity</th>
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
									<input type="search" name="search" id="search"/>
								</div>					  
		                	</tr> 
		                	</br> 
		                	</br>     
		                	<c:forEach var="item" items="${model.listResults}">
		                    <tr>
		                    	<td><input type="checkbox" id="select" name="select" title="select"/></td>
		                    	<td>${item.code}</td>
		                        <td>${item.title}</td>
		                        <td>${item.thumbnail}</td>
		                        <td>${item.origin}</td>
		                        <td>${item.price}</td>
		                        <td>${item.category.name}</td>
		                        <td>${item.quantity} kg</td>
		                    </tr>
		                    </c:forEach>
		                </tbody>
		            </table>
		            <nav class = "nav justify-content-end" aria-label="Page navigation" >
				        <ul class="pagination" id="pagination"></ul>
				        <input type="hidden" id="page" name="page" value=""/>
				        <input type="hidden" id="itemsPerPage" name="itemsPerPage" value=""/>
				        <input type="hidden" id="sortName" name="sortName" value=""/>
				        <input type="hidden" id="sortBy" name="sortBy" value=""/>
				        <input type="hidden" id="type" name="type" value="list"/>
				    </nav>
		        </div>
		    </div>
	    </form>
	</div>
</div>

<script type="text/javascript">

$(function() {
    var selectedValue = ${model.itemsPerPage}; // Biến để lưu trữ giá trị được chọn của select box
	var totalItems = ${model.totalItems};
    function reloadData() {
        var currentPageSelected = ${model.page};
        var totalPagesSelected = Math.ceil(totalItems/selectedValue);
        if(totalPagesSelected == 1 || totalPagesSelected < currentPageSelected){
        	currentPageSelected = 1;
        }
        
        // Gửi yêu cầu mới đến máy chủ để tải lại trang với các tham số mới
        $.ajax({
            url: '${URLpattern}',
            type: 'GET',
            data: { page: currentPageSelected, itemsPerPage: selectedValue, sortName: 'title', sortBy: 'asc', type: 'list' }, // Các tham số bạn muốn gửi đến máy chủ
            success: function(data) {
                console.log('Data reloaded successfully');
                // Chọn phần tử bảng và thay đổi nội dung của nó
                
                $('body').html(data);
                
                // Khởi tạo lại thư viện twbsPagination với dữ liệu mới
                initializePagination(totalPagesSelected,currentPageSelected);
            },
            error: function(xhr, status, error) {
                console.error('Error reloading data:', error);
            }
        });
    }

    // Lắng nghe sự kiện khi thay đổi tùy chọn trong select
    $('#mySelect').change(function() {
        selectedValue = $(this).val(); // Lưu trữ giá trị được chọn của select box
        reloadData();
    });
    
    $('input[type="search"]').on('input', function() {
        var keyword = $(this).val().toLowerCase(); // Lấy giá trị nhập vào và chuyển thành chữ thường
        // Lọc các hàng trong bảng dữ liệu dựa trên từ khóa nhập vào
        $('#dataTable tbody tr').each(function() {
            var rowText = $(this).text().toLowerCase(); // Lấy văn bản của hàng và chuyển thành chữ thường
            // Hiển thị hoặc ẩn hàng dựa trên việc có từ khóa trong văn bản hay không
            if (rowText.indexOf(keyword) === -1) {
                $(this).hide(); // Ẩn hàng nếu không có từ khóa
            } else {
                $(this).show(); // Hiển thị hàng nếu có từ khóa
            }
        });
    });
    
    // Hàm khởi tạo lại thư viện twbsPagination
    function initializePagination(totalPages,currentPage) {
    	$('#mySelect option[value="' + selectedValue + '"]').prop('selected', true);
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 5,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (${model.page} != page) {
                    $('#sortName').val('title');
                    $('#sortBy').val('asc');
                    $('#page').val(page);
                    $('#itemsPerPage').val(selectedValue); // Sử dụng giá trị được lưu trữ
                    $('#formSubmit').submit();	
                }    
            }
        })
    }

    // Khởi tạo thư viện twbsPagination khi trang được tải lần đầu tiên
    initializePagination(${model.totalPages},${model.page});

});
    
</script>