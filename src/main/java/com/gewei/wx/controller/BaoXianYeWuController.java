package com.gewei.wx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.gewei.commons.base.BaseController;
import com.gewei.model.vo.CreateBXOrderVo;
import com.gewei.wx.service.IDingDanZhuiZongService;

@Controller
@RequestMapping("/baoxianyewu")
public class BaoXianYeWuController extends BaseController {
	@Autowired
	private IDingDanZhuiZongService iDingDanZhuiZongServiceImpl;

	@RequestMapping(value = "/createBXOrder", method = { RequestMethod.GET, RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public Object createCommonBXOrder(CreateBXOrderVo createBXOrderVo) {
		try {
			// 校验用户
			// 提交订单
			System.out.println("createBXOrderVo:[" + JSON.toJSONString(createBXOrderVo) + "]");
			iDingDanZhuiZongServiceImpl.createCommonBXOrder(createBXOrderVo);
			return renderSuccess("创建成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("服务器内部错误");
		}
	}
}
