<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var tAccountFlowDataGrid;
    $(function() {
        tAccountFlowDataGrid = $('#tAccountFlowDataGrid').datagrid({
        url : '${path}/mgr/tAccountFlow/dataGrid',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : false,
		selectOnCheck : true,
		checkOnSelect : true,
        idField : 'id',
        sortName : 'updateTime',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        frozenColumns : [ [ 
        {
            width : '300',
            title : '时间',
            field : 'updateTime',
            align : 'center',
            sortable : true
        }, {
            width : '300',
            title : '收支明细',
            field : 'tradeType',
            align : 'center',
            sortable : true,
            formatter: function(value,row,index){
				return row.tradeType + row.tradeValue;
			}
        }, {
            width : '440',
            title : '备注',
            align : 'center',
            field : 'orderTypeName',
            sortable : true,
            formatter : function(value, row, index) {
            	if(value == '' || value == null){
            		switch (row.orderType) {
    				case "TX":
    					return '提现';
    				case "JS":
    					return '结算';
    				case "ZJT":
    					return '车险直接推荐奖奖金';
    				case "JJT":
    					return '车险间接推荐奖奖金';
    				case "QZJT":
    					return '取消车险直接推荐奖奖金';
    				case "QJJT":
    					return '取消车险间接推荐奖奖金';
    				}
            	}
			}
        } ] ],
        toolbar : '#tAccountFlowToolbar'
    });
});

/**
 * 添加框
 * @param url
 */
function tAccountFlowAddFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 700,
        height : 600,
        href : '${path}/tAccountFlow/addPage',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = tAccountFlowDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#tAccountFlowAddForm');
                f.submit();
            }
        } ]
    });
}

</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 60px; overflow: hidden;background-color: #fff">
    	<div style="height: 30px;">
			<img id="u162_img" class="img" width="100%" height="100%"
				src="${path}/static/style/images/u522.png" /> <span
				style="position: absolute; top: 7px; left: 6px; text-align: center;">佣金记录</span>
		</div>
        <div style="height: 200px;text-align: center;margin-top: 5px;">
        余额<span style="font-weight:bold; font-size:30px；">200元</span>
        </div>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="tAccountFlowDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="tAccountFlowToolbar" style="display: none; background-color: #CCCCCC; text-align: right;">
    <shiro:hasPermission name="/tAccountFlow/add">
        <a onclick="tAccountFlowAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-page-add'">添加</a>
    </shiro:hasPermission>
</div>