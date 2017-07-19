/**
 * 页面加载
 */
$(function(){
	render();
	var content = "客官，来啦，坐吧！<br/>回复[查看]收取更多精彩内容。";
	content += "<br/>回复[帮助]可以查看所有可用的指令。";
	// 添加公众号的开场白
	appendDialog("talk_recordbox","公众号",content);
	render();
});

/**
 * 发送消息
 * @param basePath
 */
function send() {
	var content = $("#content").val();
	if(!content) {
		alert("请输入内容！");
		return;
	}
	$.ajax({
		url : $("#basePath").val() + "AutoReplyServlet.action",
		type : "POST",
		dataType : "text",
		timeout : 10000,
		success : function (data) {
			appendDialog("talk_recordboxme","My账号",content);
			appendDialog("talk_recordbox","公众号",data);
			$("#content").val("");
			render();
		},
		data : {"content":content}
	});
}

/**
 * 渲染方法，加载滚动条
 */
function render() {
	$('#jp-container').jScrollPane({
		showArrows:true, scrollbarWidth: 15, arrowSize: 16
	});
}

/**
 * 向聊天记录中添加聊天内容
 * @param myClass 添内容的样式
 * @param name 发送消息的账号名称
 * @param content 发送的内容
 */
function appendDialog(myClass,name,content) {
	var div = "";
	div += "<div class='" + myClass + "'>";
	div += "<div class='user'><img src='" + $("#basePath").val() + "resources/images/thumbs/" + myClass + ".jpg'/>" + name + "</div>";
	div += "<div class='talk_recordtextbg'>&nbsp;</div>";
	div += "<div class='talk_recordtext'>";
	div += "<h3>" + content + "</h3>";
	div += "<span class='talk_time'>" + getCurrentDate() + "</span>";
	div += "</div>";
	div += "</div>";
	$('#jp-container').children().eq(0).children().eq(0).append(div);
}

/**
 * 获取当前系统时间
 * @returns {String} 当前系统时间
 */
function getCurrentDate() {
	var date = new Date();
	return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":" + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes());
}