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
/* get categories text, sent to servelt, "CategoriesShown"
/* btn : the button which the user clicked
 * user : user info, User object
 */
function goCategories(btn,user){
	
	if(user==null){											//if user doesn't log in, show log-in view
		$('#singinModal').modal('show');			
	}else{													//if user logs in, btn's value(categories name) sends
		location.href="CategoriesShown/category?"+btn.val();
	}
}