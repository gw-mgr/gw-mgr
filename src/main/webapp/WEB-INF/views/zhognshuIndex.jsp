<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		// ajax请求数据
		$.ajax({
			type : "get",
			dataType : "json",
			url : "${path}/mgr/home/homePage/ajaxData",
			success : function(result) {
				$("#examineMerchant")
						.html("( " + result.examineMerchant + " )");
				$("#examineCash").html("( " + result.examineCash + " )");
				$("#examineSettle").html("( " + result.examineSettle + " )");
			}
		});
	});
</script>
<!-- 首页三模块 -->
<div align="center">
	<div style="align-self: center;">
		<div onclick="chooseIndexTab('merchantManage');" style="border: 1px solid; padding-top: 10px; background-color: #FFA500; width: 500px; height: 80px; border-radius: 6px 6px 6px 6px; margin-top: 80px; font-size: 24px;">
			待审核机构申请
			<br />
			<span id="examineMerchant" style="font-size: 24px; color: #FFFFFF; font-weight: bold"></span>
		</div>
		<div onclick="chooseIndexTab('cashManage');" style="border: 1px solid; padding-top: 10px; background-color: #48D1CC; width: 500px; height: 80px; border-radius: 6px 6px 6px 6px; margin-top: 30px; font-size: 24px">
			待审核提现
			<br />
			<span id="examineCash" style="font-size: 24px; color: #FFFFFF; font-weight: bold"></span>
		</div>
		<div onclick="chooseIndexTab('settleManage');" style="border: 1px solid; padding-top: 10px; background-color: #1E90FF; width: 500px; height: 80px; border-radius: 6px 6px 6px 6px; margin-top: 30px; font-size: 24px;">
			待审核结算
			<br />
			<span id="examineSettle" style="font-size: 24px; color: #FFFFFF; font-weight: bold"></span>
		</div>
	</div>
</div>
