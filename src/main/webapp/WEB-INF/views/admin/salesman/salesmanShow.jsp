<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type='text/javascript' src='${path }/static/jquery.citys.js'></script>
<script type="text/javascript">
    $(function() {
        $('#city').citys({code:'${salesMan.country}'});
    });
</script>  
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="salesmanEditForm" method="post">
            <table class="grid" >
                <tr>
                    <td>姓名：</td>
                    <td>${salesMan.userName}</td>
                </tr>
                <tr>
                    <td>证件类型：</td>
                    <td>${salesMan.cardType}</td>
                </tr>
                <tr>
                    <td>证件号码：</td>
                    <td colspan="3">${salesMan.cardId}</td>
                </tr>
                <tr>
                    <td>电话：</td>
                    <td>${salesMan.telephone}</td>
                    <td>手机：</td>
                    <td>${salesMan.mobilePhone}</td>
                </tr>
                <tr>
                    <td>Mail：</td>
                    <td>${salesMan.mail}</td>
                    <td>QQ：</td>
                    <td>${salesMan.qq}</td>
                </tr>
                <tr>
                    <td>微信号：</td>
                    <td>${salesMan.wechatNum}</td>
                </tr>
                <tr>
                    <td>地址：</td>
                    <td colspan="3">
                    	<div id="city" class="citys">
		                    <select id="province" name="province" disabled="disabled"></select>
		                    <select id="city" name="city" disabled="disabled"></select>
		                    <select id="area" name="country" disabled="disabled"></select>
						</div>
                    	<input style="width: 390px" name="userAddr" type="text" class="easyui-validatebox span2" value="${salesMan.userAddr}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <td>保险公司：</td>
                    <td>${salesMan.insCompany}></td>
                </tr>
                <tr>
                    <td>开户行：</td>
                    <td>${salesMan.bankName}</td>
                    <td>开户名称：</td>
                    <td>${salesMan.bankHost}</td>
                </tr>
                <tr>
                    <td>银行卡号：</td>
                    <td colspan="3">${salesMan.bankCard}</td>
                </tr>
                
                <tr>
                    <td>状态</td>
                    <td >
                        <select name="status" class="easyui-combobox" style="width:200px;" data-options="editable:false,valueField:'id',textField:'text',data:[{id:'01',text:'正常'},{id:'02',text:'停用'}],value:'${salesMan.status}'" readonly="readonly"></select>
                    </td>
                </tr>
                <tr>
                    <td>业务员备注</td>
                    <td colspan="3"><textarea name="remark" style="width: 390px; height: 100px;" readonly="readonly">${salesMan.remark}</textarea></td>
                </tr>
            </table>
        </form>
    </div>
</div>