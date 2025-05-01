package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Carts")
public class Carts{
	@Id
	@GeneratedValue
	private Integer CartId;
	
	@ManyToOne
	@JoinColumn(name = "UserId")
	private Users user_cart;
	
	@ManyToOne
	@JoinColumn(name = "ProductId")
	private Products product;
	
	private Double Quantity;
	private Long SubTotal;
	
	@ManyToOne
	@JoinColumn(name = "OrderId")
	private Orders order;
	
	private Boolean Status;

	public Integer getCartId() {
		return CartId;
	}

	public void setCartId(Integer cartId) {
		CartId = cartId;
	}

	public Users getUser_cart() {
		return user_cart;
	}

	public void setUser_cart(Users user_cart) {
		this.user_cart = user_cart;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public Double getQuantity() {
		return Quantity;
	}

	public void setQuantity(Double quantity) {
		Quantity = quantity;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

	public Long getSubTotal() {
		return SubTotal;
	}
	
	public void setSubTotal(Long subTotal) {
		SubTotal = subTotal;
	}
}
