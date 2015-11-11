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
	
	
	return false;
}
function checkingEmailFormat(email){
	
	var emailEl = document.getElementsByName("email");
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
	
	
}

function checkingAddressFormat(address){
	
}

function checkingSSN(ssn){
	
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
		
		this.submit();
	}
	else{
		alert('Fill whole information correctly');
		return false;
	}
}

