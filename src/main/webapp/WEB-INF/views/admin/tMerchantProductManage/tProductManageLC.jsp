<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden; padding: 3px;">
		<table>
			<tr height="10px">
				<th width="100px" align="right" colspan="4"></th>
			</tr>
			<tr height="30px">
				<th width="150px" align="right">产品ID:</th>
				<td width="150px" colspan="3">${tProductManage.productId}</td>
			</tr>
			<tr height="30px">
				<th width="100px" align="right">产品名称:</th>
				<td width="200px" colspan="3">${tProductManage.productName}</td>
			</tr>
			<tr height="30px">
				<th width="100px" align="right">目标年化收益:</th>
				<td width="150px" align="left">${tProductManage.lGoalIncome}%</td>
				<th width="100px" align="right">投资期限(天):</th>
				<td width="150px" align="left">${tProductManage.lInvestDays}</td>
			</tr>
			<tr height="30px">
				<th width="100px" align="right">起购金额(元):</th>
				<td width="150px" align="left">${tProductManage.lMixBuyMoneyResp}</td>
				<th width="100px" align="right">风险等级:</th>
				<td width="150px" align="left">${tProductManage.lRiskGrade}(级)</td>
			</tr>
			<tr height="30px">
				<th width="100px" align="right">平台佣金:</th>
				<td colspan="3">${tProductManage.commissionStr}</td>
			</tr>
			<tr height="30px">
				<th width="100px" align="right">产品介绍:</th>
				<td colspan="3"><textarea rows="10" cols="50" readonly="readonly">${tProductManage.productDescript}</textarea></td>
			</tr>
		</table>
	</div>
</div>