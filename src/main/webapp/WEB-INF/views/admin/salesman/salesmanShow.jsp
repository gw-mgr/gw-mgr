<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type='text/javascript' src='${path }/static/jquery.citys.js'></script>
<script type="text/javascript">
    $(function() {
        $('#city').citys({code:'${salesMan.district}'});
    });
</script>  
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="salesmanEditForm" method="post">
            <table class="grid" >
                <tr>
                    <td>姓名：</td>
                    <td><input name="id" type="hidden"  value="${salesMan.id}">
                    <input name="userName" type="text" readonly="readonly" placeholder="请输入姓名" class="easyui-validatebox span2" data-options="required:true" value="${salesMan.userName}"></td>
                </tr>
                <tr>
                    <td>证件类型：</td>
                    <td>
						<select name="certType" class="easyui-combobox" style="width:200px;" data-options="editable:false,valueField:'id',textField:'text',data:[{id:'SFZ',text:'身份证'},{id:'HZ',text:'护照'},{id:'JGZ',text:'军官证'},{id:'QT',text:'其他'}],value:'${salesMan.certType}'" readonly="readonly"></select>
                    </td>
                </tr>
                <tr>
                    <td>证件号码：</td>
                    <td colspan="3"><input style="width: 390px" name="certNo" type="text" placeholder="请输入证件号码" class="easyui-validatebox span2" data-options="required:true" value="${salesMan.certNo}" readonly="readonly"></td>
                </tr>
                <tr>
                    <td>电话：</td>
                    <td><input name="telphone" type="text" class="easyui-validatebox span2" value="${salesMan.telphone}" readonly="readonly"></td>
                    <td>手机：</td>
                    <td><input name="mtelphone" type="text" class="easyui-validatebox span2" value="${salesMan.mtelphone}" readonly="readonly"></td>
                </tr>
                <tr>
                    <td>Mail：</td>
                    <td><input name="mail" type="text" class="easyui-validatebox span2" value="${salesMan.mail}" readonly="readonly"></td>
                    <td>QQ：</td>
                    <td><input name="qq" type="text" class="easyui-validatebox span2" value="${salesMan.qq}" readonly="readonly"></td>
                </tr>
                <tr>
                    <td>微信号：</td>
                    <td><input name="wechat" type="text" class="easyui-validatebox span2" value="${salesMan.wechat}" readonly="readonly"></td>
                </tr>
                <tr>
                    <td>地址：</td>
                    <td colspan="3">
                    	<div id="city" class="citys">
		                    <select id="province" name="province" disabled="disabled"></select>
		                    <select id="city" name="city" disabled="disabled"></select>
		                    <select id="area" name="district" disabled="disabled"></select>
						</div>
                    	<input style="width: 390px" name="linkaddr" type="text" class="easyui-validatebox span2" value="${salesMan.linkaddr}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <td>保险公司：</td>
                    <td><input name="insuranceCompany" type="text" class="easyui-validatebox span2" value="${salesMan.insuranceCompany}" readonly="readonly"></td>
                </tr>
                <tr>
                    <td>开户行：</td>
                    <td><input name="bankName" type="text" class="easyui-validatebox span2" value="${salesMan.bankName}" readonly="readonly"></td>
                    <td>开户名称：</td>
                    <td><input name="bankUserName" type="text" class="easyui-validatebox span2" value="${salesMan.bankUserName}" readonly="readonly"></td>
                </tr>
                <tr>
                    <td>银行卡号：</td>
                    <td colspan="3"><input style="width: 390px" name="bankNo" type="text" class="easyui-validatebox span2" value="${salesMan.bankNo}" readonly="readonly"></td>
                </tr>
                
                <tr>
                    <td>状态</td>
                    <td >
                        <select name="status" class="easyui-combobox" style="width:200px;" data-options="editable:false,valueField:'id',textField:'text',data:[{id:'0',text:'正常'},{id:'1',text:'停用'}],value:'${salesMan.status}'" readonly="readonly"></select>
                    </td>
                </tr>
                <tr>
                    <td>业务员备注</td>
                    <td colspan="3"><textarea name="comment" style="width: 390px; height: 100px;" readonly="readonly">${salesMan.comment}</textarea></td>
                </tr>
            </table>
        </form>
    </div>
</div>