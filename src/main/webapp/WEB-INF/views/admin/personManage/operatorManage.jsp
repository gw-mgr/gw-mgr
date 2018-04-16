<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	var operatorManageDataGrid;
	$(function() {
		operatorManageDataGrid = $('#operatorManageDataGrid')
				.datagrid(
						{
							url : '${path}/mgr/personManage/operatorManage/operatorDataGrid',
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : false,
							idField : 'id',
							sortName : 'UPDATE_TIME',
							sortOrder : 'desc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [
									{
										width : '100',
										title : '姓名(登录名)',
										field : 'userName',
										align : 'center',
										sortable : true
									},
									{
										width : '130',
										title : '手机',
										field : 'telephone',
										align : 'center',
										sortable : true
									},
									{
										width : '150',
										title : '邮箱',
										field : 'mail',
										align : 'center',
										sortable : true
									},
									{
										width : '150',
										title : '角色',
										field : 'roleId',
										align : 'center',
										hidden : true
									},
									{
										width : '120',
										title : '角色',
										field : 'roleName',
										align : 'center',
										sortable : true
									},
									{
										width : '500',
										title : '备注',
										field : 'description',
										halign : 'center',
										align : 'left',
										sortable : true
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
												return '正常';
											case "02":
												return '已删除';
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
											<shiro:hasPermission name="/tOperatorManage/look">
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="tOperatorManage-easyui-linkbutton-look" data-options="plain:true,iconCls:\'fi-eye icon-blue\'" onclick="tOperatorManageLookFun(\'{0}\');" ></a>',
															row.id);
											</shiro:hasPermission>
											<shiro:hasPermission name="/tOperatorManage/edit">
											str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="tOperatorManage-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="tOperatorManageEditFun(\'{0}\');" ></a>',
															row.id);
											</shiro:hasPermission>
											if (row.status == '01') {
												<shiro:hasPermission name="/tOperatorManage/delete">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="tOperatorManage-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="tOperatorManageUpdateFun(\'{0}\',\'02\');" ></a>',
																row.id);
												</shiro:hasPermission>
											} else if (row.status == '02') {
												<shiro:hasPermission name="/tOperatorManage/open">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="tOperatorManage-easyui-linkbutton-open" data-options="plain:true,iconCls:\'fi-check icon-blue\'" onclick="tOperatorManageUpdateFun(\'{0}\',\'01\');" ></a>',
																row.id);
												</shiro:hasPermission>
											}
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.tOperatorManage-easyui-linkbutton-look')
										.linkbutton({
											text : '查看'
										});
								$('.tOperatorManage-easyui-linkbutton-edit')
										.linkbutton({
											text : '编辑'
										});
								$('.tOperatorManage-easyui-linkbutton-del')
										.linkbutton({
											text : '删除'
										});
								$('.tOperatorManage-easyui-linkbutton-open')
										.linkbutton({
											text : '启用'
										});
							}
						});
	});
	// 编辑
	function tOperatorManageEditFun(operatorId) {
		parent.$
				.modalDialog({
					title : '编辑操作员',
					width : 600,
					height : 550,
					href : '${path}/mgr/personManage/tOperatorManage/editPage?operatorId='
							+ operatorId,
					buttons : [ {
						text : '提交',
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = operatorManageDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
							var f = parent.$.modalDialog.handler
									.find('#opeartorEditForm');
							f.submit();
						}
					} ]
				});
		operatorManageDataGrid.datagrid("unselectAll");
	}
	// 查看
	function tOperatorManageLookFun(operatorId) {
		parent.$.modalDialog({
			title : '操作员详情',
			width : 600,
			height : 550,
			href : '${path}/mgr/personManage/tOperatorManage/lookPage?operatorId='
					+ operatorId,
			buttons : [ {
				text : '关闭',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			} ]
		});
		operatorManageDataGrid.datagrid("unselectAll");
	}
	// 添加
	function addOperatorFun() {
		parent.$
				.modalDialog({
					title : '新建操作员',
					width : 600,
					height : 550,
					href : '${path}/mgr/personManage/tOperatorManage/addPage',
					buttons : [ {
						text : '确定',
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = operatorManageDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
							var f = parent.$.modalDialog.handler
									.find('#opeartorAddForm');
							f.submit();
						}
					} ]
				});
		operatorManageDataGrid.datagrid("unselectAll");
	}
	// 修改
	function tOperatorManageUpdateFun(operatorId, status) {
		var tip = '';
		if (status == '02') {
			tip = '您是否要删除该操作员？';
		} else if (status == '01') {
			tip = '您是否要启用该操作员？';
		}
		parent.$.messager.confirm('询问', tip, function(b) {
			if (b) {
				progressLoad();
				$.post('${path}/mgr/personManage/operatorManage/updateStatus', {
					status : status,
					operatorId : operatorId
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						operatorManageDataGrid.datagrid('reload');
					} else {
						parent.$.messager.alert('提示', result.msg, 'info');
					}
					progressClose();
					operatorManageDataGrid.datagrid("unselectAll");
				}, 'JSON');
			}
		});
		operatorManageDataGrid.datagrid("unselectAll");
	}
	// 搜索
	function tOperatorManageSearchFun() {
		operatorManageDataGrid.datagrid('load', $
				.serializeObject($('#tOperatorManageSearchForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 120px; overflow: hidden; background-color: #fff">
		<div style="border: 0px solid; height: 25px; width: 1300px; background-color: #DBDBDB; font-size: 16px; margin-top: 2px; padding-left: 15px; padding-top: 7px">搜索</div>
		<form id="tOperatorManageSearchForm">
			<div style="width: 1200px; height: 40px; border: 0px solid red;">
				<div style="border: 0px solid green; float: left; width: 200px; height: 35px; font-size: 16px; margin-top: 2px; padding-left: 50px; padding-top: 10px">
					关键字搜索&nbsp;&nbsp;
					<select id="editStatus" name="keywordType" class="easyui-combobox" data-options="width:90,height:25,editable:false,panelHeight:'auto'">
						<option value="userName">姓名</option>
						<option value="telephone">手机</option>
					</select>
				</div>
				<div style="border: 0px solid green; float: left; width: 220px; height: 35px; font-size: 16px; padding-left: 12px; padding-top: 12px">
					<input name="keywordInfo" placeholder="搜索内容" type="text" style="width: 220px; height: 18px">
				</div>
				<div style="border: 0px solid green; float: left; width: 150px; height: 35px; font-size: 16px; margin-top: 2px; padding-left: 12px; padding-top: 8px">
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="tOperatorManageSearchFun();">查询</a>
				</div>
			</div>
		</form>
		<br>
		<div style="float: left; border: 0px solid; width: 1300px; height: 35px; background-color: #DBDBDB; padding-left: 15px;">
			<div style="border: 0px solid; float: left; width: 750px; margin-top: 6px; font-size: 16px;">列表</div>
			<div style="border: 0px solid; float: left; width: 50px; margin-top: 3px; margin-left: 320px">
				<button type="button" onclick="addOperatorFun()" style="width: 50px; height: 26px;">增加</button>
			</div>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="operatorManageDataGrid" data-options="fit:true,border:false"></table>
	</div>
</div>
