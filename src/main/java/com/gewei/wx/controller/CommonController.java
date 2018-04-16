package com.gewei.wx.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.PageInfo;
import com.gewei.commons.result.Result;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.UUIDUtil;
import com.gewei.fuwushang.service.IMessageService;
import com.gewei.fuwushang.service.ITProductManageService;
import com.gewei.model.AreaInfo;
import com.gewei.model.Message;
import com.gewei.model.TAccount;
import com.gewei.model.TAccountFlow;
import com.gewei.model.TChinaArea;
import com.gewei.model.TMemberBasicinfo;
import com.gewei.model.TProductManage;
import com.gewei.wx.service.IAccountService;
import com.gewei.wx.service.ICommonService;
import com.gewei.wx.service.IShanJiaFuWuService;
import com.gewei.wx.service.ITAccountFlowService;
import com.gewei.wx.service.ITMemberBasicinfoService;
import com.gewei.zhongshu.service.IZMessageService;

@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {
	@Autowired
	private ICommonService iCommonServiceImpl;
	@Autowired
	private IMessageService iMessageServiceImpl;
	@Autowired
	private ITProductManageService iTProductManageServiceImpl;
	@Autowired
	private ITMemberBasicinfoService iTMemberBasicinfoServiceImpl;
	@Autowired
	private ITAccountFlowService iTAccountFlowServiceImpl;
	@Autowired
	private IAccountService iAccountServiceImpl;
	@Autowired
	private IShanJiaFuWuService iShanJiaFuWuServiceImpl;
	@Autowired
	private IZMessageService iIZMessageServiceImpl;

	// 根据区域的父节点ID，获取子节点列表
	@RequestMapping("/findAreaNodeByParentId")
	@ResponseBody
	public Object findAreaNodeByParentId(String parentId) {
		Result result = new Result();
		try {
			List<TChinaArea> childList = iCommonServiceImpl.findAreaNodeByParentId(parentId);
			result.setSuccess(true);
			result.setObj(childList);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("服务器内部错误");
			result.setObj(null);
			return result;
		}
	}

	// 微信端消息
	@RequestMapping("/message")
	@ResponseBody
	public Object findMessage(String userId, Long page, Long rows) {
		Result result = new Result();
		try {
			PageInfo pageInfo = new PageInfo(page.intValue(), rows.intValue(), "UPDATE_TIME", "asc");
			EntityWrapper<Message> wrapper = new EntityWrapper<Message>();
			wrapper.like("TYPE", "3", SqlLike.RIGHT);
			wrapper.eq("USER_ID", userId);
			wrapper.eq("STATUS", "01");
			Page<Message> pages = getPage(pageInfo);
			pages = iMessageServiceImpl.selectPage(pages, wrapper);
			List<Message> records = pages.getRecords();
			for (Message message : records) {
				String productId = message.getProductId();
				if (productId != null) {
					EntityWrapper<TProductManage> wrapper2 = new EntityWrapper<TProductManage>();
					wrapper2.eq("PRODUCT_ID", productId);
					TProductManage productManage = iTProductManageServiceImpl.selectOne(wrapper2);
					String productName = productManage.getProductName();
					message.setProductId(productName);
				}
			}
			pageInfo.setRows(records);
			pageInfo.setTotal(pages.getTotal());
			result.setObj(pageInfo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("服务器内部错误");
			result.setObj(null);
			return result;
		}
	}

	// 微信端消息编辑：删除
	@RequestMapping("/messageDel")
	@ResponseBody
	public Object messageDel(String userId, String id) {
		try {
			Message message = iMessageServiceImpl.selectById(id);
			if (message == null)
				return renderError("消息不存在");
			message.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
			message.setStatus("03");
			boolean updateById = iMessageServiceImpl.updateById(message);
			if (updateById)
				return renderError("修改失败");
			return renderSuccess("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("服务器内部错误");
		}
	}

	// 区域数据查询
	@RequestMapping("/getAreaInfo")
	@ResponseBody
	public Object getAreaInfo() {
		Result result = new Result();
		try {
			List<AreaInfo> areaInfo = iCommonServiceImpl.findAreaInfo();
			result.setSuccess(true);
			result.setObj(areaInfo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("服务器内部错误");
			result.setObj(null);
			return result;
		}
	}

	// 发送短信验证码
	@RequestMapping("/sendSMSCode")
	@ResponseBody
	public Object sendSMSCode(String telphone) {
		try {
			// 参数校验
			if (telphone == null) {
				return renderError("手机号有误");
			}
			iCommonServiceImpl.sendSMSCode(telphone);
			return renderSuccess("发送成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("发送失败");
		}
	}

	// 服务商结算，查询上一次的“持卡人”+“银行卡卡号”
	@RequestMapping("/getLastBankInfoByUserId")
	@ResponseBody
	public Object getLastBankInfoByUserId(String userId) {
		Result result = new Result();
		try {
			// 用户校验
			// 上一次的“持卡人”+“银行卡卡号”查询
			Map<String, Object> bankInfoMap = iCommonServiceImpl.getLastBankInfoByPersonId(userId);
			result.setSuccess(true);
			result.setObj(bankInfoMap);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("服务器内部错误");
			result.setObj(null);
			return result;
		}
	}

	// 提现申请通用接口
	@RequestMapping("/applyGetSettle")
	@ResponseBody
	@Transactional
	public Object applyGetSettle(HttpServletRequest request, String userId, String bankHostName, String bankCardId, Long tradeValue) {
		try {
			// 用户编号校验
			EntityWrapper<TMemberBasicinfo> entityWrapper = new EntityWrapper<TMemberBasicinfo>();
			entityWrapper.eq("USER_ID", userId);
			TMemberBasicinfo memberBasicinfo = iTMemberBasicinfoServiceImpl.selectOne(entityWrapper);
			if (memberBasicinfo == null)
				return renderError("账户不存在");
			// 总账务
			TAccount tAccount = iShanJiaFuWuServiceImpl.getAccountByPersonId(userId);
			// 提现金额校验
			if (tradeValue < 0) {
				return renderError("提现金额有误");
			}
			long beforeValue = tAccount.getBalance();
			long balance = tAccount.getBalance() - tradeValue;
			if (balance < 0) {
				return renderError("提现金额有误");
			}
			tAccount.setBalance(balance);
			tAccount.setSettleApplying(tAccount.getSettleApplying() + tradeValue);
			tAccount.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
			tAccount.setBankHostName(bankHostName);
			tAccount.setBankCardId(bankCardId);
			iAccountServiceImpl.updateById(tAccount);
			// 新增账务流水
			TAccountFlow tAccountFlow = new TAccountFlow();
			long currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date());
			String uuid = UUIDUtil.getUUID32().substring(0, 10);
			String flowId = "TX" + currTime + uuid;
			tAccountFlow.setFlowId(flowId);
			tAccountFlow.setOrderType("TX");
			tAccountFlow.setTradeValue(tradeValue);
			tAccountFlow.setStatus("01");
			tAccountFlow.setBeforeValue(beforeValue);
			tAccountFlow.setTradeType("-");
			tAccountFlow.setAfterValue(beforeValue - tradeValue);
			tAccountFlow.setBankHostName(bankHostName);
			tAccountFlow.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
			tAccountFlow.setBankCardId(bankCardId);
			tAccountFlow.setPersonId(userId);
			iTAccountFlowServiceImpl.insert(tAccountFlow);
			// 插入消息通知
			String ct = DateUtil.get_String$yyyyMMddHHmmss(new Date());
			Message message = new Message();
			message.setContent("有新的提现申请，快去处理吧~");
			message.setCreateTime(ct);
			message.setUpdateTime(ct);
			message.setOperatorId(userId);
			message.setType("02");
			message.setStatus("01");
			message.setUserId(userId);
			iIZMessageServiceImpl.insert(message);
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("交易失败！");
		}
		return renderSuccess("交易成功！");
	}

	// 提现记录查询
	@RequestMapping("/getSettleFlow")
	@ResponseBody
	public Object getMerchantSettleFlow(String userId, Long page, Long rows) {
		Result result = new Result();
		page = page == null ? 1 : page;
		rows = rows == null ? 10 : rows;
		long start = (page - 1) * rows;
		try {
			List<Map<String, Object>> settleFlowList = iShanJiaFuWuServiceImpl.getMerchantSettleFlow(userId, start, rows);
			long settleFlowCount = iShanJiaFuWuServiceImpl.getMerchantSettleFlowCount(userId);
			result.setSuccess(true);
			result.setObj(settleFlowList);
			result.setTotalCount(settleFlowCount);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("服务器内部错误");
			result.setObj(null);
		}
		return result;
	}
}
