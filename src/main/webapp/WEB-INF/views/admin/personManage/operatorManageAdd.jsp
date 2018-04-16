<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#opeartorAddForm').form({
			url : '${path}/mgr/personManage/operatorManage/operatorAdd',
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
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
</script>
<div style="width: 90%; height: 95%; border: 0px solid; padding-left: 40px; padding-top: 25px; font-size: 40px;">
	<form id="opeartorAddForm" method="post">
		<table style="width: 500px">
			<tr height="40px">
				<th align="right" width="60px">姓名(登录名)：</th>
				<td align="left" width="100px">
					<input type="text" name="userName" class="easyui-validatebox span2" data-options="required: true" style="width: 120px; height: 18px" placeholder="姓名">
				</td>
				<th align="right" width="70px">角色：</th>
				<td align="left" width="150px">
					<input name="roleId" style="width: 100px; font-size: 13px" class="easyui-combobox" data-options="required: true, panelHeight: 'auto',valueField:'roleId',textField:'roleName',url:'${path}/mgr/personManage/operatorManage/roleList'">
				</td>
			</tr>
			<tr height="40px">
				<th align="right">财产保险：</th>
				<td align="left" colspan="3">
					<input type="radio" name="rootccCommission" value="1">
					提成
					<input type="radio" name="rootccCommission" value="0">
					提成比例&nbsp;&nbsp;
					<input type="text" class="easyui-validatebox span2" data-options="required: true" name="rootccCommissionValue" style="width: 80px; height: 18px">
					&nbsp;&nbsp;(单位：提成-元，提成比例-%)
				</td>
			</tr>
			<tr height="40px">
				<th align="right">人寿保险：</th>
				<td align="left" colspan="3">
					<input type="radio" name="rootrsCommission" value="1">
					提成
					<input type="radio" name="rootrsCommission" value="0">
					提成比例&nbsp;&nbsp;
					<input type="text" class="easyui-validatebox span2" data-options="required: true" name="rootrsCommissionValue" style="width: 80px; height: 18px">
					&nbsp;&nbsp;(单位：提成-元，提成比例-%)
				</td>
			</tr>
			<tr height="40px">
				<th align="right">贷款业务：</th>
				<td align="left" colspan="3">
					<input type="radio" name="rootdkCommission" value="1">
					提成
					<input type="radio" name="rootdkCommission" value="0">
					提成比例&nbsp;&nbsp;
					<input type="text" class="easyui-validatebox span2" data-options="required: true" name="rootdkCommissionValue" style="width: 80px; height: 18px">
					&nbsp;&nbsp;(单位：提成-元，提成比例-%)
				</td>
			</tr>
			<tr height="40px">
				<th align="right">手机：</th>
				<td align="left">
					<input type="text" class="easyui-validatebox span2" data-options="required: true" name="telephone" style="width: 120px; height: 18px" placeholder="手机">
				</td>
				<th align="right">Mail：</th>
				<td align="left">
					<input type="text" class="easyui-validatebox span2" data-options="required: true" name="mail" style="width: 120px; height: 18px" placeholder="邮箱">
				</td>
			</tr>
			<tr height="40px">
				<th align="right">开户行：</th>
				<td align="left">
					<input type="text" class="easyui-validatebox span2" data-options="required: true" name="bankName" style="width: 120px; height: 18px" placeholder="开户行">
				</td>
				<th align="right">开户名称：</th>
				<td align="left">
					<input type="text" class="easyui-validatebox span2" data-options="required: true" name="bankHost" style="width: 150px; height: 18px" placeholder="开户名称">
				</td>
			</tr>
			<tr height="40px">
				<th align="right">银行卡号：</th>
				<td align="left" colspan="3">
					<input type="text" class="easyui-validatebox span2" data-options="required: true" name="bankCard" style="width: 280px; height: 18px" placeholder="银行卡号">
				</td>
			</tr>
			<tr height="80px">
				<th align="right" width="150px">备注：</th>
				<td align="left" width="480px" colspan="3">
					<textarea name="description" rows="6" cols="60" class="easyui-validatebox span2" data-options="required: true" placeholder="备注"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>