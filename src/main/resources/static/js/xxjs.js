/**
 * 
 */
/*layui.config({
    base: '/layui/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use('index');*/
  
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
				timeout:5000,//超时时间设置， 单位毫秒
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
				timeout:5000,//超时时间设置， 单位毫秒
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
			 	return '<span class="' + cls + '">' + match + '</span>';
		 });
}
