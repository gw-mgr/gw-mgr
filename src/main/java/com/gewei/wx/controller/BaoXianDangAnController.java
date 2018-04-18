<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#tMerchantManageEditForm').form({
			url : '${path}/mgr/tMerchantManage/edit',
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
		// 下拉框选择控件，下拉框的内容是动态查询数据库信息  
		$('#province3')
				.combobox(
						{
							url : '${path}/mgr/tMerchantManage/chinaAreaList?pId=0',
							editable : false, //不可编辑状态  
							cache : false,
							valueField : 'ID',
							textField : 'NAME',
							onHidePanel : function() {
								var province = $('#province3').combobox(
										'getValue');
								$('#city3').combobox('setValue', '');
								$("#country3").combobox("setValue", '');
								var country = $('#country3').combobox(
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
													$("#city3").combobox(
															"loadData", data);
												}
											});
								}
							}
						});
		$('#city3')
				.combobox(
						{
							editable : false, //不可编辑状态  
							cache : false,
							valueField : 'ID',
							textField : 'NAME',
							onHidePanel : function() {
								$("#country3").combobox("setValue", '');
								var city = $('#city3').combobox('getValue');
								if (city != '') {
									$
											.ajax({
												type : "POST",
												url : "${path}/mgr/tMerchantManage/chinaAreaList?pId="
														+ city,
												cache : false,
												dataType : "json",
												success : function(data) {
													$("#country3").combobox(
															"loadData", data);
												}
											});
								}
							}
						});
		$('#country3').combobox({
			editable : false, //不可编辑状态  
			cache : false,
			valueField : 'ID',
			textField : 'NAME',
			onHidePanel : function() {
				var str = $('#country3').combobox('getText');
				$("#cregicounty").val(str);
			}
		});

		$("#province3").val('${tMerchantManage.province}');
		$("#city3").val('${tMerchantManage.city}');
		$("#country3").val('${tMerchantManage.country}');

		// 授权区域dataGrid
		$.fn.zTree
				.init(
						$("#grantAreaTree"),
						{
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
							},
							callback : {
								onAsyncSuccess : function(event, treeId,
										treeNode, msg) {
									progressLoad();
									var $$tree = $.fn.zTree.getZTreeObj(treeId);
									$$tree.expandAll(true);
									$
											.post(
													'${path}/mgr/tMerchantManage/merchantGrant/findGrantIdsByMerchantId',
													{
														merchantId : '${tMerchantManage.merchantId}'
													},
													function(result) {
														var ids;
														if (result.success == true
																&& result.obj != undefined) {
															ids = $
																	.stringToList(result.obj
																			+ '');
														}
														if (ids.length > 0) {
															for (var i = 0; i < ids.length; i++) {
																var nodes = $$tree
																		.transformToArray($$tree
																				.getNodes());
																for (var j = 0; j < nodes.length; j++) {
																	var node = nodes[j];
																	if (node.id == ids[i]) {
																		$$tree
																				.checkNode(
																						node,
																						true,
																						false);
																	}
																}
															}
														}
													}, 'json');
									progressClose();
								}
							}
						});
	});
</script>
<div style="width: 90%; height: 95%; border: 0px solid; padding-left: 40px; padding-top: 25px; font-size: 40px;">
	<form id="tMerchantManageEditForm" action="${path}/mgr/tMerchantManage/edit" method="post" enctype="multipart/form-data">
		<table>
			<tr height="80px" style="width: 200px">
				<th align="right" width="100px">服务商头像：</th>
				<td align="left" width="480px">
					<div style="height: 70px; width: 100px; border: 0px solid; float: left">
						<img src="${path}${tMerchantManage.merchantPhotoUrl}" width="60px" height="60px">
					</div>
					<div style="height: 70px; width: 350px; border: 0px solid; float: left; padding-top: 10px; padding-left: 10px">
						<input type="file" id="headImage" name="file" style="width: 320px">
						<br>
						<br>
						只能上传单张10M以下的
						<span style="color: red"> PNG、JPG、JPEG、GIF</span>
						格式的图片
					</div>
				</td>
			</tr>
			<tr height="35px" style="width: 200px">
				<th align="right" width="100px">服务商名称：</th>
				<td>
					<input name="merchantId" value="${tMerchantManage.merchantId}" type="hidden" class="easyui-validatebox span2" style="width: 370px">
					<input name="merchantName" value="${tMerchantManage.merchantName}" type="text" value="" class="easyui-validatebox span2" data-options="required: true" style="width: 370px">
				</td>
			</tr>
			<tr height="70px" style="width: 200px">
				<th align="right" width="100px">服务商地址：</th>
				<td>
					<div style="height: 10px"></div>
					<input id="province3" name="province" data-options="required: true" style="width: 140px;" value="${tMerchantManage.province}">
					<input id="city3" name="city" data-options="required: true" style="width: 95px;" value="${tMerchantManage.city}">
					<input id="country3" name="country" data-options="required: false" style="width: 120px;" value="${tMerchantManage.country}">
					<br>
					<div style="height: 10px"></div>
					<input name="merchantAddr" type="text" value="${tMerchantManage.merchantAddr}" class="easyui-validatebox span2" data-options="required: true" style="width: 370px">
				</td>
			</tr>
			<tr height="200px" style="width: 200px">
				<th align="right" width="100px">授权区域：</th>
				<td>
					<div id="roleGrantLayout" class="easyui-layout" data-options="fit:true,">
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
					<input name="merchantType" value="${tMerchantManage.merchantType}" style="width: 400px; font-size: 13px" class="easyui-combobox" data-options="required: true, multiple:true,panelHeight: 'auto',valueField:'CATEGORY_ID',textField:'CATEGORY_NAME',url:'${path}/mgr/tMerchantManage/addMerchantTypeList'">
					(多选)
				</td>
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
