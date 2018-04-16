<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	var tOrderManageDataGrid;
	$(function() {
		tOrderManageDataGrid = $('#tOrderManageDataGrid')
				.datagrid(
						{
							url : '${path}/mgr/tOrderManage/dataGrid',
							striped : true,
							rownumbers : true,
							pagination : true,
							idField : 'id',
							sortName : 'UPDATE_TIME',
							sortOrder : 'desc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [
									{
										width : '150',
										title : '地区',
										field : 'merchantName',
										align : 'center',
										sortable : true
									},
									{
										width : '80',
										title : '昨日订单数',
										field : 'yesterdayTotalNum',
										align : 'center',
										sortable : true
									},
									{
										width : '80',
										title : '昨日成交数',
										field : 'yesterdayFinishedNum',
										align : 'center',
										sortable : true
									},
									{
										width : '80',
										title : '成交额/元',
										field : 'dealMoney',
										align : 'center',
										sortable : true
									},
									{
										width : '130',
										title : '（产生的）推荐奖/元',
										field : 'recommendMoney',
										align : 'center',
										sortable : true
									},
									{
										field : 'action',
										title : '操作',
										align : 'center',
										width : '80',
										formatter : function(value, row, index) {
											var str = '';
											<shiro:hasPermission name="/tMerchantManage/look">
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="tMerchantManage-easyui-linkbutton-look" data-options="plain:true,iconCls:\'fi-eye icon-blue\'" onclick="tMerchantManageLookFun(\'{0}\');" ></a>',
															row.id);
											</shiro:hasPermission>
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.tMerchantManage-easyui-linkbutton-look')
										.linkbutton({
											text : '查看'
										});
							}
						});
		// ajax请求数据
		$.ajax({
			type : "get",
			dataType : "json",
			url : "${path}/mgr/tOrderManage/ajaxData",
			success : function(result) {
				$("#orderTotalNum").html(result.orderTotalNum);
				$("#orderTotalMoney").html(result.orderTotalMoney + " W");
				$("#yesterdayOrderTotalNum")
						.html(result.yesterdayOrderTotalNum);
				$("#yesterdayOrderTotalMoney").html(
						result.yesterdayOrderTotalMoney);
				$("#CCBXOrderTotalNum").html(result.CCBXOrderTotalNum);
				$("#RSBXOrderTotalNum").html(result.RSBXOrderTotalNum);
				$("#DKYWOrderTotalNum").html(result.DKYWOrderTotalNum);
				$("#LCCPOrderTotalNum").html(result.LCCPOrderTotalNum);
				$("#ESQCOrderTotalNum").html(result.ESQCOrderTotalNum);
				$("#CWDBOrderTotalNum").html(result.CWDBOrderTotalNum);
				$("#QCMROrderTotalNum").html(result.QCMROrderTotalNum);
			}
		});
	});

	function chooseOrderTab(tabContent) {
		var tabName = "订单列表";
		var href = "${path}/mgr/tOrderManage/orderInfo?orderType=" + tabContent;
		//var content = '<iframe src="${path}/tOrderManage/orderInfo" frameborder="0" style="border:0;width:100%;height:99.5%;"></iframe>';
		// 添加一个新的标签页面板（tab panel）
		if ($("#index_tabs").tabs('exists', tabName)) {
			$('#index_tabs').tabs('select', tabName);
		} else {
			$('#index_tabs').tabs('add', {
				closable : true,
				title : tabName,
				closable : true,
				border : false,
				selected : true,
				fit : true,
				href : href
				//content : content

			});
		}
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" style="overflow: hidden; padding: 3px;">
		<div style="border: 0px solid; width: 1300px; height: 40px; margin: 5px">
			<div style="font-weight: bold; border: 0px solid; float: left; width: 250px; height: 40px; margin-left: 120px; font-size: 24px">
				订单总数：
				<span id="orderTotalNum" style="font-size: 24px; color: #000000; font-weight: bold"></span>
			</div>
			<div style="font-weight: bold; border: 0px solid; float: left; width: 250px; height: 40px; margin-left: 50px; font-size: 24px">
				成交总量：
				<span id="orderTotalMoney" style="font-size: 24px; color: #000000; font-weight: bold"></span>
			</div>
			<div style="font-weight: bold; border: 0px solid; float: left; width: 250px; height: 40px; margin-left: 50px; font-size: 24px">
				昨日订单数：
				<span id="yesterdayOrderTotalNum" style="font-size: 24px; color: #000000; font-weight: bold"></span>
			</div>
			<div style="font-weight: bold; border: 0px solid; float: left; width: 250px; height: 40px; margin-left: 50px; font-size: 24px">
				昨日成交数：
				<span id="yesterdayOrderTotalMoney" style="font-size: 24px; color: #000000; font-weight: bold"></span>
			</div>
		</div>
		<div style="border: 0px solid; width: 1300px; height: 90px; margin-left: 5px;">
			<div id="CaiChanDiv" onclick="chooseOrderTab('CCBX');" style="background-color: #FF3399; line-height: 30px; text-align: center; border-radius: 10px; border: 0px solid; float: left; width: 600px; height: 70px; padding-top: 10px; margin-left: 30px; font-size: 15px">
				财产保险
				<br>
				(&nbsp;昨日成交数：
				<span id="CCBXOrderTotalNum" style="font-size: 18px; color: #000000; font-weight: bold"></span>
				&nbsp;)
			</div>
			<div id="RenShouDiv" onclick="chooseOrderTab('RSBX');" style="background-color: #FF3399; line-height: 30px; text-align: center; border-radius: 10px; border: 0px solid; float: left; width: 600px; height: 70px; padding-top: 10px; margin-left: 35px; font-size: 15px">
				人寿保险
				<br>
				(&nbsp;昨日成交数：
				<span id="RSBXOrderTotalNum" style="font-size: 18px; color: #000000; font-weight: bold"></span>
				&nbsp;)
			</div>
		</div>
		<div style="border: 0px solid; width: 1300px; height: 80px; margin-left: 5px; margin-top: 10px">
			<div id="DaiKuanDiv" onclick="chooseOrderTab('DKYW');" style="background-color: #FF8000; line-height: 30px; text-align: center; border-radius: 5px; border: 0px solid; float: left; width: 180px; height: 70px; padding-top: 10px; margin-left: 30px; font-size: 15px">
				贷款业务
				<br>
				(&nbsp;昨日成交数：
				<span id="DKYWOrderTotalNum" style="font-size: 18px; color: #000000; font-weight: bold"></span>
				&nbsp;)
			</div>
			<div id="LiCaiDiv" onclick="chooseOrderTab('LCCP');" style="background-color: #00CC66; line-height: 30px; text-align: center; border-radius: 5px; border: 0px solid; float: left; width: 180px; height: 70px; padding-top: 10px; margin-left: 85px; font-size: 15px">
				理财产品
				<br>
				(&nbsp;昨日成交数：
				<span id="LCCPOrderTotalNum" style="font-size: 18px; color: #000000; font-weight: bold"></span>
				&nbsp;)
			</div>
			<div id="ErShouCheDiv" onclick="chooseOrderTab('ESQC');" style="background-color: #80FF00; line-height: 30px; text-align: center; border-radius: 5px; border: 0px solid; float: left; width: 180px; height: 70px; padding-top: 10px; margin-left: 85px; font-size: 15px">
				二手汽车
				<br>
				(&nbsp;昨日成交数：
				<span id="ESQCOrderTotalNum" style="font-size: 18px; color: #000000; font-weight: bold"></span>
				&nbsp;)
			</div>
			<div id="CheWuDiv" onclick="chooseOrderTab('CWDB');" style="background-color: #00FF7F; line-height: 30px; text-align: center; border-radius: 5px; border: 0px solid; float: left; width: 180px; height: 70px; padding-top: 10px; margin-left: 85px; font-size: 15px">
				车务代办
				<br>
				(&nbsp;昨日成交数：
				<span id="CWDBOrderTotalNum" style="font-size: 18px; color: #000000; font-weight: bold"></span>
				&nbsp;)
			</div>
			<div id="MeiRongDiv" onclick="chooseOrderTab('QCMR');" style="background-color: #0080FF; line-height: 30px; text-align: center; border-radius: 5px; border: 0px solid; float: left; width: 180px; height: 70px; padding-top: 10px; margin-left: 85px; font-size: 15px">
				汽车美容
				<br>
				(&nbsp;昨日成交数：
				<span id="QCMROrderTotalNum" style="font-size: 18px; color: #000000; font-weight: bold"></span>
				&nbsp;)
			</div>
		</div>
		<div style="border: 0px solid; background-color: #C0C0C0; font-weight: bold; width: 1300px; height: 25px; margin-top: 3px; font-size: 15px; padding-left: 20px; padding-top: 5px">保险订单数据:</div>
		<div data-options="region:'center',border:false" style="height: 325px; border: 0px solid;">
			<table id="tOrderManageDataGrid" data-options="fit:true,border:false"></table>
		</div>
	</div>
</div>