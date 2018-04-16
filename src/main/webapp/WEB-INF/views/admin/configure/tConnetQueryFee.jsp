<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	var connectQueryFeeDataGrid;
	$(function() {
		connectQueryFeeDataGrid = $('#connectQueryFeeDataGrid')
				.datagrid(
						{
							url : '${path}/mgr/ExamineManage/connectQueryFeeDataGrid',
							striped : true,
							rownumbers : true,
							pagination : true,
							idField : 'id',
							sortName : 'UPDATE_TIME',
							sortOrder : 'desc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [
									{
										width : '90',
										title : '贷款/元',
										field : 'ROOTDK',
										align : 'center',
										sortable : true,
										editor : {
											type : 'text',
											options : {
												min : 0,
												max : 3,
												valueField : 'ROOTDK',
												textField : 'ROOTDK',
												required : true
											}
										}
									},
									{
										width : '130',
										title : '二手车/元',
										field : 'ROOTQC',
										align : 'center',
										sortable : true,
										editor : {
											type : 'text',
											options : {
												valueField : 'ROOTQC',
												textField : 'ROOTQC',
												required : true
											}
										}
									},
									{
										width : '130',
										title : '车务代办/元',
										field : 'ROOTCD',
										align : 'center',
										sortable : true,
										editor : {
											type : 'text',
											options : {
												valueField : 'ROOTCD',
												textField : 'ROOTCD',
												required : true
											}
										}
									},
									{
										width : '130',
										title : '汽车美容/元',
										field : 'ROOTCM',
										align : 'center',
										sortable : true,
										editor : {
											type : 'text',
											options : {
												valueField : 'ROOTCM',
												textField : 'ROOTCM',
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
											<shiro:hasPermission name="/ExamineManage/connectQueryFeeEdit">
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="connectQueryFe-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-red\'" onclick="connectQueryFeeEditFun(\'{0}\');" ></a>',
															row.ID);
											str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="connectQueryFe-easyui-linkbutton-save" data-options="plain:true,iconCls:\'fi-save icon-blue\'" onclick="connectQueryFeeSaveFun(\'{0}\');" ></a>',
															row.ID);
											str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="connectQueryFe-easyui-linkbutton-cancel" data-options="plain:true,iconCls:\'fi-x icon-blue\'" onclick="connectQueryFeeCancelFun(\'{0}\');" ></a>',
															row.ID);
											</shiro:hasPermission>
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.connectQueryFe-easyui-linkbutton-edit')
										.linkbutton({
											text : '编辑'
										});
								$('.connectQueryFe-easyui-linkbutton-save')
										.linkbutton({
											text : '保存'
										});
								$('.connectQueryFe-easyui-linkbutton-cancel')
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
											.match("^[0-9]+([.]{1}[0-9]{1,2})?$")) {
										alert("警告，只能输入不为负的数字，且最多两位小数！！！");
										$('#connectQueryFeeDataGrid').datagrid(
												'reload');
										return;
									}
								}
								$
										.post(
												"${path}/mgr/ExamineManage/connectQueryFeeEdit?"
														+ params,
												function(result) {
													if (result['success']) {
														$(
																'#connectQueryFeeDataGrid')
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
								$('#connectQueryFeeDataGrid').datagrid(
										"unselectAll");
							}
						});
	});
	function connectQueryFeeEditFun(target) {
		//第一行开启编辑模式
		$('#connectQueryFeeDataGrid').datagrid("beginEdit", 0);
	}
	function connectQueryFeeSaveFun(target) {
		$('#connectQueryFeeDataGrid').datagrid('endEdit', 0);
	}
	function connectQueryFeeCancelFun(target) {
		$('#connectQueryFeeDataGrid').datagrid('endEdit', 0);
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 35px; overflow: hidden; background-color: #fff">
		<div style="border: 0px solid; height: 25px; background-color: #DBDBDB; font-size: 16px; margin-top: 2px; padding-left: 15px; padding-top: 7px">联系方式查询费</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="connectQueryFeeDataGrid" data-options="fit:true,border:false"></table>
	</div>
</div>
