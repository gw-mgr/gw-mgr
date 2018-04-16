<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	var userDataGrid;
	var merchantList;

	$(function() {
		organizationTree = $.fn.zTree.init($('#merchantList'), {
			data : {
				simpleData : {
					enable : true,
					rootPId : 1
				}
			},
			view : {
				txtSelectedEnable : true
			},
			async : {
				enable : true,
				url : "${path}/mgr/user/merchantList"
			},
			callback : {
				onClick : function(event, treeId, node) {
					userDataGrid.datagrid('load', {
						organizationId : node.id
					});
				}
			}
		});

		userDataGrid = $('#userDataGrid')
				.datagrid(
						{
							url : '${path}/mgr/user/dataGrid',
							fit : true,
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : true,
							idField : 'id',
							sortName : 'createTime',
							sortOrder : 'asc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							columns : [ [
									{
										width : '120',
										title : '登录名',
										field : 'loginName',
										align : 'center',
										sortable : true
									},
									{
										width : '120',
										title : '姓名',
										field : 'name',
										align : 'center',
										sortable : true
									},
									{
										width : '80',
										title : '服务商ID',
										field : 'organizationId',
										align : 'center',
										hidden : true
									},
									{
										width : '80',
										title : '所属服务商',
										align : 'center',
										field : 'organizationName'
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
										width : '40',
										title : '性别',
										align : 'center',
										field : 'sex',
										sortable : true,
										formatter : function(value, row, index) {
											switch (value) {
											case 0:
												return '男';
											case 1:
												return '女';
											}
										}
									},
									{
										width : '40',
										title : '年龄',
										align : 'center',
										field : 'age',
										sortable : true
									},
									{
										width : '100',
										title : '电话',
										align : 'center',
										field : 'phone',
										sortable : true
									},
									{
										width : '100',
										title : '角色',
										align : 'center',
										field : 'rolesList'
									},
									{
										width : '80',
										title : '用户类型',
										field : 'userType',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											if (value == 0) {
												return "管理员";
											} else if (value == 1) {
												return "用户";
											}
											return "未知类型";
										}
									},
									{
										width : '50',
										title : '状态',
										field : 'status',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											switch (value) {
											case '01':
												return '正常';
											case '02':
												return '停用';
											}
										}
									},
									{
										field : 'action',
										title : '操作',
										width : 200,
										align : 'center',
										formatter : function(value, row, index) {
											var str = '';
											<shiro:hasPermission name="/user/edit">
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="editUserFun(\'{0}\');" >编辑</a>',
															row.id);
											</shiro:hasPermission>
											<shiro:hasPermission name="/user/delete">
											str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="user-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="deleteUserFun(\'{0}\');" >删除</a>',
															row.id);
											</shiro:hasPermission>
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.user-easyui-linkbutton-edit').linkbutton({
									text : '编辑'
								});
								$('.user-easyui-linkbutton-del').linkbutton({
									text : '删除'
								});
							},
							toolbar : '#userToolbar'
						});
	});

	function addUserFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 500,
			height : 300,
			href : '${path}/mgr/user/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = userDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#userAddForm');
					f.submit();
				}
			} ]
		});
	}

	function deleteUserFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = userDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			userDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '您是否要删除当前用户？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path}/mgr/user/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						userDataGrid.datagrid('reload');
					} else {
						parent.$.messager.alert('错误', result.msg, 'error');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}

	function editUserFun(id) {
		if (id == undefined) {
			var rows = userDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			userDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 500,
			height : 300,
			href : '${path}/mgr/user/editPage?id=' + id,
			buttons : [ {
				text : '确定',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = userDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#userEditForm');
					f.submit();
				}
			} ]
		});
	}

	function searchUserFun() {
		userDataGrid.datagrid('load', $.serializeObject($('#searchUserForm')));
	}
	function cleanUserFun() {
		$('#searchUserForm input').val('');
		userDataGrid.datagrid('load', {});
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden; background-color: #fff">
		<form id="searchUserForm">
			<table>
				<tr>
					<th>姓名:</th>
					<td>
						<input name="name" placeholder="请输入用户姓名" />
					</td>
					<th>创建时间:</th>
					<td>
						<input name="createdateStart" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyyMMddHHmmss'})" readonly="readonly" />
						至
						<input name="createdateEnd" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="searchUserFun();">查询</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="cleanUserFun();">清空</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:true,title:'用户列表'">
		<table id="userDataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div data-options="region:'west',border:true,split:false,title:'所属服务商'" style="width: 180px; overflow: hidden;">
		<ul id="merchantList" class="ztree"></ul>
	</div>
</div>
<div id="userToolbar" style="display: none;">
	<shiro:hasPermission name="/user/add">
		<a onclick="addUserFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
	</shiro:hasPermission>
</div>