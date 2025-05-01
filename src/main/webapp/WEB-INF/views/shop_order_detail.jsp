<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Order Detail</title>
    <base href="${pageContext.servletContext.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="./resources/shop/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="./resources/shop/css/animate.css">
    
    <link rel="stylesheet" href="./resources/shop/css/owl.carousel.min.css">
    <link rel="stylesheet" href="./resources/shop/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="./resources/shop/css/magnific-popup.css">

    <link rel="stylesheet" href="./resources/shop/css/aos.css">

    <link rel="stylesheet" href="./resources/shop/css/ionicons.min.css">

    <link rel="stylesheet" href="./resources/shop/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="./resources/shop/css/jquery.timepicker.css">

    
    <link rel="stylesheet" href="./resources/shop/css/flaticon.css">
    <link rel="stylesheet" href="./resources/shop/css/icomoon.css">
    <link rel="stylesheet" href="./resources/shop/css/style.css">
  </head>
  <body class="goto-here">
		<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="shop/home.htm">SHOPFRUIT</a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>
	      
	      

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <jsp:include page="shop_global_user.jsp"/>
	      </div>
	    </div>
	  </nav>
    <!-- END nav -->

    <div class="hero-wrap hero-bread" style="background-image: url('./resources/shop/images/bg_1.jpg');">
      <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
          <div class="col-md-9 ftco-animate text-center">
          	<p class="breadcrumbs"><span class="mr-2"><a href="shop/home.htm">Home</a></span> <span>My Order Detail</span></p>
            <h1 class="mb-0 bread">Welcome!</h1>
          </div>
        </div>
      </div>
    </div>

    <section class="ftco-section ftco-cart">
			<div class="container">
				<div class="row">
    			<div class="col-md-12 ftco-animate">
    				<div class="cart-list">
    					<form action="./shop/checkout.htm" method="post">
    						<table class="table">
							    <thead class="thead-primary">
							      <tr class="text-center">
							        <th>STT</th>
							        <th>&nbsp;</th>
							        <th>Product name</th>
							        <th>Quantity(Kg)</th>
							        <th>Total</th>
							      </tr>
							    </thead>
							    <tbody>
							    	<c:forEach var="c" varStatus="status" items="${carts}">
										<tr class="text-center">
									        <td class="product-remove">${status.index+1}</span></a></td>
									        
									        <td class="image-prod"><div class="img" style="background-image:url(./images/${c.product.productImage});"></div></td>
									        
									        <td class="product-name">
									        	<h3>${c.product.productName}</h3>
									        	<p>
									        		<c:set var="string" value="${c.product.productDetail}"/>  
									        		${fn:substring(string, 0, 80)}...
									        	</p>
									        </td>
									        
							
									        
									        <td class="quantity soluong">
									        	<div class="input-group mb-3">
								             		<input disabled="disabled" type="number" step="any" class="quantity form-control input-number" value="${c.quantity}" >
								          		</div>
								          	</td>
									        
									        <td class="total tong"> <fmt:formatNumber value="${c.subTotal}" type="currency" currencySymbol="đ" maxFractionDigits="0"/> </td>
									     </tr><!-- END TR-->
									</c:forEach>
								      
	
				
							    </tbody>
							  </table>
							  
    					</form>
		    				
					  </div>
    			</div>
    		</div>
    		<div class="row justify-content-end">
    			
    			
    		</div>
			</div>
		</section>

		<section class="ftco-section ftco-no-pt ftco-no-pb py-5 bg-light">
      <div class="container py-4">
        
      </div>
    </section>
    <footer class="ftco-footer ftco-section">
      <div class="container">
      	<div class="row">
      		<div class="mouse">
						<a href="#" class="mouse-icon">
							<div class="mouse-wheel"><span class="ion-ios-arrow-up"></span></div>
						</a>
					</div>
      	</div>
        
        <div class="row">
          <div class="col-md-12 text-center">

            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart color-danger" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
						  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						</p>
          </div>
        </div>
      </div>
    </footer>
    
  

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


  <script src="./resources/shop/js/jquery.min.js"></script>
  <script src="./resources/shop/js/jquery-migrate-3.0.1.min.js"></script>
  <script src="./resources/shop/js/popper.min.js"></script>
  <script src="./resources/shop/js/bootstrap.min.js"></script>
  <script src="./resources/shop/js/jquery.easing.1.3.js"></script>
  <script src="./resources/shop/js/jquery.waypoints.min.js"></script>
  <script src="./resources/shop/js/jquery.stellar.min.js"></script>
  <script src="./resources/shop/js/owl.carousel.min.js"></script>
  <script src="./resources/shop/js/jquery.magnific-popup.min.js"></script>
  <script src="./resources/shop/js/aos.js"></script>
  <script src="./resources/shop/js/jquery.animateNumber.min.js"></script>
  <script src="./resources/shop/js/bootstrap-datepicker.js"></script>
  <script src="./resources/shop/js/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="./resources/shop/js/google-map.js"></script>
  <script src="./resources/shop/js/main.js"></script>

  <script>
		$(document).ready(function(){

		var quantitiy=0;
		   $('.quantity-right-plus').click(function(e){
		        
		        // Stop acting like a button
		        e.preventDefault();
		        // Get the field name
		        var quantity = parseInt($('#quantity').val());
		        
		        // If is not undefined
		            
		            $('#quantity').val(quantity + 1);

		          
		            // Increment
		        
		    });

		     $('.quantity-left-minus').click(function(e){
		        // Stop acting like a button
		        e.preventDefault();
		        // Get the field name
		        var quantity = parseInt($('#quantity').val());
		        
		        // If is not undefined
		      
		            // Increment
		            if(quantity>0){
		            $('#quantity').val(quantity - 1);
		            }
		    });
		    
		});
	</script>
    
  </body>
</html>