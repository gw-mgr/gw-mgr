<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type='text/javascript' src='${path}/static/jquery.citys.js'></script>
<script type="text/javascript">
	$(function() {
		$('#yongJinZhengCeEditForm').form({
			url : '${path}/mgr/yongJinZhengCe/edit',
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
					var form = $('#yongJinZhengCeEditForm');
					parent.$.messager.alert('错误', eval(result.msg), 'error');
				}
			}
		});
		$('#city2').citys();
		$('#city').citys({
			code : '${yongJinZhengCe.province}'
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" style="overflow: hidden; padding: 10px;">
		<form id="yongJinZhengCeEditForm" method="post">
			<input type="hidden" name = 'id' value="${yongJinZhengCe.id}">
			<table class="grid">
				<tr>
					<th align="right" width="90px">保险公司名称：</th>
					<td align="left"><input name="insurerName" type="text" placeholder="请输入名称" class="easyui-validatebox span2" data-options="required:true" value="${yongJinZhengCe.insurerName}"></td>
				</tr>
				<tr>
					<th align="right" width="90px">基础佣金：</th>
					<td align="left"><input name="jichuYongj" style="width: 60px" type="text" class="easyui-validatebox span2" data-options="required:true" value="${yongJinZhengCe.jichuYongj}"> (%)</td>
				</tr>
				<tr>
					<th align="right" width="90px">综合金融奖：</th>
					<td align="left"><input name="zongheJr" style="width: 60px" type="text" class="easyui-validatebox span2" data-options="required:true" value="${yongJinZhengCe.zongheJr}"> (%)</td>
				</tr>
				<tr>
					<th align="right" width="90px">有效区域：</th>
					<td align="left">
						<div id="city2" class="citys" style="float: left">
							<select id="province" name="province"></select>
							<select id="city" name="city"></select>
							<select id="area" name="country"></select>
						</div>
					</td>
				</tr>
				<tr>
					<th align="right" width="90px">备注：</th>
					<td align="left"><textarea rows="3" name="remark" cols="30">${yongJinZhengCe.remark}</textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>