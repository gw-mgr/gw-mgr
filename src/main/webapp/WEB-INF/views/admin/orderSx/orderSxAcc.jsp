<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/head.jsp"%>
<script type='text/javascript' src='${path }/static/distpicker/distpicker.data.js'></script> 
<script type='text/javascript' src='${path }/static/distpicker/distpicker.js'></script> 
<script type='text/javascript' src='${path }/static/distpicker/main.js'></script> 
<script type="text/javascript">
    $(function() {
        $('#orderSxAccForm').form({
            url : '${path}/mgr/orderSx/accEdit',
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
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    var form = $('#orderSxAccForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
        
        $("input[name='checkoutFlag'][value=${orderSx.checkoutFlag}]").attr("checked",true); 
        
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="orderSxAccForm" method="post">
        	<input name="orderId" type="hidden"  value="${orderSx.orderId}">
			<table class="grid">
				<tr>
					<td align="right">保险公司税前佣金比例：</td>
					<td align="left">
						<input style="width: 300px" name="bxgszxsqyjbl" class="easyui-numberbox" data-options="min:0,precision:2" value="${orderSx.bxgszxsqyjbl}">
					</td>
				</tr>
				<tr>
					<td align="right">保险公司开票佣金比例：</td>
					<td align="left">
						<input style="width: 300px" name="bxgskpyjbl" class="easyui-numberbox" data-options="min:0,precision:2" value="${orderSx.bxgskpyjbl}">
					</td>
				</tr>
				<tr>
					<td align="right">公司管理费比例：</td>
					<td align="left">
						<input style="width: 300px" name="gsglfbl" class="easyui-numberbox" data-options="min:0,precision:2" value="${orderSx.gsglfbl}">
					</td>
				</tr>
				<tr>
					<td align="right">公司交税比例：</td>
					<td align="left">
						<input style="width: 300px" name="gsjsbl" class="easyui-numberbox" data-options="min:0,precision:2" value="${orderSx.gsjsbl}">
					</td>
				</tr>
				<tr>
					<td align="right">公司提成比例：</td>
					<td align="left">
						<input style="width: 300px" name="gstcbl" class="easyui-numberbox" data-options="min:0,precision:2" value="${orderSx.gstcbl}">
					</td>
				</tr>
			</table>
        </form>
    </div>
</div>