package com.gewei.wx.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.UUIDUtil;
import com.gewei.fuwushang.service.IOrderCxService;
import com.gewei.fuwushang.service.IOrderSxService;
import com.gewei.fuwushang.service.IPolicyholderService;
import com.gewei.fuwushang.service.ITCustomerBasicinfoService;
import com.gewei.fuwushang.service.IUserService;
import com.gewei.mapper.BaoXianYeWuMapper;
import com.gewei.mapper.DingDanZhuiZongMapper;
import com.gewei.model.OrderCx;
import com.gewei.model.OrderInfo;
import com.gewei.model.OrderSx;
import com.gewei.model.Policyholder;
import com.gewei.model.TCustomerBasicinfo;
import com.gewei.model.vo.CreateBXOrderVo;
import com.gewei.model.vo.EditOrderInfo;
import com.gewei.model.vo.UserVo;
import com.gewei.wx.service.IDingDanZhuiZongService;

@Service
public class DingDanZhuiZongServiceImpl extends ServiceImpl<BaoXianYeWuMapper, OrderInfo> implements IDingDanZhuiZongService {
	@Autowired
	private DingDanZhuiZongMapper dingDanZhuiZongMapper;
	@Autowired
	private ITCustomerBasicinfoService iTCustomerBasicinfoServiceImpl;
	@Autowired
	private IOrderCxService iOrderCxServiceImpl;
	@Autowired
	private IOrderSxService iOrderSxServiceImpl;
	@Autowired
	private IUserService iUserServiceImpl;
	@Autowired
	private IPolicyholderService policyholderService;

	@Override
	@Transactional
	public void createCommonBXOrder(CreateBXOrderVo createBXOrderVo) {
		try {
			String orderType = createBXOrderVo.getOrderType();
			String userId = createBXOrderVo.getUserId();
			if ("ROOTRS".equals(orderType)) {
				// 创建人寿保险
				OrderSx orderSx = new OrderSx();
				String orderId = "RSBX" + DateUtil.get_String$yyyyMMddHHmmss(new Date()) + UUIDUtil.getUUID32().substring(0, 10);
				orderSx.setOrderId(orderId);
				orderSx.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
				orderSx.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
				Subject currentUser = SecurityUtils.getSubject();
				PrincipalCollection collection = currentUser.getPrincipals();
				if (null != collection) {
					String loginName = collection.getPrimaryPrincipal().toString();
					UserVo userVo = iUserServiceImpl.selectUserVoByLoginName(loginName);
					orderSx.setMerchantId(userVo.getOrganizationId().toString());
					orderSx.setRecorder(userVo.getName());
					orderSx.setRecorderid(userVo.getId().toString());
					orderSx.setUpdateUser(userVo.getId().toString());
				}
				boolean b = false;
				// 投保人========================================================================================================
				Policyholder policyholder = new Policyholder();
				policyholder.setUserName(createBXOrderVo.getTbrName());
				policyholder.setSex(createBXOrderVo.getTbrSex());
				policyholder.setAge(createBXOrderVo.getTbrAge());
				policyholder.setPid(com.gewei.commons.utils.StringUtils.getUUId());
				policyholder.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
				policyholder.setStatus("0");
				orderSx.setPolicyholderId(policyholder.getPid());
				b = policyholderService.insert(policyholder);
				orderSx.setPolicyholderId(policyholder.getPid());
				orderSx.setOrderFlag("01");
				orderSx.setOrderType(createBXOrderVo.getOrderType());
				orderSx.setSalesMan(createBXOrderVo.getUserId());
				iOrderSxServiceImpl.insert(orderSx);
			} else if (orderType.contains("ROOTCC")) {
				// 创建财产保险
				OrderCx orderCx = new OrderCx();
				long currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date());
				String uuid = UUIDUtil.getUUID32().substring(0, 10);
				orderCx.setOrderId(orderType + currTime + uuid);
				orderCx.setCreateTime(currTime + "");
				orderCx.setOrderType(orderType);
				orderCx.setUpdateTime(currTime + "");
				orderCx.setPolicyholder("");
				orderCx.setUpdateUser(userId);
				orderCx.setOrderFlag("01");
				orderCx.setCarOwner(createBXOrderVo.getCarOwnerName());
				orderCx.setCarNum(createBXOrderVo.getCarNum());
				orderCx.setTbdw(createBXOrderVo.getTbDanWei());
				// 新建投保人-客户
				if (createBXOrderVo.getTbrName() != null) {
					TCustomerBasicinfo tbr = new TCustomerBasicinfo();
					uuid = UUIDUtil.getUUID32();
					tbr.setUserId(uuid);
					tbr.setSex(createBXOrderVo.getTbrSex());
					tbr.setAge(createBXOrderVo.getTbrAge());
					tbr.setUserName(createBXOrderVo.getTbrName());
					iTCustomerBasicinfoServiceImpl.insert(tbr);
				}
				orderCx.setPolicyholderId(uuid);
				orderCx.setPolicyholder(createBXOrderVo.getTbrName());// 投保人
				orderCx.setBeneficiary(createBXOrderVo.getBbRName());// 被保人
				orderCx.setBbrAge(createBXOrderVo.getBbRAge());
				orderCx.setBbrSex(createBXOrderVo.getBbRSex());
				orderCx.setRemark(createBXOrderVo.getBxRemark());
				iOrderCxServiceImpl.insert(orderCx);
			} else {
				throw new RuntimeException("订单类型错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("服务器异常");
		}
	}

	@Override
	public String getOrderTypeByOrderId(String orderId) {
		return dingDanZhuiZongMapper.getOrderTypeByOrderId(orderId);
	}

	@Override
	public Map<String, Object> getCDOrderDetailInfoByOrderId(String orderId) {
		return dingDanZhuiZongMapper.getCDOrderDetailInfoByOrderId(orderId);
	}

	@Override
	public Map<String, Object> getESCOrderDetailInfoByOrderId(String orderId) {
		return dingDanZhuiZongMapper.getESCOrderDetailInfoByOrderId(orderId);
	}

	@Override
	public Map<String, Object> getCMOrderDetailInfoByOrderId(String orderId) {
		return dingDanZhuiZongMapper.getCMOrderDetailInfoByOrderId(orderId);
	}

	@Override
	public Map<String, Object> getDKOrderDetailInfoByOrderId(String orderId) {
		return dingDanZhuiZongMapper.getDKOrderDetailInfoByOrderId(orderId);
	}

	@Override
	public void editOrderDetailInfo(EditOrderInfo editOrderInfo) {
		dingDanZhuiZongMapper.editOrderDetailInfo(editOrderInfo);
	}

	@Override
	public List<Map<String, String>> getOrderSummaryByUserId(String userId) {
		return dingDanZhuiZongMapper.getOrderSummaryByUserId(userId);
	}

	@Override
	public void updateOrderStatusByOrderId(String orderId, String memberId, String orderFlag) {
		dingDanZhuiZongMapper.updateOrderStatusByOrderId(orderId, memberId, orderFlag);
	}

	@Override
	public OrderInfo getOrderInfoByOrderId(String orderId, String memberId) {
		return dingDanZhuiZongMapper.getOrderInfoByOrderId(orderId, memberId);
	}

	@Override
	public List<Map<String, String>> getOrderListByOrderTypeAndUserId(Map<String, Object> condition) {
		return dingDanZhuiZongMapper.getOrderListByOrderTypeAndUserId(condition);
	}

	@Override
	public long getOrderCountByOrderTypeAndUserId(Map<String, Object> condition) {
		return dingDanZhuiZongMapper.getOrderCountByOrderTypeAndUserId(condition);
	}
}
