<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/head.jsp"%>
<script type='text/javascript' src='${path }/static/distpicker/distpicker.data.js'></script> 
<script type='text/javascript' src='${path }/static/distpicker/distpicker.js'></script> 
<script type='text/javascript' src='${path }/static/distpicker/main.js'></script> 
<script type="text/javascript">
    $(function() {
        $('#orderSxChangeForm').form({
            url : '${path}/mgr/orderSx/change',
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
                    var form = $('#orderSxChangeForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
        
        $("input[name='checkoutFlag'][value=${orderSx.checkoutFlag}]").attr("checked",true); 
        
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="orderSxChangeForm" method="post">
        	<input name="orderId" type="hidden"  value="${orderSx.orderId}">
			<table class="grid">
				<tr>
					<td align="right">保险公司是否结账：</td>
					<td align="left">
						<input type="radio" name="checkoutFlag" class="easyui-validatebox" value="01">否
      				    <input type="radio" name="checkoutFlag" class="easyui-validatebox" value="02">是
					</td>
				</tr>
				<tr>
					<td align="right">保险公司主险税前佣金比例：</td>
					<td align="left"><input style="width: 300px" name="bxgszxsqyjbl" value="${orderSx.bxgszxsqyjbl}"></td>
				</tr>
				<tr>
					<td align="right">保险公司主险税前佣金：</td>
					<td align="left">
						<input style="width: 300px;border: 0px;outline: none;" name="bxgszxsqyj" value="${orderSx.bxgszxsqyj}">
					</td>
				</tr>
				<tr>	
					<td align="right">保险公司附险1税前佣金比例：</td>
					<td align="left"><input style="width: 300px" name="bxgsfx1sqyjbl" value="${orderSx.bxgsfx1sqyjbl}"></td>
				</tr>
				<tr>
					<td align="right">保险公司附险1税前佣金：</td>
					<td align="left">
						<input style="width: 300px;border: 0px;outline: none;" name="bxgsfx1sqyj" value="${orderSx.bxgsfx1sqyj}">
					</td>
				</tr>
				<tr>	
					<td align="right">保险公司开票佣金比例：</td>
					<td align="left"><input style="width: 300px" name="bxgskpyjbl" value="${orderSx.bxgskpyjbl}"></td>
				</tr>
				<tr>
					<td align="right">保险公司开票佣金：</td>
					<td align="left">
						<input style="width: 300px;border: 0px;outline: none;" name="bxgskpyj" value="${orderSx.bxgskpyj}">
					</td>
				</tr>
				<tr>	
					<td align="right">公司管理费比例：</td>
					<td align="left"><input style="width: 300px" name="gsglfbl" value="${orderSx.gsglfbl}"></td>
				</tr>
				<tr>
					<td align="right">公司管理费金额：</td>
					<td align="left">
						<input style="width: 300px;border: 0px;outline: none;" name="gsglfje" value="${orderSx.gsglfje}">
					</td>
				</tr>
				<tr>	
					<td align="right">公司交税比例：</td>
					<td align="left"><input style="width: 300px" name="gsjsbl" value="${orderSx.gsjsbl}"></td>
				</tr>
				<tr>
					<td align="right">公司交税金额：</td>
					<td align="left">
						<input style="width: 300px;border: 0px;outline: none;" name="gsjsje" value="${orderSx.gsjsje}">
					</td>
				</tr>
				<tr>	
					<td align="right">公司提成比例：</td>
					<td align="left"><input style="width: 300px" name="gstcbl" value="${orderSx.gstcbl}"></td>
				</tr>
				<tr>
					<td align="right">公司提成金额：</td>
					<td align="left">
						<input style="width: 300px;border: 0px;outline: none;" name="gstcje" value="${orderSx.gstcje}">
					</td>
				</tr>
			</table>
        </form>
    </div>
</div>