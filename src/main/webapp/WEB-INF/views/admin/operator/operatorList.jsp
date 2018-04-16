<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var operatorDataGrid;
    $(function() {
        operatorDataGrid = $('#operatorDataGrid').datagrid({
        url : '${path}/mgr/operator/dataGrid',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : false,
		selectOnCheck: true,
		checkOnSelect: true,
        idField : 'operId',
        sortName : 'operId',
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
            align : 'center',
            sortable : true
        } , {
            width : '150',
            title : '手机号',
            field : 'phone',
            align : 'center',
            sortable : true
        }, {
            width : '200',
            title : '邮箱',
            align : 'center',
            field : 'mail'
        }, {
            width : '100',
            title : '角色',
            align : 'center',
            field : 'roleName'
        }, {
            width : '320',
            title : '备注',
            align : 'center',
            field : 'description'
        }, {
        	width : '60',
            title : '状态',
            field : 'status',
            align : 'center',
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
            align : 'center',
            formatter : function(value, row, index) {
                var str = '';
                <shiro:hasPermission name="/operator/edit">
                    str += $.formatString('<a href="javascript:void(0)" class="operator-easyui-linkbutton-show" data-options="plain:true,iconCls:\'fi-layout icon-blue\'" onclick="operatorShowFun(\'{0}\');" >查看</a>', row.operId);
                </shiro:hasPermission>
                <shiro:hasPermission name="/operator/edit">
	                str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
	                str += $.formatString('<a href="javascript:void(0)" class="operator-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="operatorEditFun(\'{0}\');" >编辑</a>', row.operId);
	            </shiro:hasPermission>
                <shiro:hasPermission name="/operator/delete">
                    str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    str += $.formatString('<a href="javascript:void(0)" class="operator-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="operatorDeleteFun(\'{0}\');" >删除</a>', row.operId);
                </shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
        	$('.operator-easyui-linkbutton-show').linkbutton({text:'查看'});
            $('.operator-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.operator-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        toolbar : '#operatorToolbar'
    });
});

/**
 * 添加框
 * @param url
 */
function operatorAddFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 700,
        height : 600,
        href : '${path}/mgr/operator/addPage',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = operatorDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#operatorAddForm');
                f.submit();
            }
        } ]
    });
}

/**
 * 查看
 */
function operatorShowFun(id) {
	if (id == undefined) {
        var rows = operatorDataGrid.datagrid('getSelections');
        id = rows[0].id;
    } else {
    	operatorDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '查看',
        width : 700,
        height : 600,
        href :  '${path}/mgr/operator/showPage?id=' + id
    });
}

/**
 * 编辑
 */
function operatorEditFun(id) {
    if (id == undefined) {
        var rows = operatorDataGrid.datagrid('getSelections');
        id = rows[0].id;
    } else {
        operatorDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '编辑',
        width : 700,
        height : 600,
        href :  '${path}/mgr/operator/editPage?id=' + id,
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = operatorDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#operatorEditForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function operatorDeleteFun(id) {
     if (id == undefined) {//点击右键菜单才会触发这个
         var rows = operatorDataGrid.datagrid('getSelections');
         id = rows[0].operId;
         for(j = 1; j < rows.length; j++) {
        	 id = id + "," + rows[j].operId;
         }
     } else {//点击操作里面的删除图标会触发这个
         operatorDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前人员？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/mgr/operator/delete', {
                 id : id
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     operatorDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}

 /**
  * 批量导出
  */
 function exportOperatorFun(id) {
	   if (id == undefined) {//点击右键菜单才会触发这个
    	   var rows = operatorDataGrid.datagrid('getSelections');
		   if(rows.length==0){
			  id = "all"; 
		   }else{
			   id = rows[0].operId;
	           for(j = 1; j < rows.length; j++) {
	          	 id = id + "," + rows[j].operId;
	           }
		   }
	   }
	   window.open("${path }/mgr/operator/exportExcel?id="+id);
 }
 
/**
 * 清除
 */
function operatorCleanFun() {
    $('#operatorSearchForm input').val('');
    operatorDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function operatorSearchFun() {
     operatorDataGrid.datagrid('load', $.serializeObject($('#operatorSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 60px; overflow: hidden;background-color: #fff">
    <div style="height: 30px;">
	        <img id="u162_img" class="img" width="100%" height="100%" src="${path}/static/style/images/u522.png"/>
			<span style="position: absolute; top: 7px; left: 6px;text-align:center;">搜索</span>    
      	</div>
        <form id="operatorSearchForm">
            <table>
                <tr>
                    <th>关键字搜索</th>
                    <td>
                    	<select name="name" class="easyui-combobox" style="width: 100px">  
						    <option value="userName">姓名</option>  
						    <option value="telphone">手机</option>  
						</select>
                    </td>
                    <td><input name="val" style="width: 200px" placeholder="搜索内容"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="operatorSearchFun();">查询</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="operatorDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="operatorToolbar" style="display: none;background-color:#CCCCCC;text-align:right;">
    <shiro:hasPermission name="/operator/add">
        <a onclick="operatorAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="/operator/delete">
        <a onclick="operatorDeleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus fi-x icon'">批量删除</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="/operator/edit">
        <a onclick="exportOperatorFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus fi-print'">批量导出EXCEL</a>
    </shiro:hasPermission>
</div>