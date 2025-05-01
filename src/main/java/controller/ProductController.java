package controller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bean.UploadFile;
import dao.DaoProduct;
import entity.Products;
import security.Validation;

@Transactional
@Controller
@RequestMapping("products")
public class ProductController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	UploadFile baseUploadfile;
	
	@RequestMapping("dashboard")
	public String dashboard(ModelMap model, HttpServletRequest request) {
		String searchKey = "";
		try {
			searchKey = request.getParameter("search");
			searchKey = searchKey.trim();
		} catch (Exception e) {
			searchKey = "";
		}
		
		//load product
		if(!searchKey.equals("")) {
			List<Products> l = new ArrayList<>();
			l = DaoProduct.searchProducts(factory, searchKey);
			if(l.size() == 0) {
				model.addAttribute("message_search", "Sorry. No result for " + searchKey + " :(");
			}else {
				model.addAttribute("products", l);
			}
		}else {
			model.addAttribute("products", DaoProduct.readAllProducts(factory));
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
		return "product_dashboard";
	}
	
	//detail product
	@RequestMapping("detail/{productid}")
	public String detail(@PathVariable("productid") String productid, ModelMap model) {
		model.addAttribute("product", DaoProduct.readOneProduct(factory, productid));
		return "product_detail";
	}
	
	//insert product
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("product", new Products());
		return "product_form";
	}
	
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(ModelMap model, @ModelAttribute("product") Products product, BindingResult errors,
			@RequestParam("image") MultipartFile image) {
		Validation.validateProduct(product, errors);
		if(image.isEmpty()) {
			errors.rejectValue("ProductImage", "product", "Vui lòng chọn file!");
		}
		if(!errors.hasErrors()) {
			boolean successUpload = false;
			String fileName = "";
			try {
				//upload image to server
				String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss-"));
				fileName = date + image.getName();
				String photoPath = baseUploadfile.getBasePath() + File.separator + fileName;
				image.transferTo(new File(photoPath));
				Thread.sleep(2500);
				successUpload = true;
			} catch (Exception e) {
				successUpload = false;
			}
			if(successUpload) {
				product.setProductImage(fileName);
				product.setCreatedDate(new Date());
				if(DaoProduct.insertProduct(factory, product)) {
					model.addAttribute("success_message", "Thêm mới thành công.");
					model.addAttribute("action", "\"show_success\"");
				}else {
					model.addAttribute("fail_message", "Thêm mới thất bại");
					model.addAttribute("action", "\"show_error\"");
				}
			}else {
				model.addAttribute("fail_message", "Uploadfile thất bại");
				model.addAttribute("action", "\"show_error\"");
			}
				
		}
		return "product_form";
	}
	
	//edit product
	@RequestMapping(value = "update/{productid}")
	public String update(ModelMap model, @PathVariable("productid") String productid) {
		
		model.addAttribute("product", DaoProduct.readOneProduct(factory, productid));
		model.addAttribute("isUpDate", "name=\"btnUpdate\"");
		model.addAttribute("value", "value=\""+productid+"\"");
		return "product_form";
	}
	
	@RequestMapping(value = "insert", method = RequestMethod.POST, params = "btnUpdate")
	public String update(ModelMap model, @ModelAttribute("product") Products product, BindingResult errors,
			@RequestParam("image") MultipartFile image, HttpServletRequest request) {
		Products productb = DaoProduct.readOneProduct(factory, String.valueOf(request.getParameter("btnUpdate")));
		
		// validation data
		Validation.validateProduct(product, errors);
		
		if(!errors.hasErrors()) {
			String fileName = "";
			boolean successUpload = false;
			if(image.isEmpty()) {
				fileName = productb.getProductImage();
				successUpload = true;
			}else {
				try {
					//upload image to server
					String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss-"));
					fileName = date + image.getName();
					String photoPath = baseUploadfile.getBasePath() + File.separator + fileName;
					image.transferTo(new File(photoPath));
					Thread.sleep(2500);
					successUpload = true;
				} catch (Exception e) {
					successUpload = false;
				}
			}

			if(successUpload) {
				product.setProductId(Integer.valueOf(request.getParameter("btnUpdate")));
				product.setProductImage(fileName);
				product.setCreatedDate(productb.getCreatedDate());
				if(DaoProduct.updateProduct(factory, product)) {
					model.addAttribute("success_message", "Chỉnh sửa thành công.");
					model.addAttribute("action", "\"show_success\"");
				}else {
					model.addAttribute("fail_message", "Chỉnh sửa thất bại");
					model.addAttribute("action", "\"show_error\"");
				}
			}else {
				model.addAttribute("fail_message", "Uploadfile thất bại");
				model.addAttribute("action", "\"show_error\"");
			}
		}
		model.addAttribute("isUpDate", "name=\"btnUpdate\"");
		model.addAttribute("value", "value=\""+String.valueOf(product.getProductId())+"\"");
		
		return "product_form";
	}
	
	//delete product
	@RequestMapping("delete/{productid}")
	public String delete(@PathVariable("productid") String productid) {
		String message = "fdelete";
		if(DaoProduct.deleteProduct(factory, productid)) {
			message = "sdelete";
		}
		return "redirect:/products/dashboard.htm?message="+message;
	}
}
