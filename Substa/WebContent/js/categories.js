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