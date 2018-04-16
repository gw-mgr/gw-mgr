<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type='text/javascript' src='${path }/static/jquery.citys.js'></script> 
<script type="text/javascript">
	$(function() {
		$('#city').citys({code:'${salesMan.district}'});
	});
</script>
<div style="width: 88%; height: 95%; border: 0px solid; padding-left: 30px; padding-top: 5px; font-size: 40px;">
	<form id="tMerchantManageEditForm" action="${path}/mgr/tMerchantManage/edit" method="post" enctype="multipart/form-data">
		<table class="grid">
			<tr height="50px">
				<th align="right" width="80px">服务商头像：</th>
				<td align="left" width="480px" colspan="3">
					<div style="height: 50px; width: 50px; border: 0px solid; float: left">
						<img src="${path}${tMerchantManage.merchantPhotoUrl}" width="50px" height="50px">
					</div>
					<div style="height: 70px; width: 350px; border: 0px solid; float: left; padding-top: 10px; padding-left: 10px">
						<input type="file" id="headImage" name="file" style="width: 320px">
					</div>
				</td>
			</tr>
			<tr height="35px" style="width: 200px">
				<th align="right" width="80px">服务商名称：</th>
				<td>
					<input name="merchantId" value="${tMerchantManage.merchantId}" type="hidden" class="easyui-validatebox span2" style="width: 370px">
					<input name="merchantName" value="${tMerchantManage.merchantName}" type="text" value="" class="easyui-validatebox span2" data-options="required: true" style="width: 370px">
				</td>
			</tr>
			<tr height="35px" style="width: 200px">
				<th align="right" width="80px">服务商地址：</th>
				<td colspan="3">
					<div id="city" class="citys">
	                    <select id="province" name="province"></select>
	                    <select id="city" name="city"></select>
	                    <select id="area" name="country"></select>
					</div>
					<div style="height: 10px"></div>
					<input name="merchantAddr" type="text" value="${tMerchantManage.merchantAddr}" class="easyui-validatebox span2" data-options="required: true" style="width: 370px">
				</td>
			</tr>
			<tr height="35px" style="width: 200px">
				<th align="right" width="80px">授权区域：</th>
				<td colspan="3">${tMerchantManage.grantArea}</td>
			</tr>
			<tr height="35px" style="width: 200px">
				<th align="right" width="80px">服务范围：</th>
				<td colspan="3">${tMerchantManage.merchantType}</td>
			</tr>
			<tr height="40px" style="width: 200px">
				<th align="right" width="100px">联系电话：</th>
				<td>
					<input name="telphone" type="text" value="${tMerchantManage.telphone}" class="easyui-validatebox span2" data-options="required: true" style="width: 100px">
				</td>
			</tr>
			<tr height="100px" style="width: 200px">
				<th align="right" width="100px">介绍：</th>
				<td>
					<textarea name="merchantDescript" class="easyui-validatebox span2" style="width: 370px; height: 80px" data-options="required: true">${tMerchantManage.merchantDescript}</textarea>
				</td>
			</tr>
		</table>
	</form>
</div>