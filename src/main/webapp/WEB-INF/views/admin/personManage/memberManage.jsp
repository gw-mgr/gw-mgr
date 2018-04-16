<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	var memberManageDataGrid;
	$(function() {
		memberManageDataGrid = $('#memberManageDataGrid')
				.datagrid(
						{
							url : '${path}/mgr/personManage/memberDataGrid',
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : false,
							idField : 'userId',
							sortName : 'm.UPDATE_TIME',
							sortOrder : 'desc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [
									{
										width : '100',
										title : '姓名',
										field : 'userName',
										align : 'center',
										sortable : true
									},
									{
										width : '100',
										title : '手机',
										field : 'telephone',
										align : 'center',
										sortable : true
									},
									{
										width : '110',
										title : '微信号',
										field : 'wechatNum',
										align : 'center',
										sortable : true
									},
									{
										width : '90',
										title : '省份',
										field : 'province',
										align : 'center',
										sortable : true
									},
									{
										width : '200',
										title : '地址',
										field : 'userAddr',
										halign : 'center',
										align : 'left',
										sortable : true
									},
									{
										width : '100',
										title : '所属服务商',
										field : 'merchantName',
										align : 'center',
										sortable : true
									},
									{
										width : '90',
										title : '直接推荐人',
										field : 'recommender',
										align : 'center',
										sortable : true
									},
									{
										width : '90',
										title : '间接推荐人',
										field : 'dirRecommender',
										align : 'center',
										sortable : true
									},
									{
										width : '80',
										title : '创建时间',
										field : 'createTime',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											value = value.toString();
											if (value == undefined
													|| value == ""
													|| value.length != 14) {
												return "";
											}
											var year = value.substring(0, 4);
											var month = value.substring(4, 6);
											var day = value.substring(6, 8);
											return year + "-" + month + "-"
													+ day;
										}
									},
									{
										width : '50',
										title : '状态',
										field : 'status',
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											switch (value) {
											case "01":
												return '启用';
											case "02":
												return '停用';
											case "03":
												return '删除';
											}
										}
									},
									{
										field : 'action',
										title : '操作',
										align : 'center',
										width : 250,
										formatter : function(value, row, index) {
											var str = '';
											<shiro:hasPermission name="/personManage/memberManage/look">
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="tMemberManage-easyui-linkbutton-look" data-options="plain:true,iconCls:\'fi-eye icon-blue\'" onclick="tMemberManageLookFun(\'{0}\');" ></a>',
															row.userId);
											</shiro:hasPermission>
											if (row.status == '02') {
												<shiro:hasPermission name="/personManage/memberManage/up">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="tMemberManage-easyui-linkbutton-up" data-options="plain:true,iconCls:\'fi-check icon-blue\'" onclick="tMemberManageUpdateFun(\'{0}\',\'01\');" ></a>',
																row.userId);
												</shiro:hasPermission>
											}
											if (row.status == '01') {
												<shiro:hasPermission name="/personManage/memberManage/down">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="tMemberManage-easyui-linkbutton-down" data-options="plain:true,iconCls:\'fi-alert icon-red\'" onclick="tMemberManageUpdateFun(\'{0}\',\'02\');" ></a>',
																row.userId);
												</shiro:hasPermission>
											}
											if (row.status == '02') {
												<shiro:hasPermission name="/personManage/memberManage/delete">
												str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
												str += $
														.formatString(
																'<a href="javascript:void(0)" class="tMemberManage-easyui-linkbutton-delete" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="tMemberManageUpdateFun(\'{0}\',\'03\');" ></a>',
																row.userId);
												</shiro:hasPermission>
											}
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.tMemberManage-easyui-linkbutton-look')
										.linkbutton({
											text : '查看'
										});
								$('.tMemberManage-easyui-linkbutton-up')
										.linkbutton({
											text : '启用'
										});
								$('.tMemberManage-easyui-linkbutton-down')
										.linkbutton({
											text : '停用'
										});
								$('.tMemberManage-easyui-linkbutton-delete')
										.linkbutton({
											text : '删除'
										});
							}
						});
	});

	// 修改状态
	function tMemberManageUpdateFun(memberId, status) {
		parent.$.messager.confirm('询问', '您是否要修改当前角色状态？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path}/mgr/personManage/memberManage/updateStatus', {
					memberId : memberId,
					status : status
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						memberManageDataGrid.datagrid('reload');
					}
					progressClose();
					memberManageDataGrid.datagrid('unselectAll');
				}, 'JSON');
			}
		});
	}
	// 查看详情
	function tMemberManageLookFun(memberId) {
		parent.$.modalDialog({
			title : '会员详情',
			width : 720,
			height : 550,
			href : '${path }/mgr/personManage/memberManage/memberLook?memberId='
					+ memberId,
			buttons : [ {
				text : '关闭',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			} ]
		});
		memberManageDataGrid.datagrid('unselectAll');
	}
	/**
	 * 搜索
	 */
	function tMemberManageSearchFun() {
		memberManageDataGrid.datagrid('load', $
				.serializeObject($('#tMemberManageSearchForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 120px; overflow: hidden; background-color: #fff">
		<div style="border: 0px solid; height: 25px; width: 1300px; background-color: #DBDBDB; font-size: 16px; margin-top: 2px; padding-left: 15px; padding-top: 7px">搜索</div>
		<form id="tMemberManageSearchForm">
			<div style="width: 1200px; height: 40px; border: 0px solid red;">
				<div style="border: 0px solid green; float: left; width: 200px; height: 35px; font-size: 16px; margin-top: 2px; padding-left: 50px; padding-top: 10px">
					关键字搜索&nbsp;&nbsp;
					<select id="keywordType" name="keywordType" class="easyui-combobox" data-options="width:90,height:25,editable:false,panelHeight:'auto'">
						<option value="USER_NAME">姓名</option>
						<option value="TELEPHONE">手机</option>
						<option value="WECHAT_NAME">微信昵称</option>
						<option value="WECHAT_NUM">微信号</option>
					</select>
				</div>
				<div style="border: 0px solid green; float: left; width: 220px; height: 35px; font-size: 16px; padding-left: 12px; padding-top: 12px">
					<input name="keywordInfo" placeholder="搜索内容" type="text" style="width: 220px; height: 18px">
				</div>
				<div style="border: 0px solid green; float: left; width: 150px; height: 35px; font-size: 16px; margin-top: 2px; padding-left: 12px; padding-top: 8px">
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="tMemberManageSearchFun();">查询</a>
				</div>
			</div>
		</form>
		<br>
		<div style="float: left; border: 0px solid; width: 1300px; height: 35px; background-color: #DBDBDB; padding-left: 15px;">
			<div style="border: 0px solid; float: left; width: 700px; margin-top: 6px; font-size: 16px;">列表</div>
			<!-- <div style="border: 0px solid; float: left; width: 80px; margin-top: 3px; margin-left: 20px">
				<button type="button" onclick="addMerchantFun()" style="width: 80px; height: 26px;">批量删除</button>
			</div>
			<div style="border: 0px solid; float: left; width: 120px; margin-top: 3px; margin-left: 20px">
				<button type="button" id="importExcel" onclick="importExcelFun()" style="width: 120px; height: 26px;">批量导出EXCEL</button>
			</div>
			<div style="border: 0px solid; float: left; width: 120px; margin-top: 3px; margin-left: 20px">
				<button type="button" id="importExcel" onclick="importExcelFun()" style="width: 100px; height: 26px;">EXCEL导入</button>
			</div> -->
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="memberManageDataGrid" data-options="fit:true,border:false"></table>
	</div>
</div>
