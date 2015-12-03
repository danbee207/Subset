/**
 * @author Danbee Park
 */


function Logout(){
	
	location.href="Logout";
	
}
function searchItem(isLogin){
	if(isLogin){
		$("#searchForm").submit();
	}else{
		$('#singinModal').modal('show');	
	}
}

/*
 * @author Danbee park
 * 
 */
function goTosignup(){
	
	window.location.assign("singup.html");
	
	
}
/* get categories text, sent to servelt, "CategoriesShown"
/* btn : the button which the user clicked
 * user : user info, User object
 */
function goCategories(btn,user){
	
	if(user==false){											//if user doesn't log in, show log-in view
		$('#singinModal').modal('show');			
	}else{													//if user logs in, btn's value(categories name) sends
		$("#category").val($(btn).text());
		console.log($("#category").val());
		$("#listclicked").submit();

	}
}

function goTobid(){
	
	$.ajax({
		method : 'POST',
		url:"checkBidMax",
		success : handleResponse,
		error : handleError,
		datatype:"json"
		
	});
	
}

function handleResponse(data){
	
	var json_obj=$.parseJSON(data);
	var value= json_obj.value;
	var currentPrice = json_obj.currentValue;
	var valueMax = json_obj.valueMax;
	
	if(json_obj.value == -1){//new
		
	}else{			//not new
		
	}
}
function handleError(){
	
}