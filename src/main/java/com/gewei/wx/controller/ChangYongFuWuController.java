package com.gewei.wx.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.Result;
import com.gewei.commons.utils.BeanUtils;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.UUIDUtil;
import com.gewei.fuwushang.service.IOrderCxService;
import com.gewei.fuwushang.service.IOrderSxService;
import com.gewei.model.OrderCx;
import com.gewei.model.OrderSx;
import com.gewei.model.TMemberBasicinfo;
import com.gewei.wx.service.ITMemberBasicinfoService;

@Controller
@RequestMapping("/changyongfuwu")
public class ChangYongFuWuController extends BaseController {
	@Autowired
	private ITMemberBasicinfoService iTMemberBasicinfoServiceImpl;
	@Autowired
	private IOrderCxService iOrderCxServiceImpl;
	@Autowired
	private IOrderSxService iOrderSxServiceImpl;

	//查询个人详细信息
	@RequestMapping("/getUserInfoById")
	@ResponseBody
	public Object getUserInfoById(String userId) {
		Result result = new Result();
		try {
			Wrapper<TMemberBasicinfo> wrapper = new EntityWrapper<TMemberBasicinfo>();
			wrapper.eq("USER_ID", userId);
			TMemberBasicinfo memberBasicinfo = iTMemberBasicinfoServiceImpl.selectOne(wrapper);
			if (memberBasicinfo == null)
				return renderError("用户不存在");
			result.setSuccess(true);
			result.setObj(memberBasicinfo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("服务器内部错误");
		}
	}

	//编辑个人详细信息
	@RequestMapping("/editUserInfoById")
	@ResponseBody
	public Object editUserInfoById(TMemberBasicinfo tMemberBasicinfo) {
		try {
			String userId = tMemberBasicinfo.getUserId();
			Wrapper<TMemberBasicinfo> wrapper = new EntityWrapper<TMemberBasicinfo>();
			wrapper.eq("USER_ID", userId);
			TMemberBasicinfo memberBasicinfo = iTMemberBasicinfoServiceImpl.selectOne(wrapper);
			if (memberBasicinfo == null)
				return renderError("用户不存在");
			BeanUtils.copyNotNullProperties(tMemberBasicinfo, memberBasicinfo);
			boolean update = iTMemberBasicinfoServiceImpl.update(memberBasicinfo, wrapper);
			if (update)
				return renderSuccess("修改成功");
			else
				return renderError("修改失败");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("服务器内部错误");
		}
	}

	//新增微信用户
	@RequestMapping("/addMemberInfo")
	@ResponseBody
	public Object addMemberInfo(String telphone, String province, String city, String country) {
		try {
			String currTime = DateUtil.get_String$yyyyMMddHHmmss(new Date());
			EntityWrapper<TMemberBasicinfo> entityWrapper = new EntityWrapper<TMemberBasicinfo>();
			entityWrapper.eq("TELEPHONE", telphone);
			TMemberBasicinfo memberBasicinfo = iTMemberBasicinfoServiceImpl.selectOne(entityWrapper);
			if (memberBasicinfo == null) {
				// 新增用户
				memberBasicinfo = new TMemberBasicinfo();
				memberBasicinfo.setUserId("WX" + currTime + UUIDUtil.getUUID32().substring(0, 16));
				memberBasicinfo.setTelephone(telphone);
				memberBasicinfo.setProvince(province);
				memberBasicinfo.setCity(city);
				memberBasicinfo.setCountry(country);
				memberBasicinfo.setUpdateTime(currTime);
				memberBasicinfo.setType("01");
				iTMemberBasicinfoServiceImpl.insert(memberBasicinfo);
			} else {
				memberBasicinfo.setProvince(province);
				memberBasicinfo.setCity(city);
				memberBasicinfo.setCountry(country);
				memberBasicinfo.setUpdateTime(currTime);
				memberBasicinfo.setType("01");
				iTMemberBasicinfoServiceImpl.update(memberBasicinfo, entityWrapper);
			}
			return memberBasicinfo.getUserId();
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("服务器内部错误");
		}
	}

	//待领礼品列表
	@RequestMapping("/getGiftList")
	@ResponseBody
	public Object getGiftList(String userId, Long page, Long rows) {
		Result result = new Result();
		page = page == null ? 1 : page;
		rows = rows == null ? 10 : rows;
		long start = (page - 1) * rows;
		try {
			long totalCount = 0;
			// 用户校验
			Wrapper<TMemberBasicinfo> wrapper = new EntityWrapper<TMemberBasicinfo>();
			wrapper.eq("USER_ID", userId);
			TMemberBasicinfo memberBasicinfo = iTMemberBasicinfoServiceImpl.selectOne(wrapper);
			if (memberBasicinfo == null)
				return renderError("用户不存在");
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			// 财险礼品==============================================================================
			EntityWrapper<OrderCx> wrapper2 = new EntityWrapper<OrderCx>();
			wrapper2.eq("GIFT_FLAG", "01");
			wrapper2.eq("GIFT_SF", "02");
			wrapper2.eq("SALES_MAN", userId);
			wrapper2.orderBy("CREATE_TIME", true);
			List<OrderCx> selectListc = iOrderCxServiceImpl.selectList(wrapper2);
			totalCount += selectListc.size();
			for (OrderCx orderCx : selectListc) {
				HashMap<String, Object> hashMap = new HashMap<String, Object>(3);
				hashMap.put("time", orderCx.getTbStartTime());
				hashMap.put("bf", orderCx.getZbf());
				hashMap.put("lp", orderCx.getGiftComment());
				list.add(hashMap);
			}
			// 寿险礼品==============================================================================
			EntityWrapper<OrderSx> wrapper3 = new EntityWrapper<OrderSx>();
			wrapper3.eq("GIFT_FLAG", "01");
			wrapper3.eq("SFYLP", "01");
			wrapper3.eq("SALES_MAN", userId);
			wrapper3.orderBy("CREATE_TIME", true);
			List<OrderSx> selectLists = iOrderSxServiceImpl.selectList(wrapper3);
			totalCount += selectLists.size();
			for (OrderSx orderSx : selectLists) {
				HashMap<String, Object> hashMap = new HashMap<String, Object>(3);
				hashMap.put("time", orderSx.getTbStartTime());
				hashMap.put("bf", orderSx.getBfhj());
				hashMap.put("lp", orderSx.getLpnr());
				list.add(hashMap);
			}
			// 返回参数封装==============================================================================
			int forStart = 0;
			int forEnd = 0;
			if (list.size() <= start) {
				forStart = list.size();
				forEnd = list.size();
			} else if (start < list.size() && list.size() <= start + rows) {
				forStart = (int) start;
				forEnd = list.size();
			} else {
				forStart = (int) start;
				forEnd = rows.intValue();
			}
			ArrayList<HashMap<String, Object>> list2 = new ArrayList<HashMap<String, Object>>();
			for (int i = forStart; i < forEnd; i++) {
				list2.add(list.get(i));
			}
			result.setTotalCount(totalCount);
			result.setObj(list2);
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("服务器内部错误");
		}
	}
}
