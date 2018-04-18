<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/head.jsp"%>
<script type='text/javascript' src='${path }/static/jquery.citys.js'></script>  
<script type="text/javascript">
	var syrNum = 1;
    $(function() {
        $('#orderSxAddForm').form({
            url : '${path}/mgr/orderSx/add',
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
                    var form = $('#orderSxAddForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
        $('#city1').citys();
        $('#city2').citys();
        $('#city3').citys();
    });
    function zxbfFun(){
    	$("#zxbfTool").show();//必须先显示，再弹出
	    $("#zxbfTool").dialog({
	        title: "主险计算",
	        width: 400,
	        height: 200
	    });
    }
    function getJzx(){
       $("#jzx").numberbox('setValue',$("#jzxbfStr").val());
  	   $("#zxs").numberbox('setValue',$("#zxsjjeStr").val());
 	   $("#zxbfTool").css("display", "none"); 
 	   $("#zxbfTool").dialog('close');
     }
    function cancelJzx(){
       $("#zxbfTool").css("display", "none"); 
  	   $("#zxbfTool").dialog('close');
     }
    function zxbf(){
    	  var zxbf = parseFloat($("#zxbf").val());
    	  var zxsjbl = parseFloat($("#zxsjbl").val()); 
    	  $("#jzxbfStr").val((zxbf/(1+zxsjbl)).toFixed(2));
    	  $("#zxsjjeStr").val(zxsjbl*(zxbf/(1+zxsjbl)).toFixed(2));
      }
    
    function jfx1Fun(){
    	$("#jfx1Tool").show();//必须先显示，再弹出
	    $("#jfx1Tool").dialog({
	        title: "净附险计算",
	        width: 400,
	        height: 200
	    });
    }
    function getJfx1(){
    	$("#jfx1").numberbox('setValue',$("#jfx1bfStr").val());
    	$("#fxs1").numberbox('setValue',$("#fx1sjjeStr").val());
 	   $("#jfx1Tool").css("display", "none"); 
 	   $("#jfx1Tool").dialog('close');
     }
    function cancelJfx1(){
       $("#jfx1Tool").css("display", "none"); 
  	   $("#jfx1Tool").dialog('close');
     }
    function fx1bf(){
  	  var fx1bf = parseFloat($("#fx1bf").val());
  	  var fx1sjbl = parseFloat($("#fx1sjbl").val()); 
  	  $("#jfx1bfStr").val((fx1bf/(1+fx1sjbl)).toFixed(2));
  	  $("#fx1sjjeStr").val(fx1sjbl*(fx1bf/(1+fx1sjbl)).toFixed(2));
    }
    
    function jfx2Fun(){
    	$("#jfx2Tool").show();//必须先显示，再弹出
	    $("#jfx2Tool").dialog({
	        title: "净附险计算",
	        width: 400,
	        height: 200
	    });
    }
    function getJfx2(){
    	$("#jfx2").numberbox('setValue',$("#jfx2bfStr").val());
    	$("#fxs2").numberbox('setValue',$("#fx2sjjeStr").val());
 	   $("#jfx2Tool").css("display", "none"); 
 	   $("#jfx2Tool").dialog('close');
     }
    function cancelJfx2(){
       $("#jfx2Tool").css("display", "none"); 
  	   $("#jfx2Tool").dialog('close');
     }
    function fx2bf(){
  	  var fx2bf = parseFloat($("#fx2bf").val());
  	  var fx2sjbl = parseFloat($("#fx2sjbl").val()); 
  	  $("#jfx2bfStr").val((fx2bf/(1+fx2sjbl)).toFixed(2));
  	  $("#fx2sjjeStr").val(fx2sjbl*(fx2bf/(1+fx2sjbl)).toFixed(2));
    }
    
    function saleManForOrderSxCheckFun (){
       	$('#saleManForOrderSxDataGrid').datagrid({
       		url : '${path}/mgr/salesman/dataGrid',
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : false,
    		selectOnCheck: true,
    		checkOnSelect: true,
            idField : 'id',
            sortName : 'id',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            frozenColumns : [ [ {
            	field:'id',
    			checkbox:true 
            }, {
                width : '60',
                title : '姓名',
                field : 'userName',
                sortable : true
            } , {
                width : '100',
                title : '手机号',
                field : 'mtelphone',
                sortable : true
            }, {
                width : '100',
                title : '微信号',
                field : 'wechat'
            } ] ],
            onLoadSuccess:function(data){
                $('.orderInfo-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.orderInfo-easyui-linkbutton-del').linkbutton({text:'删除'});
            }
        });
       	$("#saleManForOrderSx").show();//必须先显示，再弹出
        $("#saleManForOrderSx").dialog({
            title: "业务员选择",
            width: 600,
            height: 400
        });
       }
      
      function saleManForOrderSxSearchFun() {
           $('#saleManForOrderSxDataGrid').datagrid('load', $.serializeObject($('#saleManForOrderSxSearchForm')));
      }
      function getSaleManForOrderSx(id){
         var rows = $('#saleManForOrderSxDataGrid').datagrid('getSelections');
    	   if(rows.length==0){
    		   alert('请选择一个业务员！');
    	   }else{
    		   $('#salesManSx').val(rows[0].id);
    		   $('#salesManNameSx').val(rows[0].userName);
    	   }
    	   $('#saleManForOrderSxDataGrid').datagrid('clearSelections'); 
    	   $("#saleManForOrderSx").css("display", "none"); 
    	   $("#saleManForOrderSx").dialog('close');
      }
      function cancelSaleManForOrderSx(){
    	   $('#saleManForOrderSxDataGrid').datagrid('clearSelections'); 
    	   $("#saleManForOrderSx").css("display", "none"); 
    	   $("#saleManForOrderSx").dialog('close');
      }

	function bxsxAddNew() {
		
		var length = $("#bxsx tr").length - 1;
		var str = "tbsx_bxsx[" + length + "].";
		var table1 = $('#bxsx');
		var row = $("<tr></tr>");
		var td1 = $("<td  align='center'>附险" + length + "<input type='hidden' name='" + str + "order' value='"+length+"'></td>");
		var td2 = $("<td  align='center'><textarea style='width: 60px; border: 0px; outline:none;' name='" + str + "name'></textarea></td>");
		var td3 = $("<td  align='center'><textarea style='width: 60px; border: 0px; outline:none;' name='" + str + "code'></textarea></td>");
		var td4 = $("<td  align='center'><textarea style='width: 60px; border: 0px; outline:none;' name='" + str + "bxDate'></textarea></td>");
		var td5 = $("<td  align='center'><textarea style='width: 60px; border: 0px; outline:none;' name='" + str + "jfDate'></textarea></td>");
		var td6 = $("<td  align='center'><textarea style='width: 60px; border: 0px; outline:none;' name='" + str + "bxMoney'></textarea></td>");
		var td7 = $("<td  align='center'><textarea style='width: 60px; border: 0px; outline:none;' name='" + str + "bxFee'></textarea></td>");
		var td8 = $("<td  align='center'><textarea style='width: 60px; border: 0px; outline:none;' name='" + str + "zjbf'></textarea></td>");
		var td9 = $("<td  align='center'><textarea style='width: 60px; border: 0px; outline:none;' name='" + str + "bfxj'></textarea></td>");
		row.append(td1);
		row.append(td2);
		row.append(td3);
		row.append(td4);
		row.append(td5);
		row.append(td6);
		row.append(td7);
		row.append(td8);
		row.append(td9);
		table1.append(row);
	}
	function tzljAddNew() {
		var length = $("#tzlj tr").length / 3;
		var str = "tzlj[" + length + "].";
		var table = $('#tzlj');
		var row = $("<tr></tr>");
		var row1 = $("<tr><td align='right' width='220px'>投资账户名称：</td><td align='left'><input name='"+ str + "tzzhmc'></td></tr>");
		var row2 = $("<tr><td align='right' width='220px'>分配比例：</td><td align='left'><input name='"+ str + "fpbl'></td></tr>");
		var row3 = $("<tr><td align='right' width='220px'>追加保险费分配比例：</td><td align='left'><input name='"+ str + "zjbxfpbl'></td></tr>");
		table.append(row1);
		table.append(row2);
		table.append(row3);
	}
	function syrClone(){
		var div = $('#syr');
		var str="[" + syrNum + "]";
		var table = $("<table class='grid'></table>");
		var row1 = $("<tr><td align='right' width='120px'>受益顺序：</td><td align='left'><input style='width: 200px' name='syr"+ str + ".beneficiaryOrder' ></td><td align='right'>受益比例：</td><td align='left'><input style='width: 150px' name='syr" + str + ".beneficialRate' >%</td></tr>");
		var row2 = $("<tr><td align='right'>受益人姓名：</td><td align='left'><input style='width: 200px' name='syr"+ str + ".beneficialName' ></td><td align='right'>性别：</td><td align='left'><select name='syr"+ str + ".sex' class='easyui-combobox' style='width:150px;'><option value='男'>男</option><option value='女'>女</option></select></td></tr>");
		var row3 = $("<tr><td align='right'>出生日期：</td><td align='left'><input name='syr"+ str + ".birthDate'  type='text' class='easyui-datebox'  style='width:200px;'></td><td align='right'>与被保人关系：</td><td align='left'><select name='syr"+ str + ".relationship' class='easyui-combobox' style='width:150px;'><option value='配偶'>配偶</option><option value='子女'>子女</option><option value='父母'>父母</option><option value='本人'>本人</option></select></td></tr>");
		var row4 = $("<tr><td align='right'>证件类型：</td><td align='left'><select name='syr"+ str + ".certType' class='easyui-combobox' style='width:200px;'><option value='身份证'>身份证</option><option value='护照'>护照</option><option value='军人证'>军人证</option><option value='通行证'>通行证</option><option value='户口本'>户口本</option></select></td></tr>");
		var row5 = $("<tr><td align='right'>证件号码：</td><td align='left' colspan='3'><input style='width: 300px' name='syr"+ str + ".certNo' ></td></tr>");
		var row6 = $("<tr><td align='right'>证件有效期至：</td><td align='left'><input name='syr"+ str + ".validityDate'  type='text' class='easyui-datebox'  style='width:200px;'></td></tr>");
		var row7 = $(" <tr><td align='right'>住址：</td><td align='left'><select name='syr"+ str + ".residentialAddress' class='easyui-combobox' style='width:200px;'><option value='同投保人'>同投保人</option><option value='同被保险人'>同被保险人</option><option value='其他'>其他</option></select></td></tr>");
		table.append(row1);
		table.append(row2);
		table.append(row3);
		table.append(row4);
		table.append(row5);
		table.append(row6);
		table.append(row7);
		div.append(table);
		$("input[name='syr"+str+".birthDate']").datebox();
		$("input[name='syr"+str+".validityDate']").datebox();
	}
	
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" title="" style="overflow: auto;padding: 3px;">
        <form id="orderSxAddForm" method="post">
        <input type="hidden" id="orderId" name="orderId" value="${orderId }">
            <div>
				<div class="ordercx_details">
					<ul>
						<li class="forsxtab sxprepaids checked">订单信息</li>
						<li class="forsxtab sxprepaids2">投保人信息</li>
						<li class="forsxtab sxprepaids3">被保险人信息</li>
						<li class="forsxtab sxprepaids4">身故受益人信息</li>
						<li class="forsxtab sxprepaids5">投保事项</li>
						<li class="forsxtab sxprepaids6">告知事项及说明</li>
						<li class="forsxtab sxprepaids7">保险情况</li>
					</ul>
				</div>
			</div>
			<div>
				<div class="sxtablelist sxshowdomdiv sxshowsss">
					<table class="grid">
						<tr>
							<td align="right"  width="150px">保单号：</td>
							<td align="left" colspan="3"><input style="width: 300px" name="orderNo" ></td>
						</tr>
						<tr>
							<td align="right">是否成交：</td>
							<td align="left">
								<input type="radio" name="orderFlag" class="easyui-validatebox" value="01" checked="checked">否
        				        <input type="radio" name="orderFlag" class="easyui-validatebox" value="02">是
							</td>
							<td align="right">礼品是否发放：</td>
							<td align="left">
								<input type="radio" name="giftFlag" class="easyui-validatebox" value="01" checked="checked">否
        				        <input type="radio" name="giftFlag" class="easyui-validatebox" value="02">是
        				    </td>
						</tr>
						<tr>
							<td align="right">是否打单：</td>
							<td align="left">
								<input type="radio" name="printFlag" class="easyui-validatebox" value="01" checked="checked">否
        				        <input type="radio" name="printFlag" class="easyui-validatebox" value="02">是
        				     </td>
						</tr>
					</table>
				</div>
				<div class="sxtablelist sxhidedomdiv sxshowsss">
					<table class="grid">
						<tr>
							<td align="right"  width="150px">姓名：</td>
							<td align="left"><input style="width: 200px" name="userName" ></td>
							<td align="right">性别：</td>
							<td align="left">
								<select name="sex" class="easyui-combobox" style="width:150px;" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'男',text:'男'},{id:'女',text:'女'}],value:'男'">
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">婚姻状况：</td>
							<td align="left">
								<select name="marryFlag" class="easyui-combobox" style="width:150px;" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'已婚',text:'已婚'},{id:'未婚',text:'未婚'},{id:'离异',text:'离异'},{id:'丧偶',text:'丧偶'}],value:'未婚'">
								</select>
							</td>
							<td align="right">出生日期：</td>
		                    <td align="left"><input name="birthDate"  type="text" class="easyui-datebox"  style="width:100px;"></td>
		                </tr>
		                <tr>
		                	<td align="right">证件类型：</td>
							<td align="left">
								<select name="certType" class="easyui-combobox" style="width:150px;" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'身份证',text:'身份证'},{id:'护照',text:'护照'},{id:'军人证',text:'军人证'},{id:'通行证',text:'通行证'},{id:'户口本',text:'户口本'}],value:'身份证'">
								</select>
							</td>
		                </tr>
		                <tr>
							<td align="right">证件号码：</td>
							<td align="left" colspan="3"><input style="width: 500px" name="certNo" ></td>
						</tr>
		                <tr>
		                	<td align="right">国籍：</td>
							<td align="left"><input style="width: 200px" name="nationality" ></td>
							<td align="right">户籍：</td>
							<td align="left"><input style="width: 200px" name="household" ></td>
		                </tr>
		                <tr>
		                	<td align="right">证件有效期至：</td>
		                    <td align="left"><input name="validityDate"  type="text" class="easyui-datebox"  style="width:100px;"></td>
		                </tr>
		                <tr>
		                	<td align="right">与被保人关系：</td>
							<td align="left">
								<select name="insuredRelationship" class="easyui-combobox" style="width:150px;" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'配偶',text:'配偶'},{id:'子女',text:'子女'},{id:'父母',text:'父母'},{id:'本人',text:'本人'},{id:'其他',text:'其他'}],value:'配偶'">
								</select>
							</td>
							<td align="right">居民类型：</td>
							<td align="left">
								<select name="residentType" class="easyui-combobox" style="width:150px;" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'城镇',text:'城镇'},{id:'农村',text:'农村'}],value:'城镇'">
								</select>
							</td>
		                </tr>
		                <tr>
							<td align="right">通讯地址：</td>
							<td align="left" colspan="3">
								<div id="city1" class="citys">
				                    <select id="province" name="pprovince" style="width: 120px;"></select>
				                    <select id="city" name="pcity" style="width: 120px;"></select>
				                    <select id="area" name="pdistrict" style="width: 120px;"></select>
					            </div>
								<input style="width: 390px" placeholder="详细地址" name="postalAddress" type="text" class="easyui-validatebox span2" value="">
							</td>
						</tr>
						<tr>
							<td align="right">现住址：</td>
							<td align="left" colspan="3">
								<div id="city2" class="citys">
				                    <select id="province" name="rprovince" style="width: 120px;"></select>
				                    <select id="city" name="rcity" style="width: 120px;"></select>
				                    <select id="area" name="rdistrict" style="width: 120px;"></select>
					            </div>
								<input style="width: 390px" placeholder="详细地址" name="residentialAddress" type="text" class="easyui-validatebox span2" value="">
							</td>
						</tr>
						<tr>
							<td align="right">手机：</td>
							<td align="left"><input style="width: 200px" name="telphone" ></td>
							<td align="right">固定电话：</td>
							<td align="left"><input style="width: 200px" name="mtelphone" ></td>
						</tr>
						<tr>
							<td align="right">E-mail：</td>
							<td align="left"><input style="width: 200px" name="mail" ></td>
							<td align="right">工作单位：</td>
							<td align="left"><input style="width: 200px" name="workUnit" ></td>
						</tr>
						<tr>
							<td align="right">工作内容：</td>
							<td align="left"><input style="width: 200px" name="jobContent" ></td>
							<td align="right">所属行业：</td>
							<td align="left"><input style="width: 200px" name="industry" ></td>
						</tr>
						<tr>
							<td align="right">职业/工种：</td>
							<td align="left"><input style="width: 200px" name="occupation" ></td>
						</tr>
					</table>
				</div>
				<div class="sxtablelist sxhidedomdiv sxshowsss">
					<table class="grid">
						<tr>
							<td align="right"  width="150px">姓名：</td>
							<td align="left"><input style="width: 200px" name="userNameB" ></td>
							<td align="right">性别：</td>
							<td align="left">
								<select name="sexB" class="easyui-combobox" style="width:150px;" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'男',text:'男'},{id:'女',text:'女'}],value:'男'">
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">婚姻状况：</td>
							<td align="left">
								<select name="marryFlagB" class="easyui-combobox" style="width:150px;" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'已婚',text:'已婚'},{id:'未婚',text:'未婚'},{id:'离异',text:'离异'},{id:'丧偶',text:'丧偶'}],value:'未婚'">
								</select>
							</td>
							<td align="right">出生日期：</td>
		                    <td align="left"><input name="birthDateB"  type="text" class="easyui-datebox"  style="width:100px;"></td>
		                </tr>
		                <tr>
		                	<td align="right">证件类型：</td>
							<td align="left">
								<select name="certTypeB" class="easyui-combobox" style="width:150px;" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'身份证',text:'身份证'},{id:'护照',text:'护照'},{id:'军人证',text:'军人证'},{id:'通行证',text:'通行证'},{id:'户口本',text:'户口本'}],value:'身份证'">
								</select>
							</td>
		                </tr>
		                <tr>
							<td align="right">证件号码：</td>
							<td align="left" colspan="3"><input style="width: 500px" name="certNoB" ></td>
						</tr>
		                <tr>
		                	<td align="right">国籍：</td>
							<td align="left"><input style="width: 200px" name="nationalityB" ></td>
							<td align="right">户籍：</td>
							<td align="left"><input style="width: 200px" name="householdB" ></td>
		                </tr>
		                <tr>
		                	<td align="right">证件有效期至：</td>
		                    <td align="left"><input name="validityDateB"  type="text" class="easyui-datebox"  style="width:100px;"></td>
		                </tr>
		                <tr>
							<td align="right">居民类型：</td>
							<td align="left">
								<select name="residentTypeB" class="easyui-combobox" style="width:150px;" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'城镇',text:'城镇'},{id:'农村',text:'农村'}],value:'城镇'">
								</select>
							</td>
		                </tr>
						<tr>
							<td align="right">现住址：</td>
							<td align="left" colspan="3">
								<div id="city3" class="citys">
				                    <select id="province" name="provinceB" style="width: 120px;"></select>
				                    <select id="city" name="cityB" style="width: 120px;"></select>
				                    <select id="area" name="districtB" style="width: 120px;"></select>
					            </div>
								<input style="width: 390px" placeholder="详细地址" name="residentialAddressB" type="text" class="easyui-validatebox span2" value="">
							</td>
						</tr>
						<tr>
							<td align="right">手机：</td>
							<td align="left"><input style="width: 200px" name="telphoneB" ></td>
							<td align="right">固定电话：</td>
							<td align="left"><input style="width: 200px" name="mtelphoneB" ></td>
						</tr>
						<tr>
							<td align="right">E-mail：</td>
							<td align="left"><input style="width: 200px" name="mailB" ></td>
							<td align="right">工作单位：</td>
							<td align="left"><input style="width: 200px" name="workUnitB" ></td>
						</tr>
						<tr>
							<td align="right">工作内容：</td>
							<td align="left"><input style="width: 200px" name="jobContentB" ></td>
							<td align="right">所属行业：</td>
							<td align="left"><input style="width: 200px" name="industryB" ></td>
						</tr>
						<tr>
							<td align="right">职业/工种：</td>
							<td align="left"><input style="width: 200px" name="occupationB" ></td>
						</tr>
					</table>
				</div>
				<div class="sxtablelist sxhidedomdiv sxshowsss">
					<div id="syrTable">
						<table class="grid">
							<tr>
								<td align="right" width="120px">受益顺序：</td>
								<td align="left"><input style="width: 200px" name="syr[0].beneficiaryOrder" ></td>
								<td align="right">受益比例：</td>
								<td align="left"><input style="width: 180px" name="syr[0].beneficialRate" >%</td>
							</tr>
							<tr>
								<td align="right">受益人姓名：</td>
								<td align="left"><input style="width: 200px" name="syr[0].beneficialName" ></td>
								<td align="right">性别：</td>
								<td align="left">
									<select name="syr[0].sex" class="easyui-combobox" style="width:200px;">
										<option value="男">男</option>
										<option value="女">女</option>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right">出生日期：</td>
			                    <td align="left"><input name="syr[0].birthDate"  type="text" class="easyui-datebox"  style="width:200px;"></td>
			                    <td align="right">与被保人关系：</td>
								<td align="left">
									<select name="syr[0].relationship" class="easyui-combobox" style="width:200px;" 
									data-options="editable:false,valueField:'id',textField:'text',data:[{id:'配偶',text:'配偶'},{id:'子女',text:'子女'},{id:'父母',text:'父母'},{id:'本人',text:'本人'},{id:'其他',text:'其他'}],value:'配偶'">
									</select>
								</td>
							</tr>
							 <tr>
			                	<td align="right">证件类型：</td>
								<td align="left">
									<select name="syr[0].certType" class="easyui-combobox" style="width:200px;" 
									data-options="editable:false,valueField:'id',textField:'text',data:[{id:'身份证',text:'身份证'},{id:'护照',text:'护照'},{id:'军人证',text:'军人证'},{id:'通行证',text:'通行证'},{id:'户口本',text:'户口本'}],value:'身份证'">
									</select>
								</td>
			                </tr>
			                <tr>
								<td align="right">证件号码：</td>
								<td align="left" colspan="3"><input style="width: 300px" name="syr[0].certNo" ></td>
							</tr>
							<tr>
			                	<td align="right">证件有效期至：</td>
			                    <td align="left"><input name="syr[0].validityDate"  type="text" class="easyui-datebox"  style="width:200px;"></td>
			                </tr>
			                <tr>
			                	<td align="right">住址：</td>
								<td align="left">
									<select name="syr[0].residentialAddress" class="easyui-combobox" style="width:200px;" 
									data-options="editable:false,valueField:'id',textField:'text',data:[{id:'同投保人',text:'同投保人'},{id:'同被保险人',text:'同被保险人'},{id:'其他',text:'其他'}],value:'同投保人'">
									</select>
								</td>
			                </tr>
			                <tr>
			                	<td colspan="4">
			                	</td>
			                </tr>
						</table>
					</div>
					<div id="syr"></div>
					<div>
						<input type="button" value="+增加" onclick="syrClone()">
					</div>
				</div>
				<div class="sxtablelist sxhidedomdiv sxshowsss">
					<table class="grid">
						<tr>
							<td><span style="background:#66ccff;  border-right:1px solid #fff;  border-left:1px solid #fff;  display:block;  height:25px; width:100px; line-height: 25px;  overflow:hidden;  font-weight:bold;">※ 交通事项</span></td>
						</tr>
						<tr>
							<td>
								<table class="grid">
									<tr>
										<td align="right" width="220px">缴费频率：</td>
										<td align="left">
											<select name="jfpl" class="easyui-combobox" style="width:150px;" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'年交',text:'年交'},{id:'趸交',text:'趸交'},{id:'月交（月交首付需交纳2个月保险费）',text:'月交（月交首付需交纳2个月保险费）'},{id:'其他',text:'其他'}],value:'年交'">
											</select>
										</td>
									</tr>
									<tr>
										<td align="right">保险费自动垫交选择：</td>
										<td align="left">
											<select name="sfzdxj" class="easyui-combobox" style="width:150px;" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'是',text:'是'},{id:'否',text:'否'}],value:'是'">
											</select>
										</td>
									</tr>
									<tr>
										<td align="right">首期保费支付方式：</td>
										<td align="left">
											<select name="sqjffs" class="easyui-combobox" style="width:150px;" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'银行转账',text:'银行转账'},{id:'其他',text:'其他'}],value:'银行转账'">
											</select>
										</td>
									</tr>
									<tr>
										<td align="right">续期保费支付方式：</td>
										<td align="left">
											<select name="xqjffs" class="easyui-combobox" style="width:150px;" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'银行转账',text:'银行转账'},{id:'其他',text:'其他'}],value:'银行转账'">
											</select>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><span style="background:#66ccff;  border-right:1px solid #fff;  border-left:1px solid #fff;  display:block;  height:25px; width:200px; line-height: 25px;  overflow:hidden;  font-weight:bold;">※ 银行自动转账授权事项</span></td>
						</tr>
						<tr>
							<td>
								<table class="grid">
									<tr>
										<td align="right" width="220px">开户行名称：</td>
										<td align="left">
											<input name="khhmc">
										</td>
									</tr>
									<tr>
										<td align="right">授权转账账号：</td>
										<td align="left">
											<input name="yhzh">
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><span style="background:#66ccff;  border-right:1px solid #fff;  border-left:1px solid #fff;  display:block;  height:25px; width:100px; line-height: 25px;  overflow:hidden;  font-weight:bold;">※ 保险事项</span></td>
						</tr>
						<tr>
							<td>
								<table id="bxsx" class="grid">
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
										<td align="center">主险<input type="hidden" name="tbsx_bxsx[0].order" value="0"></td>
										<td align="center"><textarea name="tbsx_bxsx[0].name" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsx_bxsx[0].code" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsx_bxsx[0].bxDate" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsx_bxsx[0].jfDate" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsx_bxsx[0].bxMoney" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsx_bxsx[0].bxFee" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsx_bxsx[0].zjbf" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsx_bxsx[0].bfxj" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
									</tr>
									<tr id="a">
										<td align="center">附险1<input type="hidden" name="tbsx_bxsx[1].order" value="1"></td>
										<td align="center"><textarea name="tbsx_bxsx[1].name" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsx_bxsx[1].code" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsx_bxsx[1].bxDate" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsx_bxsx[1].jfDate" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsx_bxsx[1].bxMoney" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsx_bxsx[1].bxFee" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsx_bxsx[1].zjbf" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
										<td align="center"><textarea name="tbsx_bxsx[1].bfxj" style="width: 60px; border: 0px; outline:none;"></textarea>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><input type="button" value="+增加附险" onclick="bxsxAddNew()"></td>
						</tr>
						<tr>
							<td>保费合计</td>
						</tr>
						<tr>
							<td><span style="background:#66ccff;  border-right:1px solid #fff;  border-left:1px solid #fff;  display:block;  height:25px; width:180px; line-height: 25px;  overflow:hidden;  font-weight:bold;">※ 分红保险填写</span></td>
						</tr>
						<tr>
							<td>
								<table class="grid">
									<tr>
										<td align="right" width="220px">红利领取方式：</td>
										<td align="left">
											<select name="hllqfs" class="easyui-combobox" style="width:150px;" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'累计生息',text:'累计生息'},{id:'现金领取',text:'现金领取'},{id:'红利转万能险账户',text:'红利转万能险账户'},{id:'抵交保险费',text:'抵交保险费'},{id:'其他',text:'其他'}],value:'累计生息'">
											</select>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><span style="background:#66ccff;  border-right:1px solid #fff;  border-left:1px solid #fff;  display:block;  height:25px; width:180px; line-height: 25px;  overflow:hidden;  font-weight:bold;">※ 分红保险填写</span></td>
						</tr>
						<tr>
							<td>
								<table class="grid">
									<tr>
										<td align="right" width="220px">领取年龄：</td>
										<td align="left">
											<select name="hllqnl" class="easyui-combobox" style="width:150px;" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'50岁',text:'50岁'},{id:'55岁',text:'55岁'},{id:'60岁',text:'60岁'},{id:'65岁',text:'65岁'},{id:'其他',text:'其他'}],value:'50岁'">
											</select>
										</td>
									</tr>
									<tr>
										<td align="right" width="220px">领取方式：</td>
										<td align="left">
											<select name="lqfs" class="easyui-combobox" style="width:150px;" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'年领',text:'年领'},{id:'月领',text:'月领'},{id:'其他',text:'其他'}],value:'年领'">
											</select>
										</td>
									</tr>
									<tr>
										<td align="right" width="220px">领取类型：</td>
										<td align="left">
											<select name="hllqlx" class="easyui-combobox" style="width:150px;" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'平准年金',text:'平准年金'},{id:'增额年金',text:'增额年金'},{id:'其他',text:'其他'}],value:'平准年金'">
											</select>
										</td>
									</tr>
									<tr>
										<td align="right" width="220px">自动支付保险费选择：</td>
										<td align="left">
											<select name="sfzdzf" class="easyui-combobox" style="width:150px;" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'是',text:'是'},{id:'否',text:'否'}],value:'是'">
											</select>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><span style="background:#66ccff;  border-right:1px solid #fff;  border-left:1px solid #fff;  display:block;  height:25px; width:200px; line-height: 25px;  overflow:hidden;  font-weight:bold;">※ 投资连结保险填写</span></td>
						</tr>
						<tr>
							<td>
								<table id="tzlj" class="grid">
									<tr>
										<td align="right" width="220px">投资账户名称：</td>
										<td align="left">
											<input name="tzlj[0].tzzhmc">
										</td>
									</tr>
									<tr>
										<td align="right">分配比例：</td>
										<td align="left">
											<input name="tzlj[0].fpbl">
										</td>
									</tr>
									<tr>
										<td align="right">追加保险费分配比例：</td>
										<td align="left">
											<input name="tzlj[0].zjbxfpbl">
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><input type="button" value="+增加" onclick="tzljAddNew()"></td>
						</tr>
					</table>
				</div>
				<div class="sxtablelist sxhidedomdiv sxshowsss">
					<table  class="grid">
						<tr>
							<td><span style="background:#66ccff;  border-right:1px solid #fff;  border-left:1px solid #fff;  display:block;  height:25px; width:100px; line-height: 25px;  overflow:hidden;  font-weight:bold;">※ A 健康告知</span></td>
						</tr>
						<tr>
							<td>
								<table class="grid">
									<tr>
										<td align="right" width="220px">投保人，身高：</td>
										<td align="left">
											<input name="tbrsg">厘米
										</td>
									</tr>
									<tr>
										<td align="right">投保人，体重：</td>
										<td align="left">
											<input name="tbrtz">公斤
										</td>
									</tr>
									<tr>
										<td align="right">被保险人，身高：</td>
										<td align="left">
											<input name="bbrsg">厘米
										</td>
									</tr>
									<tr>
										<td align="right">被保险人，体重：</td>
										<td align="left">
											<input name="bbrtz">公斤
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table class="grid">
									<tr>
										<td align="center" width="450px">保险公司询问事项</td>
										<td align="center">投保人</td>
										<td align="center">被保险人</td>
									</tr>
									<tr>
										<td align="left"  width="450px">1、您过去一年内是否有以下不适症状或体征：反复头痛、头晕、胸痛、胸闷、心悸、气喘、咳嗽、咳血、腹痛、腹泻、血尿、便血、紫癜、皮肤黄染、明显消瘦（体重在3个月内下降超过5公斤）？</td>
										<td align="center">
											<input type="radio" name="txwsx1" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx1" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx1" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx1" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">2、您过去一年内是否因受伤或患病去医院门诊接受诊查、治疗，或被医师建议治疗、住院或手术？</td>
										<td align="center">
											<input type="radio" name="txwsx2" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx2" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx2" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx2" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">3、您在过去2年内是否做过以下检查：血压、血液和尿液化验检查、x光、心电图、B超、CT、核磁共振、内窥镜、病理活组织检查或其他化验检查？</td>
										<td align="center">
											<input type="radio" name="txwsx3" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx3" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx3" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx3" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">4、您过去是否曾因受伤或患病而住院治疗？若是，请在说明栏中详述医院诊断、诊治经过及预后。</td>
										<td align="center">
											<input type="radio" name="txwsx4" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx4" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx4" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx4" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">5、您过去是否患有、曾被怀疑患者有或因以下疾病症候而接受治疗：</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
									</tr>
									<tr>
										<td align="left">a、脑、神经系统及精神方面疾病：脑膜炎、脑炎、脑瘤、脑中风（脑出血、脑梗塞、短暂性脑缺血）等脑血管疾病；癫痫、脊髓病变、重症肌无力、多发性硬化、帕金森氏病；抑郁症、精神分裂症、神经衰弱、神经官能症以及其他神经或精神方面疾病？</td>
										<td align="center">
											<input type="radio" name="txwsx5a" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx5a" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx5a" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx5a" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">b、五官科疾病：如原因不明的声音嘶哑、听力下降、复视、耳鸣、中耳炎、美尼尔病、白内障、青光眼、高度近视（800度以上）、视神经或视网膜病变、慢性鼻炎、鼻息肉或其他五官科疾病等？</td>
										<td align="center">
											<input type="radio" name="txwsx5b" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx5b" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx5b" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx5b" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">c、循环系统疾病：如高血压、冠心病、心肌梗塞、风湿性心脏病、先天性心脏病、风湿热、主动脉瘤、肺心病、心律失常、传导阻滞、心包炎、心肌病、下肢静脉曲张及其他心血管系统疾病？</td>
										<td align="center">
											<input type="radio" name="txwsx5c" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx5c" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx5c" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx5c" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">d、呼吸系统疾病：如慢性支气管炎、肺气肿、支气管扩张、肺结核、气胸、哮喘或其他呼吸系统疾病？</td>
										<td align="center">
											<input type="radio" name="txwsx5d" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx5d" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx5d" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx5d" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">e、消化系统疾病：如肝炎病毒携带、肝炎、脂肪肝、肝硬化、肝肿大、肝胆结石、胆囊息肉、胰腺疾病、胃炎、消化道溃疡或出血、穿孔、结肠炎、肠息肉、肠梗阻、疝气或其他消化系统疾病？</td>
										<td align="center">
											<input type="radio" name="txwsx5e" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx5e" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx5e" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx5e" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">f、泌尿及生殖系统疾病：如肾炎、肾小球疾病、肾病综合症、省功能衰竭、多囊肾、泌尿系结石或感染、前列腺疾病或其他泌尿及生殖系统疾病？</td>
										<td align="center">
											<input type="radio" name="txwsx5f" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx5f" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx5f" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx5f" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">g、内分泌及结缔组织疾病：如糖尿病、痛风、高尿酸血症、甲状腺或甲状腺旁腺疾病、肾上腺疾病、脑垂体疾病；类风湿性关节炎、风湿病、红斑狼疮、胶原病、白塞氏病、免疫性疾病、肌肉骨骼关节疾病等？</td>
										<td align="center">
											<input type="radio" name="txwsx5g" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx5g" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx5g" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx5g" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">h、血液疾病：如原因不明的皮肤、粘膜、及齿龈出血、白血病、再生障碍性贫血、血友病、紫癜症、贫血、脾脏疾病等？</td>
										<td align="center">
											<input type="radio" name="txwsx5h" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx5h" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx5h" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx5h" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">i、癌症、良恶性肿瘤、腺瘤、息肉、囊肿、痔疮、任何包块或肿物等？其他需持续关注或治疗的疾病或异常症状体征？</td>
										<td align="center">
											<input type="radio" name="txwsx5i" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx5i" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx5i" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx5i" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">j、顽固性皮肤病、性病，传染病？或任何职业病？您或您的配偶是否接受过艾滋病的检查和治疗？或在过去6个月以内曾经持续一周以上的下列症状：体重下降、食欲不振、盗汗、腹泻、淋巴结肿大及皮肤溃疡？</td>
										<td align="center">
											<input type="radio" name="txwsx5j" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx5j" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx5j" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx5j" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">k、接受过输血或建议不宜献血？您是否使用过任何违禁药品或成瘾性药物，是否服用、吸食或注射过毒品？</td>
										<td align="center">
											<input type="radio" name="txwsx5k" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx5k" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx5k" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx5k" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">6、身体残障情况：有无智能障碍；有无失明、聋哑、跛行、小二麻痹后遗症；有无脊柱、胸廓、四肢、五官畸形或功能障碍；有无语言、咀嚼、视力、听力、嗅觉、四肢及中枢神经系统机能障碍？</td>
										<td align="center">
											<input type="radio" name="txwsx6" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx6" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx6" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx6" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">7、有否吸烟嗜好：若有请说明每天吸烟几只，约多少年。若已停止吸烟，请说明停止的原因和时间。</td>
										<td align="center">
											<input type="radio" name="txwsx7" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx7" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx7" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx7" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="center">
											<table>
												<tr>
													<td>每天吸烟<input name="mtxy">只</td>
												</tr>
												<tr>
													<td>吸烟时间<input name="xysj">年</td>
												</tr>
											</table>
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
									</tr>
									<tr>
										<td align="left">8、有否饮酒嗜好：若有，请说明饮酒种类，每天约几两，约多少年。若已停止饮酒，请说明停止的原因及时间。</td>
										<td align="center">
											<input type="radio" name="txwsx8" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx8" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx8" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx8" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="center">
											<table>
												<tr>
													<td>饮酒种类<input name="yjzl"></td>
												</tr>
												<tr>
													<td>每天饮酒<input name="mtyj">量</td>
												</tr>
												<tr>
													<td>饮酒时间<input name="yjsj">年</td>
												</tr>
											</table>
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
									</tr>
									<tr>
										<td align="left">9、家族史栏：您的父母、子女、兄弟姐妹中，是否有人出现上述5-6项情况？</td>
										<td align="center">
											<input type="radio" name="txwsx9" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx9" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx9" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx9" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">10、女性告知栏：</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
									</tr>
									<tr>
										<td align="left">a、有否阴道不规则流血、白带异常、下腹痛或患子宫、卵巢等妇科疾病；近半年有否月经不调？</td>
										<td align="center">
											<input type="radio" name="txwsx10a" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx10a" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx10a" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx10a" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">b、有否乳房方面疾病与不适症状体征，或因此而接受医师检查、用药或住院手术等治疗？</td>
										<td align="center">
											<input type="radio" name="txwsx10b" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx10b" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx10b" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx10b" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">c、目前是否怀孕？怀孕几周；是否因妊娠分娩而住院或手术；有否异位妊娠或不孕不育？</td>
										<td align="center">
											<input type="radio" name="txwsx10c" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx10c" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx10c" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx10c" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="center">
											怀孕时间<input name="hysj">周
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
									</tr>
									<tr>
										<td align="left">11、2周岁以下（含2周岁）被保险人告知栏：出生时的身高（厘米）？体重（公斤）？</td>
										<td align="center">
											<input type="radio" name="txwsx11" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx11" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx11" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx11" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="center">
											<table>
												<tr>
													<td>身高<input name="cssg"></td>
												</tr>
												<tr>
													<td>体重<input name="cstz"></td>
												</tr>
											</table>
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
									</tr>
									<tr>
										<td align="left">a、是否为早产儿？出生时是否曾有产伤、窒息等异常情况？</td>
										<td align="center">
											<input type="radio" name="txwsx11a" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx11a" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx11a" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx11a" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">b、是否有发育迟缓、智力障碍、惊厥、抽搐、脑瘫、缺氧缺血性脑病、先天性遗传性疾病？</td>
										<td align="center">
											<input type="radio" name="txwsx11b" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx11b" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx11b" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx11b" class="easyui-validatebox" value="02">否
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><span style="background:#66ccff;  border-right:1px solid #fff;  border-left:1px solid #fff;  display:block;  height:25px; width:200px; line-height: 25px;  overflow:hidden;  font-weight:bold;">※ B 财务及其他告知</span></td>
						</tr>
						<tr>
							<td>
								<table class="grid">
									<tr>
										<td align="right" width="220px">投保人，年收入：</td>
										<td align="left">
											<input name="tbrnsr">万元
										</td>
									</tr>
									<tr>
										<td align="right">来源：</td>
										<td align="left">
											<select name="tbrsrly" class="easyui-combobox" style="width:150px;" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'工薪',text:'工薪'},{id:'个体',text:'个体'},{id:'私营',text:'私营'},{id:'房屋出租',text:'房屋出租'},{id:'证券投资',text:'证券投资'},{id:'银行利息',text:'银行利息'},{id:'其他',text:'其他'}],value:'工薪'">
											</select>
										</td>
									</tr>
									<tr>
										<td align="right">被保险人年收入：</td>
										<td align="left">
											<input name="bbrnsr">万元
										</td>
									</tr>
									<tr>
										<td align="right">来源：</td>
										<td align="left">
											<select name="bbrsrly" class="easyui-combobox" style="width:150px;" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'工薪',text:'工薪'},{id:'个体',text:'个体'},{id:'私营',text:'私营'},{id:'房屋出租',text:'房屋出租'},{id:'证券投资',text:'证券投资'},{id:'银行利息',text:'银行利息'},{id:'其他',text:'其他'}],value:'工薪'">
											</select>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table  class="grid">
									<tr>
										<td align="center" width="450px">保险公司询问事项</td>
										<td align="center">投保人</td>
										<td align="center">被保险人</td>
									</tr>
									<tr>
										<td align="left">13、您是否有借贷？若是，请在说明栏中详述借贷事由、金额、债权人及偿还期限等信息</td>
										<td align="center">
											<input type="radio" name="txwsx13" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx13" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx13" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx13" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">14、您目前是否拥有公费医疗、社会医疗保险？</td>
										<td align="center">
											<input type="radio" name="txwsx14" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx14" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx14" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx14" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">15、您是否曾购买或正在购买除本投保申请以外的其他保险产品（含其他保险公司）？</td>
										<td align="center">
											<input type="radio" name="txwsx15" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx15" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx15" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx15" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">16、您的投保生气是否曾被拒保、延期、加费、或作任何承保条件的修改（喊本公司及其他保险公司）？</td>
										<td align="center">
											<input type="radio" name="txwsx16" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx16" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx16" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx16" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">17、您是否曾向任何保险公司提出过索赔申请？若是，请详述：公司名称，索赔日期，索赔金额，索赔事由。</td>
										<td align="center">
											<input type="radio" name="txwsx17" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx17" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx17" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx17" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="center">
											<table>
												<tr>
													<td>公司名称<input name="spgsmc"></td>
												</tr>
												<tr>
													<td>索赔日期<input name="sprq"></td>
												</tr>
												<tr>
													<td>索赔金额<input name="spje"></td>
												</tr>
												<tr>
													<td>索赔事由<input name="spsy"></td>
												</tr>
											</table>
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
									</tr>
									<tr>
										<td align="left">18、您是否有机动车驾照？若是，请详述：驾照类型，签发日期，若现有驾车，请注明：驾龄，车辆类型，用途。</td>
										<td align="center">
											<input type="radio" name="txwsx18" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx18" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx18" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx18" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="center">
											<table>
												<tr>
													<td>驾照类型<input name="jzlx"></td>
												</tr>
												<tr>
													<td>签发日期<input name="jzqfrq"></td>
												</tr>
												<tr>
													<td>驾龄<input name="jzjl">年</td>
												</tr>
												<tr>
													<td>车辆类型<input name="cllx"></td>
												</tr>
												<tr>
													<td>用途<input name="clyt"></td>
												</tr>
											</table>
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
									</tr>
									<tr>
										<td align="left">19、您是否参加潜水、攀岩、蹦极、滑水、滑雪、探险、武术等高风险运动？</td>
										<td align="center">
											<input type="radio" name="txwsx19" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx19" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx19" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx19" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="left">20、您是否曾在国外持续居住三个月以上或正拟前往中国大陆以外的国家/地区？若是，请详述：</td>
										<td align="center">
											<input type="radio" name="txwsx20" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="txwsx20" class="easyui-validatebox" value="02">否
										</td>
										<td align="center">
											<input type="radio" name="bxwsx20" class="easyui-validatebox" value="01" checked="checked">是<br />
        				        			<input type="radio" name="bxwsx20" class="easyui-validatebox" value="02">否
										</td>
									</tr>
									<tr>
										<td align="center">
											<table>
												<tr>
													<td>国家/地区名<input name="jzgj"></td>
												</tr>
												<tr>
													<td>缘由<input name="jzyy"></td>
												</tr>
												<tr>
													<td>滞留时间<input name="jzzlsj"></td>
												</tr>
											</table>
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<div class="sxtablelist sxhidedomdiv sxshowsss">
					<table class="grid">
						<tr>
							<td align="right">保险公司：</td>
							<td align="left">
							<input name="insuranceCompanyId" class="easyui-combobox" data-options="width:180, valueField: 'id', textField: 'insurerName', url: '${path}/mgr/tInsurer/list'"/>
							<input name="insuranceCompany" type="hidden" value=""> 
							</td>
							<td align="right">保险公司报案电话：</td>
							<td align="left">
							<input style="width: 180px; border: 0px; outline:none;" name="insuranceCompanyTel" ></td>
						</tr>
						<tr>
							<td align="right">出单渠道：</td>
							<td align="left">
								<input name="operator" class="easyui-combobox" data-options="width:180, valueField: 'id', textField: 'name', url: '${path}/mgr/fuser/list'"/>
							</td>
							<td align="right">投保方式：</td>
							<td align="left"><input name="tbfs" class="easyui-combobox" data-options="width:180, valueField: 'id', textField: 'name', url: '${path}/mgr/parameter/list?type=01'"/></td>
						</tr>
						<tr>
							<td align="right">净主险：</td>
							<td align="left"><input style="width: 130px" class="easyui-numberbox" value="" data-options="min:0,precision:2" id="jzx" name="jzx" >
								<a href="javascript:void(0);" class="easyui-linkbutton"  onclick="zxbfFun();">工具</a>
							</td>
							<td align="right">主险税：</td>
							<td align="left"><input style="width: 180px" class="easyui-numberbox" value="" data-options="min:0,precision:2" id="zxs" name="zxs" ></td>
						</tr>
						<tr>
							<td align="right">净附险1：</td>
							<td align="left"><input style="width: 130px" class="easyui-numberbox" value="" data-options="min:0,precision:2" id="jfx1" name="jfx1">
								<a href="javascript:void(0);" class="easyui-linkbutton"  onclick="jfx1Fun();">工具</a>
							</td>
							<td align="right">附险1税：</td>
							<td align="left"><input style="width: 180px" class="easyui-numberbox" value="" data-options="min:0,precision:2" id="fxs1" name="fxs1"></td>
						</tr>
						<tr>
							<td align="right">净附险2：</td>
							<td align="left"><input style="width: 130px" class="easyui-numberbox" value="" data-options="min:0,precision:2" id="jfx2" name="jfx2">
								<a href="javascript:void(0);" class="easyui-linkbutton"  onclick="jfx2Fun();">工具</a>
							</td>
							<td align="right">附险2税：</td>
							<td align="left"><input style="width: 180px" class="easyui-numberbox" value="" data-options="min:0,precision:2" id="fxs2" name="fxs2"></td>
						</tr>
						<tr>
							<td align="right">业务员：</td>
							<td align="left"><input id="salesManSx" name="salesMan" type="hidden" value="">
	                    	<input id="salesManNameSx" name="salesManName" value="" type="text" readonly="readonly" placeholder="请选择业务员">
	                    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="saleManForOrderSxCheckFun();">选择</a></td>
							<td align="right">佣金支付对象：</td>
							<td align="left"><select name="yjzfdx" class="easyui-combobox" style="width: 180px">  
							    <option value="01">业务员</option>  
							    <option value="02">录单员</option>  
							</select></td>
						</tr>
						<tr>
							<td align="right">主险佣金比例：</td>
							<td align="left"><input style="width: 180px" name="zxyjbl" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
							<td align="right">主线佣金金额：</td>
							<td align="left"><input style="width: 180px" name="zxyjje" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
						</tr>
						<tr>
							<td align="right">附险1佣金比例：</td>
							<td align="left"><input style="width: 180px" name="fxyjbl1" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
							<td align="right">附险1佣金金额：</td>
							<td align="left"><input style="width: 180px" name="fxyjje1" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
						</tr>
						<tr>
							<td align="right">附险2佣金比例：</td>
							<td align="left"><input style="width: 180px" name="fxyjbl2" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
							<td align="right">附险2佣金金额：</td>
							<td align="left"><input style="width: 180px" name="fxyjje2" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
						</tr>
						<tr>
							<td align="right">综合金融奖比例：</td>
							<td align="left"><input style="width: 180px" name="zhjrjbl" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
							<td align="right">综合金融奖金额：</td>
							<td align="left"><input style="width: 180px" name="zhjrjje" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
						</tr>
						
						<tr>
							<td align="right">保险公司短期激励：</td>
							<td align="left"><input style="width: 180px" name="bxgsdqjl" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
							<td align="right">是否有礼品：</td>
							<td align="left"><input type="radio" name="sfylp" class="easyui-validatebox" value="01" checked="checked">否
        				        <input type="radio" name="sfylp" class="easyui-validatebox" value="02">是</td>
						</tr>
						<tr>
							<td align="right">礼品内容：</td>
							<td align="left" colspan="3"><input style="width: 500px" name="lpnr" ></td>
						</tr>
						<tr>
							<td align="right">录单员ID：</td>
							<td align="left">
								<input style="width: 180px; border: 0px;outline:none;" name="recorderName" value="${recorderName }">
								<input type="hidden" name="recorder" value="${recorder }">
							</td>
							<td align="right">录单员佣金：</td>
							<td align="left"><input style="width: 180px" name="recordertc" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
						</tr>
					</table>
				</div>
			</div>
        </form>
    </div>
</div>
<div id="zxbfTool" style="display: none;">
	<table class="grid">
		<tr>
			<td align="right">主险保费：</td>
			<td align="left"><input style="width: 200px" id="zxbf" name="zxbf" onchange="zxbf()"></td>
		</tr>
		<tr>
			<td align="right">主险税金比例：</td>
			<td align="left"><input style="width: 200px" id="zxsjbl" value="0.06"></td>
		</tr>
		<tr>
			<td align="right">净主险保费：</td>
			<td align="left"><input style="width: 200px;border: 0px;outline: none;" value="0.00" id="jzxbfStr" name="jzxbfStr"></td>
		</tr>
		<tr>
			<td align="right">主险税金金额：</td>
			<td align="left"><input style="width: 200px;border: 0px;outline: none;" value="0.00" id="zxsjjeStr" name="zxsjjeStr"></td>
		</tr>
	</table>
	<div id="carCheckToolbar" data-options="region:'south',border:false" style="display: block; background-color: #CCCCCC; text-align: center;">
	    <shiro:hasPermission name="/orderInfo/add">
	        <a onclick="getJzx();" href="javascript:void(0);" class="easyui-linkbutton">确&nbsp;&nbsp;&nbsp;&nbsp;定</a>
	    </shiro:hasPermission>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    <shiro:hasPermission name="/orderInfo/add">
	        <a onclick="cancelJzx();" href="javascript:void(0);" class="easyui-linkbutton">取&nbsp;&nbsp;&nbsp;&nbsp;消</a>
	    </shiro:hasPermission>
	</div>
</div>
<div id="jfx1Tool" style="display: none;">
	<table class="grid">
		<tr>
			<td align="right">附险1保费：</td>
			<td align="left"><input style="width: 200px" id="fx1bf" name="fx1bf" onchange="fx1bf()"></td>
		</tr>
		<tr>
			<td align="right">附险1税金比例：</td>
			<td align="left"><input style="width: 200px" id="fx1sjbl" value="0.06"></td>
		</tr>
		<tr>
			<td align="right">净附险1保费：</td>
			<td align="left"><input style="width: 200px;border: 0px;outline: none;" value="0.00" id="jfx1bfStr" name="jfx1bfStr"></td>
		</tr>
		<tr>
			<td align="right">附险1税金金额：</td>
			<td align="left"><input style="width: 200px;border: 0px;outline: none;" value="0.00" id="fx1sjjeStr" name="fx1sjjeStr"></td>
		</tr>
	</table>
		<div id="carCheckToolbar" data-options="region:'south',border:false" style="display: block; background-color: #CCCCCC; text-align: center;">
	    <shiro:hasPermission name="/orderInfo/add">
	        <a onclick="getJfx1();" href="javascript:void(0);" class="easyui-linkbutton">确&nbsp;&nbsp;&nbsp;&nbsp;定</a>
	    </shiro:hasPermission>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    <shiro:hasPermission name="/orderInfo/add">
	        <a onclick="cancelJfx1();" href="javascript:void(0);" class="easyui-linkbutton">取&nbsp;&nbsp;&nbsp;&nbsp;消</a>
	    </shiro:hasPermission>
	</div>
</div>
<div id="jfx2Tool" style="display: none;">
	<table class="grid">
		<tr>
			<td align="right">附险1保费：</td>
			<td align="left"><input style="width: 200px" id="fx2bf" name="fx2bf" onchange="fx2bf()"></td>
		</tr>
		<tr>
			<td align="right">附险1税金比例：</td>
			<td align="left"><input style="width: 200px" id="fx2sjbl" value="0.06"></td>
		</tr>
		<tr>
			<td align="right">净附险1保费：</td>
			<td align="left"><input style="width: 200px;border: 0px;outline: none;" value="0.00" id="jfx2bfStr" name="jfx2bfStr"></td>
		</tr>
		<tr>
			<td align="right">附险1税金金额：</td>
			<td align="left"><input style="width: 200px;border: 0px;outline: none;" value="0.00" id="fx2sjjeStr" name="fx2sjjeStr"></td>
		</tr>
	</table>
		<div id="carCheckToolbar" data-options="region:'south',border:false" style="display: block; background-color: #CCCCCC; text-align: center;">
	    <shiro:hasPermission name="/orderInfo/add">
	        <a onclick="getJfx2();" href="javascript:void(0);" class="easyui-linkbutton">确&nbsp;&nbsp;&nbsp;&nbsp;定</a>
	    </shiro:hasPermission>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    <shiro:hasPermission name="/orderInfo/add">
	        <a onclick="cancelJfx2();" href="javascript:void(0);" class="easyui-linkbutton">取&nbsp;&nbsp;&nbsp;&nbsp;消</a>
	    </shiro:hasPermission>
	</div>
</div>
<div id="saleManForOrderSx" style="display: none;">
	<div class="easyui-layout" data-options="fit:true,border:false">
	    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden; background-color: #fff">
	        <form id="saleManForOrderSxSearchForm">
	            <table>
	                <tr>
	                    <th>关键字搜索</th>
	                    <td>
	                    	<select name="searchName" class="easyui-combobox"
								style="width: 100px">
									<option value="userName">姓名</option>
									<option value="mtelphone">手机</option>
									<option value="wechat">微信</option>
							</select>
	                    </td>
						<td><input name="val" style="width: 200px" placeholder="产品名称查询" /></td>
	                    <td>
	                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="saleManForOrderSxSearchFun();">查询</a>
	                    </td>
	                </tr>
	            </table>
	        </form>
	     </div>	 
	     
	    <div data-options="region:'center',border:false" style="border: 0px solid red;">
	        <table id="saleManForOrderSxDataGrid" data-options="fit:true,border:false"></table>
	    </div>
	    <div id="saleManForOrderSxToolbar" data-options="region:'south',border:false" style="display: block; background-color: #CCCCCC; text-align: center;">
		    <shiro:hasPermission name="/orderInfo/add">
		        <a onclick="getSaleManForOrderSx();" href="javascript:void(0);" class="easyui-linkbutton">确&nbsp;&nbsp;&nbsp;&nbsp;定</a>
		    </shiro:hasPermission>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <shiro:hasPermission name="/orderInfo/add">
		        <a onclick="cancelSaleManForOrderSx();" href="javascript:void(0);" class="easyui-linkbutton">取&nbsp;&nbsp;&nbsp;&nbsp;消</a>
		    </shiro:hasPermission>
		</div>
	</div>
</div>