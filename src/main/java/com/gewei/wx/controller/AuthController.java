package com.gewei.wx.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.Result;
import com.gewei.commons.utils.AppUtil;
import com.gewei.commons.utils.BeanUtils;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.UUIDUtil;
import com.gewei.mapper.CommonMapper;
import com.gewei.model.SmsCode;
import com.gewei.model.TMemberBasicinfo;
import com.gewei.model.TMerchantManage;
import com.gewei.wx.service.ITMemberBasicinfoService;
import com.gewei.wx.util.WchatUtil;
import com.gewei.zhongshu.service.ITMerchantManageService;

/**
 * 
 * <pre>
 * <b>.</b>
 * <b>Description:</b> 
 *    用户授权
		1. 用户关注微信公众账号。
		2. 微信公众账号提供用户请求授权页面URL。
		3. 用户点击授权页面URL，将向服务器发起请求
		4. 服务器询问用户是否同意授权给微信公众账号(scope为snsapi_base时无此步骤)
		5. 用户同意(scope为snsapi_base时无此步骤)
		6. 服务器将CODE通过回调传给微信公众账号
		7. 微信公众账号获得CODE
		8. 微信公众账号通过CODE向服务器请求Access Token
		9. 服务器返回Access Token和OpenID给微信公众账号
		10. 微信公众账号通过Access Token向服务器请求用户信息(scope为snsapi_base时无此步骤)
		11. 服务器将用户信息回送给微信公众账号(scope为snsapi_base时无此步骤)
 * <b>Author:</b> tlibo 973297639@qq.com
 * <b>Date:</b> 2017年7月28日下午1:14:34
 * <b>Copyright:</b> Copyright ©1998-2016 iyooc.cn Technology Co., Ltd. All rights reserved.
 * <b>Changelog:</b>
 *   ----------------------------------------------------------------------------
 *   Ver   Date                    Author                           Detail
 *   ----------------------------------------------------------------------------
 *   1.0   2017年7月28日下午1:14:34   tlibo 973297639@qq.com            new file.
 * </pre>
 */
@Controller
@RequestMapping("/security")
public class AuthController extends BaseController {
	protected static final Logger logger = Logger.getLogger(AuthController.class);

	@RequestMapping("/auth")
	@ResponseBody
	protected void doGet(HttpServletRequest request, HttpServletResponse response, String userId) throws ServletException, IOException {
		System.out.println("获取来源URL:" + request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath());
		System.out.println("获取请求的URL，不包括参数:" + request.getRequestURL());
		System.out.println("获取参数信息:" + request.getQueryString());
		StringBuffer returnURL = new StringBuffer(request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/static/gw-web/web/base/login.html");// 设置回调地址
		System.out.println("returnURL...[" + returnURL + "]");
		// 需要用户点同意 scope="snsapi_userinfo";
		// 不需要用户点同意scope="snsapi_base";
		String scope = "snsapi_userinfo";
		// 拼接OAuth2.0授权
		StringBuffer sbStr = new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize?");
		sbStr.append("appid=" + AppUtil.getPropertie("wxConfig.appid")).append("&redirect_uri=" + returnURL).append("&response_type=code&scope=" + scope + "&state=1#wechat_redirect");
		System.out.println("OAuth2.0 URL[" + sbStr + "]");
		// 3,用户点击授权页面URL，将向服务器发起请求
		response.sendRedirect(sbStr.toString());
		response.setContentType("text/html");

	}

	@Autowired
	private CommonMapper commonMapper;
	@Autowired
	private ITMemberBasicinfoService iTMemberBasicinfoServiceImpl;
	@Autowired
	private ITMerchantManageService iTMerchantManageServiceImpl;

	@RequestMapping("/telLogin")
	@ResponseBody
	protected Object telLogin(String telphone, String smsCode, String code) {
		Result result = new Result();
		try {
			String openId = WchatUtil.getOpenId(AppUtil.getPropertie("wxConfig.appid"), code, AppUtil.getPropertie("wxConfig.appid"));
			System.out.println("openId=" + openId);
			// 短信验证码校验
			SmsCode code2 = commonMapper.getSMSCodeByTelephone(telphone);
			String flag = code2.getFlag();
			if ("02".equals(flag)) {
				return renderError("验证码已经被使用");
			}
			long deadTime = Long.parseLong(code2.getDeadTime());
			long currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date());
			if (deadTime < currTime) {
				return renderError("验证码已过期");
			}
			String smsCode2 = code2.getSmsCode();
			if (!smsCode.equals(smsCode2)) {
				return renderError("验证码错误");
			}
			// 验证码过期
			code2.setFlag("02");
			commonMapper.expiredSmscode(code2);
			// 查询用户是否存在
			EntityWrapper<TMemberBasicinfo> entityWrapper = new EntityWrapper<TMemberBasicinfo>();
			entityWrapper.eq("TELEPHONE", telphone);
			TMemberBasicinfo memberBasicinfo = iTMemberBasicinfoServiceImpl.selectOne(entityWrapper);
			if (memberBasicinfo == null) {
				result.setSuccess(true);
				result.setObj(null);
			} else {
				String merchantId = null;
				if ("02".equals(memberBasicinfo.getType())) {
					EntityWrapper<TMerchantManage> entityWrapper2 = new EntityWrapper<TMerchantManage>();
					entityWrapper2.eq("TELEPHONE", telphone);
					TMerchantManage tMerchantManage = iTMerchantManageServiceImpl.selectOne(entityWrapper2);
					merchantId = tMerchantManage == null ? null : tMerchantManage.getMerchantId();
				}
				HashMap<String, String> hashMap = new HashMap<String, String>(2);
				hashMap.put("userId", memberBasicinfo.getUserId());
				hashMap.put("merchantId", merchantId);
				result.setObj(hashMap);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("登录失败");
		}
	}

	// 第一次登录补充信息
	@RequestMapping("/addMemberInfo")
	@ResponseBody
	public Object addMemberInfo(TMemberBasicinfo tMemberBasicinfo, String telphone, String smsCode) {
		Result result = new Result();
		try {
			// 短信验证码校验
			SmsCode code = commonMapper.getSMSCodeByTelephone(telphone);
			if (smsCode == null) {
				return renderError("验证码不能为空");
			}
			if (code == null) {
				return renderError("验证码不存在");
			}
			if (!smsCode.equals(code.getSmsCode())) {
				return renderError("验证码有误");
			}
			long deadTime = Long.parseLong(code.getDeadTime());
			long currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date());
			if (deadTime < currTime) {
				return renderError("验证码已过期");
			}
			// 验证码过期
			code.setFlag("03");
			commonMapper.expiredSmscode(code);
			TMemberBasicinfo memberBasicinfo = new TMemberBasicinfo();
			BeanUtils.copyNotNullProperties(tMemberBasicinfo, memberBasicinfo);
			memberBasicinfo.setUserId("WX" + currTime + UUIDUtil.getUUID32().substring(0, 16));
			memberBasicinfo.setCreateTime(currTime + "");
			memberBasicinfo.setStatus("01");
			memberBasicinfo.setType("01");
			memberBasicinfo.setUpdateTime(currTime + "");
			memberBasicinfo.setUserName(telphone);
			memberBasicinfo.setTelephone(telphone);
			memberBasicinfo.setMobilePhone(telphone);
			iTMemberBasicinfoServiceImpl.insert(memberBasicinfo);
			result.setSuccess(true);
			HashMap<String, String> hashMap = new HashMap<String, String>(2);
			hashMap.put("userId", memberBasicinfo.getUserId());
			hashMap.put("merchantId", null);
			result.setObj(hashMap);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("添加失败");
		}
	}
}
