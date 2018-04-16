<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var tInsurerDataGrid;
    $(function() {
        tInsurerDataGrid = $('#tInsurerDataGrid').datagrid({
        url : '${path}/mgr/tInsurer/dataGrid',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : false,
		selectOnCheck : true,
		checkOnSelect : true,
        idField : 'id',
        sortName : 'id',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        frozenColumns : [ [ {
            width : '400',
            title : '保险公司名称',
            field : 'insurerName',
            align : 'center',
            sortable : true
        },{
            width : '400',
            title : '保险公司报案电话',
            field : 'insurerPhone',
            align : 'center',
            sortable : true
        }, {
            field : 'action',
            title : '操作',
            align : 'center',
            width : 200,
            formatter : function(value, row, index) {
                var str = '';
                <shiro:hasPermission name="/tInsurer/delete">
                    str += $.formatString('<a href="javascript:void(0)" class="tInsurer-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="tInsurerDeleteFun(\'{0}\');" >删除</a>', row.id);
                </shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.tInsurer-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.tInsurer-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        toolbar : '#tInsurerToolbar'
    });
});

/**
 * 添加框
 * @param url
 */
function tInsurerAddFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 500,
        height : 400,
        href : '${path}/mgr/tInsurer/addPage',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = tInsurerDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#tInsurerAddForm');
                f.submit();
            }
        } ]
    });
}

/**
 * 删除
 */
 function tInsurerDeleteFun(id) {
     if (id == undefined) {//点击右键菜单才会触发这个
         var rows = tInsurerDataGrid.datagrid('getSelections');
         id = rows[0].id;
     } else {//点击操作里面的删除图标会触发这个
         tInsurerDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前角色？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/mgr/tInsurer/delete', {
                 id : id
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     tInsurerDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false">
        <table id="tInsurerDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="tInsurerToolbar" style="display: none; background-color: #CCCCCC; text-align: right;">
    <shiro:hasPermission name="/tInsurer/add">
        <a onclick="tInsurerAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-page-add'">添加</a>
    </shiro:hasPermission>
</div>