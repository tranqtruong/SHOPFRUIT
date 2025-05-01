<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Single Product</title>
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
          	<p class="breadcrumbs"><span class="mr-2"><a href="shop/home.htm">Home</a></span> <span>Product Single</span></p>
            <h1 class="mb-0 bread">Welcome!</h1>
          </div>
        </div>
      </div>
    </div>

    <section class="ftco-section">
    	<div class="container">
    	<form action="./shop/addtocart.htm" method="post">
    		<div class="row">
    			<div class="col-lg-6 mb-5 ftco-animate">
    				<a href="./images/${product.productImage}" class="image-popup"><img src="./images/${product.productImage}" class="img-fluid" alt="Colorlib Template"></a>
    			</div>
    			<div class="col-lg-6 product-details pl-md-5 ftco-animate">
    				<h3>${product.productName}</h3>
    				<p class="price"><span><fmt:formatNumber value="${product.price}" type="currency" maxFractionDigits="0" currencySymbol="đ/Kg"/></span></p>
    				<p>
    					<c:if test="${product.discount>0}">
							Discount: <fmt:formatNumber value="${product.discount/100}" type="percent"/>
						</c:if>
	    			</p>
    				<p>
    					${product.productDetail}
					</p>
						<div class="row mt-4">
							
							<div class="w-100"></div>
							<div class="input-group col-md-6 d-flex mb-3">
	             	<span class="input-group-btn mr-2">
	                	<button type="button" class="quantity-left-minus btn"  data-type="minus" data-field="">
	                   <i class="ion-ios-remove"></i>
	                	</button>
	            		</span>
	             	<input type="number" step="any" id="quantity" name="quantt" class="form-control input-number" value="1" min="1" max="${product.quantity}"/>
	             	<span class="input-group-btn ml-2">
	                	<button type="button" class="quantity-right-plus btn" data-type="plus" data-field="">
	                     <i class="ion-ios-add"></i>
	                 </button>
	             	</span>
	          	</div>
	          	<div class="w-100"></div>
	          	<div class="col-md-12">
	          		<p style="color: #000;">
	          			<c:choose>
							<c:when test="${product.quantity>0}">${product.quantity} Kg available</c:when>
							<c:otherwise>Đã bán hết</c:otherwise>
						</c:choose>
	          		</p>
	          	</div>
	          	<button id="mybtn" type="submit" name="pid" value="${product.productId}" hidden="true"></button>
          	</div>
          	<p><a href="javascript:{}" onclick="document.getElementById('mybtn').click();" class="btn btn-black py-3 px-5">Add to Cart</a></p>
    			</div>
    		</div>
    	
    	</form>
    		
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