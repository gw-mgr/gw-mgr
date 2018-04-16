<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var tProductManageCDDataGrid;
    $(function() {
    	tProductManageCDDataGrid = $('#tProductManageCDDataGrid').datagrid({
        url : '${path}/mgr/tProductManage/dataGrid?type=ROOTCD',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        idField : 'productId',
        sortName : 'productId',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        frozenColumns : [ [ {
            width : '250',
            title : '产品ID',
            field : 'productId',
            sortable : true
        }, {
            width : '250',
            title : '商品名称',
            field : 'productName',
            sortable : true
        }, {
            width : '120',
            title : '价格',
            field : 'origPrice',
            sortable : true
        }, {
            width : '120',
            title : '平台佣金',
            field : 'commission',
            sortable : true
        }, {
            width : '100',
            title : '状态',
            field : 'status',
            sortable : true,
            formatter : function(value, row) {
            	if (value == '01'){
					return '启用';
				}
				if (value == '02'){
					return '停用';
				}
            }
        }, {
            field : 'action',
            title : '操作',
            width : 200,
            formatter : function(value, row, index) {
                var str = '';
                <shiro:hasPermission name="/tProductManage/edit">
                    str += $.formatString('<a href="javascript:void(0)" class="tProductManage-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="tProductManageCDEditFun(\'{0}\');" >编辑</a>', row.productId);
                </shiro:hasPermission>
                <shiro:hasPermission name="/tProductManage/delete">
                    str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    str += $.formatString('<a href="javascript:void(0)" class="tProductManage-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="tProductManageCDDeleteFun(\'{0}\');" >删除</a>', row.productId);
                </shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.tProductManage-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.tProductManage-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        toolbar : '#tProductManageToolbar'
    });
});


/**
 * 编辑
 */
function tProductManageCDEditFun(id) {
    if (id == undefined) {
        var rows = tProductManageCDDataGrid.datagrid('getSelections');
        id = rows[0].id;
    } else {
    	tProductManageCDDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '编辑',
        width : 700,
        height : 600,
        href :  '${path}/mgr/tProductManage/editPage?type=Cd&id=' + id,
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = tProductManageCDDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#tProductManageCDEditForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function tProductManageCDDeleteFun(id) {
     if (id == undefined) {//点击右键菜单才会触发这个
         var rows = tProductManageCDDataGrid.datagrid('getSelections');
         id = rows[0].id;
     } else {//点击操作里面的删除图标会触发这个
    	 tProductManageCDDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前角色？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/mgr/tProductManage/delete', {
                 id : id
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     tProductManageCDDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}


/**
 * 清除
 */
function tProductManageCDCleanFun() {
    $('#tProductManageCDSearchForm input').val('');
    tProductManageCDDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function tProductManageCDSearchFun() {
	tProductManageCDDataGrid.datagrid('load', $.serializeObject($('#tProductManageCDSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 60px; overflow: hidden;background-color: #fff">
        <div style="height: 30px;">
			<img id="u162_img" class="img" width="100%" height="100%"
				src="${path}/static/style/images/u522.png" /> <span
				style="position: absolute; top: 7px; left: 6px; text-align: center;">搜索</span>
		</div>
        <form id="tProductManageCDSearchForm">
            <table>
                <tr>
                    <th>关键字搜索</th>
					<td><select name="name" class="easyui-combobox"
						style="width: 100px">
							<option value="productName">产品名称</option>
							<option value="productId">产品ID</option>
					</select></td>
					<td><input name="val" style="width: 200px" placeholder="搜索内容" /></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="tProductManageCDSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="tProductManageCDCleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="tProductManageCDDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
