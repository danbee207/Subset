/**
 * 
 */
var isEmail=false;
var isPw = false;
var isSsn=false;
var isFirstN = false;
var isLastN = false;

function onloadHide(){
	
	$("#emailDiv").attr("class","form-group");
	$("#emailGood").hide();
	$("#emailBad").hide();
	
	$("#nameDiv").attr("class","form-group");
	$("#firstNameGood").hide();
	$("#firstNameBad").hide();
	$("#lastNameGood").hide();
	$("#lastNameBad").hide();
	
	$("#pwDiv").attr("class","form-group");
	$("#pwGood").hide();
	$("#pwBad").hide();
	
	$("#ssnDiv").attr("class","form-group");
	$("#ssnGood").hide();
	$("#ssnBad").hide();
	
	$("#alertGood").hide();
	
	return false;
}
function checkingnameFormat(){
	
	var filter = /^[0-9]/;
	var firstName = $("#firstName").val();
	var lastName = $("#lastName").val();
	
	if(filter.test(firstName)|| filter.test(lastName)){
		
		$("#nameDiv").attr("class","form-group has-error has-feedback");
		if(filter.test(firstName)){
			$("#firstName").attr("aria-describedby","inputSuccess2Status");
			$("#firstNameBad").hide();
			$("#firstNameGood").show();
			
		}else{
			$("#lastName").attr("aria-describedby","inputSuccess2Status");
			$("#lastNameBad").hide();
			$("#lastNameGood").show();
		}
		
	}else{
		$("#nameDiv").attr("class","form-group has-success has-feedback");
		$("#firstName").attr("aria-describedby","inputSuccess2Status");
		$("#lastName").attr("aria-describedby","inputSuccess2Status");
		
		$("#firstNameBad").hide();
		$("#firstNameGood").show();
		
		$("#lastNameBad").hide();
		$("#lastNameGood").show();
	}
	
}

function checkingEmailFormat(email){
	
	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	
    if (filter.test(email.value)) {
    	isEmail=true;
    	$("#emailDiv").attr("class","form-group has-success has-feedback");
    	$('[name="email"]').attr("aria-describedby","inputSuccess2Status");
    	$("#emailBad").hide();
    	$("#emailGood").show();
    }else{
    	$("#emailDiv").attr("class","form-group has-error has-feedback");
    	$('[name="email"]').attr("aria-describedby","inputError2Status");
    	$("#emailGood").hide();
    	$("#emailBad").show();
    }
    return false;
}
 
function checkingPasswordFormat(pw){
	
	var pwVal = pw.value;
	
	if(pwVal.length>=8 && pwVal.length<=10){
		$("#pwDiv").attr("class","form-group has-success has-feedback");
		$('#password').attr("aria-describedby","inputSuccess2Status");
		$("#pwBad").hide();
		$("#pwGood").show();
	}else{
		$("#pwDiv").attr("class","form-group has-error has-feedback");
		$("#password").attr("aria-describedby","inputError2Status");
		$("#pwGood").hide();
		$("#pwBad").show();
	}
	
}


function checkingSSN(ssn){
	
	var ssnVal = ssn.value;
	var ssnValtoS = ssnVal.toString.length;
	
	if(ssnValtoS.length==9 && $.isNumeric(ssnVal)){
		$("#ssnDiv").attr("class","form-group has-success has-feedback");
		$("#ssn").attr("arai-describedby","inputSuccess2Status");
		$("#ssnBad").hide();
		$("#ssnGood").show();
	}else{
		$("#ssnDiv").attr("class","form-group has-error has-feedback");
		$("#ssn").attr("arai-describedby","inputError2Status");
		$("#ssnGood").hide();
		$("#ssnBad").show();
	}
	
	
	/*
	 * The Social Security number is a nine-digit number in the format "AAA-GG-SSSS". The number is divided into three parts.
The middle two digits are the Group Number. The Group Numbers range from 01 to 99.
The last four digits are Serial Numbers. They represent a straight numerical sequence of digits from 0001 to 9999 within the group.
Some special numbers are never allocated:
Numbers with all zeros in any digit group (000-##-####, ###-00-####, ###-##-0000).
Numbers with 666 or 900-999 (Individual Taxpayer Identification Number) in the first digit group.
SSNs used in advertising have rendered those numbers invalid.
	 * 
	 */
}
function goToServlet(btn){
	if(isEmail && isPw && isSsn && isFirstN && isLastN){
		$("#alertGood").show();
		this.submit();
	}
	else{
		alert('Fill whole information correctly');
		return false;
	}
}

