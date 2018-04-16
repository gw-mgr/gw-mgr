<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var orderInfoCDDataGrid;
    $(function() {
        orderInfoCDDataGrid = $('#orderInfoCDDataGrid').datagrid({
        url : '${path}/mgr/orderInfo/dataGrid?type=ROOTCD',
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
            align : 'center',
            sortable : true
        },{
            width : '100',
            title : '下单时间',
            field : 'createTime',
            align : 'center',
            sortable : true
        },{
            width : '100',
            title : '用户姓名',
            field : 'userName',
            align : 'center',
            sortable : true
        },{
            width : '200',
            title : '厂牌型号',
            field : 'changType',
            align : 'center',
            sortable : true
        },{
            width : '150',
            title : '类型',
            field : 'orderType',
            align : 'center',
            sortable : true,
            formatter : function(value, row) {
            	if (value == 'ROOTCDSHDB'){
					return '代办新车上户';
				}
				if (value == 'ROOTCDBXDB'){
					return '代办保险过户';
				}
				if (value == 'ROOTCDBLHPDB'){
					return '补领号牌';
				}
				if (value == 'ROOTCDBHJZDB'){
					return '补换驾照';
				}
				if (value == 'ROOTCDWDNJDB'){
					return '开外地年检委托书';
				}
				if (value == 'ROOTCDDLLKDB'){
					return '代领绿标';
				}
				if (value == 'ROOTCDBXSZDB'){
					return '补行驶证';
				}
				if (value == 'ROOTCDHCYYZDB'){
					return '货车运营证';
				}
				if (value == 'ROOTCDGHDB'){
					return '代办汽车过户';
				}
				if (value == 'ROOTCDCPDB'){
					return '代选车牌';
				}
				if (value == 'ROOTCDWZDB'){
					return '代办车辆违章';
				}
				if (value == 'ROOTCDJFDB'){
					return '代缴路桥费';
				}
				if (value == 'ROOTCDSXNJDB'){
					return '上线年检代办';
				}
				if (value == 'ROOTCDKSNJDB'){
					return '快速年检代办';
				}
            }
        }, {
            width : '100',
            title : '状态',
            field : 'orderFlag',
            align : 'center',
            sortable : true,
            formatter : function(value, row) {
            	if (value == '01'){
					return '未接单';
				}
				if (value == '08'){
					return '已接单';
				}
				if (value == '11'){
					return '已取消';
				}
				if (value == '12'){
					return '处理完毕';
				}
            }
        }, {
            field : 'action',
            title : '操作',
            width : 200,
            align : 'center',
            formatter : function(value, row, index) {
                var str = '';
                <shiro:hasPermission name="/orderInfo/edit">
                    str += $.formatString('<a href="javascript:void(0)" class="orderInfo-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="orderInfoCDEditFun(\'{0}\');" >编辑</a>', row.orderId);
                </shiro:hasPermission>
                <shiro:hasPermission name="/orderInfo/delete">
                    str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    str += $.formatString('<a href="javascript:void(0)" class="orderInfo-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="orderInfoCDDeleteFun(\'{0}\');" >删除</a>', row.orderId);
                </shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.orderInfo-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.orderInfo-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        toolbar : '#orderInfoCDToolbar'
    });
});

/**
 * 添加框
 * @param url
 */
function orderInfoCDAddFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 700,
        height : 600,
        href : '${path}/mgr/orderInfo/addPage?type=Cd',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = orderInfoCDDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#orderInfoCDAddForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 编辑
 */
function orderInfoCDEditFun(id) {
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
                parent.$.modalDialog.openner_dataGrid = orderInfoCDDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#orderInfoCDEditForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function orderInfoCDDeleteFun(id) {
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
function orderInfoCDCleanFun() {
    $('#orderInfoCDSearchForm input').val('');
    orderInfoCDDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function orderInfoCDSearchFun() {
     orderInfoCDDataGrid.datagrid('load', $.serializeObject($('#orderInfoCDSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 60px; overflow: hidden; background-color: #fff">
    	<div style="height: 30px;">
			<img id="u162_img" class="img" width="100%" height="100%"
				src="${path}/static/style/images/u522.png" /> <span
				style="position: absolute; top: 7px; left: 6px; text-align: center;">搜索</span>
		</div>
        <form id="orderInfoCDSearchForm">
            <table>
                <tr>
                	<th>下单时间</th>
                	<td><input name="createDateBe"  class="easyui-datebox"  style="width:100px;" value="">
                	至<input name="createDateBe"  class="easyui-datebox"  style="width:100px;" value=""></td>
                    <th>关键字搜索</th>
					<td><select name="name" class="easyui-combobox"
						style="width: 100px">
							<option value=carNum>车牌号</option>
							<option value="userName">用户姓名</option>
							<option value="orderId">订单号</option>
					</select></td>
					<td><input name="val" style="width: 200px" placeholder="搜索内容" /></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="orderInfoCDSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="orderInfoCDCleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="orderInfoCDDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="orderInfoCDToolbar" style="display: none; background-color: #CCCCCC; text-align: right;">
    <shiro:hasPermission name="/orderInfo/add">
        <a onclick="orderInfoCDAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-page-add'">添加</a>
    </shiro:hasPermission>
</div>