package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import bean.Mailer;
import dao.DaoCarts;
import dao.DaoOrder;
import dao.DaoProduct;
import entity.Carts;
import entity.Orders;
import entity.Products;

@Transactional
@Controller
@RequestMapping("orders")
public class OrderController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	Mailer mailer;
	
	@RequestMapping("dashboard")
	public String dashboard(ModelMap model, HttpServletRequest request) {
		String searchKey = "";
		try {
			searchKey = request.getParameter("search");
			searchKey = searchKey.trim();
		} catch (Exception e) {
			searchKey = "";
		}
		
		//load order
		List<Orders> orders = new ArrayList<>();
		if(!searchKey.equals("")) {
			orders = DaoOrder.searchOrders(factory, searchKey);
			if(orders.size() == 0) {
				model.addAttribute("message_search", "Sorry. No result for " + searchKey + " :(");
			}else {
				model.addAttribute("orders", orders);
			}
		}else {
			orders = DaoOrder.readAllOrders(factory);
			model.addAttribute("orders", orders);
		}
	
		model.addAttribute("orders", orders);
		
		
		try {
			String message = request.getParameter("xacnhan");
			if(message.equals("true")) {
				model.addAttribute("success_message", "Đã xác nhận đơn hàng.");
				model.addAttribute("action", "\"show_success\"");
			}else if(message.equals("false")) {
				model.addAttribute("fail_message", "Xác nhận đơn hàng thất bại!");
				model.addAttribute("action", "\"show_error\"");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			String message2 = request.getParameter("huydon");
			if(message2.equals("true")) {
				model.addAttribute("success_message", "Đã huỷ đơn hàng.");
				model.addAttribute("action", "\"show_success\"");
			}else if(message2.equals("false")) {
				model.addAttribute("fail_message", "Huỷ đơn hàng thất bại!");
				model.addAttribute("action", "\"show_error\"");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			String message3 = request.getParameter("vanchuyen");
			if(message3.equals("true")) {
				model.addAttribute("success_message", "Đơn hàng đang vận chuyển.");
				model.addAttribute("action", "\"show_success\"");
			}else if(message3.equals("false")) {
				model.addAttribute("fail_message", "Không thể vận chuyển đơn hàng!");
				model.addAttribute("action", "\"show_error\"");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			String message4 = request.getParameter("dagiao");
			if(message4.equals("true")) {
				model.addAttribute("success_message", "Đơn hàng đã giao.");
				model.addAttribute("action", "\"show_success\"");
			}else if(message4.equals("false")) {
				model.addAttribute("fail_message", "Không thể giao đơn hàng!");
				model.addAttribute("action", "\"show_error\"");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			String message5 = request.getParameter("xoadon");
			if(message5.equals("true")) {
				model.addAttribute("success_message", "Đã xoá đơn hàng.");
				model.addAttribute("action", "\"show_success\"");
			}else if(message5.equals("false")) {
				model.addAttribute("fail_message", "Không thể xoá đơn hàng!");
				model.addAttribute("action", "\"show_error\"");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "order_dashboard";
	}
	
	@RequestMapping("detail/{orderid}")
	public String detail(@PathVariable("orderid") Integer orderid, ModelMap model) {
		Orders order = null;
		try {
			order = DaoOrder.getOrder(factory, orderid);
		} catch (Exception e) {
			order = null;
		}
		if(order == null) {
			return "redirect:/orders/dashboard.htm";
		}
		
		model.addAttribute("order", order);
		
		
		
		model.addAttribute("carts", order.getCarts());
		
		return "order_detail";
	}
	
	@RequestMapping("xacnhan/{orderid}")
	public String xacnhan(@PathVariable("orderid") Integer orderid, ModelMap model) {
		Orders order = null;
		try {
			order = DaoOrder.getOrder2(factory, orderid);
		} catch (Exception e) {
			order = null;
		}
		
		String xacnhan = "false";
		if(order != null) {
			int statusOrder = order.getStatus();
			if(statusOrder == 0 || statusOrder == 10 || statusOrder == 13) {
				if(statusOrder == 0 || statusOrder == 10) {
					String info = "";
					for(Carts cart : order.getCarts()) {
						info += cart.getProduct().getProductName() + " " + cart.getQuantity() + "kg " + cart.getSubTotal() + "đ\n";
					}
					
					//gui email xac nhan don hang
					String from = "neuertrg@gmail.com";
					String to = order.getUser_order().getEmail();
					String subject = "Thông báo xác nhận đơn hàng #" + orderid;
					String body = "SHOPFRUIT\n\n"
								+ "Cám ơn bạn đã mua hàng!\n"
								+ "Xin chào " + order.getFullName() + ", Chúng tôi đã nhận được đặt hàng của bạn và đang chuẩn bị để vận chuyển."
								+ " Đơn hàng sẽ sớm được gửi đi. Theo dõi trạng thái đơn hàng của bạn tại:\n"
								+ "http://trantruong.com:8080/SHOPFRUIT/shop/myorder.htm?status=3 \n"
								+ "Hoặc đến cửa hàng của chúng tôi:\n"
								+ "http://trantruong.com:8080/SHOPFRUIT/shop/home.htm \n\n"
								+ "Thông tin đơn hàng #" + orderid + "\n"
								+ info
								+ "-----------------------------------------------\n"
								+ "Tổng cộng: " + order.getTotal() + "đ\n\n"
								+ "Địa chỉ nhận hàng\n"
								+ order.getFullName() + "\n"
								+ order.getAddress();
					
					mailer.send(from, to, subject, body);
				}
				order.setStatus(3);// dat la cho lay hang
				if(DaoOrder.updateOrder(factory, order)) {
					xacnhan = "true";
				}
			}
		}
		
		return "redirect:/orders/dashboard.htm?xacnhan=" + xacnhan;
	}
	
	@RequestMapping("huydon/{orderid}")
	public String huydon(@PathVariable("orderid") Integer orderid, ModelMap model) {
		Orders order = null;
		try {
			order = DaoOrder.getOrder(factory, orderid);
		} catch (Exception e) {
			order = null;
		}
		
		String huydon = "false";
		if(order != null) {
			int statusOrder = order.getStatus();
			if(statusOrder == 0 || statusOrder == 10 || statusOrder == 13 || statusOrder == 3 || statusOrder == 4) {
				for (Carts cart : order.getCarts()) {
					Double quantity = cart.getQuantity();
					Products product = cart.getProduct();
					quantity = product.getQuantity() + quantity;
					quantity = quantity*100;
					quantity = (double)(quantity.intValue());
					product.setQuantity(quantity/100);
					product.setActive(true);
				}
				order.setStatus(2);
				huydon = "true";
//				if(DaoOrder.updateOrder(factory, order) && DaoProduct.updateProduct(factory, null)) {
//					
//				}
			}
		}
		
		return "redirect:/orders/dashboard.htm?huydon=" + huydon;
	}
	
	@RequestMapping("vanchuyen/{orderid}")
	public String vanchuyendon(@PathVariable("orderid") Integer orderid, ModelMap model) {
		Orders order = null;
		try {
			order = DaoOrder.getOrder2(factory, orderid);
		} catch (Exception e) {
			order = null;
		}
		
		String vanchuyen = "false";
		if(order != null) {
			int statusOrder = order.getStatus();
			if(statusOrder == 3 || statusOrder == 13) {//don hang cho lay hang
				order.setStatus(4);//dat la dang van chuyen
				if(DaoOrder.updateOrder(factory, order)) {
					vanchuyen = "true";
				}
			}
		}
		
		return "redirect:/orders/dashboard.htm?vanchuyen=" + vanchuyen;
	}
	
	@RequestMapping("dagiao/{orderid}")
	public String dagiao(@PathVariable("orderid") Integer orderid, ModelMap model) {
		Orders order = null;
		try {
			order = DaoOrder.getOrder2(factory, orderid);
		} catch (Exception e) {
			order = null;
		}
		
		String dagiao = "false";
		if(order != null) {
			int statusOrder = order.getStatus();
			if(statusOrder == 4) {//don hang dang van chuyen
				order.setStatus(5);
				if(DaoOrder.updateOrder(factory, order)) {
					dagiao = "true";
				}
			}
		}
		
		return "redirect:/orders/dashboard.htm?dagiao=" + dagiao;
	}
	
	@RequestMapping("xoadon/{orderid}")
	public String xoadon(@PathVariable("orderid") Integer orderid, ModelMap model) {
		Orders order = null;
		try {
			order = DaoOrder.getOrder2(factory, orderid);
		} catch (Exception e) {
			order = null;
		}
		
		String xoadon = "false";
		if(order != null) {
			List<Carts> carts = new ArrayList<>();
			carts = (List<Carts>) order.getCarts();
			for (Carts cart : carts) {
				if(DaoCarts.deleteCart(factory, cart)) {
					System.out.println("da xoa 1 cart");
					xoadon = "true";
				}else {
					System.out.println("delete cart fail");
					xoadon = "false";
				}
			}
			
			if(DaoOrder.deleteOrder(factory, order)) {
				System.out.println("da xoa order");
				xoadon = "true";
			}else {
				System.out.println("chua xoa order");
				xoadon = "false";
			}
//			//factory.getCurrentSession().close();
			
		}
		
		return "redirect:/orders/dashboard.htm?xoadon=" + xoadon;
	}
	
}
