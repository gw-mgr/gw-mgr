package com.gewei.zhongshu.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.PageInfo;
import com.gewei.fuwushang.service.IOrderCxService;
import com.gewei.fuwushang.service.IOrderSxService;
import com.gewei.model.OrderSx;
import com.gewei.model.vo.OrderInfoVo;
import com.gewei.zhongshu.service.IOrderInfoService;

/**
 * <p>
 * 服务商的产品管理 前端控制器
 * </p>
 *
 * @author zhixuan.wang
 * @since 2018-01-29
 */
@Controller
@RequestMapping("/mgr/tOrderManage")
public class TOrderManageController extends BaseController {
	@Autowired
	private IOrderInfoService iOrderInfoServiceImpl;
	@Autowired
	private IOrderSxService iOrderSxServiceImpl;
	@Autowired
	private IOrderCxService iOrderCxServiceImpl;

	@GetMapping("/manage")
	public String manager() {
		return "admin/orderManage/tOrderManage";
	}

	@PostMapping("/orderList")
	@ResponseBody
	public PageInfo orderList(Integer page, Integer rows, String sort, String order) throws UnsupportedEncodingException {
		TreeSet<String> orderIdSet = new TreeSet<String>((x, y) -> x.compareTo(y));
		// 寿险订单查询
		EntityWrapper<OrderSx> sxEW = new EntityWrapper<OrderSx>();
		List<OrderSx> sxList = iOrderSxServiceImpl.selectList(sxEW);
		for (OrderSx orderSx : sxList) {
			
		}
		// 财险订单查询
		// 普通订单查询
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		return pageInfo;
	}

	@GetMapping("/orderInfo")
	public String orderInfo(Model model, String orderType) {
		model.addAttribute("orderType", orderType);
		return "admin/orderManage/tOrderInfoList";
	}

	@PostMapping("/orderTypeList")
	@ResponseBody
	public Object getOrderTypeList() {
		List<Map<String, String>> orderTypeList = iOrderInfoServiceImpl.getOrderTypeList();
		return orderTypeList;
	}

	@GetMapping("/ajaxData")
	@ResponseBody
	public String ajaxData() {
		Map<String, Long> result = new HashMap<String, Long>();
		// 订单总数
		long orderTotalNum = iOrderInfoServiceImpl.getOrderTotalNum();
		result.put("orderTotalNum", orderTotalNum);
		// 成交总量
		long orderTotalMoney = iOrderInfoServiceImpl.getOrderTotalMoney();
		result.put("orderTotalMoney", orderTotalMoney);
		// 昨日订单数
		long yesterdayOrderTotalNum = iOrderInfoServiceImpl.getYesterdayOrderTotalNum();
		result.put("yesterdayOrderTotalNum", yesterdayOrderTotalNum);
		// 昨日成交数
		long yesterdayOrderTotalMoney = iOrderInfoServiceImpl.getYesterdayOrderTotalMoney();
		result.put("yesterdayOrderTotalMoney", yesterdayOrderTotalMoney);
		// 各类型订单，昨日成交数
		Map<String, Long> yesterdayOrderNum = iOrderInfoServiceImpl.getYesterdayOrderNumOfAllOrderType();
		result.putAll(yesterdayOrderNum);
		return JSON.toJSONString(result);
	}

	@PostMapping("/dataGrid")
	@ResponseBody
	public PageInfo dataGrid(OrderInfoVo orderInfoVo, Integer page, Integer rows, String sort, String order) throws UnsupportedEncodingException {
		System.out.println("orderInfoVo:" + JSON.toJSONString(orderInfoVo));
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = getCondition(orderInfoVo);
		pageInfo.setCondition(condition);
		iOrderInfoServiceImpl.getOrderInsDataGroupByProvince(pageInfo);
		return pageInfo;
	}

	private Map<String, Object> getCondition(OrderInfoVo orderInfoVo) throws UnsupportedEncodingException {
		Map<String, Object> condition = new HashMap<String, Object>();
		if (orderInfoVo.getCreateStartTime() != null && !"".equals(orderInfoVo.getCreateStartTime())) {
			String createStartTime = orderInfoVo.getCreateStartTime().replaceAll("-", "") + "000000";
			condition.put("createStartTime", createStartTime);
		}
		if (orderInfoVo.getCreateEndTime() != null && !"".equals(orderInfoVo.getCreateEndTime())) {
			String createEndTime = orderInfoVo.getCreateEndTime().replaceAll("-", "") + "000000";
			condition.put("createEndTime", createEndTime);
		}
		if (orderInfoVo.getProvince() != null && !"".equals(orderInfoVo.getProvince()) && !"省/直辖市".equals(orderInfoVo.getProvince())) {
			condition.put("province", orderInfoVo.getProvince());
		}
		if (orderInfoVo.getCity() != null && !"".equals(orderInfoVo.getCity()) && !"城市".equals(orderInfoVo.getCity())) {
			condition.put("city", orderInfoVo.getCity());
		}
		if (orderInfoVo.getOrderType() != null && !"".equals(orderInfoVo.getOrderType())) {
			condition.put("orderType", orderInfoVo.getOrderType());
		}
		String keywordInfo = orderInfoVo.getKeywordInfo();
		if (keywordInfo != null && !"".equals(keywordInfo)) {
			condition.put(orderInfoVo.getKeywordType(), keywordInfo);
		}
		return condition;
	}
}
