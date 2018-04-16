<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var messageDataGrid;
    $(function() {
        messageDataGrid = $('#messageDataGrid').datagrid({
        url : '${path}/mgr/message/dataGrid',
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
            width : '700',
            title : '信息内容',
            field : 'content',
            align : 'center',
            sortable : true
        }, {
            width : '140',
            title : '时间',
            align : 'center',
            field : 'createTime',
            sortable : true
        }, {
            field : 'action',
            title : '操作',
            align : 'center',
            width : 200,
            formatter : function(value, row, index) {
                var str = '';
                if (row.status == '01'){
                <shiro:hasPermission name="/message/sign">
                    str += $.formatString('<a href="javascript:void(0)" class="message-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="messageEditFun(\'{0}\');" >标记为已读</a>', row.id);
                </shiro:hasPermission>
                }
                if (row.status == '02'){
                <shiro:hasPermission name="/message/show">
                    str += $.formatString('<a href="javascript:void(0)" class="message-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-layout icon-blue\'" onclick="messageDeleteFun(\'{0}\');" >查看</a>', row.id);
                </shiro:hasPermission>
                }
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.message-easyui-linkbutton-edit').linkbutton({text:'标记为已读'});
            $('.message-easyui-linkbutton-del').linkbutton({text:'查看'});
        },
        toolbar : '#messageToolbar'
    });
});


/**
 * 删除
 */
 function messageDeleteFun(id) {
     if (id == undefined) {//点击右键菜单才会触发这个
         var rows = messageDataGrid.datagrid('getSelections');
         id = rows[0].id;
     } else {//点击操作里面的删除图标会触发这个
         messageDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前角色？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/message/delete', {
                 id : id
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     messageDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}


/**
 * 清除
 */
function messageCleanFun() {
    $('#messageSearchForm input').val('');
    messageDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function messageSearchFun() {
     messageDataGrid.datagrid('load', $.serializeObject($('#messageSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 60px; overflow: hidden;background-color: #fff">
    	<div style="height: 30px;">
			<img id="u162_img" class="img" width="100%" height="100%"
				src="${path}/static/style/images/u522.png" /> <span
				style="position: absolute; top: 7px; left: 6px; text-align: center;">搜索</span>
		</div>
        <form id="messageSearchForm">
            <table>
                <tr>
                    <th>消息类型:</th>
                    <td>
                    	<input name="type" type="radio" value=""/>未读消息
                    	<input name="type" type="radio" value=""/>订单消息
                    </td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="messageSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="messageCleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="messageDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="messageToolbar" style="display: none; background-color: #CCCCCC; text-align: right;">
    <shiro:hasPermission name="/message/signAll">
        <a onclick="messageAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-page-add'">全部标记为已读</a>
    </shiro:hasPermission>
</div>