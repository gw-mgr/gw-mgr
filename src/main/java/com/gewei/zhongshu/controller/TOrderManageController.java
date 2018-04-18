package com.gewei.zhongshu.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.PageInfo;
import com.gewei.fuwushang.service.IOrderCxService;
import com.gewei.fuwushang.service.IOrderSxService;
import com.gewei.model.TChinaArea;
import com.gewei.model.TMerchantProductCategory;
import com.gewei.model.vo.OrderInfoVo;
import com.gewei.zhongshu.service.IOrderInfoService;
import com.gewei.zhongshu.service.ITChinaAreaService;
import com.gewei.zhongshu.service.ITMerchantProductCategoryService;

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
	@Autowired
	private ITChinaAreaService iTChinaAreaServiceImpl;
	@Autowired
	private ITMerchantProductCategoryService iTMerchantProductCategoryServiceImpl;

	@GetMapping("/manage")
	public String manager() {
		return "admin/orderManage/tOrderManage";
	}

	@PostMapping("/orderList")
	@ResponseBody
	public PageInfo orderList(OrderInfoVo orderInfoVo, String orderTypeQuery, Integer page, Integer rows, String sort, String order, String orderType) throws UnsupportedEncodingException {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		// 封装过滤条件
		HashMap<String, Object> condition = new HashMap<String, Object>();
		String createStartTime = orderInfoVo.getCreateStartTime();
		String createEndTime = orderInfoVo.getCreateEndTime();
		String province = orderInfoVo.getProvince();
		String city = orderInfoVo.getCity();
		String country = orderInfoVo.getCountry();
		String keywordType = orderInfoVo.getKeywordType();
		String keywordInfo = orderInfoVo.getKeywordInfo();
		if (createStartTime != null && !createStartTime.trim().equals("")) {
			createStartTime = createStartTime.replace("-", "") + "000000";
			condition.put("createStartTime", createStartTime);
		}
		if (createEndTime != null && !createEndTime.trim().equals("")) {
			createEndTime = createEndTime.replace("-", "") + "235959";
			condition.put("createEndTime", createEndTime);
		}
		if (province != null && !province.trim().equals("")) {
			condition.put("province", province);
		}
		if (city != null && !city.trim().equals("")) {
			condition.put("city", city);
		}
		if (country != null && !country.trim().equals("")) {
			condition.put("country", country);
		}
		if (orderType != null && !orderType.trim().equals("")) {
			condition.put("orderType", orderType);
		}
		// 分页参数
		int start = (page - 1) * rows;
		condition.put("start", start);
		condition.put("rows", rows);
		condition.put("sort", sort);
		condition.put("order", order);
		if (keywordInfo != null && !keywordInfo.trim().equals("")) {
			condition.put("keywordInfo", keywordInfo);
			condition.put("keywordType", keywordType);
		}
		if (orderTypeQuery != null && !orderTypeQuery.trim().equals("")) {
			condition.put("orderTypeQuery", orderTypeQuery);
		}
		if (orderType.equals("ROOTCC")) {
			// 财险
			System.out.println("【condition】" + JSON.toJSONString(condition));
			List<Map<String, Object>> cxList = iOrderCxServiceImpl.selectCXList(condition);
			int cxCount = iOrderCxServiceImpl.getCXOrderCount(condition);
			pageInfo.setRows(cxList);
			pageInfo.setTotal(cxCount);
		} else if (orderType.equals("ROOTRS")) {
			// 寿险
			System.out.println("【condition】" + JSON.toJSONString(condition));
			List<Map<String, Object>> sxList = iOrderSxServiceImpl.selectSXList(condition);
			int sxCount = iOrderSxServiceImpl.getSXOrderCount(condition);
			pageInfo.setRows(sxList);
			pageInfo.setTotal(sxCount);
		} else if (orderType.equals("Other")) {
			// 其他
			System.out.println("【condition】" + JSON.toJSONString(condition));
			List<Map<String, Object>> sxList = iOrderInfoServiceImpl.selectOtherList(condition);
			int sxCount = iOrderInfoServiceImpl.getOtherOrderCount(condition);
			pageInfo.setRows(sxList);
			pageInfo.setTotal(sxCount);

		}
		return pageInfo;
	}

	@GetMapping("/orderInfo")
	public String orderInfo(Model model, String orderType) {
		model.addAttribute("orderType", orderType);
		return "admin/orderManage/order" + orderType + "List";
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

	@PostMapping("/orderTypeListOfOther")
	@ResponseBody
	public Object getOrderTypeListOfOther() {
		Wrapper<TMerchantProductCategory> wrapper = new EntityWrapper<TMerchantProductCategory>();
		wrapper.where("CATEGORY_ID in ( 'ROOTQCM','ROOTDK','ROOTCM','ROOTQCS') or CATEGORY_ID like 'ROOTCD%'");
		List<TMerchantProductCategory> selectList = iTMerchantProductCategoryServiceImpl.selectList(wrapper);
		return selectList;
	}

	@PostMapping("/orderTypeListOfCX")
	@ResponseBody
	public Object orderTypeListOfCX() {
		Wrapper<TMerchantProductCategory> wrapper = new EntityWrapper<TMerchantProductCategory>();
		wrapper.like("CATEGORY_ID", "ROOTCC", SqlLike.RIGHT);
		List<TMerchantProductCategory> selectList = iTMerchantProductCategoryServiceImpl.selectList(wrapper);
		return selectList;
	}

	@PostMapping("/areaQuery")
	@ResponseBody
	public Object areaQuery() {
		Wrapper<TChinaArea> wrapper = new EntityWrapper<TChinaArea>();
		wrapper.eq("PID", "0");
		List<TChinaArea> selectList = iTChinaAreaServiceImpl.selectList(wrapper);
		return selectList;
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
