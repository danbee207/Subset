<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="substa.beans.User"%>
<%@ page import="substa.beans.Customer"%>
<%@ page import="substa.beans.AuctionDetailInfo" %>
<%@ page import="java.util.ArrayList" %>

<jsp:useBean id="itemList" type="java.util.ArrayList<substa.beans.AuctionDetailInfo>" scope="session"></jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Substa</title>
<link rel="stylesheet" href="css/bootstrap-theme.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="css/index_css.css">
</head>

<body>
	<%
		boolean isCustomer = true;
		User LoginUser = (User) session.getAttribute("LoginUser");
		if (LoginUser != null)
			isCustomer = (boolean) session.getAttribute("isCustomer");
	%>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="main.jsp"><span class="SubstaLabel">Substa</span></a>
			</div>
			<div class="navbar-header">

				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar" onclick="goCategories(this);">Book</span>
					<span class="icon-bar" onclick="goCategories(this);">Electronics</span>
					<span class="icon-bar" onclick="goCategories(this);">Fashion</span>
					<span class="icon-bar" onclick="goCategories(this);">Home & Garden</span> <span class="icon-bar"
						onclick="goCategories(this);">Motors</span> <span
						class="icon-bar" onclick="goCategories(this);">Sporting Goods</span> <span class="icon-bar"
						onclick="goCategories(this);">Toy & Hobbies</span>
				</button>

			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<form action="CategoriesShown" method="get" id="listclicked">

					<input type="hidden" value="" name="category" id="category">
				</form>
				<ul class="nav navbar-nav navbar-left">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Book
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this);">Books</a></li>
							<li><a href="#" onclick="goCategories(this);">Textbooks</a></li>
							<li><a href="#" onclick="goCategories(this);">Magazines</a></li>
							<li><a href="#" onclick="goCategories(this);">E-Books</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Electronics<span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this);">TV & Video</a></li>
							<li><a href="#" onclick="goCategories(this);">Cell Phones</a></li>
							<li><a href="#" onclick="goCategories(this);">Desktops</a></li>
							<li><a href="#" onclick="goCategories(this);">Laptops</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Fashion
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this);">Women's Clothing</a></li>
							<li><a href="#" onclick="goCategories(this);">Men's Clothing</a></li>
							<li><a href="#" onclick="goCategories(this);">Kids' Clothing</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Home & Garden<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this);">Kitchen</a></li>
							<li><a href="#" onclick="goCategories(this);">Furniture</a></li>
							<li><a href="#" onclick="goCategories(this);">Appliance</a></li>
							<li class="divider"></li>
							<li><a href="#" onclick="goCategories(this);">Flowers & Trees</a></li>
							<li><a href="#" onclick="goCategories(this);">Gardening Supplies</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Mortors
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this);">Cars</a></li>
							<li><a href="#" onclick="goCategories(this);">Motorcylces</a></li>
							<li><a href="#" onclick="goCategories(this);">Automotive Tools</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Sporting Goods<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this);">Team Sports</a></li>
							<li><a href="#" onclick="goCategories(this);">Leisure Sports</a></li>
							<li><a href="#" onclick="goCategories(this);">Water Sports</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Toy & Hobbies <span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this);">Action Figures</a></li>
							<li><a href="#" onclick="goCategories(this);">Building Toys</a></li>
							<li><a href="#" onclick="goCategories(this);">Baby Toys</a></li>
							<li class="divider"></li>
							<li><a href="#" onclick="goCategories(this);">Games</a></li>
							<li><a href="#" onclick="goCategories(this);">Videos</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><p class="navbar-text">
							Hello, <b><%=LoginUser.getLast()%></b>!
						</p>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">My
							Account<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							
							<li><a href="#">My Bidding Process</a></li>
							<li><a href="#">History</a></li>
							<li><a href="newAuction.jsp">Upload an Auction</a></li>
							<li><a href="MySettings.jsp">My Setting</a>
 	
							<li class="divider"></li>
							
							<li><a href="#" data-toggle="modal"
								data-target="#logoutModal">Log out</a></li>
							
						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid --> </nav>
	</div>
	
	<div id="containerBody">
		<div id="blankforBody"></div>
		<nav class="navbar navbar-default">
			<div class="container-fluid">
			  
			    <form class="navbar-form navbar-left" role="search">
			      	<div class="input-group">
			    		<span class="input-group-btn">
							<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" 
								aria-haspopup="true" aria-expanded="false">
							  Search by <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
							  	<li><a href="#">Product Name</a></li>
							  	<li><a href="#">Product Type</a></li>
							  	<li><a href="#">Seller Name</a></li>
							</ul>
			    		</span>
			      		<input type="text" class="form-control" placeholder="Search for...">
			      		<span class="input-group-btn">
			        		<button class="btn btn-default" type="button">Search</button>
			      		</span>
			    	</div><!-- /input-group -->
			    </form>
			   
			    <ul class="nav navbar-nav navbar-right">
			      	<li class="dropdown">
			        	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
			        	Sort by<span class="caret"></span></a>
			        	<ul class="dropdown-menu">
			          		<li><a href="#">Price: Low to High</a></li>
			          		<li><a href="#">Price: High to Low</a></li>
			          		<li><a href="#">Avg. Customer Review</a></li>
			          		<li><a href="#">From the latest</a></li>
			        	</ul>
			      	</li>
			    </ul>
			</div><!-- /.navbar-collapse -->
		</nav>
		<div>
		<table class="table table-bordered">
		<% for(int i=0;i<itemList.size();i++){ 
			if(i%4==0){
			%><tr><%} %>
			<td>
			<img src="<%=itemList.get(i).getImgSrc() %>">
			<h3><%=itemList.get(i).getItemName() %></h3>
			<p>Type : <%=itemList.get(i).getItemType() %></p>
			<p>Auction End Time : <%=itemList.get(i).getEndDate() %></p>
			</td>
			<%if(i%4==3){ %>
			</tr>
		<%}} %>
		</table>
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
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/categories.js"></script>
</body>
</html>