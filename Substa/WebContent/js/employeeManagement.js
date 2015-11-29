/**
 * 
 */

function editEmployee(i,first,last,email,pw,ssn,level,hourR,tele,start,address,zip){
	
	$("#ShowDetail").val(i);
	
	$("#firstName").val(first);
	$("#lastNmae").val(last);
	$("#ssn").val(ssn);
	$("#tele").val(tele);
	$("#email").val(email);
	$("#password").val(pw);
	
	address = address.split(",");
	$("#street").val(address[0]);
	$("#city").val(address[1]);
	$("#state").val(address[2]);
	
	$("#zipcode").val(zip);
	$("#startDate").val(start);
	$("#level").val(level);
	$("#hourR").val(hourR);
	
	$('#infoView').modal('show');
	
}
function editEmployeeSave(){
	
}

function deleteEmployee(){
	
}
function addEmployee(){
	
	$("#ShowDetail").val(-1);
	$("#firstName").val("");
	$("#lastNmae").val("");
	$("#ssn").val("");
	$("#tele").val("");
	$("#email").val("");
	$("#password").val("");
	$("#street").val("");
	$("#city").val("");
	$("#state").val("");
	$("#zipcode").val("");
	$("#startDate").val("");
	$("#level").val("");
	$("#hourR").val("");
	
	$("#infoView").modal('show');
}
function Logout(){
	
	location.href="Logout";
	
}

