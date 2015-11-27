/**
 * 
 */
var isEmail = isPw = isSsn = isFirstN = isLastN = isTel= isZip = false;

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
	
	$("#IntroModal").modal('show');
	
	initializePage();
	
	
	return false;
}
function initializePage(){
	//initialize
	$("#firstName").val("");
	$("#lastName").val("");
	$("#email").val("");
	$("#password").val("");
	$("#ssn").val("");
	$("#street").val("");
	$("#city").val("");
	document.getElementById("state").selectedIndex = 0;
	$("#zipcode").val("");
	$("#tele").val("");
}


function checkingnameFormat(){
	
	var filter = /^\d+$/;
	var firstName = $("#firstName").val();
	var lastName = $("#lastName").val();
	
	isFirstN = !filter.test(firstName);
	isLastN = !filter.test(lastName);
	
	if(!isFirstN || !isLastN){
		
		console.log("problem");
		$("#nameDiv").attr("class","form-group has-error has-feedback");
		if(!isFirstN){
			$("#firstName").attr("aria-describedby","inputError2Status");
			$("#firstNameBad").show();
			$("#firstNameGood").hide();
			
			
		}else{
			$("#lastName").attr("aria-describedby","inputError2Status");
			$("#lastNameBad").show();
			$("#lastNameGood").hide();
			
		}
		
	}else{
		
		console.log("perfect");
		
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
      
    	$("#emailDiv").attr("class","form-group has-success has-feedback");
    	$('[name="email"]').attr("aria-describedby","inputSuccess2Status");
    	$("#emailBad").hide();
    	$("#emailGood").show();
    	
    	isEmail=true;

    }else{
    	$("#emailDiv").attr("class","form-group has-error has-feedback");
    	$('[name="email"]').attr("aria-describedby","inputError2Status");
    	$("#emailGood").hide();
    	$("#emailBad").show();
    	
    	isEmail = false;
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
		
		isPw=true;
	}else{
		$("#pwDiv").attr("class","form-group has-error has-feedback");
		$("#password").attr("aria-describedby","inputError2Status");
		$("#pwGood").hide();
		$("#pwBad").show();
		
		isPw=false;
	}
	
}


function checkingSSN(ssn){
	
	var ssnVal = ssn.value;
	var ssnFirst = ssnVal.substring(0,3);
	var ssnSecond = ssnVal.substring(3,5);
	var ssnThrid  = ssnVal.substring(5);
	var checkSSNcon = true;
	
	console.log(ssnVal + " "+ssnFirst + " " + ssnSecond + " " + ssnThrid);
	
	if(ssnFirst=='000' || ssnSecond=='00' || ssnThrid=='0000' || ssnFirst=='666' || (parseInt(ssnFirst)>=900 && parseInt(ssnFirst)<=999) ) 
		checkSSNcon=false;
	
	if(ssnVal.length==9 && $.isNumeric(ssnVal) && checkSSNcon){
		$("#ssnDiv").attr("class","form-group has-success has-feedback");
		$("#ssn").attr("arai-describedby","inputSuccess2Status");
		$("#ssnBad").hide();
		$("#ssnGood").show();
		
		isSsn=true;
		
	}else{
		$("#ssnDiv").attr("class","form-group has-error has-feedback");
		$("#ssn").attr("arai-describedby","inputError2Status");
		$("#ssnGood").hide();
		$("#ssnBad").show();
		
		isSsn=false;
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

function checkingzip(zip){
	
	var numbers = zip.value;
	
	isZip = $.isNumeric(numbers);
	
}

function checkingTel(tel){
		
		var numbers = tel.value;
		
		isTel = $.isNumeric(numbers);
		
}

function gobackToIndex(){
	location.href= "./index.jsp";
	
}

function goToServlet(btn){
	
	console.log("email : " + isEmail + "pw: "+isPw + "ssn"+isSsn + 
				"first : " + isFirstN + "last :" +isLastN + "tel : " + isTel + "zip : " + isZip );
	
	if(isEmail && isPw && isSsn && isFirstN && isLastN && isTel && isZip){
		$("#alertGood").show();
		
		$('[name="signUp"]').submit();
	}
	else{
		alert('Fill whole information correctly');
		
	}
}

