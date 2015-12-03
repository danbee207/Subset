/**
 * 
 */

function findSales(){
 
	
	
	if($("#searchType").val()==0){			//item
		$("#searSales").attr("action","ShowListofSalesItem");
		$("#searSales").submit();
		
		
	}else{									//Customer
		$("#searSales").attr("action","ShowListofSalesPerson");
		$("#searSales").submit();
		
	}
}
