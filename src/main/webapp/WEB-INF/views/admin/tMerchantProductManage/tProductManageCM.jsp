<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden; padding: 3px;">
		<table style="width: 450px">
			<tr height="10px">
				<th width="100px" align="right" colspan="3"></th>
			</tr>
			<tr height="30px">
				<th width="100px" align="right">产品名称:</th>
				<th width="10px"></th>
				<td width="200px">${tProductManage.productName}</td>
			</tr>
			<tr height="30px">
				<th width="100px" align="right">当前价格:</th>
				<th width="10px"></th>
				<td>${tProductManage.currPriceStr}</td>
			</tr>
			<tr height="30px">
				<th width="100px" align="right">原始价格:</th>
				<th width="10px"></th>
				<td>${tProductManage.origPriceStr}</td>
			</tr>
			<tr height="30px">
				<th width="100px" align="right">平台佣金:</th>
				<th width="10px"></th>
				<td>${tProductManage.commissionStr}</td>
			</tr>
			<tr height="30px">
				<th width="100px" align="right">产品介绍:</th>
				<th width="10px"></th>
				<td>${tProductManage.productDescript}</td>
			</tr>
		</table>
	</div>
</div>