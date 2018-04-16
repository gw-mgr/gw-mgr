<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#tMerchantManageEditForm').form({
			url : '${path}/mgr/tMerchantManage/lookPage',
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
					var form = $('#tMerchantManageEditForm');
					parent.$.messager.alert('错误', eval(result.msg), 'error');
				}
			}
		});
	});
</script>
<div style="width: 88%; height: 95%; border: 0px solid; padding-left: 30px; padding-top: 5px; font-size: 40px;">
	<form id="tMerchantManageEditForm" action="${path}/mgr/tMerchantManage/edit" method="post" enctype="multipart/form-data">
		<table border="0">
			<tr height="50px">
				<th align="right" width="80px">服务商头像：</th>
				<td align="left" width="480px" colspan="3">
					<div style="height: 50px; width: 50px; border: 0px solid; float: left">
						<img src="${path}${tMerchantManage.merchantPhotoUrl}" width="50px" height="50px">
					</div>
				</td>
			</tr>
			<tr height="35px" style="width: 200px">
				<th align="right" width="80px">服务商名称：</th>
				<td colspan="3">${tMerchantManage.merchantName}</td>
			</tr>
			<tr height="35px" style="width: 200px">
				<th align="right" width="80px">服务商地址：</th>
				<td colspan="3">${tMerchantManage.merchantAddr}</td>
			</tr>
			<tr height="35px" style="width: 200px">
				<th align="right" width="80px">授权区域：</th>
				<td colspan="3">${tMerchantManage.grantArea}</td>
			</tr>
			<tr height="35px" style="width: 200px">
				<th align="right" width="80px">服务范围：</th>
				<td colspan="3">${tMerchantManage.merchantType}</td>
			</tr>
			<tr height="35px" style="width: 200px">
				<th align="right" width="80px">联系电话：</th>
				<td>${tMerchantManage.telphone}</td>
				<th align="right" width="60px">余额：</th>
				<td>${tMerchantManage.balanceResp}<font style="font-weight: bold">&nbsp;&nbsp;(元)</font>
				</td>
			</tr>
			<tr height="70px" style="width: 200px">
				<th align="right" width="80px">介绍：</th>
				<td colspan="3">${tMerchantManage.merchantDescript}</td>
			</tr>
		</table>
	</form>
</div>