/**
 * 
 */
function ShowitemDetail(id,name,type,img,num,desc){
	$("#itemTitle").val(name);
	$("#itemNum").val(num);
	$("#itemId").val(id);
	$("#itemdesc").val(desc);
	$("#itemType").val(type);
	$("#itemImg").attr("src",img);
	
}