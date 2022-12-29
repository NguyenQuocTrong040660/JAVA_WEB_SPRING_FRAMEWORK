package com.training.fa.DTO.request;

public class Cart {
	
	private Integer id;
	
    private String name;
	
	private Integer price;
	
	private Integer quantity;
	
	private Integer totalPrice;
	
	private String content;
	
	private String imagePath;
	

	
	public Cart() {
		super();
	}


	public Cart(Integer id, String name, Integer price, Integer quantity, Integer totalPrice, String content,
			String imagePath) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.content = content;
		this.imagePath = imagePath;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}






	@Override
	public String toString() {
		return "Cart [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", totalPrice="
				+ totalPrice + ", content=" + content + ", imagePath=" + imagePath + "]";
	}
	

	
	
	
	
	

}
