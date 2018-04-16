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
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.Result;
import com.gewei.commons.utils.DateUtil;
import com.gewei.fuwushang.service.ITCustomerBasicinfoService;
import com.gewei.model.OrderInfo;
import com.gewei.model.TCustomerBasicinfo;
import com.gewei.model.vo.EditOrderInfo;
import com.gewei.model.vo.OrderInfoVo;
import com.gewei.wx.service.IDingDanZhuiZongService;

@Controller
@RequestMapping("/dingdanzhuizong")
public class DingDanZhuiZongController extends BaseController {
	@Autowired
	private IDingDanZhuiZongService iDingDanZhuiZongServiceImpl;
	@Autowired
	private ITCustomerBasicinfoService iTCustomerBasicinfoServiceImpl;

	// 查询用户的-订单追踪概述信息
	@RequestMapping("/getOrderSummaryByUserId")
	@ResponseBody
	public Object getOrderSummaryByUserId(String userId) {
		Result result = new Result();
		try {
			// 用户校验
			// 信息查询
			List<Map<String, String>> orderSummary = iDingDanZhuiZongServiceImpl.getOrderSummaryByUserId(userId);
			result.setSuccess(true);
			result.setObj(orderSummary);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("内部错误");
		}
		return result;
	}

	// 不同类型的订单列表+条件筛选
	@RequestMapping("/getOrderListByOrderTypeAndUserId")
	@ResponseBody
	public Object getOrderListByOrderTypeAndUserId(OrderInfoVo orderInfoVo) {
		Result result = new Result();
		Integer start = (orderInfoVo.getPage() - 1) * orderInfoVo.getRows();
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			String orderType = orderInfoVo.getOrderType();
			condition.put("start", start);
			condition.put("rows", orderInfoVo.getRows());
			condition.put("orderType", orderType);
			String userId = orderInfoVo.getUserId();
			condition.put("userId", userId);
			String createTime = orderInfoVo.getCreateTime();
			String status = orderInfoVo.getStatus();
			if (!StringUtils.isEmpty(createTime)) {
				condition.put("createTime", createTime);
			}
			if (!StringUtils.isEmpty(status)) {
				condition.put("status", status);
			}
			List<Map<String, String>> orderList = iDingDanZhuiZongServiceImpl.getOrderListByOrderTypeAndUserId(condition);
			long totalCount = iDingDanZhuiZongServiceImpl.getOrderCountByOrderTypeAndUserId(condition);
			result.setSuccess(true);
			result.setTotalCount(totalCount);
			result.setObj(orderList);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("内部错误");
		}
		return result;
	}

	// 订单详细信息，不同类型的订单
	@RequestMapping("/getOrderDetailInfoByOrderId")
	@ResponseBody
	public Object getOrderDetailInfoByOrderId(String orderId) {
		Result result = new Result();
		try {
			String orderType = iDingDanZhuiZongServiceImpl.getOrderTypeByOrderId(orderId);
			orderType = orderType == null ? "" : orderType;
			Map<String, Object> orderInfoMap = new HashMap<String, Object>();
			if (orderType.contains("ROOTCD")) {
				// 车务代办
				orderInfoMap = iDingDanZhuiZongServiceImpl.getCDOrderDetailInfoByOrderId(orderId);
			} else if ("ROOTQCM".equals(orderType) || "ROOTQCS".equals(orderType)) {
				// 二手车
				orderInfoMap = iDingDanZhuiZongServiceImpl.getESCOrderDetailInfoByOrderId(orderId);
			} else if ("ROOTCM".equals(orderType)) {
				// 汽车美容
				orderInfoMap = iDingDanZhuiZongServiceImpl.getCMOrderDetailInfoByOrderId(orderId);
			} else if ("ROOTDK".equals(orderType)) {
				// 贷款
				orderInfoMap = iDingDanZhuiZongServiceImpl.getDKOrderDetailInfoByOrderId(orderId);
			}
			result.setObj(orderInfoMap);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("内部错误");
		}
		return result;
	}

	// 订单编辑
	@RequestMapping("/editOrderDetailInfoByOrderId")
	@ResponseBody
	@Transactional
	public Object editOrderDetailInfoByOrderId(EditOrderInfo editOrderInfo) {
		try {
			System.out.println("【订单追踪，订单编辑，请求参数】：" + JSON.toJSONString(editOrderInfo));
			// 用户安全性校验
			String userId = editOrderInfo.getUserId();
			// 订单校验
			String orderId = editOrderInfo.getOrderId();
			OrderInfo orderInfo = iDingDanZhuiZongServiceImpl.getOrderInfoByOrderId(orderId, userId);
			if (orderInfo == null)
				return renderError("订单不存在");
			// 客户校验
			editOrderInfo.setCustomerId(orderInfo.getUserId());
			System.out.println("【订单追踪，订单编辑，客户编号】：" + orderInfo.getUserId());
			TCustomerBasicinfo tCustomerBasicinfo = iTCustomerBasicinfoServiceImpl.getCustomerInfoByUserId(orderInfo.getUserId());
			if (tCustomerBasicinfo == null)
				return renderError("客户不存在");
			// 客户信息编辑
			editOrderInfo.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
			iTCustomerBasicinfoServiceImpl.editCustomerInfo(editOrderInfo);
			iDingDanZhuiZongServiceImpl.editOrderDetailInfo(editOrderInfo);
			System.out.println("【订单追踪，订单编辑，订单编辑】：" + JSON.toJSONString(editOrderInfo));
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("添加失败！");
		}
		return renderSuccess("添加成功！");
	}

	// 修改订单状态
	@RequestMapping("/updateOrderStatusByOrderId")
	@ResponseBody
	public Object updateOrderStatusByOrderId(String orderId, String orderFlag, String userId) {
		try {
			// 用户安全性校验
			// 订单校验
			OrderInfo orderInfo = iDingDanZhuiZongServiceImpl.getOrderInfoByOrderId(orderId, userId);
			if (orderInfo == null)
				return renderError("订单不存在");
			// 修改订单状态
			iDingDanZhuiZongServiceImpl.updateOrderStatusByOrderId(orderId, userId, orderFlag);
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("修改失败！");
		}
		return renderSuccess("修改成功！");
	}
}
