package security;

import org.hibernate.SessionFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import dao.DaoUser;
import entity.Orders;
import entity.Products;
import entity.Users;

public class Validation {
	
	public static void ValidateUser3(SessionFactory factory, Users user, BindingResult errors, Users user2) {
		
		if(user.getFullName().trim().equals("")) {
			errors.rejectValue("FullName", "user", "Vui lòng nhập FullName!");
		}
		
		if(user.getEmail().trim().equals("")) {
			errors.rejectValue("Email", "user", "Vui lòng nhập Email!");
		}else if(!user.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
			errors.rejectValue("Email", "user", "Vui lòng nhập email hợp lệ!");
		}else {
			Users user3 = DaoUser.checkEmailExists2(factory, user.getEmail(), user2.getUsername());
			if(user3 != null) {
				errors.rejectValue("Email", "user", "Email đã được sử dụng!");
			}
		}
		
		if(user.getPhone().trim().equals("")) {
			errors.rejectValue("Phone", "user", "Vui lòng nhập Phone!");
		}else if(!user.getPhone().matches("\\d{10,11}")) {
			errors.rejectValue("Phone", "user", "Phone number Không hợp lệ!");
		}else {
			Users user4 = DaoUser.checkPhoneExists(factory, user.getPhone(), user2.getUsername());
			if(user4 != null) {
				errors.rejectValue("Phone", "user", "Số điện thoại đã được sử dụng!");
			}
		}
		
		if(user.getAddress().trim().equals("")) {
			errors.rejectValue("Address", "user", "Vui lòng nhập Address!");
		}
		
		if(user.getBirthday().equals("")) {
			errors.rejectValue("Birthday", "user", "Vui lòng nhập ngày sinh!");
		}
		
	}
	
	public static void ValidateUser2(SessionFactory factory, Users user, BindingResult errors) {
		System.out.println("here");
		if(user.getUsername().trim().equals("")) {
			errors.rejectValue("Username", "user", "Vui lòng nhập Username!");
			System.out.println("here2");
		}else if(user.getUsername().matches("[~!@#$%^&*()_+{}\\\\[\\\\]:;,.<>/?-]")) {
			errors.rejectValue("Username", "user", "Username không được chứa ký tự đặc biệt!");
		}else {
			Users user2 = DaoUser.getUser(factory, user.getUsername());
			if(user2 != null) {
				errors.rejectValue("Username", "user", "Username đã được sử dụng!");
			}
		}
		
		if(user.getPassword().length() == 0) {
			errors.rejectValue("Password", "user", "Vui lòng nhập Password!");
		}else if(!user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$")) {
			errors.rejectValue("Password", "user", "Password ít nhất 6 ký tự, 1 chữ hoa, 1 chữ thường, 1 số, 1 ký tự đặc biệt");
			System.out.println("here3");
		}
		
		if(user.getFullName().trim().equals("")) {
			errors.rejectValue("FullName", "user", "Vui lòng nhập FullName!");
		}
		
		if(user.getEmail().trim().equals("")) {
			errors.rejectValue("Email", "user", "Vui lòng nhập Email!");
		}else if(!user.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
			errors.rejectValue("Email", "user", "Vui lòng nhập email hợp lệ!");
		}else {
			Users user3 = DaoUser.checkEmailExists(factory, user.getEmail());
			if(user3 != null) {
				errors.rejectValue("Email", "user", "Email đã được sử dụng!");
			}
		}
		
		if(user.getPhone().trim().equals("")) {
			errors.rejectValue("Phone", "user", "Vui lòng nhập Phone!");
		}else if(!user.getPhone().matches("\\d{10,11}")) {
			errors.rejectValue("Phone", "user", "Phone number Không hợp lệ!");
		}else {
			Users user4 = DaoUser.getUserbyPhone(factory, user.getPhone());
			if(user4 != null) {
				errors.rejectValue("Phone", "user", "Số điện thoại đã được sử dụng!");
			}
		}
		
		if(user.getAddress().trim().equals("")) {
			errors.rejectValue("Address", "user", "Vui lòng nhập Address!");
		}
		
		if(user.getBirthday().equals("")) {
			errors.rejectValue("Birthday", "user", "Vui lòng nhập ngày sinh!");
		}
		
	}
	
	public static void ValidateUser(Users user, BindingResult errors) {
		System.out.println("here");
		if(user.getUsername().trim().equals("")) {
			errors.rejectValue("Username", "user", "Vui lòng nhập Username!");
			System.out.println("here2");
		}else if(user.getUsername().matches("[~!@#$%^&*()_+{}\\\\[\\\\]:;,.<>/?-]")) {
			errors.rejectValue("Username", "user", "Username không được chứa ký tự đặc biệt!");
		}
		
		if(user.getPassword().length() == 0) {
			errors.rejectValue("Password", "user", "Vui lòng nhập Password!");
		}else if(!user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$")) {
			errors.rejectValue("Password", "user", "Password ít nhất 6 ký tự, 1 chữ hoa, 1 chữ thường, 1 số, 1 ký tự đặc biệt");
			System.out.println("here3");
		}
		
		if(user.getFullName().trim().equals("")) {
			errors.rejectValue("FullName", "user", "Vui lòng nhập FullName!");
		}
		
		if(user.getEmail().trim().equals("")) {
			errors.rejectValue("Email", "user", "Vui lòng nhập Email!");
		}else if(!user.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
			errors.rejectValue("Email", "user", "Vui lòng nhập email hợp lệ!");
		}
		
		if(user.getPhone().trim().equals("")) {
			errors.rejectValue("Phone", "user", "Vui lòng nhập Phone!");
		}else if(!user.getPhone().matches("\\d{10,11}")) {
			errors.rejectValue("Phone", "user", "Phone number Không hợp lệ!");
		}
		
		if(user.getAddress().trim().equals("")) {
			errors.rejectValue("Address", "user", "Vui lòng nhập Address!");
		}
		
		if(user.getBirthday().equals("")) {
			errors.rejectValue("Birthday", "user", "Vui lòng nhập ngày sinh!");
		}
		
		if(user.getRole() == null) {
			errors.rejectValue("Role", "user", "Hãy chọn một Role!");
		}
		
		if(user.getStatus() == null) {
			errors.rejectValue("Status", "user", "Hãy chọn một trạng thái!");
		}
	}
	
	public static void validateProduct(Products product, BindingResult errors) {
		if(product.getProductCode().trim().equals("")) {
			errors.rejectValue("ProductCode", "product", "Vui lòng nhập ProductCode!");
		}else if(product.getProductCode().trim().length() > 10) {
			errors.rejectValue("ProductCode", "product", "Tối đa 10 ký tự");
		}
		
		if(product.getProductName().trim().length() == 0) {
			errors.rejectValue("ProductName", "product", "Vui lòng nhập ProductName!");
		}else if(product.getProductName().trim().length() > 100) {
			errors.rejectValue("ProductName", "product", "Tối đa 100 ký tự");
		}
		
		if(product.getProductDetail().trim().length() == 0) {
			errors.rejectValue("ProductDetail", "product", "Vui lòng nhập Product Description!");
		}
		
		if(product.getDiscount() == null) {
			product.setDiscount(0.0);
		}else if(product.getDiscount() < 0 || product.getDiscount() > 100) {
			errors.rejectValue("Discount", "product", "vui lòng nhập Discount từ 0-100");
		}
		
		if(product.getPrice() == null) {
			errors.rejectValue("Price", "product", "Vui lòng nhập Price!");
		}else if(product.getPrice() < 0) {
			errors.rejectValue("Price", "product", "Price không hợp lệ!");
		}
		
		if(product.getQuantity() == null) {
			errors.rejectValue("Quantity", "product", "Vui lòng nhập Quantity!");
		}else if(product.getQuantity() < 0) {
			errors.rejectValue("Quantity", "product", "Quantity không hợp lệ!");
		}
		
		if(product.getActive() == null) {
			errors.rejectValue("Active", "product", "Hãy chọn một trạng thái!");
		}
	}
	
	
	public static void validateOrder(BindingResult errors, Orders order, Users user) {
		if(order.getPhone().trim().equals("")) {
			order.setPhone(user.getPhone());
		}else if(!order.getPhone().matches("\\d{10,11}")) {
			errors.rejectValue("Phone", "order", "Phone number Không hợp lệ!");
			System.out.println("co do day");
		}
		
		if(order.getFullName().trim().length() == 0) {
			order.setFullName(user.getFullName());
		}else if(order.getFullName().length() > 50) {
			errors.rejectValue("FullName", "order", "FullName tối đa 50 ký tự");
		}
		
		if(order.getAddress().trim().length() == 0) {
			order.setAddress(user.getAddress());
		}else if(order.getAddress().length() > 250) {
			errors.rejectValue("Address", "order", "Address tối đa 250 ký tự");
		}
		
		if(order.getNote().length() > 500) {
			errors.rejectValue("Note", "order", "Note tối đa 500 ký tự");
		}
	}
}
