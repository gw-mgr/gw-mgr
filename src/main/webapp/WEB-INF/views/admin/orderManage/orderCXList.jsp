<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	var tCXOrderManage;
	$(function() {
		tCXOrderManage = $('#tCXOrderManage')
				.datagrid(
						{
							url : '${path}/mgr/tOrderManage/orderList?orderType=ROOTCC',
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : false,
							selectOnCheck : true,
							checkOnSelect : true,
							idField : 'orderId',
							sortName : 't.CREATE_TIME',
							sortOrder : 'desc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [
									{
										width : '250',
										title : '订单号',
										field : 'orderId',
										align : 'center',
										sortable : true
									},
									{
										width : '130',
										title : '下单日期',
										field : 'createTime',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											value = value + '';
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
										width : '10',
										title : '业务类型',
										field : 'orderType',
										align : 'center',
										hidden : true
									},
									{
										width : '100',
										title : '业务类型',
										field : 'orderTypeName',
										align : 'center',
										sortable : true
									},
									{
										width : '90',
										title : '客户姓名',
										field : 'customerName',
										align : 'center',
										sortable : true
									},
									{
										width : '90',
										title : '出单员',
										field : 'operatorName',
										align : 'center',
										sortable : true
									},
									{
										width : '10',
										title : '业务员',
										field : 'salesMan',
										align : 'center',
										hidden : true
									},
									{
										width : '180',
										title : '所属服务商',
										field : 'merchantName',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '地区',
										field : 'province',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '状态',
										field : 'orderFlag',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											switch (value) {
											case "01":
												return '<font color=\'red\'>未成交<font>';
											case "02":
												return '已成交';
											case "03":
												return '已删除';
											case "04":
												return '<font color=\'red\'>顾客申请重新报价<font>';
											}
										}
									},
									{
										field : 'action',
										title : '操作',
										align : 'center',
										width : 80,
										formatter : function(value, row, index) {
											var str = '';
											<shiro:hasPermission name="/tMerchantManage/look">
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="tCXOrderManage-easyui-linkbutton-look" data-options="plain:true,iconCls:\'fi-eye icon-blue\'" onclick="tOrderInfoLookFun(\'{0}\');" ></a>',
															row.id);
											</shiro:hasPermission>
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.tCXOrderManage-easyui-linkbutton-look')
										.linkbutton({
											text : '查看'
										});
							}
						});
	});

	// 查看
	function tOrderInfoLookFun(id) {
		alert('调用服务商系统');
		return;
		parent.$.messager.confirm('询问', '您是否要删除当前角色？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path}/mgr/tMerchantManage/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						tCXOrderManage.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
		tCXOrderManage.datagrid("unselectAll");
	}
	// 清除
	function tCXOrderManageCleanFun() {
		$('#tCXOrderManageSearchForm input').val('');
		tCXOrderManage.datagrid('load', {});
	}
	// 搜索
	function tCXOrderManageSearchFun() {
		tCXOrderManage.datagrid('load', $
				.serializeObject($('#tCXOrderManageSearchForm')));
		tCXOrderManage.datagrid("unselectAll");
	}
	// 导出EXCEL
	function exmportExcelFun() {
		var createStartTime = "";
		if ($('#createStartTime').val()) {
			createStartTime = $("#createStartTime").val();
		}
		var createEndTime = "";
		if ($('#createEndTime').val()) {
			createEndTime = $("#createEndTime").val();
		}
		var province = "";
		if ($('#province').val()) {
			province = $("#province").val();
		}
		var city = "";
		if ($('#city').val()) {
			city = $("#city").val();
		}
		var country = "";
		if ($('#country').val()) {
			country = $("#country").val();
		}
		var merchantType = "";
		if ($('#merchantType').val()) {
			merchantType = $("#merchantType").val();
		}
		var keywordInfo = "";
		if ($('#keywordInfo').val()) {
			keywordInfo = $("#keywordInfo").val();
		}
		parent.$.messager.confirm('询问', "确认批量导出？", function(b) {
			if (b) {
				progressLoad();
				$.get('${path}/mgr/tMerchantManage/exportExcel', {
					createStartTime : createStartTime,
					createEndTime : createEndTime,
					province : province,
					city : city,
					country : country,
					merchantType : merchantType,
					keywordInfo : keywordInfo
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						tMerchantManageDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 160px; overflow: hidden; background-color: #fff">
		<div style="border: 0px solid; height: 25px; width: 1300px; background-color: #DBDBDB; font-size: 16px; margin-top: 2px; padding-left: 15px; padding-top: 7px">搜索</div>
		<form id="tCXOrderManageSearchForm">
			<div style="width: 1200px">
				<div style="border: 0px solid; float: left; width: 350px; height: 35px; font-size: 16px; margin-top: 2px; margin-left: 50px; padding-left: 12px; padding-top: 10px">
					创建时间&nbsp;&nbsp; <input name="createStartTime" class="easyui-datebox" style="width: 100px"> &nbsp;&nbsp;至&nbsp;&nbsp; <input name="createEndTime" class="easyui-datebox" style="width: 100px">
				</div>
				<div style="border: 0px solid; float: left; height: 35px; font-size: 16px; margin-top: 2px; margin-left: 10px; padding-top: 10px">服务商地址&nbsp;&nbsp;</div>
				<div style="border: 0px solid; float: left; width: 600px; height: 35px; font-size: 16px; margin-top: 5px; margin-left: 10px; padding-top: 10px">
					<div style="float: left">
						<input name="province" style="width: 100px; height: 25px; font-size: 13px" class="easyui-combobox" data-options="panelHeight: '100px',valueField:'id',textField:'name',url:'${path}/mgr/tOrderManage/areaQuery'">
					</div>
				</div>
			</div>
			<hr width=1310px align="left"></hr>
			<div style="width: 1200px; border: 0px solid;">
				<div style="border: 0px solid; float: left; width: 250px; height: 30px; font-size: 16px; margin-left: 50px; padding-left: 12px; padding-top: 1px">
					订单类型&nbsp;&nbsp; <input id="orderType" name="orderTypeQuery" style="width: 100px; height: 25px; font-size: 13px" class="easyui-combobox" data-options="panelHeight: 'auto',valueField:'categoryId',textField:'categoryName',url:'${path}/mgr/tOrderManage/orderTypeListOfCX'">
				</div>
				<div style="border: 0px solid; float: left; width: 200px; height: 25px; font-size: 16px; padding-top: 1px;">
					关键字搜索&nbsp;&nbsp;
					<select name="keywordType" style="width: 80px; height: 25px; font-size: 13px">
						<option value="t.CAR_NUM">车牌号</option>
						<option value="t.CAR_OWNER">车主</option>
						<option value="s.user_name">业务员</option>
						<option value="t.RECORDER">出单员</option>
						<option value="f.MERCHANT_NAME">所属服务商</option>
						<option value="t.ORDER_ID">订单号</option>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				<div style="border: 0px solid; padding-top: 1px; height: 25px">
					<input placeholder="关键字搜索" name="keywordInfo" type="text" style="width: 280px; height: 18px">
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="tCXOrderManageSearchFun()">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="tCXOrderManageCleanFun();">清空</a>
				</div>
			</div>
		</form>
		<br>
		<div style="float: left; border: 0px solid; width: 1300px; height: 40px; background-color: #DBDBDB; padding-left: 15px;">
			<div style="border: 0px solid; float: left; width: 750px; margin-top: 6px; font-size: 16px;">订单列表</div>
			<!-- <div style="border: 0px solid; float: left; width: 120px; margin-top: 2px; margin-left: 120px">
				<button type="button" onclick="exmportExcelFun()" style="width: 120px; height: 26px;">批量导出EXCEL</button>
			</div> -->
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="tCXOrderManage" data-options="fit:true,border:false"></table>
	</div>
</div>
