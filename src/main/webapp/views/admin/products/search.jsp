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
	        
	        <form action="<c:url value='/admin-products'/>" method = "GET" class="row align-items-center" id="formSearch">
				<div class="col-auto">
					<c:if test=""></c:if>
					<input type="text" value="${model.keyword}" name="keyword" id="keyword" class="form-control" placeholder="Enter Code"/>
			    	<input type="hidden" id="pageS" name="page" value="1"/>
			    	<input type="hidden" id="itemsPerPageS" name="itemsPerPage" value="10"/>
				    <input type="hidden" id="typeS" name="type" value="search"/>
			    </div>	        
				
				<div class="col-auto">
					<button type="submit" class="btn btn-primary">Search</button>
				</div>
	        </form>
	        
	        <h6 class="right">Total records: ${total}</h6>
	    </div>
	    
	    <div class="card-body">
	        <div class="table-responsive">
	            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	                <thead>
	                    <tr>
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
                                    <option value="10">10</option>
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
			    </nav>
	        </div>
	    </div>
	</div>
</div>

<script type="text/javascript">

$(function() {
    
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
    
    function initializePagination(totalPages,currentPage) {
        var limit = 10;
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 5,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (${model.page} != page) {
                    $('#pageS').val(page);
                    $('#itemsPerPageS').val(limit);
                    $('#keyword').val(${keyword});
                    $('#formSearch').submit();
                }    
            }
        })
    }
    
    initializePagination(${model.totalPages},${model.page});

});
    
</script>