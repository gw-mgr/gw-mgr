package com.gewei.wx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.druid.util.StringUtils;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.Result;
import com.gewei.fuwushang.service.ITCustomerBasicinfoService;
import com.gewei.model.EditOrderCx;
import com.gewei.model.TCustomerBasicinfo;
import com.gewei.model.vo.OrderInfoVo;
import com.gewei.wx.service.IBaoXianDingDanService;
import com.gewei.wx.service.IShanJiaFuWuService;

@Controller
@RequestMapping("/baoxiandingdan")
public class BaoXianDingDanController extends BaseController {
	@Autowired
	private IBaoXianDingDanService iBaoXianDingDanServiceImpl;
	@Autowired
	private ITCustomerBasicinfoService iITCustomerBasicinfoServiceImpl;
	@Autowired
	private IShanJiaFuWuService iShanJiaFuWuServiceImpl;

	// 会员各类型订单统计
	@RequestMapping("/bxOrderStatistic")
	@ResponseBody
	public Object getBXOrderSummaryById(String userId) {
		Result result = new Result();
		try {
			// 会员编号校验
			if (StringUtils.isEmpty(userId))
				return renderError("会员编号不能为空");
			TCustomerBasicinfo tCustomerBasicinfo = iITCustomerBasicinfoServiceImpl.getCustomerInfoByUserId(userId);
			if (tCustomerBasicinfo == null)
				return renderError("会员不存在");
			// 保险订单统计
			Map<String, Object> merchantOrderSummaryList = iBaoXianDingDanServiceImpl.getBXOrderSummaryById(userId);
			result.setSuccess(true);
			result.setObj(merchantOrderSummaryList);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("内部错误");
		}
		return result;
	}

	// 各类型全部订单列表
	@RequestMapping("/getAllBXOrderListByOrderType")
	@ResponseBody
	public Object getAllBXOrderListByOrderType(OrderInfoVo orderInfoVo, Long page, Long rows) {
		Result result = new Result();
		page = page == null ? 1 : page;
		rows = rows == null ? 10 : rows;
		Long start = (page - 1) * rows;
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("start", start);
			condition.put("rows", rows);
			String orderType = orderInfoVo.getOrderType();
			condition.put("orderType", orderType);
			condition.put("userId", orderInfoVo.getUserId());
			String createTime = orderInfoVo.getCreateTime();
			String status = orderInfoVo.getStatus();
			if (!StringUtils.isEmpty(createTime)) {
				condition.put("createTime", createTime);
			}
			if (!StringUtils.isEmpty(status)) {
				condition.put("status", status);
			}
			List<Map<String, String>> orderList;
			long totalCount;
			if ("ROOTRS".equals(orderType)) {
				// 人寿
				orderList = iBaoXianDingDanServiceImpl.getRSOrderListOfSaleManByOrderType(condition);
				totalCount = iBaoXianDingDanServiceImpl.getRSOrderCountOfSaleManByOrderType(condition);
			} else if (orderType.contains("ROOTCC")) {
				// 财险
				orderList = iBaoXianDingDanServiceImpl.getCXOrderListOfSaleManByOrderType(condition);
				totalCount = iBaoXianDingDanServiceImpl.getCXOrderCountOfSaleManByOrderType(condition);
			} else {
				return renderError("订单类型参数有误");
			}
			result.setTotalCount(totalCount);
			result.setSuccess(true);
			result.setObj(orderList);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("内部错误");
		}
		return result;
	}

	// 订单详细信息
	@RequestMapping("/getOrderDetailInfoByOrderId")
	@ResponseBody
	public Object getOrderDetailInfoByOrderId(OrderInfoVo orderInfoVo) {
		Result result = new Result();
		try {
			Map<String, String> condition = new HashMap<String, String>();
			Map<String, Object> orderInfoMap;
			String orderType = orderInfoVo.getOrderType();
			if (orderType == null)
				return renderError("订单类型不能是空");
			String orderId = orderInfoVo.getOrderId();
			condition.put("orderId", orderId);
			if ("ROOTRS".equals(orderType)) {
				// 人寿
				orderInfoMap = iShanJiaFuWuServiceImpl.getRSOrderDetailInfoByOrderId(orderId);
			} else if (orderType.contains("ROOTCC")) {
				// 财险
				condition.put("orderType", orderType);
				orderInfoMap = iShanJiaFuWuServiceImpl.getRSOrderDetailInfoByOrderId(orderId);
			} else {
				return renderError("订单类型参数有误");
			}
			result.setSuccess(true);
			result.setObj(orderInfoMap == null ? "订单不存在" : orderInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("内部错误");
		}
		return result;
	}

	// 财险订单信息编辑
	@RequestMapping("/editCXOrderCarInfoByOrderId")
	@ResponseBody
	public Object editCXOrderCarInfoByOrderId(EditOrderCx editOrderCx) {
		try {
			// 用户校验
			String userId = editOrderCx.getUserId();
			// 订单内容编辑
			iBaoXianDingDanServiceImpl.editCXOrderCarInfoByOrderId(editOrderCx);
			return renderSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("参数有误");
		}
	}

	// 订单删除+报价等操作
	@RequestMapping("/updateOrderFlagByOrderId")
	@ResponseBody
	public Object updateOrderFlagByOrderId(String orderId, String orderType, String updateType, String userId) {
		try {
			// 用户校验
			// 目标状态参数校验
			if (updateType == null)
				return renderError("修改类型有误");
			Map<String, String> condition = new HashMap<String, String>();
			if ("delete".equals(updateType)) {
				condition.put("ORDER_FLAG", "03");
			} else if ("price".equals(updateType)) {
				condition.put("ORDER_FLAG", "04");
			} else {
				return renderError("目标状态错误");
			}
			condition.put("orderId", orderId);
			// 订单校验
			Map<String, Object> orderInfoMap;
			if ("ROOTRS".equals(orderType)) {
				// 人寿
				orderInfoMap = iShanJiaFuWuServiceImpl.getRSOrderDetailInfoByOrderId(orderId);
				condition.put("tableName", "order_sx");
			} else if (orderType.contains("ROOTCC")) {
				// 财险
				condition.put("orderType", orderType);
				orderInfoMap = iShanJiaFuWuServiceImpl.getRSOrderDetailInfoByOrderId(orderId);
				condition.put("tableName", "order_cx");
			} else {
				return renderError("订单类型参数有误");
			}
			if (orderInfoMap == null) {
				return renderError("订单不存在");
			}
			iBaoXianDingDanServiceImpl.updateOrderFlagByOrderId(condition);
			return renderSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("参数有误");
		}
	}
}
