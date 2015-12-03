<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.lang.Boolean"%>
<%@ page import="substa.beans.User"%>
<%@ page import="substa.beans.Customer"%>
<%@ page import="substa.beans.Employer"%>
<%@ page import="substa.beans.Item"%>
<%@ page import="substa.beans.AuctionDetailInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.sql.Timestamp" %>


<jsp:useBean id="bestSellers"
	type="java.util.ArrayList<substa.beans.Customer>" scope="session" />
<jsp:useBean id="deadlineItems"
	type="java.util.ArrayList<substa.beans.AuctionDetailInfo>" scope="session" />
<jsp:useBean id="bestSoldItems"
	type="java.util.ArrayList<substa.beans.Item>" scope="session" />	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Substa</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="css/index_css.css">
</head>

<body onload="poll()">
	<%
		boolean isCustomer = true;
		boolean isLogin = false;
		User LoginUser = (User) session.getAttribute("LoginUser");
		if (LoginUser != null) {
			isCustomer = (boolean) session.getAttribute("isCustomer");
			isLogin = true;
		} else
			isLogin = false;
	%>

	<div id="wrapper">
		<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="main.jsp"><span
					class="SubstaLabel">Substa</span></a>
			</div>
			<div class="navbar-header">

				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar" onclick="goCategories(this,<%=isLogin%>);">Book</span>
					<span class="icon-bar" onclick="goCategories(this,<%=isLogin%>);">Electronics</span>
					<span class="icon-bar" onclick="goCategories(this,<%=isLogin%>);">Fashion</span>
					<span class="icon-bar" onclick="goCategories(this,<%=isLogin%>);">Home & Garden</span> <span class="icon-bar"
						onclick="goCategories(this,<%=isLogin%>);">Motors</span> <span
						class="icon-bar" onclick="goCategories(this,<%=isLogin%>);">Sporting Goods</span> <span class="icon-bar"
						onclick="goCategories(this,<%=isLogin%>);">Toy & Hobbies</span>
				</button>

			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<%
					if (isCustomer) {
				%>
				<form action="CategoriesShown" method="get" id="listclicked">

					<input type="hidden" value="" name="category" id="category">
				</form>
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
						data-toggle="dropdown" role="button" aria-expanded="false">Home & Garden<span class="caret"></span>
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
							<li><a href="#" onclick="goCategories(this,<%=isLogin%>);">Automotive
									Tools</a></li>
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
						data-toggle="dropdown" role="button" aria-expanded="false">Toy & Hobbies <span class="caret"></span>
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
				<%
					}
				%>
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
								if (LoginUser != null) {

									if (isCustomer) {
							%>
							<li><a href="BidProcess">My Bidding Process</a></li>
							<li><a href="MyHistory">History</a></li>
							<li><a href="newAuction.jsp">Upload an Auction</a></li>
							<li><a href="MySettings.jsp">My Setting</a> <%
 	} else {
 %>
							<li><a href="PreEmployeeManagement">Employee Management</a></li>
							<li><a href="PreCustomerManagement">Customer Management</a></li>
							<li><a href="ItemManagement">Item Management</a></li>
							<li><a hef="">Summary</a>
							<li><a href="#">History</a></li>
							<%
								}
								}
							%>
							<%
								if (LoginUser != null) {
							%>
							<li class="divider"></li>
							<li><a href="#" data-toggle="modal"
								data-target="#logoutModal">Log out</a></li>
							<%
								} else {
							%>
							<li><a href="#" readonly>Substa</a>
							<li class="divider"></li>
							<li><a href="#" data-toggle="modal"
								data-target="#singinModal">Sign in</a></li>
							<%
								}
							%>
						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid --> </nav>
	</div>
	<%
		if (isCustomer == true) {
	%>
	<div id="containerBody">
		<div id="blankforBody"></div>
		<div class="jumbotron" id="jumboTron1">
			<h1>Welcome to Substa,</h1>
			<h2 class="lead">Substa is a new auction Site. I hope you could
				find your like.</h2>
			<br />
			<form class="form-horizontal" method="post" action="SearchItem" id="searchForm">
				<div class="form-group" id="serarchDiv">
					<div class="col-sm-8">
						<input type="text" id="search" name="search" size="10"
							class="form-control input-lg"
							placeholder="Samsung, Coffee Machine, Microwave">
					</div>
					<div class="col-sm-4">
						<button class="btn btn-warning btn-lg"
							onclick="searchItem(<%=isLogin%>);">Search</button>
					</div>
				</div>
			</form>
		</div>

		<div class="row">
			<h2 class="col-md-11">Deadline Soon!</h2>
			
		</div>
		<% for(int i=0;i<deadlineItems.size();i++){
			if(i%4==0){%>
		<div class="row">
		<%} %>

			<div class="col-sm-5 col-md-3">
				<div class="thumbnail">
				<%if(deadlineItems.get(i).getImgSrc()==null) {%>
				<img src="img/basic/NotitemShown.png" alt="...">
				
				<%}else{ %>
					<img src="FileDownload?file_name=<%=deadlineItems.get(i).getImgSrc() %>" alt="...">
					<%} %>				
					<div class="caption">
						<h3><%=deadlineItems.get(i).getItemName() %></h3>
						<p>Categories:<%=deadlineItems.get(i).getItemType()%></p>
						<% SimpleDateFormat before = new SimpleDateFormat("yyyy-m-dd hh:mm:ss");
						   SimpleDateFormat after  = new SimpleDateFormat("yyyy/mm/dd hh:mm");
						   
						   Date d = before.parse(deadlineItems.get(i).getEndDate().toString());	  
						   String fixedDate = after.format(d);
						%>
						<p>Auction End Time : <%=fixedDate %></p>
						<p class="text-right">
							<a href="#" class="btn btn-warning" role="button">Detail</a>
						</p>
					</div>
				</div>
			</div>
						
			<%if(i%4==3){ %>
		</div>
		<%break;}}if(deadlineItems.size()<3){ %>
	</div>
	<%} %>

	<div class="row">
			<h2 class="col-md-11">Check the Best-Sellers!</h2>
			
		</div>
		<% for(int i=0;i<bestSoldItems.size();i++){if(i%4==0){%>
		<div class="row">
		<%} %>

			<div class="col-sm-5 col-md-3">
				<div class="thumbnail">
				<%if(bestSoldItems.get(i).getImgsrc()==null) {%>
				<img src="img/basic/NotitemShown.png" alt="...">
				
				<%}else{ %>
					<img src="FileDownload?file_name=<%=bestSoldItems.get(i).getImgsrc() %>" alt="...">
					<%} %>				
					<div class="caption">
						<h3><%=bestSoldItems.get(i).getItemName() %></h3>
						<p>Categories:<%=bestSoldItems.get(i).getItemType()%></p>
					</div>
				</div>
			</div>
						
			<%if(i%4==3){ %>
		</div>
		<%break;}}if(bestSoldItems.size()<3){ %>
	</div>
	<%} %>
	
	<div class="row">
		<h2 class="col-md-11">Most Popular Sellers</h2>
		<form action="ShowSellersInfo" method="get" id="bestsellerInfo">
			<input type="hidden" value="" name="bestSeller" id="bestSeller">
		</form>
	</div>
	<%
			for (int i = 0; i < bestSellers.size(); i++) {
					if (i % 4 == 0) {
		%>

	<div class="row">
		<%
				}
			%>
		<div class="col-sm-5 col-md-3">
			<div class="thumbnail">
				<img src="img/basic/person.png">
				<div class="caption">
					<%
							String name = bestSellers.get(i).getFirst() + " " + bestSellers.get(i).getLast();
						%>
					<h3><%=name%>
					</h3>
					<p>E-mail: <%=bestSellers.get(i).getEmail()%></p>
					<p>Rate  :<%for(int j=0;j<bestSellers.get(i).getRating();j++){%>
						 <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
					<%}for(int j=0;j<5-bestSellers.get(i).getRating();j++){ %>
						 <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
					<%} %>
					</p>
					<p class="text-right">
						<a href="#" class="btn btn-warning" role="button" onclick="showbestSellersInfo(<%=i %>)" >Detail</a>
						
					</p>
				</div>
			</div>
		</div>


		<%
				if (i % 4 == 3) {
			%>
	</div>
	<%break;
			}
				}
				if (bestSellers.size() < 3) {
		%>
	</div>
	<%
		}
	%>


	</div>
	<%
		} else {
	%>
	<div id="containerBody">

		<div id="blankforBody"></div>

		<div class="jumbotron" id="jumboTron2">
			<h1>
				Hello,
				<%=LoginUser.getFirst()%>!
			</h1>
			<p class="lead">Thanks to your work! I hope you have a good day!</p>

		</div>


		<p class="lead">Which function do you need?</p>
		<div class="list-group">
			<a href="PreCustomerManagement"
				class="list-group-item list-group-item-info">
				<h4 class="list-group-item-heading">Customer Management</h4>
				<p class="list-group-item-text">Mail list for Customers</p>
			</a> <a href="PreEmployeeManagement" class="list-group-item">
				<h4 class="list-group-item-heading">Employeee Management</h4>
				<p class="list-group-item-text">Employer Information List /
					Auction's Mornitor List</p>
			</a> <a href="ItemManagement" class="list-group-item list-group-item-info">
				<h4 class="list-group-item-heading">Item Management</h4>
				<p class="list-group-item-text">- Item List</p>
			</a> <a href="#" class="list-group-item">
				<h4 class="list-group-item-heading">Summery</h4>
				<p class="list-group-item-text">- Monthly Sum</p>
			</a>
		</div>


	</div>

	<%
		}
	%>



	<div class="container" id="footer">
		<div class="navbar-header" id=footerHeader>
			<a class="navbar-brand" href="#"><span class="SubstaLabel">Substa</span></a>
		</div>
		<a href="help/Home.jsp"> HELP </a>
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
						<input type="hidden" value="main.jsp" name="TargetPage">
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


	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/index_js.js"></script>
	<script type="text/javascript" src="js/checkSales.js"></script>
</body>
</html>