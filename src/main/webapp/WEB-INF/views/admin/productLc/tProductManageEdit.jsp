<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#tProductManageLCEditForm').form({
            url : '${path}/mgr/tProductManage/edit',
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
                    var form = $('#tProductManageLCEditForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
        
        $("#editStatus").val('${tProductManage.status}'); 
        
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="tProductManageLCEditForm" method="post">
            <table class="grid">
                <tr>
                    <td>产品名称</td>
                    <td colspan="3">
	                    <input name="productType" type="hidden" value="${tProductManage.productType }">
	                   	<input name="productId" type="hidden" value="${tProductManage.productId }">
	                   	<input name="status" type="hidden" value="${tProductManage.status }">	
                    	<input name="productName" type="text" style="width: 400px" placeholder="请输入名称" class="easyui-validatebox span2" data-options="required:true" value="${tProductManage.productName}">
                    </td>
                </tr> 
                <tr>
                    <td>目标年化收益</td>
                    <td><input name="lGoalIncome" type="text" class="easyui-validatebox span2" value="${tProductManage.lGoalIncome }">%</td>
                    <td>投资期限（天）</td>
                    <td><input name="lInvestDays" type="text" class="easyui-validatebox span2" value="${tProductManage.lInvestDays }"></td>
                </tr>
                <tr>
                    <td>起购金额（元）</td>
                    <td><input name="lMixBuyMoney" type="text" class="easyui-validatebox span2" value="${tProductManage.lMixBuyMoney }"></td>
                    <td>风险等级</td>
                    <td>
						<select name="lRiskGrade" class="easyui-combobox" style="width:200px;" data-options="editable:false,valueField:'id',textField:'text',data:[{id:'1',text:'1级'},{id:'2',text:'2级'},{id:'3',text:'3级'},{id:'4',text:'4级'},{id:'5',text:'5级'}],value:'${tProductManage.lRiskGrade}'"></select>
					</td>
                </tr>
                <tr>
                    <td>平台佣金</td>
                    <td><input name="commission" type="text" class="easyui-validatebox span2" value="${tProductManage.commission }"></td>
                </tr>
                 <tr>
                    <td>产品介绍</td>
                    <td colspan="3"><textarea name="productDescript" style="width: 390px; height: 100px;">${tProductManage.productDescript }</textarea></td>
                </tr> 
            </table>
        </form>
    </div>
</div>