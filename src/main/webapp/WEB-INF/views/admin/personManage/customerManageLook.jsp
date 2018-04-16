<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
</script>
<div style="height: 95%; border: 0px solid;width: 620px;padding-top: 10px;padding-left: 15px; font-size: 40px;border: 0px solid red">
	<form id="customerInfoLookForm" method="post">
		<table>
			<tr>
				<th height="30px" align="right" width="140px">ID：</th>
				<td width="180px">${customerInfo.userId}</td>
				<th height="30px" align="right" width="65px"><font color="red">所属机构：</font></th>
				<td width="100px"><font color="red">${customerInfo.organizations}</font></td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">姓名：</th>
				<td width="200px" colspan="3">${customerInfo.userName}</td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">证件类型：</th>
				<td width="200px"  colspan="3">${customerInfo.cardType}</td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">证件号码：</th>
				<td width="200px"  colspan="3">${customerInfo.cardId}</td>
			</tr>
			<tr>
				<th height="2px" align="right" colspan="4"><hr></th>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">车牌号：</th>
				<td width="200px" colspan="3">${customerInfo.carNum}</td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">车架号：</th>
				<td width="200px" colspan="3">${customerInfo.carFrameNum}</td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">发动机号：</th>
				<td width="200px" colspan="3">${customerInfo.engineNum}</td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">厂牌类型：</th>
				<td width="200px" colspan="3">${customerInfo.changType}</td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">初登日期：</th>
				<td width="200px">${customerInfo.registerTime}</td>
				<th height="30px" align="right" width="150px">年审到期日：</th>
				<td width="200px">${customerInfo.examineTime}</td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">商业险起保日：</th>
				<td width="200px">${customerInfo.businessInsStartTime}</td>
				<th height="30px" align="right" width="150px">交强险起保日：</th>
				<td width="200px">${customerInfo.trafficInsStartTime}</td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">车辆类型：</th>
				<td width="200px" colspan="3">${customerInfo.carType}</td>
			</tr>
		</table>
	</form>
</div>