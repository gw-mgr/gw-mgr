<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	var cashManageDataGrid;
	$(function() {
		cashManageDataGrid = $('#cashManageDataGrid')
				.datagrid(
						{
							url : '${path}/mgr/ExamineManage/cashDataGrid',
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
										width : '120',
										title : '姓名',
										field : 'userName',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '电话',
										field : 'telephone',
										align : 'center',
										sortable : true
									},
									{
										width : '120',
										title : '微信号',
										field : 'wechatNum',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '账户可用余额/元',
										field : 'balance',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											var i = parseFloat(value);
											return i / 100;
										}
									},
									{
										width : '90',
										title : '提现金额/元',
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
										width : '180',
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
										width : '120',
										title : '上月车险佣金/元',
										field : 'beforeMonthCXCommend',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											var i = parseFloat(value);
											return i / 100;
										}
									},
									{
										width : '60',
										title : '状态',
										field : 'status',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											switch (value) {
											case "01":
												return '申请中';
											case "02":
												return '已提现';
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
												<shiro:hasPermission name="/tCashManage/allow">
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="tCashManage-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-check icon-red\'" onclick="tCashManageEditFun(\'{0}\',\'0\');" ></a>',
																row.flowId,
																row.cheXian);
												</shiro:hasPermission>
											}
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.tCashManage-easyui-linkbutton-edit')
										.linkbutton({
											text : '通过审核'
										});
							}
						});
	});

	// 通过审核
	function tCashManageEditFun(flowId, cheXian) {
		/* if (cheXian == 0) {
			$.messager.alert('提示', '上月佣金为0元，不可提现！');
			return;
		} */
		parent.$.messager.confirm('询问', '确定要通过提现吗？', function(flag) {
			if (flag) {
				progressLoad();
				$.post('${path}/mgr/ExamineManage/passSettle', {
					flowId : flowId,
					orderType : 'TX'
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						tMerchantManageDataGrid.datagrid('reload');
					}
					progressClose();
					cashManageDataGrid.datagrid("unselectAll");
				}, 'JSON');
			}
		});
	}

	// 搜索
	function tCashManageSearchFun() {
		cashManageDataGrid.datagrid('load', $
				.serializeObject($('#tCashManageSearchForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 120px; overflow: hidden; background-color: #fff">
		<div style="border: 0px solid; height: 25px; width: 1300px; background-color: #DBDBDB; font-size: 16px; margin-top: 2px; padding-left: 15px; padding-top: 7px">搜索</div>
		<form id="tCashManageSearchForm">
			<div style="width: 1200px; height: 40px; border: 0px solid red;">
				<div style="border: 0px solid; float: left; width: 200px; height: 35px; font-size: 16px; margin-top: 2px; margin-left: 50px; padding-left: 12px; padding-top: 10px">
					审核状态&nbsp;&nbsp;
					<select id="editStatus" name="status" class="easyui-combobox" data-options="width:100,height:25,editable:false,panelHeight:'auto'">
						<option value="01">申请中</option>
						<option value="02">已提现</option>
					</select>
				</div>
				<div style="border: 0px solid green; float: left; width: 180px; height: 35px; font-size: 16px; margin-top: 2px; padding-left: 12px; padding-top: 10px">
					关键字搜索&nbsp;&nbsp;
					<select id="editStatus" name="keywordType" class="easyui-combobox" data-options="width:80,height:25,editable:false,panelHeight:'auto'">
						<option value="userName">姓名</option>
					</select>
				</div>
				<div style="border: 0px solid green; float: left; width: 220px; height: 35px; font-size: 16px; padding-left: 12px; padding-top: 12px">
					<input name="keywordInfo" type="text" style="width: 220px; height: 18px">
				</div>
				<div style="border: 0px solid green; float: left; width: 150px; height: 35px; font-size: 16px; margin-top: 2px; padding-left: 12px; padding-top: 8px">
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="tCashManageSearchFun();">查询</a>
				</div>
			</div>
		</form>
		<br>
		<div style="float: left; border: 0px solid; width: 1300px; height: 35px; background-color: #DBDBDB; padding-left: 15px;">
			<div style="border: 0px solid; float: left; width: 750px; margin-top: 6px; font-size: 16px;">列表</div>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="cashManageDataGrid" data-options="fit:true,border:false"></table>
	</div>
</div>
<div id="tCashManageToolbar" style="display: none;">
	<shiro:hasPermission name="/tCashManage/add">
		<a onclick="tCashManageAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-page-add'">添加</a>
	</shiro:hasPermission>
</div>