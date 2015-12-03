/**
 * 
 */
var revenueValue=0;

function initialize(){
	$("#selectTxt").hide();

}

function searchSelectedbtn(btn){
	
	if(btn==0){
		$("#searchFrom").attr("action","ShowListofSalesItem");
		$("#searchItem").attr("class","btn btn-default active");
		$("#searchSeller").attr("class","btn btn-default");
		$("#searchMonth").attr("class","btn btn-default");
	}else if(btn==1){
		$("#searchFrom").attr("action","ShowListofSalesPerson");
		$("#searchItem").attr("class","btn btn-default");
		$("#searchSeller").attr("class","btn btn-default active");
		$("#searchMonth").attr("class","btn btn-default");
	}else{
		$("#searchFrom").attr("action","MonthlyReport");
		$("#searchItem").attr("class","btn btn-default");
		$("#searchSeller").attr("class","btn btn-default");
		$("#searchMonth").attr("class","btn btn-default active");
	}
	
	
}
function searchSelectedRevenue(btn){
	revenueValue=btn;
	
	if(btn==0){
		
		$("#searchItemRevenue").attr("class","btn btn-default active");
		$("#searchSellerRevenue").attr("class","btn btn-default");
		$("#searchTypeRevenue").attr("class","btn btn-default");
		
		$("#inputTxt").show();
		$("#selectTxt").hide();
	}else if(btn==1){
		$("#searchFromRevenue").attr("action","RevenueSeller");
		$("#searchItemRevenue").attr("class","btn btn-default");
		$("#searchSellerRevenue").attr("class","btn btn-default active");
		$("#searchTypeRevenue").attr("class","btn btn-default");
		
		$("#inputTxt").show();
		$("#selectTxt").hide();
	}else{
		$("#searchFromRevenue").attr("action","RevenueSeller");
		$("#searchItemRevenue").attr("class","btn btn-default");
		$("#searchSellerRevenue").attr("class","btn btn-default");
		$("#searchTypeRevenue").attr("class","btn btn-default active");
		
		$("#inputTxt").hide();
		$("#selectTxt").show();
	}
	
}
function getValuefromServlet(){
	$.ajax({
		method : 'POST',
		url:"RevenueValue",
		success : handleResponseJson,
		error : handleError,
		data : {searchtype:revenueValue, search:$("#typeSearch").val() },
		datatype : 'json'
	});
	
		
	
}
function handleResponseJson(data){
	
	console.log("complite!");

	var json_obj=$.parseJSON(data);
	console.log(json_obj.RevenueValue);
	$("#revenueValue").text(json_obj.RevenueValue); 
	
}
function handleError(){
	window.alert("Failed! Try again");
}

function selectRevenuechange(btn){
	$("#typeSearch").val($(btn).text());
}