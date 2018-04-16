package com.gewei.wx.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.Result;
import com.gewei.commons.shiro.PasswordHash;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.UUIDUtil;
import com.gewei.fuwushang.service.IMessageService;
import com.gewei.fuwushang.service.IOrderCxService;
import com.gewei.fuwushang.service.IOrderInfoService;
import com.gewei.fuwushang.service.IOrderSxService;
import com.gewei.model.Message;
import com.gewei.model.OrderCx;
import com.gewei.model.OrderInfo;
import com.gewei.model.OrderSx;
import com.gewei.model.TAccount;
import com.gewei.model.TMemberBasicinfo;
import com.gewei.model.TMerchantManage;
import com.gewei.model.User;
import com.gewei.model.vo.OrderInfoVo;
import com.gewei.model.vo.UserVo;
import com.gewei.wx.service.IAccountService;
import com.gewei.wx.service.IDingDanZhuiZongService;
import com.gewei.wx.service.IShanJiaFuWuService;
import com.gewei.wx.service.ITMemberBasicinfoService;
import com.gewei.zhongshu.service.ITMerchantManageService;
import com.gewei.zhongshu.service.IUserService;
import com.gewei.zhongshu.service.IZMessageService;

/**
 * @Description: TODO 微信，商家服务
 * @author: Tiger
 * @date: 2018年3月26日 上午9:41:26
 */
@Controller
@RequestMapping("/shanjiafuwu")
public class ShanJiaFuWuController extends BaseController {
	protected static final Logger logger = Logger.getLogger(ShanJiaFuWuController.class);
	@Autowired
	private IAccountService iAccountServiceImpl;
	@Autowired
	private IShanJiaFuWuService iShanJiaFuWuServiceImpl;
	@Autowired
	private ITMerchantManageService tMerchantManageServiceImpl;
	@Autowired
	private IDingDanZhuiZongService iDingDanZhuiZongServiceImpl;
	@Autowired
	private IZMessageService iIZMessageServiceImpl;
	@Autowired
	private IOrderInfoService iIOrderInfoServiceImpl;
	@Autowired
	private IUserService userService;
	@Autowired
	private PasswordHash passwordHash;
	@Autowired
	private IOrderSxService iOrderSxServiceImpl;
	@Autowired
	private IOrderCxService iOrderCxServiceImpl;
	@Autowired
	private IMessageService iMessageServiceImpl;
	@Autowired
	private ITMemberBasicinfoService iTMemberBasicinfoServiceImpl;

	// 服务商入驻
	@RequestMapping("/add")
	@ResponseBody
	@Transactional
	public Object add(@Valid TMerchantManage tMerchantManageRe, String userId, HttpServletRequest request) {
		try {
			System.out.println("tMerchantManageRe：" + JSON.toJSONString(tMerchantManageRe));
			// 添加服务商基本信息======================================================================
			TMerchantManage tMerchantManage = new TMerchantManage();
			long currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date());
			String uuid = UUIDUtil.getUUID32().substring(0, 10);
			String merchantId = "FWS" + currTime + uuid;
			tMerchantManage.setMerchantId(merchantId);
			tMerchantManage.setMerchantName(tMerchantManageRe.getMerchantName());
			tMerchantManage.setMerchantPhotoUrl("/static/upload/default.jpg");
			tMerchantManage.setMerchantType(tMerchantManageRe.getMerchantType());
			tMerchantManage.setGrantArea(tMerchantManageRe.getGrantArea());
			tMerchantManage.setProvince(tMerchantManageRe.getProvince());
			tMerchantManage.setCity(tMerchantManageRe.getCity());
			tMerchantManage.setCountry(tMerchantManageRe.getCountry());
			tMerchantManage.setMerchantAddr(tMerchantManageRe.getMerchantAddr());
			tMerchantManage.setTelphone(tMerchantManageRe.getTelphone());
			tMerchantManage.setCreateTime(currTime);
			tMerchantManage.setUpdateTime(currTime);
			tMerchantManage.setOperatorId(userId);
			tMerchantManage.setMerchantDescript(tMerchantManageRe.getMerchantDescript());
			// 记录操作人
			tMerchantManage.setOperatorId(userId);
			tMerchantManage.setStatus("01");
			System.out.println("tMerchantManage：" + JSON.toJSONString(tMerchantManage));
			tMerchantManageServiceImpl.insert(tMerchantManage);
			// 插入账务记录
			TAccount tAccount = new TAccount();
			tAccount.setBalance(0L);
			tAccount.setPersonId(tMerchantManage.getMerchantId());
			tAccount.setBankHostName("");
			tAccount.setBankCardId("");
			tAccount.setTotalIncome(0L);
			tAccount.setSettleApplying(0L);
			tAccount.setUpdateTime(currTime + "");
			iAccountServiceImpl.insert(tAccount);
			// 插入消息通知
			String ct = DateUtil.get_String$yyyyMMddHHmmss(new Date());
			Message message = new Message();
			message.setContent("有新的服务商申请，快去处理吧~");
			message.setCreateTime(ct);
			message.setUpdateTime(ct);
			message.setOperatorId(userId);
			message.setType("01");
			message.setStatus("01");
			message.setUserId(merchantId);
			iIZMessageServiceImpl.insert(message);
			// 初始化一个微信端的admin账号======================================================================
			TMemberBasicinfo memberBasicinfo = new TMemberBasicinfo();
			memberBasicinfo.setUserId("WX" + currTime + UUIDUtil.getUUID32().substring(0, 16));
			memberBasicinfo.setCreateTime(currTime + "");
			memberBasicinfo.setStatus("01");
			memberBasicinfo.setType("02");// 服务商
			memberBasicinfo.setUpdateTime(currTime + "");
			memberBasicinfo.setUserName(tMerchantManageRe.getTelphone());
			memberBasicinfo.setTelephone(tMerchantManageRe.getTelphone());
			memberBasicinfo.setMobilePhone(tMerchantManageRe.getTelphone());
			memberBasicinfo.setUpdateTime(currTime + "");
			memberBasicinfo.setCreateTime(currTime + "");
			iTMemberBasicinfoServiceImpl.insert(memberBasicinfo);
			// 添加服务商admin账户,作为服务商系统的操作员======================================================================
			UserVo userVo = new UserVo();
			userVo.setId(UUIDUtil.getUUID32());
			userVo.setLoginName(tMerchantManage.getMerchantName());
			List<User> list = userService.selectByLoginName(userVo);
			if (list != null && !list.isEmpty()) {
				return renderError("该服务已已存在!");
			}
			String salt = com.gewei.commons.utils.StringUtils.getUUId();
			String pwd = passwordHash.toHex("admin", salt);
			userVo.setSalt(salt);
			userVo.setPassword(pwd);
			userVo.setName(tMerchantManage.getMerchantName());
			userVo.setUserType("02");
			userVo.setPhone(tMerchantManageRe.getTelphone());
			userVo.setCreateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
			userVo.setOrganizationId(tMerchantManage.getMerchantId());
			User user = new User();
			BeanUtils.copyProperties(userVo, user);
			userService.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("添加失败！");
		}
		return renderSuccess("添加成功！");
	}

	// 商家助手
	@RequestMapping("/merchantAssistant")
	@ResponseBody
	public Object merchantAssistant(String merchantId) {
		Result result = new Result();
		try {
			// 服务商编号校验
			if (StringUtils.isEmpty(merchantId))
				return renderError("服务商编号不能为空");
			TMerchantManage tMerchantManage = tMerchantManageServiceImpl.selectById(merchantId);
			if (tMerchantManage == null)
				return renderError("服务商不存在");
			Map<String, Object> merchantOrderSummaryList = iShanJiaFuWuServiceImpl.getMerchantOrderSummaryById(merchantId);
			result.setSuccess(true);
			result.setObj(merchantOrderSummaryList);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("内部错误");
		}
		return result;
	}

	// 服务商订单列表
	@RequestMapping("/merchantOrderList")
	@ResponseBody
	public Object merchantOrderList(OrderInfoVo orderInfoVo, Long page, Long rows) {
		Result result = new Result();
		page = page == null ? 1 : page;
		rows = rows == null ? 10 : rows;
		long start = (page - 1) * rows;
		try {
			List<Map<String, String>> orderList = iShanJiaFuWuServiceImpl.getMerchantOrderList(orderInfoVo, start, rows);
			long totalCount = iShanJiaFuWuServiceImpl.getMerchantOrderCount(orderInfoVo);
			result.setSuccess(true);
			result.setObj(orderList);
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

	// 服务商订单详情
	@RequestMapping("/getMerchantOrderDetailInfo")
	@ResponseBody
	public Object getMerchantOrderDetailInfo(String orderId, String orderType) {
		Result result = new Result();
		try {
			Map<String, Object> orderInfoMap = new HashMap<String, Object>();
			System.out.println("参数打印：orderType=" + orderType);
			if (orderType.contains("ROOTCD")) {
				// 车务代办
				orderInfoMap = iDingDanZhuiZongServiceImpl.getCDOrderDetailInfoByOrderId(orderId);
			} else if ("ROOTQCM".equals(orderType) || "ROOTQCS".equals(orderType)) {
				// 二手车业务
				orderInfoMap = iDingDanZhuiZongServiceImpl.getESCOrderDetailInfoByOrderId(orderId);
			} else if ("ROOTCM".equals(orderType)) {
				// 汽车美容
				orderInfoMap = iDingDanZhuiZongServiceImpl.getCMOrderDetailInfoByOrderId(orderId);
			} else if ("ROOTDK".equals(orderType)) {
				// 贷款业务
				orderInfoMap = iDingDanZhuiZongServiceImpl.getDKOrderDetailInfoByOrderId(orderId);
			} else if (orderType.contains("ROOTCC")) {
				// 财产保险
				orderInfoMap = iShanJiaFuWuServiceImpl.getCCOrderDetailInfoByOrderId(orderId);
			} else if ("ROOTRS".equals(orderType)) {
				// 人寿保险
				orderInfoMap = iShanJiaFuWuServiceImpl.getRSOrderDetailInfoByOrderId(orderId);
			} else {
				orderInfoMap.put("tip", "参数错误");
			}
			result.setSuccess(true);
			result.setObj(orderInfoMap);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("服务器内部错误");
			result.setObj(null);
			return result;
		}
	}

	// 服务商订单编辑
	@RequestMapping("/editMerchantOrderInfo")
	@ResponseBody
	@Transactional
	public Object editMerchantOrderInfo(String orderNo, String orderId, String merchantId, String orderType, String status) {
		try {
			String currTime = DateUtil.get_String$yyyyMMddHHmmss(new Date());
			// 用户校验
			// 订单校验
			String messageTo = null;// 消息提醒人
			String productId = null;// 产品编号
			if (orderType.contains("ROOTCC")) {
				// 财产保险
				EntityWrapper<OrderCx> entityWrapper = new EntityWrapper<OrderCx>();
				entityWrapper.eq("ORDER_ID", orderId);
				OrderCx orderCx = iOrderCxServiceImpl.selectOne(entityWrapper);
				if (orderCx == null)
					return renderError("订单不存在");
				messageTo = orderCx.getSalesMan();
				orderCx.setOrderNo(orderNo);
				orderCx.setOrderFlag(status);
				orderCx.setOperator(merchantId);
				orderCx.setUpdateTime(currTime);
				orderCx.setUpdateUser(merchantId);
				iOrderCxServiceImpl.update(orderCx, entityWrapper);
			} else if ("ROOTRS".equals(orderType)) {
				// 人寿保险
				EntityWrapper<OrderSx> entityWrapper = new EntityWrapper<OrderSx>();
				entityWrapper.eq("ORDER_ID", orderId);
				OrderSx orderSx = iOrderSxServiceImpl.selectOne(entityWrapper);
				if (orderSx == null)
					return renderError("订单不存在");
				messageTo = orderSx.getSalesMan();
				orderSx.setOrderNo(orderNo);
				orderSx.setOrderFlag(status);
				orderSx.setOperator(merchantId);
				orderSx.setUpdateUser(merchantId);
				orderSx.setUpdateTime(currTime);
				iOrderSxServiceImpl.update(orderSx, entityWrapper);
			} else {
				OrderInfo orderInfo = iIOrderInfoServiceImpl.selectById(orderId);
				if (orderInfo == null)
					return renderError("订单不存在");
				messageTo = orderInfo.getUserId();
				productId = orderInfo.getProductId();
				orderInfo.setOrderFlag(status);
				orderInfo.setRemark1(merchantId);
				orderInfo.setUpdateTime(currTime);
				iShanJiaFuWuServiceImpl.updateById(orderInfo);
			}
			if ("02".equals(status)) {
				// 若果是订单成交，则插入消息记录
				Message message = new Message();
				message.setUserId(messageTo);
				message.setStatus("01");
				message.setContent("订单交易成功");
				message.setType("23");// 所有订单成交
				message.setOperatorId(merchantId);
				message.setCreateTime(currTime.toString());
				message.setUpdateTime(currTime.toString());
				message.setOrderId(orderId);
				message.setProductId(productId);
				message.setOrderType(orderType);
				iMessageServiceImpl.insert(message);
			}
			return renderSuccess("交易成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("服务器内部错误");
		}
	}

	// 服务商结算，概述查询
	@RequestMapping("/queryMerchantSettleInfo")
	@ResponseBody
	public Object queryMerchantSettleInfo(String merchantId, String createTime) {
		Result result = new Result();
		Map<String, Object> resultS = new HashMap<String, Object>();
		try {
			// 累计收入+累计未结算金额
			Map<String, Object> summaryMap = iShanJiaFuWuServiceImpl.getMerchantSettleSummary(merchantId, createTime);
			resultS.putAll(summaryMap);
			// 贷款业务/理财业务/二手车业务/车务代办/汽修美容
			Map<String, Object> detailInfoMap = iShanJiaFuWuServiceImpl.getMerchantSettleOrderByOrderType(merchantId, createTime);
			resultS.putAll(detailInfoMap);
			result.setSuccess(true);
			result.setObj(resultS);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("服务器内部错误");
			result.setObj(null);
			return result;
		}
	}
}
