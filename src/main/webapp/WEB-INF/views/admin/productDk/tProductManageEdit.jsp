<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#tProductManageDKEditFun').form({
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
                    var form = $('#tProductManageDKEditFun');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
        
        $("#editStatus").val('${tProductManage.status}'); 
        
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="tProductManageDKEditFun" method="post">
            <table class="grid">
                <tr>
                    <td>商家名称</td>
                    <td colspan="2">
                   	<input name="productType" type="hidden" value="${tProductManage.productType }">
                   	<input name="productId" type="hidden" value="${tProductManage.productId }">
                   	<input name="status" type="hidden" value="${tProductManage.status }">
                    <input name="productName" type="text" style="width: 400px" placeholder="请输入名称" class="easyui-validatebox" data-options="required:true" value="${tProductManage.productName}"></td>
                </tr>
                <tr>
                    <td>平台佣金</td>
                    <td colspan="2"><input name="commission" type="text" class="easyui-validatebox span2" value="${tProductManage.commission}"></td>
                </tr>
                <tr>
                    <td>基本要求</td>
                    <td>婚姻状况</td>
                    <td>
						<select name="dMarryFlag" class="easyui-combobox" style="width:200px;" data-options="editable:false,valueField:'id',textField:'text',data:[{id:'0',text:'未婚'},{id:'1',text:'已婚'},{id:'2',text:'离异'}],value:'${tProductManage.dMarryFlag}'"></select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>有无房产</td>
                    <td>
						<select name="dHouseFlag" class="easyui-combobox" style="width:200px;" data-options="editable:false,valueField:'id',textField:'text',data:[{id:'0',text:'无'},{id:'1',text:'按揭'},{id:'2',text:'全款'}],value:'${tProductManage.dHouseFlag}'"></select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>有无社保</td>
                    <td>
						<select name="dSocialFlag" class="easyui-combobox" style="width:200px;" data-options="editable:false,valueField:'id',textField:'text',data:[{id:'0',text:'无'},{id:'1',text:'有'}],value:'${tProductManage.dSocialFlag}'"></select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>每月收入</td>
                    <td>
						<select name="dIncome" class="easyui-combobox" style="width:200px;" data-options="editable:false,valueField:'id',textField:'text',data:[{id:'1000-3000',text:'1000-3000'},{id:'3000-6000',text:'3000-6000'},{id:'6000-10000',text:'6000-10000'},{id:'10000-30000',text:'10000-30000'},{id:'3万以上',text:'3万以上'}],value:'${tProductManage.dIncome}'"></select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>有无车辆</td>
                    <td>
						<select name="dCarFlag" class="easyui-combobox" style="width:200px;" data-options="editable:false,valueField:'id',textField:'text',data:[{id:'0',text:'无'},{id:'1',text:'按揭'},{id:'2',text:'全款'}],value:'${tProductManage.dCarFlag}'"></select>
                    </td>
                </tr>
                 <tr>
                    <td>产品介绍</td>
                    <td colspan="2"><textarea name="productDescript" style="width: 390px; height: 100px;">${tProductManage.productDescript}</textarea></td>
                </tr> 
            </table>
        </form>
    </div>
</div>