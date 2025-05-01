<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <base href="${pageContext.servletContext.contextPath}/">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>
    Order detail
  </title>
  <!-- Favicon -->
  <link href="./resources/assets/img/brand/favicon.png" rel="icon" type="image/png">
  <!-- Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
  <!-- Icons -->
  <link href="./resources/assets/js/plugins/nucleo/css/nucleo.css" rel="stylesheet" />
  <link href="./resources/assets/js/plugins/@fortawesome/fontawesome-free/css/all.min.css" rel="stylesheet" />
  <!-- CSS Files -->
  <link href="./resources/assets/css/argon-dashboard.css?v=1.1.2" rel="stylesheet" />
  <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap" rel="stylesheet">
    
  
</head>

<body class="">
  <nav class="sidenav navbar navbar-vertical  fixed-left  navbar-expand-xs navbar-light bg-white" id="sidenav-main">
    <div class="scrollbar-inner">
      <!-- Brand -->
      <div class="sidenav-header  align-items-center">
        <a class="navbar-brand" href="javascript:void(0)">
          <img src="./resources/assets/img/brand/blue.png" class="navbar-brand-img" alt="...">
        </a>
      </div>
      <div class="navbar-inner">
        <!-- Collapse -->
        <div class="collapse navbar-collapse" id="sidenav-collapse-main">
          <!-- Nav items -->
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" href="products/dashboard.htm">
                <i class="ni ni-box-2 text-primary"></i>
                <span class="nav-link-text">Product Management</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="users/dashboard.htm">
                <i class="fas fa-users text-yellow"></i>
                <span class="nav-link-text">User Management</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" href="orders/dashboard.htm">
                <i class="ni ni-bullet-list-67 text-orange"></i>
                <span class="nav-link-text">Order Management</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="shop/home.htm">
                <i class="ni ni-shop text-green"></i>
                <span class="nav-link-text">Go to Shop</span>
              </a>
            </li>
            
          </ul>
          
        </div>
      </div>
    </div>
  </nav>
  
  <div class="main-content">
    <!-- Navbar -->
    <nav class="navbar navbar-top navbar-expand-md navbar-dark" id="navbar-main">
      <div class="container-fluid">
        <!-- Brand -->
        <a class="h4 mb-0 text-white text-uppercase d-none d-lg-inline-block" href="orders/dashboard.htm">Order Management</a>
        
        <!-- User -->
        <jsp:include page="user.jsp"/>
      </div>
    </nav>
    <!-- End Navbar -->
    <!-- Header -->
    <div class="header bg-gradient-primary pb-6 pt-5 pt-md-8">
      <div class="container-fluid">
        <div class="header-body">
          <!-- Card stats -->
          
        </div>
      </div>
    </div>
    <div class="container-fluid mt--7">
      <!-- Table -->
      <div class="row">
        <div class="col">
          <div class="card shadow">
            <div class="card-header border-0">
              <h3 class="mb-0">Order details</h3>
              
            </div>
            <div class="table-responsive">
              
              <section class="ftco-section">
		   		<div class="container">
		   			<div class="row">
		   				<div class="col-lg-6 mb-5 ftco-animate">
							<h3>Products:</h3>
			   				 	<div class="table">
				              <table class="table align-items-center table-flush">
				                <thead class="thead-light">
				                  <tr>
				                    <th scope="col">Product Name</th>		               
				                    <th scope="col">Quantity</th>
				                    <th scope="col">Total</th>
				                  </tr>
				                </thead>
				                <tbody>
				                	<c:forEach var="c" items="${carts}">
				                		<tr>
						                    <th scope="row">
						                      <div class="media align-items-center">
						                        <a href="#" class="avatar rounded-circle mr-3">
						                          <img alt="Image placeholder" src="./images/${c.product.productImage}">
						                        </a>
						                        <div class="media-body">
						                          <span class="mb-0 text-sm">${c.product.productName}</span>
						                        </div>
						                      </div>
						                    </th>
						                    <td>
						                      ${c.quantity} Kg
						                    </td>
						                    <td>
						                      <fmt:formatNumber value="${c.subTotal}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
						                    </td>
						                  </tr>
				                	
				                	</c:forEach>
						                  
				                  
				                  
				                  
				                  
				                </tbody>
				              </table>
				            </div>
				            
							    
						</div>
		   				
		   				<div class="col-lg-6 product-details pl-md-5 ftco-animate">
			   				<h2>#ID: ${order.id}</h2>
			   				<h4>Account: ${order.user_order.username}</h4>
			   				<h4>Recipient's name: ${order.fullName}</h4>
			   				<h4>Recipient's phone number: ${order.phone}</h4>
			   				<h4>Receiver's address: ${order.address}</h4>
			   				<h4>Order Date: <fmt:formatDate value="${order.createdDate}" pattern="dd/MM/yyyy"/></h4>
			   				<h4>Total: <fmt:formatNumber value="${order.total}" type="currency" currencySymbol="đ" maxFractionDigits="0"/></h4>
			   				<h4>Status: 
			   					<c:choose>
									<c:when test="${order.status==0}">Chờ xác nhận</c:when>
									<c:when test="${order.status==1}">Khách yêu cầu huỷ</c:when>
									<c:when test="${order.status==2}">Đã huỷ</c:when>
									<c:when test="${order.status==3}">Chờ lấy hàng</c:when>
									<c:when test="${order.status==4}">Đang vận chuyển</c:when>
									<c:when test="${order.status==5}">Đã giao</c:when>
								</c:choose>
			   				</h4>
						</div>
						
						
					</div>
		   		</div>
    		</section>
              
            </div>
            
          </div>
        </div>
      </div>
      
      
      <!-- Footer -->
      
    </div>
  </div>
  
  
  
  <!--   Core   -->
  <script src="./resources/assets/js/plugins/jquery/dist/jquery.min.js"></script>
  <script src="./resources/assets/js/plugins/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <!--   Optional JS   -->
  <script src="./resources/assets/js/plugins/chart.js/dist/Chart.min.js"></script>
  <script src="./resources/assets/js/plugins/chart.js/dist/Chart.extension.js"></script>
  <!--   Argon JS   -->
  <script src="./resources/assets/js/argon-dashboard.min.js?v=1.1.2"></script>
  <script src="https://cdn.trackjs.com/agent/v3/latest/t.js"></script>
  <script>
    window.TrackJS &&
      TrackJS.install({
        token: "ee6fab19c5a04ac1a32a645abde4613a",
        application: "argon-dashboard-free"
      });
  </script>
</body>
</html>