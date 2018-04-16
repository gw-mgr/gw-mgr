<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	var settleManageDataGrid;
	$(function() {
		settleManageDataGrid = $('#settleManageDataGrid')
				.datagrid(
						{
							url : '${path}/mgr/ExamineManage/settleDataGrid',
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : false,
							selectOnCheck : true,
							checkOnSelect : true,
							idField : 'id',
							sortName : 'F.UPDATE_TIME',
							sortOrder : 'desc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [
									{
										field : 'ck',
										checkbox : true
									},
									{
										width : '140',
										title : '服务商名称',
										field : 'merchantName',
										align : 'center',
										sortable : true
									},
									{
										width : '150',
										title : '服务范围',
										field : 'merchantTypeName',
										align : 'center',
										sortable : true
									},
									{
										width : '200',
										title : '授权区域',
										field : 'grantAreaName',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '未结算余额/元',
										field : 'balance',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											var i = parseFloat(value);
											return i / 100;
										}
									},
									{
										width : '100',
										title : '申请结算金额/元',
										field : 'tradeVlue',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											var i = parseFloat(value);
											return i / 100;
										}
									},
									{
										width : '90',
										title : '持卡人',
										field : 'bankHostName',
										align : 'center',
										sortable : true
									},
									{
										width : '170',
										title : '银行卡卡号',
										field : 'bankCardId',
										align : 'center',
										sortable : true
									},
									{
										width : '130',
										title : '申请时间',
										field : 'updateTime',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											value = value.toString();
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
										width : '80',
										title : '状态',
										field : 'status',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											switch (value) {
											case "01":
												return '申请中';
											case "02":
												return '已结算';
											}
										}
									},
									{
										field : 'action',
										title : '操作',
										align : 'center',
										width : 90,
										formatter : function(value, row, index) {
											var str = '';
											if (row.status == '01') {
												<shiro:hasPermission name="/tSettleManage/allow">
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="tSettleManage-easyui-linkbutton-update" data-options="plain:true,iconCls:\'fi-check icon-red\'" onclick="tSettleManageEditFun(\'{0}\');" ></a>',
																row.flowId);
												</shiro:hasPermission>
											}
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.tSettleManage-easyui-linkbutton-update')
										.linkbutton({
											text : '通过审核'
										});
							}
						});
	});
	// 通过审核
	function tSettleManageEditFun(flowId) {
		parent.$.messager.confirm('询问', '确定要通过结算吗？', function(flag) {
			if (flag) {
				progressLoad();
				$.post('${path}/mgr/ExamineManage/passSettle', {
					flowId : flowId,
					orderType : 'JS'
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						settleManageDataGrid.datagrid('reload');
					}
					progressClose();
					settleManageDataGrid.datagrid("unselectAll");
				}, 'JSON');
			}
		});
	}
	// 搜索
	function tSettleManageSearchFun() {
		settleManageDataGrid.datagrid('load', $
				.serializeObject($('#tSettleManageForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 120px; overflow: hidden; background-color: #fff">
		<div style="border: 0px solid; height: 25px; width: 1300px; background-color: #DBDBDB; font-size: 16px; margin-top: 2px; padding-left: 15px; padding-top: 7px">搜索</div>
		<form id="tSettleManageForm">
			<div style="width: 1200px; height: 40px; border: 0px solid red;">
				<div style="border: 0px solid; float: left; width: 200px; height: 35px; font-size: 16px; margin-top: 2px; margin-left: 50px; padding-left: 12px; padding-top: 10px">
					审核状态&nbsp;&nbsp;
					<select id="editStatus" name="status" class="easyui-combobox" data-options="width:100,height:25,editable:false,panelHeight:'auto'">
						<option value="01">申请中</option>
						<option value="02">已提现</option>
					</select>
				</div>
				<div style="border: 0px solid green; float: left; width: 200px; height: 35px; font-size: 16px; margin-top: 2px; padding-left: 12px; padding-top: 10px">
					关键字搜索&nbsp;&nbsp;
					<select id="editStatus" name="keywordType" class="easyui-combobox" data-options="width:100,height:25,editable:false,panelHeight:'auto'">
						<option value="merchantName">服务商名称</option>
					</select>
				</div>
				<div style="border: 0px solid green; float: left; width: 220px; height: 35px; font-size: 16px; margin-top: 0px; padding-left: 20px; padding-top: 12px">
					<input name="keywordInfo" type="text" style="width: 220px; height: 18px">
				</div>
				<div style="border: 0px solid green; float: left; width: 150px; height: 35px; font-size: 16px; margin-top: 2px; padding-left: 12px; padding-top: 8px">
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="tSettleManageSearchFun();">查询</a>
				</div>
			</div>
		</form>
		<br>
		<div style="float: left; border: 0px solid; width: 1300px; height: 35px; background-color: #DBDBDB; padding-left: 15px;">
			<div style="border: 0px solid; float: left; width: 750px; margin-top: 6px; font-size: 16px;">列表</div>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="height: 200px">
		<table id="settleManageDataGrid" data-options="fit:true,border:false"></table>
	</div>
</div>