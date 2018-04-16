<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#operatorAddForm').form({
            url : '${path}/mgr/operator/add',
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
                    var form = $('#operatorAddForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="operatorAddForm" method="post">
            <table class="grid">
                <tr>
                    <td  align="right">姓名：</td>
                    <td  align="left"><input name="userName" type="text" placeholder="请输入姓名" class="easyui-validatebox span2" data-options="required:true" value=""></td>
                    <td  align="right">角色：</td>
                    <td  align="left">
                    	<input name="roleId" class="easyui-combobox" data-options="width:180, valueField: 'id', textField: 'name', url: '${path}/mgr/role/list'"/>
                    </td>
                </tr>
                <tr>
                    <td  align="right">财产保险：</td>
                    <td  align="left">
                    	<input type="radio" name="rootccCommissionType" class="easyui-validatebox" value="01">提成
        				<input type="radio" name="rootccCommissionType" class="easyui-validatebox" value="02">提成比例
                    </td>
                </tr>
                <tr>
                    <td  align="right"></td>
                    <td  align="left"><input name="rootccCommissionValue" type="text" class="easyui-validatebox span2" value=""></td>
                </tr>
                <tr>
                    <td  align="right">人寿保险：</td>
                    <td  align="left">
                    	<input type="radio" name="rootrsCommissionType" class="easyui-validatebox" value="01">提成
        				<input type="radio" name="rootrsCommissionType" class="easyui-validatebox" value="02">提成比例
                    </td>
                </tr>
                <tr>
                    <td  align="right"></td>
                    <td  align="left"><input name="rootrsCommissionValue" type="text" class="easyui-validatebox span2" value=""></td>
                </tr>
                 <tr>
                    <td  align="right">贷款业务：</td>
                    <td  align="left">
                    	<input type="radio" name="rootdkCommissionType" class="easyui-validatebox" value="01">提成
        				<input type="radio" name="rootdkCommissionType" class="easyui-validatebox" value="02">提成比例
                    </td>
                </tr>
                <tr>
                    <td align="right"></td>
                    <td align="left"><input name="rootdkCommissionValue" type="text" class="easyui-validatebox span2" value=""></td>
                </tr>
                <tr>
                    <td align="right">手机：</td>
                    <td align="left"><input name="telephone" type="text" class="easyui-validatebox span2" value=""></td>
                    <td align="right">Mail：</td>
                    <td align="left"><input name="mail" type="text" class="easyui-validatebox span2" value=""></td>
                </tr>
                <tr>
                    <td align="right">开户行：</td>
                    <td align="left"><input name="bankName" type="text" class="easyui-validatebox span2" value=""></td>
                    <td align="right">开户名称：</td>
                    <td align="left"><input name="bankHost" type="text" class="easyui-validatebox span2" value=""></td>
                </tr>
                <tr>
                    <td align="right">银行卡号：</td>
                    <td align="left" colspan="3"><input style="width: 390px" name="bankCard" type="text" class="easyui-validatebox span2" value=""></td>
                </tr>
                <tr>
                    <td align="right">备注</td>
                    <td align="left" colspan="3"><textarea name="description" style="width: 390px; height: 100px;"></textarea></td>
                </tr>
            </table>
        </form>
    </div>
</div>