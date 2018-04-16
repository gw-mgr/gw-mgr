<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type='text/javascript' src='${path}/static/jquery.citys.js'></script>
<script type="text/javascript">
	var yongJinZhengCeDataGrid;
	$(function() {
		yongJinZhengCeDataGrid = $('#yongJinZhengCeDataGrid')
				.datagrid(
						{
							url : '${path}/mgr/yongJinZhengCe/dataGrid',
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : false,
							selectOnCheck : true,
							checkOnSelect : true,
							idField : 'id',
							sortName : 'CREATE_TIME',
							sortOrder : 'desc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [
									{
										width : '130',
										title : '保险公司',
										field : 'insurerName',
										align : 'center',
										sortable : true
									},
									{
										width : '110',
										title : '基础佣金（%）',
										field : 'jichuYongj',
										align : 'center',
										sortable : true
									},
									{
										width : '110',
										title : '综合金融奖（%）',
										field : 'zongheJr',
										align : 'center',
										sortable : true
									},
									{
										width : '200',
										title : '有效地区',
										field : 'province',
										align : 'center',
										sortable : true
									},
									{
										width : '400',
										title : '备注',
										field : 'remark',
										align : 'left',
										halign : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '有效日期',
										align : 'center',
										field : 'createTime',
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
													+ day;
										}
									},
									{
										field : 'action',
										title : '操作',
										align : 'center',
										width : 250,
										formatter : function(value, row, index) {
											var str = '';
											<shiro:hasPermission name="/yongJinZhengCe/edit">
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="yongJinZhengCe-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="yongJinZhengCeEditFun(\'{0}\');" ></a>',
															row.id);
											</shiro:hasPermission>
											<shiro:hasPermission name="/yongJinZhengCe/delete">
											str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="yongJinZhengCe-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="yongJinZhengCeDeleteFun(\'{0}\');" ></a>',
															row.id);
											</shiro:hasPermission>
											<shiro:hasPermission name="/yongJinZhengCe/add">
											str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="yongJinZhengCe-easyui-linkbutton-add" data-options="plain:true,iconCls:\'fi-plus icon-blue\'" onclick="yongJinZhengCeAddFun(\'{0}\');" ></a>',
															row.id);
											</shiro:hasPermission>
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.yongJinZhengCe-easyui-linkbutton-edit')
										.linkbutton({
											text : '编辑'
										});
								$('.yongJinZhengCe-easyui-linkbutton-del')
										.linkbutton({
											text : '删除'
										});
								$('.yongJinZhengCe-easyui-linkbutton-add')
										.linkbutton({
											text : '添加'
										});
							},
						});
		$('#city').citys();
	});

	/**
	 * 添加框
	 * @param url
	 */
	function yongJinZhengCeAddFun() {
		parent.$
				.modalDialog({
					title : '添加',
					width : 500,
					height : 300,
					href : '${path}/mgr/yongJinZhengCe/addPage',
					buttons : [ {
						text : '确定',
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = yongJinZhengCeDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
							var f = parent.$.modalDialog.handler
									.find('#yongJinZhengCeAddForm');
							f.submit();
						}
					} ]
				});
	}

	/**
	 * 编辑
	 */
	function yongJinZhengCeEditFun(id) {
		if (id == undefined) {
			var rows = yongJinZhengCeDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			yongJinZhengCeDataGrid.datagrid('unselectAll').datagrid(
					'uncheckAll');
		}
		parent.$
				.modalDialog({
					title : '编辑',
					width : 500,
					height : 300,
					href : '${path}/mgr/yongJinZhengCe/editPage?id=' + id,
					buttons : [ {
						text : '确定',
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = yongJinZhengCeDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
							var f = parent.$.modalDialog.handler
									.find('#yongJinZhengCeEditForm');
							f.submit();
						}
					} ]
				});
	}

	/**
	 * 删除
	 */
	function yongJinZhengCeDeleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = yongJinZhengCeDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			yongJinZhengCeDataGrid.datagrid('unselectAll').datagrid(
					'uncheckAll');
		}
		parent.$.messager.confirm('询问', '您是否要删除该政策？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path}/mgr/yongJinZhengCe/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						yongJinZhengCeDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}

	// 清除
	function yongJinZhengCeCleanFun() {
		$('#yongJinZhengCeSearchForm input').val('');
		yongJinZhengCeDataGrid.datagrid('load', {});
	}
	// 搜索
	function yongJinZhengCeSearchFun() {
		yongJinZhengCeDataGrid.datagrid('load', $
				.serializeObject($('#yongJinZhengCeSearchForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 88px; overflow: hidden; background-color: #fff">
		<div style="height: 30px;">
			<img id="u162_img" class="img" width="100%" height="100%" src="${path}/static/style/images/u522.png" />
			<span style="position: absolute; top: 7px; left: 6px; text-align: center;">搜索</span>
		</div>
		<form id="yongJinZhengCeSearchForm">
			<div id="city" class="citys" style="margin-top: 10px; margin-left: 30px; float: left">
				<select id="province" name="province"></select>
				<select id="city" name="city"></select>
				<select id="area" name="district"></select>
			</div>
			<div style="margin-top: 10px; margin-left: 30px; float: left">生效日期：</div>
			<div style="margin-top: 6px; margin-left: 5px; float: left">
				<input name='createTime' id="createTimeId" type="text" class="easyui-datebox">
			</div>
			<div style="margin-top: 4px; margin-left: 10px; float: left">
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="yongJinZhengCeSearchFun();">查询</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="yongJinZhengCeCleanFun();">清空</a>
			</div>
		</form>
		<div style="height: 30px;">
			<img id="u162_img" class="img" width="100%" height="100%" src="${path}/static/style/images/u522.png" />
			<span style="position: absolute; top: 70px; left: 6px; text-align: center;">列表</span>
		</div>
	</div>

	<div data-options="region:'center',border:false">
		<table id="yongJinZhengCeDataGrid" data-options="fit:true,border:false"></table>
	</div>
</div>
