<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<%@ include file="/commons/basejs.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#dataGrid')
				.datagrid(
						{
							url : '${path}/mgr/user/dataGrid',
							fit : true,
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : true,
							idField : 'id',
							sortName : 'id',
							sortOrder : 'asc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							columns : [ [
									{
										width : '150',
										title : '到期时间',
										field : 'loginName',
									},
									{
										width : '150',
										title : '业务类型',
										field : 'name',
									},
									{
										width : '150',
										title : '车牌号',
										field : 'organizationId',
									},
									{
										width : '150',
										title : '车主',
										field : 'organizationName'
									},
									{
										width : '150',
										title : '总保费',
										field : 'createTime',
									},
									{
										width : '150',
										title : '业务员',
										field : 'age',
									},
									{
										field : 'action',
										title : '操作',
										width : 200,
										formatter : function(value, row, index) {
											return "<a href=\"#\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-add',plain:true\"/a>";
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.easyui-linkbutton').linkbutton({
									text : '退出',
									plain : true,
									iconCls : 'icon-add'
								});
							}
						});
	});
</script>
<div
	style="display: inline-block;width: 37%; height:18%; margin: 0 0 0 10%; background-image: url('${staticPath }/static/style/images/u131.png');background-repeat:no-repeat">
	<p
		style="width: 100%; margin: 0 10% 0 10%; font-size: 17px; line-height: 200%">
		<span>财产保险</span>
	</p>
	<p
		style="width: 100%; margin: 0 10% 0 10%; font-size: 17px; line-height: 200%">
		<span>（未成交：</span><span>12）</span>
	</p>
</div>
<div
	style="display: inline-block;width: 37%; height:18%; margin: 0 10% 0 1%; background-image: url('${staticPath }/static/style/images/u131.png');background-repeat:no-repeat">
	<p
		style="width: 100%; margin: 0 10% 0 10%; font-size: 17px; line-height: 200%">
		<span>人寿保险</span>
	</p>
	<p
		style="width: 100%; margin: 0 10% 0 10%; font-size: 17px; line-height: 200%">
		<span>（未成交：</span><span>12）</span>
	</p>
</div>
<div
	style="display: inline-block;width: 15%; height:18%; margin: 0 0 0 10%; background-image: url('${staticPath }/static/style/images/u135.png');background-repeat:no-repeat">
	<p
		style="width: 200%; margin: 0 10% 0 10%; font-size: 17px; line-height: 200%">
		<span>贷款业务</span>
	</p>
	<p
		style="width: 100%; margin: 0 10% 0 10%; font-size: 17px; line-height: 200%">
		<span>（未成交：</span><span>12）</span>
	</p>
</div>
<div
	style="display: inline-block;width: 15%; height:18%; margin: 0 0 0 0; background-image: url('${staticPath }/static/style/images/u137.png');background-repeat:no-repeat">
	<p
		style="width: 200%; margin: 0 10% 0 10%; font-size: 17px; line-height: 200%">
		<span>理财业务</span>
	</p>
	<p
		style="width: 100%; margin: 0 10% 0 10%; font-size: 17px; line-height: 200%">
		<span>（未成交：</span><span>12）</span>
	</p>
</div>
<div
	style="display: inline-block;width: 15%; height:18%; margin: 0 0 0 0; background-image: url('${staticPath }/static/style/images/u139.png');background-repeat:no-repeat">
	<p
		style="width: 200%; margin: 0 10% 0 10%; font-size: 17px; line-height: 200%">
		<span>二手车业务</span>
	</p>
	<p
		style="width: 100%; margin: 0 10% 0 10%; font-size: 17px; line-height: 200%">
		<span>（未成交：</span><span>12）</span>
	</p>
</div>
<div
	style="display: inline-block;width: 15%; height:18%; margin: 0 0 0 0; background-image: url('${staticPath }/static/style/images/u141.png');background-repeat:no-repeat">
	<p
		style="width: 200%; margin: 0 10% 0 10%; font-size: 17px; line-height: 200%">
		<span>车务代办</span>
	</p>
	<p
		style="width: 100%; margin: 0 10% 0 10%; font-size: 17px; line-height: 200%">
		<span>（未成交：</span><span>12）</span>
	</p>
</div>
<div
	style="display: inline-block;width: 15%; height:18%; margin: 0 10% 0 0; background-image: url('${staticPath }/static/style/images/u143.png');background-repeat:no-repeat">
	<p
		style="width: 200%; margin: 0 10% 0 10%; font-size: 17px; line-height: 200%">
		<span>汽修美容</span>
	</p>
	<p
		style="width: 100%; margin: 0 10% 0 10%; font-size: 17px; line-height: 200%">
		<span>（未成交：</span><span>12）</span>
	</p>
</div>
<div style="top: 40%; width: 100%; position: absolute;">
	<p>
		<span style="font-family: 'Applied Font Bold', 'Applied Font';">待</span><span
			style="font-family: 'Applied Font Bold', 'Applied Font';">办事项</span>
	</p>
	<div data-options="region:'center',border:false">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
</div>
