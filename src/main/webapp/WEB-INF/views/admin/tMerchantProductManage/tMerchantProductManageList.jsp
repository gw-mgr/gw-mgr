<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	var tProductManageDataGrid;
	$(function() {
		tProductManageDataGrid = $('#tProductManageDataGrid')
				.datagrid(
						{
							url : '${path}/mgr/tMerchantProductManage/dataGrid',
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : false,
							idField : 'id',
							sortName : 'updateTime',
							sortOrder : 'DESC',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [
									{
										field : 'ck',
										checkbox : true
									},
									{
										width : '230',
										title : '产品ID',
										field : 'productId',
										align : 'center',
										sortable : true
									},
									{
										width : '150',
										title : '产品名称',
										field : 'productName',
										align : 'center',
										sortable : true
									},
									{
										width : '200',
										title : '服务商名称',
										field : 'merchantName',
										align : 'center',
										sortable : true
									},
									{
										width : '80',
										title : '服务商状态',
										field : 'mStatus',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											if (value == '01') {
												return '申请中';
											} else if (value == '02') {
												return '已上线';
											} else if (value == '03') {
												return '已关闭';
											}
										}
									},
									{
										width : '10',
										title : '产品类型',
										field : 'productType',
										align : 'center',
										hidden : true
									},
									{
										width : '100',
										title : '产品类型',
										field : 'productTypeName',
										align : 'center',
										sortable : true
									},
									{
										width : '150',
										title : '创建时间',
										field : 'updateTime',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											value = value + "";
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
										title : '产品状态',
										field : 'status',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											if (value == '01') {
												if (row.productType == "ROOTCD"
														|| row.productType == "ROOTCM") {
													return '启用';
												} else {
													return '上架'
												}
											} else if (value == '02') {
												if (row.productType == "ROOTCD"
														|| row.productType == "ROOTCM") {
													return '停用';
												} else {
													return '下架';
												}
											} else if (value == '03') {
												return '强制下架';
											}
										}
									},
									{
										field : 'action',
										title : '操作',
										align : 'center',
										width : 230,
										formatter : function(value, row, index) {
											var str = '';
											<shiro:hasPermission name="/tProductManage/look">
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="tProductManage-easyui-linkbutton-look" data-options="plain:true,iconCls:\'fi-eye icon-blue\'" onclick="tProductManageLookFun(\'{0}\',\'{1}\');" ></a>',
															row.productId,
															row.productType);
											</shiro:hasPermission>
											if (row.status == "02"
													|| row.status == "03") {
												<shiro:hasPermission name="/tProductManage/up">
												if (row.productType == "ROOTCD"
														|| row.productType == "ROOTCM") {
													str += $
															.formatString(
																	'<a href="javascript:void(0)" class="tProductManage-easyui-linkbutton-up2" data-options="plain:true,iconCls:\'fi-check icon-blue\'" onclick="tProductManageUpdateStatusFun(\'{0}\',\'{1}\',\'up\');"></a>',
																	row.productId,
																	row.mStatus);
												} else {
													str += $
															.formatString(
																	'<a href="javascript:void(0)" class="tProductManage-easyui-linkbutton-up" data-options="plain:true,iconCls:\'fi-check icon-blue\'" onclick="tProductManageUpdateStatusFun(\'{0}\',\'{1}\',\'up\');"></a>',
																	row.productId,
																	row.mStatus);
												}
												</shiro:hasPermission>
											} else {
												<shiro:hasPermission name="/tProductManage/forceDown">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="tProductManage-easyui-linkbutton-forceDown" data-options="plain:true,iconCls:\'fi-alert icon-red\'" onclick="tProductManageUpdateStatusFun(\'{0}\',\'{1}\',\'forceDown\');"></a>',
																row.productId,
																row.mStatus);
												</shiro:hasPermission>
											}
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.tProductManage-easyui-linkbutton-look')
										.linkbutton({
											text : '查看'
										});
								$('.tProductManage-easyui-linkbutton-up')
										.linkbutton({
											text : '上架'
										});
								$('.tProductManage-easyui-linkbutton-up2')
										.linkbutton({
											text : '启用'
										});
								$('.tProductManage-easyui-linkbutton-down')
										.linkbutton({
											text : '下架'
										});
								$('.tProductManage-easyui-linkbutton-down2')
										.linkbutton({
											text : '停用'
										});
								$('.tProductManage-easyui-linkbutton-forceDown')
										.linkbutton({
											text : '强制下架'
										});
							},
							toolbar : '#tProductManageToolbar'
						});
	});

	// 查看
	function tProductManageLookFun(productId, productType) {
		parent.$.modalDialog({
			title : '产品详情',
			width : 500,
			height : 400,
			href : '${path}/mgr/tMerchantProductManage/productInfo?productId='
					+ productId + '&productType=' + productType,
			buttons : [ {
				text : '关闭',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			} ]
		});
		tProductManageDataGrid.datagrid("unselectAll");
	}
	// 产品适用范围上下架操作
	function tProductManageUpdateStatusFun(productId, mStatus, statusStr) {
		var status;
		if (statusStr == 'up') {
			status = '01';
		} else if (statusStr == 'forceDown') {
			status = '03';
		}
		if (status == '03') {
			parent.$.messager.confirm('询问', '您是否要下架该产品？', function(b) {
				if (b) {
					progressLoad();
					$.post('${path}/mgr/tMerchantProductManage/update', {
						status : status,
						productId : productId
					}, function(result) {
						if (result['success']) {
							parent.$.messager.alert('提示', result.msg, 'info');
							tProductManageDataGrid.datagrid('reload');
						}
						progressClose();
						tProductManageDataGrid.datagrid("unselectAll");
					}, 'JSON');
				}
			});
		} else {
			if (mStatus != '02') {
				parent.$.messager.alert('警告', '确保服务商状态为已上线，才能上架/启用该产品！！！',
						'warning');
				return;
			}
			$.post('${path}/mgr/tMerchantProductManage/update', {
				status : status,
				productId : productId
			}, function(result) {
				if (result['success']) {
					parent.$.messager.alert('提示', result.msg, 'info');
					tProductManageDataGrid.datagrid('reload');
				}
				progressClose();
				tProductManageDataGrid.datagrid("unselectAll");
			}, 'JSON');
		}
	}

	// 搜索
	function tProductManageSearchFun() {
		var json = $.serializeObject($('#tProductManageSearchForm'));
		var param = '';
		for ( var i in json) {
			param += i + '=' + json[i] + ',';
		}
		tProductManageDataGrid.datagrid('load', $
				.serializeObject($('#tProductManageSearchForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 135px; overflow: hidden; background-color: #fff">
		<div style="border: 0px solid; height: 25px; width: 1300px; background-color: #DBDBDB; font-size: 16px; margin-top: 2px; padding-left: 15px; padding-top: 7px">搜索</div>
		<form id="tProductManageSearchForm">
			<div style="width: 1200px">
				<div style="border: 0px solid; float: left; width: 200px; height: 35px; font-size: 16px; margin-top: 2px; margin-left: 50px; padding-left: 12px; padding-top: 10px">
					产品类型&nbsp;&nbsp;
					<select name="productType" style="width: 90px; height: 25px; font-size: 13px">
						<option value="all">全部</option>
						<option value="ROOTLC">理财产品</option>
						<option value="ROOTDK">贷款业务</option>
						<option value="ROOTCD">车务代办</option>
						<option value="ROOTCM">汽车美容</option>
						<option value="ROOTQCM">二手汽车(买)</option>
						<option value="ROOTQCS">二手汽车(卖)</option>
					</select>
				</div>
				<div style="border: 0px solid; float: left; width: 270px; height: 35px; font-size: 16px; margin-top: 2px; padding-top: 10px">
					关键字搜索&nbsp;&nbsp;
					<select name="keywordType" style="width: 150px; height: 25px; font-size: 13px">
						<option value="merchantName">服务商名称</option>
						<option value="productName">产品名称</option>
						<option value="productId">产品ID</option>
					</select>
				</div>
				<div style="border: 0px solid; float: left; width: 360px; height: 35px; font-size: 16px; margin-top: 2px; padding-top: 10px">
					<input type="text" name='keywordInfo' placeholder="搜索条件" style="width: 280px; height: 18px">
					&nbsp;&nbsp;
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="tProductManageSearchFun();">查询</a>
				</div>
			</div>
		</form>
		<br>
		<div style="float: left; border: 0px solid; width: 1300px; height: 35px; background-color: #DBDBDB; padding-left: 15px;">
			<div style="border: 0px solid; float: left; width: 750px; margin-top: 8px; font-size: 16px;">产品列表</div>
			<!-- <div style="border: 0px solid; float: left; width: 120px; margin-top: 4px; margin-left: 300px">
				<button type="button" id="importExcel" onclick="importExcelFun()" style="width: 120px; height: 26px;">批量导出EXCEL</button>
			</div> -->
		</div>
	</div>

	<div data-options="region:'center',border:false">
		<table id="tProductManageDataGrid" data-options="fit:true,border:false"></table>
	</div>
</div>