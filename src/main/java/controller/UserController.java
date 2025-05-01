package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.DaoUser;
import entity.Users;
import security.Validation;

@Transactional
@Controller
@RequestMapping("users")
public class UserController {
	@Autowired
	SessionFactory factory;
	
	
	@RequestMapping("dashboard")
	public String dashboard(ModelMap model, HttpServletRequest request) {
		String searchKey = "";
		try {
			searchKey = request.getParameter("search");
			searchKey = searchKey.trim();
		} catch (Exception e) {
			searchKey = "";
		}
		
		//load user
		if(!searchKey.equals("")) {
			List<Users> l = new ArrayList<>();
			l = DaoUser.searchUsers(factory, searchKey);
			if(l.size() == 0) {
				model.addAttribute("message_search", "Sorry. No result for " + searchKey + " :(");
			}else {
				model.addAttribute("users", l);
			}
		}else {
			model.addAttribute("users", DaoUser.readAllUsers(factory));
		}
			
		
		try {
			String message = request.getParameter("message");
			if(message.equals("sdelete")) {
				model.addAttribute("success_message", "Xoá thành công.");
				model.addAttribute("action", "\"show_success\"");
			}else if(message.equals("fdelete")) {
				model.addAttribute("fail_message", "Xoá thất bại");
				model.addAttribute("action", "\"show_error\"");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "users_dashboard";
	}
	
	@ModelAttribute("status_user")
	public Map<String, String> getStatusUser(){
		HashMap<String, String> map_status = new HashMap<String, String>();
		map_status.put("1", "active");
		map_status.put("0", "inactive");
		return map_status;
	}
	
	@ModelAttribute("roles_user")
	public Map<String, String> getRolesUser(){
		HashMap<String, String> map_roles = new HashMap<String, String>();
		map_roles.put("1", "admin");
		map_roles.put("0", "user");
		return map_roles;
	}
	
	
	//thêm mới user
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("user", new Users());
		return "users_form";
	}
	
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute("user") Users user, BindingResult errors, ModelMap model) {
		// validation data
		Validation.ValidateUser(user, errors);
		
		//insert user
		if(!errors.hasErrors()) {
			if(DaoUser.insertUser(user, factory)) {
				model.addAttribute("success_message", "Thêm mới thành công.");
				model.addAttribute("action", "\"show_success\"");
			}else {
				model.addAttribute("fail_message", "Thêm mới thất bại");
				model.addAttribute("action", "\"show_error\"");
			}
		}
		
		return "users_form";
	}
	
	
	//edit user
	@RequestMapping(value = "update/{userid}")
	public String update(ModelMap model, @PathVariable("userid") String userid) {
		
		model.addAttribute("user", DaoUser.readOneUsers(factory, userid));
		model.addAttribute("isUpDate", "name=\"btnUpdate\"");
		model.addAttribute("value", "value=\""+userid+"\"");
		return "users_form";
	}
	
	@RequestMapping(value = "insert", method = RequestMethod.POST, params = "btnUpdate")
	public String update(ModelMap model, @ModelAttribute("user") Users user, BindingResult errors, HttpServletRequest request) {
		// validation data
		Validation.ValidateUser(user, errors);
		
		//update user
		if(!errors.hasErrors()) {
			user.setUserId(Integer.valueOf(request.getParameter("btnUpdate")));
			if(DaoUser.updateUser(user, factory)) {
				model.addAttribute("success_message", "Chỉnh sửa thành công.");
				model.addAttribute("action", "\"show_success\"");
			}else {
				model.addAttribute("fail_message", "Chỉnh sửa thất bại");
				model.addAttribute("action", "\"show_error\"");
			}
		}
		model.addAttribute("isUpDate", "name=\"btnUpdate\"");
		model.addAttribute("value", "value=\""+String.valueOf(user.getUserId())+"\"");
		return "users_form";
	}
	
	//delete user
	@RequestMapping("delete/{userid}")
	public String delete(@PathVariable("userid") String userid) {
		String message = "fdelete";
		if(DaoUser.deleteUser(factory, userid)) {
			message = "sdelete";
		}
		return "redirect:/users/dashboard.htm?message="+message;
	}
}
