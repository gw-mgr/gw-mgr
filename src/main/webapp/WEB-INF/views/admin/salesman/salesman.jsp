<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
    var salesManDataGrid;
    $(function() {
        salesManDataGrid = $('#salesManDataGrid').datagrid({
        url : '${path}/mgr/salesman/dataGrid',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : false,
		selectOnCheck: true,
		checkOnSelect: true,
        idField : 'userId',
        sortName : 'createTime',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        frozenColumns : [ [ {
        	field:'id',
			checkbox:true 
        }, {
            width : '60',
            title : '姓名',
            field : 'userName',
            sortable : true ,
            align : 'center'
        } , {
            width : '100',
            title : '手机号',
            field : 'mobilePhone',
            sortable : true ,
            align : 'center'
        }, {
            width : '100',
            title : '微信号',
            field : 'wechatNum' ,
            align : 'center'
        }, {
            width : '80',
            title : '省份',
            field : 'province' ,
            align : 'center'
        }, {
            width : '300',
            title : '地址',
            field : 'userAddr' ,
            align : 'center'
        }, {
            width : '100',
            title : '保险公司',
            field : 'insurerName' ,
            align : 'center'
        }, {
            width : '120',
            title : '创建时间',
            field : 'createTime' ,
            align : 'center'
        }, {
        	width : '40',
            title : '状态',
            field : 'status',
            sortable : true, 
            align : 'center',
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
            align : 'center',
            width : 200,
            formatter : function(value, row, index) {
                var str = '';
                if (row.status == '01'){
                    <shiro:hasPermission name="/salesman/show">
              		 str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-show" data-options="plain:true,iconCls:\'fi-layout icon-blue\'" onclick="showSalesManFun(\'{0}\');" >查看</a>', row.userId);
	            </shiro:hasPermission>
	            <shiro:hasPermission name="/salesman/edit">
	                 str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
         		 	 str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="editSalesManFun(\'{0}\');" >编辑</a>', row.userId);
          		</shiro:hasPermission>
	            <shiro:hasPermission name="/salesman/disable">
	                str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
	                str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-disable" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="disableSalesManFun(\'{0}\');" >停用</a>', row.userId);
	            </shiro:hasPermission>
				}
				if (row.status == '02'){
	                <shiro:hasPermission name="/salesman/show">
              		 str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-show" data-options="plain:true,iconCls:\'fi-layout icon-blue\'" onclick="showSalesManFun(\'{0}\');" >查看</a>', row.userId);
	            </shiro:hasPermission>
	            <shiro:hasPermission name="/salesman/enable">
	                str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
	                str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-enable" data-options="plain:true,iconCls:\'fi-check icon-red\'" onclick="enableSalesManFun(\'{0}\');" >启用</a>', row.userId);
	            </shiro:hasPermission>
	            <shiro:hasPermission name="/salesman/delete">
		            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
		            str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="deleteSalesManFun(\'{0}\');" >删除</a>', row.userId);
		        </shiro:hasPermission>
				}
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
        	$('.role-easyui-linkbutton-show').linkbutton({text:'查看'});
            $('.role-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.role-easyui-linkbutton-disable').linkbutton({text:'停用'});
            $('.role-easyui-linkbutton-enable').linkbutton({text:'启用'});
            $('.role-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        toolbar : '#salesManToolbar'
    });
});

/**
 * 添加框
 * @param url
 */
function addSalesmanFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 700,
        height : 600,
        href : '${path}/mgr/salesman/addPage',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = salesManDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#salesmanAddForm');
                f.submit();
            }
        } ]
    });
}

/**
 * 查看
 */
function showSalesManFun(id) {
	if (id == undefined) {
        var rows = salesManDataGrid.datagrid('getSelections');
        id = rows[0].userId;
    } else {
        salesManDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '查看',
        width : 700,
        height : 600,
        href :  '${path}/mgr/salesman/showPage?id=' + id
    });
}

/**
 * 编辑
 */
function editSalesManFun(id) {
	if (id == undefined) {
        var rows = salesManDataGrid.datagrid('getSelections');
        id = rows[0].userId;
    } else {
        salesManDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '编辑',
        width : 700,
        height : 600,
        href :  '${path}/mgr/salesman/editPage?id=' + id,
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = salesManDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#salesmanEditForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function deleteSalesManFun(id) {
     if (id == undefined) {//点击右键菜单才会触发这个
    	 var rows = salesManDataGrid.datagrid('getSelections');
    	 id = rows[0].userId;
         for(j = 1; j < rows.length; j++) {
        	 id = id + "," + rows[j].userId;
         }
     } else {//点击操作里面的删除图标会触发这个
         salesManDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
      parent.$.messager.confirm('询问', '您是否要删除当前选择人员？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path }/mgr/salesman/delete', {
                 id : id
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     salesManDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     }); 
 }

 /**
  * 启用
  */
  function enableSalesManFun(id) {
      if (id == undefined) {//点击右键菜单才会触发这个
     	 var rows = salesManDataGrid.datagrid('getSelections');
     	 id = rows[0].userId;
          for(j = 1; j < rows.length; j++) {
         	 id = id + "," + rows[j].userId;
          }
      } else {//点击操作里面的删除图标会触发这个
          salesManDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
      }
       parent.$.messager.confirm('询问', '您是否要启用当前选择人员？', function(b) {
          if (b) {
              progressLoad();
              $.post('${path }/mgr/salesman/enable', {
                  id : id
              }, function(result) {
                  if (result.success) {
                      parent.$.messager.alert('提示', result.msg, 'info');
                      salesManDataGrid.datagrid('reload');
                  }
                  progressClose();
              }, 'JSON');
          }
      }); 
  }
 
  /**
   * 停用
   */
   function disableSalesManFun(id) {
       if (id == undefined) {//点击右键菜单才会触发这个
      	 var rows = salesManDataGrid.datagrid('getSelections');
      	 id = rows[0].userId;
           for(j = 1; j < rows.length; j++) {
          	 id = id + "," + rows[j].userId;
           }
       } else {//点击操作里面的删除图标会触发这个
           salesManDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
       }
        parent.$.messager.confirm('询问', '您是否要停用当前选择人员？', function(b) {
           if (b) {
               progressLoad();
               $.post('${path }/mgr/salesman/disable', {
                   id : id
               }, function(result) {
                   if (result.success) {
                       parent.$.messager.alert('提示', result.msg, 'info');
                       salesManDataGrid.datagrid('reload');
                   }
                   progressClose();
               }, 'JSON');
           }
       }); 
   }
   /**
    * 批量导出
    */
   function exportSalesManFun(id) {
	   if (id == undefined) {//点击右键菜单才会触发这个
      	   var rows = salesManDataGrid.datagrid('getSelections');
		   if(rows.length==0){
			  id = "all"; 
		   }else{
			   id = rows[0].userId;
	           for(j = 1; j < rows.length; j++) {
	          	 id = id + "," + rows[j].userId;
	           }
		   }
	   }
	   window.open("${path }/mgr/salesman/exportExcel?id="+id);
   }
   
   /**
    * EXCEL导入
    */
   function importSalesmanFun() {
        salesManDataGrid.datagrid('load', $.serializeObject($('#salesManSearchForm')));
   }
  
  
	/**
	 * 清除
	 */
	function salesManCleanFun() {
	    $('#salesManSearchForm input').val('');
	    salesManDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
function salesManSearchFun() {
     salesManDataGrid.datagrid('load', $.serializeObject($('#salesManSearchForm')));
}

</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false"
		style="height: 60px; overflow: hidden; background-color: #fff">
		<div style="height: 30px;">
			<img id="u162_img" class="img" width="100%" height="100%"
				src="${path}/static/style/images/u522.png" /> <span
				style="position: absolute; top: 7px; left: 6px; text-align: center;">搜索</span>
		</div>
		<form id="salesManSearchForm">
			<table>
				<tr>
					<th>关键字搜索</th>
					<td><select name="searchName" class="easyui-combobox"
						style="width: 100px">
							<option value="userName">姓名</option>
							<option value="mtelphone">手机</option>
							<option value="wechat">微信</option>
					</select></td>
					<td><input name="val" style="width: 200px" placeholder="搜索内容" /></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'fi-magnifying-glass',plain:true"
						onclick="salesManSearchFun();">搜索</a></td>
				</tr>
			</table>
		</form>
	</div>

	<div data-options="region:'center',border:false">
		<table id="salesManDataGrid" data-options="fit:true,border:false"></table>
	</div>
</div>
<div id="salesManToolbar"
	style="display: none; background-color: #CCCCCC; text-align: right;">
	<shiro:hasPermission name="/salesman/add">
		<a onclick="addSalesmanFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="/salesman/delete">
		<a onclick="deleteSalesManFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'fi-plus fi-x icon'">批量删除</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="/salesman/edit">
		<a onclick="exportSalesManFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'fi-plus fi-print'">批量导出EXCEL</a>
	</shiro:hasPermission>
	<!-- 
	<shiro:hasPermission name="/salesman/add">
		<a onclick="importSalesmanFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'fi-plus fi-save'">EXCEL导入</a>
	</shiro:hasPermission>
	-->
</div>