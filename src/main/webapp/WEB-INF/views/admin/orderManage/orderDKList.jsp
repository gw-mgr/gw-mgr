<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var orderInfoDKDataGrid;
    $(function() {
        orderInfoDKDataGrid = $('#orderInfoDKDataGrid').datagrid({
        url : '${path}/mgr/orderInfo/dataGrid?type=ROOTDK',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : false,
		selectOnCheck: true,
		checkOnSelect: true,
        idField : 'orderId',
        sortName : 'orderId',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        frozenColumns : [ [ {
        	field:'id',
			checkbox:true 
        },{
            width : '200',
            title : '订单号',
            field : 'orderId',
            sortable : true
        },{
            width : '100',
            title : '下单时间',
            field : 'createTime',
            sortable : true
        },{
            width : '100',
            title : '用户姓名',
            field : 'userName',
            sortable : true
        },{
            width : '150',
            title : '产品名称',
            field : 'productName',
            sortable : true
        },{
            width : '100',
            title : '贷款金额',
            field : 'orderMoney',
            sortable : true
        },{
            width : '100',
            title : '到期日期',
            field : 'endDate',
            sortable : true
        }, {
            width : '80',
            title : '状态',
            field : 'orderFlag',
            sortable : true,
            formatter : function(value, row) {
            	if (value == '01'){
					return '未接单';
				}
				if (value == '02'){
					return '接单未放款';
				}
				if (value == '03'){
					return '已放款';
				}
				if (value == '04'){
					return '已收回';
				}
            }
        }, {
            width : '100',
            title : '录单员',
            field : 'recorder',
            sortable : true
        }, {
            field : 'action',
            title : '操作',
            width : 200,
            formatter : function(value, row, index) {
                var str = '';
                <shiro:hasPermission name="/orderInfo/edit">
                    str += $.formatString('<a href="javascript:void(0)" class="orderInfo-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="orderInfoDKEditFun(\'{0}\');" >编辑</a>', row.orderId);
                </shiro:hasPermission>
                <shiro:hasPermission name="/orderInfo/delete">
                    str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    str += $.formatString('<a href="javascript:void(0)" class="orderInfo-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="orderInfoDKDeleteFun(\'{0}\');" >删除</a>', row.orderId);
                </shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.orderInfo-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.orderInfo-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        toolbar : '#orderInfoDKToolbar'
    });
});

/**
 * 添加框
 * @param url
 */
function orderInfoDKAddFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 700,
        height : 600,
        href : '${path}/mgr/orderInfo/addPage?type=Dk',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = orderInfoDKDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#orderInfoDKAddForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 编辑
 */
function orderInfoDKEditFun(id) {
    if (id == undefined) {
        var rows = orderInfoDataGrid.datagrid('getSelections');
        id = rows[0].id;
    } else {
        orderInfoDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '编辑',
        width : 700,
        height : 600,
        href :  '${path}/mgr/orderInfo/editPage?id=' + id,
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = orderInfoDKDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#orderInfoDKEditForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function orderInfoDKDeleteFun(id) {
     if (id == undefined) {//点击右键菜单才会触发这个
         var rows = orderInfoDataGrid.datagrid('getSelections');
         id = rows[0].id;
     } else {//点击操作里面的删除图标会触发这个
         orderInfoDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前角色？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/mgr/orderInfo/delete', {
                 id : id
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     orderInfoDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}


/**
 * 清除
 */
function orderInfoDKCleanFun() {
    $('#orderInfoDKSearchForm input').val('');
    orderInfoDKDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function orderInfoDKSearchFun() {
     orderInfoDKDataGrid.datagrid('load', $.serializeObject($('#orderInfoDKSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 60px; overflow: hidden; background-color: #fff">
    	<div style="height: 30px;">
			<img id="u162_img" class="img" width="100%" height="100%"
				src="${path}/static/style/images/u522.png" /> <span
				style="position: absolute; top: 7px; left: 6px; text-align: center;">搜索</span>
		</div>
        <form id="orderInfoDKSearchForm">
            <table>
                <tr>
                	<th>下单时间</th>
                	<td><input name="createDateBe"  class="easyui-datebox"  style="width:100px;" value="">
                	至<input name="createDateBe"  class="easyui-datebox"  style="width:100px;" value=""></td>
                    <th>关键字搜索</th>
					<td><select name="name" class="easyui-combobox"
						style="width: 100px">
							<option value=productName>产品名字</option>
							<option value="userName">用户姓名</option>
							<option value="orderId">订单号</option>
					</select></td>
					<td><input name="val" style="width: 200px" placeholder="搜索内容" /></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="orderInfoDKSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="orderInfoDKCleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="orderInfoDKDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="orderInfoDKToolbar" style="display: none; background-color: #CCCCCC; text-align: right;">
    <shiro:hasPermission name="/orderInfo/add">
        <a onclick="orderInfoDKAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-page-add'">添加</a>
    </shiro:hasPermission>
</div>