<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var tCustomerBasicinfoDataGrid;
    $(function() {
        tCustomerBasicinfoDataGrid = $('#tCustomerBasicinfoDataGrid').datagrid({
        url : '${path}/mgr/tCustomerBasicinfo/dataGrid',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : false,
		selectOnCheck : true,
		checkOnSelect : true,
        idField : 'createTime',
        sortName : 'createTime',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        frozenColumns : [ [ 
        {
			field : 'ck',
			checkbox : true
		}, {
            width : '100',
            title : '姓名',
            field : 'userName',
            align : 'center',
            sortable : true
        }, {
            width : '150',
            title : '车牌',
            field : 'carNum',
            align : 'center',
            sortable : true
        }, {
            width : '250',
            title : '车架号码',
            field : 'carFrameNum',
            align : 'center',
            sortable : true
        }, {
            width : '300',
            title : '厂牌类型',
            field : 'changType',
            align : 'center',
            sortable : true
        }, {
            width : '150',
            title : '初登日期',
            field : 'registerTime',
            align : 'center',
            sortable : true
        }, {
            field : 'action',
            title : '操作',
            align : 'center',
            width : 200,
            formatter : function(value, row, index) {
                var str = '';
                <shiro:hasPermission name="/tCustomerBasicinfo/edit">
                    str += $.formatString('<a href="javascript:void(0)" class="tCustomerBasicinfo-easyui-linkbutton-show" data-options="plain:true,iconCls:\'fi-layout icon-blue\'" onclick="tCustomerBasicinfoShowFun(\'{0}\');" >查看</a>', row.userId);
                </shiro:hasPermission>
                <shiro:hasPermission name="/tCustomerBasicinfo/delete">
                    str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    str += $.formatString('<a href="javascript:void(0)" class="tCustomerBasicinfo-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="tCustomerBasicinfoDeleteFun(\'{0}\');" >删除</a>', row.userId);
                </shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.tCustomerBasicinfo-easyui-linkbutton-show').linkbutton({text:'查看'});
            $('.tCustomerBasicinfo-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        toolbar : '#tCustomerBasicinfoToolbar'
    });
});


/**
 * 查看
 */
function tCustomerBasicinfoShowFun(id) {
	if (id == undefined) {
        var rows = tCustomerBasicinfoDataGrid.datagrid('getSelections');
        id = rows[0].userId;
    } else {
    	tCustomerBasicinfoDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '查看',
        width : 700,
        height : 600,
        href :  '${path}/mgr/tCustomerBasicinfo/showPage?id=' + id
    });
}


/**
 * 删除
 */
 function tCustomerBasicinfoDeleteFun(id) {
	 if (id == undefined) {//点击右键菜单才会触发这个
         var rows = tCustomerBasicinfoDataGrid.datagrid('getSelections');
         id = rows[0].userId;
         for(j = 1; j < rows.length; j++) {
        	 id = id + "," + rows[j].userId;
         }
     } else {//点击操作里面的删除图标会触发这个
    	 tCustomerBasicinfoDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前用户？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/mgr/tCustomerBasicinfo/delete', {
                 id : id
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     tCustomerBasicinfoDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}
 /**
  * 批量导出
  */
 function exportCustomerFun(id) {
	   if (id == undefined) {//点击右键菜单才会触发这个
    	   var rows = tCustomerBasicinfoDataGrid.datagrid('getSelections');
		   if(rows.length==0){
			  id = "all"; 
		   }else{
			   id = rows[0].userId;
	           for(j = 1; j < rows.length; j++) {
	          	 id = id + "," + rows[j].userId;
	           }
		   }
	   }
	   window.open("${path }/mgr/tCustomerBasicinfo/exportExcel?id="+id);
 }

/**
 * 清除
 */
function tCustomerBasicinfoCleanFun() {
    $('#tCustomerBasicinfoSearchForm input').val('');
    tCustomerBasicinfoDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function tCustomerBasicinfoSearchFun() {
     tCustomerBasicinfoDataGrid.datagrid('load', $.serializeObject($('#tCustomerBasicinfoSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 60px; overflow: hidden;background-color: #fff">
    	<div style="height: 30px;">
			<img id="u162_img" class="img" width="100%" height="100%"
				src="${path}/static/style/images/u522.png" /> <span
				style="position: absolute; top: 7px; left: 6px; text-align: center;">搜索</span>
		</div>
        <form id="tCustomerBasicinfoSearchForm">
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
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="tCustomerBasicinfoSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="tCustomerBasicinfoCleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="tCustomerBasicinfoDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="tCustomerBasicinfoToolbar" style="display: none; background-color: #CCCCCC; text-align: right;">
    <shiro:hasPermission name="/tCustomerBasicinfo/add">
        <a onclick="tCustomerBasicinfoAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-page-add'">添加</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="/tCustomerBasicinfo/delete">
        <a onclick="tCustomerBasicinfoDeleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus fi-x icon'">批量删除</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="/tCustomerBasicinfo/edit">
        <a onclick="exportCustomerFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus fi-print'">批量导出EXCEL</a>
    </shiro:hasPermission>
</div>