package com.gewei.zhongshu.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.PageInfo;
import com.gewei.model.vo.MerchantVo;
import com.gewei.zhongshu.service.ITMerchantManageService;

/**
 * @Description: 审核管理控制层
 * @author: Tiger
 * @date: 2018年2月5日 下午8:44:05
 */
@Controller
@RequestMapping("/mgr/configureManage")
public class ConfigureManageController extends BaseController {
	@Autowired
	private ITMerchantManageService tMerchantManageService;

	@GetMapping("/connetQueryFee")
	public String connetQueryFee() {
		return "admin/configure/tConnetQueryFee";
	}

	@GetMapping("/recommendAward")
	public String recommendAward() {
		return "admin/configure/tRecommendAward";
	}

	@PostMapping("/connetQueryFee")
	@ResponseBody
	public PageInfo connetQueryFeeDataGrid(MerchantVo merchantVo, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		if (merchantVo.getCreateStartTime() != null) {
			condition.put("createStartTime", merchantVo.getCreateStartTime());
		}
		if (merchantVo.getCreateEndTime() != null) {
			condition.put("createEndTime", merchantVo.getCreateEndTime());
		}
		if (merchantVo.getProvince() != null) {
			condition.put("province", merchantVo.getProvince());
		}
		if (merchantVo.getCity() != null) {
			condition.put("city", merchantVo.getCity());
		}
		if (merchantVo.getCountry() != null) {
			condition.put("country", merchantVo.getCountry());
		}
		if (merchantVo.getMerchantType() != null) {
			condition.put("merchantType", merchantVo.getMerchantType());
		}
		if (merchantVo.getKeywordInfo() != null) {
			condition.put("keywordInfo", merchantVo.getKeywordInfo());
		}
		pageInfo.setCondition(condition);
		tMerchantManageService.selectDataGrid(pageInfo);
		return pageInfo;
	}

	@PostMapping("/recommendAward")
	@ResponseBody
	public PageInfo recommendAward(MerchantVo merchantVo, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		if (merchantVo.getCreateStartTime() != null) {
			condition.put("createStartTime", merchantVo.getCreateStartTime());
		}
		if (merchantVo.getCreateEndTime() != null) {
			condition.put("createEndTime", merchantVo.getCreateEndTime());
		}
		if (merchantVo.getProvince() != null) {
			condition.put("province", merchantVo.getProvince());
		}
		if (merchantVo.getCity() != null) {
			condition.put("city", merchantVo.getCity());
		}
		if (merchantVo.getCountry() != null) {
			condition.put("country", merchantVo.getCountry());
		}
		if (merchantVo.getMerchantType() != null) {
			condition.put("merchantType", merchantVo.getMerchantType());
		}
		if (merchantVo.getKeywordInfo() != null) {
			condition.put("keywordInfo", merchantVo.getKeywordInfo());
		}
		pageInfo.setCondition(condition);
		tMerchantManageService.selectDataGrid(pageInfo);
		return pageInfo;
	}
}
