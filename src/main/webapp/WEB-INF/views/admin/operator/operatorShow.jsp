<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $("input[name='rootccCommissionType'][value=${operator.rootccCommissionType}]").attr("checked",true);
        $("input[name='rootrsCommissionType'][value=${operator.rootrsCommissionType}]").attr("checked",true);
        $("input[name='rootdkCommissionType'][value=${operator.rootdkCommissionType}]").attr("checked",true);
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="operatorEditForm" method="post">
        	<input type="hidden" name="operId" value="${operator.operId }">
            <table class="grid">
                <tr>
                    <td  align="right">姓名：</td>
                    <td  align="left">${operator.userName }</td>
                    <td  align="right">角色：</td>
                    <td  align="left">
                    	<input name="roleId" class="easyui-combobox" disabled="disabled" data-options="width:180, valueField: 'id', textField: 'name', url: '${path}/mgr/role/list', value: '${operator.roleId }'"/>
                    </td>
                </tr>
                <tr>
                    <td  align="right">财产保险：</td>
                    <td  align="left">
                    	${operator.rootccCommissionType==01?'提成':'提成比例'}
                    </td>
                </tr>
                <tr>
                    <td  align="right"></td>
                    <td  align="left">${operator.rootccCommissionValue }</td>
                </tr>
                <tr>
                    <td  align="right">人寿保险：</td>
                    <td  align="left">
                    ${operator.rootrsCommissionType==01?'提成':'提成比例'}
                    </td>
                </tr>
                <tr>
                    <td  align="right"></td>
                    <td  align="left">${operator.rootrsCommissionValue }</td>
                </tr>
                 <tr>
                    <td  align="right">贷款业务：</td>
                    <td  align="left">
                    ${operator.rootdkCommissionType==01?'提成':'提成比例'}
                    </td>
                </tr>
                <tr>
                    <td align="right"></td>
                    <td align="left">${operator.rootdkCommissionValue }</td>
                </tr>
                <tr>
                    <td align="right">手机：</td>
                    <td align="left">${operator.telephone }</td>
                    <td align="right">Mail：</td>
                    <td align="left">${operator.mail }</td>
                </tr>
                <tr>
                    <td align="right">开户行：</td>
                    <td align="left">${operator.bankName }</td>
                    <td align="right">开户名称：</td>
                    <td align="left">${operator.bankHost }</td>
                </tr>
                <tr>
                    <td align="right">银行卡号：</td>
                    <td align="left" colspan="3">${operator.bankCard }</td>
                </tr>
                <tr>
                    <td align="right">备注</td>
                    <td align="left" colspan="3"><textarea name="description" disabled="disabled" style="width: 390px; height: 100px;">${operator.description }</textarea></td>
                </tr>
            </table>
        </form>
    </div>
</div>