<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.lang.Boolean" %>
<%@ page import="substa.beans.User" %>
<%@ page import="substa.beans.Customer"%>
<%@ page import="substa.beans.Employer" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Substa</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="css/index_css.css">
</head>
	
<body>
<%
		boolean isCustomer =true;
		User LoginUser = (User)session.getAttribute("LoginUser");
		if(LoginUser!=null)
			 isCustomer = (boolean)session.getAttribute("isCustomer");
	
%>

<div id="wrapper">
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
 	<div class="navbar-header">
      <a class="navbar-brand" href="#"><span class="SubstaLabel">Substa</span></a>
    </div>    
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar">Book</span>
        <span class="icon-bar">Electronics</span>
        <span class="icon-bar">Fashion</span>
        <span class="icon-bar">Home & Garden</span>
        <span class="icon-bar">Motors</span>
        <span class="icon-bar">Sporting Goods</span>
        <span class="icon-bar">Toy & Hobbies</span>
      </button>
      <a class="navbar-brand" href="#"><img src=""></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-left">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Book <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li class="divider"></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Electronics<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li class="divider"></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Fashion <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li class="divider"></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Home & Garden<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li class="divider"></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Mortors <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li class="divider"></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Sporting Goods<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li class="divider"></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Toy & Hobbies <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li class="divider"></li>
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      	<li><p class="navbar-text"><%if(LoginUser!=null){%>Hello, <b><%=LoginUser.getLast()%></b>!<%}%></p>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">My Account<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
          <% if(LoginUser==null){ %>
          	<li class="disabled"><a hef="#">My Bidding Process</a>
          	<li class="disabled"><a href="#">History</a>
          	<%}else if(isCustomer && LoginUser !=null){%>
          	<li><a hef="#">My Bidding Process</a>
          	<li><a href="#">History</a>
          	<%}else if(!isCustomer && LoginUser !=null){ %>
          	<li><a hef="#">Customer Management</a>
          	<li><a href="#">History</a>
          	<%} %>
            <li class="divider"></li>
            <%if(LoginUser==null){ %>
            <li><a href="#" data-toggle="modal" data-target="#singinModal">Sign in</a></li>
            <%}else{ %>
            <li><a href="#">Log out</a></li>
            <%} %>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</div>
<div id="containerBody">
</div>
<div class="modal fade bs-example-modal-sm" id="singinModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Sing in</h4>
      </div>
      <form method="post" action="UserLogin" name="signIn" >
      <div class="modal-body">
         
        <div class="form-group">
          <label for="SigninId">ID</label> <input type="text" name="signInId" class="form-control" placeholder="ex)abc@substa.com">
        </div>
        <div class="form-group">
         <label for="SigninPassword">Password</label><input type="password" name="signInPw" class="form-control" >
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary"  >Sign in</button>
      </div>
       </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade bs-example-modal-sm" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Logout</h4>
      </div>
      
      <div class="modal-body">
         Do you want to log out ?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
        <button type="button" class="btn btn-primary" data-target="Logout" >Yes</button>
      </div>
      
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>

</body>
</html>