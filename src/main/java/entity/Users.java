package entity;

import java.util.Collection;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class Users {
	@Id
	@GeneratedValue
	private Integer UserId;
	private String Username;
	private String Password;
	private String FullName;
	private String Email;
	private String Phone;
	private String Address;
	private String Birthday;
	private Boolean Role;
	private Boolean Status;
	
	@OneToMany(mappedBy = "user_order", fetch = FetchType.EAGER)
	private Collection<Orders> orders;
	
	@OneToMany(mappedBy = "user_cart", fetch = FetchType.EAGER)
	private Collection<Carts> carts;

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getBirthday() {
		return Birthday;
	}

	public void setBirthday(String birthday) {
		Birthday = birthday;
	}

	public Boolean getRole() {
		return Role;
	}

	public void setRole(Boolean role) {
		Role = role;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

	public Collection<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Orders> orders) {
		this.orders = orders;
	}

	public Collection<Carts> getCarts() {
		return carts;
	}

	public void setCarts(Collection<Carts> carts) {
		this.carts = carts;
	}

	

	
}
