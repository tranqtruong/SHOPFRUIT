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
    Users Dashboard
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
        <!-- Form -->
        <form class="navbar-search navbar-search-dark form-inline mr-3 d-none d-md-flex ml-lg-auto">
          <div class="form-group mb-0">
            <div class="input-group input-group-alternative">
              <div class="input-group-prepend">
                <span class="input-group-text"><i class="fas fa-search"></i></span>
              </div>
              <input class="form-control" placeholder="Search User" type="text" name="search">
            </div>
          </div>
        </form>
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
            <div class="card-header border-0" style="display: flex; flex-direction: row; justify-content: space-between;">
              <h3 class="mb-0">Users tables: ${message_search}</h3>
              <a href="users/insert.htm" class="btn btn-sm btn-neutral"	>New User+</a>
            </div>
            <div class="table">
              <table class="table align-items-center table-flush">
                <thead class="thead-light">
                  <tr>
                    <th scope="col">Username</th>
                    <th scope="col">FullName</th>
                    <th scope="col">Email</th>
                    <th scope="col">Phone Number</th>
                    <th scope="col">Role</th>
                    <th scope="col">Status</th>
                    <th scope="col">DateOfBirth</th>
                    <th scope="col"></th>
                  </tr>
                </thead>
                <tbody>
                	<c:forEach var="u" items="${users}">
						<tr>
		                    <th scope="row">
		                      <span class="mb-0 text-sm">${u.username}</span>
		                    </th>
		                    <td>${u.fullName}</td>
		                    <td>${u.email}</td>
		                    <td>${u.phone}</td>
		                    <td>${u.role?'admin':'user'}</td>
		                    <td>
		                      <span class="badge badge-dot">
		                        <i class="${u.status?'bg-success':'bg-danger'}"></i> ${u.status?'active':'inactive'}
		                      </span>
		                    </td>
		                    <td>${u.birthday}</td>
		                    
		                    
		                    <td class="text-right">
		                      <div class="dropdown">
		                        <a class="btn btn-sm btn-icon-only text-light" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		                          <i class="fas fa-ellipsis-v"></i>
		                        </a>
		                        <div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
		                          <a class="dropdown-item" href="users/update/${u.userId}.htm">Edit</a>
		                          <a class="dropdown-item" onclick="theFunction(h = 'users/delete/${u.userId}.htm');">Delete</a>
		                        </div>
		                      </div>
		                    </td>
		                  </tr>
					
					</c:forEach>
                </tbody>
              </table>
            </div>
            
          </div>
        </div>
      </div>
      
      
      <!-- Footer -->
      
    </div>
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
	
	<button hidden="" id="aaa" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
	  Launch demo modal
	</button>
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Warning!</h5>
	        <button id="bbb" type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        Bạn có chắc muốn xóa user này không?
	      </div>
	      <div class="modal-footer">
	        <button type="button" id="ddd" class="btn btn-secondary" data-dismiss="modal">Huỷ</button>
	        <button type="button" id="ccc" class="btn btn-primary"> <a nonce="chophep2" style="color:white" id="no" href=""> Xoá </a> </button>
	      </div>
	    </div>
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
  <script type="text/javascript">
	  function theFunction () {
	      // return true or false, depending on whether you want to allow the `href` property to follow through or not
	      var btn2 = document.getElementById("aaa");
	      
	      btn2.click();
	      document.getElementById("no").href=h;
	      return false;
	  }
		
		function theFunction2 () {
	      // return true or false, depending on whether you want to allow the `href` property to follow through or not
	      var btn2 = document.getElementById("kkk");
	      
	      btn2.click();
	      return false;
	  }
  </script>
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