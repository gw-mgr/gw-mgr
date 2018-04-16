<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#tProductManageCDEditForm').form({
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
                    var form = $('#tProductManageEditForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
        
        $("#editStatus").val('${tProductManage.status}'); 
        
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="tProductManageCDEditForm" method="post">
            <table class="grid">
                <tr>
                    <td>产品名称</td>
                    <td colspan="2">	
                    	<p><span>快速年检代办</span></p>
                    	<input name="productName" value="快速年检代办" type="hidden">
                    	<input name="productType" type="hidden" value="${tProductManage.productType }">
                    	<input name="productId" type="hidden" value="${tProductManage.productId }">
                    	<input name="lNumberOfPerson" type="hidden" value="${tProductManage.lNumberOfPerson }">
                    	<input name="status" type="hidden" value="${tProductManage.status }">
                    </td>
                </tr> 
                <tr>
                    <td>价格</td>
                    <td colspan="2"><input name="origPrice" type="text" class="easyui-validatebox span2" value="${tProductManage.origPrice }"></td>
                </tr>
                <tr>
                    <td>平台佣金</td>
                    <td colspan="2"><input name="commission" type="text" class="easyui-validatebox span2" value="${tProductManage.commission }"></td>
                </tr>
                 <tr>
                    <td>产品介绍</td>
                    <td colspan="2"><textarea name="productDescript" style="width: 390px; height: 100px;">${tProductManage.productDescript }</textarea></td>
                </tr> 
            </table>
        </form>
    </div>
</div>