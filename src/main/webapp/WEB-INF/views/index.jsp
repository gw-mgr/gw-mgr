<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<%@ page import="com.gewei.commons.utils.PropertUtil"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统主页</title>
<script type="text/javascript">
	var index_tabs;
	var indexTabsMenu;
	var indexMenuZTree;
	$(function() {
		$('#index_layout').layout({
			fit : true
		});

		index_tabs = $('#index_tabs').tabs(
				{
					fit : true,
					border : false,
					onContextMenu : function(e, title) {
						e.preventDefault();
						indexTabsMenu.menu('show', {
							left : e.pageX,
							top : e.pageY
						}).data('tabTitle', title);
					},
					tools : [
							{
								iconCls : 'fi-home',
								handler : function() {
									index_tabs.tabs('select', 0);
								}
							},
							{
								iconCls : 'fi-loop',
								handler : function() {
									refreshTab();
								}
							},
							{
								iconCls : 'fi-x',
								handler : function() {
									var index = index_tabs.tabs('getTabIndex',
											index_tabs.tabs('getSelected'));
									var tab = index_tabs.tabs('getTab', index);
									if (tab.panel('options').closable) {
										index_tabs.tabs('close', index);
									}
								}
							} ]
				});
		// 选项卡菜单
		indexTabsMenu = $('#tabsMenu').menu(
				{
					onClick : function(item) {
						var curTabTitle = $(this).data('tabTitle');
						var type = $(item.target).attr('type');
						if (type === 'refresh') {
							refreshTab();
							return;
						}
						if (type === 'close') {
							var t = index_tabs.tabs('getTab', curTabTitle);
							if (t.panel('options').closable) {
								index_tabs.tabs('close', curTabTitle);
							}
							return;
						}
						var allTabs = index_tabs.tabs('tabs');
						var closeTabsTitle = [];
						$.each(allTabs, function() {
							var opt = $(this).panel('options');
							if (opt.closable && opt.title != curTabTitle
									&& type === 'closeOther') {
								closeTabsTitle.push(opt.title);
							} else if (opt.closable && type === 'closeAll') {
								closeTabsTitle.push(opt.title);
							}
						});
						for (var i = 0; i < closeTabsTitle.length; i++) {
							index_tabs.tabs('close', closeTabsTitle[i]);
						}
					}
				});

		indexMenuZTree = $.fn.zTree
				.init(
						$("#layout_west_tree"),
						{
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
								url : "${path}/mgr/resource/tree"
							},
							callback : {
								onClick : function(event, treeId, node) {
									var opts = {
										title : node.name,
										border : false,
										closable : true,
										fit : true,
										iconCls : node.iconCls
									};
									var url = node.attributes;
									if (url && url.indexOf("http") == -1) {
										url = '${path}/mgr' + url;
									}
									if (node.openMode == 'iframe') {
										opts.content = '<iframe src="'
												+ url
												+ '" frameborder="0" style="border:0;width:100%;height:99.5%;"></iframe>';
										addTab(opts);
									} else if (url) {
										opts.href = url;
										addTab(opts);
									}
								}
							}
						});
		// ajax请求数据：三大模块数量
		$.ajax({
			type : "get",
			dataType : "json",
			url : "${path}/mgr/home/homePage/ajaxData",
			success : function(result) {
				$("#examineMerchant")
						.html("( " + result.examineMerchant + " )");
				$("#examineCash").html("( " + result.examineCash + " )");
				$("#examineSettle").html("( " + result.examineSettle + " )");
			}
		});
		// ajax请求数据：消息数量
		$.ajax({
			type : "get",
			dataType : "json",
			url : "${path}/mgr/zmessage/getCount",
			success : function(result) {
				$("#messageCount").html("( " + result.totalCount + " )");
			}
		});
	});

	function refreshTab() {
		var index = index_tabs.tabs('getTabIndex', index_tabs
				.tabs('getSelected'));
		var tab = index_tabs.tabs('getTab', index);
		var options = tab.panel('options');
		if (options.content) {
			index_tabs.tabs('update', {
				tab : tab,
				options : {
					content : options.content
				}
			});
		} else {
			tab.panel('refresh', options.href);
		}
	}

	function logout() {
		$.messager.confirm('提示', '确定要退出?', function(r) {
			if (r) {
				progressLoad();
				$.post('${path}/mgr/logout', function(result) {
					if (result.success) {
						progressClose();
						window.location.href = '${path}/mgr';
					}
				}, 'json');
			}
		});
	}

	function editUserPwd() {
		parent.$.modalDialog({
			title : '修改密码',
			width : 300,
			height : 250,
			href : '${path}/mgr/user/editPwdPage',
			buttons : [ {
				text : '确定',
				handler : function() {
					var f = parent.$.modalDialog.handler
							.find('#editUserPwdForm');
					f.submit();
				}
			} ]
		});
	}

	function addTab(opts) {
		var t = $('#index_tabs');
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
		} else {
			t.tabs('add', opts);
		}
	}

	function chooseIndexTab(tabContent) {
		var href = "";
		var tabName = "";
		if (tabContent == 'merchantManage') {
			tabName = "服务商管理";
			href = "${path}/mgr/tMerchantManage/manager?status=01";
		} else if (tabContent == 'cashManage') {
			tabName = "提现审核";
			href = "${path}/mgr/ExamineManage/getCash";
		} else if (tabContent == 'settleManage') {
			tabName = "结算审核";
			href = "${path}/mgr/ExamineManage/getSettle";
		} else if (tabContent == 'messageManage') {
			tabName = "消息管理";
			href = "${path}/mgr/zmessage/manager";
		}else if (tabContent == 'examineOrderCx') {
			tabName = "财险订单管理";
			href = "${path}/mgr/orderCx/manager";
		}
		else if (tabContent == 'examineOrderSx') {
			tabName = "寿险订单管理";
			href = "${path}/mgr/orderSx/manager";
		}
		else if (tabContent == 'examineOrderDk') {
			tabName = "贷款订单管理";
			href = "${path}/mgr/orderInfo/manager?type=Dk";
		}
		else if (tabContent == 'examineOrderLc') {
			tabName = "理财订单管理";
			href = "${path}/mgr/orderInfo/manager?type=Lc";
		}
		else if (tabContent == 'examineOrderCm') {
			tabName = "汽车美容订单管理";
			href = "${path}/mgr/orderInfo/manager?type=Cm";
		}
		else if (tabContent == 'examineOrderCd') {
			tabName = "车务订单管理";
			href = "${path}/mgr/orderInfo/manager?type=Cd";
		}
		else if (tabContent == 'examineOrderCp') {
			tabName = "二手车订单管理";
			href = "${path}/mgr/orderInfo/manager?type=Cp";
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
</script>
</head>
<body>
	<div id="index_layout">
		<!-- 首页顶部 -->
		<div data-options="region:'north',border:false" style="height: 80px; border: 0px solid red">
			<!-- 不同系统显示不同模块 -->
			<shiro:hasPermission name="adminIndex">
				<div style="float: left; height: 70px; width: 300px; border: 0px solid; padding-left: 50px; padding-top: 8px; font-size: 40px">超级管理员</div>
			</shiro:hasPermission>
			<shiro:hasPermission name="zhognshuIndex">
				<div style="float: left; height: 70px; width: 300px; border: 0px solid; padding-left: 50px; padding-top: 8px; font-size: 40px">中枢系统</div>
			</shiro:hasPermission>
			<shiro:hasPermission name="fuwushangIndex">
				<div style="float: left; height: 70px; width: 300px; border: 0px solid; padding-left: 50px; padding-top: 8px; font-size: 40px">服务商管理系统</div>
			</shiro:hasPermission>
			<div style="width: 320px; float: right; border: 0px solid red; height: 75px">
				<div style="float: right; padding-top: 10px; padding-right: 20px; height: 35px; width: 320px; font-size: 15px; font-weight: 200; border: 0px solid blue">
					<%
						out.print(com.gewei.commons.utils.StringUtils.formateString("{0}&nbsp;&nbsp;{1}", PropertUtil.getDateYmd(), PropertUtil.getWeek()));
					%>
					&nbsp;&nbsp;&nbsp;&nbsp;欢迎：
					<font color="red">
						<b> <shiro:principal></shiro:principal></b> &nbsp;&nbsp;
					</font>
				</div>
				<div style="float: right; padding-right: 25px; border: 0px solid green">
					<shiro:hasPermission name="zhognshuIndex">
						<a href="javascript:void(0)" onclick="chooseIndexTab('messageManage')" class="easyui-linkbutton">
							消息提醒
							<font id="messageCount" color="red"></font>
						</a>
					</shiro:hasPermission>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<shiro:hasPermission name="/user/editPwdPage">
						<a href="javascript:void(0)" onclick="editUserPwd()" class="easyui-linkbutton">修改密码</a>
					</shiro:hasPermission>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0)" onclick="logout()" class="easyui-linkbutton">安全退出</a>
				</div>
			</div>
		</div>

		<div data-options="region:'west',split:true" title="导航" style="width: 160px; overflow: hidden; overflow-y: auto; padding: 0px">
			<div class="well well-small" style="padding: 5px 5px 5px 5px;">
				<ul id="layout_west_tree" class="ztree"></ul>
			</div>
		</div>
		<div data-options="region:'center'" style="overflow: hidden;">
			<div id="index_tabs" style="overflow: hidden;">
				<div title="首页" data-options="iconCls:'fi-home',border:false" style="overflow: hidden;">
					<!-- 不同系统显示不同模块 -->
					<shiro:hasPermission name="adminIndex">
						<%@ include file="adminIndex.jsp"%>
					</shiro:hasPermission>
					<shiro:hasPermission name="zhognshuIndex">
						<%@ include file="zhognshuIndex.jsp"%>
					</shiro:hasPermission>
					<shiro:hasPermission name="fuwushangIndex">
						<%@ include file="fuwushangIndex.jsp"%>
					</shiro:hasPermission>
				</div>
			</div>
		</div>
		<div data-options="region:'south',border:false" style="height: 30px; line-height: 30px; overflow: hidden; text-align: center; background-color: #eee">
			Copyright © 2018 power by
			<a href="https://www.baidu.com/" target="_blank">格威保险</a>
		</div>
	</div>
	<div id="tabsMenu">
		<div data-options="iconCls:'fi-loop'" type="refresh" style="font-size: 12px;">刷新</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'fi-x'" type="close" style="font-size: 12px;">关闭</div>
		<div data-options="iconCls:''" type="closeOther">关闭其他</div>
		<div data-options="iconCls:''" type="closeAll">关闭所有</div>
	</div>
</body>
</html>