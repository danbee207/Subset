<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="substa.beans.User"%>
<%@ page import="java.lang.Boolean"%>
<%@ page import="java.lang.String" %>

<jsp:useBean id="isCustomer" type="java.lang.Boolean" scope="session" />
<jsp:useBean id="customerInfo" type="substa.beans.Customer" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Substa</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="css/singup.css">
</head>

<body onload="onloadHide('<%=customerInfo.getAddress()%>');">

	<div id=wrapper>

		<div class="page-header">
			<h1>Edit your information!</h1>
		</div>
		

		<form method="post" action="Mysetting" name="editCustomer"
			class="form-horizontal" method="post" >
			<div class="form-group" id="nameDiv">
				<label for="firstName" class="col-sm-4 control-label">First
					Name</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="firstName"
						id="firstName" onchange="checkingnameFormat();" value="<%=customerInfo.getFirst()%>">
					<div id="firstNameGood">
						<span class="glyphicon glyphicon-ok form-control-feedback"
							aria-hidden="true"></span> <span id="inputSuccess2Status"
							class="sr-only">(success)</span>
					</div>
					<div id="firstNameBad">
						<span class="glyphicon glyphicon-remove form-control-feedback"
							aria-hidden="true"></span> <span id="inputError2Status"
							class="sr-only">(error)</span>
					</div>
				</div>

				<label for="lastName" class="col-sm-1 control-label">Last
					Name</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="lastName"
						id="lastName" onchange="checkingnameFormat();" value="<%=customerInfo.getLast()%>">
					<div id="lastNameGood">
						<span class="glyphicon glyphicon-ok form-control-feedback"
							aria-hidden="true"></span> <span id="inputSuccess2Status"
							class="sr-only">(success)</span>
					</div>
					<div id="lastNameBad">
						<span class="glyphicon glyphicon-remove form-control-feedback"
							aria-hidden="true"></span> <span id="inputError2Status"
							class="sr-only">(error)</span>
					</div>
				</div>

			</div>
			<div id="emailDiv" class="form-group">
				<label for="email" class="col-sm-4 control-label">Email</label>
				<div class="col-sm-5">
					<input type="email" class="form-control" name="email" id="email"
						placeholder="ex)abc@substa.com"
						onchange="checkingEmailFormat(this); return false;" value="<%=customerInfo.getEmail()%>" disabled>

				</div>
			</div>
			<div class="form-group" id="pwDiv">
				<label for="password" class="col-sm-4 control-label">Password</label>
				<div class="col-sm-5">
					<input type="password" class="form-control" name="password"
						id="password" placeholder="Password"
						onchange="checkingPasswordFormat(this);" value="<%=customerInfo.getPw()%>">
					<div id="pwGood">
						<span class="glyphicon glyphicon-ok form-control-feedback"
							aria-hidden="true"></span> <span id="inputSuccess2Status"
							class="sr-only">(success)</span>
					</div>
					<div id="pwBad">
						<span class="glyphicon glyphicon-remove form-control-feedback"
							aria-hidden="true"></span> <span id="inputError2Status"
							class="sr-only">(error)</span>
					</div>
				</div>
			</div>
			<div class="form-group" id="ssnDiv">
				<label for="ssn" class="col-sm-4 control-label">SSN</label>
				<div class="col-sm-5">
					<input type="tel" class="form-control" name="ssn" id="ssn"
						placeholder="Social Security Number"
						onchange="checkingSSN(this);return false;" value="<%=customerInfo.getSsn()%>" disabled>

				</div>

			</div>

			

			<div class="form-group">
				<label for="street" class="col-sm-4 control-label">Street</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="street" id="street"
						placeholder="Street Address" >
				</div>


			</div>
			
			<div class="form-group">
				<label for="city" class="col-sm-4 control-label">City</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" placeholder="City"
						name="city" id="city" onchange="checkBlank(this);" >
				</div>
				<label for="
							state" class="col-sm-1 control-label">State</label>
				<div class="col-sm-1">
					<select class="form-control" name="state" id="state">	
						<option>AL</option>
						<option>AK</option>
						<option>AS</option>
						<option>AZ</option>
						<option>AR</option>
						<option>CA</option>
						<option>CO</option>
						<option>CT</option>
						<option>DE</option>
						<option>DC</option>
						<option>FL</option>
						<option>GA</option>
						<option>GU</option>
						<option>HI</option>
						<option>ID</option>
						<option>IL</option>
						<option>IN</option>
						<option>IA</option>
						<option>KS</option>
						<option>KY</option>
						<option>LA</option>
						<option>ME</option>
						<option>MD</option>
						<option>MH</option>
						<option>MA</option>
						<option>MI</option>
						<option>FM</option>
						<option>MN</option>
						<option>MS</option>
						<option>MO</option>
						<option>MT</option>
						<option>NE</option>
						<option>NV</option>
						<option>NH</option>
						<option>NJ</option>
						<option>NM</option>
						<option>NY</option>
						<option>NC</option>
						<option>ND</option>
						<option>MP</option>
						<option>OH</option>
						<option>OK</option>
						<option>OR</option>
						<option>PW</option>
						<option>PA</option>
						<option>PR</option>
						<option>RI</option>
						<option>SC</option>
						<option>SD</option>
						<option>TN</option>
						<option>TX</option>
						<option>UT</option>
						<option>VT</option>
						<option>VA</option>
						<option>VI</option>
						<option>WA</option>
						<option>WV</option>
						<option>WI</option>
						<option>WY</option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="zipcode" class="col-sm-4 control-label">ZipCode</label>
				<div class="col-sm-2">
					<input type="tel" class="form-control" name="zipcode" id="zipcode"
						onchange="checkingzip(this);" value="<%=customerInfo.getZipcode()%>">
				</div>
			</div>

			<div class="form-group">
				<label for="tele" class="col-sm-4 control-label">Telephone</label>
				<div class="col-sm-5">
					<input type="tel" class="form-control" name="tele" id="tele"
						onchange="checkingTel(this);" value="<%=customerInfo.getTelephone()%>">
				</div>
			</div>
			<div class="form-group">
				<label for="cardN" class="col-sm-4 control-label">CardNumber</label>
				<div class="col-sm-5">
					<input type="tel" class="form-control" name="card" id="card"
						onchange="checkingCard(this);" value="<%=customerInfo.getCreditCardNum()%>">
				</div>
			</div>
			<br /> <br />
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-10">
					<button type="button" class="btn btn-danger"
						onclick="goToServlet(this);">I want to leave!</button>
					<button type="button" class="btn btn-warning"
						onclick="goToServlet(this);">Fix it!</button>
				</div>

			</div>
			<input type="hidden" value="" id="type" name="type">
		</form>

	</div>

	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/mysetting.js"></script>


</body>
</html>