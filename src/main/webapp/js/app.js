var $app = (function(){
	var App = {
		loginUrl:'/jroa/login.do'
		,imgUpload:"/jroa/image/upload.do"
		,sysAppid:"web"
		,reqKey:"97a7577b766d"
			};

	App.getContext = function(){
		
		var context = window;	
		if(window.top)context = window.top;
		return context;
	}

	App.request = function(url,call,param){
		
		var p = getReqParam(param?param.param:null);
		
		$.ajax({ 
			type: "post", 
			dataType:"json",
			url: url, 
			data:p,	
			beforeSend: function(XMLHttpRequest){ 
			
			}, 
			success: function(data, status){
				if(data&&data.status==5)window.location.href = $app.loginUrl;
				if(data.sid)setCookie("sysSid",data.sid);
				call(data,status);
			}, 
			complete: function(XMLHttpRequest, textStatus){ 
			if(param&&param.complete)
				param.complete(XMLHttpRequest, textStatus);
			}, 
			error: function(){ 
				if(param&&param.error)param.error();
			} 
			}); 
		
	}
	
	function getReqParam(param){
		if(!param)param={};
		param.sysAppid = App.sysAppid;
		param.sysSid = getCookie("sysSid");
		param.sysVersion="1"
		param.timestamp=new Date().getTime();
		param.sysSign = md5(asciiSort(param)+"&reqKey="+App.reqKey);
		return param;
	}
	
	
	
	function asciiSort(param){
		var keys = [];
		for ( var i in param) {
			keys.push(i);
		}
		keys = keys.sort(function(a,b){return a>b;}); 
		var str = "";
		for (var i = 0; i < keys.length; i++) {
			if(param[keys[i]])
			str+=keys[i]+"="+param[keys[i]]+"&";
		}
		console.log(str);
		
		return str.substring(0,str.length-1);
		
		
	}
	
	function getCookie(name)
	{
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
	return unescape(arr[2]);
	else
	return null;
	}
	
	function setCookie(name,value)
	{
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	}
	
	return App;
	
})();
