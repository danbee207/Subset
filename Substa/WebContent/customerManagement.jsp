<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="substa.beans.User"%>
<%@ page import="substa.beans.Employer"%>
<%@ page import="substa.beans.Customer"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Substa</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="css/jquery.datetimepicker.css">

<link rel="stylesheet" href="css/employeeM.css">
</head>

<%
	User LoginUser = (User) session.getAttribute("LoginUser");
	Employer employeeInfo = (Employer) session.getAttribute("employeeInfo");
	ArrayList<Customer> customerList = (ArrayList<Customer>) session.getAttribute("customerList");
	if (LoginUser != null) {
%>
<body>
	<%
		} else {
	%>

<body onload="loginReset();">
	<%
		}
	%>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="main.jsp"><span
					class="SubstaLabel">Substa</span></a>
			</div>


			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">


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

							<li><a href="PreEmployeeManagement">Employee Management</a></li>
							<li><a href="PreCustomerManagement">Customer Management</a></li>
							<li><a href="ItemManagement">Item Management</a></li>
							<li><a href="#">Summary </a></li>
							<li><a href="#">History</a></li>
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
	<div class="container-fluid">
		<div class="upperPart"></div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Customer Mailing List</h3>
			</div>
			<div class="panel-body">

				
				<table class="table table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>E-mail</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (int i = 0; i < customerList.size(); i++) {
						%>
						<tr
							onclick="SendingEmail('<%=customerList.get(i).getFirst()%>','<%=customerList.get(i).getLast()%>',
												'<%=customerList.get(i).getEmail()%>')">
							<th scope="row"><%=i%></th>
							<td><%=customerList.get(i).getFirst()%></td>
							<td><%=customerList.get(i).getLast()%></td>
							<td><%=customerList.get(i).getEmail()%></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>

			</div>
		</div>


	</div>
	<div id="footer">
		
		<div class="container">
			<div class="navbar-header" id=footerHeader>
				<a class="navbar-brand" href="#"><span class="SubstaLabel">Substa</span></a>
			</div>
			<div class="navbar-footer navbar-right">
				<h4>Copy@ Right Substa | Stony Brook University</h4>
			</div>
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
						<input type="hidden" value="EmployeeManagement.jsp"
							name="TargetPage">
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
	<div class="modal fade" id="sendingEmail" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Send an email</h4>
				</div>
				<form class="form-horizontal">
					<div class="modal-body">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">To</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" id="customerEmail"
									placeholder="Email" name="email" id="email">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">Content</label>
							<div class="col-sm-10">
								<textarea rows="10" cols="100" class="form-control" id="content"></textarea>
							</div>
						</div>
					

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Send</button>
				</div>
				</form>
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
	<script type="text/javascript" src="js/customerManagement.js"></script>

</body>
</html>