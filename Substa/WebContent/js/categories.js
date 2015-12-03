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
	}else{
		$("#searchFrom").attr("action","");
	}
	
	
}
function gotoServlet(btn){
	if(btn==0){  //item
		
	}else{		//seller
		
	}
	
}