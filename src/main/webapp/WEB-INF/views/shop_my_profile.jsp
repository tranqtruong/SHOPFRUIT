<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Profile</title>
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
    <style type="text/css">
  	.error-mes{
  		color: #f5365c !important;
  	}
  </style>
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

    <section class="ftco-section">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-xl-7 ftco-animate card-body">
		  	<form:form action="./shop/myprofile.htm" class="billing-form" method="post" modelAttribute="user">
				<h3 class="mb-4 billing-heading">My profile</h3>
		          	<div class="row">
		          		<div class="col-md-6">
		                <div class="form-group">
		                	<label for="firstname">Full Name</label>
		                  <form:input path="FullName" type="text" class="form-control" placeholder=""/>
		                  <div class="meta error-mes"><form:errors path="FullName"/></div>
		                </div>
		              </div>
		              <div class="col-md-6">
		                <div class="form-group">
		                	<label for="lastname">Phone</label>
		                  <form:input path="phone" type="text" class="form-control" placeholder=""/>
		                  <div class="meta error-mes"><form:errors path="Phone"/></div>
		                </div>
	                </div>
	                
	                <div class="col-md-6">
		                <div class="form-group">
		                	<label for="lastname">Email</label>
		                  <form:input path="Email" type="email" class="form-control" placeholder=""/>
		                  <div class="meta error-mes"><form:errors path="Email"/></div>
		                </div>
	                </div>
	                
	                <div class="col-md-6">
			            	<div class="form-group">
			            		<label for="country">Birthday</label>
			            		<form:input path="Birthday" type="date" class="form-control" placeholder=""/>
		                  		<div class="meta error-mes"><form:errors path="Birthday"/></div>
			            	</div>
			            </div>
	                <div class="w-100"></div>
			            <div class="col-md-12">
			            	<div class="form-group">
			            		<label for="country">Address</label>
			            		<form:textarea path="Address" id=""  rows="7" class="form-control" placeholder="Message"/>
			            		<div class="meta error-mes"><form:errors path="Address"/></div>
			            	</div>
			            </div>
			            <button id="mybtn4" type="submit" hidden="true"></button>
			            
			            
		            </div>
	          </form:form><!-- END -->
					</div>
					<div class="col-xl-5">
	          <div class="row mt-5 pt-3">
	          	<div class="col-md-12 d-flex mb-5">
	          		<div class="cart-detail cart-total p-3 p-md-4">
	          			
	          			<h3 class="billing-heading mb-4"><a href="changepass.htm" >Change Password</a></h3>
	          			
					</div>
	          	</div>
	          	<div class="col-md-12">
	          		<div class="cart-detail p-3 p-md-4">
	          			<h3 class="billing-heading mb-4">Update Profile</h3>
	          			<c:choose>
							<c:when test="${message == false}"><p style="color: red;">Update fail!</p></c:when>
							<c:when test="${message == true}"><p style="color: green;">Update successful!</p></c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
							          			
	          			
					<p><a href="javascript:{}" onclick="document.getElementById('mybtn4').click();" class="btn btn-primary py-3 px-4">Save Change</a></p>
					</div>
	          	</div>
	          </div>
          </div> <!-- .col-md-8 -->
        </div>
      </div>
    </section> <!-- .section -->

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