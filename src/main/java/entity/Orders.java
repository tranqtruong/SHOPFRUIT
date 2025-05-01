package entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Orders {
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "UserId")
	private Users user_order;
	
	private String Address;
	private String Note;
	private Long Total;
	private Integer Status;
	private Date CreatedDate;
	private String Phone;
	private String FullName;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	private Collection<Carts> carts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUser_order() {
		return user_order;
	}

	public void setUser_order(Users user_order) {
		this.user_order = user_order;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public Long getTotal() {
		return Total;
	}

	public void setTotal(Long total) {
		Total = total;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public Date getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public Collection<Carts> getCarts() {
		return carts;
	}

	public void setCarts(Collection<Carts> carts) {
		this.carts = carts;
	}

	
	
	
}
