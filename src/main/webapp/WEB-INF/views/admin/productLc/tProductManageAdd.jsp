<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#tProductManageLCAddForm').form({
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
                    var form = $('#tProductManageLCAddForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="tProductManageLCAddForm" method="post">
            <table class="grid">
                <tr>
                    <td>产品名称</td>
                    <td colspan="3">	
                    	<input name="productName" type="text" style="width: 400px" placeholder="请输入名称" class="easyui-validatebox span2" data-options="required:true" value="">
                    	<input name="productType" value="ROOTLC" type="hidden">
                    </td>
                </tr> 
                <tr>
                    <td>目标年化收益</td>
                    <td><input name="lGoalIncome" type="text" class="easyui-validatebox span2" value="">%</td>
                    <td>投资期限（天）</td>
                    <td><input name="lInvestDays" type="text" class="easyui-validatebox span2" value=""></td>
                </tr>
                <tr>
                    <td>起购金额（元）</td>
                    <td><input name="lMixBuyMoney" type="text" class="easyui-validatebox span2" value=""></td>
                    <td>风险等级</td>
                    <td>
						<select name="lRiskGrade" class="easyui-combobox" style="width: 200px">  
						    <option value="1">1级</option>  
						    <option value="2">2级</option>  
						    <option value="3">3级</option> 
						    <option value="4">4级</option>  
						    <option value="5">5级</option>   
						</select>
					</td>
                </tr>
                <tr>
                    <td>平台佣金</td>
                    <td><input name="commission" type="text" class="easyui-validatebox span2" value=""></td>
                </tr>
                 <tr>
                    <td>产品介绍</td>
                    <td colspan="3"><textarea name="productDescript" style="width: 390px; height: 100px;"></textarea></td>
                </tr> 
            </table>
        </form>
    </div>
</div>