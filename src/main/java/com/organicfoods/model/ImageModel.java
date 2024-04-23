package com.organicfoods.model;

public class ImageModel extends AbstractModel<ImageModel>{
	private String title;
	private String url;
	private Long productId;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	} 
	
}
