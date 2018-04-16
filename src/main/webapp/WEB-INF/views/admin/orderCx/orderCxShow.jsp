<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/head.jsp"%>
<script type='text/javascript' src='${path }/static/jquery.citys.js'></script>  
<script type="text/javascript">
    $(function() {
    	$('#city').citys({code:'${salesMan.district}'});
        $("#orderType").val('${orderCx.orderType}');
        $("input[name='orderFlag'][value=${orderCx.orderFlag}]").attr("checked",true); 
        $("input[name='giftFlag'][value=${orderCx.giftFlag}]").attr("checked",true); 
        $("input[name='printFlag'][value=${orderCx.printFlag}]").attr("checked",true); 
        $("input[name='checkoutFlag'][value=${orderCx.checkoutFlag}]").attr("checked",true); 
        $("#yjzfdx").val('${orderCx.yjzfdx}');
        $("input[name='giftSf'][value=${orderCx.giftSf}]").attr("checked",true); 
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: auto;padding: 3px;">
        <form id="orderCxEditForm" method="post">
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
		                    <td align="right" width="150px">ID：</td>
		                    <td align="left" colspan="3">${orderCx.orderId}</td>
		                    
		                </tr>
						<tr>
							<td align="right">产险类型：</td>
							<td align="left">${orderCx.orderTypeName}</td>
							<td align="right">下单日期：</td>
		                    <td align="left">${orderCx.createTime}</td>
						</tr>
						<tr>
							<td align="right">保单号：</td>
							<td align="left" colspan="3">${orderCx.orderNo}</td>
						</tr>
						<tr>
		                    <td align="right">最后修改时间：</td>
		                    <td align="left">${orderCx.updateTime}</td>
		                    <td align="right">最后修改人：</td>
		                    <td align="left">${orderCx.updateUser}</td>
		                </tr>
						<tr>
							<td align="right">是否成交：</td>
							<td align="left">${orderCx.orderFlag==01?'否':'是'}</td>
							<td align="right">礼品是否发放：</td>
							<td align="left">${orderCx.giftFlag==01?'否':'是'}</td>
						</tr>
						<tr>
							<td align="right">是否打单：</td>
							<td align="left">${orderCx.printFlag==01?'否':'是'}</td>
        				     <td align="right">保险公司是否结账：</td>
							<td align="left">${orderCx.checkoutFlag==01?'否':'是'}</td>
						</tr>
						<tr>
		                    <td align="right">打单人：</td>
		                    <td align="left">${orderCx.ddr}</td>
		                    <td align="right">打单时间：</td>
		                    <td align="left">${orderCx.ddDate}</td>
		                </tr>
		                <tr>
		                    <td align="right">保险公司税前佣金比例：</td>
		                    <td align="left">${orderCx.bxgssqyjbl}</td>
		                    <td align="right">保险公司税前佣金：</td>
		                    <td align="left">${orderCx.bxgssqyj}</td>
		                </tr>
		                <tr>
		                    <td align="right">保险公司开票佣金比例：</td>
		                    <td align="left">${orderCx.bxgskpyjbl}</td>
		                    <td align="right">保险公司开票佣金：</td>
		                    <td align="left">${orderCx.bxgskpyj}</td>
		                </tr>
		                <tr>
		                    <td align="right">公司管理费比例：</td>
		                    <td align="left">${orderCx.gsglfbl}</td>
		                    <td align="right">公司管理费金额：</td>
		                    <td align="left">${orderCx.gsglfje}</td>
		                </tr>
		                <tr>
		                    <td align="right">公司交税比例：</td>
		                    <td align="left">${orderCx.gsjsbl}</td>
		                    <td align="right">公司交税金额：</td>
		                    <td align="left">${orderCx.gsjsje}</td>
		                </tr>
		                <tr>
		                    <td align="right">公司提成比例：</td>
		                    <td align="left">${orderCx.gstcbl}</td>
		                    <td align="right">公司提成金额：</td>
		                    <td align="left">${orderCx.gstcje}</td>
		                </tr>
		                <tr>
		                    <td align="right">本公司毛利：</td>
		                    <td align="left">${orderCx.bgsml}</td>
		                </tr>
					</table>
				</div>
				<div class="cxtablelist cxhidedomdiv cxshowsss">
					<table class="grid">
						<tr>
							<td align="right">车牌号：</td>
							<td align="left" colspan="3">${orderCx.carNum}</td>
						</tr>
						<tr>
							<td align="right">车架号：</td>
							<td align="left" colspan="3">${orderCx.carFrameNum}</td>
						</tr>
						<tr>
							<td align="right">发动机号：</td>
							<td align="left" colspan="3">${orderCx.engineNum}</td>
						</tr>
						<tr>
							<td align="right">厂牌类型：</td>
							<td align="left" colspan="3">${orderCx.changType}</td>
						</tr>
						<tr>
		                    <td align="right">初登日期：</td>
		                    <td align="left">${orderCx.registerTime}</td>
		                    <td align="right">年审到期日：</td>
		                    <td align="left">${orderCx.examineTime}</td>
		                </tr>
		                <tr>
		                	<td align="right">商业险起保日：</td>
		                    <td align="left">${orderCx.businessInsStartTime}</td>
		                	<td align="right">交强险起保日：</td>
		                    <td align="left">${orderCx.trafficInsStartTime}</td>
		                </tr>
		                <tr>
		                    <td align="right">车辆类型：</td>
		                    <td align="left">
		                    	<select id="carType" name="carType" class="easyui-combobox" style="width:150px;border: 0px;outline:none;" readonly="readonly" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'家庭自用车',text:'家庭自用车'},{id:'公司自用车',text:'公司自用车'},{id:'党机关用车',text:'党机关用车'},{id:'非营运货车',text:'非营运货车'},{id:'工程机械',text:'工程机械'},{id:'摩托车',text:'摩托车'},{id:'特一',text:'特一'},{id:'特二',text:'特二'},{id:'特三',text:'特三'},{id:'特四',text:'特四'},{id:'营运货车',text:'营运货车'},{id:'营运客车',text:'营运客车'}],value:'${orderCx.carType}'">
								</select>
		                    </td>
		                    
		                </tr>
						<tr>
							<td align="right">车辆备注：</td>
							<td align="left" colspan="3">
								<textarea id="carRemark" name="carRemark" rows="10" cols="70" style="border: 0px;outline:none;" readonly="readonly">${orderCx.carRemark}</textarea>
							</td>
						</tr>
					</table>
				</div>
				<div class="cxtablelist cxhidedomdiv cxshowsss">
					<table class="grid">
						<tr>
							<td align="right">车主：</td>
							<td align="left">${orderCx.carOwner}</td>
							<td align="right">车主证件类型：</td>
							<td align="left">
								<select name="ownerCardType" class="easyui-combobox" style="width:150px;;border: 0px;outline:none;" readonly="readonly" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'身份证',text:'身份证'},{id:'毕业证',text:'毕业证'},{id:'出生证',text:'出生证'},{id:'独生子女证',text:'独生子女证'},{id:'护照',text:'护照'},{id:'驾驶证',text:'驾驶证'},{id:'结婚证',text:'结婚证'},{id:'警官证',text:'警官证'},{id:'离婚证',text:'离婚证'},{id:'签证',text:'签证'},{id:'学生证',text:'学生证'},{id:'执行公务证',text:'执行公务证'}],value:'${orderCx.ownerCardType}'">
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">车主证件号：</td>
							<td align="left" colspan="3">${orderCx.ownerCardId}</td>
						</tr>
						<tr>
							<td align="right">投保人：</td>
							<td align="left">${orderCx.policyholder}</td>
							<td align="right">投保人证件类型：</td>
							<td align="left">
								<select name="tbrCardType" class="easyui-combobox" style="width:150px;border: 0px;outline:none;" readonly="readonly" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'身份证',text:'身份证'},{id:'毕业证',text:'毕业证'},{id:'出生证',text:'出生证'},{id:'独生子女证',text:'独生子女证'},{id:'护照',text:'护照'},{id:'驾驶证',text:'驾驶证'},{id:'结婚证',text:'结婚证'},{id:'警官证',text:'警官证'},{id:'离婚证',text:'离婚证'},{id:'签证',text:'签证'},{id:'学生证',text:'学生证'},{id:'执行公务证',text:'执行公务证'}],value:'${orderCx.tbrCardType}'">
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">投保人证件号：</td>
							<td align="left" colspan="3">${orderCx.tbrCardId}</td>
						</tr>
						<tr>
							<td align="right">被保险人：</td>
							<td align="left">${orderCx.beneficiary}</td>
							<td align="right">被保险人证件类型：</td>
							<td align="left">
								<select name="bbrCardType" class="easyui-combobox" style="width:150px;border: 0px;outline:none;" readonly="readonly" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'身份证',text:'身份证'},{id:'毕业证',text:'毕业证'},{id:'出生证',text:'出生证'},{id:'独生子女证',text:'独生子女证'},{id:'护照',text:'护照'},{id:'驾驶证',text:'驾驶证'},{id:'结婚证',text:'结婚证'},{id:'警官证',text:'警官证'},{id:'离婚证',text:'离婚证'},{id:'签证',text:'签证'},{id:'学生证',text:'学生证'},{id:'执行公务证',text:'执行公务证'}],value:'${orderCx.bbrCardType}'">
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">被保险人证件号：</td>
							<td align="left" colspan="3">${orderCx.bbrCardId}</td>
						</tr>
						<tr>
							<td align="right">手机：</td>
							<td align="left">${orderCx.mtelphone}</td>
							<td align="right">电话：</td>
							<td align="left">${orderCx.telphone}</td>
						</tr>
						<tr>
							<td align="right">QQ：</td>
							<td align="left">${orderCx.qq}</td>
							<td align="right">Mail：</td>
							<td align="left">${orderCx.mail}</td>
						</tr>
						<tr>
							<td align="right">联系地址：</td>
							<td align="left" colspan="3">
								<div id="city" class="citys">
				                    <select id="province" name="province" disabled="disabled"></select>
				                    <select id="city" name="city" disabled="disabled"></select>
				                    <select id="area" name="district" disabled="disabled"></select>
								</div>
								<input style="width: 390px;border: 0px;outline:none;" readonly="readonly" placeholder="详细地址" name="linkaddr" type="text" class="easyui-validatebox span2" value="${orderCx.linkaddr}">
							</td>
						</tr>
						<tr>
							<td align="right">车主备注：</td>
							<td align="left" colspan="3">
								<textarea rows="5" cols="70" name="ownerRemark" style="border: 0px;outline:none;" readonly="readonly">${orderCx.ownerRemark}</textarea>
							</td>
						</tr>
					</table>
				</div>
				<div class="cxtablelist cxhidedomdiv cxshowsss">
					<table class="grid">
						<tr>
							<td align="right">保险公司：</td>
							<td align="left">${orderCx.insuranceCompany}</td>
							<td align="right">保险公司报案电话：</td>
							<td align="left">${orderCx.insuranceCompanyTel}</td>
						</tr>
						<tr>
							<td align="right">出单渠道：</td>
							<td align="left">
								<input name="operator" class="easyui-combobox" data-options="width:180, valueField: 'id', textField: 'name', url: '${path}/mgr/fuser/list',value:'${orderCx.operator}'"/>
							</td>
							<td align="right">投保方式：</td>
							<td align="left"><input name="tbfs" class="easyui-combobox" data-options="width:180, valueField: 'id', textField: 'insurerName', url: '${path}/mgr/tInsurer/list',value:'${orderCx.tbfs}'"/></td>
						</tr>
						<tr>
							<td align="right">净商业险：</td>
							<td align="left">${orderCx.jsyx}</td>
							<td align="right">商业险税：</td>
							<td align="left">${orderCx.syxs}</td>
						</tr>
						<tr>
							<td align="right">总商业险：</td>
							<td align="left">${orderCx.zsyx}</td>
						</tr>
						<tr>
							<td align="right">净交强险：</td>
							<td align="left">${orderCx.jjqx}</td>
							<td align="right">交强税：</td>
							<td align="left">${orderCx.jqxs}</td>
						</tr>
						<tr>
							<td align="right">总交强险：</td>
							<td align="left">${orderCx.zjq}</td>
						</tr>
						<tr>
							<td align="right">车船税：</td>
							<td align="left">${orderCx.ccs}</td>
							<td align="right">总保费：</td>
							<td align="left">${orderCx.zbf}</td>
						</tr>
						<tr>
							<td align="right">业务员：</td>
							<td align="left">${orderCx.salesManName}</td>
							<td align="right">佣金支付对象：</td>
							<td align="left">${orderCx.yjzfdx==01?'业务员':'录单员'}</td>
						</tr>
						<tr>
							<td align="right">商业险佣金比例：</td>
							<td align="left">${orderCx.syxyjbl}</td>
							<td align="right">商业险佣金：</td>
							<td align="left">${orderCx.syxyj}</td>
						</tr>
						<tr>
							<td align="right">综合金融奖比例：</td>
							<td align="left">${orderCx.zhjrjbl}</td>
							<td align="right">综合金融奖金额：</td>
							<td align="left">${orderCx.zhjrjje}</td>
						</tr>
						<tr>
							<td align="right">交强险佣金比例：</td>
							<td align="left">${orderCx.jjxyjbl}</td>
							<td align="right">交强险佣金：</td>
							<td align="left">${orderCx.jjxyj}</td>
						</tr>
						<tr>
							<td align="right">车船税佣金比例：</td>
							<td align="left">${orderCx.ccsyjbl}</td>
							<td align="right">车船税佣金：</td>
							<td align="left">${orderCx.ccsyj}</td>
						</tr>
						<tr>
							<td align="right">保险公司短期激励：</td>
							<td align="left">${orderCx.bxgsdqjl}</td>
							<td align="right">是否有礼品：</td>
							<td align="left">${orderCx.giftSf==01?'否':'是'}</td>
						</tr>
						<tr>
							<td align="right">礼品内容：</td>
							<td align="left" colspan="3">${orderCx.giftComment}</td>
						</tr>
						<tr>
							<td align="right">业务员扣佣金：</td>
							<td align="left">${orderCx.bxgsdqjl}</td>
							<td align="right">实际支付佣金：</td>
							<td align="left">${orderCx.sjzfyj}</td>
						</tr>
						<tr>
							<td align="right">佣金支付时间：</td>
							<td align="left">${orderCx.bxgsdqjl}</td>
							<td align="right">佣金支付人：</td>
							<td align="left">${orderCx.giftSf==01?'否':'是'}</td>
						</tr>
						<tr>
							<td align="right">业务员手机：</td>
							<td align="left">${orderCx.salesTelphone}</td>
							<td align="right">业务员微信：</td>
							<td align="left">${orderCx.wechat}</td>
						</tr>
						<tr>
							<td align="right">业务员开户行：</td>
							<td align="left">${orderCx.bankName}</td>
							<td align="right">业务员开户名称：</td>
							<td align="left">${orderCx.bankUserName}</td>
						</tr>
						<tr>
							<td align="right">业务员银行卡号：</td>
							<td align="left">${orderCx.bankNo}</td>
						</tr>
						<tr>
							<td align="right">业务员地址：</td>
							<td align="left">${orderCx.salesLinkaddr}</td>
						</tr>
						<tr>
							<td align="right">录单员ID：</td>
							<td align="left">${orderCx.recorder}</td>
							<td align="right">录单员佣金：</td>
							<td align="left">${orderCx.recorderyj}</td>
						</tr>
					</table>
				</div>
				<div class="cxtablelist cxhidedomdiv cxshowsss">
					<table class="grid">
						<tr>
							<td align="right">机动车损失险保额：</td>
							<td align="left">${orderCx.jdcssxbe}元</td>
							<td align="right">保费：</td>
							<td align="left">${orderCx.jdcssxbf}元</td>
						</tr>
						<tr>
							<td align="right">第三责任险保额：</td>
							<td align="left">${orderCx.dszrxbe}元</td>
							<td align="right">保费：</td>
							<td align="left">${orderCx.dszrxbf}元</td>
						</tr>
						<tr>
							<td align="right">全车盗抢险：</td>
							<td align="left">${orderCx.qcdqx}元</td>
						</tr>
						<tr>
							<td align="right">司机责任险保额：</td>
							<td align="left">${orderCx.sjzrxbe}万元</td>
							<td align="right">保费：</td>
							<td align="left">${orderCx.sjzrxbf}元</td>
						</tr>
						<tr>
							<td align="right">乘客责任险保额：</td>
							<td align="left">${orderCx.ckzrxbe}万元</td>
							<td align="right">保费：</td>
							<td align="left">${orderCx.ckzrxbf}元</td>
						</tr>
						<tr>
							<td align="right">玻璃单独破碎险：</td>
							<td align="left">${orderCx.blddpsx}元</td>
							<td align="right">自燃损失险：</td>
							<td align="left">${orderCx.zrssx}元</td>
						</tr>
						<tr>
							<td align="right">车身划痕险：</td>
							<td align="left">${orderCx.cshhx}元</td>
							<td align="right">发动车涉水损失险：</td>
							<td align="left">${orderCx.fdcssssx}元</td>
						</tr>
						<tr>
							<td align="right">机动车损失无法找到第三方特约险：</td>
							<td align="left">${orderCx.jdcsstyx}元</td>
							<td align="right">不计免赔：</td>
							<td align="left">${orderCx.bjmp}元</td>
						</tr>
						<tr>
							<td align="right">备注：</td>
							<td align="left" colspan="3">
								<textarea rows="3" cols="70" name="remark" style="border: 0px;outline:none;" readonly="readonly">${orderCx.remark}</textarea>
							</td>
						</tr>
					</table>
				</div>
			</div>
        </form>
    </div>
</div>