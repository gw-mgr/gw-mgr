<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#orderInfoLCAddForm').form({
            url : '${path}/mgr/orderInfo/add',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                    //之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    var form = $('#orderInfoLCAddForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
    });
    
    function productCheckFun() {
    	$('#productCheckLCDataGrid').datagrid({
            url : '${path}/mgr/tProductManage/dataGrid?type=ROOTLC',
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : false,
    		selectOnCheck: true,
    		checkOnSelect: true,
            idField : 'productId',
            sortName : 'productId',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            frozenColumns : [ [ {
            	field:'productId',
    			checkbox:true 
            },{
                width : '200',
                title : '商品名称',
                field : 'productName',
                sortable : true,
                align : 'center'
            },{
                width : '80',
                title : '目标年化收益',
                field : 'lGoalIncome',
                sortable : true,
                align : 'center'
            }, {
                width : '80',
                title : '投资期限（天）',
                field : 'lLimit',
                sortable : true,
                align : 'center'
            }, {
                width : '60',
                title : '风险等级',
                field : 'lRiskGrade',
                sortable : true,
                align : 'center'
            }, {
                width : '100',
                title : '起购金额',
                field : 'lMixBuyMoney',
                sortable : true,
                align : 'center'
            } ] ],
            onLoadSuccess:function(data){
                $('.orderInfo-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.orderInfo-easyui-linkbutton-del').linkbutton({text:'删除'});
            }
        });
	    $("#dialog").show();//必须先显示，再弹出
	    $("#dialog").dialog({
	        title: "产品选择",
	        width: 600,
	        height: 400
	    });
    }
    /**
     * 搜索
     */
    function productSearchFun() {
         $('#productCheckLCDataGrid').datagrid('load', $.serializeObject($('#productSearchForm')));
    }
    function getProductId(id){
       var rows = $('#productCheckLCDataGrid').datagrid('getSelections');
       var productName;
	   if(rows.length==0){
		   alert('请选择一个产品！');
	   }else{
		   id = rows[0].productId;
		   productName = rows[0].productName;
	   }
	   $('#productId').val(id);
	   $('#productName').val(productName);
	   $('#productCheckLCDataGrid').datagrid('clearSelections');
	   $("#dialog").css("display", "none"); 
	   $("#dialog").dialog('close');
    }
    function cancelProductFun(){
  	  $('#productCheckLCDataGrid').datagrid('clearSelections'); 
  	   $("#dialog").css("display", "none"); 
  	   $("#dialog").dialog('close');
      }
</script>

<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="orderInfoLCAddForm" method="post">
				<table class="grid">
	                <tr>
	                    <td align="right">用户姓名：</td>
	                    <td align="left">
	                    	<input id="productId" name="productId" value="" type="hidden">
	                    	<input name="orderType" value="ROOTLC" type="hidden">
	                    	<input name="userName" type="text" class="easyui-validatebox span2" value="">
	                    </td>
	                    <td align="right">联系电话：</td>
	                    <td align="left"><input name="telephone" type="text" class="easyui-validatebox span2" value=""></td>
	                </tr> 
	               <tr>
	                    <td align="right">证件类型：</td>
	                    <td align="left">
							<select name="cardType" class="easyui-combobox" style="width:150px;" 
							data-options="editable:false,valueField:'id',textField:'text',data:[{id:'身份证',text:'身份证'},{id:'毕业证',text:'毕业证'},{id:'出生证',text:'出生证'},{id:'独生子女证',text:'独生子女证'},{id:'护照',text:'护照'},{id:'驾驶证',text:'驾驶证'},{id:'结婚证',text:'结婚证'},{id:'警官证',text:'警官证'},{id:'离婚证',text:'离婚证'},{id:'签证',text:'签证'},{id:'学生证',text:'学生证'},{id:'执行公务证',text:'执行公务证'}],value:'身份证'">
							</select>
	                    </td>
	                </tr>
	                <tr>
	                    <td align="right">证件号码：</td>
	                    <td align="left" colspan="3"><input style="width: 390px" name="cardId" type="text" placeholder="请输入证件号码" class="easyui-validatebox span2" value=""></td>
	                </tr>
	                 <tr>
	                    <td align="right">理财产品：</td>
	                    <td align="left">
	                    	<input id="productName" name="productName" value="" type="text" readonly="readonly" placeholder="请选择产品">
	                    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="productCheckFun();">选择</a>
	                    </td>
	                    <td align="right">投资金额：</td>
	                    <td align="left"><input name="investmentAmount" type="text" class="easyui-validatebox span2" value=""></td>
	                </tr> 
	                <tr>
	                    <td align="right">生效时间：</td>
	                    <td align="left"><input name="beginDate" style="width:100px;" class="easyui-datebox" value=""></td>
	                </tr>
	                <tr>
	                    <td align="right">备注：</td>
	                    <td align="left" colspan="3"><textarea name="remark3" rows="3" cols="55"></textarea></td>
	                </tr>
	            </table>
        </form>
    </div>
</div>
<div id="dialog" style="display: none;">
	<div class="easyui-layout" data-options="fit:true,border:false">
	    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden; background-color: #fff">
	        <form id="productSearchForm">
	            <table>
	                <tr>
	                    <th>关键字搜索</th>
						<td><input name="productName" style="width: 200px" placeholder="产品名称查询" /></td>
	                    <td>
	                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="productSearchFun();">查询</a>
	                    </td>
	                </tr>
	            </table>
	        </form>
	     </div>	 
	     
	    <div data-options="region:'center',border:false" style="border: 0px solid red;">
	        <table id="productCheckLCDataGrid" data-options="fit:true,border:false"></table>
	    </div>
	    <div id="productCheckLCToolbar" data-options="region:'south',border:false" style="display: block; background-color: #CCCCCC; text-align: center;">
		    <shiro:hasPermission name="/orderInfo/add">
		        <a onclick="getProductId();" href="javascript:void(0);" class="easyui-linkbutton">确&nbsp;&nbsp;&nbsp;&nbsp;定</a>
		    </shiro:hasPermission>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <shiro:hasPermission name="/orderInfo/add">
		        <a onclick="cancelProductFun();" href="javascript:void(0);" class="easyui-linkbutton">取&nbsp;&nbsp;&nbsp;&nbsp;消</a>
		    </shiro:hasPermission>
		</div>
	</div>
</div>