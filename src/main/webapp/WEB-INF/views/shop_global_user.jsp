<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
			<ul class="navbar-nav ml-auto">
	         
	          <li class="nav-item"><a href="shop/home.htm" class="nav-link">Home</a></li>
	          <c:if test="${uname1==null}">
	          	<li class="nav-item"><a href="login.htm" class="nav-link">LOGIN</a></li>
	          	<li class="nav-item"><a href="newaccount.htm" class="nav-link">REGISTER</a></li>
	          </c:if>
	          <c:if test="${uname1!=null}">
	          	<li class="nav-item active dropdown">
	              	<a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${uname1}</a>
		              <div class="dropdown-menu" aria-labelledby="dropdown04">
		              	<a class="dropdown-item" href="./shop/myorder.htm">My Orders</a>
		              	<a class="dropdown-item" href="./shop/myprofile.htm">My Profile</a>
		              	<a class="dropdown-item" href="logout.htm">Logout</a>
		              </div>
	            </li>
	            <li class="nav-item cta cta-colored"><a href="shop/mycart.htm" class="nav-link"><span class="icon-shopping_cart"></span>[${sl}]</a></li>
	          </c:if>
	        </ul>
</body>
</html>