//测试
//window.base_url = 'http://119.29.5.131:8080/gw-mgr';

//window.base_url = 'http://192.168.1.19:80/gw-mgr';

window.base_url = 'http://119.29.5.131:8080/gw-mgr';

document.write('<link href="../../css/mui.picker.min.css" rel="stylesheet" />')
document.write("<script src='../../js/mui.pullToRefresh.js' type='text/javascript'></script>");
document.write("<script src='../../js/mui.pullToRefresh.material.js' type='text/javascript'></script>");
document.write("<script src='../../js/mui.picker.min.js' type='text/javascript'></script>");
document.write("<script src='../../js/mui.picker.js' type='text/javascript'></script>");
document.write("<script src='../../js/mui.poppicker.js' type='text/javascript'></script>");
document.write("<script src='../../js/vue.min.js' type='text/javascript'></script>");
document.write("<script src='../../js/city.data-3.js' type='text/javascript'></script>");
document.write("<script src='../../js/jsondata.js' type='text/javascript'></script>");
/*




sessionStorage.setItem('merchantId','3dd0ad5f995f4caabcd6c027d95c4d67');
sessionStorage.setItem('userId','0a07e034d5144d49a5e3052965e35905');
//sessionStorage.setItem('userId','0a07e034d5144d49a5e3052965e35905');
//sessionStorage.setItem('userId','19');//车务代办  


console.log("merchantId>>>>>>>>>" + sessionStorage.getItem('merchantId'));

console.log("userId>>>>>>>>>" + sessionStorage.getItem('userId'));
*/
var $$ = jQuery.noConflict();

var timemill;
var mac_key = sessionStorage.getItem('mac_key');

function generateMac() {
	var $$ = jQuery.noConflict();
	timemill = new Date().getTime();
	var length = mac_key.length - 10;
	var index = (timemill * 1) % length;
	var key = mac_key.substring(index, mac_key.length);
	var mac = $$.md5(key);
	return mac;
}

/*
var timemill;
var mac_key = sessionStorage.getItem('mac_key');
function generateMac() {
	var $$ = jQuery.noConflict();
	timemill = sessionStorage.getItem('timemill');
	var length = mac_key.length - 10;
	var index = (timemill*1) % length;
	var key = mac_key.substring(index, mac_key.length);
	var mac = $$.md5(key);
	return mac;
}*/
var areaData;
$$(function(){
	getAreaInfo();
});
/**
 * 
 * 获取城市列表
 */
function getAreaInfo(){
	if(isNull(sessionStorage.getItem("areaData"))){
		$$.ajax({                
			type:   "POST",
			url: base_url + '/common/getAreaInfo/',
            data: {},
			dataType: 'json',
				success:   function(response)  {
				console.log(response);
				if(response.success) {
					sessionStorage.setItem("areaData",JSON.stringify(response.obj));
					areaData = JSON.parse(sessionStorage.getItem("areaData"));
					console.log(areaData)
				} else {
					sessionStorage.setItem("areaData",JSON.stringify(cityData3));
					areaData = JSON.parse(sessionStorage.getItem("areaData"));
				}
			},
			error:   function(result)  {
				sessionStorage.setItem("areaData",JSON.stringify(cityData3));
				areaData = JSON.parse(sessionStorage.getItem("areaData"));
			}
		 });
	}else{
		areaData = JSON.parse(sessionStorage.getItem("areaData"));
	}
}
$$(function($) {
	// 备份jquery的ajax方法    
	var _ajax = $.ajax;
	// 重写ajax方法，先判断登录在执行success函数   
	$.ajax = function(opt) {
		console.log("----------------------")
		console.log(opt);
		var _data = JSON.stringify(opt.data);
		_data = _data.substring(2,_data.length-2);
		_data = _data.replace(/\","/g, '&');
		_data = _data.replace(/\":"/g, '=');
		//console.log(opt.url+"?"+_data);
		
		mac_key = sessionStorage.getItem('mac_key');
		if(!isNull(mac_key)) {

			var businessId = sessionStorage.getItem('businessId');
			if(isNull(sessionStorage.getItem('businessId'))) businessId = "";

			var loginTel = sessionStorage.getItem('loginTel');
			if(isNull(sessionStorage.getItem('loginTel'))) loginTel = "";

			var token = sessionStorage.getItem('token');
			if(isNull(sessionStorage.getItem('token'))) token = "";
			opt.url = opt.url + "?mac=" + generateMac() + "&time_mills=" + timemill + "&channel_mac=" + businessId + "&cooperator_user_id=" + loginTel + "&token=" + token;
		}
		var _success = opt && opt.success || function(a, b) {};
		var _error = opt && opt.error || function(a, b) {
			var resp_msg;
			if(a.responseText) {
				var resps = a.responseText.split(',');
				for(var i in resps) {
					if(resps[i].indexOf('resp_msg') > -1) {
						resp_msg = resps[i].split(":")[1];
						break;
					}
				}
			}
			_errors();
		};
		var _opt = $.extend(opt, {
			success: function(data, textStatus) {
				console.log(data)
				console.log("----------------------")
				//alert(data.resp_result)
				// 如果token失效则返回到登录页
				if(data.resp_result == 'token_fail') {
					hide();
					/*mui.alert('<p><span class="mui-icon mui-icon-info red mui-h4" style="font-size: 80px;"></span></p>糟糕，系统连接失败，请重新登录！', '温馨提示',"立即登录", function() {
						window.location.href='../login.html';
					});*/

				} else if(data.resp_result == 'mac_fail') {
					/*location.href = '../fail.html';*/

				} else {
					_success(data, textStatus);
				}
			},
			error: _error
		});
		return _ajax(_opt);
	};
});
// 毫秒时间转化日日期格式
function unixToDate(unixTime, fmt) {
	var time = new Date(unixTime);
	return formatDate(time, fmt);
}

function formatDate(date, fmt) {
	var o = {
		"M+": date.getMonth() + 1, //月份 
		"d+": date.getDate(), //日 
		"h+": date.getHours(), //小时 
		"m+": date.getMinutes(), //分 
		"s+": date.getSeconds(), //秒 
		"q+": Math.floor((date.getMonth() + 3) / 3), //季度 
		"S": date.getMilliseconds() //毫秒 
	};
	if(!fmt) {
		fmt = "yyyy-MM-dd hh:mm:ss";
	}
	if(/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
	for(var k in o)
		if(new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

function getYear(sss){
	var sss=sss+"";
	return sss.substr(0,4)+"-"+sss.substr(4,2)+"-"+sss.substr(6,2);
}


function getData(sss){
	var sss=sss+"";
	return sss.substr(0,4)+"-"+sss.substr(4,2)+"-"+sss.substr(6,2) +" "+sss.substr(8,2)+":"+sss.substr(10,2)+":"+sss.substr(12,2);
}
function getTime(sss){
	var sss=sss+"";
	return sss.substr(8,2)+":"+sss.substr(10,2)+":"+sss.substr(12,2);
}
//获取表单值
function getFormValues(form) {
	var elements = {};
	var element;
	var tagElements = form.getElementsByTagName('input');
	for(var j = 0; j < tagElements.length; j++) {
		element = tagElements[j];
		switch(element.type.toLowerCase()) {
			case 'checkbox':
			case 'radio':
				if(element.checked) {
					elements[element.name] = element.value;
				}
				break;
			case 'number':
				elements[element.name] = element.value - 0;
				break;
			default:
				elements[element.name] = element.value;
				break;
		}
	}
	tagElements = form.getElementsByTagName('select');
	for(var j = 0; j < tagElements.length; j++) {
		element = tagElements[j];
		elements[element.name] = element.value;
	}
	tagElements = form.getElementsByTagName('textarea');
	for(var j = 0; j < tagElements.length; j++) {
		element = tagElements[j];
		elements[element.name] = element.value;
	}
	return elements;
}
//设置表单值
function setFormValues(form, values) {
	var elements = {};
	var	element;
	var tagElements = form.getElementsByTagName('input');
	for(var j = 0; j < tagElements.length; j++) {
		element = tagElements[j];
		switch(element.type.toLowerCase()) {
			case 'checkbox':
				if(values[element.name]) {
					element.checked = 'checked';
				}
				break;
			case 'radio':
				if(element.value === values[element.name]){
					element.checked = 'checked';
				}
				break;
			case 'file':
				break;
			default:
				if(!isNull(values[element.name])) {
					element.value = values[element.name];
				}
				break;
		}
	}
	tagElements = form.getElementsByTagName('select');
	for(var j = 0; j < tagElements.length; j++) {
		element = tagElements[j];
		if(typeof values[element.name] != 'undefined') {
			element.value = values[element.name];
		}
	}
	tagElements = form.getElementsByTagName('textarea');
	for(var j = 0; j < tagElements.length; j++) {
		element = tagElements[j];
		if(typeof values[element.name] != 'undefined') {
			element.value = values[element.name];
		}
	}
}
//是否为空
function isNull(str) {
	return (/^\s*$/.test(str)) || (str == null) || (str === 'null') || (typeof str === 'undefined') || (str === 'undefined');
}
//获取URL参数
function getUrlParam(key) {
	var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) {
		return decodeURI(r[2]);
	} else {
		return null;
	}
}

function logout() {
	localStorage.clear();
	sessionStorage.clear();
	location.href = 'login.html';
}

function _errors() {
	document.getElementsByClassName("mui-content")[0].innerHTML = '<div class="mui-content"><div class="data">' +
		'<div class="data-icon icon-network"></div>' +
		'<p class="data-title gray">糟糕，好像出错了！</p>' +
		'</div><div class="line"></div><div class="line"></div>' +
		'<div class="line"></div><div class="line"></div>' +
		'<div class="mui-text-center"><button type="button" class="mui-btn mui-btn-outlined" onclick="location.reload() ">刷新页面</button></div></div>';
}
$$("#check").click(function() {
	$$(".check").toggleClass('checkin');
	if($$(this).hasClass('checkin')) {
		$$("#apply").attr("disabled", "disabled");
	} else {
		$$("#apply").removeAttr("disabled");
	}
});

function Time() {
	var i = 59;
	var a = setInterval(function() {
		$$('#sendcode').text(i + 'S').attr('disabled', 'disabled');
		$$("#sendcode").addClass("sendcodein");
		i--;
		if(i == 1) {
			i = 59;
			clearInterval(a);
			$$('#sendcode').text('获取验证码').removeAttr('disabled');
			$$("#sendcode").removeClass("sendcodein");
		}
	}, 1000)
}
function checkcard(ID) {
	if(typeof ID !== 'string') {
		msgs = '请填写正确的身份证号';
		return false;
	}
	var city = {
		11: "北京",
		12: "天津",
		13: "河北",
		14: "山西",
		15: "内蒙古",
		21: "辽宁",
		22: "吉林",
		23: "黑龙江 ",
		31: "上海",
		32: "江苏",
		33: "浙江",
		34: "安徽",
		35: "福建",
		36: "江西",
		37: "山东",
		41: "河南",
		42: "湖北 ",
		43: "湖南",
		44: "广东",
		45: "广西",
		46: "海南",
		50: "重庆",
		51: "四川",
		52: "贵州",
		53: "云南",
		54: "西藏 ",
		61: "陕西",
		62: "甘肃",
		63: "青海",
		64: "宁夏",
		65: "新疆",
		71: "台湾",
		81: "香港",
		82: "澳门",
		91: "国外"
	};
	var birthday = ID.substr(6, 4) + '/' + Number(ID.substr(10, 2)) + '/' + Number(ID.substr(12, 2));
	var d = new Date(birthday);
	var newBirthday = d.getFullYear() + '/' + Number(d.getMonth() + 1) + '/' + Number(d.getDate());
	var currentTime = new Date().getTime();
	var time = d.getTime();
	var arrInt = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
	var arrCh = ['1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'];
	var sum = 0,
		i, residue;
	if(!/^\d{17}(\d|x)$/i.test(ID)) {
		msgs = '请填写正确的身份证号';
		return false;
	}
	if(city[ID.substr(0, 2)] === undefined) {
		msgs = '请填写正确的身份证号';
		return false;
	}
	if(time >= currentTime || birthday !== newBirthday) {
		msgs = '请填写正确的身份证号';
		return false;
	}
	for(i = 0; i < 17; i++) {
		sum += ID.substr(i, 1) * arrInt[i];
	}
	residue = arrCh[sum % 11];
	if(residue !== ID.substr(17, 1)) {
		msgs = '请填写正确的身份证号';
		return false;
	}
	var sex = ID.substr(16, 1) % 2 ? "02" : "01";

	var myDate = new Date();
	var month = myDate.getMonth() + 1;
	var day = myDate.getDate();
	var age = myDate.getFullYear() - ID.substring(6, 10) - 1;
	if(ID.substring(10, 12) < month || ID.substring(10, 12) == month && ID.substring(12, 14) <= day) {
		age++;
	}
	console.log(city[ID.substr(0, 2)] + "," + birthday + "," + (ID.substr(16, 1) % 2 ? " 男" : "女") + "," + age);
	return true;
}

function checkPhone(phone) {
	if(/^1[3|4|5|7|8][0-9]{9}$/.test(phone)) {
		return false;
	} else {
		return true;
	}
}

function hide() {
	$$("#submit").remove();
	 $$('body').removeAttr("style")
}

function show(val) {
	if(isNull(val)) {
		val = '正在加载';
	}
	$$('body').append('<div class="submit" id="submit"><div id="audio_btn" class="rotate"><p class="mui-text-center"><span class="mui-spinner"></span></p><p class="mui-text-center black mui-h6">' + val + '</p></div></div>');
	$$('body').css('overflow', 'hidden');
}

function _href(url) {
	window.location.href = url;
}

function _showcar() {
	$$('body').append('<div id="cartextcon" class="cartextcon mui-popover mui-popover-action mui-popover-bottom bankcard">' +
		'<div class="mui-poppicker-header">' +
		'<a class="mui-btn mui-poppicker-btn-cancel" data-value="0"></a>' +
		'<h1 class="poppicker-title">请选择</h1>' +
		'<a class="mui-btn mui-poppicker-btn-ok" data-value="1">确认</a>' +
		'<div class="mui-poppicker-clear"></div>' +
		'</div>' +
		'<ul class="iphone-keyboard cbckey mui-table-view">' +
		'<li>' +
		'<button type="button">京</button>' +
		'<button type="button">沪</button>' +
		'<button type="button">津</button>' +
		'<button type="button">渝</button>' +
		'<button type="button">冀</button>' +
		'<button type="button">晋</button>' +
		'<button type="button">蒙</button>' +
		'<button type="button">辽</button>' +
		'<button type="button">吉</button>' +
		'<button type="button">黑</button>' +
		'</li>' +
		'<li>' +
		'<button type="button">苏</button>' +
		'<button type="button">浙</button>' +
		'<button type="button">皖</button>' +
		'<button type="button">闽</button>' +
		'<button type="button">赣</button>' +
		'<button type="button">鲁</button>' +
		'<button type="button">豫</button>' +
		'<button type="button">鄂</button>' +
		'<button type="button">湘</button>' +
		'<button type="button">粤</button>' +
		'</li>' +
		'<li>' +
		'<button type="button">桂</button>' +
		'<button type="button">琼</button>' +
		'<button type="button">川</button>' +
		'<button type="button">贵</button>' +
		'<button type="button">云</button>' +
		'<button type="button">藏</button>' +
		'<button type="button">陕</button>' +
		'<button type="button">甘</button>' +
		'<button type="button">青</button>' +
		'</li>' +
		'<li>' +
		'<button type="button">宁</button>' +
		'<button type="button">新</button>' +
		'<button type="button">台</button>' +
		'</li>' +
		'</ul>' +
		'</div>');
	$$('.iphone-keyboard li button').click(function() {
		$$(this).addClass('on').siblings().removeClass('on');
	});
	mui('body').on('tap', '.mui-popover-action a', function() {
		var a = this,
			parent;
		//根据点击按钮，反推当前是哪个actionsheet
		for(parent = a.parentNode; parent != document.body; parent = parent.parentNode) {
			if(parent.classList.contains('mui-popover-action')) {
				break;
			}
		}
		if($$(a).attr("data-value") === "1") {
			mui('.iphone-keyboard button').each(function() {
				if($$(this).hasClass("on")) {
					$$(".cartext").html($$(this).html())
				}
			});
		} else {}
		//关闭
		mui('#' + parent.id).popover('toggle');

	});
}