<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shop - Vegefoods & Fruits</title>
    <meta charset="utf-8">
    <base href="${pageContext.servletContext.contextPath}/">
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
          	<p class="breadcrumbs"><span class="mr-2"><a href="shop/home.htm">Home</a></span> <span>Products</span></p>
            <h1 class="mb-0 bread">Welcome!</h1>
          </div>
        </div>
      </div>
    </div>
    
    

    <section class="ftco-section">
    	<div class="container">
    		<div class="row justify-content-center">
    			<div class="col-md-10 mb-5 text-center">
    				<div class="">
		              <form class="search-form">
		                <div class="form-group">
		                  <span class="icon ion-ios-search"></span>
		                  <input type="text" class="form-control" placeholder="Search..." name="search">
		                </div>
		              </form>
		            </div>
		            <div>${search_mess}</div>
    			</div>
    		</div>
    		
    		<jsp:useBean id="pagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="/shop/home.htm" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>
    		
    		<div class="row">
    			<c:forEach items="${pagedListHolder.pageList}" var="product">
					<div class="col-md-6 col-lg-3 ftco-animate">
	    				<div class="product">
	    					<a href="shop/single/${product.productId}.htm" class="img-prod" style="height: 202.4px !important; width: 253px !important;">
	    						<img class="img-fluid" src="./images/${product.productImage}" style="max-width:100% !important; max-height:100% !important;" alt="Colorlib Template">
	    						
    							<c:if test="${product.discount>0}">
	    							<span class="status">
	    								<fmt:formatNumber value="${product.discount/100}" type="percent"/>
	    							</span>
    							</c:if>
	    						
	    						<div class="overlay"></div>
	    					</a>
	    					<div class="text py-3 pb-4 px-3 text-center">
	    						<h3><a href="shop/single/${product.productId}.htm">${product.productName}</a></h3>
	    						<div class="d-flex">
	    							<div class="pricing">
			    						<p class="price">
			    							<c:choose>
												<c:when test="${product.discount>0}">
													<span class="mr-2 price-dc"> <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="đ" maxFractionDigits="0"/> </span>
			    									<span class="price-sale"> <fmt:formatNumber value="${product.price*(1-product.discount/100)}" type="currency" currencySymbol="đ" maxFractionDigits="0"/> </span>
												</c:when>
												<c:otherwise>
													<span> <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="đ" maxFractionDigits="0"/> </span>
												</c:otherwise>
											</c:choose>
			    						</p>
			    					</div>
		    					</div>
		    					<div class="bottom-area d-flex px-3">
		    						<div class="m-auto d-flex">
		    							<a href="shop/addtocart/${product.productId}.htm" class="buy-now d-flex justify-content-center align-items-center mx-1">
		    								<span><i class="ion-ios-cart"></i></span>
		    							</a>
	    							</div>
	    						</div>
	    					</div>
	    				</div>
	    			</div>
				</c:forEach>
    		</div>
    		<div class="row mt-5">
	          <div class="col text-center">
	            <div class="block-27">
	              <tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" />
	            </div>
	          </div>
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
    
  </body>
</html>