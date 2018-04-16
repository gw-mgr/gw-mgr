<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/head.jsp"%>
<script type="text/javascript">
    $(function() {
		$('#province')
				.combobox(
						{
							url : '${path}/mgr/tMerchantManage/chinaAreaList?pId=0',
							editable : false, //不可编辑状态  
							cache : false,
							valueField : 'ID',
							textField : 'NAME',
							onHidePanel : function() {
								var province = $('#province').combobox(
										'getValue');
								$('#city').combobox('setValue', '');
								$("#district").combobox("setValue", '');
								var country = $('#district').combobox(
										'getValue');
								if (province != '') {
									$
											.ajax({
												type : "POST",
												url : "${path}/mgr/tMerchantManage/chinaAreaList?pId="
														+ province,
												cache : false,
												dataType : "json",
												success : function(data) {
													$("#city").combobox(
															"loadData", data);
												}
											});
								}
							}
						});
		$('#city')
				.combobox(
						{
							editable : false, //不可编辑状态  
							cache : false,
							valueField : 'ID',
							textField : 'NAME',
							onHidePanel : function() {
								$("#district").combobox("setValue", '');
								var city = $('#city').combobox('getValue');
								if (city != '') {
									$
											.ajax({
												type : "POST",
												url : "${path}/mgr/tMerchantManage/chinaAreaList?pId="
														+ city,
												cache : false,
												dataType : "json",
												success : function(data) {
													$("#district").combobox(
															"loadData", data);
												}
											});
								}
							}
						});
		$('#district').combobox({
			editable : false, //不可编辑状态  
			cache : false,
			valueField : 'ID',
			textField : 'NAME',
			onHidePanel : function() {
				var str = $('#district').combobox('getText');
				$("#cregicounty").val(str);
			}
		});
        
		
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" title="" style="overflow: auto;padding: 3px;">
        <form id="orderSxEditForm" method="post">
        <input type="hidden" id="orderId" name="orderId" value="${orderSx.orderId }">
        <input type="hidden" id="policyholderId" name="policyholderId" value="${orderSx.policyholderId }">
        <input type="hidden" id="insuredId" name="insuredId" value="${orderSx.insuredId }">
        <input type="hidden" id="beneficiaryId" name="beneficiaryId" value="${orderSx.beneficiaryId }">
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
		                    <td align="right" width="150px">ID：</td>
		                    <td align="left" colspan="3">${orderSx.orderId}</td>
		                </tr>
						<tr>
							<td align="right"  width="150px">保单号：</td>
							<td align="left">${orderSx.orderNo }</td>
							<td align="right">下单日期：</td>
		                    <td align="left">${orderSx.createTime}</td>
						</tr>
						<tr>
		                    <td align="right">最后修改时间：</td>
		                    <td align="left">${orderSx.updateTime}</td>
		                    <td align="right">最后修改人：</td>
		                    <td align="left">${orderSx.updateUser}</td>
		                </tr>
						<tr>
							<td align="right">是否成交：</td>
							<td align="left">${orderSx.orderFlag==01?'否':'是'}</td>
							<td align="right">礼品是否发放：</td>
							<td align="left">${orderSx.giftFlag==01?'否':'是'}</td>
						</tr>
						<tr>
							<td align="right">是否打单：</td>
							<td align="left">${orderSx.printFlag==01?'否':'是'}</td>
        				     <td align="right">保险公司是否结账：</td>
							<td align="left">${orderSx.checkoutFlag==01?'否':'是'}</td>
						</tr>
						<tr>
		                    <td align="right">打单人：</td>
		                    <td align="left">${orderSx.ddr}</td>
		                    <td align="right">打单时间：</td>
		                    <td align="left">${orderSx.ddDate}</td>
		                </tr>
		                <tr>
		                    <td align="right">保险公司主险税前佣金比例：</td>
		                    <td align="left">${orderSx.bxgszxsqyjbl}</td>
		                    <td align="right">保险公司主险税前佣金：</td>
		                    <td align="left">${orderSx.bxgszxsqyj}</td>
		                </tr>
		                <tr>
		                    <td align="right">保险公司附险1税前佣金比例：</td>
		                    <td align="left">${orderSx.bxgsfx1sqyjbl}</td>
		                    <td align="right">保险公司附险1税前佣金：</td>
		                    <td align="left">${orderSx.bxgsfx1sqyj}</td>
		                </tr>
		                <tr>
		                    <td align="right">险公司附险2税前佣金比例：</td>
		                    <td align="left">${orderSx.bxgsfx2sqyjbl}</td>
		                    <td align="right">保险公司附险2税前佣金：</td>
		                    <td align="left">${orderSx.bxgsfx2sqyj}</td>
		                </tr>
		                <tr>
		                    <td align="right">保险公司开票佣金比例：</td>
		                    <td align="left">${orderSx.bxgskpyjbl}</td>
		                    <td align="right">保险公司开票佣金：</td>
		                    <td align="left">${orderSx.bxgskpyj}</td>
		                </tr>
		                <tr>
		                    <td align="right">公司管理费比例：</td>
		                    <td align="left">${orderSx.gsglfbl}</td>
		                    <td align="right">公司管理费金额：</td>
		                    <td align="left">${orderSx.gsglfje}</td>
		                </tr>
		                <tr>
		                    <td align="right">公司交税比例：</td>
		                    <td align="left">${orderSx.gsjsbl}</td>
		                    <td align="right">公司交税金额：</td>
		                    <td align="left">${orderSx.gsjsje}</td>
		                </tr>
		                <tr>
		                    <td align="right">公司提成比例：</td>
		                    <td align="left">${orderSx.gstcbl}</td>
		                    <td align="right">公司提成金额：</td>
		                    <td align="left">${orderSx.gstcje}</td>
		                </tr>
		                <tr>
		                    <td align="right">本公司毛利：</td>
		                    <td align="left">${orderSx.bgsml}</td>
		                </tr>
					</table>
				</div>
				<div class="sxtablelist sxhidedomdiv sxshowsss">
					<table class="grid">
						<tr>
							<td align="right"  width="150px">姓名：</td>
							<td align="left">${orderSx.userName }</td>
							<td align="right">性别：</td>
							<td align="left">
								<select name="sex" class="easyui-combobox" style="width:150px;" readonly="readonly" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'男',text:'男'},{id:'女',text:'女'}],value:'${orderSx.sex }'">
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">婚姻状况：</td>
							<td align="left">
								<select name="marryFlag" class="easyui-combobox" style="width:150px;" readonly="readonly" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'已婚',text:'已婚'},{id:'未婚',text:'未婚'},{id:'离异',text:'离异'},{id:'丧偶',text:'丧偶'}],value:'${orderSx.marryFlag }'">
								</select>
							</td>
							<td align="right">出生日期：</td>
		                    <td align="left">${orderSx.birthDate }</td>
		                </tr>
		                <tr>
		                	<td align="right">证件类型：</td>
							<td align="left">
								<select name="certType" class="easyui-combobox" style="width:150px;" readonly="readonly" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'身份证',text:'身份证'},{id:'护照',text:'护照'},{id:'军人证',text:'军人证'},{id:'通行证',text:'通行证'},{id:'户口本',text:'户口本'}],value:'${orderSx.certType }'">
								</select>
							</td>
		                </tr>
		                <tr>
							<td align="right">证件号码：</td>
							<td align="left" colspan="3">${orderSx.certNo }</td>
						</tr>
		                <tr>
		                	<td align="right">国籍：</td>
							<td align="left">${orderSx.nationality }</td>
							<td align="right">户籍：</td>
							<td align="left">${orderSx.household }</td>
		                </tr>
		                <tr>
		                	<td align="right">证件有效期至：</td>
		                    <td align="left">${orderSx.validityDate }</td>
		                </tr>
		                <tr>
		                	<td align="right">与被保人关系：</td>
							<td align="left">
								<select name="insuredRelationship" class="easyui-combobox" style="width:150px;" readonly="readonly" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'配偶',text:'配偶'},{id:'子女',text:'子女'},{id:'父母',text:'父母'},{id:'本人',text:'本人'},{id:'其他',text:'其他'}],value:'${orderSx.insuredRelationship }'">
								</select>
							</td>
							<td align="right">居民类型：</td>
							<td align="left">
								<select name="residentType" class="easyui-combobox" style="width:150px;" readonly="readonly" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'城镇',text:'城镇'},{id:'农村',text:'农村'}],value:'${orderSx.residentType }'">
								</select>
							</td>
		                </tr>
		                <tr>
							<td align="right">通讯地址：</td>
							<td align="left" colspan="3">
								<input id="province1" name="pprovince" data-options="required: true" style="width: 120px;" value="${orderSx.pprovince }">
								<input id="city1" name="pcity" data-options="required: true" style="width: 95px;" value="${orderSx.pcity }">
								<input id="district1" name="pdistrict" data-options="required: false" style="width: 120px;" value="${orderSx.pdistrict }">
								${orderSx.postalAddress }
							</td>
						</tr>
						<tr>
							<td align="right">现住址：</td>
							<td align="left" colspan="3">
								<input id="province2" name="rprovince" data-options="required: true" style="width: 120px;" value="${orderSx.rprovince }">
								<input id="city2" name="rcity" data-options="required: true" style="width: 95px;" value="${orderSx.rcity }">
								<input id="district2" name="rdistrict" data-options="required: false" style="width: 120px;" value="${orderSx.rdistrict }">
								${orderSx.residentialAddress }
							</td>
						</tr>
						<tr>
							<td align="right">手机：</td>
							<td align="left">${orderSx.telphone }</td>
							<td align="right">固定电话：</td>
							<td align="left">${orderSx.mtelphone }</td>
						</tr>
						<tr>
							<td align="right">E-mail：</td>
							<td align="left">${orderSx.mail }</td>
							<td align="right">工作单位：</td>
							<td align="left">${orderSx.workUnit }</td>
						</tr>
						<tr>
							<td align="right">工作内容：</td>
							<td align="left">${orderSx.jobContent }</td>
							<td align="right">所属行业：</td>
							<td align="left">${orderSx.industry }</td>
						</tr>
						<tr>
							<td align="right">职业/工种：</td>
							<td align="left">${orderSx.occupation }</td>
						</tr>
					</table>
				</div>
				<div class="sxtablelist sxhidedomdiv sxshowsss">
					<table class="grid">
						<tr>
							<td align="right">与被保人关系：</td>
							<td align="left">
								<select name="insuredRelationship" class="easyui-combobox" style="width:150px;" readonly="readonly" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'配偶',text:'配偶'},{id:'子女',text:'子女'},{id:'父母',text:'父母'},{id:'本人',text:'本人'},{id:'其他',text:'其他'}],value:'${orderSx.insuredRelationship }'">
								</select>
							</td>
						</tr>
					</table>
				</div>
				<div class="sxtablelist sxhidedomdiv sxshowsss">
					<table class="grid">
						<tr>
							<td align="right" width="120px">受益顺序：</td>
							<td align="left">${orderSx.beneficiaryOrder }</td>
							<td align="right">受益比例：</td>
							<td align="left">${orderSx.beneficialRate }%</td>
						</tr>
						<tr>
							<td align="right">受益人姓名：</td>
							<td align="left">${orderSx.beneficialName }</td>
							<td align="right">性别：</td>
							<td align="left">
								<select name="sexS" class="easyui-combobox" style="width:150px;" readonly="readonly" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'男',text:'男'},{id:'女',text:'女'}],value:'${orderSx.sexS }'">
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">出生日期：</td>
		                    <td align="left">${orderSx.birthDateS }</td>
		                    <td align="right">与被保人关系：</td>
							<td align="left">
								<select name="relationship" class="easyui-combobox" style="width:150px;" readonly="readonly" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'配偶',text:'配偶'},{id:'子女',text:'子女'},{id:'父母',text:'父母'},{id:'本人',text:'本人'},{id:'其他',text:'其他'}],value:'${orderSx.relationship }'">
								</select>
							</td>
						</tr>
						 <tr>
		                	<td align="right">证件类型：</td>
							<td align="left">
								<select name="certTypeS" class="easyui-combobox" style="width:150px;" readonly="readonly" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'身份证',text:'身份证'},{id:'护照',text:'护照'},{id:'军人证',text:'军人证'},{id:'通行证',text:'通行证'},{id:'户口本',text:'户口本'}],value:'${orderSx.certTypeS }'">
								</select>
							</td>
		                </tr>
		                <tr>
							<td align="right">证件号码：</td>
							<td align="left" colspan="3">${orderSx.certNoS }</td>
						</tr>
						<tr>
		                	<td align="right">证件有效期至：</td>
		                    <td align="left">${orderSx.validityDateS }</td>
		                </tr>
		                <tr>
		                	<td align="right">住址：</td>
							<td align="left">
								<select name="residentialAddressS" class="easyui-combobox" style="width:150px;" readonly="readonly" 
								data-options="editable:false,valueField:'id',textField:'text',data:[{id:'同投保人',text:'同投保人'},{id:'同被保险人',text:'同被保险人'},{id:'其他',text:'其他'}],value:'${orderSx.residentialAddressS }'">
								</select>
							</td>
		                </tr>
					</table>
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
											<select name="jfpl" class="easyui-combobox" style="width:150px;" readonly="readonly" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'年交',text:'年交'},{id:'趸交',text:'趸交'},{id:'月交（月交首付需交纳2个月保险费）',text:'月交（月交首付需交纳2个月保险费）'},{id:'其他',text:'其他'}],value:'${orderSx.jfpl }'">
											</select>
										</td>
									</tr>
									<tr>
										<td align="right">保险费自动垫交选择：</td>
										<td align="left">
											<select name="sfzdxj" class="easyui-combobox" style="width:150px;" readonly="readonly" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'是',text:'是'},{id:'否',text:'否'}],value:'${orderSx.sfzdxj }'">
											</select>
										</td>
									</tr>
									<tr>
										<td align="right">首期保费支付方式：</td>
										<td align="left">
											<select name="sqjffs" class="easyui-combobox" style="width:150px;" readonly="readonly" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'银行转账',text:'银行转账'},{id:'其他',text:'其他'}],value:'${orderSx.sqjffs }'">
											</select>
										</td>
									</tr>
									<tr>
										<td align="right">续期保费支付方式：</td>
										<td align="left">
											<select name="xqjffs" class="easyui-combobox" style="width:150px;" readonly="readonly" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'银行转账',text:'银行转账'},{id:'其他',text:'其他'}],value:'${orderSx.xqjffs }'">
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
											${orderSx.khhmc }
										</td>
									</tr>
									<tr>
										<td align="right">授权转账账号：</td>
										<td align="left">
											${orderSx.yhzh }
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
										<td align="center">主险</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
									</tr>
									<tr>
										<td align="center">附险1</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
									</tr>
								</table>
							</td>
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
											<select name="hllqfs" class="easyui-combobox" style="width:150px;" readonly="readonly" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'累计生息',text:'累计生息'},{id:'现金领取',text:'现金领取'},{id:'红利转万能险账户',text:'红利转万能险账户'},{id:'抵交保险费',text:'抵交保险费'},{id:'其他',text:'其他'}],value:'${orderSx.hllqfs }'">
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
											<select name="hllqnl" class="easyui-combobox" style="width:150px;" readonly="readonly" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'50岁',text:'50岁'},{id:'55岁',text:'55岁'},{id:'60岁',text:'60岁'},{id:'65岁',text:'65岁'},{id:'其他',text:'其他'}],value:'${orderSx.hllqnl }'">
											</select>
										</td>
									</tr>
									<tr>
										<td align="right" width="220px">领取方式：</td>
										<td align="left">
											<select name="lqfs" class="easyui-combobox" style="width:150px;" readonly="readonly" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'年领',text:'年领'},{id:'月领',text:'月领'},{id:'其他',text:'其他'}],value:'${orderSx.lqfs }'">
											</select>
										</td>
									</tr>
									<tr>
										<td align="right" width="220px">领取类型：</td>
										<td align="left">
											<select name="hllqlx" class="easyui-combobox" style="width:150px;" readonly="readonly" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'平准年金',text:'平准年金'},{id:'增额年金',text:'增额年金'},{id:'其他',text:'其他'}],value:'${orderSx.hllqlx }'">
											</select>
										</td>
									</tr>
									<tr>
										<td align="right" width="220px">自动支付保险费选择：</td>
										<td align="left">
											<select name="sfzdzf" class="easyui-combobox" style="width:150px;" readonly="readonly" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'是',text:'是'},{id:'否',text:'否'}],value:'${orderSx.sfzdzf }'">
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
								<table class="grid">
									<tr>
										<td align="right" width="220px">投资账户名称：</td>
										<td align="left">
											${orderSx.tzzhmc }
										</td>
									</tr>
									<tr>
										<td align="right">分配比例：</td>
										<td align="left">
											${orderSx.fpbl }
										</td>
									</tr>
									<tr>
										<td align="right">追加保险费分配比例：</td>
										<td align="left">
											${orderSx.zjbxfpbl }
										</td>
									</tr>
								</table>
							</td>
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
											${orderSx.tbrsg }厘米
										</td>
									</tr>
									<tr>
										<td align="right">投保人，体重：</td>
										<td align="left">
											${orderSx.tbrtz }公斤
										</td>
									</tr>
									<tr>
										<td align="right">被保险人，身高：</td>
										<td align="left">
											${orderSx.bbrsg }厘米
										</td>
									</tr>
									<tr>
										<td align="right">被保险人，体重：</td>
										<td align="left">
											${orderSx.bbrtz }公斤
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
        				        			${orderSx.txwsx1==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx1==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">2、您过去一年内是否因受伤或患病去医院门诊接受诊查、治疗，或被医师建议治疗、住院或手术？</td>
										<td align="center">
        				        			${orderSx.txwsx2==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx2==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">3、您在过去2年内是否做过以下检查：血压、血液和尿液化验检查、x光、心电图、B超、CT、核磁共振、内窥镜、病理活组织检查或其他化验检查？</td>
										<td align="center">
        				        			${orderSx.txwsx3==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx3==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">4、您过去是否曾因受伤或患病而住院治疗？若是，请在说明栏中详述医院诊断、诊治经过及预后。</td>
										<td align="center">
        				        			${orderSx.txwsx4==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx4==01?'否':'是'}
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
        				        			${orderSx.txwsx5a==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx5a==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">b、五官科疾病：如原因不明的声音嘶哑、听力下降、复视、耳鸣、中耳炎、美尼尔病、白内障、青光眼、高度近视（800度以上）、视神经或视网膜病变、慢性鼻炎、鼻息肉或其他五官科疾病等？</td>
										<td align="center">
        				        			${orderSx.txwsx5b==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx5b==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">c、循环系统疾病：如高血压、冠心病、心肌梗塞、风湿性心脏病、先天性心脏病、风湿热、主动脉瘤、肺心病、心律失常、传导阻滞、心包炎、心肌病、下肢静脉曲张及其他心血管系统疾病？</td>
										<td align="center">
        				        			${orderSx.txwsx5c==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx5c==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">d、呼吸系统疾病：如慢性支气管炎、肺气肿、支气管扩张、肺结核、气胸、哮喘或其他呼吸系统疾病？</td>
										<td align="center">
        				        			${orderSx.txwsx5d==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx5d==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">e、消化系统疾病：如肝炎病毒携带、肝炎、脂肪肝、肝硬化、肝肿大、肝胆结石、胆囊息肉、胰腺疾病、胃炎、消化道溃疡或出血、穿孔、结肠炎、肠息肉、肠梗阻、疝气或其他消化系统疾病？</td>
										<td align="center">
        				        			${orderSx.txwsx5e==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx5e==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">f、泌尿及生殖系统疾病：如肾炎、肾小球疾病、肾病综合症、省功能衰竭、多囊肾、泌尿系结石或感染、前列腺疾病或其他泌尿及生殖系统疾病？</td>
										<td align="center">
        				        			${orderSx.txwsx5f==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx5f==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">g、内分泌及结缔组织疾病：如糖尿病、痛风、高尿酸血症、甲状腺或甲状腺旁腺疾病、肾上腺疾病、脑垂体疾病；类风湿性关节炎、风湿病、红斑狼疮、胶原病、白塞氏病、免疫性疾病、肌肉骨骼关节疾病等？</td>
										<td align="center">
        				        			${orderSx.txwsx5g==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx5g==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">h、血液疾病：如原因不明的皮肤、粘膜、及齿龈出血、白血病、再生障碍性贫血、血友病、紫癜症、贫血、脾脏疾病等？</td>
										<td align="center">
        				        			${orderSx.txwsx5h==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx5h==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">i、癌症、良恶性肿瘤、腺瘤、息肉、囊肿、痔疮、任何包块或肿物等？其他需持续关注或治疗的疾病或异常症状体征？</td>
										<td align="center">
        				        			${orderSx.txwsx5i==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx5i==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">j、顽固性皮肤病、性病，传染病？或任何职业病？您或您的配偶是否接受过艾滋病的检查和治疗？或在过去6个月以内曾经持续一周以上的下列症状：体重下降、食欲不振、盗汗、腹泻、淋巴结肿大及皮肤溃疡？</td>
										<td align="center">
        				        			${orderSx.txwsx5j==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx5j==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">k、接受过输血或建议不宜献血？您是否使用过任何违禁药品或成瘾性药物，是否服用、吸食或注射过毒品？</td>
										<td align="center">
        				        			${orderSx.txwsx5k==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx5k==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">6、身体残障情况：有无智能障碍；有无失明、聋哑、跛行、小二麻痹后遗症；有无脊柱、胸廓、四肢、五官畸形或功能障碍；有无语言、咀嚼、视力、听力、嗅觉、四肢及中枢神经系统机能障碍？</td>
										<td align="center">
        				        			${orderSx.txwsx6==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx6==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">7、有否吸烟嗜好：若有请说明每天吸烟几只，约多少年。若已停止吸烟，请说明停止的原因和时间。</td>
										<td align="center">
        				        			${orderSx.txwsx7==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx7==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="center">
											<table>
												<tr>
													<td>每天吸烟<input>只</td>
												</tr>
												<tr>
													<td>吸烟时间<input>年</td>
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
        				        			${orderSx.txwsx8==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx8==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="center">
											<table>
												<tr>
													<td>饮酒种类<input></td>
												</tr>
												<tr>
													<td>每天饮酒<input>量</td>
												</tr>
												<tr>
													<td>饮酒时间<input>年</td>
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
        				        			${orderSx.txwsx9==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx9==01?'否':'是'}
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
        				        			${orderSx.txwsx10a==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx10a==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">b、有否乳房方面疾病与不适症状体征，或因此而接受医师检查、用药或住院手术等治疗？</td>
										<td align="center">
        				        			${orderSx.txwsx10b==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx10b==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">c、目前是否怀孕？怀孕几周；是否因妊娠分娩而住院或手术；有否异位妊娠或不孕不育？</td>
										<td align="center">
        				        			${orderSx.txwsx10c==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx10c==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="center">
											怀孕时间<input>周
										</td>
										<td align="center">
										</td>
										<td align="center">
										</td>
									</tr>
									<tr>
										<td align="left">11、2周岁以下（含2周岁）被保险人告知栏：出生时的身高（厘米）？体重（公斤）？</td>
										<td align="center">
        				        			${orderSx.txwsx11==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx11==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="center">
											<table>
												<tr>
													<td>身高<input></td>
												</tr>
												<tr>
													<td>体重<input></td>
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
        				        			${orderSx.txwsx11a==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx11a==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">b、是否有发育迟缓、智力障碍、惊厥、抽搐、脑瘫、缺氧缺血性脑病、先天性遗传性疾病？</td>
										<td align="center">
        				        			${orderSx.txwsx11b==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx11b==01?'否':'是'}
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
											<input name="tbrnsr" value="${orderSx.tbrnsr }">万元
										</td>
									</tr>
									<tr>
										<td align="right">来源：</td>
										<td align="left">
											<select name="tbrsrly" class="easyui-combobox" style="width:150px;" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'工薪',text:'工薪'},{id:'个体',text:'个体'},{id:'私营',text:'私营'},{id:'房屋出租',text:'房屋出租'},{id:'证券投资',text:'证券投资'},{id:'银行利息',text:'银行利息'},{id:'其他',text:'其他'}],value:'${orderSx.tbrsrly }'">
											</select>
										</td>
									</tr>
									<tr>
										<td align="right">被保险人年收入：</td>
										<td align="left">
											<input name="bbrnsr" value="${orderSx.bbrnsr }">万元
										</td>
									</tr>
									<tr>
										<td align="right">来源：</td>
										<td align="left">
											<select name="bbrsrly" class="easyui-combobox" style="width:150px;" 
											data-options="editable:false,valueField:'id',textField:'text',data:[{id:'工薪',text:'工薪'},{id:'个体',text:'个体'},{id:'私营',text:'私营'},{id:'房屋出租',text:'房屋出租'},{id:'证券投资',text:'证券投资'},{id:'银行利息',text:'银行利息'},{id:'其他',text:'其他'}],value:'${orderSx.bbrsrly }'">
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
        				        			${orderSx.txwsx13==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx13==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">14、您目前是否拥有公费医疗、社会医疗保险？</td>
										<td align="center">
        				        			${orderSx.txwsx14==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx14==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">15、您是否曾购买或正在购买除本投保申请以外的其他保险产品（含其他保险公司）？</td>
										<td align="center">
        				        			${orderSx.txwsx15==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx15==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">16、您的投保生气是否曾被拒保、延期、加费、或作任何承保条件的修改（喊本公司及其他保险公司）？</td>
										<td align="center">
        				        			${orderSx.txwsx16==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx16==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">17、您是否曾向任何保险公司提出过索赔申请？若是，请详述：公司名称，索赔日期，索赔金额，索赔事由。</td>
										<td align="center">
        				        			${orderSx.txwsx17==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx17==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="center">
											<table>
												<tr>
													<td>公司名称<input></td>
												</tr>
												<tr>
													<td>索赔日期<input></td>
												</tr>
												<tr>
													<td>索赔金额<input></td>
												</tr>
												<tr>
													<td>索赔事由<input></td>
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
        				        			${orderSx.txwsx18==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx18==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="center">
											<table>
												<tr>
													<td>驾照类型<input></td>
												</tr>
												<tr>
													<td>签发日期<input></td>
												</tr>
												<tr>
													<td>驾龄<input>年</td>
												</tr>
												<tr>
													<td>车辆类型<input></td>
												</tr>
												<tr>
													<td>用途<input></td>
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
        				        			${orderSx.txwsx19==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx19==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="left">20、您是否曾在国外持续居住三个月以上或正拟前往中国大陆以外的国家/地区？若是，请详述：</td>
										<td align="center">
        				        			${orderSx.txwsx20==01?'否':'是'}
										</td>
										<td align="center">
        				        			${orderSx.bxwsx20==01?'否':'是'}
										</td>
									</tr>
									<tr>
										<td align="center">
											<table>
												<tr>
													<td>国家/地区名<input></td>
												</tr>
												<tr>
													<td>缘由<input></td>
												</tr>
												<tr>
													<td>滞留时间<input></td>
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
							<input name="insuranceCompanyId" class="easyui-combobox" value="${orderSx.insuranceCompanyId }" data-options="width:180, valueField: 'id', textField: 'insurerName', url: '${path}/mgr/tInsurer/list'"/>
							<input name="insuranceCompany" type="hidden" value="${orderSx.insuranceCompany }"> 
							</td>
							<td align="right">保险公司报案电话：</td>
							<td align="left">
							<input style="width: 180px" name="insuranceCompanyTel" value="${orderSx.insuranceCompanyTel }"></td>
						</tr>
						<tr>
							<td align="right">出单渠道：</td>
							<td align="left">
								<input name="operator" class="easyui-combobox" value="${orderSx.operator }" data-options="width:180, valueField: 'id', textField: 'name', url: '${path}/mgr/fuser/list'"/>
							</td>
							<td align="right">投保方式：</td>
							<td align="left"><input name="tbfs" class="easyui-combobox" value="${orderSx.tbfs }" data-options="width:180, valueField: 'id', textField: 'name', url: '${path}/mgr/parameter/list?type=01'"/></td>
						</tr>
						<tr>
							<td align="right">净主险：</td>
							<td align="left"><input style="width: 130px" class="easyui-numberbox" value="${orderSx.jzx }" data-options="min:0,precision:2" id="jzx" name="jzx" >
								<a href="javascript:void(0);" class="easyui-linkbutton"  onclick="zxbfFun();">工具</a>
							</td>
							<td align="right">主险税：</td>
							<td align="left"><input style="width: 180px" class="easyui-numberbox" value="${orderSx.zxs }" data-options="min:0,precision:2" id="zxs" name="zxs" ></td>
						</tr>
						<tr>
							<td align="right">净附险1：</td>
							<td align="left"><input style="width: 130px" class="easyui-numberbox" value="${orderSx.jfx1 }" data-options="min:0,precision:2" id="jfx1" name="jfx1">
								<a href="javascript:void(0);" class="easyui-linkbutton"  onclick="jfx1Fun();">工具</a>
							</td>
							<td align="right">附险1税：</td>
							<td align="left"><input style="width: 180px" class="easyui-numberbox" value="${orderSx.fxs1 }" data-options="min:0,precision:2" id="fxs1" name="fxs1"></td>
						</tr>
						<tr>
							<td align="right">净附险2：</td>
							<td align="left"><input style="width: 130px" class="easyui-numberbox" value="${orderSx.jfx2 }" data-options="min:0,precision:2" id="jfx2" name="jfx2">
								<a href="javascript:void(0);" class="easyui-linkbutton"  onclick="jfx2Fun();">工具</a>
							</td>
							<td align="right">附险2税：</td>
							<td align="left"><input style="width: 180px" class="easyui-numberbox" value="${orderSx.fxs2 }" data-options="min:0,precision:2" id="fxs2" name="fxs2"></td>
						</tr>
						<tr>
							<td align="right">业务员：</td>
							<td align="left"><input id="salesManSx" name="salesMan" type="hidden" value="${orderSx.salesMan }">
	                    	<input id="salesManNameSx" name="salesManName" value="${orderSx.salesManName }" type="text" readonly="readonly" placeholder="请选择业务员">
	                    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="saleManForOrderSxCheckFun();">选择</a></td>
							<td align="right">佣金支付对象：</td>
							<td align="left"><select name="yjzfdx" class="easyui-combobox" style="width: 180px">  
							    <option value="01">业务员</option>  
							    <option value="02">录单员</option>  
							</select></td>
						</tr>
						<tr>
							<td align="right">主险佣金比例：</td>
							<td align="left"><input style="width: 180px" name="zxyjbl" class="easyui-numberbox" value="${orderSx.zxyjbl }" data-options="min:0,precision:2"></td>
							<td align="right">主线佣金金额：</td>
							<td align="left"><input style="width: 180px" name="zxyjje" class="easyui-numberbox" value="${orderSx.zxyjje }" data-options="min:0,precision:2"></td>
						</tr>
						<tr>
							<td align="right">附险1佣金比例：</td>
							<td align="left"><input style="width: 180px" name="fxyjbl1" class="easyui-numberbox" value="${orderSx.fxyjbl1 }" data-options="min:0,precision:2"></td>
							<td align="right">附险1佣金金额：</td>
							<td align="left"><input style="width: 180px" name="fxyjje1" class="easyui-numberbox" value="${orderSx.fxyjje1 }" data-options="min:0,precision:2"></td>
						</tr>
						<tr>
							<td align="right">附险2佣金比例：</td>
							<td align="left"><input style="width: 180px" name="fxyjbl2" class="easyui-numberbox" value="${orderSx.fxyjbl2 }" data-options="min:0,precision:2"></td>
							<td align="right">附险2佣金金额：</td>
							<td align="left"><input style="width: 180px" name="fxyjje2" class="easyui-numberbox" value="${orderSx.fxyjje2 }" data-options="min:0,precision:2"></td>
						</tr>
						<tr>
							<td align="right">综合金融奖比例：</td>
							<td align="left"><input style="width: 180px" name="zhjrjbl" class="easyui-numberbox" value="${orderSx.zhjrjbl }" data-options="min:0,precision:2"></td>
							<td align="right">综合金融奖金额：</td>
							<td align="left"><input style="width: 180px" name="zhjrjje" class="easyui-numberbox" value="${orderSx.zhjrjje }" data-options="min:0,precision:2"></td>
						</tr>
						
						<tr>
							<td align="right">保险公司短期激励：</td>
							<td align="left"><input style="width: 180px" name="bxgsdqjl" class="easyui-numberbox" value="${orderSx.bxgsdqjl }" data-options="min:0,precision:2"></td>
							<td align="right">是否有礼品：</td>
							<td align="left"><input type="radio" name="sfylp" class="easyui-validatebox" value="01">否
        				        <input type="radio" name="sfylp" class="easyui-validatebox" value="02">是</td>
						</tr>
						<tr>
							<td align="right">礼品内容：</td>
							<td align="left" colspan="3"><input style="width: 500px" name="lpnr" value="${orderSx.lpnr }"></td>
						</tr>
						<tr>
							<td align="right">录单员ID：</td>
							<td align="left">
								<input style="width: 180px; border: 0px;outline:none;" name="recorderName" value="${recorderName }">
								<input type="hidden" name="recorder" value="${recorder }">
							</td>
							<td align="right">录单员佣金：</td>
							<td align="left"><input style="width: 180px" name="recordertc" value="${orderSx.recordertc }" class="easyui-numberbox" value="" data-options="min:0,precision:2"></td>
						</tr>
					</table>
				</div>
			</div>
        </form>
    </div>
</div>