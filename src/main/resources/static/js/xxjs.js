/**
 * 
 */
/*layui.config({
    base: '/layui/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use('index');*/

var timeOut = 80*1000;
var layer = '';
var element = '';
var table = '';
var form = '';
var soulTable = '';
var layedit = '';
var laydate = '';
var code = '';

layui.use(['element', 'layer','table','soulTable','layedit', 'laydate','form','code'], function(){
  element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
  layer = layui.layer;
  table = layui.table;
  form = layui.form;
  soulTable = layui.soulTable;
  layedit = layui.layedit;
  laydate = layui.laydate;
  code = layui.code;
});




	//侧边导航显示信息
    $(".getElement").hover(function(e) {
    	var id = $(e.target).attr('id'); 
    	var hidid = "#hid" + id;
    	id = '#' + id;
    	var msg = $(hidid).text();
    	layer.tips(msg, id, {
    		  tips: [2, '#3595CC'],
    		  time: 400000
    		});
    }, function() {
    	layer.closeAll('tips');
    });
    
 

//添加标签
function tabAdd(title,content,tabId){
	//新增一个Tab项
    element.tabAdd('functionOperation', {
      title: title //标题
      ,content: content//内容
      ,id: tabId //id
    })
}


//切换导航标签
function tabChange(title,appId,methodId){
	console.log("进入方法");
	 var tabId = "navigationTab" + appId + methodId;
	 var exist=$("li[lay-id='"+tabId+"']").length; //判断是否存在tab
		if(exist==0){
			var formData = new FormData();
			formData.append("appid", appId);
			formData.append("metid", methodId);
			var ret = '';
			$.ajax({
				type:"post",
				url:"/method.opt",
				data:formData,
				timeout:timeOut,//超时时间设置， 单位毫秒
				processData: false,
				contentType: false,
				success:function(data){
					element.tabDelete('functionOperation', tabId); //删除
					tabAdd(title,data,tabId);
					element.tabChange('functionOperation', tabId); //切换到：用户管理
				},
				error: function(err){
					layer.msg("系统异常,请联系管理员！",{icon:5});
				}
			});
		}else{
			element.tabChange('functionOperation', tabId); //切换到：用户管理
		}
}


//设置应用参数
function appSetting(title,appId){
	title = title + '设置';
	 var tabId = "appSettingTab" + appId;
	 var exist=$("li[lay-id='"+tabId+"']").length; //判断是否存在tab
		if(exist==0){
			var formData = new FormData();
			formData.append("appid", appId);
			var ret = '';
			$.ajax({
				type:"post",
				url:"/app.opt",
				data:formData,
				timeout:timeOut,//超时时间设置， 单位毫秒
				processData: false,
				contentType: false,
				success:function(data){
					element.tabDelete('functionOperation', tabId); //删除
					tabAdd(title,data,tabId);
					element.tabChange('functionOperation', tabId); //切换到：用户管理
				},
				error: function(err){
					layer.msg("系统异常,请联系管理员！",{icon:5});
				}
			});
		}else{
			element.tabChange('functionOperation', tabId); //切换到：用户管理
		}
}

var local = 100;
var idNum = 0;
function  promptMsg(msg,outTime){
		var idName = "mydiv" +idNum;
		var idValue = "#" + "mydiv" +(idNum-1);
		var lastH = $(idValue).height();
		local = local + lastH +30;
		if(lastH == undefined){
			local = 100;
		}
		//msg = idValue + "高度：" +lastH + "窗口高度：" + $(window).height();
		if(local > ($(window).height() - 50)){
		local = 100;
		}
		idNum = idNum + 1;
  //多窗口模式，层叠置顶
  layer.open({
     type: 1, //此处以iframe举例
     //area: ['100px', '600px'],
     skin: 'mycss', //样式类名
     closeBtn: 0, //不显示关闭按钮
     //,anim: 2
    title: false, //不显示标题
	shadeClose: true, //开启遮罩关闭
	content: msg,
    shade: 0,
    id:idName,
    maxmin: false,
    offset: [ //为了演示，随机坐标
      local
      ,($(window).width()-300)
    ] ,
    time: outTime, //20s后自动关闭
    zIndex: layer.zIndex, //重点1
    success: function(layero){
      layer.setTop(layero); //重点2
      //console.log("重点2");
    }
  });
}

//json数据格式化
function JsonFormat(json) {
	if (typeof json == 'string') {
		console.log("String");
		json = $.parseJSON(json);
	}
	json = JSON.stringify(json, undefined, 2);
		json = json.replace(/&/g, '&').replace(/</g, '<').replace(/>/g, '>');
		console.log(json);
		return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
			 var cls = 'number';
			 	if (/^"/.test(match)) {
			 			if (/:$/.test(match)) {
			 				cls = 'key';
			 			} else {
			 				cls = 'string';
			 			}
			 	} else if (/true|false/.test(match)) {
			 		cls = 'boolean';
			 	} else if (/null/.test(match)) {
			 		cls = 'null';
			 	}
			 	match = match.replace(/</g, "&lt;");
			 	match = match.replace(/>/g, "&gt;");
			 	match = match.replace(/\\\"/g, "\"");
			 	return '<span class="' + cls + '">' + match + '</span>';
		 });
}


function requestFun(appid,metid){
	var requestId = "#request" + appid + metid;
	var intabid = "inparams" + appid + metid;
	var rsponseid = '#rsponse' + appid + metid
	var toolid = "#tool"  + appid + metid;
	var textid = "text"  + appid + metid;
	var getTextid = "#text"  + appid + metid;
	var payType = "";
	 if($(requestId)["0"].value != 1){
		 console.log($(requestId));
		 return;
	 }
	 console.log($(requestId));
	 console.log(rsponseid);
	 $(requestId).val("0");
	 $(requestId)[0].innerHTML = "处理中";
	 var htmlCon = "<i class='layui-icon layui-icon-loading layui-anim layui-anim-rotate layui-anim-loop ' style='font-size: 30px; color: #1E9FFF;'></i>";
	 $(rsponseid).css("text-align","center");
	 $(rsponseid).html(htmlCon);
	 $(toolid).html("");
	 var urlId = "#url" + appid + metid;
	 var reqURL = $(urlId).val();
	 var jsonDataReq = table.cache[intabid];
	 var tableData = JSON.stringify(jsonDataReq);
	 for(var i =0;i<jsonDataReq.length;i++){
			var nameEn = jsonDataReq[i].nameEn;
			if("payType" == nameEn){
				payType = jsonDataReq[i].value;
			}
		}
	 var formData = new FormData();
		formData.append("tableData", tableData);
		formData.append("reqURL", reqURL);
		formData.append("appid", appid);
		/*console.log(tableData);
		console.log(reqURL);
		console.log(appid);*/
	 var ajaxReq = $.ajax({
			type:"post",
			url:"/useMethod",
			data:formData,
			timeout:timeOut,//超时时间设置， 单位毫秒 
			processData: false,
			contentType: false,
			success:function(data){
		    	 if(data.success){
		    		  var dataJson = $.parseJSON(data.data);
		    		  console.log(dataJson);
		    		  for(var key in dataJson){  
						if(key == "codeUrl"){
							console.log(dataJson[key]);
							var toolCode = '<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" onclick = "getQRCode(\''+dataJson[key]+'\',\''+payType+'\')">生成二维码</button>'
							$(toolid).html(toolCode);
							break;
						}else if(key == "html"){
							var urlPath = window.document.location.href; 
							urlPath = urlPath + "/payPage";
							var toolCode = '';
							    toolCode = toolCode + '<div class="layui-row interface-head-content">';
							    toolCode = toolCode + '<div class="layui-col-xs12">';
							    
							    toolCode = toolCode +      '<button type="button" class="layui-btn layui-btn-primary layui-btn-sm"  onclick = "openHtml()">打开支付页面</button>';
							    toolCode = toolCode +      '<button type="button" class="layui-btn layui-btn-primary layui-btn-sm"  onclick = "getQRCode(\'payPage\',\'page\')">局域网扫码访问</button>';
							    toolCode = toolCode +    '</div>';
							    toolCode = toolCode +  '</div>';
							    $(toolid).html(toolCode);
							break;
						}
		    			}
		    		  $(rsponseid).css("text-align","left");
		    		  var showData = JsonFormat(data.data);
		    		  $(rsponseid).html(showData);
		    	 }else{
		    		 $(rsponseid).html(data.msg + "参考信息：" + data.code);
		    	 }
			},
			complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
				console.log(status);
			　　　　if(status=='timeout'){//超时,status还有success,error等值的情况
			 　　　　　 ajaxReq.abort(); //取消请求
			  		  $(rsponseid).html("请求超时！");
			　　　　}else if(status=='success'){
					
			　　　　}else{
						layer.msg("系统异常,请联系管理员！",{icon:5});
						$(rsponseid).html("系统异常！");
			　　　　}
					$(requestId).val("1");
		 			$(requestId)[0].innerHTML = "请求";
			　　}
		}); 
}

function openHtml(){
	window.open("/payPage"); 
}

function getQRCode(text,payType){
	var formData = new FormData();
	formData.append("text", text);
	formData.append("payType", payType);
	$.ajax({
		type:"post",
		url:"/qrcode.show",
		data:formData,
		timeout:timeOut,//超时时间设置， 单位毫秒 
		processData: false,
		contentType: false,
		success:function(data){
			layer.alert(data, {
				  btn:0,
				  title: false, //不显示标题
				})
		},
		error: function(err){
			layer.msg("获取二维码异常,请联系管理员！",{icon:5});
		}
	}); 
}

function saveParam(appid,metid){
	var saveId = "#save" + appid + metid;
	var intabid = "inparams" + appid + metid;
	 if($(saveId)["0"].value != 1){
		 console.log($(saveId));
		 return;
	 }
	 $(saveId).val("0");
	 $(saveId)[0].innerHTML = "保存中";
	 var tableData = JSON.stringify(table.cache[intabid]);
	 var formData = new FormData();
		formData.append("tableData", tableData);
		formData.append("appid", appid);
	 $.ajax({
			type:"post",
			url:"/saveParam",
			data:formData,
			timeout:timeOut,//超时时间设置， 单位毫秒 
			processData: false,
			contentType: false,
			success:function(data){
				 $(saveId).val("1");
		    	 $(saveId)[0].innerHTML = "保存";
		    	 layer.msg("保存成功！",{icon:6});
			},
			error: function(err){
				layer.msg("系统异常,请联系管理员！",{icon:5});
				$(saveId).val("1");
	 			$(saveId)[0].innerHTML = "保存";
			}
		}); 
}


function encodeHtml(con){
    return con.replace(
        /"|&|'|<|>|[\x00-\x20]|[\x7F-\xFF]|[\u0100-\u2700]/g,
        function($0){
            var c = $0.charCodeAt(0);
            switch(c){
            case 13: return "<br />";
            case 32: return "&#160;";
            default: return "&#"+c+";";
            }
        }
    );
};