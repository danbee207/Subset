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
		$("#searchFrom").attr("action","SearchItem");
		$("#searchItem").attr("class","btn btn-default active");
		$("#searchSeller").attr("class","btn btn-default");
	}else{
		$("#searchFrom").attr("action","SearchSeller");
		$("#searchItem").attr("class","btn btn-defaul");
		$("#searchSeller").attr("class","btn btn-default active");
		
	}
	
	
}
