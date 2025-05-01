package security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import dao.DaoCarts;
import dao.DaoUser;
import entity.Carts;
import entity.Users;

@Transactional
public class GlobalInterceptorUser extends HandlerInterceptorAdapter{
	@Autowired
	SessionFactory factory;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("goi intorcepter");
		HttpSession ss = request.getSession();
		String username = (String) ss.getAttribute("user");
		request.setAttribute("uname1", username);
		if(username != null) {
			Users user = DaoUser.getUser(factory, username);
			for (Carts cart : user.getCarts()) {
				if(cart.getStatus()) {
					if(!cart.getProduct().getActive()) {
						if(DaoCarts.deleteCart(factory, cart)) {
							System.out.println("xoa thanh cong");
						}else {
							System.out.println("xoa tthat bai");
						}
					}else {
						if(cart.getQuantity() > cart.getProduct().getQuantity()) {
							cart.setQuantity(cart.getProduct().getQuantity());
							cart.setSubTotal(Math.round(cart.getQuantity() * (cart.getProduct().getPrice() * (1 - cart.getProduct().getDiscount()/100) ) ));
						}else {
							cart.setSubTotal(Math.round(cart.getQuantity() * (cart.getProduct().getPrice() * (1 - cart.getProduct().getDiscount()/100) ) ));
						}
						
						if(DaoCarts.updateCart(factory, cart)) {
							System.out.println("update thanh cong");
						}else {
							System.out.println("update tthat bai");
						}
					}
				}
			}
			
			Long n = null;
			try {
				n = DaoCarts.countCart(factory, username);
			} catch (Exception e) {
				n = null;
				System.out.println("loi o dem cart");
			}
			if(n == null) {
				n = (long) 0;
			}
			request.setAttribute("sl", n);
		}
		
		//System.out.println("user: " + ss.getAttribute("user"));
		return true;
	}
}
