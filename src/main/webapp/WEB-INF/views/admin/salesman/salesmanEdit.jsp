<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type='text/javascript' src='${path }/static/jquery.citys.js'></script>  
<script type="text/javascript">
    $(function() {
        $('#salesmanEditForm').form({
            url : '${path }/mgr/salesman/edit',
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
                    var form = $('#salesmanEditForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
        $("#roleEditStatus").val('${salesMan.status}');
        $('#city').citys({code:'${salesMan.country}'});
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="salesmanEditForm" method="post">
            <table class="grid">
                <tr>
                    <td>姓名：</td>
                    <td><input name="userId" type="hidden"  value="${salesMan.userId}">
                    <input name="userName" type="text" placeholder="请输入姓名" class="easyui-validatebox span2" data-options="required:true" value="${salesMan.userName}"></td>
                </tr>
                <tr>
                    <td>证件类型：</td>
                    <td>
						<select name="cardType" class="easyui-combobox" style="width:200px;" data-options="editable:false,valueField:'id',textField:'text',data:[{id:'身份证',text:'身份证'},{id:'护照',text:'护照'},{id:'军官证',text:'军官证'},{id:'其他',text:'其他'}],value:'${salesMan.cardType}'"></select>
                    </td>
                </tr>
                <tr>
                    <td>证件号码：</td>
                    <td colspan="3"><input style="width: 390px" name="cardId" type="text" placeholder="请输入证件号码" class="easyui-validatebox span2" data-options="required:true" value="${salesMan.cardId}"></td>
                </tr>
                <tr>
                    <td>电话：</td>
                    <td><input name="telephone" type="text" class="easyui-validatebox span2" value="${salesMan.telephone}"></td>
                    <td>手机：</td>
                    <td><input name="mobilePhone" type="text" class="easyui-validatebox span2" value="${salesMan.mobilePhone}"></td>
                </tr>
                <tr>
                    <td>Mail：</td>
                    <td><input name="mail" type="text" class="easyui-validatebox span2" value="${salesMan.mail}"></td>
                    <td>QQ：</td>
                    <td><input name="qq" type="text" class="easyui-validatebox span2" value="${salesMan.qq}"></td>
                </tr>
                <tr>
                    <td>微信号：</td>
                    <td><input name="wechatNum" type="text" class="easyui-validatebox span2" value="${salesMan.wechatNum}"></td>
                </tr>
                <tr>
                    <td>地址：</td>
                    <td colspan="3">
						<div id="city" class="citys">
		                    <select id="province" name="province"></select>
		                    <select id="city" name="city"></select>
		                    <select id="area" name="country"></select>
						</div>
						<div style="height: 10px"></div>
                    	<input style="width: 390px" name="userAddr" type="text" class="easyui-validatebox span2" value="${salesMan.userAddr}">
                    </td>
                </tr>
                <tr>
                    <td>保险公司：</td>
                    <td>
                    	<input name="insCompany" class="easyui-combobox" data-options="width:180, valueField: 'id', textField: 'insurerName', url: '${path}/mgr/tInsurer/list',value:'${salesMan.insCompany}'"/>
                    </td>
                </tr>
                <tr>
                    <td>开户行：</td>
                    <td><input name="bankName" type="text" class="easyui-validatebox span2" value="${salesMan.bankName}"></td>
                    <td>开户名称：</td>
                    <td><input name="bankHost" type="text" class="easyui-validatebox span2" value="${salesMan.bankHost}"></td>
                </tr>
                <tr>
                    <td>银行卡号：</td>
                    <td colspan="3"><input style="width: 390px" name="bankCard" type="text" class="easyui-validatebox span2" value="${salesMan.bankCard}"></td>
                </tr>
                
                <tr>
                    <td>状态</td>
                    <td >
                        <select name="status" class="easyui-combobox" style="width:200px;" data-options="editable:false,valueField:'id',textField:'text',data:[{id:'01',text:'正常'},{id:'02',text:'停用'}],value:'${salesMan.status}'"></select>
                    </td>
                </tr>
                <tr>
                    <td>业务员备注</td>
                    <td colspan="3"><textarea name="remark" style="width: 390px; height: 100px;">${salesMan.remark}</textarea></td>
                </tr>
            </table>
        </form>
    </div>
</div>