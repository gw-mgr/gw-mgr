package com.gewei.wx.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.Result;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.UUIDUtil;
import com.gewei.fuwushang.service.IOrderInfoService;
import com.gewei.fuwushang.service.ITProductManageService;
import com.gewei.model.OrderInfo;
import com.gewei.model.TCustomerBasicinfo;
import com.gewei.model.TMemberBasicinfo;
import com.gewei.model.TMerchantManage;
import com.gewei.model.TProductManage;
import com.gewei.model.vo.DKProductVo;
import com.gewei.model.vo.OrderInfoVo;
import com.gewei.wx.service.ICommonService;
import com.gewei.wx.service.ITMemberBasicinfoService;
import com.gewei.wx.service.IWeixinPayService;
import com.gewei.wx.service.IXianShangYeWuService;
import com.gewei.zhongshu.service.ITMerchantManageService;
import com.gewei.zhongshu.service.ITMerchantProductManageService;

@Controller
@RequestMapping("/xianshangyewu")
public class XianShangYeWuController extends BaseController {
	@Autowired
	private IXianShangYeWuService iXianShangYeWuServiceImpl;
	@Autowired
	private ICommonService iCommonServiceImpl;
	@Autowired
	private ITMerchantProductManageService iTMerchantProductManageServiceImpl;
	@Autowired
	private ITMerchantManageService tMerchantManageServiceImpl;
	@Autowired
	private IWeixinPayService iWeixinPayServiceImpl;
	@Autowired
	private IOrderInfoService iOrderInfoServiceImpl;
	@Autowired
	private ITProductManageService iTProductManageServiceImpl;

	// 车务代办商家地区列表
	@RequestMapping("/getROOTCDMerchantGrantAreaList")
	@ResponseBody
	public Object getROOTCDMerchantGrantAreaList() {
		Result result = new Result();
		try {
			Object cwdbAreaList = iXianShangYeWuServiceImpl.getROOTCDMerchantGrantAreaList();
			result.setSuccess(true);
			result.setObj(cwdbAreaList);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("服务器内部错误");
			result.setObj(null);
			return result;
		}
	}

	// 车务代办商家选择
	@RequestMapping("/getCDMerchantList")
	@ResponseBody
	public Object getCDMerchantList(String productType, String grantArea, Long page, Long rows) {
		Result result = new Result();
		page = page == null ? 1 : page;
		rows = rows == null ? 10 : rows;
		long start = (page - 1) * rows;
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("start", start);
			condition.put("rows", rows);
			if (!StringUtils.isEmpty(productType)) {
				condition.put("productType", productType);
			}
			if (!StringUtils.isEmpty(grantArea)) {
				condition.put("grantArea", grantArea);
			}
			List<Map<String, String>> cwdbMerchantList = iXianShangYeWuServiceImpl.getROOTCDList(condition);
			long totalCount = iXianShangYeWuServiceImpl.getROOTCDCount(condition);
			result.setSuccess(true);
			result.setObj(cwdbMerchantList);
			result.setTotalCount(totalCount);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("服务器内部错误");
			result.setObj(null);
			return result;
		}
	}

	// 汽车美容商家选择
	@RequestMapping("/getCMMerchantList")
	@ResponseBody
	public Object getCMMerchantList(Integer page, Integer rows) {
		Result result = new Result();
		page = page == null ? 1 : page;
		rows = rows == null ? 10 : rows;
		Integer start = (page - 1) * rows;
		try {
			List<Map<String, String>> qcmrMerchantList = iXianShangYeWuServiceImpl.getROOTCMMerchantList(start, rows);
			long totalCount = iXianShangYeWuServiceImpl.getROOTCMMerchantCount();
			result.setSuccess(true);
			result.setObj(qcmrMerchantList);
			result.setTotalCount(totalCount);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("服务器内部错误");
			result.setObj(null);
			return result;
		}
	}

	// 商家详情
	@RequestMapping("/getMerchantInfo")
	@ResponseBody
	public Object getMerchantInfoById(String merchantId) {
		Result result = new Result();
		try {
			TMerchantManage tMerchantManage = tMerchantManageServiceImpl.getMerchantInfoById(merchantId);
			result.setSuccess(true);
			result.setObj(tMerchantManage);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("服务器内部错误");
			result.setObj(null);
			return result;
		}
	}

	// 商家产品列表
	@RequestMapping("/getProductListByMerchantId")
	@ResponseBody
	public Object getProductListByMerchantId(String merchantId, String orderType) {
		Result result = new Result();
		try {
			Map<String, String> condition = new HashMap<String, String>();
			condition.put("merchantId", merchantId);
			if (!StringUtils.isEmpty(orderType)) {
				if ("ROOTCD".equals(orderType)) {
					condition.put("ROOTCD", orderType);
				} else if ("ROOTCM".equals(orderType)) {
					condition.put("ROOTCM", orderType);
				}
			}
			List<Map<String, String>> productListOfMerchant = iXianShangYeWuServiceImpl.getProductListByMerchantId(condition);
			result.setSuccess(true);
			result.setObj(productListOfMerchant);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("服务器内部错误");
			result.setObj(null);
			return result;
		}
	}

	// 商家产品详细信息
	@RequestMapping("/getProductInfoById")
	@ResponseBody
	public Object getProductInfoById(String productId, Float longitude, Float latitude) {
		Result result = new Result();
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("productId", productId);
			condition.put("longitude", longitude);
			condition.put("latitude", latitude);// 纬度
			Map<String, Object> productInfo = iXianShangYeWuServiceImpl.getProductInfoById(condition);
			result.setSuccess(true);
			result.setObj(productInfo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("服务器内部错误");
			result.setObj(null);
			return result;
		}
	}

	// 贷款产品筛选
	@RequestMapping("/getDKProductList")
	@ResponseBody
	public Object getDKProductList(DKProductVo dKProductVo) {
		Integer page = dKProductVo.getPage();
		Integer rows = dKProductVo.getRows();
		Result result = new Result();
		page = page == null ? 1 : page;
		rows = rows == null ? 10 : rows;
		Integer start = (page - 1) * rows;
		dKProductVo.setStart(start);
		System.out.println("dKProductVo" + JSON.toJSONString(dKProductVo));
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("start", start);
			condition.put("rows", rows);
			// 收入
			condition.put("dMoney", dKProductVo.getdMoney());
			condition.put("dIncome", dKProductVo.getdIncome());
			// 婚姻
			String dmarryFlag = dKProductVo.getdMarryFlag();
			if ("0".equals(dmarryFlag)) {
				// 无，要求：0,1
				condition.put("dMarryFlag", 1);
			} else if ("1".equals(dmarryFlag)) {
				// 离婚，要求：0,1
				condition.put("dMarryFlag", 1);
			} else if ("2".equals(dmarryFlag)) {
				// 已婚，要求：2
				condition.put("dMarryFlag", 2);
			}
			// 社保
			String socialFlag = dKProductVo.getdSocialFlag();
			if ("1".equals(socialFlag)) {
				// 有社保，无过滤
			} else if ("0".equals(socialFlag)) {
				// 无社保，社保项要求为：0
				condition.put("dSocialFlag", 0);
			}
			// 房产
			String houseFlag = dKProductVo.getdHouseFlag();
			if ("0".equals(houseFlag)) {
				// 无，房产要求为：0
				condition.put("dHouseFlag", 0);
			} else if ("1".equals(houseFlag)) {
				// 按揭，房产要求为：0或1
				condition.put("dHouseFlag", 1);
			} else if ("2".equals(houseFlag)) {
				// 全款，无过滤
			}
			// 车
			String carFlag = dKProductVo.getdCarFlag();
			if ("0".equals(carFlag)) {
				// 无，要求为：0
				condition.put("dCarFlag", 0);
			} else if ("1".equals(carFlag)) {
				// 按揭，要求为：0或1
				condition.put("dCarFlag", 1);
			} else if ("2".equals(houseFlag)) {
				// 全款，无过滤
			}
			System.out.println("condition" + JSON.toJSONString(condition));
			List<Map<String, String>> dkProductList = iXianShangYeWuServiceImpl.getDKProductList(condition);
			long totalCount = iXianShangYeWuServiceImpl.getDKProductCount(condition);
			result.setSuccess(true);
			result.setObj(dkProductList);
			result.setTotalCount(totalCount);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("服务器内部错误");
			result.setObj(null);
			return result;
		}
	}

	// 创建二手车订单
	@RequestMapping("/createESCOrder")
	@ResponseBody
	@Transactional
	public Object createESCOrder(OrderInfoVo orderInfovo) {
		Result result = new Result();
		try {
			// 校验客户是否已经存在,若不存在则添加；若存在，则更新客户信息
			TCustomerBasicinfo tCustomerBasicinfo = new TCustomerBasicinfo();
			tCustomerBasicinfo.setUserName(orderInfovo.getUserName());
			tCustomerBasicinfo.setTelephone(orderInfovo.getConTel());
			tCustomerBasicinfo.setCarNum(orderInfovo.getCarNum());
			String customerId = iCommonServiceImpl.checkCustomerIsExsit(tCustomerBasicinfo);
			// 创建二手车订单
			OrderInfo orderInfo = new OrderInfo();
			String currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString();
			String uuid = UUIDUtil.getUUID32().substring(0, 8);
			String orderId = orderInfovo.getOrderType() + currTime + uuid;
			orderInfo.setOrderId(orderId);
			orderInfo.setOrderType(orderInfovo.getOrderType());
			orderInfo.setUserId(customerId);// 客户编号
			orderInfo.setConTel(orderInfovo.getConTel() == null ? "" : orderInfovo.getConTel());
			orderInfo.setTradeAddr(orderInfovo.getTradeAddr());
			orderInfo.setCreateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
			orderInfo.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
			orderInfo.setOrderFlag("01");// 未接单
			orderInfo.setCommission(0L);
			orderInfo.setCommissionFor("");// 佣金对象
			orderInfo.setCommissionRate(0F);
			iXianShangYeWuServiceImpl.createESCOrder(orderInfo);
			result.setObj(orderInfo.getOrderId());
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("服务器内部错误");
			result.setObj(null);
			return result;
		}
	}

	// 创建车务代办、贷款、汽车美容订单
	@RequestMapping("/createCommonOrder")
	@ResponseBody
	@Transactional
	public Object createCommonOrder(Long orderMoney, String userName, String orderType, String userId, String conTel, String productId, String merchantId) {
		Result result = new Result();
		try {
			// 产品校验
			EntityWrapper<TProductManage> entityWrapper = new EntityWrapper<TProductManage>();
			entityWrapper.eq("PRODUCT_ID", productId);
			TProductManage productManage = iTProductManageServiceImpl.selectOne(entityWrapper);
			// 订单金额校验
			long currPrice = productManage.getCurrPrice();
			if (("ROOTCM".equals(orderType) || orderType.contains("ROOTCD")) && orderMoney < currPrice) {
				return renderError("订单金额错误");
			}
			// 创建订单
			OrderInfo orderInfo = new OrderInfo();
			String currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString();
			String uuid = UUIDUtil.getUUID32().substring(0, 8);
			String orderId = orderType + currTime + uuid;
			orderInfo.setOrderId(orderId);
			orderInfo.setOrderType(orderType);
			orderInfo.setUserId(userId);
			orderInfo.setOrderMoney(currPrice);
			orderInfo.setConTel(conTel == null ? "" : conTel);
			orderInfo.setProductId(productId);
			orderInfo.setMerchantId(merchantId);
			orderInfo.setCreateTime(currTime);
			orderInfo.setUpdateTime(currTime);
			orderInfo.setOrderFlag("01");// 未接单
			Map<String, Object> productInfo = iTMerchantProductManageServiceImpl.getProductInfoById(productId);
			orderInfo.setCommission((Long) productInfo.get("COMMISSION"));
			orderInfo.setCommissionFor("");// 佣金对象
			orderInfo.setCommissionRate((Float) productInfo.get("COMMISSION_RATE"));
			iXianShangYeWuServiceImpl.createCommonOrder(orderInfo);
			// 穿件佣金记录及佣金账务
			result.setObj(orderInfo.getOrderId());
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("服务器内部错误");
			result.setObj(null);
			return result;
		}
	}
	@Autowired
	private ITMemberBasicinfoService iTMemberBasicinfoServiceImpl;

	// 订单支付
	@RequestMapping("/payOrder")
	@ResponseBody
	@Transactional
	public Object payOrder(String userId, String orderId, String telphone) {
		Result result = new Result();
		try {
			// 订单查询
			EntityWrapper<OrderInfo> entityWrapper = new EntityWrapper<OrderInfo>();
			entityWrapper.eq("ORDER_ID", orderId);
			OrderInfo orderInfo = iOrderInfoServiceImpl.selectOne(entityWrapper);
			if (orderInfo == null) {
				return renderError("订单不存在");
			}
			// 产品校验
			EntityWrapper<TProductManage> entityWrapper2 = new EntityWrapper<TProductManage>();
			entityWrapper.eq("PRODUCT_ID", orderInfo.getProductId());
			TProductManage productManage = iTProductManageServiceImpl.selectOne(entityWrapper2);
			if (productManage == null) {
				return renderError("产品不存在");
			}
			// 用户openId
			EntityWrapper<TMemberBasicinfo> entityWrapper3 = new EntityWrapper<TMemberBasicinfo>();
			entityWrapper.eq("USER_ID", userId);
			TMemberBasicinfo memberBasicinfo = iTMemberBasicinfoServiceImpl.selectOne(entityWrapper3);
			if (memberBasicinfo.getOpenId().isEmpty()) {
				return renderError("需要微信授权");
			}
			// 订单金额校验
			double orderMoney = orderInfo.getOrderMoney();
			iWeixinPayServiceImpl.pay(memberBasicinfo.getOpenId(), productManage.getProductName(), orderId, orderMoney);
			// 创建订单
			// 穿件佣金记录及佣金账务
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("服务器内部错误");
			result.setObj(null);
			return result;
		}
	}
}
