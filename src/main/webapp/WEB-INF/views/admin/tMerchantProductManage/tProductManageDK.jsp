<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden; padding: 3px;">
		<table>
			<tr height="10px">
				<th width="100px" align="right" colspan="3"></th>
			</tr>
			<tr height="30px">
				<th width="100px" align="right">产品ID:</th>
				<td width="200px" colspan="2">${tProductManage.productId}</td>
			</tr>
			<tr height="30px">
				<th width="100px" align="right">产品名称:</th>
				<td width="200px" colspan="2">${tProductManage.productName}</td>
			</tr>
			<tr height="30px">
				<th width="100px" align="right">平台佣金:</th>
				<td colspan="2" colspan="2">${tProductManage.commissionStr}</td>
			</tr>
			<tr height="30px">
				<th align="right">基本要求:</th>
				<th align="right" width="60px">婚姻状况:</th>
				<td>${tProductManage.dMarryFlag}</td>
			</tr>
			<tr height="30px">
				<td></td>
				<th align="right">有无房产:</th>
				<td>${tProductManage.dHouseFlag}</td>
			</tr>
			<tr height="30px">
				<td></td>
				<th align="right">有无社保:</th>
				<td>${tProductManage.dSocialFlag}</td>
			</tr>
			<tr height="30px">
				<td></td>
				<th align="right">每月收入:</th>
				<td>${tProductManage.dIncome}
			</tr>
			<tr height="30px">
				<td></td>
				<th align="right">有无车辆:</th>
				<td>${tProductManage.dCarFlag}</td>
			</tr>
			<tr height="30px">
				<th align="right">产品介绍:</th>
				<td colspan="2">
					<textarea name="productDescript" style="width: 390px; height: 100px;" readonly="readonly">${tProductManage.productDescript}</textarea>
				</td>
			</tr>
		</table>
	</div>
</div>