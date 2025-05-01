package controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bean.Mailer;
import dao.DaoUser;
import entity.Users;
import security.RecaptchaVeritication;
import security.Validation;

@Transactional
@Controller
public class HomeController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String index() {
		return "redirect:http://trantruong.com:8080/SHOPFRUIT/shop/home.htm";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		HttpSession ss = request.getSession();
		if(ss.getAttribute("admin") != null) {
			return "redirect:products/dashboard.htm";
		}else if(ss.getAttribute("user") != null) {
			return "redirect:shop/home.htm";
		}
		return "login_page";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, ModelMap model, HttpSession ss, 
			@RequestParam("username") String username, @RequestParam("password") String password) {
		boolean hasError = false;
		String gRecaptchaRespone;
		boolean verity = false;
		
		try {
			gRecaptchaRespone = request.getParameter("g-recaptcha-response");
			verity = RecaptchaVeritication.verify(gRecaptchaRespone);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(!verity) {
			hasError = true;
			model.addAttribute("recap_mess", "Vui lòng click vào ô xác thực!");
		}
		if(username.trim().length() == 0) {
			hasError = true;
			model.addAttribute("username_mess", "Vui lòng nhập username!");
		}
		if(password.length() == 0) {
			hasError = true;
			model.addAttribute("pass_mess", "Vui lòng nhập password!");
		}
		
		if(!hasError) {
			//kiem tra thong tin dang nhap
			Users user = DaoUser.verifyUser(factory, username, password);
			if(user == null) {
				model.addAttribute("login_mess", "Incorrect!");
			}
			else if(!user.getStatus()) {
				model.addAttribute("login_mess", "account has been locked!");
			}else if(user.getRole()) {
				//ss.setAttribute("user", username);
				ss.setAttribute("admin", user.getUsername());
				return "redirect:products/dashboard.htm";
			}else{
				ss.setAttribute("user", user.getUsername());
				return "redirect:index.htm";
			}

		}
		
		return "login_page";
	}
	
	@RequestMapping("adminlogout")
	public String adminLogout(HttpSession ss) {
		ss.removeAttribute("admin");
		return "redirect:login.htm";
	}
	
	@RequestMapping("logout")
	public String userLogout(HttpSession ss) {
		ss.removeAttribute("user");
		return "redirect:login.htm";
	}
	
	@RequestMapping("forgotpass")
	public String forgot() {
		return "forgot_password_page";
	}
	
	@Autowired
	Mailer mailer;
	
	HashMap<String, String> user_otp = new HashMap<>();
	
	public void createOTP(String username) {
		//create otp
		if(user_otp.containsKey(username)) {
			user_otp.remove(username);
			System.out.println("da xoa ma cu");
		}
		
		boolean stop = false;
		do {
			String otp = new DecimalFormat("000000").format(new Random().nextInt(999999));
			if(user_otp.values().contains(otp)) {
				stop = false;
			}else {
				user_otp.put(username, otp);
				stop = true;
			}
		}while(!stop);
		
		
		//set time for otp
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String otp = user_otp.get(username);
					Thread.sleep(300000);
					if(user_otp.get(username).equals(otp)) {
						user_otp.remove(username);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t1.start();
	}
	
	@RequestMapping(value = "resetpass", method = RequestMethod.POST)
	public String resetpass(@RequestParam("email") String email, ModelMap model, HttpSession ss) {
		if(email.trim().length() == 0) {
			model.addAttribute("val_email", "Vui lòng nhập 1 email");
			return "forgot_password_page";
		}
		
		//kiem tra user
		Users user = DaoUser.checkEmailExists(factory, email);
		if(user == null) {
			model.addAttribute("check_mess", "Account not found!");
			return "forgot_password_page";
		}
		
		ss.setAttribute("userotp", user);
		
		createOTP(user.getUsername());
		
		//send OTP to email user
		String from = "neuertrg@gmail.com";
		String to = email;
		String subject = "SHOPFRUIT OTP";
		String body = "Mã xác thực của bạn là: \n"
				+ user_otp.get(user.getUsername()) 
				+ "\n\nNếu bạn không sử dụng mã sau 5 phút, mã sẽ hết hạn.";
		mailer.send(from, to, subject, body);	
		
		return "redirect:checkotp.htm";
	}
	
	@RequestMapping("checkotp")
	public String checkotp(HttpServletRequest request) {
		HttpSession ss = request.getSession();
		Users user = (Users) ss.getAttribute("userotp");
		
		if(user == null) {
			return "redirect:forgotpass.htm";
		}
		
		try {
			String resendOTP = request.getParameter("resendOTP");
			if(resendOTP.equals("true")) {
				createOTP(user.getUsername());
				
				//send OTP to email user
				String from = "neuertrg@gmail.com";
				String to = user.getEmail();
				String subject = "SHOPFRUIT OTP";
				String body = "Mã xác thực của bạn là: \n"
							+ user_otp.get(user.getUsername()) 
							+ "\n\nNếu bạn không sử dụng mã sau 5 phút, mã sẽ hết hạn.";
				mailer.send(from, to, subject, body);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "otp_page";
	}
	
	@RequestMapping(value = "checkotp", method = RequestMethod.POST)
	public String checkotp2(ModelMap model, @RequestParam("otp") String otp, HttpSession sess, HttpServletRequest request) {
		HttpSession ss = request.getSession();
		Users user = (Users) ss.getAttribute("userotp");
		
		if(user == null) {
			return "redirect:forgotpass.htm";
		}
		
		if(otp.trim().length() == 0) {
			model.addAttribute("otp_mess", "Vui lòng nhập otp!");
			return "otp_page";
		}
		
		String otpServer = user_otp.get(user.getUsername());
		
		if(otpServer == null) {
			model.addAttribute("otp_mess2", "OTP expired!");
			return "otp_page";
		}
		
		if(!user_otp.get(user.getUsername()).equals(otp)) {
			model.addAttribute("otp_mess2", "OTP incorrect!");
			return "otp_page";
		}
		
		sess.removeAttribute("userotp");
		sess.setAttribute("user_tmp", user);
		return "redirect:changepass.htm";
	}
	
	@RequestMapping(value = "changepass", method = RequestMethod.GET)
	public String changepass(HttpServletRequest request, ModelMap model) {
		HttpSession ss = request.getSession();
		String username = null;
		if(ss.getAttribute("user") != null) {
			username = (String)ss.getAttribute("user");
		}else if(ss.getAttribute("admin") != null) {
			username = (String) ss.getAttribute("admin");
		}else if(ss.getAttribute("user_tmp") != null) {
			Users user = (Users) ss.getAttribute("user_tmp");
			username = user.getUsername();
		}
		
		if(username == null) {
			return "redirect:login.htm";
		}
		
		model.addAttribute("username", username);
		
		return "change_password_page";
	}
	
	@RequestMapping(value = "changepass", method = RequestMethod.POST)
	public String changepass(HttpServletRequest request, ModelMap model, HttpSession ses,
			@RequestParam("newpass") String newpass, @RequestParam("confirmpass") String confirmpass) {
		HttpSession ss = request.getSession();
		String username = null;
		if(ss.getAttribute("user") != null) {
			username = (String)ss.getAttribute("user");
		}else if(ss.getAttribute("admin") != null) {
			username = (String) ss.getAttribute("admin");
		}else if(ss.getAttribute("user_tmp") != null) {
			Users user = (Users) ss.getAttribute("user_tmp");
			username = user.getUsername();
		}
		
		if(username == null) {
			return "redirect:login.htm";
		}
		
		
		boolean hasError = false;
		System.out.println("new pass: " + newpass);
		System.out.println("confirm pass: " + confirmpass);
		if(newpass.length() == 0) {
			model.addAttribute("new_mess", "Không được để trống!");
			hasError = true;
		}else if(!newpass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$")) {
			model.addAttribute("new_mess", "ít nhất 6 ký tự, 1 chữ hoa, 1 chữ thường, 1 số, 1 ký tự đặc biệt");
			hasError = true;
		}
		
		if(confirmpass.length() == 0) {
			model.addAttribute("confirm_mess", "Không được để trống!");
			hasError = true;
		}else if(!confirmpass.equals(newpass)) {
			model.addAttribute("message", "Mật khẩu không khớp!");
			hasError = true;
		}
		
		if(!hasError) {
			if(newpass.equals(confirmpass)) {
				Users user = DaoUser.getUser(factory, username);
				user.setPassword(newpass);
				System.out.println("change pass sucess");
				ses.removeAttribute("user");
				ses.removeAttribute("admin");
				ses.removeAttribute("user_tmp");
				return "redirect:login.htm";
			}
		}
		
		model.addAttribute("username", username);
		return "change_password_page";
	}
	
	@RequestMapping(value = "newaccount", method = RequestMethod.GET)
	public String newAccount(ModelMap model) {
		model.addAttribute("user", new Users());
		return "create_account";
	}
	
	@RequestMapping(value = "newaccount", method = RequestMethod.POST)
	public String newAccount(ModelMap model, @ModelAttribute("user") Users user, BindingResult errors, HttpSession ss) {
		Validation.ValidateUser2(factory, user, errors);
		
		if(!errors.hasErrors()) {
			user.setRole(false);
			user.setStatus(true);
			
			if(DaoUser.insertUser(user, factory)) {
				ss.setAttribute("user", user.getUsername());
				return "redirect:login.htm";
			}else {
				model.addAttribute("message", "cannot create account. error server");
			}
		}
		return "create_account";
	}
}	
