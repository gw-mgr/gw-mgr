<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<%@ include file="/commons/head.jsp"%>
<script type='text/javascript' src='${path }/static/jquery.citys.js'></script>  
<script type="text/javascript">
	$(function() {
		$('#orderCxAddForm').form({
			url : '${path}/mgr/orderCx/add',
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
					var form = $('#orderCxAddForm');
					parent.$.messager.alert('错误', eval(result.msg), 'error');
				}
			}
		});
		$('#city').citys();
		$("#recorder").val('${recorder}');
	});
	function carCheckFun() {
    	$('#carCheckDataGrid').datagrid({
            url : '${path}/mgr/tCustomerBasicinfo/dataGrid',
            queryParams: {          
            	carNum: $('#carNum').val()
              },
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : false,
    		selectOnCheck: true,
    		checkOnSelect: true,
            idField : 'userId',
            sortName : 'userId',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            frozenColumns : [ [ {
            	field:'userId',
    			checkbox:true 
            },{
                width : '100',
                title : '车牌号',
                field : 'carNum',
                sortable : true,
                align : 'center'
            },{
                width : '250',
                title : '车架号',
                field : 'carFrameNum',
                sortable : true,
                align : 'center'
            } ] ],
            onLoadSuccess:function(data){
                $('.orderInfo-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.orderInfo-easyui-linkbutton-del').linkbutton({text:'删除'});
            }
        });
	    $("#car").show();//必须先显示，再弹出
	    $("#car").dialog({
	        title: "产品选择",
	        width: 400,
	        height: 300
	    });
    }
    function getCar(){
        var rows = $('#carCheckDataGrid').datagrid('getSelections');
        var productName;
 	   if(rows.length==0){
 		   alert('请选择一驾车辆！');
 	   }else{
 		 	$('#carFrameNum').val(rows[0].carFrameNum);
 		 	$('#carType').combobox('setValue', rows[0].carType);
 		 	$('#carNum').val(rows[0].carNum);
	 		$('#engineNum').val(rows[0].engineNum);
	 		$('#changType').val(rows[0].changType);
	 		$('#trafficInsStartTime').datebox('setValue', rows[0].trafficInsStartTime);	
	 		$('#businessInsStartTime').datebox('setValue', rows[0].businessInsStartTime);	
	 		$('#registerTime').datebox('setValue', rows[0].registerTime);	
 	   }
 	   $('#carCheckDataGrid').datagrid('clearSelections');
 	   $("#car").css("display", "none"); 
 	   $("#car").dialog('close');
     }
    function cancelCar(){
    	$('#carCheckDataGrid').datagrid('clearSelections');
 	   $("#car").css("display", "none"); 
 	   $("#car").dialog('close');
     }
    
    
    function jsyxFun(){
    	$("#jsyxTool").show();//必须先显示，再弹出
	    $("#jsyxTool").dialog({
	        title: "净商业险计算",
	        width: 400,
	        height: 200
	    });
    }
    function getJsyx(){
       $("#jsyx").numberbox('setValue',$("#jsyxStr").val());
  	   $("#syxs").numberbox('setValue',$("#syxsStr").val());
 	   $("#jsyxTool").css("display", "none"); 
 	   $("#jsyxTool").dialog('close');
     }
    function cancelJsyx(){
       $("#jsyxTool").css("display", "none"); 
  	   $("#jsyxTool").dialog('close');
     }
    
    function jjqxFun(){
    	$("#jjqxTool").show();//必须先显示，再弹出
	    $("#jjqxTool").dialog({
	        title: "净交强险计算",
	        width: 400,
	        height: 200
	    });
    }
    function getJjqx(){
    	$("#jjqx").numberbox('setValue',$("#jjqxStr").val());
    	$("#jqxs").numberbox('setValue',$("#jqxsStr").val());
 	   $("#jjqxTool").css("display", "none"); 
 	   $("#jjqxTool").dialog('close');
     }
    function cancelJjqx(){
       $("#jjqxTool").css("display", "none"); 
  	   $("#jjqxTool").dialog('close');
     }
    
    function saleManForOrderCxCheckFun (){
       	$('#saleManForOrderCxDataGrid').datagrid({
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
       	$("#saleManForOrderCx").show();//必须先显示，再弹出
        $("#saleManForOrderCx").dialog({
            title: "业务员选择",
            width: 600,
            height: 400
        });
       }
      
      function saleManForOrderCxSearchFun() {
           $('#saleManForOrderCxDataGrid').datagrid('load', $.serializeObject($('#saleManForOrderCxSearchForm')));
      }
      function getSaleManForOrderCx(id){
         var rows = $('#saleManForOrderCxDataGrid').datagrid('getSelections');
    	   if(rows.length==0){
    		   alert('请选择一个业务员！');
    	   }else{
    		   $('#salesManCx').val(rows[0].id);
    		   $('#salesManNameCx').val(rows[0].userName);
    	   }
    	   $('#saleManForOrderCxDataGrid').datagrid('clearSelections'); 
    	   $("#saleManForOrderCx").css("display", "none"); 
    	   $("#saleManForOrderCx").dialog('close');
      }
      function cancelSaleManForOrderCx(){
    	   $('#saleManForOrderCxDataGrid').datagrid('clearSelections'); 
    	   $("#saleManForOrderCx").css("display", "none"); 
    	   $("#saleManForOrderCx").dialog('close');
      }
      function syx(){
    	  var zsyx = parseFloat($("#zsyx").val());
    	  var syxsjbl = parseFloat($("#syxsjbl").val()); 
    	  $("#jsyxStr").val((zsyx/(1+syxsjbl)).toFixed(2));
    	  $("#syxsStr").val(zsyx-(zsyx/(1+syxsjbl)).toFixed(2));
      }
      function jqx(){
    	  var zjqx = parseFloat($("#zjqStr").val());
    	  var jqxsjbl = parseFloat($("#jqsjbl").val()); 
    	  $("#jjqxStr").val((zjqx/(1+jqxsjbl)).toFixed(2));
    	  $("#jqxsStr").val(zjqx-(zjqx/(1+jqxsjbl)).toFixed(2));
      }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="overflow: hidden;">
		<form id="orderCxAddForm" method="post">
			<div>
				<div class="ordercx_details">
					<ul>
						<li class="forcxtab cxprepaids checked">订单信息</li>
						<li class="forcxtab cxprepaids2">车辆信息</li>
						<li class="forcxtab cxprepaids3">车主信息</li>
						<li class="forcxtab cxprepaids4">保险情况</li>
						<li class="forcxtab cxprepaids5">商业险</li>
					</ul>
				</div>
			</div>
			<div>
				<div class="cxtablelist cxshowdomdiv cxshowsss">
					<table class="grid">
						<tr>
							<td align="right">产险类型：</td>
							<td align="left"><select name="orderType" class="easyui-combobox" style="width: 100px">  
							    <option value="ROOTCCCL">车辆保险</option>  
							    <option value="ROOTCCQY">企业财产险</option>  
							    <option value="ROOTCCJZ">建筑工程险</option>  
							    <option value="ROOTCCGC">工程机械险</option>  
							    <option value="ROOTCCTY">团意雇主险</option>
							    <option value="ROOTCCDQ">短期意外险</option>
							    <option value="ROOTCCGZ">公众责任险</option>
							    <option value="ROOTQT">其他</option>
							</select></td>
						</tr>
						<tr>
							<td align="right">保单号：</td>
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
				<div class="cxtablelist cxhidedomdiv cxshowsss">
					<table class="grid">
						<tr>
							<td align="right">车牌号：</td>
							<td align="left" colspan="3"><input style="width: 100px" id="carNum" name="carNum" >
								<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="carCheckFun();">查询</a>
							</td>
						</tr>
						<tr>
							<td align="right">车架号：</td>
							<td align="left" colspan="3"><input style="width: 500px" id="carFrameNum" name="carFrameNum" ></td>
						</tr>
						<tr>
							<td align="right">发动机号：</td>
							<td align="left" colspan="3"><input style="width: 500px" id="engineNum" name="engineNum" ></td>
						</tr>
						<tr>
							<td align="right">厂牌类型：</td>
							<td align="left" colspan="3"><input style="width: 500px" id="changType" name="changType"  ></td>
						</tr>
						<tr>
		                    <td align="right">初登日期：</td>
		                    <td align="left"><input id="registerTime" name="registerTime" type="text" class="easyui-datebox"  style="width:100px;"></td>
		                    <td align="right">商业险起保日：</td>
		                    <td align="left"><input id="businessInsStartTime" name="businessInsStartTime" type="text" class="easyui-datebox"  style="width:100px;"></td>
		                </tr>
		                <tr>
		                    <td align="right">车辆类型：</td>
		                    <td align="left">
		                    	<select id="carType" name="carType" class="easyui-combobox" style="width:150px;" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'家庭自用车',text:'家庭自用车'},{id:'公司自用车',text:'公司自用车'},{id:'党机关用车',text:'党机关用车'},{id:'非营运货车',text:'非营运货车'},{id:'工程机械',text:'工程机械'},{id:'摩托车',text:'摩托车'},{id:'特一',text:'特一'},{id:'特二',text:'特二'},{id:'特三',text:'特三'},{id:'特四',text:'特四'},{id:'营运货车',text:'营运货车'},{id:'营运客车',text:'营运客车'}],value:'家庭自用车'">
								</select>
		                    </td>
		                    <td align="right">交强险起保日：</td>
		                    <td align="left"><input id="trafficInsStartTime" name="trafficInsStartTime"  type="text" class="easyui-datebox"  style="width:100px;"></td>
		                </tr>
						<tr>
							<td align="right">车辆备注：</td>
							<td align="left" colspan="3">
								<textarea id="carRemark" name="carRemark" rows="10" cols="70"></textarea>
							</td>
						</tr>
					</table>
				</div>
				<div class="cxtablelist cxhidedomdiv cxshowsss">
					<table class="grid">
						<tr>
							<td align="right">车主：</td>
							<td align="left"><input style="width: 200px" name="carOwner" ></td>
							<td align="right">车主证件类型：</td>
							<td align="left">
								<select name="ownerCardType" class="easyui-combobox" style="width:150px;" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'身份证',text:'身份证'},{id:'毕业证',text:'毕业证'},{id:'出生证',text:'出生证'},{id:'独生子女证',text:'独生子女证'},{id:'护照',text:'护照'},{id:'驾驶证',text:'驾驶证'},{id:'结婚证',text:'结婚证'},{id:'警官证',text:'警官证'},{id:'离婚证',text:'离婚证'},{id:'签证',text:'签证'},{id:'学生证',text:'学生证'},{id:'执行公务证',text:'执行公务证'}],value:'身份证'">
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">车主证件号：</td>
							<td align="left" colspan="3"><input style="width: 500px" name="ownerCardId" ></td>
						</tr>
						<tr>
							<td align="right">投保人：</td>
							<td align="left"><input style="width: 200px" name="policyholder" ></td>
							<td align="right">投保人证件类型：</td>
							<td align="left">
								<select name="tbrCardType" class="easyui-combobox" style="width:150px;" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'身份证',text:'身份证'},{id:'毕业证',text:'毕业证'},{id:'出生证',text:'出生证'},{id:'独生子女证',text:'独生子女证'},{id:'护照',text:'护照'},{id:'驾驶证',text:'驾驶证'},{id:'结婚证',text:'结婚证'},{id:'警官证',text:'警官证'},{id:'离婚证',text:'离婚证'},{id:'签证',text:'签证'},{id:'学生证',text:'学生证'},{id:'执行公务证',text:'执行公务证'}],value:'身份证'">
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">投保人证件号：</td>
							<td align="left" colspan="3"><input style="width: 500px" name="tbrCardId" ></td>
						</tr>
						<tr>
							<td align="right">被保险人：</td>
							<td align="left"><input style="width: 200px" name="beneficiary" ></td>
							<td align="right">被保险人证件类型：</td>
							<td align="left">
								<select name="bbrCardType" class="easyui-combobox" style="width:150px;" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'身份证',text:'身份证'},{id:'毕业证',text:'毕业证'},{id:'出生证',text:'出生证'},{id:'独生子女证',text:'独生子女证'},{id:'护照',text:'护照'},{id:'驾驶证',text:'驾驶证'},{id:'结婚证',text:'结婚证'},{id:'警官证',text:'警官证'},{id:'离婚证',text:'离婚证'},{id:'签证',text:'签证'},{id:'学生证',text:'学生证'},{id:'执行公务证',text:'执行公务证'}],value:'身份证'">
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">被保险人证件号：</td>
							<td align="left" colspan="3"><input style="width: 500px" name="bbrCardId" ></td>
						</tr>
						<tr>
							<td align="right">手机：</td>
							<td align="left"><input style="width: 200px" name="mtelphone" ></td>
							<td align="right">电话：</td>
							<td align="left"><input style="width: 200px" name="telphone" ></td>
						</tr>
						<tr>
							<td align="right">QQ：</td>
							<td align="left"><input style="width: 200px" name="qq" ></td>
							<td align="right">Mail：</td>
							<td align="left"><input style="width: 200px" name="mail" ></td>
						</tr>
						<tr>
							<td align="right">联系地址：</td>
							<td align="left" colspan="3">
								<div id="city" class="citys">
				                    <select id="province" name="province" style="width: 120px;"></select>
				                    <select id="city" name="city" style="width: 120px;"></select>
				                    <select id="area" name="district" style="width: 120px;"></select>
					            </div>
								<input style="width: 390px" placeholder="详细地址" name="linkaddr" type="text" class="easyui-validatebox span2" value="">
							</td>
						</tr>
						<tr>
							<td align="right">车主备注：</td>
							<td align="left" colspan="3">
								<textarea rows="5" cols="70" name="ownerRemark"></textarea>
							</td>
						</tr>
					</table>
				</div>
				<div class="cxtablelist cxhidedomdiv cxshowsss">
					<table class="grid">
						<tr>
							<td align="right">保险公司：</td>
							<td align="left">
							<input name="insuranceCompanyId" class="easyui-combobox" data-options="width:180, valueField: 'id', textField: 'insurerName', url: '${path}/mgr/tInsurer/list'"/>
							<input name="insuranceCompany" type="hidden" value=""> 
							</td>
							<td align="right">保险公司报案电话：</td>
							<td align="left">
							<input style="width: 180px" name="insuranceCompanyTel" ></td>
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
							<td align="right">净商业险：</td>
							<td align="left"><input style="width: 130px" class="easyui-numberbox" value="" data-options="min:0,precision:2" id="jsyx" name="jsyx" >
								<a href="javascript:void(0);" class="easyui-linkbutton"  onclick="jsyxFun();">工具</a>
							</td>
							<td align="right">商业险税：</td>
							<td align="left"><input style="width: 180px" class="easyui-numberbox" value="" data-options="min:0,precision:2" id="syxs" name="syxs" ></td>
						</tr>
						<tr>
							<td align="right">净交强险：</td>
							<td align="left"><input style="width: 130px" class="easyui-numberbox" value="" data-options="min:0,precision:2" id="jjqx" name="jjqx">
								<a href="javascript:void(0);" class="easyui-linkbutton"  onclick="jjqxFun();">工具</a>
							</td>
							<td align="right">交强税：</td>
							<td align="left"><input style="width: 180px" class="easyui-numberbox" value="" data-options="min:0,precision:2" id="jqxs" name="jqxs"></td>
						</tr>
						<tr>
							<td align="right">车船税：</td>
							<td align="left"><input style="width: 180px" name="ccs" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
							<td align="right">总保费：</td>
							<td align="left"><input style="width: 180px" name="zbf" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
						</tr>
						<tr>
							<td align="right">业务员：</td>
							<td align="left"><input id="salesManCx" name="salesMan" type="hidden" value="">
	                    	<input id="salesManNameCx" name="salesManName" value="" type="text" readonly="readonly" placeholder="请选择业务员">
	                    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="saleManForOrderCxCheckFun();">选择</a></td>
							<td align="right">佣金支付对象：</td>
							<td align="left"><select name="yjzfdx" class="easyui-combobox" style="width: 180px">  
							    <option value="01">业务员</option>  
							    <option value="02">录单员</option>  
							</select></td>
						</tr>
						<tr>
							<td align="right">商业险佣金比例：</td>
							<td align="left"><input style="width: 180px" name="syxyjbl" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
							<td align="right">商业险佣金：</td>
							<td align="left"><input style="width: 180px" name="syxyj" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
						</tr>
						<tr>
							<td align="right">综合金融奖比例：</td>
							<td align="left"><input style="width: 180px" name="zhjrjbl" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
							<td align="right">综合金融奖金额：</td>
							<td align="left"><input style="width: 180px" name="zhjrjje" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
						</tr>
						<tr>
							<td align="right">交强险佣金比例：</td>
							<td align="left"><input style="width: 180px" name="jjxyjbl" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
							<td align="right">交强险佣金：</td>
							<td align="left"><input style="width: 180px" name="jjxyj" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
						</tr>
						<tr>
							<td align="right">车船税佣金比例：</td>
							<td align="left"><input style="width: 180px" name="ccsyjbl" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
							<td align="right">车船税佣金：</td>
							<td align="left"><input style="width: 180px" name="ccsyj" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
						</tr>
						
						<tr>
							<td align="right">保险公司短期激励：</td>
							<td align="left"><input style="width: 180px" name="bxgsdqjl" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
							<td align="right">是否有礼品：</td>
							<td align="left"><input type="radio" name="giftSf" class="easyui-validatebox" value="01">否
        				        <input type="radio" name="giftSf" class="easyui-validatebox" value="02">是</td>
						</tr>
						<tr>
							<td align="right">礼品内容：</td>
							<td align="left" colspan="3"><input style="width: 500px" name="giftComment" ></td>
						</tr>
						<tr>
							<td align="right">录单员ID：</td>
							<td align="left"><input style="width: 180px; border: 0px;outline:none;" id="recorder" name="recorder"></td>
							<td align="right">录单员佣金：</td>
							<td align="left"><input style="width: 180px" name="recorderyj" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
						</tr>
					</table>
				</div>
				<div class="cxtablelist cxhidedomdiv cxshowsss">
					<table class="grid">
						<tr>
							<td align="right">机动车损失险保额：</td>
							<td align="left"><input style="width: 150px" name="jdcssxbe" class="easyui-numberbox" value="" data-options="min:0,precision:2">元</td>
							<td align="right">保费：</td>
							<td align="left"><input style="width: 150px" name="jdcssxbf" class="easyui-numberbox" value="" data-options="min:0,precision:2">元</td>
						</tr>
						<tr>
							<td align="right">第三责任险保额：</td>
							<td align="left">
							<select name="dszrxbe" class="easyui-combobox" style="width:150px;" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'0',text:'0'},{id:'5',text:'5'},{id:'10',text:'10'},{id:'20',text:'20'},{id:'30',text:'30'},{id:'40',text:'40'},{id:'50',text:'50'},{id:'60',text:'60'},{id:'70',text:'70'},{id:'80',text:'80'},{id:'90',text:'90'},{id:'100',text:'100'},{id:'150',text:'150'},{id:'200',text:'200'},{id:'300',text:'300'}],value:'0'">
							</select>元</td>
							<td align="right">保费：</td>
							<td align="left"><input style="width: 150px" name="dszrxbf" class="easyui-numberbox" value="" data-options="min:0,precision:2">元</td>
						</tr>
						<tr>
							<td align="right">全车盗抢险：</td>
							<td align="left"><input style="width: 150px" name="qcdqx" class="easyui-numberbox" value="" data-options="min:0,precision:2">元</td>
						</tr>
						<tr>
							<td align="right">司机责任险保额：</td>
							<td align="left"><select name="sjzrxbe" class="easyui-combobox" style="width:150px;" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'0',text:'0'},{id:'1',text:'1'},{id:'2',text:'2'},{id:'3',text:'3'},{id:'4',text:'4'},{id:'5',text:'5'}],value:'0'">
							</select>万元</td>
							<td align="right">保费：</td>
							<td align="left"><input style="width: 150px" name="sjzrxbf" class="easyui-numberbox" value="" data-options="min:0,precision:2">元</td>
						</tr>
						<tr>
							<td align="right">乘客责任险保额：</td>
							<td align="left"><select name="ckzrxbe" class="easyui-combobox" style="width:150px;" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'0',text:'0'},{id:'1',text:'1'},{id:'2',text:'2'},{id:'3',text:'3'},{id:'4',text:'4'},{id:'5',text:'5'}],value:'0'">
							</select>万元</td>
							<td align="right">保费：</td>
							<td align="left"><input style="width: 150px" name="ckzrxbf" class="easyui-numberbox" value="" data-options="min:0,precision:2">元</td>
						</tr>
						<tr>
							<td align="right">玻璃单独破碎险：</td>
							<td align="left"><input style="width: 150px" name="blddpsx" class="easyui-numberbox" value="" data-options="min:0,precision:2">元</td>
							<td align="right">自燃损失险：</td>
							<td align="left"><input style="width: 150px" name="zrssx" class="easyui-numberbox" value="" data-options="min:0,precision:2">元</td>
						</tr>
						<tr>
							<td align="right">车身划痕险：</td>
							<td align="left"><input style="width: 150px" name="cshhx" class="easyui-numberbox" value="" data-options="min:0,precision:2">元</td>
							<td align="right">发动车涉水损失险：</td>
							<td align="left"><input style="width: 150px" name="fdcssssx" class="easyui-numberbox" value="" data-options="min:0,precision:2">元</td>
						</tr>
						<tr>
							<td align="right">机动车损失无法找到第三方特约险：</td>
							<td align="left"><input style="width: 150px" name="jdcsstyx" class="easyui-numberbox" value="" data-options="min:0,precision:2">元</td>
							<td align="right">不计免赔：</td>
							<td align="left"><input style="width: 150px" name="bjmp" class="easyui-numberbox" value="" data-options="min:0,precision:2">元</td>
						</tr>
						<tr>
							<td align="right">备注：</td>
							<td align="left" colspan="3">
								<textarea rows="3" cols="70" name="remark"></textarea>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>
	<div>
		<span>确定保存时，发送本报价信息到业务员微信</span>
	</div>
</div>
<div id="car" style="display: none;">
	<div class="easyui-layout" data-options="fit:true,border:false">
	    <div data-options="region:'center',border:false" style="border: 0px solid red;">
	        <table id="carCheckDataGrid" data-options="fit:true,border:false"></table>
	    </div>
	    <div id="carCheckToolbar" data-options="region:'south',border:false" style="display: block; background-color: #CCCCCC; text-align: center;">
		    <shiro:hasPermission name="/orderInfo/add">
		        <a onclick="getCar();" href="javascript:void(0);" class="easyui-linkbutton">确&nbsp;&nbsp;&nbsp;&nbsp;定</a>
		    </shiro:hasPermission>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <shiro:hasPermission name="/orderInfo/add">
		        <a onclick="cancelCar();" href="javascript:void(0);" class="easyui-linkbutton">取&nbsp;&nbsp;&nbsp;&nbsp;消</a>
		    </shiro:hasPermission>
		</div>
	</div>
</div>
<div id="jsyxTool" style="display: none;">
	<table class="grid">
		<tr>
			<td align="right">总商业险：</td>
			<td align="left"><input style="width: 200px" id="zsyx" name="zsyx" onchange="syx()"></td>
		</tr>
		<tr>
			<td align="right">商业险税金比例：</td>
			<td align="left"><input style="width: 200px" id="syxsjbl" value="0.06"></td>
		</tr>
		<tr>
			<td align="right">净商业险：</td>
			<td align="left"><input style="width: 200px;border: 0px;outline: none;" value="0.00" id="jsyxStr" name="jsyxStr"></td>
		</tr>
		<tr>
			<td align="right">商业险税金金额：</td>
			<td align="left"><input style="width: 200px;border: 0px;outline: none;" value="0.00" id="syxsStr" name="syxsStr"></td>
		</tr>
	</table>
	<div id="carCheckToolbar" data-options="region:'south',border:false" style="display: block; background-color: #CCCCCC; text-align: center;">
	    <shiro:hasPermission name="/orderInfo/add">
	        <a onclick="getJsyx();" href="javascript:void(0);" class="easyui-linkbutton">确&nbsp;&nbsp;&nbsp;&nbsp;定</a>
	    </shiro:hasPermission>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    <shiro:hasPermission name="/orderInfo/add">
	        <a onclick="cancelJsyx();" href="javascript:void(0);" class="easyui-linkbutton">取&nbsp;&nbsp;&nbsp;&nbsp;消</a>
	    </shiro:hasPermission>
	</div>
</div>
<div id="jjqxTool" style="display: none;">
	<table class="grid">
		<tr>
			<td align="right">总交强险：</td>
			<td align="left"><input style="width: 200px" id="zjqStr" name="zjqStr" onchange="jqx()"></td>
		</tr>
		<tr>
			<td align="right">交强险税金比例：</td>
			<td align="left"><input style="width: 200px" id="jqsjbl" value="0.06"></td>
		</tr>
		<tr>
			<td align="right">净交强险：</td>
			<td align="left"><input style="width: 200px;border: 0px;outline: none;" value="0.00" id="jjqxStr" name="jjqxStr"></td>
		</tr>
		<tr>
			<td align="right">交强险税金金额：</td>
			<td align="left"><input style="width: 200px;border: 0px;outline: none;" value="0.00" id="jqxsStr" name="jqxsStr"></td>
		</tr>
	</table>
		<div id="carCheckToolbar" data-options="region:'south',border:false" style="display: block; background-color: #CCCCCC; text-align: center;">
	    <shiro:hasPermission name="/orderInfo/add">
	        <a onclick="getJjqx();" href="javascript:void(0);" class="easyui-linkbutton">确&nbsp;&nbsp;&nbsp;&nbsp;定</a>
	    </shiro:hasPermission>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    <shiro:hasPermission name="/orderInfo/add">
	        <a onclick="cancelJjqx();" href="javascript:void(0);" class="easyui-linkbutton">取&nbsp;&nbsp;&nbsp;&nbsp;消</a>
	    </shiro:hasPermission>
	</div>
</div>
<div id="saleManForOrderCx" style="display: none;">
	<div class="easyui-layout" data-options="fit:true,border:false">
	    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden; background-color: #fff">
	        <form id="saleManForOrderCxSearchForm">
	            <table>
	                <tr>
	                    <th>关键字搜索</th>
	                    <td>
	                    	<select name="name" class="easyui-combobox"
								style="width: 100px">
									<option value="userName">姓名</option>
									<option value="mtelphone">手机</option>
									<option value="wechat">微信</option>
							</select>
	                    </td>
						<td><input name="val" style="width: 200px" placeholder="产品名称查询" /></td>
	                    <td>
	                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="saleManForOrderCxSearchFun();">查询</a>
	                    </td>
	                </tr>
	            </table>
	        </form>
	     </div>	 
	     
	    <div data-options="region:'center',border:false" style="border: 0px solid red;">
	        <table id="saleManForOrderCxDataGrid" data-options="fit:true,border:false"></table>
	    </div>
	    <div id="saleManForOrderCxToolbar" data-options="region:'south',border:false" style="display: block; background-color: #CCCCCC; text-align: center;">
		    <shiro:hasPermission name="/orderInfo/add">
		        <a onclick="getSaleManForOrderCx();" href="javascript:void(0);" class="easyui-linkbutton">确&nbsp;&nbsp;&nbsp;&nbsp;定</a>
		    </shiro:hasPermission>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <shiro:hasPermission name="/orderInfo/add">
		        <a onclick="cancelSaleManForOrderCx();" href="javascript:void(0);" class="easyui-linkbutton">取&nbsp;&nbsp;&nbsp;&nbsp;消</a>
		    </shiro:hasPermission>
		</div>
	</div>
</div>