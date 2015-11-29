/**
 * 
 */
var isPw =isFirstN = isLastN = isTel= isZip = isCard= false;

function onloadHide(state){
	
	console.log(state);
	$("#nameDiv").attr("class","form-group");
	$("#firstNameGood").hide();
	$("#firstNameBad").hide();
	$("#lastNameGood").hide();
	$("#lastNameBad").hide();
	
	$("#pwDiv").attr("class","form-group");
	$("#pwGood").hide();
	$("#pwBad").hide();
	
	var address = state.split(",");
	$("#street").val(address[0]);
	$("#city").val(address[1]);
	console.log(address[2]);
	var stateValue = 'opton[text="NY"]';
	$('#state').find('opton[text="NY"]').attr('selected','selected');
	console.log("1" +$('#state').find(stateValue).text());
	
	return false;
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



function checkingzip(zip){
	
	var numbers = zip.value;
	
	isZip = $.isNumeric(numbers);
	if(number="") isTel =true;
}

function checkingTel(tel){
		
		var numbers = tel.value;
		
		isTel = $.isNumeric(numbers);
		if(number="") isTel =true;
			
}

function gobackToIndex(){
	location.href= "./index.jsp";
	
}

function goToServlet(btn){
	
	if($(btn).text()=="Fix it!"){
		$("#type").val("0");
	}else{							//delete
		$("#type").val("1");
	}
	
	$('[name="signUp"]').submit();
	
	
	if(isPw && isFirstN && isLastN && isTel && isZip){
		
		$('[name="editCustomer"]').submit();
	}
	else{
		alert('Fill whole information correctly');
		
	}
}
function checkingCard(cardNum){
	
	var numbers = cardNum.value;
	
	isCard = $.isNumeric(numbers);
	if(cardNum.value.length>16 || cardNum.value.length <14)
		isCard=false;
	
	
}
