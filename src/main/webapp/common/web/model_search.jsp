<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<!-- Modal Search Start -->

<div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-fullscreen">
        <div class="modal-content rounded-0">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Search by keyword</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="<c:url value='/trang-chu'/>" method="GET">
	            <div class="modal-body d-flex align-items-center">
	                <div class="input-group w-75 mx-auto d-flex">
	                	<input type="hidden" name="action" id="action" value="search">
	                    <input type="text" name="keyword" id="keyword" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1">
	                    <button type="submit"><span id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></span></button>
	                </div>	
	            </div>
            </form>
        </div>
    </div>
</div>
<!-- Modal Search End -->