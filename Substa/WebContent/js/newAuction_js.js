
/**
 * @author Danbee Park
 */

var fashion ={0 : "Men's Clothing", 1: "Women's Clothing",2:"Kids's Clothing"};
var kitchen = {0 : "Kitchen", 1 : "Furniture", 2: "Appliance", 13:"Flowers & Trees",14: "Gardening Supplies"};

$( document ).ready(function() {
	$("#subType").hide();
});

function Logout(){
	
	location.href="Logout";
	
}

function changeSubType(btype){
	
	btypeVal = parseInt(btype.value);
	
	$("#subType").show();
	$("#sType").empty();
	var SubtypeA;
	switch(btypeVal){
	case 1 : SubtypeA = fashion; break;
	case 2 : SubtypeA = kitchen; break;
	}
	for(var key in SubtypeA) 
    {
        var opt = document.createElement('option');
        opt.value = key;
        opt.innerHTML = SubtypeA[key];
        $("#sType").append(opt);
    }
	
	
}