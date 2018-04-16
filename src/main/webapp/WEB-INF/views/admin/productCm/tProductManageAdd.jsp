<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#tProductManageCMAddForm').form({
            url : '${path}/mgr/tProductManage/add',
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
                    var form = $('#tProductManageCMAddForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="tProductManageCMAddForm" method="post">
            <table class="grid">
                <tr>
                    <td>产品名称</td>
                    <td colspan="2">	
                    	<input name="productName" type="text" style="width: 400px" placeholder="请输入名称" class="easyui-validatebox span2" data-options="required:true" value="">
                    	<input name="productType" value="ROOTCM" type="hidden">
                    </td>
                </tr> 
                <tr>
                    <td>现价</td>
                    <td colspan="2"><input name="origPrice" type="text" class="easyui-validatebox span2" value=""></td>
                </tr>
                <tr>
                    <td>原价</td>
                    <td colspan="2"><input name="currPrice" type="text" class="easyui-validatebox span2" value=""></td>
                </tr>
                <tr>
                    <td>平台佣金</td>
                    <td colspan="2"><input name="commission" type="text" class="easyui-validatebox span2" value="">%</td>
                </tr>
                 <tr>
                    <td>产品介绍</td>
                    <td colspan="2"><textarea name="productDescript" style="width: 390px; height: 100px;"></textarea></td>
                </tr> 
            </table>
        </form>
    </div>
</div>