<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.lang.Boolean"%>
<%@ page import="substa.beans.User"%>
<%@ page import="substa.beans.Customer"%>
<%@ page import="substa.beans.Employer"%>
<%@ page import="substa.model.DBManagers" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Substa</title>
<link rel="stylesheet" href="css/bootstrap-theme.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="css/index_css.css">
</head>

<body>
	<%
		boolean isCustomer = true;
		boolean isLogin =false;
		User LoginUser = (User) session.getAttribute("LoginUser");
		if (LoginUser != null){
			isCustomer = (boolean) session.getAttribute("isCustomer");
			isLogin = true;
		}else
			isLogin = false;
		
		DBManagers db = new DBManagers("jdbc:mysql://mysql2.cs.stonybrook.edu:3306/danpark","danpark","110142214");
		
		
	%>

	<div id="wrapper">
		<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.jsp"><span class="SubstaLabel">Substa</span></a>
			</div>
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar" onclick="goCategories(this,<%=isLogin%>);">Book</span> 
					<span class="icon-bar" onclick="goCategories(this,<%=isLogin%>);">Electronics</span>
					<span class="icon-bar" onclick="goCategories(this,<%=isLogin%>);">Fashion</span> 
					<span class="icon-bar" onclick="goCategories(this,<%=isLogin%>);">Home & Garden</span> 
					<span class="icon-bar" onclick="goCategories(this,<%=isLogin%>);">Motors</span> 
					<span class="icon-bar" onclick="goCategories(this,<%=isLogin%>);">Sporting Goods</span> 
					<span class="icon-bar" onclick="goCategories(this,<%=isLogin%>);">Toy & Hobbies</span>
				</button>
			
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-left">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Book
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Books</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Textbooks</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Magazines</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">E-Books</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Electronics<span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">TV & Video</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Cell Phones</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Desktops</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Laptops</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Fashion
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Women's Clothing</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Men's Clothing</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Kids' Clothing</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Home
							& Garden<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Kitchen</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Furniture</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Appliance</a></li>
							<li class="divider"></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Flowers & Trees</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Gardening Supplies</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Mortors
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Cars</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Motorcylces</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Automotive Tools</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Sporting
							Goods<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Team Sports</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Leisure Sports</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Water Sports</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Toy
							& Hobbies <span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Action Figures</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Building Toys</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Baby Toys</a></li>
							<li class="divider"></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Games</a></li>
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Videos</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><p class="navbar-text">
							<%
								if (LoginUser != null) {
							%>Hello, <b><%=LoginUser.getLast()%></b>!<%
								}
							%>
						</p>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">My
							Account<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<%
								if (LoginUser == null) {
									
							%>
							<li class="disabled"><a hef="#">My Bidding Process</a></li>
							<li class="disabled"><a href="#">History</a></li> <%
 							} else { if (isCustomer) {
 							%>
							<li><a href="#">My Bidding Process</a></li>
							<li><a href="#">History</a></li> 
							<li><a href="newAuction.jsp" >Upload an Auction</a></li>
							<li><a href="MySettings.jsp" >My Setting</a>
							<% } else { %>
							<li><a href="PreEmployeeManagement">Employee Management</a></li>
							<li><a href="#">Customer Management</a></li>
							<li><a href="#">History</a></li> <%
 	}
 	}
 %>
							<li class="divider"></li>
							<%
								if (LoginUser == null) {
							%>
							<li><a href="#" data-toggle="modal"
								data-target="#singinModal">Sign in</a></li>
							<%
								} else {
							%>
							<li><a href="#" data-toggle="modal"
								data-target="#logoutModal">Log out</a></li>
							<%
								}
							%>
						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid --> 
		</nav>
	</div>

	<div id="containerBody">
		<div id="blankforBody"></div>
		<div class="jumbotron">
			<h1>Hello, Guest!</h1>
			<p>Our site supports ~~~~.</p>
			<form>
				<div class="form-group">
					<label></label>
					<input type="text" id="search" name="search">
					<button >Search</button>
				</div>
			</form>
		</div>
		
		<h2>You are interested in...</h2>
		<div class="detailsBtn">
			<a href="#"class="btn btn-default" role="button" id="Interestingdetail"> more</a>
		</div>
		
		<div class="row">
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<img src="..." alt="...">
					<div class="caption">
						<h3>Name of Product</h3>
						<p>Description of product...</p>
						<p>Current High Bid: </p>
						<p>Sale ends in: </p>
						<p>
							<a href="#" class="btn btn-primary" role="button">Detail</a> 
						</p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<img src="..." alt="...">
					<div class="caption">
						<h3>Name of Product</h3>
						<p>Description of product...</p>
						<p>Current High Bid: </p>
						<p>Sale ends in: </p>
						<p>
							<a href="#" class="btn btn-primary" role="button">Detail</a> 
						</p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<img src="..." alt="...">
					<div class="caption">
						<h3>Name of Product</h3>
						<p>Description of product...</p>
						<p>Current High Bid: </p>
						<p>Sale ends in: </p>
						<p>
							<a href="#" class="btn btn-primary" role="button">Detail</a> 
						</p>
					</div>
				</div>
			</div>
		</div>
		
		<h2>Deadline Soon!</h2>
		<div class="detailsBtn">
			<a href="#"class="btn btn-default" role="button" id="Interestingdetail"> more</a>
		</div>
		
		<div class="row">
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<img src="..." alt="...">
					<div class="caption">
						<h3>Name of Product</h3>
						<p>Description of product...</p>
						<p>Current High Bid: </p>
						<p>Sale ends in: </p>
						<p>
							<a href="#" class="btn btn-primary" role="button">Detail</a> 
						</p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<img src="..." alt="...">
					<div class="caption">
						<h3>Name of Product</h3>
						<p>Description of product...</p>
						<p>Current High Bid: </p>
						<p>Sale ends in: </p>
						<p>
							<a href="#" class="btn btn-primary" role="button">Detail</a> 
						</p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<img src="..." alt="...">
					<div class="caption">
						<h3>Name of Product</h3>
						<p>Description of product...</p>
						<p>Current High Bid: </p>
						<p>Sale ends in: </p>
						<p>
							<a href="#" class="btn btn-primary" role="button">Detail</a> 
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
		

	
	<div class="container" id="footer">
		<div class="navbar-header" id=footerHeader>
			<a class="navbar-brand" href="#"><span class="SubstaLabel">Substa</span></a>
		</div>
		<div class="navbar-footer navbar-right">
			<h4>Copy@ Right Substa | Stony Brook University</h4>
		</div>
	</div>

	<div class="modal fade bs-example-modal-sm" id="singinModal"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Sing in</h4>
				</div>
				<form method="post" action="UserLogin" name="signIn">
					<div class="modal-body">

						<div class="form-group">
							<label for="SigninId">ID</label> <input type="text"
								name="signInId" class="form-control"
								placeholder="ex)abc@substa.com">
						</div>
						<div class="form-group">
							<label for="SigninPassword">Password</label><input
								type="password" name="signInPw" class="form-control">
						</div>
						<input type="hidden" value="index.jsp" name="TargetPage">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-warning"
							onclick="goTosignup();">Sign up</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Sign in</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<div class="modal fade bs-example-modal-sm" id="logoutModal"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Logout</h4>
				</div>

				<div class="modal-body">Do you want to log out ?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
					<button type="button" class="btn btn-primary" onclick="Logout()">Yes</button>
				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/index_js.js"></script>
</body>
</html>