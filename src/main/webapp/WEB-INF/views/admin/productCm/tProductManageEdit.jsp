<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#tProductManageCMEditForm').form({
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
                    var form = $('#tProductManageCMEditForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
        
        $("#editStatus").val('${tProductManage.status}'); 
        
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="tProductManageCMEditForm" method="post">
            <table class="grid">
                <tr>
                    <td>商家名称</td>
                    <td>
                   	<input name="productType" type="hidden" value="${tProductManage.productType }">
                   	<input name="productId" type="hidden" value="${tProductManage.productId }">
                   	<input name="status" type="hidden" value="${tProductManage.status }">
                    <input name="productName" type="text" placeholder="请输入名称" class="easyui-validatebox" data-options="required:true" value="${tProductManage.productName}"></td>
                </tr>
               <tr>
                    <td>现价</td>
                    <td colspan="2"><input name="origPrice" type="text" class="easyui-validatebox span2" value="${tProductManage.origPrice }"></td>
                </tr>
                <tr>
                    <td>原价</td>
                    <td colspan="2"><input name="currPrice" type="text" class="easyui-validatebox span2" value="${tProductManage.currPrice }"></td>
                </tr>
                <tr>
                    <td>平台佣金</td>
                    <td colspan="2"><input name="commission" type="text" class="easyui-validatebox span2" value="${tProductManage.commission }"></td>
                </tr>
                 <tr>
                    <td>产品详情</td>
                    <td colspan="2"><textarea name="productDescript" style="width: 390px; height: 100px;">${tProductManage.productDescript }</textarea></td>
                </tr>
            </table>
        </form>
    </div>
</div>