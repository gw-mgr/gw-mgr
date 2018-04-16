<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#parameterAddForm').form({
            url : '${path}/mgr/parameter/add',
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
                    var form = $('#parameterAddForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="parameterAddForm" method="post">
            <table class="grid">
                <tr>
                    <td align="right">商家名称</td>
                    <td align="left"><input name="name" type="text" placeholder="请输入名称" class="easyui-validatebox span2" data-options="required:true" value=""></td>
                </tr> 
            </table>
            <table class="grid">
									<tr>
										<td align="center">类型</td>
										<td align="center">险种名称</td>
										<td align="center">险种代码</td>
										<td align="center">保险期间</td>
										<td align="center">交费期间</td>
										<td align="center">保险金额<br />（元）</td>
										<td align="center">保险费<br />（元）</td>
										<td align="center">追加保费<br />（元）</td>
										<td align="center">保险费<br />小计<br />（元）</td>
									</tr>
									<tr>
										<td align="center">主险<input type="hidden" name="tbsxBxsx[0].order" value="0"></td>
										<td align="center"><textarea name="tbsxBxsx[0].name" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsxBxsx[0].code" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsxBxsx[0].bxDate" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsxBxsx[0].jfDate" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsxBxsx[0].bxMoney" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsxBxsx[0].bxFee" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsxBxsx[0].zjbf" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsxBxsx[0].bfxj" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
									</tr>
									<tr>
										<td align="center">附险1<input type="hidden" name="tbsxBxsx[1].order" value="1"></td>
										<td align="center"><textarea name="tbsxBxsx[1].name" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsxBxsx[1].code" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsxBxsx[1].bxDate" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsxBxsx[1].jfDate" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsxBxsx[1].bxMoney" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsxBxsx[1].bxFee" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsxBxsx[1].zjbf" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsxBxsx[1].bfxj" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
									</tr>
								</table>
        </form>
    </div>
</div>