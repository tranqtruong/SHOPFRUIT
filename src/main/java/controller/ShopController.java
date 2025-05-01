package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.DaoCarts;
import dao.DaoOrder;
import dao.DaoProduct;
import dao.DaoUser;
import entity.Carts;
import entity.Orders;
import entity.Products;
import entity.Users;
import security.Validation;

@Transactional
@Controller
@RequestMapping("shop")
public class ShopController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("home")
	public String goHome(HttpServletRequest request, ModelMap model) {
		List<Products> products = new ArrayList<>();
		
		String searchKey = "";
		try {
			searchKey = request.getParameter("search");
			searchKey = searchKey.trim();
		} catch (Exception e) {
			searchKey = "";
		}
		
		if(!searchKey.equals("")) {
			products = DaoProduct.searchProductsActive(factory, searchKey);
			if(products.size() == 0) {
				model.addAttribute("search_mess", "No result for \"" + searchKey +"\"");
			}
		}else {
			products = DaoProduct.readAllProductsActive(factory);
		}
		
		PagedListHolder pagedListHolder = new PagedListHolder(products);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(12);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "shop";
	}
	
	@RequestMapping("single/{productid}")
	public String singleProduct(ModelMap model, @PathVariable("productid") String productid) {
		Products product = DaoProduct.readOneProduct(factory, productid);
		if(product == null) {
			return "redirect:/shop/home.htm";
		}
		model.addAttribute("product", product);
		return "shop_single";
	}
	
	
	@RequestMapping(value = "addtocart/{productid}", method = RequestMethod.GET)
	public String addOneToCart(@PathVariable("productid") String productid, HttpServletRequest request) {
		//b1: lay username tu session
		HttpSession ss = request.getSession();
		String username = (String) ss.getAttribute("user");
		
		//b2: lay user object tu username (2 cach: truy van hql hoac dung method session.get())
		Users user = DaoUser.getUser(factory, username);
		
		//b3: lay product object tu productid
		Products product = DaoProduct.readOneProduct(factory, productid);
		if(product != null && product.getQuantity() >= 1) {//san pham con hang thi them 1 kg vao gio hang
			//b4: kiem tra san pham da ton tai trong gio hang
			Carts cart = DaoCarts.getCart(factory, Integer.valueOf(productid), Integer.valueOf(user.getUserId()));
			if(cart == null) {
				//b5: tao doi tuong cart, set cac thuoc tinh can thiet
				cart = new Carts();
				cart.setUser_cart(user);
				cart.setProduct(product);
				cart.setQuantity(1.0);
				cart.setSubTotal( Math.round(cart.getQuantity() * (product.getPrice() * (1 - product.getDiscount() / 100) ) ) );
				cart.setStatus(true);
				
				//b5: insert cart vao db
				DaoCarts.insertCart(factory, cart);
			}else {
				//update quantity
				Double quantit = cart.getQuantity()+1;
				if(quantit > product.getQuantity()) {
					quantit = product.getQuantity();
				}
				cart.setQuantity(quantit);
				cart.setSubTotal( Math.round(cart.getQuantity() * (product.getPrice() * (1 - product.getDiscount() / 100) ) ) );
				DaoCarts.updateCart(factory, cart);
			}
		}
		
			
		return "redirect:/shop/home.htm";
	}
	
	@RequestMapping(value = "addtocart", method = RequestMethod.POST)
	public String addToCart(HttpServletRequest request, @RequestParam("pid") String productid, @RequestParam("quantt") Double productquantity) {
		//b1: lay username tu session
		HttpSession ss = request.getSession();
		String username = (String) ss.getAttribute("user");
		
		//b2: lay user object tu username (2 cach: truy van hql hoac dung method session.get())
		Users user = DaoUser.getUser(factory, username);
				
		//b3: lay product object tu productid
		Products product = DaoProduct.readOneProduct(factory, productid);
		if(product != null) {
			if(productquantity > product.getQuantity()) {
				productquantity = product.getQuantity();
			}
			//b4: kiem tra cart da ton tai
			Carts cart = DaoCarts.getCart(factory, Integer.valueOf(productid), Integer.valueOf(user.getUserId()));
			if(cart == null) {
				//b5: tao doi tuong cart, set cac thuoc tinh can thiet
				cart = new Carts();
				cart.setUser_cart(user);
				cart.setProduct(product);
				cart.setQuantity(productquantity);
				cart.setSubTotal( Math.round(cart.getQuantity() * (product.getPrice() * (1 - product.getDiscount() / 100) ) ) );
				cart.setStatus(true);
				
				//b5: insert cart vao db
				DaoCarts.insertCart(factory, cart);
			}else {
				//update quantity
				productquantity += cart.getQuantity();
				if(productquantity > product.getQuantity()) {
					productquantity = product.getQuantity();
				}
				cart.setQuantity(productquantity);
				cart.setSubTotal( Math.round(cart.getQuantity() * (product.getPrice() * (1 - product.getDiscount() / 100) ) ) );
				DaoCarts.updateCart(factory, cart);
			}
		}
		
		return "redirect:/shop/single/" + productid + ".htm";
	}
	
	@RequestMapping("mycart")
	public String mycart(ModelMap model, HttpServletRequest request) {
		//b1: lay username tu session
		HttpSession ss = request.getSession();
		String username = (String) ss.getAttribute("user");
		
		//b2: lay user object tu username
		Users user = DaoUser.getUser(factory, username);
		List<Carts> carts = new ArrayList<>();
		for (Carts cart : user.getCarts()) {
			if(cart.getStatus()) {
				carts.add(cart);
			}
		}
		
		model.addAttribute("carts", carts);
		return "shop_cart";
	}
	
	@RequestMapping("removecart/{cartid}")
	public String removeCart(@PathVariable("cartid") Integer cartid, HttpServletRequest request) {
		HttpSession ss = request.getSession();
		String username = (String) ss.getAttribute("user");
		
		Carts cart = null; 
		try {
        	cart = DaoCarts.getCart(factory, cartid);
		} catch (Exception e) {
			cart = null;
		}
		
		if(cart != null && cart.getUser_cart().getUsername().equals(username)) {
			DaoCarts.deleteCart(factory, cart);
		}
		return "redirect:/shop/mycart.htm";
	}
	
	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public String checkout(HttpServletRequest request) {
		//b1: cap nhat lai so luong sp trong cart cua user dua tren cart id
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
            String cartId = enumeration.nextElement();
            String quantity = request.getParameter(cartId);
            Carts cart = null;
            try {
            	cart = DaoCarts.getCart(factory, Integer.valueOf(cartId));
			} catch (Exception e) {
				cart = null;
			}
            
            if(cart != null) {
            	if(Double.valueOf(quantity) > cart.getProduct().getQuantity()) {
            		cart.setQuantity(cart.getProduct().getQuantity());
            		cart.setSubTotal(Math.round(cart.getQuantity() * (cart.getProduct().getPrice() * (1 - cart.getProduct().getDiscount()/100))));
            	}else {
            		cart.setQuantity(Double.valueOf(quantity));
            		cart.setSubTotal(Math.round(cart.getQuantity() * (cart.getProduct().getPrice() * (1 - cart.getProduct().getDiscount()/100))));
            	}
            	DaoCarts.updateCart(factory, cart);
            }
        }
		
		return "redirect:/shop/checkout.htm";
	}
	
	
	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public String testcheckout(ModelMap model, HttpServletRequest request) {
		//b2: tinh tong tien trong cart
		HttpSession ss = request.getSession();
		String username = (String) ss.getAttribute("user");
		Users user = DaoUser.getUser(factory, username);
		Long total = (long) 0;
		for (Carts c : user.getCarts()) {
			if(c.getStatus()) {
				total += c.getSubTotal();
			}
		}
		
		model.addAttribute("total", total);
		model.addAttribute("order", new Orders());
		return "shop_checkout";
	}
	
	@RequestMapping(value = "order", method = RequestMethod.POST)
	public String order(@ModelAttribute("order") Orders order, HttpServletRequest request, ModelMap model, BindingResult errors) {
		HttpSession ss = request.getSession();
		String username = (String) ss.getAttribute("user");
		
		Users user = DaoUser.getUser(factory, username);
		Long total = (long) 0;
		for (Carts c : user.getCarts()) {
			if(c.getStatus()) {
				total += c.getSubTotal();
			}
		}
		model.addAttribute("total", total);
		
		if(total <= 0 || user.getCarts().size() <= 0) {
			return "redirect:/shop/myorder.htm";
		}
		
		//b1: kiem tra input order
		Validation.validateOrder(errors, order, user);
		if(!errors.hasErrors()) {
			order.setUser_order(user);
			order.setTotal(total);
			order.setStatus(0);
			order.setCreatedDate(new Date());
			
			//b2: insert order
			if(DaoOrder.insertOrder(factory, order)) {
				Integer oid = DaoOrder.getOrderId(factory, user.getUserId());
				Orders order1 = DaoOrder.getOrder(factory, oid);
				for (Carts c : user.getCarts()) {
					if(c.getStatus()) {
						c.setStatus(false);
						c.setOrder(order1);
//						if(DaoCarts.updateCart(factory, c)) {
//							
//						}else {
//							System.out.println("qua update cart not ok");
//						}
						
						Double quantity = c.getQuantity();
						Products product = c.getProduct();
						//Products product = DaoProduct.readOneProduct(factory, String.valueOf(proid));
						quantity = product.getQuantity() - quantity;
						quantity = quantity*100;
						quantity = (double)(quantity.intValue());
						product.setQuantity(quantity/100);
						if(product.getQuantity() <= 0) {
							product.setQuantity(0.0);
							product.setActive(false);
						}
						
//						if(DaoProduct.updateProduct(factory, product)) {
//							
//						}else {
//							System.out.println("qua update product not ok");
//						}
					}
				}
			}
			return "redirect:/shop/myorder.htm";
		}
		
		return "shop_checkout";
	}
	
	@RequestMapping("myorder")
	public String myOrder(ModelMap model, HttpServletRequest request) {
		HttpSession ss = request.getSession();
		String username = (String) ss.getAttribute("user");
		Users user = DaoUser.getUser(factory, username);
		
		String option = null;
		
		try {
			option = request.getParameter("status");
		} catch (Exception e) {
			option = null;
		}
		
		List<Orders> orders = new ArrayList<>();
		if(option == null || option.equals("0")) {// cho xac nhan
			orders = DaoOrder.layDonChoXacNhan(factory, user.getUserId());
			model.addAttribute("select0", "selected=\"selected\"");
		}else if(option.equals("3")) {// cho lay hang
			orders = DaoOrder.layDonChoLayHang(factory, user.getUserId());
			model.addAttribute("select3", "selected=\"selected\"");
		}else if(option.equals("4")) {// dang giao
			orders = DaoOrder.layDonDangGiao(factory, user.getUserId());
			model.addAttribute("select4", "selected=\"selected\"");
		}else if(option.equals("5")) {// da giao
			orders = DaoOrder.layDonDaGiao(factory, user.getUserId());
			model.addAttribute("select5", "selected=\"selected\"");
		}else if(option.equals("2")) {// da huy
			orders = DaoOrder.layDonDaHuy(factory, user.getUserId());
			model.addAttribute("select2", "selected=\"selected\"");
		}else {
			orders = null;
		}

		model.addAttribute("orders", orders);
		
		return "shop_my_order";
	}
	
	@RequestMapping("orderdetail/{orderid}")
	public String detail(@PathVariable("orderid") Integer orderid, ModelMap model, HttpServletRequest request) {
		
		Orders order = null;
		try {
			order = DaoOrder.getOrder2(factory, orderid);
		} catch (Exception e) {
			order = null;
		}
		if(order == null) {
			return "redirect:/shop/myorder.htm";
		}
		
		model.addAttribute("order", order);
		
		HttpSession ss = request.getSession();
		String username = (String) ss.getAttribute("user");
		Users user = DaoUser.getUser(factory, username);
		
		List<Carts> carts = new ArrayList<>();
		for (Carts cart : user.getCarts()) {
			if(cart.getStatus() == false && cart.getOrder().getId() == orderid) {
				carts.add(cart);
			}
		}
		model.addAttribute("carts", carts);
		
		return "shop_order_detail";
	}
	
	@RequestMapping("cancelorder/{orderid}")
	public String cancel(@PathVariable("orderid") Integer orderid, HttpServletRequest request) {
		HttpSession ss = request.getSession();
		String username = (String) ss.getAttribute("user");
		
		Orders order = null;
		try {
			order = DaoOrder.getOrder2(factory, orderid);
		} catch (Exception e) {
			order = null;
		}
		
		if(order != null && order.getUser_order().getUsername().equals(username)) {
			int statusOrder = order.getStatus();
			if(statusOrder == 0) {
				order.setStatus(10);
				if(DaoOrder.updateOrder(factory, order)) {
					System.out.println("da yeu cau huy");
					return "redirect:/shop/myorder.htm?status=0";
				}else {
					System.out.println("chua yeu cau huy");
				}
			}else if(statusOrder == 3) {
				order.setStatus(13);
				if(DaoOrder.updateOrder(factory, order)) {
					System.out.println("da yeu cau huy 2");
					return "redirect:/shop/myorder.htm?status=3";
				}else {
					System.out.println("chua yeu cau huy 2");
				}
			}
		}
		
		return "redirect:/shop/myorder.htm";
	}
	
	@RequestMapping("continueorder/{orderid}")
	public String continueorder(@PathVariable("orderid") Integer orderid, HttpServletRequest request) {
		HttpSession ss = request.getSession();
		String username = (String) ss.getAttribute("user");
		
		Orders order = null;
		try {
			order = DaoOrder.getOrder2(factory, orderid);
		} catch (Exception e) {
			order = null;
		}
		
		if(order != null && order.getUser_order().getUsername().equals(username)) {
			int statusOrder = order.getStatus();
			if(statusOrder == 10) {
				order.setStatus(0);
				if(DaoOrder.updateOrder(factory, order)) {
					System.out.println("da tiep tuc");
					return "redirect:/shop/myorder.htm?status=0";
				}else {
					System.out.println("chua tiep tuc");
				}
			}else if(statusOrder == 13) {
				order.setStatus(3);
				if(DaoOrder.updateOrder(factory, order)) {
					System.out.println("da tiep tuc 2");
					return "redirect:/shop/myorder.htm?status=3";
				}else {
					System.out.println("chua tiep tuc 2");
				}
			}
		}
		
		return "redirect:/shop/myorder.htm";
	}
	
	@RequestMapping(value = "myprofile")
	public String myprofile(ModelMap model, HttpServletRequest request) {
		HttpSession ss = request.getSession();
		String username = (String) ss.getAttribute("user");
		Users user = DaoUser.getUser(factory, username);
		
		model.addAttribute("user", user);
		return "shop_my_profile";
	}
	
	@RequestMapping(value = "myprofile", method = RequestMethod.POST)
	public String updateprofile(@ModelAttribute("user") Users user, ModelMap model, HttpServletRequest request, BindingResult errors) {
		
		HttpSession ss = request.getSession();
		String username = (String) ss.getAttribute("user");
		Users user2 = DaoUser.getUser(factory, username);
		
		Validation.ValidateUser3(factory, user, errors, user2);
		
		
		if(!errors.hasErrors()) {
			
			try {
				user2.setFullName(user.getFullName());
				user2.setPhone(user.getPhone());
				user2.setEmail(user.getEmail());
				user2.setBirthday(user.getBirthday());
				user2.setAddress(user.getAddress());
				model.addAttribute("message", true);
			} catch (Exception e) {
				model.addAttribute("message", false);
			}
			
		}
		
		return "shop_my_profile";
	}
}
