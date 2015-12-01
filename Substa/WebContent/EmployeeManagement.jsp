<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="substa.beans.User" %>
<%@ page import="substa.beans.Employer"%>

<jsp:useBean id="employerList"
	type="java.util.ArrayList<substa.beans.Employer>" scope="session" />
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
		User LoginUser = (User)session.getAttribute("LoginUser");
		Employer employeeInfo = (Employer)session.getAttribute("employeeInfo");
		if(LoginUser!=null){
	%>
	<body><%}else{ %>
 	<body onload="loginReset();"><%} %>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.jsp"><span
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

							<li><a href="#">Employee Management</a></li>
							<li><a href="#">Customer Management</a></li>
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
				<h3 class="panel-title">Employee Management</h3>
			</div>
			<div class="panel-body">

				<%  int j = -1;
					if (employeeInfo.getLevel() >= 2) {
				%>
				<button type="button" class="btn btn-default"
					onclick="<%j=-1;%> addEmployee();">
					<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
					Add
				</button>
				<%
					}
				%>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>E-mail</th>
							<th>Level</th>
						</tr>
					</thead>
					<tbody>
						<%
							
							for (int i = 0; i < employerList.size(); i++) {
						%>
						<tr onclick="editEmployee(<%=i%>,'<%=employerList.get(i).getFirst()%>','<%=employerList.get(i).getLast()%>',
												'<%=employerList.get(i).getEmail()%>','<%=employerList.get(i).getPw()%>','<%=employerList.get(i).getSsn()%>',
												<%=employerList.get(i).getLevel()%>,<%=employerList.get(i).getHourlyRate()%>,'<%=employerList.get(i).getTelephone()%>',
												'<%=employerList.get(i).getStartDate()%>','<%=employerList.get(i).getAddress()%>',<%=employerList.get(i).getZipcode()%>);">
							<th scope="row"><%=i%></th>
							<td><%=employerList.get(i).getFirst()%></td>
							<td><%=employerList.get(i).getLast()%></td>
							<td><%=employerList.get(i).getEmail()%></td>
							<td><%=employerList.get(i).getLevel()%></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>

			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Auction Management</h3>
			</div>
			<div class="panel-body">
				<%
					if (employeeInfo.getLevel() >= 2) {
				%>
				<button type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
					Save
				</button>
				<%
					}
				%>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Auction #</th>
							<th>Auction Name</th>
							<th>Mornitor</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>

			</div>
		</div>
	</div>
	<div id="footer">
		<nav class="navbar navbar-default navbar-fixed-bottom">
		<div class="container">
			<div class="navbar-header" id=footerHeader>
				<a class="navbar-brand" href="#"><span class="SubstaLabel">Substa</span></a>
			</div>
			<div class="navbar-footer navbar-right">
				<h4>Copy@ Right Substa | Stony Brook University</h4>
			</div>
		</div>
		</nav>

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
	<div class="modal fade" id="infoView" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="Employ">Employee Info</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal" id="emInfo" action="EmployeeManagement" method="post">
						<div class="form-group" id="nameDiv">
							<label for="firstName" class="col-sm-2 control-label">First
								Name</label>
							<div class="col-sm-3">
								<%
									if (employeeInfo.getLevel() >= 2) {
								%>
								<input type="text" class="form-control" name="firstName"
									id="firstName">
								<%}else{ %>
								<input type="text" class="form-control" name="firstName"
									id="firstName" readonly>
								<%} %>
							</div>

							<label for="lastName" class="col-sm-2 control-label">Last
								Name</label>
							<div class="col-sm-3">
								<%
									if (employeeInfo.getLevel() >= 2) {
								%>
								<input type="text" class="form-control" name="lastName"
									id="lastName">
								<%}else{ %>
								<input type="text" class="form-control" name="lastName"
									id="lastName" readonly>
								<%} %>
							</div>

						</div>
						<div class="form-group" id="ssnDiv">
							<label for="ssn" class="col-sm-2 control-label">SSN</label>
							<div class="col-sm-8">
								<%
									if (employeeInfo.getLevel() >= 2) {
								%>
								<input type="tel" class="form-control" name="ssn" id="ssn"
									placeholder="Social Security Number"
									onchange="checkingSSN(this);return false;">
								<%}else{ %>
								<input type="tel" class="form-control" name="ssn" id="ssn"
									placeholder="Social Security Number"
									onchange="checkingSSN(this);return false;" readonly>
								<%} %>

							</div>

						</div>
						<div class="form-group">
							<label for="tele" class="col-sm-2 control-label">Telephone</label>
							<div class="col-sm-8">
								<%
									if (employeeInfo.getLevel() >= 2) {
								%>
								<input type="tel" class="form-control" name="tele" id="tele">
								<%}else{ %>
								<input type="tel" class="form-control" name="tele" id="tele"
									readonly>
								<%} %>
							</div>
						</div>
						<div id="emailDiv" class="form-group">
							<label for="email" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-8">
								<%
									if (employeeInfo.getLevel() >= 2) {
								%>
								<input type="email" class="form-control" name="email" id="email"
									placeholder="ex)abc@substa.com" value="">
								<%}else{ %>
								<input type="email" class="form-control" name="email" id="email"
									placeholder="ex)abc@substa.com" value="" readonly>
								<%} %>

							</div>
						</div>
						<div class="form-group" id="pwDiv">
							<label for="password" class="col-sm-2 control-label">Password</label>
							<div class="col-sm-8">
								<%
									if (employeeInfo.getLevel() >= 2) {
								%>
								<input type="text" class="form-control" name="password"
									id="password" placeholder="Password" value="">
								<%}else{ %>
								<input type="text" class="form-control" name="password"
									id="password" placeholder="Password" value="" readonly>
								<%} %>

							</div>
						</div>

						<div class="form-group">
							<label for="street" class="col-sm-2 control-label">Street</label>
							<div class="col-sm-8">
								<%
									if (employeeInfo.getLevel() >= 2) {
								%>
								<input type="text" class="form-control" name="street"
									id="street" placeholder="Street Address">
								<%}else{ %>
								<input type="text" class="form-control" name="street"
									id="street" placeholder="Street Address" readonly>
								<%} %>
							</div>


						</div>
						<div class="form-group">
							<label for="city" class="col-sm-2 control-label">City</label>
							<div class="col-sm-3">
								<%
									if (employeeInfo.getLevel() >= 2) {
								%>
								<input type="text" class="form-control" placeholder="City"
									name="city" id="city">
								<%}else{ %>
								<input type="text" class="form-control" placeholder="City"
									name="city" id="city" readonly>
								<%} %>
							</div>
							<label for="
							state" class="col-sm-2 control-label">State</label>
							<div class="col-sm-3">
								<%
									if (employeeInfo.getLevel() >= 2) {
								%>
								<input type="text" class="form-control" placeholder="State"
									name="state" id="state" value="">
								<%}else{ %>
								<input type="text" class="form-control" placeholder="State"
									name="state" id="state" value="" readonly>
								<%} %>
							</div>
						</div>
						<div class="form-group">
							<label for="zipcode" class="col-sm-2 control-label">ZipCode</label>
							<div class="col-sm-8">
								<%
									if (employeeInfo.getLevel() >= 2) {
								%>
								<input type="tel" class="form-control" name="zipcode"
									id="zipcode" value="">
								<%}else{ %>
								<%} %>
							</div>
						</div>

						<div class="form-group">
							<label for="startDatelb" class="col-sm-2 control-label">Start
								Date</label>
							<div class="col-sm-8">
								<%
									if (employeeInfo.getLevel() >= 2) {
								%>
								<input type="text" class="form-control" name="startDate"
									id="startDate" value="">
								<%}else{ %>
								<input type="text" class="form-control" name="startDate"
									id="startDate" value="" readonly>
								<%} %>
							</div>
						</div>



						<div class="form-group">
							<label for="levellb" class="col-sm-2 control-label">
								Level</label>
							<div class="col-sm-3">
								<%
									if (employeeInfo.getLevel() >= 2) {
								%>
								<input type="text" class="form-control" name="level" id="level"
									value="">
								<%}else{ %>
								<input type="text" class="form-control" name="level" id="level"
									value="" readonly>
								<%} %>

							</div>
							<label for="hourlb" class="col-sm-2 control-label">HourlyRate</label>
							<div class="col-sm-3">
								<%
									if (employeeInfo.getLevel() >= 2) {
								%>
								<input type="text" class="form-control" name="hourR" id="hourR"
									value="">

								<%
									} else {
								%>
								<input type="text" class="form-control" name="hourR" id="hourR"
									value="" readonly>

								<%
									}
								%>
							</div>


						</div>



					</form>
				</div>
				<div class="modal-footer">
					<input type="hidden" name="ShowDetail" id="ShowDetail">
					<%
						if (employeeInfo.getLevel() >= 2) {
					%>
					<button type="button" class="btn btn-danger"
						onclick="deleteEmployee();">Delete</button>
					<button type="button" class="btn btn-primary"
						onclick="editEmployeeSave">Save changes</button>
					<%
						}
					%>
				</div>
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
						<input type="hidden" value="EmployeeManagement.jsp" name="TargetPage">
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
	<script type="text/javascript" src="js/employeeManagement.js"></script>
	<script type="text/javascript" src="js/jquery.datetimepicker.full.js"></script>
	<script>
	$("#startDate").datetimepicker({value:new Date(),step:10});
	</script>
</body>
</html>