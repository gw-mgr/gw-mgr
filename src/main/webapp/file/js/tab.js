/*!
 * Bootstrap v3.2.0 (http://getbootstrap.com)
 * Copyright 2011-2014 Twitter, Inc.
 * Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
 */
$(document).ready(function(){
 // 改为鼠标移上的事件只需把click改为mousemove
 $(".fortab").click(function(){
 	var number=$(".fortab").index(this);
 	$(this).addClass("checked");
   $(this).siblings().removeClass("checked");
   $(".tablelist:eq("+number+")").show();
   $(".tablelist:eq("+number+")").siblings().hide();
 });
 
 $(".forcxtab").click(function(){
	 	var number=$(".forcxtab").index(this);
	 	$(this).addClass("checked");
	   $(this).siblings().removeClass("checked");
	   $(".cxtablelist:eq("+number+")").show();
	   $(".cxtablelist:eq("+number+")").siblings().hide();
	 });
 $(".forsxtab").click(function(){
	 	var number=$(".forsxtab").index(this);
	 	$(this).addClass("checked");
	   $(this).siblings().removeClass("checked");
	   $(".sxtablelist:eq("+number+")").show();
	   $(".sxtablelist:eq("+number+")").siblings().hide();
	 });
});