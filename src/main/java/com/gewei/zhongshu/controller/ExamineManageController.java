package com.gewei.zhongshu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.PageInfo;
import com.gewei.commons.utils.BeanUtils;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.UUIDUtil;
import com.gewei.fuwushang.service.IOrderCxService;
import com.gewei.model.OrderCx;
import com.gewei.model.TAccount;
import com.gewei.model.TAccountFlow;
import com.gewei.model.TMemberBasicinfo;
import com.gewei.model.TRecommendAward;
import com.gewei.model.vo.MerchantVo;
import com.gewei.wx.service.IAccountService;
import com.gewei.wx.service.IShanJiaFuWuService;
import com.gewei.wx.service.ITAccountFlowService;
import com.gewei.wx.service.ITMemberBasicinfoService;
import com.gewei.zhongshu.service.ITMerchantProductManageService;
import com.gewei.zhongshu.service.ITRecommendAwardService;
import com.gewei.zhongshu.service.IUserService;

/**
 * @Description: 审核管理控制层
 * @author: Tiger
 * @date: 2018年2月5日 下午8:44:05
 */
@Controller
@RequestMapping("/mgr/ExamineManage")
public class ExamineManageController extends BaseController {
	@Autowired
	private ITMerchantProductManageService iTMerchantProductManageServiceImpl;
	@Autowired
	private IUserService userServiceImpl;
	@Autowired
	private IAccountService iAccountServiceImpl;
	@Autowired
	private IOrderCxService iOrderCxServiceImpl;
	@Autowired
	private IShanJiaFuWuService iShanJiaFuWuServiceImpl;
	@Autowired
	private ITAccountFlowService iTAccountFlowServiceImpl;
	@Autowired
	private ITMemberBasicinfoService iTMemberBasicinfoServiceImpl;
	@Autowired
	private ITRecommendAwardService iTRecommendAwardServiceImpl;

	@GetMapping("/getDirectSell")
	public String directSell() {
		return "admin/examineManage/tGetDirectSell";
	}

	@GetMapping("/getCash")
	public String cashManager() {
		return "admin/examineManage/tGetCash";
	}

	@GetMapping("/getSettle")
	public String settleManager() {
		return "admin/examineManage/tGetSettle";
	}

	@PostMapping("/updateTuiJianJiangStatus")
	@ResponseBody
	public Object updateTuiJianJiangStatus(String orderId) {
		try {
			System.out.println("【取消推荐奖】orderId=" + orderId);
			EntityWrapper<OrderCx> entityWrapper = new EntityWrapper<OrderCx>();
			entityWrapper.eq("ORDER_ID", orderId);
			OrderCx orderCx = iOrderCxServiceImpl.selectOne(entityWrapper);
			orderCx.setTjjyjCancle("01");
			orderCx.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
			// 记录操作人
			Subject currentUser = SecurityUtils.getSubject();
			PrincipalCollection collection = currentUser.getPrincipals();
			String operatorId = null;
			if (null != collection) {
				String loginName = collection.getPrimaryPrincipal().toString();
				operatorId = userServiceImpl.getUserIdByLoginName(loginName);
				orderCx.setUpdateUser(operatorId);
			}
			// 更新数据库
			iOrderCxServiceImpl.update(orderCx, entityWrapper);
			// 直接推荐奖
			long jsyx = orderCx.getJsyx().longValue();
			TRecommendAward recommendAward1 = iTRecommendAwardServiceImpl.selectById("1");
			float rate1 = recommendAward1.getRate();
			float zjtjj = jsyx * rate1;
			// 间接推荐奖
			TRecommendAward recommendAward2 = iTRecommendAwardServiceImpl.selectById("2");
			float rate2 = recommendAward2.getRate();
			float jjtjj = jsyx * rate2;
			// 取消推荐奖
			String salesMan = orderCx.getSalesMan();
			Wrapper<TMemberBasicinfo> wrapper = new EntityWrapper<TMemberBasicinfo>();
			wrapper.eq("USER_ID", salesMan);
			TMemberBasicinfo memberBasicinfo = iTMemberBasicinfoServiceImpl.selectOne(wrapper);
			if (memberBasicinfo != null) {
				// 1.取消直接推荐奖-直接推荐人
				String recommender = memberBasicinfo.getRecommender();
				if (!recommender.isEmpty()) {
					System.out.println("【直接推荐人】PERSON_ID=" + recommender);
					// 修改账务信息
					TAccount tAccount = iShanJiaFuWuServiceImpl.getAccountByPersonId(recommender);
					if (tAccount == null) {
						// 插入账务记录
						tAccount = new TAccount();
						tAccount.setBalance(0L);
						tAccount.setPersonId(recommender);
						tAccount.setBankHostName("");
						tAccount.setBankCardId("");
						tAccount.setTotalIncome((long) -zjtjj);
						tAccount.setSettleApplying(0L);
						tAccount.setBalance((long) -zjtjj);
					}
					// 更新
					long beforeValue = tAccount.getBalance();
					long totalIncomeBeforeValue = tAccount.getTotalIncome();
					long totalIncome = (long) (totalIncomeBeforeValue - zjtjj);
					long balance = (long) (tAccount.getBalance() - zjtjj);
					tAccount.setBalance(balance);
					tAccount.setTotalIncome(totalIncome);
					tAccount.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
					System.out.println("【直接推荐奖账务】tAccount=" + tAccount);
					iAccountServiceImpl.insertOrUpdate(tAccount);
					// 新增账务流水
					TAccountFlow tAccountFlow = new TAccountFlow();
					long currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date());
					String uuid = UUIDUtil.getUUID32().substring(0, 10);
					String flowId = "QZJT" + currTime + uuid;
					tAccountFlow.setFlowId(flowId);
					tAccountFlow.setOrderType("QZJT");
					tAccountFlow.setTradeValue((long) zjtjj);
					tAccountFlow.setStatus("02");
					tAccountFlow.setBeforeValue(beforeValue);
					tAccountFlow.setTradeType("-");
					tAccountFlow.setAfterValue((long) (beforeValue - zjtjj));
					tAccountFlow.setBankHostName("");
					tAccountFlow.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
					tAccountFlow.setPersonId(operatorId);
					System.out.println("【直接推荐奖账务流水】tAccountFlow=" + tAccountFlow);
					iTAccountFlowServiceImpl.insert(tAccountFlow);
				}
				String dirRecommender = memberBasicinfo.getDirRecommender();
				// 2.取消直接推荐奖-间接推荐人
				if (!dirRecommender.isEmpty()) {
					System.out.println("【间接推荐人】PERSON_ID=" + dirRecommender);
					// 修改账务信息
					// 修改账务信息
					TAccount tAccount = iShanJiaFuWuServiceImpl.getAccountByPersonId(dirRecommender);
					if (tAccount == null) {
						// 插入账务记录
						tAccount = new TAccount();
						tAccount.setBalance(0L);
						tAccount.setPersonId(dirRecommender);
						tAccount.setBankHostName("");
						tAccount.setBankCardId("");
						tAccount.setTotalIncome((long) -jjtjj);
						tAccount.setSettleApplying(0L);
						tAccount.setBalance((long) -jjtjj);
					}
					// 更新
					long beforeValue = tAccount.getBalance();
					long totalIncomeBeforeValue = tAccount.getTotalIncome();
					long totalIncome = (long) (totalIncomeBeforeValue - jjtjj);
					long balance = (long) (tAccount.getBalance() - jjtjj);
					tAccount.setBalance(balance);
					tAccount.setTotalIncome(totalIncome);
					tAccount.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
					System.out.println("【间接推荐奖账务】tAccount=" + tAccount);
					iAccountServiceImpl.insertOrUpdate(tAccount);
					// 新增账务流水
					TAccountFlow tAccountFlow = new TAccountFlow();
					long currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date());
					String uuid = UUIDUtil.getUUID32().substring(0, 10);
					String flowId = "QJJT" + currTime + uuid;
					tAccountFlow.setFlowId(flowId);
					tAccountFlow.setOrderType("QJJT");
					tAccountFlow.setTradeValue((long) jjtjj);
					tAccountFlow.setStatus("02");
					tAccountFlow.setBeforeValue(beforeValue);
					tAccountFlow.setTradeType("-");
					tAccountFlow.setAfterValue((long) (beforeValue - jjtjj));
					tAccountFlow.setBankHostName("");
					tAccountFlow.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
					tAccountFlow.setPersonId(operatorId);
					System.out.println("【间接推荐奖账务流水】tAccountFlow=" + tAccountFlow);
					iTAccountFlowServiceImpl.insert(tAccountFlow);
				}
			}
			return renderSuccess("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("修改失败");
		}
	}

	@PostMapping("/directSellDataGrid")
	@ResponseBody
	public PageInfo directSellDataGrid(MerchantVo merchantVo, Integer page, Integer rows, String sort, String order) {
		int start = (page - 1) * rows;
		try {
			System.out.println("merchantVo:" + JSON.toJSONString(merchantVo));
			PageInfo pageInfo = new PageInfo(page, rows, sort, order);
			EntityWrapper<OrderCx> entityWrapper = new EntityWrapper<OrderCx>();
			String keywordInfo = merchantVo.getKeywordInfo();
			String salesManNameQ = "";
			if (!StringUtils.isEmpty(keywordInfo) && "SALES_MAN".equals(merchantVo.getKeywordType())) {
				salesManNameQ = keywordInfo;
			}
			if (!StringUtils.isEmpty(keywordInfo) && !"SALES_MAN".equals(merchantVo.getKeywordType())) {
				entityWrapper.like(merchantVo.getKeywordType(), keywordInfo, SqlLike.DEFAULT);
			}
			entityWrapper.eq("ORDER_TYPE", "ROOTCCCL");
			entityWrapper.last("limit " + start + "," + rows);
			List<OrderCx> selectList = iOrderCxServiceImpl.selectList(entityWrapper);
			ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
			for (OrderCx orderCx : selectList) {
				System.out.println("【车险订单编号】：" + orderCx.getOrderId());
				Map<String, Object> map = BeanUtils.ConvertObjToMap(orderCx);
				// 业务员姓名
				String salesMan = orderCx.getSalesMan();
				Wrapper<TMemberBasicinfo> wrapper = new EntityWrapper<TMemberBasicinfo>();
				wrapper.eq("USER_ID", salesMan);
				System.out.println("【车险订单业务员编号】：" + salesMan);
				TMemberBasicinfo memberBasicinfo = iTMemberBasicinfoServiceImpl.selectOne(wrapper);
				String salesManName = memberBasicinfo.getUserName() == null ? "" : memberBasicinfo.getUserName();
				if (!salesManName.contains(salesManNameQ)) {
					continue;
				}
				map.put("salesManName", salesManName);
				// 直接推荐奖
				long jsyx = orderCx.getJsyx().longValue();
				TRecommendAward recommendAward1 = iTRecommendAwardServiceImpl.selectById("1");
				float rate1 = recommendAward1.getRate();
				float zjtjj = jsyx * rate1;
				map.put("zjRecommendAward", zjtjj);
				// 间接推荐奖
				TRecommendAward recommendAward2 = iTRecommendAwardServiceImpl.selectById("2");
				float rate2 = recommendAward2.getRate();
				float jjtjj = jsyx * rate2;
				map.put("jjRecommendAward", jjtjj);
				arrayList.add(map);
			}
			int total = iOrderCxServiceImpl.selectCount(entityWrapper);
			pageInfo.setRows(arrayList);
			pageInfo.setTotal(total);
			return pageInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/settleDataGrid")
	@ResponseBody
	public PageInfo settleDataGrid(MerchantVo merchantVo, Integer page, Integer rows, String sort, String order) {
		try {
			System.out.println("merchantVo:" + JSON.toJSONString(merchantVo));
			PageInfo pageInfo = new PageInfo(page, rows, sort, order);
			Map<String, Object> condition = new HashMap<String, Object>();
			String status = merchantVo.getStatus();
			String keywordInfo = merchantVo.getKeywordInfo();
			if (!StringUtils.isEmpty(status)) {
				condition.put("status", status);
			}
			if (!StringUtils.isEmpty(keywordInfo)) {
				condition.put("merchantName", keywordInfo);
			}
			pageInfo.setCondition(condition);
			iTMerchantProductManageServiceImpl.settleDataGrid(pageInfo);
			return pageInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 通过结算or结算
	@PostMapping("/passSettle")
	@ResponseBody
	public Object passSettleOrCash(String flowId, String orderType) {
		try {
			if (orderType == null || !"TX".equals(orderType) || !"JS".equals(orderType))
				return renderError("类型有误");
			EntityWrapper<TAccountFlow> entityWrapper = new EntityWrapper<TAccountFlow>();
			entityWrapper.eq("FLOW_ID", flowId);
			entityWrapper.eq("ORDER_TYPE", orderType);
			TAccountFlow accountFlow = iTAccountFlowServiceImpl.selectOne(entityWrapper);
			if (accountFlow == null)
				return renderError("结算(提现)申请不存在");
			// 记录操作人
			Subject currentUser = SecurityUtils.getSubject();
			PrincipalCollection collection = currentUser.getPrincipals();
			String operatorId = null;
			if (null != collection) {
				String loginName = collection.getPrimaryPrincipal().toString();
				operatorId = userServiceImpl.getUserIdByLoginName(loginName);
			}
			iTMerchantProductManageServiceImpl.passSettleOrCash(flowId, orderType, operatorId);
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败！");
		}
		return renderSuccess("操作成功！");
	}

	@PostMapping("/cashDataGrid")
	@ResponseBody
	public PageInfo cashDataGrid(MerchantVo merchantVo, Integer page, Integer rows, String sort, String order) {
		System.out.println("merchantVo:" + JSON.toJSONString(merchantVo));
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		String status = merchantVo.getStatus();
		String keywordInfo = merchantVo.getKeywordInfo();
		if (!StringUtils.isEmpty(status)) {
			condition.put("status", status);
		}
		if (!StringUtils.isEmpty(keywordInfo)) {
			condition.put("userName", keywordInfo);
		}
		pageInfo.setCondition(condition);
		iTMerchantProductManageServiceImpl.cashDataGrid(pageInfo);
		return pageInfo;
	}

	@PostMapping("/recommendAwardDataGrid")
	@ResponseBody
	public PageInfo recommendAwardDataGrid() {
		PageInfo pageInfo = iTMerchantProductManageServiceImpl.recommendAwardDataGrid();
		return pageInfo;
	}

	@PostMapping("/recommendAwardEdit")
	@ResponseBody
	public Object recommendAwardEdit(String firstGeneration, String secondGeneration) {
		try {
			iTMerchantProductManageServiceImpl.recommendAwardEdit(firstGeneration, secondGeneration);
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("添加失败！");
		}
		return renderSuccess("添加成功！");
	}

	@PostMapping("/connectQueryFeeDataGrid")
	@ResponseBody
	public PageInfo connectQueryFeeDataGrid() {
		PageInfo pageInfo = iTMerchantProductManageServiceImpl.connectQueryFeeDataGrid();
		return pageInfo;
	}

	@PostMapping("/connectQueryFeeEdit")
	@ResponseBody
	public Object connectQueryFeeEdit(String ROOTDK, String ROOTQC, String ROOTCD, String ROOTCM) {
		try {
			iTMerchantProductManageServiceImpl.connectQueryFeeEdit(ROOTDK, ROOTQC, ROOTCD, ROOTCM);
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("添加失败！");
		}
		return renderSuccess("添加成功！");
	}
}
