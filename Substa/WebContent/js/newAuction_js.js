
/**
 * @author Danbee Park
 */
var books = {"Books": "Books","Textbooks":"Textbooks","Magazines":"Magazines" , "E-books": "E-books"};
var electronics ={"TV & Video":"TV & Video" ,"Cell Phones":"Cell Phones","Desktops":"Desktops","Laptops":"Laptops"};
var motors ={"Cars":"Cars","Motorcycles":"Motorcycles","Automotive Tools":"Automotive Tools"};
var sportingGoods={"Team Sports": "Team Sports","Leisure Sports":"Leisure Sports","Water Sports":"Water Sports"};
var toyHobbies ={"Action Figures":"Action Figures","Building Toys":"Building Toys","Baby Toys":"Baby Toys",
				"Games":"Games","Videos":"Videos"};
var fashion ={"Women's Clothing" : "Women's Clothing", "Men's Clothing": "Men's Clothing","Kids' Clothing":"Kids' Clothing"};
var homeGarden = {"Kitchen" : "Kitchen", "Furniture" : "Furniture", "Appliance": "Appliance", "Flowers & Trees":"Flowers & Trees",
			"Gardening Supplies": "Gardening Supplies"};

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


