<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	var messageDataGrid;
	$(function() {
		messageDataGrid = $('#messageDataGrid')
				.datagrid(
						{
							url : '${path}/mgr/zmessage/dataGrid',
							striped : true,
							rownumbers : true,
							pagination : true,
							idField : 'id',
							sortName : 'id',
							sortOrder : 'asc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [
									{
										width : '750',
										title : '信息内容',
										align : 'left',
										halign : 'center',
										field : 'content'
									},
									{
										width : '150',
										title : '创建时间',
										align : 'center',
										field : 'createTime',
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
											var hour = value.substring(8, 10);
											var min = value.substring(10, 12);
											var second = value
													.substring(12, 14);
											return year + "-" + month + "-"
													+ day + "&nbsp;&nbsp;"
													+ hour + ":" + min + ":"
													+ second;
										}
									},
									{
										width : '90',
										title : '类型',
										align : 'center',
										field : 'type',
										sortable : true,
										formatter : function(value, row, index) {
											switch (value) {
											case '01':
												return '服务商申请';
											case '02':
												return '提现申请';
											}
										}
									},
									{
										width : '60',
										title : '状态',
										align : 'center',
										field : 'status',
										sortable : true,
										formatter : function(value, row, index) {
											switch (value) {
											case '01':
												return '未读';
											case '02':
												return '已读';
											case '03':
												return '已删除';
											}
										}
									},

									{
										field : 'action',
										title : '操作',
										align : 'center',
										width : 80,
										formatter : function(value, row, index) {
											var str = '';
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="message-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-eye icon-blue\'" onclick="messageEditFun(\'{0}\',\'{1}\');" ></a>',
															row.id, row.type);
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.message-easyui-linkbutton-edit')
										.linkbutton({
											text : '查看'
										});
							},
							toolbar : '#messageToolbar'
						});
	});

	// ajax请求数据：消息数量
	$.ajax({
		type : "get",
		dataType : "json",
		url : "${path}/mgr/zmessage/getCount",
		success : function(result) {
			$("#messageCount2").html("( " + result.totalCount + " )");
		}
	});

	// 修改状态
	function editAllStatus() {
		// 状态修改
		parent.$.messager.confirm('询问', '是否全部标记为已读？', function(b) {
			if (b) {
				progressLoad();
				$.get('${path}/mgr/zmessage/editAllStatus', function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						messageDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
	// 修改状态
	function messageEditFun(id, type) {
		// 状态修改
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = messageDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			messageDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '查看后该消息将不再显示？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path}/mgr/zmessage/editStatus', {
					id : id
				}, function(result) {
					if (result.success) {
						messageDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
			parent.$.messager.confirm('询问', '是否去管理页面处理该消息？', function(b) {
				if (b) {
					// 页面条状
					var href = "";
					var tabName = "";
					if (type == '01') {
						tabName = "服务商管理";
						href = "${path}/mgr/tMerchantManage/manager?status=01";
					} else if (type == '02') {
						tabName = "提现审核";
						href = "${path}/mgr/ExamineManage/getCash";
					}
					// 添加一个新的标签页面板（tab panel）
					if ($("#index_tabs").tabs('exists', tabName)) {
						$('#index_tabs').tabs('select', tabName);
					} else {
						$('#index_tabs').tabs('add', {
							title : tabName,
							closable : true,
							border : false,
							selected : true,
							fit : true,
							href : href
						});
					}
				}
			});
		});
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 110px; overflow: hidden; background-color: #fff">
		<div style="border: 0px solid; height: 25px; width: 1300px; background-color: #DBDBDB; font-size: 16px; margin-top: 2px; padding-left: 15px; padding-top: 7px">搜索</div>
		<div style="border: 0px solid; margin-left: 30px; margin-top: 10px; font-weight: bold; float: left; height: 30px">
			<div style="border: 0px solid; font-size: 20px; float: left">消息类型&nbsp;&nbsp;&nbsp;&nbsp;</div>
			<div style="border: 0px solid; font-size: 15px; float: left; margin-top: 5px">
				<input type="radio">
				未读消息
				<font id="messageCount2" color="red"></font>
			</div>
		</div>
		<div style="float: left; border: 0px solid; width: 1300px; height: 35px; background-color: #DBDBDB; padding-left: 15px;">
			<div style="border: 0px solid; float: left; width: 900px; margin-top: 6px; font-size: 16px;">消息列表</div>
			<div style="border: 0px solid; float: left; width: 120px; margin-top: 4px; margin-left: 60px">
				<button type="button" onclick="editAllStatus()" style="width: 120px; height: 26px;">全部标记为已读</button>
			</div>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="messageDataGrid" data-options="fit:true,border:false"></table>
	</div>
</div>
