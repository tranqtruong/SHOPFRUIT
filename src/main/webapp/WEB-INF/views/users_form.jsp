<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <base href="${pageContext.servletContext.contextPath}/">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>
    User Form
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
  
  <style type="text/css">
  	.val-error{
  		display: flex;
  		flex-direction: row;
  		
  	}
  </style>
  
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
              <a class="nav-link active" href="users/dashboard.htm">
                <i class="fas fa-users text-yellow"></i>
                <span class="nav-link-text">User Management</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="orders/dashboard.htm">
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
        <a class="h4 mb-0 text-white text-uppercase d-none d-lg-inline-block" href="users/dashboard.htm">Users Management</a>
        
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
              <h3 class="mb-0">User form</h3>
              
            </div>
            <div class="card-body">
              <form:form action="users/insert.htm" method="post" modelAttribute="user">
              	<div class="pl-lg-1">
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group">
                      	<div class="row">
                      		<label class="form-control-label col-3" for="input-username">Username</label>
                      		<label class="form-control-label" for="input-username"> <span class="badge-pill badge-warning badge"><form:errors path="Username"/></span></label>
                      	</div>
                        <form:input path="username" type="text" id="input-username" class="form-control form-control-alternative" placeholder="Username"/>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                      	<div class="row">
                      		<label class="form-control-label col-3" for="input-username">Password</label>
                      		<label class="form-control-label" for="input-username"> <span class="badge-pill badge-warning badge"><form:errors path="Password"/></span> </label>
                      	</div>
                        <form:input path="password" type="password" id="input-username" class="form-control form-control-alternative" placeholder="Password"/>
                      </div>
                    </div>
                    
                  </div>
                  
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group">
                      	<div class="row">
                      		<label class="form-control-label col-3" for="input-username">Fullname</label>
                      		<label class="form-control-label" for="input-username"> <span class="badge-pill badge-warning badge"><form:errors path="FullName"/></span> </label>
                      	</div>
                        <form:input path="fullName" type="text" id="input-username" class="form-control form-control-alternative" placeholder="Fullname" />
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                      	<div class="row">
                      		<label class="form-control-label col-3" for="input-username">Email</label>
                      		<label class="form-control-label" for="input-username"> <span class="badge-pill badge-warning badge"><form:errors path="Email"/></span> </label>
                      	</div>
                        <form:input path="email" type="email" id="input-username" class="form-control form-control-alternative" placeholder="Email" />
                      </div>
                    </div>
                    
                  </div>
                  
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group">
                      	<div class="row">
                      		<label class="form-control-label col-3" for="input-username">Phone</label>
                      		<label class="form-control-label" for="input-username"> <span class="badge-pill badge-warning badge"><form:errors path="Phone"/></span> </label>
                      	</div>
                        <form:input path="phone" type="tel" id="input-username" class="form-control form-control-alternative" placeholder="Phone"/>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                      	<div class="row">
                      		<label class="form-control-label col-3" for="input-username">Address</label>
                      		<label class="form-control-label" for="input-username"> <span class="badge-pill badge-warning badge"><form:errors path="Address"/></span> </label>
                      	</div>
                        <form:input path="address" type="text" id="input-username" class="form-control form-control-alternative" placeholder="Address"/>
                      </div>
                    </div>
                    
                  </div>
                  
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group">
                      	<div class="row">
                      		<label class="form-control-label col-3" for="input-username">DateOfBirth</label>
                      		<label class="form-control-label" for="input-username"> <span class="badge-pill badge-warning badge"><form:errors path="Birthday"/></span> </label>
                      	</div>
                        <form:input path="birthday" type="date" id="input-username" class="form-control form-control-alternative" placeholder="DateOfBirth"/>
                      </div>
                    </div>
                    
                    
                  </div>
                  <div class="row">
                  	<div class="col-lg-6">
                    <div class="form-group">
                      	<div class="row">
                      		<label class="form-control-label col-2" for="input-username">Status</label>
                      		<label class="form-control-label" for="input-username"> <span class="badge-pill badge-warning badge"><form:errors path="Status"/></span> </label>
                      	</div>
                      		
                      	<form:select path="status" items="${status_user}" class="form-control form-control-alternative"/>
                        <!-- <div class="custom-control custom-radio custom-control-inline">
						  <input type="radio" id="customRadioInline1" name="customRadioInline1" class="custom-control-input">
						  <label class="custom-control-label" for="customRadioInline1">Active</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
						  <input type="radio" id="customRadioInline2" name="customRadioInline1" class="custom-control-input">
						  <label class="custom-control-label" for="customRadioInline2">Inactive</label>
						</div> -->
                     </div>
                    
                    </div>
                      
                    <div class="col-lg-6">
                    <div class="form-group">
                      	<div class="row">
                      		<label class="form-control-label col-2" for="input-username">Role</label>
                      		<label class="form-control-label" for="input-username"> <span class="badge-pill badge-warning badge"><form:errors path="Role"/></span> </label>
                      	</div>
                      	<form:select path="role" items="${roles_user}" class="form-control form-control-alternative"/>
                      	
                        <!-- <div class="custom-control custom-radio custom-control-inline">
						  <input type="radio" id="customRadioInline1" name="customRadioInline1" class="custom-control-input">
						  <label class="custom-control-label" for="customRadioInline1">admin</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
						  <input type="radio" id="customRadioInline2" name="customRadioInline1" class="custom-control-input">
						  <label class="custom-control-label" for="customRadioInline2">user</label>
						</div> -->
                      </div>
                    
                    </div>
                    
                    </div>
  
                  <div class="row">
                  <div class="col-lg-9">
                      
                    </div>
                    <div class="col-lg-3">
                      <button type="submit" class="btn btn-primary" ${isUpDate} ${value}>Submit</button>
                    </div>
                    
                    
                  </div>
                  
                </div>
              
              </form:form>
                
                
                
         </div>
                
              
            </div>
            
          </div>
        </div>
      </div>
      
      
      <!-- Footer -->
      
    </div>
    
	
	<div id="alert_error" class="alert alert-danger alert-dismissible fade show" role="alert" style="position: fixed; left: 0; bottom: 0; display: none;">
	    <span class="alert-icon"><i class="ni ni-notification-70"></i></span>
	    <span class="alert-text"><strong>Failed!</strong> ${fail_message}</span>
	    <button id="btn_error" type="button" class="close" data-dismiss="alert" aria-label="Close">
	        <span aria-hidden="true">&times;</span>
	    </button>
	</div>
	
	<div id="alert_success" class="alert alert-success alert-dismissible fade show" role="alert" style="position: fixed; left: 0; bottom: 0; display: none;">
	    <span class="alert-icon"><i class="ni ni-notification-70"></i></span>
	    <span class="alert-text"><strong>Success!</strong> ${success_message}</span>
	    <button id="btn_success" type="button" class="close" data-dismiss="alert" aria-label="Close">
	        <span aria-hidden="true">&times;</span>
	    </button>
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
    function hideDiv (div_id, btn_id) {
    	var div_mess = document.getElementById(div_id);
    	div_mess.style.display = 'block';
    	setTimeout(() => {
    		var btn_close = document.getElementById(btn_id);
    		btn_close.click();
    	}, 5000);
        return false;
    }
    
    var flag = ${action};
    if(flag == "show_success"){	
    	hideDiv("alert_success", "btn_success");

    }else if(flag == "show_error"){
    	hideDiv("alert_error", "btn_error");
    }
  </script>
</body>
</html>