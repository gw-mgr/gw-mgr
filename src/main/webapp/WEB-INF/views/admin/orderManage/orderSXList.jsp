<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var orderSxDataGrid;
    var data = [];
    $(function() {
        orderSxDataGrid = $('#orderSxDataGrid').datagrid({
        url : '${path}/mgr/orderSx/dataGrid',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : false,
		selectOnCheck: true,
		checkOnSelect: true,
        idField : 'orderId',
        sortName : 'createTime',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        frozenColumns : [ [ {
        	field:'id',
			checkbox:true 
        },{
            width : '200',
            title : '订单号',
            field : 'orderNo',
            align : 'center',
            sortable : true
        },{
            width : '140',
            title : '下单日期',
            field : 'createTime',
			align : 'center',
            sortable : true
        },{
        	width : '60',
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
        }] ],
		columns : [ [ {
            width : '100',
            title : '录单员',
            field : 'recorder',
            align : 'center',
            sortable : true
        },{
            width : '140',
            title : '保险公司',
            field : 'insuranceCompany',
            align : 'center',
            sortable : true
        },{
            width : '100',
            title : '投保人',
            field : 'policyholder',
            align : 'center',
            sortable : true
        },{
            width : '100',
            title : '主险',
            field : 'jzx',
            align : 'center',
            sortable : true
        },{
            width : '140',
            title : '附险1',
            field : 'jfx1',
            align : 'center',
            sortable : true
        },{
            width : '140',
            title : '附险2',
            field : 'jfx2',
            align : 'center',
            sortable : true
        },{
            width : '140',
            title : '总保费',
            field : 'bfhj',
            align : 'center',
            sortable : true
        },{
            width : '140',
            title : '业务员',
            field : 'salesManName',
            align : 'center',
            sortable : true
        },{
            width : '140',
            title : '业务员佣金',
            field : 'salesManCommission',
            align : 'center',
            sortable : true
        },{
            width : '140',
            title : '投保方式',
            field : 'tbfsName',
            align : 'center',
            sortable : true
        },{
            width : '140',
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
        },{
            width : '140',
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
        },{
            width : '140',
            title : '出单渠道',
            field : 'operatorName',
            align : 'center',
            sortable : true
        },{
            width : '140',
            title : '保险公司税前<br />佣金比例',
            field : 'bxgszxsqyjbl',
            align : 'center',
            sortable : true
        },{
            width : '140',
            title : '保险公司税前<br />佣金金额',
            field : 'bxgszxsqyj',
            align : 'center',
            sortable : true
        },{
            width : '140',
            title : '本公司毛利',
            field : 'bgsml',
            align : 'center',
            sortable : true
        },{
            width : '140',
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
        },{
            width : '140',
            title : '佣金实际<br />支付金额',
            field : 'sjzfyj',
            align : 'center',
            sortable : true
        },{
            width : '140',
            title : '保险公司开票<br />金额',
            field : 'bxgskpyj',
            align : 'center',
            sortable : true
        },{
            width : '140',
            title : '最后修改人',
            field : 'updateUserName',
            align : 'center',
            sortable : true
        },{
            width : '140',
            title : '最后修改时间',
            field : 'updateTime',
            align : 'center',
            sortable : true
        }, {
            field : 'action',
            title : '操作',
            align : 'center',
            width : 460,
            formatter : function(value, row, index) {
            	var str = '';
            	if (row.orderFlag == '01'){
            		<shiro:hasPermission name="/orderSx/show">
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-show" data-options="plain:true,iconCls:\'fi-layout icon-blue\'" onclick="orderSxShowFun(\'{0}\');" >查看</a>',
    								row.orderId);
    				</shiro:hasPermission>
    				<shiro:hasPermission name="/orderSx/edit">
    				str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="orderSxEditFun(\'{0}\');" >编辑</a>',
    								row.orderId);
    				</shiro:hasPermission>
    				<shiro:hasPermission name="/orderSx/change">
    				str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-change" data-options="plain:true,iconCls:\'fi-page-filled icon-blue\'" onclick="orderSxChangeFun(\'{0}\');" >变更</a>',
    								row.orderId);
    				</shiro:hasPermission>
    				<shiro:hasPermission name="/orderSx/delete">
    				str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="orderSxDeleteFun(\'{0}\');" >删除</a>',
    								row.orderId);
    				</shiro:hasPermission>
            	}
            	if (row.orderFlag == '02' && row.commissionFlag == '01' && row.checkoutFlag == '01'){
            		<shiro:hasPermission name="/orderSx/show">
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-show" data-options="plain:true,iconCls:\'fi-layout icon-blue\'" onclick="orderSxShowFun(\'{0}\');" >查看</a>',
    								row.orderId);
    				</shiro:hasPermission>
    				<shiro:hasPermission name="/orderSx/edit">
    				str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="orderSxEditFun(\'{0}\');" >编辑</a>',
    								row.orderId);
    				</shiro:hasPermission>
    				<shiro:hasPermission name="/orderSx/change">
    				str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-change" data-options="plain:true,iconCls:\'fi-page-filled icon-blue\'" onclick="orderSxChangeFun(\'{0}\');" >变更</a>',
    								row.orderId);
    				</shiro:hasPermission>
    				<shiro:hasPermission name="/orderSx/pay">
    				str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-pay" data-options="plain:true,iconCls:\'fi-paypal icon-blue\'" onclick="orderSxPayFun(\'{0}\');" >支付佣金</a>',
    								row.orderId);
    				</shiro:hasPermission>
    				<shiro:hasPermission name="/orderSx/check">
    				str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-check" data-options="plain:true,iconCls:\'fi-credit-card icon-blue\'" onclick="orderSxCheckFun(\'{0}\');" >结账设置</a>',
    								row.orderId);
    				</shiro:hasPermission>
    				
    				<shiro:hasPermission name="/orderSx/delete">
    				str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="orderSxDeleteFun(\'{0}\');" >删除</a>',
    								row.orderId);
    				</shiro:hasPermission>
            	}
            	if (row.orderFlag == '02' && row.commissionFlag == '01' && row.checkoutFlag == '02'){
            		<shiro:hasPermission name="/orderSx/show">
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-show" data-options="plain:true,iconCls:\'fi-layout icon-blue\'" onclick="orderSxShowFun(\'{0}\');" >查看</a>',
    								row.orderId);
    				</shiro:hasPermission>
    				<shiro:hasPermission name="/orderSx/edit">
    				str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="orderSxEditFun(\'{0}\');" >编辑</a>',
    								row.orderId);
    				</shiro:hasPermission>
    				<shiro:hasPermission name="/orderSx/pay">
    				str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-pay" data-options="plain:true,iconCls:\'fi-paypal icon-blue\'" onclick="orderSxPayFun(\'{0}\');" >支付佣金</a>',
    								row.orderId);
    				</shiro:hasPermission>
    				<shiro:hasPermission name="/orderSx/delete">
    				str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="orderSxDeleteFun(\'{0}\');" >删除</a>',
    								row.orderId);
    				</shiro:hasPermission>
            	}
            	if (row.orderFlag == '02' && row.commissionFlag == '02' && row.checkoutFlag == '01'){
            		<shiro:hasPermission name="/orderSx/show">
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-show" data-options="plain:true,iconCls:\'fi-layout icon-blue\'" onclick="orderSxShowFun(\'{0}\');" >查看</a>',
    								row.orderId);
    				</shiro:hasPermission>
    				<shiro:hasPermission name="/orderSx/change">
    				str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-change" data-options="plain:true,iconCls:\'fi-page-filled icon-blue\'" onclick="orderSxChangeFun(\'{0}\');" >变更</a>',
    								row.orderId);
    				</shiro:hasPermission>
    				<shiro:hasPermission name="/orderSx/check">
    				str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-check" data-options="plain:true,iconCls:\'fi-credit-card icon-blue\'" onclick="orderSxCheckFun(\'{0}\');" >结账设置</a>',
    								row.orderId);
    				</shiro:hasPermission>
    				
    				<shiro:hasPermission name="/orderSx/delete">
    				str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="orderSxDeleteFun(\'{0}\');" >删除</a>',
    								row.orderId);
    				</shiro:hasPermission>
            	}
            	if (row.orderFlag == '02' && row.commissionFlag == '02' && row.checkoutFlag == '02'){
            		<shiro:hasPermission name="/orderSx/show">
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-show" data-options="plain:true,iconCls:\'fi-layout icon-blue\'" onclick="orderSxShowFun(\'{0}\');" >查看</a>',
    								row.orderId);
    				</shiro:hasPermission>
    				<shiro:hasPermission name="/orderSx/delete">
    				str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
    				str += $
    						.formatString(
    								'<a href="javascript:void(0)" class="orderIns-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="orderSxDeleteFun(\'{0}\');" >删除</a>',
    								row.orderId);
    				</shiro:hasPermission>
            	}
				return str;
            }
        } ] ],
        onLoadSuccess:function(data){
        	$('.orderIns-easyui-linkbutton-show').linkbutton({text : '查看'});
			$('.orderIns-easyui-linkbutton-edit').linkbutton({text : '编辑'});
			$('.orderIns-easyui-linkbutton-change').linkbutton({text : '变更'});
			$('.orderIns-easyui-linkbutton-pay').linkbutton({text : '支付佣金'});
			$('.orderIns-easyui-linkbutton-check').linkbutton({text : '结账设置'});
			$('.orderIns-easyui-linkbutton-del').linkbutton({text : '删除'});
        },
        toolbar : '#orderSxToolbar',
        onSelect : function(rowIndex, rowData){  //用于解决点击某行不会高亮
            var fl = rowData.checkoutFlag;
            data.length = 0;
            data.push({ 'text': '批量操作', 'id': '', selected:true},{ 'text': '成交/未成交', 'id': 'orderFlag' },{ 'text': '打单/未打单', 'id': 'printFlag' },{ 'text': '礼品发放', 'id': 'giftFlag' });
			 if(fl == 02){
				 $('#batchSx').combobox('loadData', {});
				 $("#batchSx").combobox("loadData", data);
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
	        	 $('#batchSx').combobox('loadData', {});
				 $("#batchSx").combobox("loadData", data);
	         }  
       }
    });
    $("#batchSx").combobox({
    	onChange: function (n,o) {
		var val = $('#batchSx').combobox('getValue');
		if(val != ''){
			var rows = orderSxDataGrid.datagrid('getSelections');
			var id;
			if(rows.length>0){
				var id = rows[0].orderId;
				for(j = 1; j < rows.length; j++) {
				 id = id + "," + rows[j].orderId;
				}
				
				if(val == "checkoutSet"){
					parent.$.modalDialog({
						title : '编辑',
						width : 400,
						height : 300,
						href : '${path}/mgr/orderSx/accPage?id=' + id,
						buttons : [ {
							text : '确定',
							handler : function() {
								parent.$.modalDialog.openner_dataGrid = orderSxDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
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
							$.post('${path}/mgr/orderSx/changeStatus', {
								id : id,
								cloumn : val
							}, function(result) {
								if (result.success) {
									parent.$.messager.alert('提示', result.msg, 'info');
									orderSxDataGrid.datagrid('reload');
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
function orderSxAddFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 750,
        height : 600,
        href : '${path}/mgr/orderSx/addPage',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = orderSxDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#orderSxAddForm');
                f.submit();
            }
        } ]
    });
}

/**
 * 查看框
 * @param url
 */
function orderSxShowFun(id){
	if (id == undefined) {
		var rows = orderSxDataGrid.datagrid('getSelections');
		id = rows[0].id;
	} else {
		orderSxDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
	}
	parent.$.modalDialog({
		title : '编辑',
		width : 750,
		height : 600,
		href : '${path}/mgr/orderSx/show?id=' + id,
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
function orderSxEditFun(id) {
    if (id == undefined) {
        var rows = orderSxDataGrid.datagrid('getSelections');
        id = rows[0].id;
    } else {
        orderSxDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '编辑',
        width : 750,
        height : 600,
        href :  '${path}/mgr/orderSx/editPage?id=' + id,
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = orderSxDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#orderSxEditForm');
                f.submit();
            }
        } ]
    });
}

/**
 * 变更
 */
function orderSxChangeFun(id) {
	if (id == undefined) {
		var rows = orderSxDataGrid.datagrid('getSelections');
		id = rows[0].id;
	} else {
		orderSxDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
	}
	parent.$.modalDialog({
		title : '编辑',
		width : 700,
		height : 600,
		href : '${path}/mgr/orderSx/changePage?id=' + id,
		buttons : [ {
			text : '确定',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = orderSxDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
				var f = parent.$.modalDialog.handler
						.find('#orderSxChangeForm');
				f.submit();
			}
		} ]
	});
}
/**
 * 支付设置
 */
function orderSxPayFun(id) {
	if (id == undefined) {
		var rows = orderSxDataGrid.datagrid('getSelections');
		id = rows[0].id;
	} else {
		orderSxDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
	}
	parent.$.modalDialog({
		title : '编辑',
		width : 700,
		height : 600,
		href : '${path}/mgr/orderSx/payPage?id=' + id,
		buttons : [ {
			text : '确定',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = orderSxDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
				var f = parent.$.modalDialog.handler
						.find('#orderSxPayForm');
				f.submit();
			}
		} ]
	});
}

/**
 * 结账设置
 */
function orderSxCheckFun(id) {
	if (id == undefined) {
		var rows = orderSxDataGrid.datagrid('getSelections');
		id = rows[0].id;
	} else {
		orderSxDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
	}
	parent.$.modalDialog({
		title : '编辑',
		width : 700,
		height : 600,
		href : '${path}/mgr/orderSx/accPage?id=' + id,
		buttons : [ {
			text : '确定',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = orderSxDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
				var f = parent.$.modalDialog.handler
						.find('#orderSxAccForm');
				f.submit();
			}
		} ]
	});
}

/**
 * 删除
 */
 function orderSxDeleteFun(id) {
     if (id == undefined) {//点击右键菜单才会触发这个
         var rows = orderSxDataGrid.datagrid('getSelections');
         id = rows[0].id;
     } else {//点击操作里面的删除图标会触发这个
         orderSxDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前角色？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/mgr/orderSxRx/delete', {
                 id : id
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     orderSxDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}

 /**
  * 批量导出
  */
 function exportorderSxFun(id) {
	   if (id == undefined) {//点击右键菜单才会触发这个
    	   var rows = orderSxDataGrid.datagrid('getSelections');
		   if(rows.length==0){
			  id = "all"; 
		   }else{
			   id = rows[0].orderId;
	           for(j = 1; j < rows.length; j++) {
	          	 id = id + "," + rows[j].orderId;
	           }
		   }
	   }
	   window.open("${path }/mgr/orderSx/exportExcel?id="+id);
 }

/**
 * 清除
 */
function orderSxCleanFun() {
    $('#orderSxSearchForm input').val('');
    orderSxDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function orderSxSearchFun() {
     orderSxDataGrid.datagrid('load', $.serializeObject($('#orderSxSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false">
    		<div style="height: 30px;">
			<img id="u162_img" class="img" width="100%" height="100%"
				src="${path}/static/style/images/u522.png" /> <span
				style="position: absolute; top: 7px; left: 6px; text-align: center;">搜索</span>
		</div>
        <form id="orderSxSearchForm">
        	<div style="text-align: left; display: none;">
        		<table style="width: 80%;left: 0px;">
        			<tr>
        				<td style="text-align: right;">下单时间</td>
        				<td style="text-align: left;"><input name=""  class="easyui-datebox"  style="width:30%;"  value="${data.birthdayStr }">
        				至<input name="data.birthdayStr"  class="easyui-datebox"  style="width:30%;"  value="${data.birthdayStr }"></td>
        			</tr>
        			<tr>
        				<td style="text-align: right;">是否成交</td>
        				<td style="text-align: left;"><select id="u568_input" class="text_sketch" data-label="关键字搜索">
				          <option selected value="不限">不限</option>
				          <option value="01">是</option>
				          <option value="02">否</option>
				        </select></td>
        				<td style="text-align: right;">录单员</td>
        				<td style="text-align: left;"><select id="u568_input" class="text_sketch" data-label="关键字搜索">
				          <option selected value="不限">不限</option>
				          <option value="车辆保险">车辆保险</option>
				          <option value="其它">其它</option>
				        </select></td>
        			</tr>
        			<tr>
        				<td style="text-align: right;">保险公司</td>
        				<td style="text-align: left;"><select id="u568_input" class="text_sketch" data-label="关键字搜索">
				          <option selected value="不限">不限</option>
				          <option value="01">是</option>
				          <option value="02">否</option>
				        </select></td>
        				<td style="text-align: right;">主险</td>
        				<td style="text-align: left;"><input>至<input></td>
        			</tr>
        			<tr>
        				<td style="text-align: right;">净主险</td>
        				<td style="text-align: left;"><input>至<input></td>
        				<td style="text-align: right;">主险税</td>
        				<td style="text-align: left;"><input>至<input></td>
        			</tr>
        			<tr>
        				<td style="text-align: right;">附险1税</td>
        				<td style="text-align: left;"><input>至<input></td>
        				<td style="text-align: right;">附险2税</td>
        				<td style="text-align: left;"><input>至<input></td>
        			</tr>
        			<tr>
        				<td style="text-align: right;">总保费</td>
        				<td style="text-align: left;"><input>至<input></td>
        				<td style="text-align: right;">业务员</td>
        				<td style="text-align: left;"><select id="u568_input" class="text_sketch" data-label="关键字搜索">
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
        				<td style="text-align: left;"><input>至<input></td>
        				<td style="text-align: right;">业务员佣金</td>
        				<td style="text-align: left;"><input>至<input></td>
        			</tr>
        			<tr>
        				<td style="text-align: right;">投保方式</td>
        				<td style="text-align: left;"><select id="u568_input" class="text_sketch" data-label="关键字搜索">
				          <option selected value="不限">不限</option>
				          <option value="01">是</option>
				          <option value="02">否</option>
				        </select></td>
        				<td style="text-align: right;">佣金结算</td>
        				<td style="text-align: left;"><select id="u568_input" class="text_sketch" data-label="关键字搜索">
				         <option selected value="不限">不限</option>
				          <option value="01">是</option>
				          <option value="02">否</option>
				        </select></td>
        			</tr>
        			<tr>
        				<td style="text-align: right;">礼品结算</td>
        				<td style="text-align: left;"><select id="u568_input" class="text_sketch" data-label="关键字搜索">
				         <option selected value="不限">不限</option>
				          <option value="01">是</option>
				          <option value="02">否</option>
				        </select></td>
        				<td style="text-align: right;">出单渠道</td>
        				<td style="text-align: left;"><select id="u568_input" class="text_sketch" data-label="关键字搜索">
				          <option selected value="不限">不限</option>
				          <option value="01">是</option>
				          <option value="02">否</option>
				        </select></td>
        			</tr>
        			<tr>
        				<td style="text-align: right;">保险公司税前佣金比例</td>
        				<td style="text-align: left;"><input>至<input></td>
        				<td style="text-align: right;">保险公司税前佣金</td>
        				<td style="text-align: left;"><input>至<input></td>
        			</tr>
        			<tr>
        				<td style="text-align: right;">本公司毛利</td>
        				<td style="text-align: left;"><input>至<input></td>
        				<td style="text-align: right;">业务员佣金对象</td>
        				<td style="text-align: left;"><select id="u568_input" class="text_sketch" data-label="关键字搜索">
				          <option selected value="不限">不限</option>
				          <option value="车辆保险">车辆保险</option>
				          <option value="企业财产险">企业财产险</option>
				        </select></td>
        			</tr>
        			<tr>
        				<td style="text-align: right;">佣金支付人</td>
        				<td style="text-align: left;"><select id="u568_input" class="text_sketch" data-label="关键字搜索">
				          <option selected value="不限">不限</option>
				          <option value="车辆保险">车辆保险</option>
				          <option value="企业财产险">企业财产险</option>
				        </select></td>
        				<td style="text-align: right;">佣金实际支付金额</td>
        				<td style="text-align: left;"><input>至<input></td>
        			</tr>
        			<tr>
        				<td style="text-align: right;">保险公司开票金额</td>
        				<td style="text-align: left;"><input>至<input></td>
        				<td style="text-align: right;">最后修改人</td>
        				<td style="text-align: left;"><select id="u568_input" class="text_sketch" data-label="关键字搜索">
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
        				<td style="text-align: left;"><input name=""  class="easyui-datebox"  style="width:30%;"  value="${data.birthdayStr }">
        				至<input name=""  class="easyui-datebox"  style="width:30%;"  value="${data.birthdayStr }"></td>
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
								<option value="policyholder_id">投保人</option>
								<option value="salesMan">业务员</option>
								<option value="recorder">出单员</option>
						</select></td>
						<td><input name="val" style="width: 200px" placeholder="搜索内容" /></td>
	                    <td>
	                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="orderSxSearchFun();">查询</a>
	                    </td>
	                    <td><p><span style="font-family:'Applied Font Regular', 'Applied Font';text-decoration:underline;">更多搜索项</span></p></td>
	                </tr>
	            </table>
        	</div>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="orderSxDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="orderSxToolbar" style="display: none; background-color: #CCCCCC; text-align: right;">
    <shiro:hasPermission name="/orderSx/add">
        <a onclick="orderSxAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-page-add'">添加</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="/orderSx/add">
		<select class="easyui-combobox" data-options="valueField:'id', textField:'text', panelHeight:'auto', data: [{ 'text': '批量操作', 'id': '' ,selected:true},{ 'text': '成交/未成交', 'id': 'orderFlag' },{ 'text': '打单/未打单', 'id': 'printFlag' },{ 'text': '礼品发放', 'id': 'giftFlag' },{ 'text': '结账设置', 'id': 'checkoutSet' },{ 'text': '保险公司结账', 'id': 'checkoutFlag' }]" id='batchSx' style='width: 100px'>
		</select>
	</shiro:hasPermission>
	<shiro:hasPermission name="/orderSx/add">
		<a onclick="exportorderSxFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'fi-plus fi-print'">批量导出EXCEL</a>
	</shiro:hasPermission>
</div>