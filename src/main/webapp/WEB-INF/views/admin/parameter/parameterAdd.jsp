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
        	<input type="hidden" value="01" name="type">
            <table class="grid">
                <tr>
					<td align="right">渠道名称：</td>
					<td align="left" colspan="3"><input style="width: 200px" name="name"  ></td>
				</tr>
				<tr>
					<td align="right">电话：</td>
					<td align="left" colspan="3"><input style="width: 200px" name="description"  ></td>
				</tr> 
            </table>
        </form>
    </div>
</div>