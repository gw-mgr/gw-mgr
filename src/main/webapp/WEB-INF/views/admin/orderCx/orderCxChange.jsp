<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/head.jsp"%>
<script type="text/javascript">
    $(function() {
        $('#orderCxChangeForm').form({
            url : '${path}/mgr/orderCx/change',
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
                    var form = $('#orderCxChangeForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
        
        $("input[name='checkoutFlag'][value=${orderCx.checkoutFlag}]").attr("checked",true); 
        
    });
    function changeJe(obj){
    	var sum = parseFloat($('#jsyxStr').val())+parseFloat($('#jjqxStr').val())+parseFloat($('#ccsStr').val());
    	var str = obj.name;
    	var val = obj.value;
    	if(str == 'bxgssqyjbl'){
    		$("#bxgssqyj").val(($('#jsyxStr').val()*val).toFixed(2));
    	}
    	if(str == 'bxgssqjqyjbl'){
    		$("#bxgssqjqyj").val(($('#jjqxStr').val()*val).toFixed(2));
    	}
    	if(str == 'bxgskpyjbl'){
    		$("#bxgskpyj").val(($('#jsyxStr').val()*val).toFixed(2));
    	}
    	if(str == 'gsglfbl'){
    		$("#gsglfje").val(($('#jsyxStr').val()*val).toFixed(2));
    	}
    	if(str == 'gsjsbl'){
    		$("#gsjsjeStr").val((sum * val ).toFixed(2));
    	}
    	if(str == 'gstcbl'){
    		$("#gstcjeStr").val((sum * val ).toFixed(2));
    	}
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="orderCxChangeForm" method="post">
        	<input name="orderId" type="hidden"  value="${orderCx.orderId}">
        	<input  id="jsyxStr" type="hidden" name="jsyx" value="${orderCx.jsyx}">
        	<input  id="jjqxStr" type="hidden" name="jjqx" value="${orderCx.jjqx}">
        	<input  id="ccsStr" type="hidden" name="ccs" value="${orderCx.ccs}">
			<table class="grid">
				<tr>
					<td align="right">保险公司是否结账：</td>
					<td align="left">
						<input type="radio" name="checkoutFlag" class="easyui-validatebox" value="01">否
      				    <input type="radio" name="checkoutFlag" class="easyui-validatebox" value="02">是
					</td>
				</tr>
				<tr>
					<td align="right">保险公司商业险税前佣金比例：</td>
					<td align="left"><input style="width: 300px" name="bxgssqyjbl" onchange="changeJe(this)" value="${orderCx.bxgssqyjbl}"></td>
				</tr>
				<tr>
					<td align="right">保险公司商业险税前佣金：</td>
					<td align="left">
						<input style="width: 300px;border: 0px;outline: none;" id="bxgssqyj" name="bxgssqyj" value="${orderCx.bxgssqyj}">
					</td>
				</tr>
				<tr>	
					<td align="right">保险公司交强险税前佣金比例：</td>
					<td align="left"><input style="width: 300px" name="bxgssqjqyjbl" onchange="changeJe(this)" value="${orderCx.bxgssqjqyjbl}"></td>
				</tr>
				<tr>
					<td align="right">保险公司交强险税前佣金：</td>
					<td align="left">
						<input style="width: 300px;border: 0px;outline: none;" id="bxgssqjqyj" name="bxgssqjqyj" value="${orderCx.bxgssqjqyj}">
					</td>
				</tr>
				<tr>	
					<td align="right">保险公司开票佣金比例：</td>
					<td align="left"><input style="width: 300px" name="bxgskpyjbl" onchange="changeJe(this)" value="${orderCx.bxgskpyjbl}"></td>
				</tr>
				<tr>
					<td align="right">保险公司开票佣金：</td>
					<td align="left">
						<input style="width: 300px;border: 0px;outline: none;" id="bxgskpyj" name="bxgskpyj" value="${orderCx.bxgskpyj}">
					</td>
				</tr>
				<tr>	
					<td align="right">公司管理费比例：</td>
					<td align="left"><input style="width: 300px" name="gsglfbl" onchange="changeJe(this)" value="${orderCx.gsglfbl}"></td>
				</tr>
				<tr>
					<td align="right">公司管理费金额：</td>
					<td align="left">
						<input style="width: 300px;border: 0px;outline: none;" id="gsglfje" name="gsglfje" value="${orderCx.gsglfje}">
					</td>
				</tr>
				<tr>	
					<td align="right">公司交税比例：</td>
					<td align="left"><input style="width: 300px" name="gsjsbl" onchange="changeJe(this)" value="${orderCx.gsjsbl}"></td>
				</tr>
				<tr>
					<td align="right">公司交税金额：</td>
					<td align="left">
						<input style="width: 300px;border: 0px;outline: none;" name="gsjsje" id="gsjsjeStr" value="${orderCx.gsjsje}">
					</td>
				</tr>
				<tr>	
					<td align="right">公司提成比例：</td>
					<td align="left"><input style="width: 300px" name="gstcbl" onchange="changeJe(this)" value="${orderCx.gstcbl}"></td>
				</tr>
				<tr>
					<td align="right">公司提成金额：</td>
					<td align="left">
						<input style="width: 300px;border: 0px;outline: none;" id="gstcjeStr" name="gstcje" value="${orderCx.gstcje}">
					</td>
				</tr>
			</table>
        </form>
    </div>
</div>