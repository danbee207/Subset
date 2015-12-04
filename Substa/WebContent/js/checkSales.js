/**
 * 
 */
var t=0;
function poll(){
	
	$.ajax({
		method : 'POST',
		url:"CheckDateServer",
		success : handleResponseJson,
		error : handleError,
		data : {isCheck:true }});
	
	
}

function handleResponseJson(data){
	
	clearInterval(t);
	t=setTimeout(poll,30000);
}
function handleError(){
	
	clearInterval(t);
	t=setTimeout(poll,30000);
}
