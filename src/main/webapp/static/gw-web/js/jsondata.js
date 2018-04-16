var json_data = {
		goods:{
			loanProduct:"",//贷款产品
			channelType:"",//渠道类型
			clienteleType:"",//客户类型
			loanMoney:"",//贷款金额
			coId:"",//运营商ID
			coRelationId:"",//套餐不能为空
			activityType:"",//活动类型
			goodsKind:"",//门店Id
		}
	};
var save_data = {
		isSave:{//0 已保存  1 未保存
			userBaseInfo:"1",//用户基本信息
			repaymentInfo:"1",//还款账户信息
			workInfo:"1",//工作信息
			contactInfo:"1",//联系人信息
			goodsInfo:"1",//商品分期信息
			attachInfo:"1",//归档信息
			assistInfo:"1",//协省信息
			tempSign:"1",//签约
		}
	};
var wedlockdata = [{//婚姻状况
	value: '0',
	text: '已婚'
}, {
	value: '1',
	text: '未婚'
}, {
	value: '2',
	text: '离异'
}];
var creTypedata=[{
	value: '1',
	text: '居民身份证'
}, {
	value: '2',
	text: '毕业证'
}, {
	value: '3',
	text: '出生证'
}, {
	value: '4',
	text: '独生子女证'
}, {
	value: '5',
	text: '护照'
}, {
	value: '6',
	text: '驾驶证'
}, {
	value: '7',
	text: '结婚证'
}, {
	value: '8',
	text: '警官证'
}, {
	value: '9',
	text: '离婚证'
}, {
	value: '10',
	text: '签证'
}, {
	value: '11',
	text: '学生证'
}, {
	value: '12',
	text: '执行公务证'
}];
var sexdata=[{
	value: '0',
	text: '男'
}, {
	value: '1',
	text: '女'
}];


var populationtypedata=[{
	value: '0',
	text: '城镇'
}, {
	value: '1',
	text: '农村'
}];

var benefitdata=[{//受益人顺序
	value: '1',
	text: '1'
}, {
	value: '2',
	text: '2'
},{
	value: '3',
	text: '3'
}, {
	value: '4',
	text: '4'
},{
	value: '5',
	text: '5'
}, {
	value: '6',
	text: '6'
}, {
	value: '7',
	text: '7'
}, {
	value: '8',
	text: '8'
}, {
	value: '9',
	text: '9'
}, {
	value: '10',
	text: '10'
}, {
	value: '11',
	text: '11'
}, {
	value: '12',
	text: '12'
}, {
	value: '13',
	text: '13'
}, {
	value: '14',
	text: '14'
}, {
	value: '15',
	text: '15'
}, {
	value: '16',
	text: '16'
}, {
	value: '17',
	text: '17'
}, {
	value: '18',
	text: '18'
}, {
	value: '19',
	text: '19'
}, {
	value: '20',
	text: '20'
}, {
	value: '21',
	text: '21'
}, {
	value: '22',
	text: '22'
}, {
	value: '23',
	text: '23'
}, {
	value: '24',
	text: '24'
}, {
	value: '25',
	text: '25'
}, {
	value: '26',
	text: '26'
}, {
	value: '27',
	text: '27'
}, {
	value: '28',
	text: '28'
}, {
	value: '29',
	text: '29'
}, {
	value: '30',
	text: '30'
}, {
	value: '31',
	text: '31'
}];
var paymentsdata=[{//交费频率
	value: '0',
	text: '年交'
}, {
	value: '1',
	text: '趸交'
}, {
	value: '1',
	text: '月交（月交首付需交纳2个月保险费）'
}, {
	value: '1',
	text: '其他'
}];


var isdata=[{//是否
	value: '01',
	text: '是'
}, {
	value: '02',
	text: '否'
}];

var paydata=[{//是否
	value: '0',
	text: '银行转账'
}, {
	value: '1',
	text: '其他'
}];


var yeardata=[{//是否
	value: '10',
	text: '十年'
}, {
	value: '15',
	text: '十五年'
}, {
	value: '20',
	text: '二十年'
}];



var gogetdata=[{//红利领取方式
	value: '1',
	text: '累计生息'
}, {
	value: '2',
	text: '现金领取'
}, {
	value: '3',
	text: '红利转万能险账户'
}, {
	value: '4',
	text: '抵交保险费'
}, {
	value: '5',
	text: '其他'
}];


var oldData=[{//领取年龄
	value: '50',
	text: '50岁'
}, {
	value: '55',
	text: '55岁'
}, {
	value: '60',
	text: '60岁'
}, {
	value: '65',
	text: '65岁'
}, {
	value: '00',
	text: '其他'
}];

var fsData=[{//领取方式
	value: '00',
	text: '年领'
}, {
	value: '01',
	text: '月领'
}, {
	value: '02',
	text: '其他'
}];
var lxData=[{//领取类型
	value: '00',
	text: '平准年金'
}, {
	value: '01',
	text: '增额年金'
}, {
	value: '02',
	text: '其他'
}];


var lyData=[{//来源
	value: '01',
	text: '工薪'
}, {
	value: '02',
	text: '个体'
}, {
	value: '03',
	text: '私营'
}, {
	value: '04',
	text: '房屋出租'
}, {
	value: '05',
	text: '证券投入'
}, {
	value: '06',
	text: '银行利息'
}, {
	value: '07',
	text: '其他'
}];



var servicesTypedata=[{//业务类型
	value: 'ROOTQCM',
	text: '我要买车'
}, {
	value: 'ROOTQCS',
	text: '我要卖车'
}];

var monthIncomedata = [{//月收入
	value: '1',
	text: '1200-3000'
}, {
	value: '2',
	text: '3000-6000'
}, {
	value: '3',
	text: '6000-10000'
}, {
	value: '4',
	text: '10000-30000'
}, {
	value: '5',
	text: '3万以上'
}];
var loandata = [{//有无车辆/有无房产
	value: '0',
	text: '无'
}, {
	value: '1',
	text: '按揭'
}, {
	value: '2',
	text: '全款'
}];

var ishavedata = [{//有无
	value: '0',
	text: '无'
}, {
	value: '1',
	text: '有'
}];


var workdata = [{//有无
	value: '01',
	text: '民营'
}, {
	value: '02',
	text: '国企'
}, {
	value: '03',
	text: '公务员'
}, {
	value: '04',
	text: '自由职业'
}];


var carTypeData = [{//有无
	value: '1',
	text: '家庭自用车'
}, {
	value: '2',
	text: '公司自用车'
}, {
	value: '3',
	text: '党机关用车'
}, {
	value: '4',
	text: '非营运货车'
}, {
	value: '5',
	text: '工程机械'
}, {
	value: '6',
	text: '摩托车'
}, {
	value: '7',
	text: '特一'
}, {
	value: '8',
	text: '特二'
}, {
	value: '9',
	text: '特三'
}, {
	value: '10',
	text: '特四'
}, {
	value: '11',
	text: '营运货车'
}, {
	value: '2',
	text: '营运客车'
}];



var merchantTypeData = [{//服务范围
	value: 'ROOTCC',
	text: '财产保险'
}, {
	value: 'ROOTRS',
	text: '人寿保险'
}, {
	value: 'ROOTLC',
	text: '理财产品'
}, {
	value: 'ROOTDK',
	text: '贷款业务'
}, {
	value: 'ROOTQCM',
	text: '二手汽车(买)'
}, {
	value: 'ROOTQCS',
	text: '二手汽车(卖)'
},{
	value: 'ROOTCD',
	text: '车务代办'
}, {
	value: 'ROOTCM',
	text: '汽车美容'
}];
var orderFlagData = [{//订单状态
	value: '01',
	text: '未接单',
	color:'red'
}, {
	value: '02',
	text: '接单未放款',
	color:'blue'
}, {
	value: '03',
	text: '已放款',
	color:'green'
}, {
	value: '04',
	text: '已收回',
	color:'green'
}, {
	value: '05',
	text: '未生效',
	color:'green'
}, {
	value: '06',
	text: '投资中',
	color:'yellow'
},{
	value: '07',
	text: '投资结束',
	color:'green'
}, {
	value: '08',
	text: '已接单',
	color:'green'
}, {
	value: '09',
	text: '验车/报价',
	color:'blue'
}, {
	value: '10',
	text: '已成交',
	color:'green'
}, {
	value: '11',
	text: '已取消',
	color:'gray'
},{
	value: '12',
	text: '处理完毕',
	color:'green'
}];

var orderData = [{//订单状态
	value: '01',
	text: '未接单',
	color:'red'
}, {
	value: '08',
	text: '已接单',
	color:'green'
}, {
	value: '11',
	text: '已取消',
	color:'gray'
},{
	value: '12',
	text: '处理完毕',
	color:'green'
}];
	
var xcOrderData = [{//订单状态
	value: '02',
	text: '未放款',
	color:'red'
}, {
	value: '03',
	text: '已放款',
	color:'green'
}, {
	value: '04',
	text: '已收回',
	color:'green'
}];

var xcOrderFlagData = [{//订单状态
	value: '01',
	text: '未成交',
	color:'red'
}, {
	value: '02',
	text: '已成交',
	color:'green'
}, {
	value: '04',
	text: '报价申请中',
	color:'green'
}];

var relationshipData = [{//与被保人关系
	value: '01',
	text: '配偶'
}, {
	value: '02',
	text: '子女'
}, {
	value: '03',
	text: '父母'
}, {
	value: '04',
	text: '本人'
}, {
	value: '05',
	text: '其他'
}];


var addressTypeData = [{//住址
	value: '01',
	text: '同投保人'
}, {
	value: '02',
	text: '同被保人'
}, {
	value: '03',
	text: '其他'
}];

var orderTypeData = [{//产品类型
	value: '',
	text: '全部'
}, {
	value: 'ROOTCD',
	text: '车务代办'
}, {
	value: 'ROOTCM',
	text: '汽车美容'
}];