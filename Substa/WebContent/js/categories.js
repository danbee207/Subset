/**
 * 
 */
function goCategories(btn){
	
									//if user logs in, btn's value(categories name) sends
		$("#category").val($.trim($(btn).text()));
		
		console.log($("#category").val());
		
		$("#listclicked").submit();

	
}
function Logout(){
	
	location.href="Logout";
	
}

function searchSelectedbtn(btn){
	
	if(btn==0){
		$("#searchItembtn").attr("class","btn btn-default active");
		$("#searchSellerbtn").attr("class","btn btn-default");
	}else{
		$("#searchSellerbtn").attr("class","btn btn-default active");
		$("#searchItembtn").attr("class","btn btn-default");
	}
	
	
}
function gotoServlet(btn){
	if(btn==0){  //item
		
	}else{		//seller
		
	}
	
}