<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	//下拉框选择控件，下拉框的内容是动态查询数据库信息  
	var tMerchantManageDataGrid;
	$(function() {
		tMerchantManageDataGrid = $('#tMerchantManageDataGrid')
				.datagrid(
						{
							url : '${path}/mgr/tMerchantManage/dataGrid',
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : false,
							selectOnCheck : true,
							checkOnSelect : true,
							idField : 'merchantId',
							sortName : 'M.UPDATE_TIME',
							sortOrder : 'desc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [
									{
										width : '10',
										title : '服务商名称',
										field : 'merchantId',
										align : 'center',
										hidden : true
									},
									{
										width : '160',
										title : '服务商名称',
										field : 'merchantName',
										align : 'center',
										sortable : true
									},
									{
										width : '10',
										title : '服务范围',
										field : 'merchantType',
										align : 'center',
										hidden : true
									},
									{
										width : '200',
										title : '服务范围',
										field : 'merchantTypeName',
										align : 'center',
										sortable : true
									},
									{
										width : '10',
										title : '授权区域',
										field : 'grantArea',
										align : 'center',
										hidden : true
									},
									{
										width : '270',
										title : '授权区域',
										field : 'grantAreaName',
										align : 'center',
										sortable : true
									},
									{
										width : '70',
										title : '余额/元',
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
										title : '状态',
										field : 'status',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											switch (value) {
											case "01":
												return '<font color=\'red\' >申请中</font>';
											case "02":
												return '已上线';
											case "03":
												return '<b>已关闭</b>';
											}
										}
									},
									{
										width : '130',
										title : '创建时间',
										field : 'createTime',
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
										field : 'action',
										title : '操作',
										align : 'center',
										width : 330,
										formatter : function(value, row, index) {
											var str = '';
											<shiro:hasPermission name="/tMerchantManage/look">
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="tMerchantManage-easyui-linkbutton-look" data-options="plain:true,iconCls:\'fi-eye icon-blue\'" onclick="tMerchantManageLookFun(\'{0}\');" ></a>',
															row.merchantId);
											</shiro:hasPermission>
											<shiro:hasPermission name="/tMerchantManage/edit">
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="tMerchantManage-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="tMerchantManageEditFun(\'{0}\');" ></a>',
															row.merchantId);
											</shiro:hasPermission>
											// 已上线才显示【关闭店铺】和【重置密码】
											if (row.status == '02') {
												<shiro:hasPermission name="/tMerchantManage/down">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="tMerchantManage-easyui-linkbutton-down" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="tMerchantManageUpdateFun(\'{0}\',\'03\');" ></a>',
																row.merchantId);
												</shiro:hasPermission>
												<shiro:hasPermission name="/tMerchantManage/resetPassword">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="tMerchantManage-easyui-linkbutton-resetPassword" data-options="plain:true,iconCls:\'fi-refresh\'" onclick="tMerchantManageResetPasswordFun(\'{0}\');" ></a>',
																row.merchantId);
												</shiro:hasPermission>
												<shiro:hasPermission name="/tMerchantManage/up">
											}
											// 申请中、已关闭才显示【通过/上架店铺】
											if (row.status == '01'
													| row.status == '03') {
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="tMerchantManage-easyui-linkbutton-up" data-options="plain:true,iconCls:\'fi-check\'" onclick="tMerchantManageUpdateFun(\'{0}\',\'02\');" ></a>',
																row.merchantId);
												</shiro:hasPermission>
											}
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.tMerchantManage-easyui-linkbutton-look')
										.linkbutton({
											text : '查看'
										});
								$('.tMerchantManage-easyui-linkbutton-edit')
										.linkbutton({
											text : '编辑'
										});
								$('.tMerchantManage-easyui-linkbutton-down')
										.linkbutton({
											text : '关闭店铺'
										});
								$(
										'.tMerchantManage-easyui-linkbutton-resetPassword')
										.linkbutton({
											text : '重置密码'
										});
								$('.tMerchantManage-easyui-linkbutton-up')
										.linkbutton({
											text : '通过/上架店铺'
										});
							}
						});

		// 下拉框选择控件，下拉框的内容是动态查询数据库信息  
		$('#province')
				.combobox(
						{
							url : '${path}/mgr/tMerchantManage/chinaAreaList?pId=0',
							editable : false, //不可编辑状态  
							cache : false,
							valueField : 'ID',
							textField : 'NAME',
							onHidePanel : function() {
								var province = $('#province').combobox(
										'getValue');
								$('#city').combobox('setValue', '');
								$("#country").combobox("setValue", '');
								var country = $('#country').combobox(
										'getValue');
								if (province != '') {
									$
											.ajax({
												type : "POST",
												url : "${path}/mgr/tMerchantManage/chinaAreaList?pId="
														+ province,
												cache : false,
												dataType : "json",
												success : function(data) {
													$("#city").combobox(
															"loadData", data);
												}
											});
								}
							}
						});
		$('#city')
				.combobox(
						{
							editable : false, //不可编辑状态  
							cache : false,
							valueField : 'ID',
							textField : 'NAME',
							onHidePanel : function() {
								$("#country").combobox("setValue", '');
								var city = $('#city').combobox('getValue');
								if (city != '') {
									$
											.ajax({
												type : "POST",
												url : "${path}/mgr/tMerchantManage/chinaAreaList?pId="
														+ city,
												cache : false,
												dataType : "json",
												success : function(data) {
													$("#country").combobox(
															"loadData", data);
												}
											});
								}
							}
						});
		$('#country').combobox({
			editable : false, //不可编辑状态  
			cache : false,
			valueField : 'ID',
			textField : 'NAME',
			onHidePanel : function() {
				var str = $('#country').combobox('getText');
				$("#cregicounty").val(str);
			}
		});
	});

	/**
	 * 修改服务商状态status
	 */
	function tMerchantManageUpdateFun(merchantId, status) {
		var tipStr;
		if (status == '02') {//上架
			tipStr = '您是否要通过/上架当前店铺?';
		} else if (status == '03') {
			tipStr = '您是否要关闭当前店铺?';
		}
		parent.$.messager.confirm('询问', tipStr, function(b) {
			if (b) {
				progressLoad();
				$.post('${path}/mgr/tMerchantManage/updateStatus', {
					merchantId : merchantId,
					status : status
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						tMerchantManageDataGrid.datagrid('reload');
					}
					progressClose();
					tMerchantManageDataGrid.datagrid("unselectAll");
				}, 'JSON');
			}
		});
	}
	/**
	 * 新建
	 */
	function addMerchantFun() {
		parent.$
				.modalDialog({
					title : '新建服务商',
					width : 700,
					height : 700,
					href : '${path}/mgr/tMerchantManage/addPage',
					buttons : [ {
						text : '确定',
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = tMerchantManageDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
							var f = parent.$.modalDialog.handler
									.find('#tMerchantManageAddForm');
							// 获取勾选的授权区域ID
							var $$tree = $.fn.zTree
									.getZTreeObj("grantAreaTree");
							var checknodes = $$tree.getCheckedNodes(true);
							var ids = [];
							if (checknodes && checknodes.length > 0) {
								for (var i = 0; i < checknodes.length; i++) {
									ids.push(checknodes[i].id);
								}
							}
							$('#grantAreaIds').val(ids);
							// 服务商头像类型过滤
							var imgName = $('#headImage').val();
							idx = imgName.lastIndexOf(".");
							if (idx != -1) {
								ext = imgName.substr(idx + 1).toUpperCase();
								ext = ext.toLowerCase();
								if (ext != 'jpg' && ext != 'png'
										&& ext != 'jpeg' && ext != 'gif') {
									alert("服务商头像只能上传.jpg .png .jpeg .gif类型的文件!");
									return;
								}
							}
							f.submit();
						}
					} ]
				});
		tMerchantManageDataGrid.datagrid("unselectAll");
	}
	// 查看
	function tMerchantManageLookFun(merchantId) {
		parent.$.modalDialog({
			title : '服务商详情',
			width : 500,
			height : 400,
			href : '${path}/mgr/tMerchantManage/lookPage?merchantId='
					+ merchantId,
			buttons : [ {
				text : '关闭',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			} ]
		});
		tMerchantManageDataGrid.datagrid("unselectAll");
	}
	// 编辑
	function tMerchantManageEditFun(id) {
		if (id == undefined) {
			var rows = tMerchantManageDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			tMerchantManageDataGrid.datagrid('unselectAll').datagrid(
					'uncheckAll');
		}
		parent.$
				.modalDialog({
					title : '编辑',
					width : 700,
					height : 700,
					href : '${path}/mgr/tMerchantManage/editPage?merchantId='
							+ id,
					buttons : [ {
						text : '确定',
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = tMerchantManageDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
							var f = parent.$.modalDialog.handler
									.find('#tMerchantManageEditForm');
							// 获取勾选的授权区域ID
							var $$tree = $.fn.zTree
									.getZTreeObj("grantAreaTree");
							var checknodes = $$tree.getCheckedNodes(true);
							var ids = [];
							if (checknodes && checknodes.length > 0) {
								for (var i = 0; i < checknodes.length; i++) {
									ids.push(checknodes[i].id);
								}
							}
							$('#grantAreaIds').val(ids);
							// 服务商头像类型过滤
							var imgName = $('#headImage').val();
							idx = imgName.lastIndexOf("\.");
							if (idx != -1) {
								ext = imgName.substr(idx + 1).toUpperCase();
								ext = ext.toLowerCase();
								if (ext != 'jpg' && ext != 'png'
										&& ext != 'jpeg' && ext != 'gif') {
									alert("服务商头像只能上传.jpg .png .jpeg .gif类型的文件!");
									return;
								}
							}
							f.submit();
						}
					} ]
				});
		tMerchantManageDataGrid.datagrid("unselectAll");
	}
	// 清除
	function tMerchantManageCleanFun() {
		$('#tMerchantManageSearchForm input').val('');
		tMerchantManageDataGrid.datagrid('load', {});
	}
	// 搜索
	function tMerchantManageSearchFun() {
		tMerchantManageDataGrid.datagrid('load', $
				.serializeObject($('#tMerchantManageSearchForm')));
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
		<form id="tMerchantManageSearchForm" method="post">
			<div style="width: 1200px">
				<div style="border: 0px solid; float: left; width: 350px; height: 35px; font-size: 16px; margin-top: 2px; margin-left: 50px; padding-left: 12px; padding-top: 10px">
					创建时间&nbsp;&nbsp; <input id="createStartTime" name="createStartTime" class="easyui-datebox" style="width: 100px"> &nbsp;至&nbsp; <input id="createEndTime" name="createEndTime" class="easyui-datebox" style="width: 100px">
				</div>
				<div style="border: 0px solid; float: left; width: 600px; height: 35px; font-size: 16px; margin-top: 2px; margin-left: 10px; padding-top: 10px">
					<div style="font-size: 16px; float: left; margin-top: 2px">服务商地址&nbsp;&nbsp;</div>
					<input id="province" name="province" data-options="required: true" style="width: 140px;">
					<input id="city" name="city" data-options="required: true" style="width: 95px;">
					<input id="country" name="country" data-options="required: false" style="width: 120px;">
				</div>
			</div>
			<hr width=1310px align="left"></hr>
			<div style="width: 1200px; border: 0px solid;">
				<div style="border: 0px solid; float: left; width: 220px; height: 35px; font-size: 16px; margin-left: 50px; padding-left: 12px; padding-top: 1px">
					服务范围&nbsp;&nbsp;
					<select name="merchantType" style="width: 90px; height: 25px; font-size: 13px">
						<option value="all">全部</option>
						<option value="ROOTLC">理财产品</option>
						<option value="ROOTDK">贷款业务</option>
						<option value="ROOTCD">车务代办</option>
						<option value="ROOTCM">汽车美容</option>
						<option value="ROOTQCM">二手汽车(买)</option>
						<option value="ROOTQCS">二手汽车(卖)</option>
					</select>
				</div>
				<div style="border: 0px solid; float: left; width: 380px; height: 30px; font-size: 16px; padding-top: 1px">
					名称搜索&nbsp;&nbsp; <input id="keywordInfo" name="keywordInfo" placeholder="名称搜索" type="text" style="width: 280px; height: 17px"> &nbsp;&nbsp;
				</div>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="tMerchantManageSearchFun();">查询</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="tMerchantManageCleanFun();">清空</a>
			</div>
		</form>
		<br>
		<div style="float: left; border: 0px solid; width: 1300px; height: 35px; background-color: #DBDBDB; padding-left: 15px;">
			<div style="border: 0px solid; float: left; width: 900px; margin-top: 6px; font-size: 16px;">机构列表</div>
			<shiro:hasPermission name="/tMerchantManage/add">
				<div style="border: 0px solid; float: left; width: 60px; margin-top: 2px; margin-left: 20px">
					<button type="button" onclick="addMerchantFun()" style="width: 60px; height: 26px;">新建</button>
				</div>
			</shiro:hasPermission>
			<!-- <div style="border: 0px solid; float: left; width: 120px; margin-top: 2px; margin-left: 20px">
				<button type="button" onclick="exmportExcelFun()" style="width: 120px; height: 26px;">批量导出EXCEL</button>
			</div> -->
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="tMerchantManageDataGrid" data-options="fit:true,border:false"></table>
	</div>
</div>
