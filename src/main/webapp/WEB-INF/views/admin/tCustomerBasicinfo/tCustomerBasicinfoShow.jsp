<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $("#editStatus").val('${tCustomerBasicinfo.status}'); 
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
         <table class="grid">
             <tr>
                 <td  align="right">ID：</td>
                 <td  align="left">
                 	${tCustomerBasicinfo.userId}
                 </td>
             </tr>
             <tr>
                 <td  align="right">姓名：</td>
                 <td  align="left">
                 	${tCustomerBasicinfo.userName}
                 </td>
             </tr>
             <tr>
                 <td  align="right">证件类型：</td>
                 <td  align="left">
                 	${tCustomerBasicinfo.cardType}
                 </td>
             </tr>
             <tr>
                 <td  align="right">证件号：</td>
                 <td  align="left">
                 	${tCustomerBasicinfo.cardId}
                 </td>
             </tr>
             <tr>
                 <td  align="right">车牌号：</td>
                 <td  align="left">
                 	${tCustomerBasicinfo.carNum}
                 </td>
             </tr>
             <tr>
                 <td  align="right">车架号：</td>
                 <td  align="left">
                 	${tCustomerBasicinfo.carFrameNum}
                 </td>
             </tr>
             <tr>
                 <td  align="right">发动机号：</td>
                 <td  align="left">
                 	${tCustomerBasicinfo.engineNum}
                 </td>
             </tr>
             <tr>
                 <td  align="right">厂牌类型：</td>
                 <td  align="left">
                 	${tCustomerBasicinfo.changType}
                 </td>
             </tr>
             <tr>
                    <td  align="right">初登日期：</td>
                    <td  align="left">${tCustomerBasicinfo.registerTime }</td>
                    <td  align="right">年审到期日：</td>
                    <td  align="left">
                    	${tCustomerBasicinfo.examineTime }
                    </td>
                </tr>
                <tr>
                    <td  align="right">商业险起保日：</td>
                    <td  align="left">${tCustomerBasicinfo.trafficInsStartTime }</td>
                    <td  align="right">交强险起保日：</td>
                    <td  align="left">
                    	${tCustomerBasicinfo.businessInsStartTime }
                    </td>
                </tr>
                <tr>
                 <td  align="right">车辆类型：</td>
                 <td  align="left">
                 	${tCustomerBasicinfo.carType}
                 </td>
             </tr>
         </table>
    </div>
</div>