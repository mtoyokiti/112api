

function reg() {
	
	var s = $("#url").val(),
	title = $("#title").val(),
	url = $("#url").val();
	
	save(title, url);
}


function del(i) {
	var now = new Date();

	$.ajax({
		  async: false,
		  type: 'POST',
		  url: '/delurl',
		  data: {index:i},
		  success: save$finish,
		  error: save$finish,
		  dataType: "json"
		});
	writeUserUrlList();
}




function writeUserUrlList() {

	var urllist = $("#urllist");
	urllist
	.empty();
	urllist
	.append("【あなたが登録したサイト一覧】<br><br>");
	
	var now = new Date();
	$.ajax({
		  async: false,
		  type: 'GET',
		  url: '/UserUrlList',
		  data: {n:now},
		  success: writeUserUrlListR,
		  error: writeUserUrlListR,
		  dataType: "json"
		});
}





function writeUserUrlListR(d, status) {
	if (status != "success") {
		return;
	}
	var u = $("#urllist");
	
	for (i=0; i<d.data.length; i=i+2) {
		var id = d.data[i];
		u
		.append("<img src='img/del.png' onclick='del("+id+")' class='del'>")
		.append(d.data[i+1])
		.append("<br>");
	}
}





function save$finish(data, status) {
	if (status == "success") {
		alert('完了しました');
		writeUserUrlList();
		
		var i=0,
			t = save$nodes.length;
		if (data.data[0] != 0) {
			alert("err:" + data.data[0]);
		}
	} else if (status == "error") {
		alert('エラー(通信できませんでした)');
	} else if (status == "notmodified") {
		alert('エラー(2)');
	} else if (status == "timeout") {
		alert('エラー(3)');
	} else if (status == "parsererror") {
		alert('OK');
	}
	if (dialog$end != null) {dialog$end();}
}




function save(title, url){
	var now = new Date();
	
	$("#status").text("保存中");
	$.ajax({
		  async: false,
		  type: 'POST',
		  url: '/saveurl',
		  data: {url:url, title: title},
		  success: save$finish,
		  error: save$finish,
		  dataType: "json"
		});
	$("#status").text("保存完了");
	
};