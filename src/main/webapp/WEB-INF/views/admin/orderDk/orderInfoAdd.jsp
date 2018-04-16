<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/head.jsp" %>
<script type='text/javascript' src='${path }/static/distpicker/distpicker.data.js'></script> 
<script type='text/javascript' src='${path }/static/distpicker/distpicker.js'></script> 
<script type='text/javascript' src='${path }/static/distpicker/main.js'></script> 
<script type="text/javascript">
    $(function() {
        $('#orderInfoDKAddForm').form({
            url : '${path}/mgr/orderInfo/add',
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
                    var form = $('#orderInfoDKAddForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
    });
    
    
    function customerCheckFun() {
    	$('#customerCheckLDKataGrid').datagrid({
            url : '${path}/mgr/tCustomerBasicinfo/dataGrid',
            queryParams: {          
            	userName: $('#userName').val(),
            	telephone:$('#telephone').val(),
            	cardId:$('#cardId').val()
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
	    $("#customer").show();//必须先显示，再弹出
	    $("#customer").dialog({
	        title: "产品选择",
	        width: 400,
	        height: 300
	    });
    }
    function getCustomer(id){
        var rows = $('#customerCheckLDKataGrid').datagrid('getSelections');
        var productName;
 	   if(rows.length==0){
 		   alert('请选择一驾车辆！');
 	   }else{
 		  	$('#carNum').val(rows[0].carNum);
 		 	$('#carFrameNum').val(rows[0].carFrameNum);
 		 	$('#carType').combobox('setValue', rows[0].carType);
	 		$('#engineNum').val(rows[0].engineNum);
	 		$('#changType').val(rows[0].changType);
	 		$('#trafficInsStartTime').datebox('setValue', rows[0].trafficInsStartTime);	
	 		$('#businessInsStartTime').datebox('setValue', rows[0].businessInsStartTime);	
	 		$('#registerTime').datebox('setValue', rows[0].registerTime);	
 	   }
 	   $('#customerCheckLDKataGrid').datagrid('clearSelections');
 	   $("#customer").css("display", "none"); 
 	   $("#customer").dialog('close');
     }
    function cancelCustomer(){
    	$('#customerCheckLDKataGrid').datagrid('clearSelections');
 	   $("#customer").css("display", "none"); 
 	   $("#customer").dialog('close');
     }
    
    function productDkCheckFun (){
    	$('#productCheckDKDataGrid').datagrid({
            url : '${path}/mgr/tProductManage/dataGrid?type=ROOTDK',
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : false,
    		selectOnCheck: true,
    		checkOnSelect: true,
            idField : 'productId',
            sortName : 'productId',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            frozenColumns : [ [ {
            	field:'productId',
    			checkbox:true 
            },{
                width : '200',
                title : '商品名称',
                field : 'productName',
                sortable : true,
                align : 'center'
            },{
                width : '80',
                title : '目标年化收益',
                field : 'lGoalIncome',
                sortable : true,
                align : 'center'
            }, {
                width : '80',
                title : '投资期限（天）',
                field : 'lLimit',
                sortable : true,
                align : 'center'
            }, {
                width : '60',
                title : '风险等级',
                field : 'lRiskGrade',
                sortable : true,
                align : 'center'
            }, {
                width : '100',
                title : '起购金额',
                field : 'lMixBuyMoney',
                sortable : true,
                align : 'center'
            } ] ],
            onLoadSuccess:function(data){
                $('.orderInfo-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.orderInfo-easyui-linkbutton-del').linkbutton({text:'删除'});
            }
        });
    	$("#productDk").show();//必须先显示，再弹出
	    $("#productDk").dialog({
	        title: "产品选择",
	        width: 600,
	        height: 400
	    });
    }
   
    
   function productDkSearchFun() {
        $('#productCheckDKDataGrid').datagrid('load', $.serializeObject($('#productDkSearchForm')));
   }
   function getProductDkId(id){
      var rows = $('#productCheckDKDataGrid').datagrid('getSelections');
      var productName;
	   if(rows.length==0){
		   alert('请选择一个产品！');
	   }else{
		   id = rows[0].productId;
		   productName = rows[0].productName;
	   }
	   $('#productId').val(id);
	   $('#productName').val(productName);
	   $('#productCheckDKDataGrid').datagrid('clearSelections');
	   $("#productDk").css("display", "none"); 
	   $("#productDk").dialog('close');
   }
   function cancelProductDk(){
	   $('#productCheckDKDataGrid').datagrid('clearSelections');
 	   $("#productDk").css("display", "none"); 
 	   $("#productDk").dialog('close');
     }
   
   function saleManDkCheckFun (){
   	$('#saleManDKDataGrid').datagrid({
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
   	$("#saleMan").show();//必须先显示，再弹出
    $("#saleMan").dialog({
        title: "业务员选择",
        width: 600,
        height: 400
    });
   }
  
  function saleManSearchFun() {
       $('#saleManDKDataGrid').datagrid('load', $.serializeObject($('#saleManDkSearchForm')));
  }
  function getSaleMan(id){
     var rows = $('#saleManDKDataGrid').datagrid('getSelections');
	   if(rows.length==0){
		   alert('请选择一个业务员！');
	   }else{
		   $('#salesMan').val(rows[0].userId);
		   $('#salesManName').val(rows[0].userName);
	   }
	   $('#saleManDKDataGrid').datagrid('clearSelections'); 
	   $("#saleMan").css("display", "none"); 
	   $("#saleMan").dialog('close');
  }
  function cancelSaleMan(){
	  $('#saleManDKDataGrid').datagrid('clearSelections'); 
	   $("#saleMan").css("display", "none"); 
	   $("#saleMan").dialog('close');
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="orderInfoDKAddForm" method="post">
            <div class="order_details">
				<ul>
					<li class="fortab prepaids checked">基本信息</li>
					<li class="fortab prepaids2">产品信息</li>
					<li class="fortab prepaids3">佣金信息</li>
				</ul>
			</div>
			<div>
				<div class="tablelist showdomdiv showsss">
				<table class="grid">
	                <tr>
	                    <td align="right">用户姓名：</td>
	                    <td align="left"><input id="productId" name="productId" value="" type="hidden">
	                    	<input name="orderType" value="ROOTDK" type="hidden">
	                    	<input id="userName" name="userName" type="text" class="easyui-validatebox span2" value=""></td>
	                    <td align="right">联系电话：</td>
	                    <td align="left"><input id="telephone" name="telephone" type="text" class="easyui-validatebox span2" value=""></td>
	                </tr> 
	               <tr>
	                    <td align="right">证件类型：</td>
	                    <td align="left">
							<select name="cardType" class="easyui-combobox" style="width:150px;" 
							data-options="editable:false,valueField:'id',textField:'text',data:[{id:'身份证',text:'身份证'},{id:'毕业证',text:'毕业证'},{id:'出生证',text:'出生证'},{id:'独生子女证',text:'独生子女证'},{id:'护照',text:'护照'},{id:'驾驶证',text:'驾驶证'},{id:'结婚证',text:'结婚证'},{id:'警官证',text:'警官证'},{id:'离婚证',text:'离婚证'},{id:'签证',text:'签证'},{id:'学生证',text:'学生证'},{id:'执行公务证',text:'执行公务证'}],value:'身份证'">
							</select>
	                    </td>
	                </tr>
	                <tr>
	                    <td align="right">证件号码：</td>
	                    <td align="left" colspan="3"><input style="width: 390px" id="cardId" name="cardId" type="text" placeholder="请输入证件号码" class="easyui-validatebox span2" value=""></td>
	                </tr>
	                <tr>
	                    <td align="right">婚姻状况：</td>
	                    <td align="left">
	                    	<select name="marryFlag" class="easyui-combobox" style="width:150px;" 
							data-options="editable:false,valueField:'id',textField:'text',data:[{id:'0',text:'未婚'},{id:'1',text:'已婚'},{id:'2',text:'离异'}],value:'0'">
							</select>
						</td>
	                    <td align="right">有无社保：</td>
	                    <td align="left">
							<select name="socialFlag" class="easyui-combobox" style="width:150px;" 
							data-options="editable:false,valueField:'id',textField:'text',data:[{id:'0',text:'无'},{id:'1',text:'有'}],value:'0'">
							</select>
						</td>
	                </tr>
	                <tr>
	                    <td align="right">有无房产：</td>
	                    <td align="left">
	                    	<select name="houseFlag" class="easyui-combobox" style="width:150px;" 
							data-options="editable:false,valueField:'id',textField:'text',data:[{id:'0',text:'无'},{id:'1',text:'按揭'},{id:'2',text:'全款'}],value:'0'">
							</select>
						</td>
	                </tr>
	                <tr>
		                <td align="right">房产区域：</td>
	                    <td colspan="3" align="left">
	                    	<div data-toggle="distpicker">
								<select data-province="" name="province"></select>
								<select data-city="" name="city"></select>
								<select data-district="" name="district"></select>
							</div>
	                    </td>
	                </tr>
	                <tr>
	                    <td align="right">每月收入：</td>
	                    <td align="left">
							<select name="income" class="easyui-combobox" style="width:150px;" 
							data-options="editable:false,valueField:'id',textField:'text',data:[{id:'1',text:'1000-3000'},{id:'2',text:'3000-6000'},{id:'3',text:'6000-10000'},{id:'4',text:'10000-30000'},{id:'5',text:'3万以上'}],value:'1'">
							</select>
						</td>
	                    <td align="right">工作性质：</td>
	                    <td align="left">
	                    	<select name="workType" class="easyui-combobox" style="width:150px;" 
							data-options="editable:false,valueField:'id',textField:'text',data:[{id:'01',text:'民企'},{id:'02',text:'国企'},{id:'03',text:'公务员'},{id:'04',text:'自由职业'}],value:'01'">
							</select>
						</td>
	                </tr>
	                <tr>
	                    <td align="right">有无车辆：</td>
	                    <td align="left">
							<select name="carlag" class="easyui-combobox" style="width:150px;" 
							data-options="editable:false,valueField:'id',textField:'text',data:[{id:'0',text:'无'},{id:'1',text:'按揭'},{id:'2',text:'全款'}],value:'0'">
							</select>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="customerCheckFun();">查询</a>
						</td>
	                </tr>
	                <tr>
	                    <td align="right">车牌号：</td>
	                    <td align="left"><input id="carNum" name="carNum" type="text" class="easyui-validatebox span2" value=""></td>
	                </tr>
	                <tr>
	                    <td align="right">车架号：</td>
	                    <td colspan="3" align="left"><input style="width: 350px" id="carFrameNum" name="carFrameNum" type="text" class="easyui-validatebox span2" value=""></td>
	                </tr>
	                <tr>
	                    <td align="right">发动机号：</td>
	                    <td colspan="3" align="left"><input style="width: 390px" id="engineNum" name="engineNum" type="text" class="easyui-validatebox span2" value=""></td>
	                </tr>
	                <tr>
	                    <td align="right">厂牌类型：</td>
	                    <td colspan="3" align="left"><input style="width: 390px" id="changType" name="changType" type="text" class="easyui-validatebox span2" value=""></td>
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
	            </table>
				</div>
				<div class="tablelist hidedomdiv showsss">
				<table class="grid">
	                <tr>
	                    <td align="right">贷款产品：</td>
	                    <td align="left">
	                    	<input id="productName" name="productName" value="" type="text" readonly="readonly" placeholder="请选择产品">
	                    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="productDkCheckFun();">选择</a>
	                    </td>
	                    <td align="right">贷款金额：</td>
	                    <td align="left"><input name="orderMoney" type="text" class="easyui-validatebox span2" value=""></td>
	                </tr> 
	                <tr>
	                    <td align="right">生效时间：</td>
	                    <td align="left"><input name="beginDate"  class="easyui-datebox"  style="width:150px;" value=""></td>
	                </tr>
	                <tr>
	                    <td align="right">备注</td>
	                    <td colspan="3" align="left"><textarea name="remark3" style="width: 390px; height: 100px;"></textarea></td>
	                </tr>
	            </table>
				</div>
				<div class="tablelist hidedomdiv showsss" >
				<table class="grid">
	                <tr>
	                    <td align="right">业务员：</td>
	                    <td align="left"><input id="salesMan" name="salesMan" type="hidden" value="">
	                    	<input id="salesManName" name="salesManName" value="" type="text" readonly="readonly" placeholder="请选择业务员">
	                    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="saleManDkCheckFun();">选择</a>
	                    </td>
	                    <td align="right">佣金支付对象：</td>
	                    <td align="left"><select name="commissionFor" class="easyui-combobox" style="width: 200px">  
							    <option value="01">业务员</option>  
							    <option value="02">录单员</option>  
							</select></td>
	                </tr>
	                <tr>
	                    <td align="right">佣金比例：</td>
	                    <td align="left"><input name="commissionRate" type="text" class="easyui-validatebox span2" value=""></td>
	                    <td align="right">佣金：</td>
	                    <td align="left"><input name="commission" type="text" class="easyui-validatebox span2" value=""></td>
	                </tr>
	                <tr>
	                    <td align="right">录单员ID：</td>
	                    <td align="left"><input name="recorder" type="text" class="easyui-validatebox span2" value=""></td>
	                    <td align="right">录单员佣金：</td>
	                    <td align="left"><input name="recorderCommission" type="text" class="easyui-validatebox span2" value=""></td>
	                </tr> 
	            </table>
				</div>
			</div>
        </form>
    </div>
</div>
<div id="customer" style="display: none;">
	<div class="easyui-layout" data-options="fit:true,border:false">
	    <div data-options="region:'center',border:false" style="border: 0px solid red;">
	        <table id="customerCheckLDKataGrid" data-options="fit:true,border:false"></table>
	    </div>
	    <div id="customerCheckLCToolbar" data-options="region:'south',border:false" style="display: block; background-color: #CCCCCC; text-align: center;">
		    <shiro:hasPermission name="/orderInfo/add">
		        <a onclick="getCustomer();" href="javascript:void(0);" class="easyui-linkbutton">确&nbsp;&nbsp;&nbsp;&nbsp;定</a>
		    </shiro:hasPermission>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <shiro:hasPermission name="/orderInfo/add">
		        <a onclick="cancelCustomer();" href="javascript:void(0);" class="easyui-linkbutton">取&nbsp;&nbsp;&nbsp;&nbsp;消</a>
		    </shiro:hasPermission>
		</div>
	</div>
</div>
<div id="productDk" style="display: none;">
	<div class="easyui-layout" data-options="fit:true,border:false">
	    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden; background-color: #fff">
	        <form id="productDkSearchForm">
	            <table>
	                <tr>
	                    <th>关键字搜索</th>
						<td><input name="productName" style="width: 200px" placeholder="产品名称查询" /></td>
	                    <td>
	                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="productDkSearchFun();">查询</a>
	                    </td>
	                </tr>
	            </table>
	        </form>
	     </div>	 
	     
	    <div data-options="region:'center',border:false" style="border: 0px solid red;">
	        <table id="productCheckDKDataGrid" data-options="fit:true,border:false"></table>
	    </div>
	    <div id="productCheckDKToolbar" data-options="region:'south',border:false" style="display: block; background-color: #CCCCCC; text-align: center;">
		    <shiro:hasPermission name="/orderInfo/add">
		        <a onclick="getProductDkId();" href="javascript:void(0);" class="easyui-linkbutton">确&nbsp;&nbsp;&nbsp;&nbsp;定</a>
		    </shiro:hasPermission>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <shiro:hasPermission name="/orderInfo/add">
		        <a onclick="cancelProductDk()();" href="javascript:void(0);" class="easyui-linkbutton">取&nbsp;&nbsp;&nbsp;&nbsp;消</a>
		    </shiro:hasPermission>
		</div>
	</div>
</div>
<div id="saleMan" style="display: none;">
	<div class="easyui-layout" data-options="fit:true,border:false">
	    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden; background-color: #fff">
	        <form id="saleManDkSearchForm">
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
	                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="saleManSearchFun();">查询</a>
	                    </td>
	                </tr>
	            </table>
	        </form>
	     </div>	 
	     
	    <div data-options="region:'center',border:false" style="border: 0px solid red;">
	        <table id="saleManDKDataGrid" data-options="fit:true,border:false"></table>
	    </div>
	    <div id="saleManDKToolbar" data-options="region:'south',border:false" style="display: block; background-color: #CCCCCC; text-align: center;">
		    <shiro:hasPermission name="/orderInfo/add">
		        <a onclick="getSaleMan();" href="javascript:void(0);" class="easyui-linkbutton">确&nbsp;&nbsp;&nbsp;&nbsp;定</a>
		    </shiro:hasPermission>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <shiro:hasPermission name="/orderInfo/add">
		        <a onclick="cancelSaleMan();" href="javascript:void(0);" class="easyui-linkbutton">取&nbsp;&nbsp;&nbsp;&nbsp;消</a>
		    </shiro:hasPermission>
		</div>
	</div>
</div>