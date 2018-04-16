<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#tProductManageDKAddForm').form({
            url : '${path}/mgr/tProductManage/add',
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
                    //之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    var form = $('#tProductManageDKAddForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="tProductManageDKAddForm" method="post">
            <table class="grid">
                <tr>
                    <td>产品名称</td>
                    <td colspan="2">	
                    	<input name="productName" type="text" style="width: 400px" placeholder="请输入名称" class="easyui-validatebox span2" data-options="required:true" value="">
                    	<input name="productType" value="ROOTDK" type="hidden">
                    </td>
                </tr> 
                <tr>
                    <td>平台佣金</td>
                    <td colspan="2"><input name="commission" type="text" class="easyui-validatebox span2" value=""></td>
                </tr>
                <tr>
                    <td>基本要求</td>
                    <td>婚姻状况</td>
                    <td>
                    	<select name="dMarryFlag" class="easyui-combobox" style="width: 200px">  
						    <option value="0">未婚</option>  
						    <option value="1">已婚</option>  
						    <option value="2">离异</option>  
						</select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>有无房产</td>
                    <td>
                    	<select name="dHouseFlag" class="easyui-combobox" style="width: 200px">  
						    <option value="0">无</option>  
						    <option value="1">按揭</option>  
						    <option value="2">全款</option>  
						</select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>有无社保</td>
                    <td>
                    	<select name="dSocialFlag" class="easyui-combobox" style="width: 200px">  
						    <option value="0">无</option>  
						    <option value="1">有</option>  
						</select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>每月收入</td>
                    <td>
                    	<select name="dIncome" class="easyui-combobox" style="width: 200px">  
						    <option value="1">1000-3000</option>  
						    <option value="2">3000-6000</option>  
						    <option value="3">6000-10000</option>  
						    <option value="4">10000-30000</option>
						    <option value="5">3万以上</option>
						</select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>有无车辆</td>
                    <td>
                    	<select name="dCarFlag" class="easyui-combobox" style="width: 200px">  
						   <option value="0">无</option>  
						    <option value="1">按揭</option>  
						    <option value="2">全款</option>  
						</select>
                    </td>
                </tr>
                 <tr>
                    <td>产品介绍</td>
                    <td colspan="2"><textarea name="productDescript" style="width: 390px; height: 100px;"></textarea></td>
                </tr> 
            </table>
        </form>
    </div>
</div>