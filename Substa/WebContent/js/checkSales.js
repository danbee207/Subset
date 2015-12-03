/**
 * 
 */

function poll(){
	
	$.ajax({
		method : 'POST',
		url:"CheckDateServer",
		success : handleResponseJson,
		error : handleError,
		data : {isCheck:true }});
	
	
}

function handelResponseJson(data){
	
	clearInterval(t);
	t=setTimeout(poll,30000);
}
function handelResponseJson(data){
	
	clearInterval(t);
	t=setTimeout(poll,30000);
}
