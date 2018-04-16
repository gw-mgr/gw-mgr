<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var policyholderDataGrid;
    $(function() {
        policyholderDataGrid = $('#policyholderDataGrid').datagrid({
        url : '${path}/mgr/policyholder/dataGrid',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : false,
		selectOnCheck: true,
		checkOnSelect: true,
        idField : 'id',
        sortName : 'id',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        frozenColumns : [ [ {
        	field:'id',
			checkbox:true 
        }, {
        	width : '100',
            title : '姓名',
            field : 'userName',
            sortable : true ,
            align : 'center'
        }, {
        	width : '60',
            title : '车牌号',
            field : 'userName',
            sortable : true ,
            align : 'center'
        }, {
        	width : '60',
            title : '姓名',
            field : 'userName',
            sortable : true ,
            align : 'center'
        }, {
        	width : '60',
            title : '姓名',
            field : 'userName',
            sortable : true ,
            align : 'center'
        }, {
        	width : '60',
            title : '姓名',
            field : 'userName',
            sortable : true ,
            align : 'center'
        }, {
        	width : '60',
            title : '姓名',
            field : 'userName',
            sortable : true ,
            align : 'center'
        }, {
            field : 'action',
            title : '操作',
            width : 200,
            formatter : function(value, row, index) {
                var str = '';
                <shiro:hasPermission name="/policyholder/edit">
                    str += $.formatString('<a href="javascript:void(0)" class="policyholder-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="policyholderEditFun(\'{0}\');" >编辑</a>', row.id);
                </shiro:hasPermission>
                <shiro:hasPermission name="/policyholder/delete">
                    str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    str += $.formatString('<a href="javascript:void(0)" class="policyholder-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="policyholderDeleteFun(\'{0}\');" >删除</a>', row.id);
                </shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.policyholder-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.policyholder-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        toolbar : '#policyholderToolbar'
    });
});

/**
 * 添加框
 * @param url
 */
function policyholderAddFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 700,
        height : 600,
        href : '${path}/mgr/policyholder/addPage',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = policyholderDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#policyholderAddForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 编辑
 */
function policyholderEditFun(id) {
    if (id == undefined) {
        var rows = policyholderDataGrid.datagrid('getSelections');
        id = rows[0].id;
    } else {
        policyholderDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '编辑',
        width : 700,
        height : 600,
        href :  '${path}/mgr/policyholder/editPage?id=' + id,
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = policyholderDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#policyholderEditForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function policyholderDeleteFun(id) {
     if (id == undefined) {//点击右键菜单才会触发这个
         var rows = policyholderDataGrid.datagrid('getSelections');
         id = rows[0].id;
     } else {//点击操作里面的删除图标会触发这个
         policyholderDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前角色？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/mgr/policyholder/delete', {
                 id : id
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     policyholderDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}


/**
 * 清除
 */
function policyholderCleanFun() {
    $('#policyholderSearchForm input').val('');
    policyholderDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function policyholderSearchFun() {
     policyholderDataGrid.datagrid('load', $.serializeObject($('#policyholderSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 60px; overflow: hidden; background-color: #fff">
		<div style="height: 30px;">
			<img id="u162_img" class="img" width="100%" height="100%"
				src="${path}/static/style/images/u522.png" /> <span
				style="position: absolute; top: 7px; left: 6px; text-align: center;">搜索</span>
		</div>
		<form id="tProductManageSearchForm">
		<table>
				<tr>
					<th>关键字搜索</th>
					<td><select name="name" class="easyui-combobox"
						style="width: 100px">
							<option value="userName">姓名</option>
							<option value="mtelphone">手机</option>
					</select></td>
					<td><input name="val" style="width: 200px" placeholder="搜索内容" /></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'fi-magnifying-glass',plain:true"
						onclick="policyholderSearchFun();">搜索</a></td>
				</tr>
			</table>
		</form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="policyholderDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="policyholderToolbar" style="display: none; background-color: #CCCCCC; text-align: right;">
    <shiro:hasPermission name="/policyholder/add">
        <a onclick="policyholderAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-page-add'">添加</a>
    </shiro:hasPermission>
</div>