<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	$(function() {
		// 下拉框选择控件，下拉框的内容是动态查询数据库信息  
		$('#province2')
				.combobox(
						{
							url : '${path}/mgr/tMerchantManage/chinaAreaList?pId=0',
							editable : false, //不可编辑状态  
							cache : false,
							valueField : 'ID',
							textField : 'NAME',
							onHidePanel : function() {
								var province = $('#province2').combobox(
										'getValue');
								$('#city2').combobox('setValue', '');
								$("#country2").combobox("setValue", '');
								var country = $('#country2').combobox(
										'getValue');
								if (province != '') {
									$
											.ajax({
												type : "POST",
												url : "${path}/mgr/tMerchantManage/chinaAreaList?pId="
														+ province,
												cache : false,
												dataType : "json",
												success : function(data) {
													$("#city2").combobox(
															"loadData", data);
												}
											});
								}
							}
						});
		$('#city2')
				.combobox(
						{
							editable : false, //不可编辑状态  
							cache : false,
							valueField : 'ID',
							textField : 'NAME',
							onHidePanel : function() {
								$("#country2").combobox("setValue", '');
								var city = $('#city2').combobox('getValue');
								if (city != '') {
									$
											.ajax({
												type : "POST",
												url : "${path}/mgr/tMerchantManage/chinaAreaList?pId="
														+ city,
												cache : false,
												dataType : "json",
												success : function(data) {
													$("#country2").combobox(
															"loadData", data);
												}
											});
								}
							}
						});
		$('#country2').combobox({
			editable : false, //不可编辑状态  
			cache : false,
			valueField : 'ID',
			textField : 'NAME',
			onHidePanel : function() {
				var str = $('#country2').combobox('getText');
				$("#cregicounty").val(str);
			}
		});
		$('#tMerchantManageAddForm').form({
			url : '${path}/mgr/tMerchantManage/add',
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
					var form = $('#tMerchantManageAddForm');
					parent.$.messager.alert('错误', eval(result.msg), 'error');
				}
			}
		});
		// 授权区域dataGrid
		$.fn.zTree.init($("#grantAreaTree"), {
			check : {
				enable : true,
				nocheckInherit : true,
				chkboxType : {
					"Y" : "",
					"N" : ""
				}
			},
			data : {
				simpleData : {
					enable : true,
					rootPId : 1
				}
			},
			view : {
				txtSelectedEnable : true
			},
			async : {
				enable : true,
				url : '${path}/mgr/tMerchantManage/merchantGrant/allTrees',
			}
		});
	});
</script>
<div style="width: 90%; height: 95%; border: 0px solid; padding-left: 40px; padding-top: 25px; font-size: 40px;">
	<form id="tMerchantManageAddForm" action="${path}/mgr/tMerchantManage/add" method="post" enctype="multipart/form-data">
		<table>
			<tr height="60px" style="width: 200px">
				<th align="right" width="100px">服务商头像：</th>
				<td align="left" width="480px">
					<input type="file" name="file" id="headImage" style="width: 320px">
					<br>
					<br>
					只能上传单张10M以下的
					<span style="color: red"> PNG、JPG、JPEG、GIF</span>
					格式的图片
				</td>
			</tr>
			<tr height="35px" style="width: 200px">
				<th align="right" width="100px">服务商名称：</th>
				<td>
					<input name="merchantName" type="text" placeholder="服务商名称" class="easyui-validatebox span2" data-options="required: true" style="width: 370px">
				</td>
			</tr>
			<tr height="70px" style="width: 200px">
				<th align="right" width="100px">服务商地址：</th>
				<td>
					<div style="height: 10px"></div>
					<input id="province2" name="province" data-options="required: true" style="width: 140px;">
					<input id="city2" name="city" data-options="required: true" style="width: 95px;">
					<input id="country2" name="country" data-options="required: false" style="width: 120px;">
					<br>
					<div style="height: 10px"></div>
					<input name="merchantAddr" type="text" placeholder="详细地址" class="easyui-validatebox span2" data-options="required: true" style="width: 370px">
				</td>
			</tr>
			<tr height="35px" style="width: 200px">
				<th align="right" width="100px">服务商经纬度：</th>
				<td>
					经度&nbsp;&nbsp;&nbsp;
					<input name="longitude" type="text" placeholder="服务商经度" class="easyui-validatebox span2" data-options="required: false" style="width: 100px">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;纬度&nbsp;&nbsp;&nbsp;
					<input name="latitude" type="text" placeholder="服务商纬度" class="easyui-validatebox span2" data-options="required: false" style="width: 100px">
					&nbsp;&nbsp;&nbsp;
					<font color="red">*&nbsp;百度地图为准</font>
				</td>
			</tr>
			<tr height="150px" style="width: 200px">
				<th align="right" width="100px">授权区域：</th>
				<td>
					<div id="roleGrantLayout" class="easyui-layout" data-options="fit:true,border:true">
						<div data-options="region:'west'" title="授权区域" style="width: 400px; padding: 1px;">
							<div class="well well-small">
								<ul id="grantAreaTree" class="ztree"></ul>
								<input name="grantAreaIds" id="grantAreaIds" type="hidden" />
							</div>
						</div>
					</div>
				</td>
			</tr>
			<tr height="40px" style="width: 200px">
				<th align="right" width="100px">服务范围：</th>
				<td>
					<div style="height: 3px"></div>
					<input name="merchantType" style="width: 400px; font-size: 13px" class="easyui-combobox" data-options="required: true, multiple:true,panelHeight: 'auto',valueField:'CATEGORY_ID',textField:'CATEGORY_NAME',url:'${path}/mgr/tMerchantManage/addMerchantTypeList'">
					(多选)
				</td>
			</tr>
			<tr height="40px" style="width: 200px">
				<th align="right" width="100px">联系电话：</th>
				<td>
					<input name="telphone" type="text" placeholder="联系电话" class="easyui-validatebox span2" data-options="required: true" style="width: 100px">
				</td>
			</tr>
			<tr height="100px" style="width: 200px">
				<th align="right" width="100px">介绍：</th>
				<td>
					<textarea name="merchantDescript" placeholder="介绍" class="easyui-validatebox span2" data-options="required: true" style="width: 370px; height: 80px"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>