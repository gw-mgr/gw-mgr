<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/head.jsp"%>
<script type='text/javascript' src='${path }/static/distpicker/distpicker.data.js'></script> 
<script type='text/javascript' src='${path }/static/distpicker/distpicker.js'></script> 
<script type='text/javascript' src='${path }/static/distpicker/main.js'></script> 
<script type="text/javascript">
    $(function() {
        $('#orderCxPayForm').form({
            url : '${path}/mgr/orderCx/payEdit',
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
                    var form = $('#orderCxPayForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
        
        $("input[name='checkoutFlag'][value=${orderCx.checkoutFlag}]").attr("checked",true); 
        
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="orderCxPayForm" method="post">
        	<input name="orderId" type="hidden"  value="${orderCx.orderId}">
			<table class="grid">
				<tr>
					<td align="right">佣金支付对象：</td>
					<td align="left">
      				    ${orderCx.yjzfdx==01?'业务员':'录单员'}
					</td>
				</tr>
				<tr>
					<td align="right">开户行：</td>
					<td align="left"><input style="width: 300px;border: 0px;outline: none;" name="bankName" value="${orderCx.bankName}"></td>
				</tr>
				<tr>
					<td align="right">开户名称：</td>
					<td align="left">
						<input style="width: 300px;border: 0px;outline: none;" name="bankUserName" value="${orderCx.bankUserName}">
					</td>
				</tr>
				<tr>
					<td align="right">银行卡号：</td>
					<td align="left">
						<input style="width: 300px;border: 0px;outline: none;" name="bxgssqyj" value="${orderCx.bankNo}">
					</td>
				</tr>				
				<tr>	
					<td align="right">业务员佣金：</td>
					<td align="left"><input style="width: 300px;border: 0px;outline: none;" name="bxgssqjqyjbl" value="${orderCx.bxgssqjqyjbl}"></td>
				</tr>
				<tr>
					<td align="right">保险公司短期激励：</td>
					<td align="left">
						<input style="width: 300px;border: 0px;outline: none;" name="bxgssqjqyj" value="${orderCx.bxgssqjqyj}">
					</td>
				</tr>
				<tr>	
					<td align="right">业务员扣佣：</td>
					<td align="left"><input style="width: 300px;border: 0px;outline: none;" name="bxgskpyjbl" value="${orderCx.bxgskpyjbl}"></td>
				</tr>
				<tr>
					<td align="right">该订单的实际支付金额：</td>
					<td align="left">
						<input style="width: 300px" name="bxgskpyj" class="easyui-numberbox" data-options="min:0,precision:2" value="${orderCx.bxgskpyj}">
					</td>
				</tr>
			</table>
        </form>
    </div>
</div>