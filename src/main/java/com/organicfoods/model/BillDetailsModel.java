package com.organicfoods.model;

public class BillDetailsModel extends AbstractModel<BillDetailsModel>{
	private Long productId; 
	private Integer quantity; 
	private Double totalPrice;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double price) {
		this.totalPrice = price*this.quantity;
	}
	
	public void incrementQuantity() {
		this.quantity++;
	}
}
