<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	var directSellManageDataGrid;
	$(function() {
		directSellManageDataGrid = $('#directSellManageDataGrid')
				.datagrid(
						{
							url : '${path}/mgr/ExamineManage/directSellDataGrid',
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : false,
							selectOnCheck : true,
							checkOnSelect : true,
							idField : 'id',
							sortName : 'UPDATE_TIME',
							sortOrder : 'desc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [
									{
										width : '240',
										title : '订单号',
										field : 'orderId',
										align : 'center',
										sortable : true
									},
									{
										width : '140',
										title : '下单时间',
										field : 'createTime',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											value = value + '';
											if (value == undefined
													|| value == ""
													|| value.length != 14) {
												return "";
											}
											var year = value.substring(0, 4);
											var month = value.substring(4, 6);
											var day = value.substring(6, 8);
											var hour = value.substring(8, 10);
											var min = value.substring(10, 12);
											var second = value
													.substring(12, 14);
											return year + "-" + month + "-"
													+ day + "&nbsp;&nbsp;"
													+ hour + ":" + min + ":"
													+ second;
										}
									},
									{
										width : '100',
										title : '业务员姓名',
										field : 'salesManName',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '电话',
										field : 'telphone',
										align : 'center',
										sortable : true
									},
									{
										width : '80',
										title : '总保费/元',
										field : 'zbf',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											value = (value / 100).toFixed(2);
											if (value == 0)
												return 0;
											return value;
										}
									},
									{
										width : '90',
										title : '直接推荐奖/元',
										field : 'zjRecommendAward',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											value = (value / 100).toFixed(2);
											if (value == 0)
												return 0;
											return value;
										}
									},
									{
										width : '90',
										title : '间接推荐奖/元',
										field : 'jjRecommendAward',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											value = (value / 100).toFixed(2);
											if (value == 0)
												return 0;
											return value;
										}
									},
									{
										width : '120',
										title : '录单员',
										field : 'recorder',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '状态',
										field : 'tjjyjCancle',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											switch (value) {
											case "01":
												return '<font color = \'red\'>推荐奖已取消</font>';
											case "02":
												return '正常';
											}
										}
									},
									{
										field : 'action',
										title : '操作',
										align : 'center',
										width : 180,
										formatter : function(value, row, index) {
											var str = '';
											<shiro:hasPermission name="/tuiJianJiang/lookOrderInfo">
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="tDirectSellManage-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-eye icon-blue\'" onclick="tDirectSellManageEditFun(\'{0}\',\'look\');" ></a>',
															row.orderId);
											</shiro:hasPermission>
											if (row.tjjyjCancle == '02') {
												<shiro:hasPermission name="/tuiJianJiang/cancel">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="tDirectSellManage-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-pencil icon-red\'" onclick="tDirectSellManageEditFun(\'{0}\',\'cancel\');" ></a>',
																row.orderId);
												</shiro:hasPermission>
											}
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.tDirectSellManage-easyui-linkbutton-edit')
										.linkbutton({
											text : '查看'
										});
								$('.tDirectSellManage-easyui-linkbutton-del')
										.linkbutton({
											text : '取消推荐奖'
										});
							}
						});
	});

	function tDirectSellManageEditFun(orderId, flag) {
		if (flag == 'cancel') {
			// 取消推荐奖
			parent.$.messager
					.confirm(
							'询问',
							'确定要取消该订单的推荐奖吗？',
							function(b) {
								if (b) {
									progressLoad();
									$
											.post(
													'${path}/mgr/ExamineManage/updateTuiJianJiangStatus',
													{
														orderId : orderId
													},
													function(result) {
														if (result.success) {
															parent.$.messager
																	.alert(
																			'提示',
																			result.msg,
																			'info');
															directSellManageDataGrid
																	.datagrid('reload');
														}
														progressClose();
														directSellManageDataGrid
																.datagrid("unselectAll");
													}, 'JSON');
								}
							});
		} else {
			// 查看订单详情
			alert('复用服务商系统订单详情');
		}
	}
	// 清除
	function tDirectSellManageCleanFun() {
		$('#tDirectSellManageSearchForm input').val('');
		directSellManageDataGrid.datagrid('load', {});
	}
	// 搜索
	function tDirectSellManageSearchFun() {
		directSellManageDataGrid.datagrid('load', $
				.serializeObject($('#tDirectSellManageSearchForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 120px; overflow: hidden; background-color: #fff">
		<div style="border: 0px solid; height: 25px; width: 1300px; background-color: #DBDBDB; font-size: 16px; margin-top: 2px; padding-left: 15px; padding-top: 7px">搜索</div>
		<form id="tDirectSellManageSearchForm">
			<div style="width: 1200px; height: 40px; border: 0px solid red;">
				<div style="border: 0px solid green; float: left; width: 180px; height: 35px; font-size: 16px; margin-top: 2px; padding-left: 50px; padding-top: 10px">
					关键字搜索&nbsp;&nbsp;
					<select id="editStatus" name="keywordType" class="easyui-combobox" data-options="width:80,height:25,editable:false,panelHeight:'auto'">
						<option value="SALES_MAN">业务员</option>
						<option value="RECORDER">录单员</option>
						<option value="ORDER_ID">订单号</option>
					</select>
				</div>
				<div style="border: 0px solid green; float: left; width: 220px; height: 35px; font-size: 16px; padding-left: 12px; padding-top: 12px">
					<input name="keywordInfo" type="text" style="width: 220px; height: 18px">
				</div>
				<div style="border: 0px solid green; float: left; width: 150px; height: 35px; font-size: 16px; margin-top: 2px; padding-left: 12px; padding-top: 8px">
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="tDirectSellManageSearchFun();">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="tDirectSellManageCleanFun();">清空</a>
				</div>
			</div>
		</form>
		<br>
		<div style="float: left; border: 0px solid; width: 1300px; height: 35px; background-color: #DBDBDB; padding-left: 15px;">
			<div style="border: 0px solid; float: left; width: 750px; margin-top: 6px; font-size: 16px;">列表</div>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="directSellManageDataGrid" data-options="fit:true,border:false"></table>
	</div>
</div>
