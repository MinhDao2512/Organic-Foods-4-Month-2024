package com.organicfoods.model;

public class BillDetailsModel extends AbstractModel<BillDetailsModel>{
	private Long productId; 
	private Integer quantity; 
	private Double totalPrice;
	private ProductModel product;
	
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
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void incrementQuantity() {
		this.quantity++;
	}
	public ProductModel getProduct() {
		return product;
	}
	public void setProduct(ProductModel product) {
		this.product = product;
	}
	
	public void updateTotalPrice(Double price) {
		this.totalPrice = price*this.quantity;
	}
}
