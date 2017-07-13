/*
 * 调用后台批量删除的方法
 */
function deleteBatch(basePath){
	var len=document.listForm.id.length
	var checked=false;
	for(i=0;i<len;i++){
		if(document.listForm.id[i].checked==true){
			checked=true;
			break;
		}
	}
	if(!checked)
		alert("请至少选择一个关键词");
	else{
		$("#mainForm").attr("action",basePath+"DeleteBatchServlet.action")
		$("#mainForm").submit();				
	}
}