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
function showbestSellersInfo(num){
	$("#bestSeller").val(num);
	$("#bestsellerInfo").submit();
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