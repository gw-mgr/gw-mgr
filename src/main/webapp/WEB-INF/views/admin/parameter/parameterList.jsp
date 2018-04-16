<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var parameterDataGrid;
    $(function() {
        parameterDataGrid = $('#parameterDataGrid').datagrid({
        url : '${path}/mgr/parameter/dataGrid?type=01',
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
        frozenColumns : [ [ 
        {
            width : '400',
            title : '投保方式',
            field : 'name',
            align : 'center',
            sortable : true
        }, {
            field : 'action',
            title : '操作',
            align : 'center',
            width : 400,
            formatter : function(value, row, index) {
                var str = '';
                <shiro:hasPermission name="/parameter/delete">
                    str += $.formatString('<a href="javascript:void(0)" class="parameter-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="parameterDeleteFun(\'{0}\');" >删除</a>', row.id);
                </shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.parameter-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        toolbar : '#parameterToolbar'
    });
});

/**
 * 添加框
 * @param url
 */
function parameterAddFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 400,
        height : 300,
        href : '${path}/mgr/parameter/addPage',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = parameterDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#parameterAddForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function parameterDeleteFun(id) {
     if (id == undefined) {//点击右键菜单才会触发这个
         var rows = parameterDataGrid.datagrid('getSelections');
         id = rows[0].id;
     } else {//点击操作里面的删除图标会触发这个
         parameterDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前角色？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/mgr/parameter/delete', {
                 id : id
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     parameterDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}


/**
 * 清除
 */
function parameterCleanFun() {
    $('#parameterSearchForm input').val('');
    parameterDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function parameterSearchFun() {
     parameterDataGrid.datagrid('load', $.serializeObject($('#parameterSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 60px; overflow: hidden;background-color: #fff">
    	<div style="height: 30px;">
			<img id="u162_img" class="img" width="100%" height="100%"
				src="${path}/static/style/images/u522.png" /> <span
				style="position: absolute; top: 7px; left: 6px; text-align: center;"></span>
		</div>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="parameterDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="parameterToolbar" style="display: none; background-color: #CCCCCC; text-align: right;">
    <shiro:hasPermission name="/parameter/add">
        <a onclick="parameterAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-page-add'">添加</a>
    </shiro:hasPermission>
</div>