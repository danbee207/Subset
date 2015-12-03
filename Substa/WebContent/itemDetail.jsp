<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="substa.beans.User"%>
<%@ page import="substa.beans.Customer"%>
<%@ page import="substa.beans.AuctionDetailInfo" %>
<%@ page import="substa.beans.BidHistory" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.sql.Timestamp" %>

<jsp:useBean id="itemDetail" type="substa.beans.AuctionDetailInfo" scope="session"/>
<jsp:useBean id="sellerInfo" type="substa.beans.Customer" scope="session"/>
<jsp:useBean id="thisAucHistory" type="java.util.ArrayList<substa.beans.BidHistory>" scope="session"/>

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
				<a class="navbar-brand" href="index.jsp"><span class="SubstaLabel">Substa</span></a>
			</div>
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> 
					<spanclass="icon-bar">Book</span> 
					<span class="icon-bar">Electronics</span>
					<span class="icon-bar">Fashion</span> 
					<span class="icon-bar">Home	& Garden</span> 
					<span class="icon-bar">Motors</span> 
					<span class="icon-bar">Sporting Goods</span> 
					<span class="icon-bar">Toy & Hobbies</span>
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
							<li><a href="#">Books</a></li>
							<li><a href="#">Textbooks</a></li>
							<li><a href="#">Magazines</a></li>
							<li><a href="#">E-Books</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Electronics<span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">TV & Video</a></li>
							<li><a href="#">Cell Phones</a></li>
							<li><a href="#">Desktops</a></li>
							<li><a href="#">Laptops</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Fashion
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Women's Clothing</a></li>
							<li><a href="#">Men's Clothing</a></li>
							<li><a href="#">Kids' Clothing</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Home
							& Garden<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Kitchen</a></li>
							<li><a href="#">Furniture</a></li>
							<li><a href="#">Appliance</a></li>
							<li class="divider"></li>
							<li><a href="#">Flowers & Trees</a></li>
							<li><a href="#">Gardening Supplies</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Mortors
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Cars</a></li>
							<li><a href="#">Motorcylces</a></li>
							<li><a href="#">Automotive Tools</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Sporting
							Goods<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Team Sports</a></li>
							<li><a href="#">Leisure Sports</a></li>
							<li><a href="#">Water Sports</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Toy
							& Hobbies <span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Action Figures</a></li>
							<li><a href="#">Building Toys</a></li>
							<li><a href="#">Baby Toys</a></li>
							<li class="divider"></li>
							<li><a href="#">Games</a></li>
							<li><a href="#">Videos</a></li>
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
								if (LoginUser == null || isCustomer) {
							%>
							<li class="disabled"><a hef="#">My Bidding Process</a>
							<li class="disabled"><a href="#">History</a> <%
 	} else {
 		if (isCustomer) {
 %>
							<li><a href="#">My Bidding Process</a>
							<li><a href="#">History</a> <%
 	} else {
 %>
							<li><a href="#">Customer Management</a>
							<li><a href="#">History</a> <%
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
		<!-- /.container-fluid --> </nav>
	</div>
	
	<div id="containerBody">
		<div id="blankforBody"></div>
		<div class="container">
		<div class="page-header">
		<input type="hidden" value="0" id="needMaxBid">
			  <h1><%=itemDetail.getItemName() %> <small> <%=itemDetail.getItemType() %></small></h1>
			</div>
		<div class="row">
			<div class="col-sm-10">
			<button type="button" class="btn btn-primary" onclick="">Bid</button>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">Basic Information</div>
			<div class="panel-body">
			<%if(itemDetail.getImgSrc()==null) {%>
				<img src="img/basic/NotitemShown.png" alt="...">
				
				<%}else{ %>
					<img src="FileDownload?file_name=<%=itemDetail.getImgSrc() %>" alt="...">
					<%} %>	
			
			<ul>
			<li><strong>Number of Copies</strong> <%=itemDetail.getCopy() %> </li>
			<li onclick="ShowSellersInfo?cusId=<%=sellerInfo.getSsn() %>"><strong>Seller ID</strong> <%=sellerInfo.getEmail() %> </li>
			<li><strong>Description</strong> <%=itemDetail.getDescription() %> </li>
			
			</ul>
			</div>
		
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">Auction's History</div>
			<div class="panel-body">

				<table class="table table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>*****@*******</th>
							<th>Bid Price</th>
							<th>Bid Time</th>
							
						</tr>
					</thead>
					<tbody>
						<%for(int i=0;i<thisAucHistory.size();i++){ %>
						<tr">
							<th scope="row"><%=i %></th>
							<td><span class="glyphicon glyphicon-usd" aria-hidden="true"></span> <%=thisAucHistory.get(i).getBidPrice() %></td>
							<% SimpleDateFormat before = new SimpleDateFormat("yyyy-m-dd hh:mm:ss");
						  		 SimpleDateFormat after  = new SimpleDateFormat("yyyy/mm/dd hh:mm");
						   
						   		Date d = before.parse(thisAucHistory.get(i).getBidTime().toString());	  
						   		String fixedDate = after.format(d);
							%>
							<td><%=fixedDate %></td>							
						</tr>
						<%} %>
						
					</tbody>
				</table>
			
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
	
	<div class="modal fade" id="BidMax" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Set BidMax</h4>
      </div>
      <div class="modal-body">
        <input type="text" id="futureMaxBid" name="futureMaxBid">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="bidingReal()">Bid</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/index_js.js"></script>
</body>
</html>