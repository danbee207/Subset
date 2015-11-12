/**
 * @author Danbee Park
 */

function Logout(){
	
	location.href="Logout";
	
}
/*
 * @author Danbee park
 * 
 */
function goTosignup(){
	
	window.location.assign("singup.html");
	
	
}
function goCategories(btn,user){
	if(user==null){
		$('#singinModal').modal('show');
	}else{
		location.href="CategoriesShown/category?"+btn.val();
	}
}