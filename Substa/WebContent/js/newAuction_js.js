
/**
 * @author Danbee Park
 */
var books = {0: "Books",1:"Textbooks",2:"Magazines" , 3: "E-books"};
var electronics ={0:"TV & Video" ,1:"Cell Phones",2:"Desktops",3:"Laptops"};
var motors ={0:"Cars",1:"Motorcycles",2:"Automotive Tools"};
var sportingGoods={0: "Team Sports",1:"Leisure Sports",2:"Water Sports"};
var toyHobbies ={0:"Action Figures",1:"Building Toys",2:"Baby Toys",3:"Games",4:"Videos"}
var fashion ={0 : "Women's Clothing", 1: "Men's Clothing",2:"Kids' Clothing"};
var homeGarden = {0 : "Kitchen", 1 : "Furniture", 2: "Appliance", 3:"Flowers & Trees",4: "Gardening Supplies"};

$( document ).ready(function() {
	$("#subType").hide();
});
function loginReset(){
	$("#singinModal").modal('show');
}

function Logout(){
	
	location.href="Logout";
	
}

function changeSubType(btype){
	
	btypeVal = parseInt(btype.value);
	
	$("#subType").show();
	$("#sType").empty();
	var SubtypeA;
	switch(btypeVal){
	case 1 : SubtypeA = books; break;
	case 2 : SubtypeA = electronics; break;
	case 3 : SubtypeA = fashion; break;
	case 4 : SubtypeA = homeGarden; break;
	case 5 : SubtypeA = motors; break;
	case 6 : SubtypeA = sportingGoods; break;
	case 7 : SubtypeA = toyHobbies; break;
	
	
	}
	for(var key in SubtypeA) 
    {
        var opt = document.createElement('option');
        opt.value = key;
        opt.innerHTML = SubtypeA[key];
        $("#sType").append(opt);
    }
	
	
}

function readURL(input) {

    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#previewImg').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}

