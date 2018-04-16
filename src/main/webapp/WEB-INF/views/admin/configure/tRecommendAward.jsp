<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	var recommendAwardDataGrid;
	$(function() {
		recommendAwardDataGrid = $('#recommendAwardDataGrid')
				.datagrid(
						{
							url : '${path}/mgr/ExamineManage/recommendAwardDataGrid',
							striped : true,
							rownumbers : true,
							pagination : true,
							idField : 'ID',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [
									{
										width : '130',
										title : '直接推荐奖 (%)',
										field : 'firstGeneration',
										align : 'center',
										sortable : true,
										editor : {
											type : 'text',
											options : {
												valueField : 'firstGeneration',
												textField : 'firstGeneration',
												required : true
											}
										}
									},
									{
										width : '130',
										title : '间接推荐奖(%)',
										field : 'secondGeneration',
										align : 'center',
										sortable : true,
										editor : {
											type : 'text',
											options : {
												valueField : 'secondGeneration',
												textField : 'secondGeneration',
												required : true
											}
										}
									},
									{
										field : 'action',
										title : '操作',
										align : 'center',
										width : 300,
										formatter : function(value, row, index) {
											var str = '';
											<shiro:hasPermission name="/ExamineManage/recommendAwardDataGrid/edit">
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="tMerchantManage-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-red\'" onclick="editFun(\'{0}\');" ></a>',
															row.ID);
											str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="tMerchantManage-easyui-linkbutton-save" data-options="plain:true,iconCls:\'fi-save icon-blue\'" onclick="saveFun(\'{0}\');" ></a>',
															row.ID);
											str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="tMerchantManage-easyui-linkbutton-cancel" data-options="plain:true,iconCls:\'fi-x icon-blue\'" onclick="cancelFun(\'{0}\');" ></a>',
															row.ID);
											</shiro:hasPermission>
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.tMerchantManage-easyui-linkbutton-edit')
										.linkbutton({
											text : '编辑'
										});
								$('.tMerchantManage-easyui-linkbutton-save')
										.linkbutton({
											text : '保存'
										});
								$('.tMerchantManage-easyui-linkbutton-cancel')
										.linkbutton({
											text : '取消编辑'
										});
							},
							onAfterEdit : function(rowIndex, rowData, changes) {
								//endEdit该方法触发此事件
								var params = "";
								for ( var i in rowData) {
									params += i + "=" + rowData[i] + "&";
								}
								var change = "";
								for ( var i in changes) {
									if (!changes[i]
											.match("^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$")) {
										alert("警告，只能输入不为负的数字！！！");
										$('#recommendAwardDataGrid').datagrid(
										'reload');
								return;
									}
								}
								$
										.post(
												"${path}/mgr/ExamineManage/recommendAwardEdit?"
														+ params,
												function(result) {
													if (result['success']) {
														$(
																'#recommendAwardDataGrid')
																.datagrid(
																		'reload');
													} else {
														parent.$.messager
																.alert(
																		'提示',
																		result.message,
																		'输入有误');
													}
												}, 'json');
								//应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
								$('#recommendAwardDataGrid').datagrid(
										"unselectAll");
							}
						});
	});
	function editFun(target) {
		//第一行开启编辑模式
		$('#recommendAwardDataGrid').datagrid("beginEdit", 0);
	}
	function saveFun(target) {
		$('#recommendAwardDataGrid').datagrid('endEdit', 0);
	}
	function cancelFun(target) {
		$('#recommendAwardDataGrid').datagrid('endEdit', 0);
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 35px; overflow: hidden; background-color: #fff">
		<div style="border: 0px solid; height: 25px; background-color: #DBDBDB; font-size: 16px; margin-top: 2px; padding-left: 15px; padding-top: 7px">推荐奖奖金设置</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="recommendAwardDataGrid" data-options="fit:true,border:false"></table>
	</div>
</div>
