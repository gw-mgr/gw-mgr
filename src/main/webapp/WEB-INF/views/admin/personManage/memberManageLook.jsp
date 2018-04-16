<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="font-size: 20px; height: 600px; overflow: hidden; background-color: #fff; width: 600px; border: 0px solid red; padding-left:10px;padding-top: 10px">
		<table style="width: 700px" >
			<tr>
				<th height="30px" align="right" width="100px"><font color="red">ID：</font></th>
				<td width="200px"><font color="red">${memberInfo.userId}</font></td>
				<th height="30px" align="right" width="100px"><font color="red">所属服务商：</font></th>
				<td width="200px"><font color="red">${memberInfo.merchantName}</font></td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">姓名：</th>
				<td width="200px" colspan="3">${memberInfo.userName}</td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">证件类型：</th>
				<td width="200px"  colspan="3">${memberInfo.cardType}</td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">证件号码：</th>
				<td width="200px"  colspan="3">${memberInfo.cardId}</td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">电话：</th>
				<td width="200px">${memberInfo.mobilePhone}</td>
				<th height="30px" align="right" width="150px">手机：</th>
				<td width="200px">${memberInfo.telephone}</td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">Mail：</th>
				<td width="200px">${memberInfo.mail}</td>
				<th height="30px" align="right" width="150px">QQ：</th>
				<td width="200px">${memberInfo.qq}</td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">微信号：</th>
				<td width="200px">${memberInfo.wechatNum}</td>
				<th height="30px" align="right" width="150px"><font color="red">省份：</font></th>
				<td width="200px"><font color="red">${memberInfo.province}</font></td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">地址：</th>
				<td width="200px" colspan="3">${memberInfo.userAddr}</td>
			</tr>
			<tr>
				<th height="2px" align="right" colspan="4"><hr></th>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">保险公司：</th>
				<td width="200px" colspan="3">${memberInfo.insCompany}</td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">开户行：</th>
				<td width="200px">${memberInfo.bankName}</td>
				<th height="30px" align="right" width="150px">开户名称：</th>
				<td width="200px">${memberInfo.bankHost}</td>
			</tr>
			<tr>
				<th height="30px" align="right" width="80px">银行卡号：</th>
				<td width="200px" colspan="3">${memberInfo.bankCard}</td>
			</tr>

			<tr>
				<th height="30px" align="right" width="80px">会员备注：</th>
				<td width="200px" colspan="3">${memberInfo.remark}</td>
			</tr>
			<tr>
				<th height="30px" align="right" width="100px"><font color="red">直接推荐人：</font></th>
				<td width="160px"><font color="red">${memberInfo.recommender}</font></td>
				<th height="30px" align="right" width="150px"><font color="red">间接推荐人：</font></th>
				<td width="200px"><font color="red">${memberInfo.dirRecommender}</font></td>
			</tr>
		</table>
	</div>
</div>