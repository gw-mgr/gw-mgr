<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type='text/javascript' src='${path }/static/jquery.citys.js'></script>  
<script type="text/javascript">
    $(function() {
        $('#salesmanAddForm').form({
            url : '${path }/mgr/salesman/add',
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
                    var form = $('#salesmanAddForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
        $('#city').citys();
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="salesmanAddForm" method="post">
            <table class="grid">
                <tr>
                    <td>姓名：</td>
                    <td><input name="userName" type="text" placeholder="请输入姓名" class="easyui-validatebox span2" data-options="required:true" value=""></td>
                </tr>
                <tr>
                    <td>证件类型：</td>
                    <td>
                     <select name="certType" class="easyui-combobox" style="width: 200px">  
						    <option value="SFZ">身份证</option>  
						    <option value="HZ">护照</option>  
						    <option value="JGZ">军官证</option>  
						    <option value="QT">其它</option>  
						</select>
                    </td>
                </tr>
                <tr>
                    <td>证件号码：</td>
                    <td colspan="3"><input style="width: 390px" name="certNo" type="text" placeholder="请输入证件号码" class="easyui-validatebox span2" data-options="required:true" value=""></td>
                </tr>
                <tr>
                    <td>电话：</td>
                    <td><input name="telphone" type="text" class="easyui-validatebox span2" value=""></td>
                    <td>手机：</td>
                    <td><input name="mtelphone" type="text" class="easyui-validatebox span2" value=""></td>
                </tr>
                <tr>
                    <td>Mail：</td>
                    <td><input name="mail" type="text" class="easyui-validatebox span2" value=""></td>
                    <td>QQ：</td>
                    <td><input name="qq" type="text" class="easyui-validatebox span2" value=""></td>
                </tr>
                <tr>
                    <td>微信号：</td>
                    <td  colspan="3"><input name="wechat" type="text" class="easyui-validatebox span2" value=""></td>
                </tr>
                <tr>
                    <td>地址：</td>
                    <td  colspan="3">
						<div id="city" class="citys">
		                    <select id="province" name="province"></select>
		                    <select id="city" name="city"></select>
		                    <select id="area" name="district"></select>
			            </div>
                    	<input style="width: 390px" name="linkaddr" type="text" class="easyui-validatebox span2" value="">
                    </td>
                </tr>
                <tr>
                    <td>保险公司：</td>
                    <td  colspan="3">
                    	<input name="insuranceCompanyId" class="easyui-combobox" data-options="width:180, valueField: 'id', textField: 'insurerName', url: '${path}/mgr/tInsurer/list'"/>
                    </td>
                </tr>
                <tr>
                    <td>开户行：</td>
                    <td><input name="bankName" type="text" class="easyui-validatebox span2" value=""></td>
                    <td>开户名称：</td>
                    <td><input name="bankUserName" type="text" class="easyui-validatebox span2" value=""></td>
                </tr>
                <tr>
                    <td>银行卡号：</td>
                    <td  colspan="3"><input style="width: 390px" name="bankNo" type="text" class="easyui-validatebox span2" value=""></td>
                </tr>
                
                <tr>
                    <td>状态</td>
                    <td  colspan="3">
                        <select name="status" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0">正常</option>
                            <option value="1">停用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>业务员备注</td>
                    <td colspan="3"><textarea name="comment" style="width: 390px; height: 100px;"></textarea></td>
                </tr>
            </table>
        </form>
    </div>
</div>