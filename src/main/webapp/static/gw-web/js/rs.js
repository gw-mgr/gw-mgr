


$$("#syr").on("tap", function() {
	var syrHtml = '<div class="syr">';
		syrHtml += '<div class="mui-input-group">'
		syrHtml += '<div class="mui-input-row">'
		syrHtml += '<div class="mui-navigate-right">'
		syrHtml += '<label>受益顺序</label>'
		syrHtml += '<input type="text" name="beneficiaryOrder"  class=" mui-text-right beneficiaryOrder" placeholder="请选择" maxlength="32" readonly="readonly">'
		syrHtml += '</div>'
		syrHtml += '</div>'
		syrHtml += '<div class="mui-input-row">'
		syrHtml += '<label>受益比例(%)</label>'
		syrHtml += '<input type="tel" name="beneficialRate" class=" mui-text-right mui-input-clear" placeholder="请输入" maxlength="32">'
		syrHtml += '</div>'
		syrHtml += '<div class="mui-input-row">'
		syrHtml += '<label>受益人姓名</label>'
		syrHtml += '<input type="text" name="beneficialName" class=" mui-text-right mui-input-clear" placeholder="请输入" maxlength="32">'
		syrHtml += '</div>'
		syrHtml += '<div class="mui-input-row">'
		syrHtml += '<div class="mui-navigate-right">'
		syrHtml += '<label>性别</label>'
		syrHtml += '<input type="text" name="sex" class=" mui-text-right sex" placeholder="请选择" maxlength="50" readonly="readonly">'
		syrHtml += '</div>'
		syrHtml += '</div>'
		syrHtml += '<div class="mui-input-row">'
		syrHtml += '<div class="mui-navigate-right">'
		syrHtml += '<label>出生日期</label>'
		syrHtml += '<input type="text" name="birthDate" data-options=\'{"type":"date","beginYear":1900,"endYear":2018}\' class=" mui-text-right date" placeholder="请输入" maxlength="50" readonly="readonly">'
		syrHtml += '</div>'
		syrHtml += '</div>'
		syrHtml += '<div class="mui-input-row">'
		syrHtml += '<div class="mui-navigate-right">'
		syrHtml += '<label>与被保人关系</label>'
		syrHtml += '<input type="text" name="relationship" class=" mui-text-right relationship" placeholder="请选择" maxlength="50" readonly="readonly">'
		syrHtml += '</div>'
		syrHtml += '</div>'
		syrHtml += '<div class="mui-input-row">'
		syrHtml += '<div class="mui-navigate-right">'
		syrHtml += '<label>证件类型</label>'
		syrHtml += '<input type="text" name="certType"  class=" mui-text-right certType" placeholder="请选择" maxlength="50" readonly="readonly">'
		syrHtml += '</div>'
		syrHtml += '</div>'
		syrHtml += '<div class="mui-input-row">'
		syrHtml += '<label>证件号码</label>'
		syrHtml += '<input type="text" name="certNo" class=" mui-text-right mui-input-clear" placeholder="请输入" maxlength="32">'
		syrHtml += '</div>'
		syrHtml += '<div class="mui-input-row" id="creValidDiv">'
		syrHtml += '<div class="mui-navigate-right creValid">'
		syrHtml += '<label style="width: 100px;">证件有效期至</label>'
		syrHtml += '<input type="text" name="validityDate" id="validityDate" data-options=\'{"type":"date","beginYear":1900,"endYear":2018}\' class=" mui-text-right date" placeholder="请选择" maxlength="50" readonly="readonly"> <span class="check">长期</span>'
		syrHtml += '</div>'
		syrHtml += '</div>'
		syrHtml += '<div class="mui-input-row">'
		syrHtml += '<div class="mui-navigate-right">'
		syrHtml += '<label>通讯地址</label>'
		syrHtml += '<input name="provinceName" type="text" class=" mui-text-right address" placeholder="请选择(省、市、区)" maxlength="50" readonly="readonly">'
		syrHtml += '<input name="province" type="hidden">'
		syrHtml += '<input name="city" type="hidden">'
		syrHtml += '<input name="country" type="hidden">'
		syrHtml += '</div>'
		syrHtml += '</div>'
		syrHtml += '<div class="mui-input-row">'
		syrHtml += '<label>详细地址</label>'
		syrHtml += '<input type="text" name="residentialAddress" class=" mui-text-right mui-input-clear" placeholder="请输入" maxlength="32">'
		syrHtml += '</div>'
		syrHtml += '</div>'
		syrHtml += '<div class="mui-table-view-divider none"></div>'
		syrHtml += '</div>'
		$$("#syr_list").append(syrHtml);
		
		_load();
	
});
/****保险事项*****/
$$("#tbsxbxsx").on("tap", function() {
	var tbsx_bxsxHtml = '<div class="tbsxbxsx">';
	var _index = ($$("#tbsx_bxsx .tbsxbxsx").length);
	tbsx_bxsxHtml += '<div class="mui-table-view-divider none"></div>'
	tbsx_bxsxHtml += '<div class="mui-input-row">'
	tbsx_bxsxHtml += '<label>附险'+_index+ '</label>'
	tbsx_bxsxHtml += '<input type="text" name="name" class=" mui-text-right mui-input-clear" placeholder="请输入" maxlength="32">'
	tbsx_bxsxHtml += '<input type="hidden" name="order"value="'+_index+'">'
	tbsx_bxsxHtml += '</div>'
	tbsx_bxsxHtml += '<div class="mui-input-row">'
	tbsx_bxsxHtml += '<label>险种代码</label>'
	tbsx_bxsxHtml += '<input type="text" name="code" class=" mui-text-right mui-input-clear" placeholder="请输入" maxlength="32">'
	tbsx_bxsxHtml += '</div>'
	tbsx_bxsxHtml += '<div class="mui-input-row">'
	tbsx_bxsxHtml += '<label>保险期间</label>'
	tbsx_bxsxHtml += '<input type="text" name="bxDate" class=" mui-text-right mui-input-clear" placeholder="请输入" maxlength="32">'
	tbsx_bxsxHtml += '</div>'
	tbsx_bxsxHtml += '<div class="mui-input-row">'
	tbsx_bxsxHtml += '<div class="mui-navigate-right">'
	tbsx_bxsxHtml += '<label>交费期间</label>'
	tbsx_bxsxHtml += '<input type="text" name="jfDate"  class=" mui-text-right jfDate" placeholder="请选择" maxlength="32" readonly="readonly">'
	tbsx_bxsxHtml += '</div>'
	tbsx_bxsxHtml += '</div>'
	tbsx_bxsxHtml += '<div class="mui-input-row">'
	tbsx_bxsxHtml += '<label>保险金额（元）</label>'
	tbsx_bxsxHtml += '<input type="tel" name="bxMoney" class=" mui-text-right mui-input-clear" placeholder="请输入" maxlength="32">'
	tbsx_bxsxHtml += '</div>'
	tbsx_bxsxHtml += '<div class="mui-input-row">'
	tbsx_bxsxHtml += '<label>保险费（元）</label>'
	tbsx_bxsxHtml += '<input type="tel" name="bxFee" class=" mui-text-right mui-input-clear" placeholder="请输入" maxlength="32">'
	tbsx_bxsxHtml += '</div>'
	tbsx_bxsxHtml += '<div class="mui-input-row">'
	tbsx_bxsxHtml += '<label>追加保费（元）</label>'
	tbsx_bxsxHtml += '<input type="tel" name="zjbf" class=" mui-text-right mui-input-clear" placeholder="请输入" maxlength="32">'
	tbsx_bxsxHtml += '</div>'
	tbsx_bxsxHtml += '<div class="mui-input-row">'
	tbsx_bxsxHtml += '<label>保险费小计（元）</label>'
	tbsx_bxsxHtml += '<input type="tel" name="bfxj"  class=" mui-text-right mui-input-clear" placeholder="请输入" maxlength="32">'
	tbsx_bxsxHtml += '</div>'
	tbsx_bxsxHtml += '</div>';
	$$("#tbsx_bxsx").append(tbsx_bxsxHtml);
	_load();
});
/*****投资连结保险*****/
$$("#tzljbxtx").on("tap", function() {
	var tzljbxtxHtml = '<div class="tzljbxtx">';
	tzljbxtxHtml += '<div class="mui-table-view-divider none"></div>';
	tzljbxtxHtml += '<div class="mui-input-row">'
	tzljbxtxHtml += '<label>投资账户名称</label>'
	tzljbxtxHtml += '<input type="text"  name="name" class=" mui-text-right mui-input-clear" placeholder="请输入" maxlength="32">'
	tzljbxtxHtml += '</div>'
	tzljbxtxHtml += '<div class="mui-input-row">'
	tzljbxtxHtml += '<label>分配比例</label>'
	tzljbxtxHtml += '<input type="text" name="rate" class=" mui-text-right mui-input-clear" placeholder="请输入" maxlength="32">'
	tzljbxtxHtml += '</div>'
	tzljbxtxHtml += '<div class="mui-input-row">'
	tzljbxtxHtml += '<label>追加保险费分配比例</label>'
	tzljbxtxHtml += '<input type="text" name="zRate"  class=" mui-text-right mui-input-clear" placeholder="请输入" maxlength="32">'
	tzljbxtxHtml += '</div>'
	tzljbxtxHtml += '</div>';
	$$("#TZLJBXTX").append(tzljbxtxHtml);
});
/*说明栏*/
$$("#gzsxjsmlCsml").on("tap", function() {
	var gzsxjsmlCsmlHtml = '<div class="gzsxjsmlCsml">';
	gzsxjsmlCsmlHtml += '<div class="mui-table-view-divider none"></div>'
	gzsxjsmlCsmlHtml += '<div class="mui-input-row">'
	gzsxjsmlCsmlHtml += '<label>序号</label>'
	gzsxjsmlCsmlHtml += '<input type="text"  name="seq" class=" mui-text-right mui-input-clear" placeholder="请输入" maxlength="32">'
	gzsxjsmlCsmlHtml += '</div>'
	gzsxjsmlCsmlHtml += '<div class="mui-input-row">'
	gzsxjsmlCsmlHtml += '<label>说明对象</label>'
	gzsxjsmlCsmlHtml += '<input type="text" name="name" class=" mui-text-right mui-input-clear" placeholder="请输入" maxlength="32">'
	gzsxjsmlCsmlHtml += '</div>'
	gzsxjsmlCsmlHtml += '<div class="mui-input-row">'
	gzsxjsmlCsmlHtml += '<label>说明内容</label>'
	gzsxjsmlCsmlHtml += '<input type="text" name="description"  class=" mui-text-right mui-input-clear" placeholder="请输入" maxlength="32">'
	gzsxjsmlCsmlHtml += '</div>'
	gzsxjsmlCsmlHtml += '</div>';
	$$("#GZSXJSML_CSML").append(gzsxjsmlCsmlHtml);
});
$$(function(){
	_load();
})
function _load(){
(function($, doc) {
	console.log("mui-load()-------------");
	$.init();
	var deceleration = mui.os.ios ? 0.003 : 0.0009;
	$('.mui-scroll-wrapper').scroll({
		bounce: false,
		indicators: true, //是否显示滚动条
		deceleration: deceleration
	});
	$.ready(function() {
		$$("body .mui-poppicker").remove();
		
		var sexPicker = new $.PopPicker({
			layer: "",
			title: "性别"
		});
		var wedlockNamePicker = new $.PopPicker({
			layer: "",
			title: "婚姻状况"
		});
		var creTypeNamePicker = new $.PopPicker({
			layer: "",
			title: "证件类型"
		});
		var populationTypeNamePicker = new $.PopPicker({
			layer: "",
			title: "居民类型"
		});
		var relationshipPicker = new $.PopPicker({
			layer: "",
			title: "与被保人关系"
		});
		var benefitPicker = new $.PopPicker({
			layer: "",
			title: "受益顺序"
		});
		var addressTypePicker = new $.PopPicker({
			layer: "",
			title: "请选择"
		});
		var paymentsNamePicker = new $.PopPicker({
			layer: "",
			title: "交费频率"
		});
		var isPicker = new $.PopPicker({
			layer: "",
			title: "请选择"
		});

		var payNamePicker = new $.PopPicker({
			layer: "",
			title: "支付方式"
		});
		var yearNamePicker = new $.PopPicker({
			layer: "",
			title: "交费期间"
		});

		var gogetNamePicker = new $.PopPicker({
			layer: "",
			title: "红利领取方式"
		});
		var oldDataPicker = new $.PopPicker({
			layer: "",
			title: "领取年龄"
		});
		var lqStatusPicker = new $.PopPicker({
			layer: "",
			title: "领取方式"
		});
		var lqTypePicker = new $.PopPicker({
			layer: "",
			title: "领取类型"
		});
		var lyPicker = new $.PopPicker({
			layer: "",
			title: "来源"
		});
		/*性别*/
		sexPicker.setData(sexdata);
		$$(".sex").click(function(event) { //投保人性别
			var _self = this;
			sexPicker.show(function(items) {
				$$(_self).val(JSON.stringify(items[0].text).replace(/"/g, ""));
			});
		});
		/*婚姻状况*/
		wedlockNamePicker.setData(wedlockdata);
		$$(".marryFlag").click(function(event) { //投保人婚姻状况
			var _self = this;
			wedlockNamePicker.show(function(items) {
				$$(_self).val(JSON.stringify(items[0].text).replace(/"/g, ""));
			});
		});
		/*证件类型*/
		creTypeNamePicker.setData(creTypedata);
		$$(".certType").click(function(event) { //投保人证件类型
			var _this = $$(this);
			creTypeNamePicker.show(function(items) {
				$$(_this).val(JSON.stringify(items[0].text).replace(/"/g, ""));			
			});
		});
		/*居民类型*/
		populationTypeNamePicker.setData(populationtypedata);
		$$(".residentType").click(function(event) { //投保人居民类型
			var _this = $$(this);
			populationTypeNamePicker.show(function(items) {
				$$(_this).val(JSON.stringify(items[0].text).replace(/"/g, ""));			
			});
		});
		/*与被保险人关系*/
		relationshipPicker.setData(relationshipData);
		$$(".relationship").click(function(event) { //
			var _self = this;
			relationshipPicker.show(function(items) {
				$$(_self).val(JSON.stringify(items[0].text).replace(/"/g, ""));
			});
		});
		/*受益顺序*/
		benefitPicker.setData(benefitdata);
		$$(".beneficiaryOrder").click(function(event) { //
			var _self = this;
			benefitPicker.show(function(items) {
				$$(_self).val(JSON.stringify(items[0].value).replace(/"/g, ""));
			});
		});
		/*住址*/
		addressTypePicker.setData(addressTypeData);
		$$(".addressTypeData").click(function(event) { //
			var _self = this;
			addressTypePicker.show(function(items) {
				$$(_self).val(JSON.stringify(items[0].value).replace(/"/g, ""));
			});
		});
		/*交费频率*/
		paymentsNamePicker.setData(paymentsdata);
		$$("input[name='JFPL']").click(function(event) { //
			paymentsNamePicker.show(function(items) {
				$$("input[name='JFPL']").val(JSON.stringify(items[0].text).replace(/"/g, ""));
			});
		});
		/*保险费自动垫交选择*/
		isPicker.setData(isdata);
		$$(".is").click(function(event) { //
			var _self = this;
			isPicker.show(function(items) {
				$$(_self).val(JSON.stringify(items[0].text).replace(/"/g, ""));
			});
		});
		$$(".isT").click(function(event) { //
			var _self = this;
			isPicker.show(function(items) {
				$$(_self).val(JSON.stringify(items[0].text).replace(/"/g, ""));
				$$(_self).next().val(JSON.stringify(items[0].value).replace(/"/g, ""));
			});
		});
		/*支付方式*/
		payNamePicker.setData(paydata);
		$$(".pay").click(function(event) { //
			var _self = this;
			payNamePicker.show(function(items) {
				$$(_self).val(JSON.stringify(items[0].text).replace(/"/g, ""));
			});
		});
		/*交费时间*/
		yearNamePicker.setData(yeardata);
		$$(".jfDate").click(function(event) { //
			var _self = this;
			yearNamePicker.show(function(items) {
				$$(_self).val(JSON.stringify(items[0].text).replace(/"/g, ""));
			});
		});
		/*红利领取方式*/
		gogetNamePicker.setData(gogetdata);
		$$("input[name='hllqfs']").click(function(event) { //
			gogetNamePicker.show(function(items) {
				$$("input[name='hllqfs']").val(JSON.stringify(items[0].text).replace(/"/g, ""));
			});
		});
		/*领取年龄*/
		oldDataPicker.setData(oldData);
		$$("input[name='hllqnl']").click(function(event) { //
			oldDataPicker.show(function(items) {
				$$("input[name='hllqnl']").val(JSON.stringify(items[0].text).replace(/"/g, ""));
			});
		});
		/*领取方式*/
		
		
		lqStatusPicker.setData(fsData);
		$$("input[name='lqfs']").click(function(event) { //
			lqStatusPicker.show(function(items) {
				$$("input[name='lqfs']").val(JSON.stringify(items[0].text).replace(/"/g, ""));
			});
		});
		
		
		/*领取类型*/
		lqTypePicker.setData(lxData);
		$$("input[name='hllqlx']").click(function(event) { //
			lqTypePicker.show(function(items) {
				$$("input[name='hllqlx']").val(JSON.stringify(items[0].text).replace(/"/g, ""));
			});
		});
		
		/*来源*/
		lyPicker.setData(lyData);
		$$(".lys").click(function(event) { //
			var _self = this;
			lyPicker.show(function(items) {
				$$(_self).val(JSON.stringify(items[0].text).replace(/"/g, ""));
			});
		});
		
		



		/*************************户籍*********************************/
		var _getParam = function(obj, param) {
			return obj[param] || '';
		};
		var cityPicker3 = new $.PopPicker({
			layer: 3,
			title: "选择地区"
		});
		cityPicker3.setData(areaData);
		
		//投保人现住址
		$$(".address").click(function(event) { //
			var _self = this;
			cityPicker3.show(function(items) {
				$$(_self).val(_getParam(items[0], 'text') + " " + _getParam(items[1], 'text') + " " + _getParam(items[2], 'text'));
				$$(_self).next().val(_getParam(items[0], 'value'));
				$$(_self).next().next().val(_getParam(items[1], 'value'));
				$$(_self).next().next().next().val(_getParam(items[2], 'value'));
			});
		});
		
		$$(".date").click(function(event) { //
			var _self = this;
			if(_self.picker) {
				_self.picker.show(function(rs) {
					$$(_self).val(rs.text);
					_self.picker.dispose();
					_self.picker = null;
				});
			} else {
				var optionsJson = this.getAttribute('data-options') || '{}';
				var options = JSON.parse(optionsJson);
				var id = this.getAttribute('id');
				_self.picker = new $.DtPicker(options);
				_self.picker.show(function(rs) {
					$$(_self).val(rs.text);
					_self.picker.dispose();
					_self.picker = null;
				});
			}
		});

	});
	$.ready(function() {
		//循环初始化所有下拉刷新，上拉加载。
		$.each(document.querySelectorAll('.mui-slider-group .mui-scroll'), function(index, pullRefreshEl) {
			$(pullRefreshEl).pullToRefresh({
				up: {
					callback: function() {
						var self = this;
						self.endPullUpToRefresh(true);
					},
					contentinit: '&nbsp;', //可以上拉提示信息
					contentdown: '&nbsp;', //上拉结束提示信息
					contentrefresh: '&nbsp;', //上拉进行中提示信息
					contentnomore: '&nbsp;' //上拉无更多信息时提示信息
				}
			});
		});
	});
})(mui, document);
}