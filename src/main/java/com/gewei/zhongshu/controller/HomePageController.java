package com.gewei.zhongshu.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.gewei.commons.base.BaseController;
import com.gewei.zhongshu.service.ITMerchantManageService;
import com.gewei.zhongshu.service.ITMerchantProductManageService;

/**
 * @Description: 中枢系统首页
 * @author: Tiger
 * @date: 2018年1月25日 上午9:29:35
 */
@Controller
@RequestMapping("/mgr/home")
public class HomePageController extends BaseController {
	@Autowired
	private ITMerchantManageService tMerchantManageServiceImpl;
	@Autowired
	private ITMerchantProductManageService iTMerchantProductManageServiceImpl;

	/**
	 * @Author: Tiger
	 * @Description: 首页展示
	 * @param @return
	 * @return: String
	 */
	@GetMapping("/homePage")
	public String homePage() {
		return "admin/home/homePage";
	}

	/**
	 * @Author: Tiger
	 * @Description: 首页数据
	 * @param @return
	 * @return: String
	 */
	@GetMapping("/homePage/ajaxData")
	@ResponseBody
	public String ajaxData() {
		Map<String, Long> result = new HashMap<String, Long>();
		long examineMerchant = tMerchantManageServiceImpl.getCountExamineMerchant();
		result.put("examineMerchant", examineMerchant);
		long examineCash = iTMerchantProductManageServiceImpl.getExamineCash();
		result.put("examineCash", examineCash);
		long examineSettle = iTMerchantProductManageServiceImpl.getExamineSettle();
		result.put("examineSettle", examineSettle);
		return JSON.toJSONString(result);
	}
}