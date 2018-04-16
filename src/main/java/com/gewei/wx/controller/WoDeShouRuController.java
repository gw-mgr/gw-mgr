package com.gewei.wx.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.PageInfo;
import com.gewei.commons.result.Result;
import com.gewei.commons.utils.DateUtil;
import com.gewei.fuwushang.service.IOrderCxService;
import com.gewei.fuwushang.service.IOrderInfoService;
import com.gewei.fuwushang.service.IOrderSxService;
import com.gewei.fuwushang.service.IPolicyholderService;
import com.gewei.fuwushang.service.ITCustomerBasicinfoService;
import com.gewei.model.OrderCx;
import com.gewei.model.OrderInfo;
import com.gewei.model.OrderSx;
import com.gewei.model.Policyholder;
import com.gewei.model.TAccount;
import com.gewei.model.TAccountFlow;
import com.gewei.model.TCustomerBasicinfo;
import com.gewei.model.TMemberBasicinfo;
import com.gewei.wx.service.IAccountService;
import com.gewei.wx.service.IShanJiaFuWuService;
import com.gewei.wx.service.ITAccountFlowService;
import com.gewei.wx.service.ITMemberBasicinfoService;

@Controller
@RequestMapping("/wodechexiantuandui")
public class WoDeShouRuController extends BaseController {
	@Autowired
	private ITAccountFlowService iTAccountFlowServiceImpl;
	@Autowired
	private ITCustomerBasicinfoService iTCustomerBasicinfoServiceImpl;
	@Autowired
	private ITMemberBasicinfoService iTMemberBasicinfoServiceImpl;
	@Autowired
	private IAccountService iAccountServiceImpl;
	@Autowired
	private IShanJiaFuWuService iShanJiaFuWuServiceImpl;
	@Autowired
	private IOrderInfoService iOrderInfoServiceImpl;
	@Autowired
	private IOrderSxService iIOrderSxServiceImpl;
	@Autowired
	private IOrderCxService iIOrderCxServiceImpl;
	@Autowired
	private IPolicyholderService iPolicyholderServiceImpl;

	// 会员车险团队查询
	@RequestMapping("/getCheXianTermByUserId")
	@ResponseBody
	public Object getCheXianTermByUserId(String userId, String type, Integer page, Integer rows) {
		Result result = new Result();
		try {
			PageInfo pageInfo = new PageInfo(page == null ? 1 : page, rows == null ? 10 : rows, "USER_ID", "asc");
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put(type, userId);
			pageInfo.setCondition(condition);
			Page<TMemberBasicinfo> page2 = getPage(pageInfo);
			Page<TMemberBasicinfo> pages = iTMemberBasicinfoServiceImpl.selectPage(page2);
			List<TMemberBasicinfo> records = pages.getRecords();
			ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			for (TMemberBasicinfo tMemberBasicinfo : records) {
				// 推荐奖
				String userId2 = tMemberBasicinfo.getUserId();
				EntityWrapper<TAccountFlow> wrapper0 = new EntityWrapper<TAccountFlow>();
				wrapper0.eq("PERSON_ID", userId2);
				wrapper0.eq("TRADE_TYPE", "+");
				wrapper0.or("ORDER_TYPE = {0} or ORDER_TYPE = {1}", "ZJT", "JJT");
				List<TAccountFlow> list0 = iTAccountFlowServiceImpl.selectList(wrapper0);
				long money = 0;
				for (TAccountFlow tAccountFlow : list0) {
					money += tAccountFlow.getTradeValue();
				}
				hashMap.put("recommendMoney", money);
				hashMap.put("registerTime", tMemberBasicinfo.getCreateTime());
				hashMap.put("wechatNum", tMemberBasicinfo.getWechatNum());
				hashMap.put("conTel", tMemberBasicinfo.getTelephone());
				String dirRecommender = tMemberBasicinfo.getDirRecommender();// 间接推荐人
				// 推荐人姓名
				EntityWrapper<TMemberBasicinfo> wrapper2 = new EntityWrapper<TMemberBasicinfo>();
				wrapper2.eq("USER_ID", dirRecommender);
				TMemberBasicinfo memberBasicinfo = iTMemberBasicinfoServiceImpl.selectOne(wrapper2);
				hashMap.put("director", memberBasicinfo.getUserName());
				arrayList.add(hashMap);
			}
			int total = pages.getTotal();
			result.setSuccess(true);
			result.setObj(arrayList);
			result.setTotalCount((long) total);
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("交易失败！");
		}
		return result;
	}

	// 会员车险团队收入概况查询
	@RequestMapping("/getCheXianIncomeSummaryByUserId")
	@ResponseBody
	public Object getCheXianIncomeSummaryByUserId(String userId) {
		Result result = new Result();
		try {
			String month = DateUtil.get_String$yyyyMMddHHmmss(new Date()).substring(0, 6);
			String currTime = DateUtil.get_String$yyyyMMddHHmmss(new Date());
			HashMap<String, Long> hashMap = new HashMap<String, Long>();
			// 可提现金额======================================================
			EntityWrapper<TAccountFlow> wrapper0 = new EntityWrapper<TAccountFlow>();
			wrapper0.eq("PERSON_ID", userId);
			wrapper0.eq("TRADE_TYPE", "+");
			wrapper0.or("ORDER_TYPE = {0}", "ROOTCCCL");
			wrapper0.like("UPDATE_TIME", month, SqlLike.RIGHT);
			List<TAccountFlow> list0 = iTAccountFlowServiceImpl.selectList(wrapper0);
			long money = 0;// 本月车险佣金
			for (TAccountFlow tAccountFlow : list0) {
				money += tAccountFlow.getTradeValue();
			}
			// 未结算金额======================================================
			TAccount tAccount = iShanJiaFuWuServiceImpl.getAccountByPersonId(userId);
			if (tAccount == null) {
				tAccount = new TAccount();
				tAccount.setBalance(0L);
				tAccount.setPersonId(userId);
				tAccount.setBankHostName("");
				tAccount.setBankCardId("");
				tAccount.setTotalIncome(0L);
				tAccount.setSettleApplying(0L);
				tAccount.setUpdateTime(currTime);
				iAccountServiceImpl.insert(tAccount);
				hashMap.put("balance", 0L);// 未结算金额
				hashMap.put("restCash", 0L);// 可提现
				hashMap.put("totalIncome", 0L);///累计收入
				hashMap.put("RECOMMENDER", 0L);
				hashMap.put("DIR_RECOMMENDER", 0L);
				result.setObj(hashMap);
				result.setSuccess(true);
				return result;
			}
			Long balance = tAccount.getBalance();
			hashMap.put("balance", balance);// 未结算金额
			hashMap.put("restCash", balance);// 可提现
			if (money == 0) {//如果会员本人当月车险佣金为0，那么可提现金额为0
				hashMap.put("restCash", 0L);
			}
			// 直接推荐人数======================================================
			EntityWrapper<TMemberBasicinfo> wrapper2 = new EntityWrapper<TMemberBasicinfo>();
			wrapper2.eq("RECOMMENDER", userId);
			int RECOMMENDER = iTMemberBasicinfoServiceImpl.selectCount(wrapper2);
			hashMap.put("RECOMMENDER", (long) RECOMMENDER);
			// 间接推荐人数======================================================
			EntityWrapper<TMemberBasicinfo> wrapper3 = new EntityWrapper<TMemberBasicinfo>();
			wrapper3.eq("DIR_RECOMMENDER", userId);
			int DIR_RECOMMENDER = iTMemberBasicinfoServiceImpl.selectCount(wrapper3);
			hashMap.put("DIR_RECOMMENDER", (long) DIR_RECOMMENDER);
			// 累计收入======================================================
			EntityWrapper<TAccountFlow> wrapper = new EntityWrapper<TAccountFlow>();
			wrapper.eq("PERSON_ID", userId);
			wrapper.eq("TRADE_TYPE", "+");
			wrapper.or("ORDER_TYPE = {0} or ORDER_TYPE = {1}  or ORDER_TYPE = {2}", "ZJT", "JJT", "ROOTCCCL");
			List<TAccountFlow> list = iTAccountFlowServiceImpl.selectList(wrapper);
			long totalIncome = 0;
			for (TAccountFlow tAccountFlow : list) {
				totalIncome += tAccountFlow.getTradeValue();
			}
			hashMap.put("totalIncome", totalIncome);//累计收入
			result.setObj(hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("交易失败！");
		}
		return result;
	}

	// 会员收入概况查询
	@RequestMapping("/getMyIncomeSummaryByUserId")
	@ResponseBody
	public Object getMyIncomeSummaryByUserId(String userId) {
		Result result = new Result();
		try {
			String month = DateUtil.get_String$yyyyMMddHHmmss(new Date()).substring(0, 6);
			String currTime = DateUtil.get_String$yyyyMMddHHmmss(new Date());
			HashMap<String, Long> hashMap = new HashMap<String, Long>();
			// 总账务
			TAccount tAccount = iShanJiaFuWuServiceImpl.getAccountByPersonId(userId);
			System.out.println("【tAccount】" + JSON.toJSONString(tAccount));
			if (tAccount == null) {
				tAccount = new TAccount();
				tAccount.setBalance(0L);
				tAccount.setPersonId(userId);
				tAccount.setBankHostName("");
				tAccount.setBankCardId("");
				tAccount.setTotalIncome(0L);
				tAccount.setSettleApplying(0L);
				tAccount.setUpdateTime(currTime);
				iAccountServiceImpl.insert(tAccount);
				hashMap.put("balance", 0L);//可提现金额
				hashMap.put("totalIncome", 0L);//累计收入
				hashMap.put("EstimateMoney", 0L);//本月预估收入
				result.setObj(hashMap);
				result.setSuccess(true);
				return result;
			}
			long balance = tAccount.getBalance();
			hashMap.put("balance", balance);//可提现金额
			long totalIncome = tAccount.getTotalIncome();
			hashMap.put("totalIncome", totalIncome);//累计收入
			// 本月预估金额
			EntityWrapper<TAccountFlow> wrapper = new EntityWrapper<TAccountFlow>();
			wrapper.eq("PERSON_ID", userId);
			wrapper.eq("TRADE_TYPE", "+");
			wrapper.or("ORDER_TYPE like 'ROOTCC%' or ORDER_TYPE = {0} or ORDER_TYPE = {1}", "ROOTRS", "ROOTDK");
			wrapper.eq("UPDATE_TIME", month);
			List<TAccountFlow> list = iTAccountFlowServiceImpl.selectList(wrapper);
			long estimateMoney = 0;
			for (TAccountFlow tAccountFlow : list) {
				estimateMoney += tAccountFlow.getTradeValue();
			}
			hashMap.put("EstimateMoney", estimateMoney);//本月预估收入
			result.setSuccess(true);
			result.setObj(hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("交易失败！");
		}
		return result;
	}

	// 会员收入列表
	@RequestMapping("/getIncomeListByOrderType")
	@ResponseBody
	public Object getIncomeListByOrderType(String userId, String createTime, String orderType, Integer page, Integer rows) {
		Result result = new Result();
		Integer start = (page - 1) * rows;
		try {
			System.out.println("[getIncomeListByOrderType]:orderType=" + orderType);
			if (orderType.equals("ROOTRS")) {
				ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
				// 人寿保险
				EntityWrapper<OrderSx> wrapper = new EntityWrapper<OrderSx>();
				wrapper.eq("SALES_MAN", userId);
				wrapper.eq("ORDER_FLAG", "02");
				wrapper.orderBy("CREATE_TIME", false);
				wrapper.like("CREATE_TIME", createTime, SqlLike.RIGHT);
				int selectCount = iIOrderSxServiceImpl.selectCount(wrapper);
				wrapper.last("limit " + start + "," + rows);
				List<OrderSx> records = iIOrderSxServiceImpl.selectList(wrapper);
				for (OrderSx orderSx : records) {
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					String policyholderId = orderSx.getPolicyholderId();
					EntityWrapper<Policyholder> wrapper2 = new EntityWrapper<Policyholder>();
					wrapper2.eq("PID", policyholderId);
					Policyholder policyholder = iPolicyholderServiceImpl.selectOne(wrapper2);
					hashMap.put("tbr", policyholder.getUserName());
					hashMap.put("zbf", orderSx.getBfhj());
					hashMap.put("yjje", orderSx.getSalesManCommission());
					hashMap.put("zhjrj", orderSx.getZhjrjje());
					hashMap.put("time", orderSx.getCreateTime());
					arrayList.add(hashMap);
				}
				result.setObj(arrayList);
				result.setTotalCount((long) selectCount);
			} else if (orderType.equals("ROOTCCCL")) {
				//车辆保险
				ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
				EntityWrapper<OrderCx> wrapper = new EntityWrapper<OrderCx>();
				wrapper.eq("SALES_MAN", userId);
				wrapper.eq("YJZFDX", userId);
				wrapper.eq("ORDER_FLAG", "02");
				wrapper.eq("ORDER_TYPE", "ROOTCCCL");
				wrapper.orderBy("CREATE_TIME", false);
				wrapper.like("CREATE_TIME", createTime, SqlLike.RIGHT);
				int selectCount = iIOrderCxServiceImpl.selectCount(wrapper);
				wrapper.last("limit " + start + "," + rows);
				List<OrderCx> records = iIOrderCxServiceImpl.selectList(wrapper);
				for (OrderCx orderCx : records) {
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					String policyholderId = orderCx.getPolicyholderId();
					EntityWrapper<Policyholder> wrapper2 = new EntityWrapper<Policyholder>();
					wrapper2.eq("PID", policyholderId);
					Policyholder policyholder = iPolicyholderServiceImpl.selectOne(wrapper2);
					hashMap.put("cph", policyholder.getUserName());
					hashMap.put("cz", orderCx.getCarOwner());
					hashMap.put("zbf", orderCx.getZbf());
					hashMap.put("yjje", orderCx.getSjzfyj());
					hashMap.put("zhjrj", orderCx.getZhjrjje());
					hashMap.put("time", orderCx.getCreateTime());
					arrayList.add(hashMap);
				}
				result.setObj(arrayList);
				result.setTotalCount((long) selectCount);
			} else if (orderType.equals("ROOTCCTY") || orderType.equals("ROOTCCGC") || orderType.equals("ROOTCCJZ") || orderType.equals("ROOTCCGZ") || orderType.equals("ROOTCCDQ")) {
				//团意雇主险,工程机械险,建筑工程险,公众责任险,短期意外险
				ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
				EntityWrapper<OrderCx> wrapper = new EntityWrapper<OrderCx>();
				wrapper.eq("SALES_MAN", userId);
				wrapper.eq("YJZFDX", userId);
				wrapper.eq("ORDER_FLAG", "02");
				wrapper.eq("ORDER_TYPE", orderType);
				wrapper.orderBy("CREATE_TIME", false);
				wrapper.like("CREATE_TIME", createTime, SqlLike.RIGHT);
				int selectCount = iIOrderCxServiceImpl.selectCount(wrapper);
				wrapper.last("limit " + start + "," + rows);
				List<OrderCx> records = iIOrderCxServiceImpl.selectList(wrapper);
				for (OrderCx orderCx : records) {
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					String policyholderId = orderCx.getPolicyholderId();
					EntityWrapper<Policyholder> wrapper2 = new EntityWrapper<Policyholder>();
					wrapper2.eq("PID", policyholderId);
					Policyholder policyholder = iPolicyholderServiceImpl.selectOne(wrapper2);
					hashMap.put("tbr", policyholder.getUserName());
					hashMap.put("zbf", orderCx.getZbf());
					hashMap.put("yjje", orderCx.getSjzfyj());
					hashMap.put("zhjrj", orderCx.getZhjrjje());
					hashMap.put("time", orderCx.getCreateTime());
					arrayList.add(hashMap);
				}
				result.setObj(arrayList);
				result.setTotalCount((long) selectCount);
			} else if (orderType.equals("ROOTDK")) {
				//贷款业务
				ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
				EntityWrapper<OrderInfo> wrapper = new EntityWrapper<OrderInfo>();
				wrapper.eq("SALES_MAN", userId);
				wrapper.eq("COMMISSION_FOR", userId);
				//				wrapper.eq("YJ_ZF", "02");// 未支付
				wrapper.in("ORDER_FLAG", new String[] { "03", "04" });
				wrapper.eq("ORDER_TYPE", "ROOTDK");
				wrapper.orderBy("CREATE_TIME", false);
				wrapper.like("CREATE_TIME", createTime, SqlLike.RIGHT);
				int selectCount = iOrderInfoServiceImpl.selectCount(wrapper);
				wrapper.last("limit " + start + "," + rows);
				List<OrderInfo> records = iOrderInfoServiceImpl.selectList(wrapper);
				for (OrderInfo orderInfo : records) {
					EntityWrapper<TCustomerBasicinfo> wrapper2 = new EntityWrapper<TCustomerBasicinfo>();
					wrapper2.eq("USER_ID", orderInfo.getUserId());
					TCustomerBasicinfo customerBasicinfo = iTCustomerBasicinfoServiceImpl.selectOne(wrapper2);
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("dkr", customerBasicinfo.getUserName());
					hashMap.put("dkje", orderInfo.getOrderMoney());
					hashMap.put("yjje", orderInfo.getCommission());
					hashMap.put("time", orderInfo.getCreateTime());
					arrayList.add(hashMap);
				}
				result.setObj(arrayList);
				result.setTotalCount((long) selectCount);
			} else {
				return renderError("类型错误");
			}
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("交易失败！");
		}
	}
}
