<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	var orderCxDataGrid;
	var data = [];
	$(function() {
		orderCxDataGrid = $('#orderCxDataGrid')
				.datagrid(
						{
							url : '${path}/mgr/orderCx/dataGrid',
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : false,
							selectOnCheck : true,
							checkOnSelect : false,
							idField : 'orderId',
							sortName : 'createTime',
							sortOrder : 'desc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [ {
								field : 'id',
								checkbox : true
							}, {
								width : '100',
								title : '订单号',
								field : 'orderNo',
								align : 'center',
								sortable : true
							}, {
								width : '100',
								title : '下单日期',
								field : 'createTime',
								align : 'center',
								sortable : true
							}, {
								width : '100',
								title : '是否成交',
								field : 'orderFlag',
								align : 'center',
								sortable : true,
								formatter : function(value, row) {
					            	if (value == '01'){
										return '未成交';
									}
									if (value == '02'){
										return '成交';
									}
					            }
							} ] ],
							columns : [ [
									{
										width : '100',
										title : '录单员',
										field : 'recorder',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '订单类型',
										field : 'orderType',
										align : 'center',
										sortable : true,
										formatter : function(value, row) {
							            	if (value == 'ROOTCCCL'){
												return '车辆保险';
											}
											if (value == 'ROOTCCQY'){
												return '企业财产险';
											}
											if (value == 'ROOTCCJZ'){
												return '建筑工程险';
											}
											if (value == 'ROOTCCGC'){
												return '工程机械险';
											}
											if (value == 'ROOTCCTY'){
												return '团意雇主险';
											}
											if (value == 'ROOTCCDQ'){
												return '短期意外险';
											}
											if (value == 'ROOTCCGZ'){
												return '公众责任险';
											}
											if (value == 'ROOTQT'){
												return '其他';
											}
							            }
									},
									{
										width : '100',
										title : '车牌号',
										field : 'carNum',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '车主',
										field : 'carOwner',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '保险公司',
										field : 'insuranceCompany',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '总商业险',
										field : 'zsyx',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '净商业险',
										field : 'jsyx',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '商业险税',
										field : 'syxs',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '交强险',
										field : 'zjq',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '交强险税',
										field : 'jqxs',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '车船税',
										field : 'ccs',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '总保费',
										field : 'zbf',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '业务员',
										field : 'salesManName',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '业务员佣金',
										field : 'sjzfyj',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '投保方式',
										field : 'tbfs',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '佣金结算',
										field : 'commissionFlag',
										align : 'center',
										sortable : true,
										formatter : function(value, row) {
							            	if (value == '01'){
												return '未发放';
											}
											if (value == '02'){
												return '已发放';
											}
							            }
									},
									{
										width : '100',
										title : '礼品结算',
										field : 'giftFlag',
										align : 'center',
										sortable : true,
										formatter : function(value, row) {
											if (value == '01'){
												return '未发放';
											}
											if (value == '02'){
												return '已发放';
											}
							            }
									},
									{
										width : '100',
										title : '出单渠道',
										field : 'operatorName',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '保险公司税前<br />佣金比例',
										field : 'bxgssqyjbl',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '本公司毛利',
										field : 'bgsml',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '保险公司<br />是否结账',
										field : 'checkoutFlag',
										align : 'center',
										sortable : true,
										formatter : function(value, row) {
											if (value == '01'){
												return '未结算';
											}
											if (value == '02'){
												return '已结算';
											}
							            }
									},
									{
										width : '100',
										title : '公司管理费<br />比例',
										field : 'gsglfbl',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '公司管理费<br />金额',
										field : 'gsglfje',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '业务员佣金<br />支付对象',
										field : 'yjzfdx',
										align : 'center',
										sortable : true,
										formatter : function(value, row) {
											if (value == '01'){
												return '业务员';
											}
											if (value == '02'){
												return '录单员';
											}
							            }
									},
									{
										width : '100',
										title : '佣金支付人',
										field : 'yjzfr',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '商业险起始日期',
										field : 'syxqbDate',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '保险公司开票<br />佣金比例',
										field : 'bxgskpyjbl',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '保险公司开票<br />金额',
										field : 'bxgskpyj',
										align : 'center',
										sortable : true
									},
									{
										width : '140',
										title : '车辆备注',
										field : 'carRemark',
										align : 'center',
										sortable : true
									},
									{
										width : '140',
										title : '礼品内容',
										field : 'giftComment',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '最后修改时间',
										field : 'updateTime',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '最后修改人',
										field : 'updateUserName',
										align : 'center',
										sortable : true
									},
									{
										field : 'action',
										title : '操作',
										width : 460,
										align : 'center',
										formatter : function(value, row, index) {
											var str = '';
											if (row.orderFlag == '01'){
												<shiro:hasPermission name="/orderCx/show">
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-show" data-options="plain:true,iconCls:\'fi-layout icon-blue\'" onclick="orderCxShowFun(\'{0}\');" >查看</a>',
																row.orderId);
												</shiro:hasPermission>
												<shiro:hasPermission name="/orderCx/edit">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="orderCxEditFun(\'{0}\');" >编辑</a>',
																row.orderId);
												</shiro:hasPermission>
												<shiro:hasPermission name="/orderCx/change">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-change" data-options="plain:true,iconCls:\'fi-page-filled icon-blue\'" onclick="orderCxChangeFun(\'{0}\');" >变更</a>',
																row.orderId);
												</shiro:hasPermission>
												<shiro:hasPermission name="/orderCx/delete">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="orderCxDeleteFun(\'{0}\');" >删除</a>',
																row.orderId);
												</shiro:hasPermission>
											}
											if (row.orderFlag == '02' && row.commissionFlag == '01' && row.checkoutFlag == '01'){
												<shiro:hasPermission name="/orderCx/show">
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-show" data-options="plain:true,iconCls:\'fi-layout icon-blue\'" onclick="orderCxShowFun(\'{0}\');" >查看</a>',
																row.orderId);
												</shiro:hasPermission>
												<shiro:hasPermission name="/orderCx/edit">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="orderCxEditFun(\'{0}\');" >编辑</a>',
																row.orderId);
												</shiro:hasPermission>
												<shiro:hasPermission name="/orderCx/change">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-change" data-options="plain:true,iconCls:\'fi-page-filled icon-blue\'" onclick="orderCxChangeFun(\'{0}\');" >变更</a>',
																row.orderId);
												</shiro:hasPermission>
												<shiro:hasPermission name="/orderCx/pay">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-pay" data-options="plain:true,iconCls:\'fi-paypal icon-blue\'" onclick="orderCxPayFun(\'{0}\');" >支付佣金</a>',
																row.orderId);
												</shiro:hasPermission>
												<shiro:hasPermission name="/orderCx/check">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-check" data-options="plain:true,iconCls:\'fi-credit-card icon-blue\'" onclick="orderCxCheckFun(\'{0}\');" >结账设置</a>',
																row.orderId);
												</shiro:hasPermission>
												
												<shiro:hasPermission name="/orderCx/delete">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="orderCxDeleteFun(\'{0}\');" >删除</a>',
																row.orderId);
												</shiro:hasPermission>
											}
											if (row.orderFlag == '02' && row.commissionFlag == '01' && row.checkoutFlag == '02'){
												<shiro:hasPermission name="/orderCx/show">
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-show" data-options="plain:true,iconCls:\'fi-layout icon-blue\'" onclick="orderCxShowFun(\'{0}\');" >查看</a>',
																row.orderId);
												</shiro:hasPermission>
												<shiro:hasPermission name="/orderCx/edit">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="orderCxEditFun(\'{0}\');" >编辑</a>',
																row.orderId);
												</shiro:hasPermission>
												<shiro:hasPermission name="/orderCx/pay">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-pay" data-options="plain:true,iconCls:\'fi-paypal icon-blue\'" onclick="orderCxPayFun(\'{0}\');" >支付佣金</a>',
																row.orderId);
												</shiro:hasPermission>
												<shiro:hasPermission name="/orderCx/delete">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="orderCxDeleteFun(\'{0}\');" >删除</a>',
																row.orderId);
												</shiro:hasPermission>
											}
											if (row.orderFlag == '02' && row.commissionFlag == '02' && row.checkoutFlag == '01'){
												<shiro:hasPermission name="/orderCx/show">
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-show" data-options="plain:true,iconCls:\'fi-layout icon-blue\'" onclick="orderCxShowFun(\'{0}\');" >查看</a>',
																row.orderId);
												</shiro:hasPermission>
												<shiro:hasPermission name="/orderCx/change">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-change" data-options="plain:true,iconCls:\'fi-page-filled icon-blue\'" onclick="orderCxChangeFun(\'{0}\');" >变更</a>',
																row.orderId);
												</shiro:hasPermission>
												<shiro:hasPermission name="/orderCx/check">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-check" data-options="plain:true,iconCls:\'fi-credit-card icon-blue\'" onclick="orderCxCheckFun(\'{0}\');" >结账设置</a>',
																row.orderId);
												</shiro:hasPermission>
												<shiro:hasPermission name="/orderCx/delete">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="orderCxDeleteFun(\'{0}\');" >删除</a>',
																row.orderId);
												</shiro:hasPermission>
											}
											if (row.orderFlag == '02' && row.commissionFlag == '02' && row.checkoutFlag == '02'){
												<shiro:hasPermission name="/orderCx/show">
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-show" data-options="plain:true,iconCls:\'fi-layout icon-blue\'" onclick="orderCxShowFun(\'{0}\');" >查看</a>',
																row.orderId);
												</shiro:hasPermission>
												<shiro:hasPermission name="/orderCx/delete">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="orderCxDeleteFun(\'{0}\');" >删除</a>',
																row.orderId);
												</shiro:hasPermission>
											}
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.orderIns-easyui-linkbutton-show').linkbutton({text : '查看'});
								$('.orderIns-easyui-linkbutton-edit').linkbutton({text : '编辑'});
								$('.orderIns-easyui-linkbutton-change').linkbutton({text : '变更'});
								$('.orderIns-easyui-linkbutton-pay').linkbutton({text : '支付佣金'});
								$('.orderIns-easyui-linkbutton-check').linkbutton({text : '结账设置'});
								$('.orderIns-easyui-linkbutton-del').linkbutton({text : '删除'});
							},
							toolbar : '#orderCxToolbar',
							onSelect : function(rowIndex, rowData){  //用于解决点击某行不会高亮
					             var fl = rowData.checkoutFlag;
					             data.length = 0;
					             data.push({ 'text': '批量操作', 'id': '', selected:true},{ 'text': '成交/未成交', 'id': 'orderFlag' },{ 'text': '打单/未打单', 'id': 'printFlag' },{ 'text': '礼品发放', 'id': 'giftFlag' });
								 if(fl == 02){
									 $('#batch').combobox('loadData', {});
									 $("#batch").combobox("loadData", data);
								 }
					        },
					        onUnselect : function(rowIndex, rowData){  //用于解决点击某行不会高亮
					             var fl = rowData.checkoutFlag;
					             data.length = 0;
					             data.push({ 'text': '批量操作', 'id': '', selected:true},{ 'text': '成交/未成交', 'id': 'orderFlag' },{ 'text': '打单/未打单', 'id': 'printFlag' },{ 'text': '礼品发放', 'id': 'giftFlag' },{ 'text': '结账设置', 'id': 'checkoutSet' },{ 'text': '保险公司结账', 'id': 'checkoutFlag' });
					             var rows = orderCxDataGrid.datagrid('getSelections');
					             var flge = true;
					             if(rows.length != 0){
					            	 for(j = 0; j < rows.length; j++) {
							          	 if(rows[j].checkoutFlag==02){
							          		flge = false;
							          	 }
							          }
					             }
						         if(flge){
						        	 $('#batch').combobox('loadData', {});
									 $("#batch").combobox("loadData", data);
						         }  
					        }
						});
		
		
		$("#batch").combobox({
			onChange: function (n,o) {
				var val = $('#batch').combobox('getValue');
				if(val != ''){
					var rows = orderCxDataGrid.datagrid('getSelections');
					var id;
					if(rows.length>0){
						var id = rows[0].orderId;s
						for(j = 1; j < rows.length; j++) {
						 id = id + "," + rows[j].orderId;
						}
						
						if(val == "checkoutSet"){
							parent.$.modalDialog({
								title : '编辑',
								width : 400,
								height : 300,
								href : '${path}/mgr/orderCx/accPage?id=' + id,
								buttons : [ {
									text : '确定',
									handler : function() {
										parent.$.modalDialog.openner_dataGrid = orderCxDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
										var f = parent.$.modalDialog.handler
												.find('#orderCxAccForm');
										f.submit();
									}
								} ]
							});
						}else{
							parent.$.messager.confirm('询问', '请确认当前操作？', function(b) {
								if (b) {
									progressLoad();
									$.post('${path}/mgr/orderCx/changeStatus', {
										id : id,
										cloumn : val
									}, function(result) {
										if (result.success) {
											parent.$.messager.alert('提示', result.msg, 'info');
											orderCxDataGrid.datagrid('reload');
										}
										progressClose();
									}, 'JSON');
								}
							});
						}
					}
				}
			}
		});
	});

	/**
	 * 添加框
	 * @param url
	 */
	function orderCxAddFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 700,
			height : 600,
			href : '${path}/mgr/orderCx/addPage',
			buttons : [ {
				text : '确定',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = orderCxDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					var f = parent.$.modalDialog.handler
							.find('#orderCxAddForm');
					f.submit();
				}
			} ]
		});
	}
	/**
	 * 查看框
	 * @param url
	 */
	function orderCxShowFun(id){
		if (id == undefined) {
			var rows = orderCxDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			orderCxDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 700,
			height : 600,
			href : '${path}/mgr/orderCx/show?id=' + id,
			buttons : [ {
				text : '确定',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			} ]
		});
	}
	/**
	 * 编辑
	 */
	function orderCxEditFun(id) {
		if (id == undefined) {
			var rows = orderCxDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			orderCxDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 700,
			height : 600,
			href : '${path}/mgr/orderCx/editPage?id=' + id,
			buttons : [ {
				text : '确定',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = orderCxDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler
							.find('#orderCxEditForm');
					f.submit();
				}
			} ]
		});
	}
	/**
	 * 变更
	 */
	function orderCxChangeFun(id) {
		if (id == undefined) {
			var rows = orderCxDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			orderCxDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 700,
			height : 600,
			href : '${path}/mgr/orderCx/changePage?id=' + id,
			buttons : [ {
				text : '确定',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = orderCxDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler
							.find('#orderCxChangeForm');
					f.submit();
				}
			} ]
		});
	}
	/**
	 * 支付设置
	 */
	function orderCxPayFun(id) {
		if (id == undefined) {
			var rows = orderCxDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			orderCxDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 700,
			height : 600,
			href : '${path}/mgr/orderCx/payPage?id=' + id,
			buttons : [ {
				text : '确定',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = orderCxDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler
							.find('#orderCxPayForm');
					f.submit();
				}
			} ]
		});
	}
	
	/**
	 * 结账设置
	 */
	function orderCxCheckFun(id) {
		if (id == undefined) {
			var rows = orderCxDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			orderCxDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 700,
			height : 600,
			href : '${path}/mgr/orderCx/accPage?id=' + id,
			buttons : [ {
				text : '确定',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = orderCxDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler
							.find('#orderCxAccForm');
					f.submit();
				}
			} ]
		});
	}
	/**
	 * 删除
	 */
	function orderCxDeleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = orderCxDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			orderCxDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '您是否要删除当前角色？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path}/mgr/orderCx/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						orderCxDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
	   /**
	    * 批量导出
	    */
	   function exportorderCxFun(id) {
		   if (id == undefined) {//点击右键菜单才会触发这个
	      	   var rows = orderCxDataGrid.datagrid('getSelections');
			   if(rows.length==0){
				  id = "all"; 
			   }else{
				   id = rows[0].orderId;
		           for(j = 1; j < rows.length; j++) {
		          	 id = id + "," + rows[j].orderId;
		           }
			   }
		   }
		   window.open("${path }/mgr/orderCx/exportExcel?id="+id);
	   }
	/**
	 * 清除
	 */
	function orderCxCleanFun() {
		$('#orderCxSearchForm input').val('');
		orderCxDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function orderCxSearchFun() {
		orderCxDataGrid.datagrid('load', $.serializeObject($('#orderCxSearchForm')));
	}
	function change() {
		var s = $("#searchMore").css('display');
		if (s == 'none')
			$("#searchMore").attr("style", "display:block;");
		if (s == 'block')
			$("#searchMore").attr("style", "display:none;");
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false">
		<div style="height: 30px;">
			<img id="u162_img" class="img" width="100%" height="100%"
				src="${path}/static/style/images/u522.png" /> <span
				style="position: absolute; top: 7px; left: 6px; text-align: center;">搜索</span>
		</div>
		<form id="orderCxSearchForm">
			<div id="searchMore" style="text-align: left; display: none;">
				<table style="width: 80%; left: 0px;">
					<tr>
						<td style="text-align: right;">下单时间</td>
						<td style="text-align: left;"><input name="createDateBe"
							class="easyui-datebox" style="width: 30%;"
							value="${data.birthdayStr }"> 至 <input
							name="createDateEn" class="easyui-datebox" style="width: 30%;"
							value="${data.birthdayStr }"></td>
						<td style="text-align: right;">订单类型</td>
						<td><select name="orderType" class="text_sketch"
							data-label="关键字搜索">
								<option selected value="不限">不限</option>
								<option value="车辆保险">车辆保险</option>
								<option value="企业财产险">企业财产险</option>
								<option value="建筑工程险">建筑工程险</option>
								<option value="工程机械险">工程机械险</option>
								<option value="团意雇主险">团意雇主险</option>
								<option value="短期意外险">短期意外险</option>
								<option value="公众责任险">公众责任险</option>
								<option value="其它">其它</option>
						</select></td>
					</tr>
					<tr>
						<td style="text-align: right;">是否成交</td>
						<td style="text-align: left;"><select name="orderFlag"
							class="text_sketch" data-label="关键字搜索">
								<option selected value="不限">不限</option>
								<option value="01">是</option>
								<option value="02">否</option>
						</select></td>
						<td style="text-align: right;">录单员</td>
						<td style="text-align: left;"><select name="recorder"
							class="text_sketch" data-label="关键字搜索">
								<option selected value="不限">不限</option>
								<option value="车辆保险">车辆保险</option>
								<option value="其它">其它</option>
						</select></td>
					</tr>
					<tr>
						<td style="text-align: right;">保险公司</td>
						<td style="text-align: left;"><select
							name="insuranceCompanyId" class="text_sketch" data-label="关键字搜索">
								<option selected value="不限">不限</option>
								<option value="01">是</option>
								<option value="02">否</option>
						</select></td>
						<td style="text-align: right;">总商业险</td>
						<td style="text-align: left;"><input> 至 <input>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">净商业险</td>
						<td style="text-align: left;"><input> 至 <input>
						</td>
						<td style="text-align: right;">商业险税</td>
						<td style="text-align: left;"><input> 至 <input>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">交强险税</td>
						<td style="text-align: left;"><input> 至 <input>
						</td>
						<td style="text-align: right;">车船税</td>
						<td style="text-align: left;"><input> 至 <input>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">总保费</td>
						<td style="text-align: left;"><input> 至 <input>
						</td>
						<td style="text-align: right;">业务员</td>
						<td style="text-align: left;"><select id="u568_input"
							class="text_sketch" data-label="关键字搜索">
								<option selected value="不限">不限</option>
								<option value="车辆保险">车辆保险</option>
								<option value="企业财产险">企业财产险</option>
								<option value="建筑工程险">建筑工程险</option>
								<option value="工程机械险">工程机械险</option>
								<option value="团意雇主险">团意雇主险</option>
								<option value="短期意外险">短期意外险</option>
								<option value="公众责任险">公众责任险</option>
								<option value="其它">其它</option>
						</select></td>
					</tr>
					<tr>
						<td style="text-align: right;">业务员佣金比例</td>
						<td style="text-align: left;"><input> 至 <input>
						</td>
						<td style="text-align: right;">业务员佣金</td>
						<td style="text-align: left;"><input> 至 <input>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">投保方式</td>
						<td style="text-align: left;"><select id="u568_input"
							class="text_sketch" data-label="关键字搜索">
								<option selected value="不限">不限</option>
								<option value="01">是</option>
								<option value="02">否</option>
						</select></td>
						<td style="text-align: right;">佣金结算</td>
						<td style="text-align: left;"><select id="u568_input"
							class="text_sketch" data-label="关键字搜索">
								<option selected value="不限">不限</option>
								<option value="01">是</option>
								<option value="02">否</option>
						</select></td>
					</tr>
					<tr>
						<td style="text-align: right;">礼品结算</td>
						<td style="text-align: left;"><select id="u568_input"
							class="text_sketch" data-label="关键字搜索">
								<option selected value="不限">不限</option>
								<option value="01">是</option>
								<option value="02">否</option>
						</select></td>
						<td style="text-align: right;">出单渠道</td>
						<td style="text-align: left;"><select id="u568_input"
							class="text_sketch" data-label="关键字搜索">
								<option selected value="不限">不限</option>
								<option value="01">是</option>
								<option value="02">否</option>
						</select></td>
					</tr>
					<tr>
						<td style="text-align: right;">保险公司税前佣金比例</td>
						<td style="text-align: left;"><input> 至 <input>
						</td>
						<td style="text-align: right;">保险公司税前佣金</td>
						<td style="text-align: left;"><input> 至 <input>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">本公司毛利</td>
						<td style="text-align: left;"><input> 至 <input>
						</td>
						<td style="text-align: right;">业务员佣金对象</td>
						<td style="text-align: left;"><select id="u568_input"
							class="text_sketch" data-label="关键字搜索">
								<option selected value="不限">不限</option>
								<option value="车辆保险">车辆保险</option>
								<option value="企业财产险">企业财产险</option>
						</select></td>
					</tr>
					<tr>
						<td style="text-align: right;">佣金支付人</td>
						<td style="text-align: left;"><select id="u568_input"
							class="text_sketch" data-label="关键字搜索">
								<option selected value="不限">不限</option>
								<option value="车辆保险">车辆保险</option>
								<option value="企业财产险">企业财产险</option>
						</select></td>
						<td style="text-align: right;">佣金实际支付金额</td>
						<td style="text-align: left;"><input> 至 <input>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">保险公司开票金额</td>
						<td style="text-align: left;"><input> 至 <input>
						</td>
						<td style="text-align: right;">最后修改人</td>
						<td style="text-align: left;"><select id="u568_input"
							class="text_sketch" data-label="关键字搜索">
								<option selected value="不限">不限</option>
								<option value="车辆保险">车辆保险</option>
								<option value="企业财产险">企业财产险</option>
								<option value="建筑工程险">建筑工程险</option>
								<option value="工程机械险">工程机械险</option>
								<option value="团意雇主险">团意雇主险</option>
								<option value="短期意外险">短期意外险</option>
								<option value="公众责任险">公众责任险</option>
								<option value="其它">其它</option>
						</select></td>
					</tr>
					<tr>
						<td style="text-align: right;">最后修改时间</td>
						<td style="text-align: left;"><input name=""
							class="easyui-datebox" style="width: 30%;"
							value="${data.birthdayStr }"> 至 <input name=""
							class="easyui-datebox" style="width: 30%;"
							value="${data.birthdayStr }"></td>
					</tr>
				</table>
			</div>
			<div>
				<table style="left: 200px;">
					<tr>
						<th>关键字搜索</th>
						<td><select name="name" class="easyui-combobox"
							style="width: 100px">
								<option value="orderId">订单号</option>
								<option value="mtelphone">车牌号</option>
								<option value="wechat">车主</option>
								<option value="salesMan">业务员</option>
								<option value="recorder">出单员</option>
						</select></td>
						<td><input name="val" style="width: 200px" placeholder="搜索内容" />
						</td>
						<td><a href="javascript:void(0);" class="easyui-linkbutton"
							data-options="iconCls:'fi-magnifying-glass',plain:true"
							onclick="orderCxSearchFun();">查询</a></td>
						<td>
							<p onclick="change()">
								<span
									style="font-family: 'Applied Font Regular', 'Applied Font'; text-decoration: underline;">更多搜索项</span>
							</p>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>

	<div data-options="region:'center',border:false" id="orderCx">
		<table id="orderCxDataGrid" data-options="fit:true,border:false"></table>
	</div>
</div>
<div id="orderCxToolbar"
	style="display: none; background-color: #CCCCCC; text-align: right;">
	<shiro:hasPermission name="/orderCx/add">
		<a onclick="orderCxAddFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'fi-page-add'">新增</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="/orderCx/add">
		<select class="easyui-combobox" data-options="valueField:'id', textField:'text', panelHeight:'auto', data: [{ 'text': '批量操作', 'id': '' ,selected:true},{ 'text': '成交/未成交', 'id': 'orderFlag' },{ 'text': '打单/未打单', 'id': 'printFlag' },{ 'text': '礼品发放', 'id': 'giftFlag' },{ 'text': '结账设置', 'id': 'checkoutSet' },{ 'text': '保险公司结账', 'id': 'checkoutFlag' }]" id='batch' style='width: 100px;background-color: #cccccc;'>
		</select>
	</shiro:hasPermission>
	<shiro:hasPermission name="/orderCx/add">
		<a onclick="exportorderCxFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'fi-plus fi-print'">批量导出EXCEL</a>
	</shiro:hasPermission>
</div>