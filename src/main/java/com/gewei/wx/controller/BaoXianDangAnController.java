package com.gewei.wx.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.Result;
import com.gewei.commons.utils.BeanUtils;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.UUIDUtil;
import com.gewei.fuwushang.service.IBeneficialService;
import com.gewei.fuwushang.service.IOrderCxService;
import com.gewei.fuwushang.service.IOrderSxService;
import com.gewei.fuwushang.service.IParameterService;
import com.gewei.fuwushang.service.IPolicyholderService;
import com.gewei.fuwushang.service.ITCustomerBasicinfoService;
import com.gewei.fuwushang.service.IUserService;
import com.gewei.model.Beneficial;
import com.gewei.model.EditOrderCx;
import com.gewei.model.OrderCx;
import com.gewei.model.OrderSx;
import com.gewei.model.OrderSxBxsx;
import com.gewei.model.Parameter;
import com.gewei.model.Policyholder;
import com.gewei.model.TCustomerBasicinfo;
import com.gewei.model.vo.OrderInfoVo;
import com.gewei.model.vo.OrderSxVo;
import com.gewei.model.vo.UserVo;
import com.gewei.wx.service.IBaoXianDangAnService;
import com.gewei.wx.service.IBaoXianDingDanService;
import com.gewei.wx.service.IShanJiaFuWuService;
import com.gewei.zhongshu.service.IOrderSxBxsxService;

@Controller
@RequestMapping("/baoxiandangan")
public class BaoXianDangAnController extends BaseController {
	@Autowired
	private IBaoXianDangAnService iBaoXianDangAnServiceImpl;
	@Autowired
	private IOrderSxService iIOrderSxServiceImpl;
	@Autowired
	private IOrderCxService iIOrderCxServiceImpl;
	@Autowired
	private ITCustomerBasicinfoService iITCustomerBasicinfoServiceImpl;
	@Autowired
	private IBaoXianDingDanService iBaoXianDingDanServiceImpl;
	@Autowired
	private IUserService iUserServiceImpl;
	@Autowired
	private IPolicyholderService policyholderService;
	@Autowired
	private IBeneficialService beneficialService;
	@Autowired
	private IParameterService iParameterServiceImpl;
	@Autowired
	private IOrderSxBxsxService iOrderSxBxsxServiceImpl;
	@Autowired
	private IShanJiaFuWuService iShanJiaFuWuServiceImpl;

	// 客户查询,只显示车险、寿险投保客户信息
	@RequestMapping("/getCxAndSxCustomerList")
	@ResponseBody
	public Object getCxAndSxCustomerList(String userId, Long page, Long rows, String keyWords, String keyType) {
		System.out.println("【entry-getCxAndSxCustomerList】userId=" + userId);
		Result result = new Result();
		page = page == null ? 1 : page;
		rows = rows == null ? 10 : rows;
		long start = (page - 1) * rows;
		try {
			// 客户编号集合（去重）
			Set<String> cxCustomerIdSet = new TreeSet<String>(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			// 各客户的保费
			HashMap<String, Long> zbfMap = new HashMap<String, Long>();
			HashMap<String, Long> lastTBDateMap = new HashMap<String, Long>();
			HashMap<String, String> carNumMap = new HashMap<String, String>();
			// =======================用户校验=======================
			// =======================车险查询=======================
			EntityWrapper<OrderCx> orderCxWrapper = new EntityWrapper<OrderCx>();
			orderCxWrapper.eq("ORDER_TYPE", "ROOTCCCL");
			orderCxWrapper.eq("SALES_MAN", userId);
			// 所有车险订单
			List<OrderCx> cxOrderList = iIOrderCxServiceImpl.selectList(orderCxWrapper);
			for (OrderCx orderCx : cxOrderList) {
				System.out.println("【车险客户】:policyholderId=" + orderCx.getPolicyholderId());
				// 过滤掉不符合筛选条件的客户
				String policyholderId = orderCx.getPolicyholderId();
				if (keyWords != null && !keyWords.trim().equals("")) {
					Wrapper<Policyholder> wrapper = new EntityWrapper<Policyholder>();
					wrapper.eq("PID", policyholderId);
					wrapper.where("USER_NAME like '%{0}%' or TELPHONE like '%{0}%' or MTELPHONE like '%{0}%' or CAR_NUM like '%{0}%'", keyWords);
					Policyholder one = policyholderService.selectOne(wrapper);
					if (one == null) {
						System.out.println("不符合条件车险客户：" + policyholderId);
						continue;
					}
				}
				String customerId = orderCx.getPolicyholderId();
				cxCustomerIdSet.add(customerId);
				// 总保费
				BigDecimal zbf = orderCx.getZbf();
				if (zbfMap.containsKey(customerId)) {
					long zbf2 = zbfMap.get(customerId) + zbf.longValue();
					zbfMap.put(customerId, zbf2);
				} else {
					zbfMap.put(customerId, zbf.longValue());
				}
				// 最近投保日期
				long createTime = Long.parseLong(orderCx.getCreateTime());
				if (lastTBDateMap.containsKey(customerId) && lastTBDateMap.get(customerId) < createTime) {
					lastTBDateMap.put(customerId, createTime);
				} else {
					lastTBDateMap.put(customerId, createTime);
				}
				// 车牌号
				if (!carNumMap.containsKey(customerId)) {
					carNumMap.put(customerId, orderCx.getCarNum());
				}
			}
			// =======================寿险查询=======================
			EntityWrapper<OrderSx> orderSxWrapper = new EntityWrapper<OrderSx>();
			orderSxWrapper.eq("SALES_MAN", userId);
			// 所有寿险订单
			List<OrderSx> sxOrderList = iIOrderSxServiceImpl.selectList(orderSxWrapper);
			for (OrderSx orderSx : sxOrderList) {
				System.out.println("【寿险客户】:policyholderId=" + orderSx.getPolicyholderId());
				// 过滤掉不符合筛选条件的客户
				String policyholderId = orderSx.getPolicyholderId();
				if (keyWords != null && !keyWords.trim().equals("")) {
					Wrapper<Policyholder> wrapper = new EntityWrapper<Policyholder>();
					wrapper.eq("PID", policyholderId);
					wrapper.where("USER_NAME like '%{0}%' or TELPHONE like '%{0}%' or MTELPHONE like '%{0}%' or CAR_NUM like '%{0}%'", keyWords);
					Policyholder one = policyholderService.selectOne(wrapper);
					if (one == null) {
						System.out.println("不符合条件寿险客户：" + policyholderId);
						continue;
					}
				}
				String customerId = orderSx.getPolicyholderId();
				Wrapper<Policyholder> wrapper = new EntityWrapper<Policyholder>();
				wrapper.eq("PID", customerId);
				Policyholder one = policyholderService.selectOne(wrapper);
				// 总保费
				BigDecimal bfhj = orderSx.getBfhj();
				cxCustomerIdSet.add(customerId);
				if (zbfMap.containsKey(customerId)) {
					long zbf2 = zbfMap.get(customerId) + bfhj.longValue();
					zbfMap.put(customerId, zbf2);
				} else {
					zbfMap.put(customerId, bfhj.longValue());
				}
				// 最近投保日期
				long createTime = Long.parseLong(orderSx.getCreateTime());
				if (lastTBDateMap.containsKey(customerId) && lastTBDateMap.get(customerId) < createTime) {
					lastTBDateMap.put(customerId, createTime);
				} else {
					lastTBDateMap.put(customerId, createTime);
				}
				// 车牌号
				if (!carNumMap.containsKey(customerId)) {
					carNumMap.put(customerId, one.getCarNum());
				}
			}
			System.out.println("【客户列表】:cxCustomerIdSet=" + JSON.toJSON(cxCustomerIdSet));
			// =======================客户查询=======================
			String[] customerIdArray = new String[cxCustomerIdSet.size()];
			Iterator<String> iterator = cxCustomerIdSet.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				String customerId = iterator.next();
				customerIdArray[i] = customerId;
				i++;
			}
			// 返回参数封装
			ArrayList<HashMap<String, Object>> customerList = new ArrayList<HashMap<String, Object>>();
			int forStart = 0;
			int forEnd = 0;
			if (customerIdArray.length <= start) {
				forStart = customerIdArray.length;
				forEnd = customerIdArray.length;
			} else if (start < customerIdArray.length && customerIdArray.length <= start + rows) {
				forStart = (int) start;
				forEnd = customerIdArray.length;
			} else {
				forStart = (int) start;
				forEnd = rows.intValue();
			}
			System.out.println("customerIdArray=" + Arrays.toString(customerIdArray) + ",forStart=" + forStart + ",forEnd=" + forEnd);
			for (int j = forStart; j < forEnd; j++) {
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				String policyholderId = customerIdArray[j];
				Wrapper<Policyholder> wrapper = new EntityWrapper<Policyholder>();
				wrapper.eq("PID", policyholderId);
				Policyholder one = policyholderService.selectOne(wrapper);
				if (one != null) {
					hashMap.put("customerId", one.getPid());
					hashMap.put("customerName", one.getUserName());
					hashMap.put("carNum", carNumMap.get(policyholderId));
					hashMap.put("zbf", zbfMap.get(policyholderId) == null ? 0 : zbfMap.get(policyholderId));
					hashMap.put("lastTBDate", lastTBDateMap.get(policyholderId));
					customerList.add(hashMap);
				}
			}
			// 参数返回
			result.setObj(customerList);
			result.setTotalCount((long) cxCustomerIdSet.size());
			result.setSuccess(true);
			System.out.println("【out-getCxAndSxCustomerList】result=" + JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("内部错误");
		}
		return result;
	}

	// 客户详情
	@RequestMapping("/getCustomerInfo")
	@ResponseBody
	public Object getCustomerInfo(String userId, String customerId) {
		System.out.println("【entry-getCustomerInfo】:customerId=" + customerId);
		Result result = new Result();
		result.setSuccess(true);
		try {
			// =======================用户校验=======================
			// =======================客户查询=======================
			EntityWrapper<TCustomerBasicinfo> entityWrapper = new EntityWrapper<TCustomerBasicinfo>();
			entityWrapper.eq("USER_ID", customerId);
			TCustomerBasicinfo tCustomerBasicinfo = iITCustomerBasicinfoServiceImpl.selectOne(entityWrapper);
			System.out.println("tCustomerBasicinfo=" + JSON.toJSONString(tCustomerBasicinfo));
			if (tCustomerBasicinfo != null) {
				result.setObj(tCustomerBasicinfo);
				return result;
			}
			EntityWrapper<Policyholder> entityWrapper2 = new EntityWrapper<Policyholder>();
			entityWrapper2.eq("PID", customerId);
			Policyholder policyholder = policyholderService.selectOne(entityWrapper2);
			System.out.println("policyholder=" + JSON.toJSONString(policyholder));
			Map<String, Object> convertObjToMap = BeanUtils.ConvertObjToMap(policyholder);
			if (policyholder != null) {
				convertObjToMap.put("customerId", policyholder.getPid());
				convertObjToMap.put("cardId", policyholder.getCertNo());
				convertObjToMap.put("cardType", policyholder.getCertType());
				result.setObj(convertObjToMap);
				return result;
			}
			// 参数返回
			result.setMsg("用户不存在");
			System.out.println("【out-getCustomerInfo】:result=" + JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("内部错误");
		}
		return result;
	}

	// 新增or编辑客户信息
	@RequestMapping("/saveOrUpdateCustomer")
	@ResponseBody
	public Object saveOrUpdateCustomer(TCustomerBasicinfo tCustomerBasicinfoRe, String customerId, String pid) {
		System.out.println("【entry-saveOrUpdateCustomer】:tCustomerBasicinfoRe=" + JSON.toJSONString(tCustomerBasicinfoRe));
		System.out.println("【entry-saveOrUpdateCustomer】:customerId=" + customerId + ",pid=" + pid);
		try {
			// =======================用户校验=======================
			// =======================客户新增or编辑=======================
			Long currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date());
			tCustomerBasicinfoRe.setUserId(customerId);
			if (pid != null) {
				// 编辑-投保客户
				EntityWrapper<Policyholder> entityWrapper2 = new EntityWrapper<Policyholder>();
				entityWrapper2.eq("PID", customerId);
				Policyholder policyholder = policyholderService.selectOne(entityWrapper2);
				System.out.println("【entry-getCxAndSxCustomerList】");
				String certNo = policyholder.getCertNo();
				String certType = policyholder.getCertType();
				BeanUtils.copyNotNullProperties(tCustomerBasicinfoRe, policyholder);
				policyholder.setCertNo(tCustomerBasicinfoRe.getCardId() == null ? certNo : tCustomerBasicinfoRe.getCardId());
				policyholder.setCertType(tCustomerBasicinfoRe.getCardType() == null ? certType : tCustomerBasicinfoRe.getCardType());
				policyholderService.update(policyholder, entityWrapper2);
			} else if (pid == null && customerId == null) {
				// 新增
				TCustomerBasicinfo tCustomerBasicinfo = new TCustomerBasicinfo();
				BeanUtils.copyProperties(tCustomerBasicinfoRe, tCustomerBasicinfo);
				tCustomerBasicinfo.setUserId("KH" + currTime + UUIDUtil.getUUID32().substring(0, 16));
				tCustomerBasicinfo.setStatus("01");
				tCustomerBasicinfo.setCreateTime(currTime.toString());
				tCustomerBasicinfo.setUpdateTime(currTime.toString());
				iITCustomerBasicinfoServiceImpl.insert(tCustomerBasicinfo);
			} else if (pid == null && customerId != null) {
				// 编辑-普通客户
				TCustomerBasicinfo tCustomerBasicinfo = iITCustomerBasicinfoServiceImpl.getCustomerInfoByUserId(customerId);
				BeanUtils.copyProperties(tCustomerBasicinfoRe, tCustomerBasicinfo);
				tCustomerBasicinfo.setCreateTime(currTime.toString());
				tCustomerBasicinfo.setUpdateTime(currTime.toString());
				EntityWrapper<TCustomerBasicinfo> entityWrapper = new EntityWrapper<TCustomerBasicinfo>();
				entityWrapper.eq("USER_ID", customerId);
				iITCustomerBasicinfoServiceImpl.update(tCustomerBasicinfo, entityWrapper);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("修改失败！");
		}
		return renderSuccess("修改成功！");
	}

	// 寿险or产险档案列表
	@RequestMapping("/getBXDangAnList")
	@ResponseBody
	public Object getBXDangAnList(OrderInfoVo orderInfoVo, Long page, Long rows) {
		System.out.println("【entry-getBXDangAnList】:orderInfoVo=" + JSON.toJSONString(orderInfoVo));
		Result result = new Result();
		page = page == null ? 1 : page;
		rows = rows == null ? 10 : rows;
		long start = (page - 1) * rows;
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			String orderType = orderInfoVo.getOrderType();
			String userId = orderInfoVo.getUserId();
			condition.put("SALES_MAN", userId);
			condition.put("start", start);
			condition.put("rows", rows);
			String createTime = orderInfoVo.getCreateTime();
			if (!StringUtils.isEmpty(createTime)) {
				condition.put("createTime", createTime);
			}
			String status = orderInfoVo.getStatus();
			if (!StringUtils.isEmpty(status)) {
				condition.put("status", status);
			}
			List<Map<String, Object>> list;
			long totalCount;
			if ("ROOTRS".equals(orderType)) {
				// 人寿档案
				list = iBaoXianDangAnServiceImpl.getSXDangAnList(condition);
				totalCount = iBaoXianDangAnServiceImpl.getSXDangAnCount(condition);
			} else if (orderType.contains("ROOTCC")) {
				// 财险档案;显示最近2条车辆保险,其他类型的保险只显示1条
				list = iBaoXianDangAnServiceImpl.getCXDangAnList(condition);
				totalCount = iBaoXianDangAnServiceImpl.getCXDangAnCount(condition);
			} else {
				return renderError("订单类型有误");
			}
			// 参数返回
			result.setObj(list);
			result.setTotalCount(totalCount);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("内部错误");
		}
		return result;
	}

	// 订单详细信息：寿险+产险
	@RequestMapping("/getOrderDetailInfoByOrderId")
	@ResponseBody
	public Object getOrderDetailInfoByOrderId(OrderInfoVo orderInfoVo, String userId) {
		System.out.println("【entry-getOrderDetailInfoByOrderId】:orderInfoVo=" + JSON.toJSONString(orderInfoVo));
		Result result = new Result();
		try {
			// =======================用户校验=======================
			Map<String, String> condition = new HashMap<String, String>();
			Map<String, Object> orderInfoMap;
			String orderType = orderInfoVo.getOrderType();
			if (orderType == null)
				return renderError("订单类型不能是空");
			String orderId = orderInfoVo.getOrderId();
			condition.put("orderId", orderId);
			if ("ROOTRS".equals(orderType)) {
				// 人寿
				orderInfoMap = iShanJiaFuWuServiceImpl.getRSOrderDetailInfoByOrderId(orderId);
			} else if (orderType.contains("ROOTCC")) {
				// 财险
				condition.put("orderType", orderType);
				orderInfoMap = iShanJiaFuWuServiceImpl.getRSOrderDetailInfoByOrderId(orderId);
			} else {
				return renderError("订单类型参数有误");
			}
			result.setSuccess(true);
			result.setObj(orderInfoMap == null ? "订单不存在" : orderInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("内部错误");
		}
		return result;
	}

	// 寿险
	@RequestMapping(value = "/addOrEditSXDangAn")
	@ResponseBody
	@Transactional
	public Object addOrEditSXDangAn(HttpServletRequest req) {
		Result result = new Result();
		try {
			// 用户校验
			// 日他mmp的参数获取
			ServletInputStream inputStream = req.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			inputStream.close();
			reader.close();
			String paramStr = sb.toString();
			System.out.println("【addOrEditSXDangAn，接收参数】" + sb.toString());
			OrderSxVo orderSxVo = JSON.parseObject(paramStr, OrderSxVo.class);
			System.out.println("【addOrEditSXDangAn，实体转换】" + orderSxVo.toString());
			System.out.println("【addOrEditSXDangAn，受益人列表】" + orderSxVo.getSyr().toString());
			String orderId = orderSxVo.getOrderId();
			System.out.println("orderId=" + orderSxVo.getSyr().toString());
			String userId = orderSxVo.getUserId();
			if (orderId == null) {// +++++++++++++++新建++++++++++++++++++++
				// 添加寿险订单记录========================================================================================================
				OrderSx orderSx = new OrderSx();
				BeanUtils.copyNotNullProperties(orderSxVo, orderSx);
				orderId = "RSBX" + DateUtil.get_String$yyyyMMddHHmmss(new Date()) + UUIDUtil.getUUID32().substring(0, 10);
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
				changeMoney(orderSx, "100");
				// 投保人========================================================================================================
				Policyholder policyholder = new Policyholder();
				policyholder.setUserName(orderSxVo.getTbr_userName());
				policyholder.setSex(orderSxVo.getTbr_sex());
				policyholder.setMarryFlag(orderSxVo.getTbr_marryFlag());
				policyholder.setBirthDate(orderSxVo.getTbr_birthDate());
				policyholder.setCertType(orderSxVo.getTbr_certType());
				policyholder.setCertNo(orderSxVo.getTbr_certNo());
				policyholder.setNationality(orderSxVo.getTbr_nationality());
				policyholder.setHousehold(orderSxVo.getTbr_houseHold());
				policyholder.setValidityDate(orderSxVo.getTbr_validityDate());
				policyholder.setResidentType(orderSxVo.getTbr_residentType());
				policyholder.setRprovince(orderSxVo.getTbr_pprovince());
				policyholder.setRcity(orderSxVo.getTbr_pcity());
				policyholder.setRdistrict(orderSxVo.getTbr_pdistrict());
				policyholder.setResidentialAddress(orderSxVo.getTbr_residentialAddress());
				policyholder.setTelphone(orderSxVo.getTbr_telphone());
				policyholder.setMtelphone(orderSxVo.getTbr_mtelphone());
				policyholder.setMail(orderSxVo.getTbr_mail());
				policyholder.setWorkUnit(orderSxVo.getTbr_workUnit());
				policyholder.setJobContent(orderSxVo.getTbr_jobContent());
				policyholder.setIndustry(orderSxVo.getTbr_industry());
				policyholder.setOccupation(orderSxVo.getTbr_occupation());
				policyholder.setPid(com.gewei.commons.utils.StringUtils.getUUId());
				policyholder.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
				policyholder.setStatus("0");
				orderSx.setPolicyholderId(policyholder.getPid());
				System.out.println("【添加】投保人：" + JSON.toJSONString(policyholder));
				policyholderService.insert(policyholder);
				// 被保人========================================================================================================
				Policyholder bpolicyholder = new Policyholder();
				bpolicyholder.setUserName(orderSxVo.getBbr_userName());
				bpolicyholder.setSex(orderSxVo.getBbr_sex());
				bpolicyholder.setMarryFlag(orderSxVo.getBbr_marryFlag());
				bpolicyholder.setBirthDate(orderSxVo.getBbr_birthDate());
				bpolicyholder.setCertType(orderSxVo.getBbr_certType());
				bpolicyholder.setCertNo(orderSxVo.getBbr_certNo());
				bpolicyholder.setNationality(orderSxVo.getBbr_nationality());
				bpolicyholder.setHousehold(orderSxVo.getBbr_houseHold());
				bpolicyholder.setValidityDate(orderSxVo.getBbr_validityDate());
				bpolicyholder.setResidentType(orderSxVo.getBbr_residentType());
				bpolicyholder.setRprovince(orderSxVo.getBbr_pprovince());
				bpolicyholder.setRcity(orderSxVo.getBbr_pcity());
				bpolicyholder.setRdistrict(orderSxVo.getBbr_pdistrict());
				bpolicyholder.setResidentialAddress(orderSxVo.getBbr_residentialAddress());
				bpolicyholder.setTelphone(orderSxVo.getBbr_telphone());
				bpolicyholder.setMtelphone(orderSxVo.getBbr_mtelphone());
				bpolicyholder.setMail(orderSxVo.getBbr_mail());
				bpolicyholder.setWorkUnit(orderSxVo.getBbr_workUnit());
				bpolicyholder.setJobContent(orderSxVo.getBbr_jobContent());
				bpolicyholder.setIndustry(orderSxVo.getBbr_industry());
				bpolicyholder.setOccupation(orderSxVo.getBbr_occupation());
				bpolicyholder.setPid(com.gewei.commons.utils.StringUtils.getUUId());
				bpolicyholder.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
				bpolicyholder.setStatus("0");
				System.out.println("【添加】被保人：" + JSON.toJSONString(bpolicyholder));
				policyholderService.insert(bpolicyholder);
				orderSx.setInsuredId(bpolicyholder.getPid());
				// 告知事项及说明栏：C说明========================================================================================================
				String GZSXJSML_CSML = orderSxVo.getGZSXJSML_CSML();
				if (GZSXJSML_CSML != null) {
					String[] split = GZSXJSML_CSML.split("@");
					if (split != null && split.length >= 1) {
						for (String tz : split) {
							Parameter parameter = new Parameter();
							String[] split2 = tz.split("#");
							parameter.setSeq(split2[0]);
							parameter.setName(split2[1]);
							parameter.setDescription(split2[2]);
							parameter.setRelationId(orderId);
							System.out.println("【添加】告知事项及说明栏：C说明" + JSON.toJSONString(parameter));
							iParameterServiceImpl.insert(parameter);
						}
					}
				}
				// 受益人========================================================================================================
				List<Beneficial> list = orderSxVo.getSyr();
				System.out.println("受益人：" + JSON.toJSONString(list));
				StringBuffer beneficiaryId = new StringBuffer("");
				if (list != null && list.size() >= 1) {
					for (Beneficial beneficial : list) {
						beneficial.setPersonId(com.gewei.commons.utils.StringUtils.getUUId());
						beneficial.setOrderId(orderId);
						beneficial.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
						beneficialService.insert(beneficial);
						System.out.println("【添加】受益人：" + JSON.toJSONString(beneficial));
						beneficiaryId.append(beneficial.getPersonId());
						beneficiaryId.append(",");
					}
				}
				String beneficiaryIds = beneficiaryId.length() == 0 ? beneficiaryId.toString() : beneficiaryId.substring(0, beneficiaryId.length() - 1).toString();
				orderSx.setBeneficiaryId(beneficiaryIds);
				iIOrderSxServiceImpl.insert(orderSx);
				return renderSuccess("添加成功！");
			} else {// ++++++++++++++++++++编辑++++++++++++++++++++++
				EntityWrapper<OrderSx> orderSxWrapper = new EntityWrapper<OrderSx>();
				orderSxWrapper.eq("ORDER_ID", orderId);
				orderSxWrapper.eq("SALES_MAN", userId);
				// 编辑订单===============================
				OrderSx orderSx = iIOrderSxServiceImpl.selectOne(orderSxWrapper);
				BeanUtils.copyNotNullProperties(orderSxVo, orderSx);
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
				System.out.println("【编辑】订单号：" + orderId);
				// 编辑投保人===============================
				EntityWrapper<Policyholder> policyholderWrapper = new EntityWrapper<Policyholder>();
				policyholderWrapper.eq("PID", orderSxVo.getTbr_id());
				Policyholder policyholder = policyholderService.selectOne(policyholderWrapper);
				policyholder.setUserName(orderSxVo.getTbr_userName() == null ? policyholder.getUserName() : orderSxVo.getTbr_userName());
				policyholder.setSex(orderSxVo.getTbr_sex() == null ? policyholder.getSex() : orderSxVo.getTbr_sex());
				policyholder.setMarryFlag(orderSxVo.getTbr_marryFlag() == null ? policyholder.getMarryFlag() : orderSxVo.getTbr_marryFlag());
				policyholder.setBirthDate(orderSxVo.getTbr_birthDate() == null ? policyholder.getBirthDate() : orderSxVo.getTbr_birthDate());
				policyholder.setCertType(orderSxVo.getTbr_certType() == null ? policyholder.getCertType() : orderSxVo.getTbr_certType());
				policyholder.setCertNo(orderSxVo.getTbr_certNo() == null ? policyholder.getCertNo() : orderSxVo.getTbr_certNo());
				policyholder.setNationality(orderSxVo.getTbr_nationality() == null ? policyholder.getNationality() : orderSxVo.getTbr_nationality());
				policyholder.setHousehold(orderSxVo.getTbr_houseHold() == null ? policyholder.getHousehold() : orderSxVo.getTbr_houseHold());
				policyholder.setValidityDate(orderSxVo.getTbr_validityDate() == null ? policyholder.getValidityDate() : orderSxVo.getTbr_validityDate());
				policyholder.setResidentType(orderSxVo.getTbr_residentType() == null ? policyholder.getResidentType() : orderSxVo.getTbr_residentType());
				policyholder.setPprovince(orderSxVo.getTbr_pprovince() == null ? policyholder.getRprovince() : orderSxVo.getTbr_pprovince());
				policyholder.setPcity(orderSxVo.getTbr_pcity() == null ? policyholder.getPcity() : orderSxVo.getTbr_pcity());
				policyholder.setPdistrict(orderSxVo.getTbr_pdistrict() == null ? policyholder.getPdistrict() : orderSxVo.getTbr_pdistrict());
				policyholder.setPostalAddress(orderSxVo.getTbr_postalAddress() == null ? policyholder.getPostalAddress() : orderSxVo.getTbr_postalAddress());
				policyholder.setRprovince(orderSxVo.getTbr_rprovince() == null ? policyholder.getRprovince() : orderSxVo.getTbr_rprovince());
				policyholder.setRcity(orderSxVo.getTbr_rcity() == null ? policyholder.getRcity() : orderSxVo.getTbr_rcity());
				policyholder.setRdistrict(orderSxVo.getTbr_rdistrict() == null ? policyholder.getRdistrict() : orderSxVo.getTbr_rdistrict());
				policyholder.setResidentialAddress(orderSxVo.getTbr_residentialAddress() == null ? policyholder.getResidentialAddress() : orderSxVo.getTbr_residentialAddress());
				policyholder.setTelphone(orderSxVo.getTbr_telphone() == null ? policyholder.getTelphone() : orderSxVo.getTbr_telphone());
				policyholder.setMtelphone(orderSxVo.getTbr_mtelphone() == null ? policyholder.getMtelphone() : orderSxVo.getTbr_mtelphone());
				policyholder.setMail(orderSxVo.getTbr_mail() == null ? policyholder.getMail() : orderSxVo.getTbr_mail());
				policyholder.setWorkUnit(orderSxVo.getTbr_workUnit() == null ? policyholder.getWorkUnit() : orderSxVo.getTbr_workUnit());
				policyholder.setJobContent(orderSxVo.getTbr_jobContent() == null ? policyholder.getJobContent() : orderSxVo.getTbr_jobContent());
				policyholder.setIndustry(orderSxVo.getTbr_industry() == null ? policyholder.getIndustry() : orderSxVo.getTbr_industry());
				policyholder.setOccupation(orderSxVo.getTbr_occupation() == null ? policyholder.getOccupation() : orderSxVo.getTbr_occupation());
				System.out.println("【编辑】投保人：" + policyholder.getPid());
				policyholderService.update(policyholder, policyholderWrapper);
				// 编辑被保人===============================
				EntityWrapper<Policyholder> policyholderWrapper2 = new EntityWrapper<Policyholder>();
				policyholderWrapper2.eq("PID", orderSxVo.getBbr_id());
				Policyholder bPolicyholder = policyholderService.selectOne(policyholderWrapper2);
				bPolicyholder.setUserName(orderSxVo.getBbr_userName() == null ? bPolicyholder.getUserName() : orderSxVo.getBbr_userName());
				bPolicyholder.setSex(orderSxVo.getBbr_sex() == null ? bPolicyholder.getSex() : orderSxVo.getBbr_sex());
				bPolicyholder.setMarryFlag(orderSxVo.getBbr_marryFlag() == null ? bPolicyholder.getMarryFlag() : orderSxVo.getBbr_marryFlag());
				bPolicyholder.setBirthDate(orderSxVo.getBbr_birthDate() == null ? bPolicyholder.getBirthDate() : orderSxVo.getBbr_birthDate());
				bPolicyholder.setCertType(orderSxVo.getBbr_certType() == null ? bPolicyholder.getCertType() : orderSxVo.getBbr_certType());
				bPolicyholder.setCertNo(orderSxVo.getBbr_certNo() == null ? bPolicyholder.getCertNo() : orderSxVo.getBbr_certNo());
				bPolicyholder.setNationality(orderSxVo.getBbr_nationality() == null ? bPolicyholder.getNationality() : orderSxVo.getBbr_nationality());
				bPolicyholder.setHousehold(orderSxVo.getBbr_houseHold() == null ? bPolicyholder.getHousehold() : orderSxVo.getBbr_houseHold());
				bPolicyholder.setValidityDate(orderSxVo.getBbr_validityDate() == null ? bPolicyholder.getValidityDate() : orderSxVo.getBbr_validityDate());
				bPolicyholder.setResidentType(orderSxVo.getBbr_residentType() == null ? bPolicyholder.getResidentType() : orderSxVo.getBbr_residentType());
				bPolicyholder.setPprovince(orderSxVo.getBbr_pprovince() == null ? bPolicyholder.getRprovince() : orderSxVo.getBbr_pprovince());
				bPolicyholder.setPcity(orderSxVo.getBbr_pcity() == null ? bPolicyholder.getPcity() : orderSxVo.getBbr_pcity());
				bPolicyholder.setPdistrict(orderSxVo.getBbr_pdistrict() == null ? bPolicyholder.getPdistrict() : orderSxVo.getBbr_pdistrict());
				bPolicyholder.setPostalAddress(orderSxVo.getBbr_postalAddress() == null ? bPolicyholder.getPostalAddress() : orderSxVo.getBbr_postalAddress());
				bPolicyholder.setRprovince(orderSxVo.getBbr_rprovince() == null ? bPolicyholder.getRprovince() : orderSxVo.getBbr_rprovince());
				bPolicyholder.setRcity(orderSxVo.getBbr_rcity() == null ? bPolicyholder.getRcity() : orderSxVo.getBbr_rcity());
				bPolicyholder.setRdistrict(orderSxVo.getBbr_rdistrict() == null ? bPolicyholder.getRdistrict() : orderSxVo.getBbr_rdistrict());
				bPolicyholder.setResidentialAddress(orderSxVo.getBbr_residentialAddress() == null ? bPolicyholder.getResidentialAddress() : orderSxVo.getBbr_residentialAddress());
				bPolicyholder.setTelphone(orderSxVo.getBbr_telphone() == null ? bPolicyholder.getTelphone() : orderSxVo.getBbr_telphone());
				bPolicyholder.setMtelphone(orderSxVo.getBbr_mtelphone() == null ? bPolicyholder.getMtelphone() : orderSxVo.getBbr_mtelphone());
				bPolicyholder.setMail(orderSxVo.getBbr_mail() == null ? bPolicyholder.getMail() : orderSxVo.getBbr_mail());
				bPolicyholder.setWorkUnit(orderSxVo.getBbr_workUnit() == null ? bPolicyholder.getWorkUnit() : orderSxVo.getBbr_workUnit());
				bPolicyholder.setJobContent(orderSxVo.getBbr_jobContent() == null ? bPolicyholder.getJobContent() : orderSxVo.getBbr_jobContent());
				bPolicyholder.setIndustry(orderSxVo.getBbr_industry() == null ? bPolicyholder.getIndustry() : orderSxVo.getBbr_industry());
				bPolicyholder.setOccupation(orderSxVo.getBbr_occupation() == null ? bPolicyholder.getOccupation() : orderSxVo.getBbr_occupation());
				System.out.println("【编辑】被保人：" + bPolicyholder.getPid());
				policyholderService.update(bPolicyholder, policyholderWrapper2);
				// 编辑受益人===============================
				List<Beneficial> list2 = orderSxVo.getSyr();
				StringBuffer beneficiaryId = new StringBuffer("");
				if (list2 != null && list2.size() >= 1) {
					for (Beneficial beneficial : list2) {
						System.out.println("【编辑】受益人：" + beneficial.getPersonId());
						EntityWrapper<Beneficial> beneficialWrapper = new EntityWrapper<Beneficial>();
						beneficialWrapper.eq("PERSON_ID", beneficial.getPersonId());
						Beneficial beneficial2 = beneficialService.selectOne(beneficialWrapper);
						if (beneficial2 == null) {
							beneficial2 = new Beneficial();
							beneficial2.setPersonId(com.gewei.commons.utils.StringUtils.getUUId());
							beneficial2.setOrderId(orderId);
							beneficial2.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
							BeanUtils.copyNotNullProperties(beneficial, beneficial2);
							beneficiaryId.append(beneficial2.getPersonId());
							beneficiaryId.append(",");
							beneficialService.insert(beneficial2);
							continue;
						}
						BeanUtils.copyNotNullProperties(beneficial, beneficial2);
						beneficiaryId.append(beneficial2.getPersonId());
						beneficiaryId.append(",");
						beneficial2.setOrderId(orderId);
						beneficial2.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
						beneficialService.update(beneficial2, beneficialWrapper);
					}
				}
				String beneficiaryIds = beneficiaryId.length() == 0 ? beneficiaryId.toString() : beneficiaryId.substring(0, beneficiaryId.length() - 1).toString();
				orderSx.setBeneficiaryId(beneficiaryIds);

				// 告知事项及说明栏：C说明========================================================================================================
				String GZSXJSML_CSML = orderSxVo.getGZSXJSML_CSML();
				if (GZSXJSML_CSML != null) {
					String[] split = GZSXJSML_CSML.split("@");
					if (split != null && split.length >= 1) {
						for (String tz : split) {
							EntityWrapper<Parameter> parameterWrapper = new EntityWrapper<Parameter>();
							parameterWrapper.eq("RELATION_ID", orderId);
							Parameter parameter = iParameterServiceImpl.selectOne(parameterWrapper);
							String[] split2 = tz.split("#");
							if (parameter == null) {
								parameter = new Parameter();
								parameter.setRelationId(orderId);
								parameter.setSeq(split2[0]);
								parameter.setName(split2[1]);
								parameter.setDescription(split2[2]);
								iParameterServiceImpl.insert(parameter);
								continue;
							}
							parameter.setRelationId(orderId);
							parameter.setSeq(split2[0]);
							parameter.setName(split2[1]);
							parameter.setDescription(split2[2]);
							iParameterServiceImpl.update(parameter, parameterWrapper);
						}
					}
				}
				// 封装：投保事项-保险事项========================================================================================================
				List<OrderSxBxsx> list3 = orderSxVo.getTbsx_bxsx();
				if (list3 != null && list3.size() >= 1) {
					for (OrderSxBxsx orderSxBxsx : list3) {
						System.out.println("【编辑】保险事项：" + orderSxBxsx.getId());
						EntityWrapper<OrderSxBxsx> orderSxBxsxWrapper = new EntityWrapper<OrderSxBxsx>();
						orderSxBxsxWrapper.eq("ORDER_ID", orderId);
						orderSxBxsxWrapper.eq("ID", orderSxBxsx.getId());
						OrderSxBxsx orderSxBxsx2 = iOrderSxBxsxServiceImpl.selectOne(orderSxBxsxWrapper);
						if (orderSxBxsx2 == null) {
							orderSxBxsx2 = new OrderSxBxsx();
							orderSxBxsx2.setOrderId(orderId);
							BeanUtils.copyNotNullProperties(orderSxBxsx, orderSxBxsx2);
							iOrderSxBxsxServiceImpl.insert(orderSxBxsx2);
							continue;
						}
						BeanUtils.copyNotNullProperties(orderSxBxsx, orderSxBxsx2);
						iOrderSxBxsxServiceImpl.update(orderSxBxsx2, orderSxBxsxWrapper);
					}
				}
				iIOrderSxServiceImpl.update(orderSx, orderSxWrapper);
				result.setSuccess(true);
				result.setMsg("编辑成功");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("编辑失败");
			return result;
		}
	}

	// 财险订单信息编辑
	@RequestMapping("/editCXOrderCarInfoByOrderId")
	@ResponseBody
	public Object editCXOrderCarInfoByOrderId(EditOrderCx editOrderCx) {
		try {
			// 用户校验
			String userId = editOrderCx.getUserId();
			// 订单内容编辑
			iBaoXianDingDanServiceImpl.editCXOrderCarInfoByOrderId(editOrderCx);
			return renderSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("参数有误");
		}
	}

	public static OrderSx changeMoney(OrderSx orderSx, String charge) {
		if (orderSx.getBxgszxsqyjbl() != null)
			orderSx.setBxgszxsqyjbl(orderSx.getBxgszxsqyjbl().multiply(new BigDecimal(charge)));
		if (orderSx.getBxgszxsqyj() != null)
			orderSx.setBxgszxsqyj(orderSx.getBxgszxsqyj().multiply(new BigDecimal(charge)));
		if (orderSx.getGstcje() != null)
			orderSx.setGstcje(orderSx.getGstcje().multiply(new BigDecimal(charge)));
		if (orderSx.getGstcbl() != null)
			orderSx.setGstcbl(orderSx.getGstcbl().multiply(new BigDecimal(charge)));
		if (orderSx.getGsjsje() != null)
			orderSx.setGsjsje(orderSx.getGsjsje().multiply(new BigDecimal(charge)));
		if (orderSx.getGsjsbl() != null)
			orderSx.setGsjsbl(orderSx.getGsjsbl().multiply(new BigDecimal(charge)));
		if (orderSx.getGsglfje() != null)
			orderSx.setGsglfje(orderSx.getGsglfje().multiply(new BigDecimal(charge)));
		if (orderSx.getGsglfbl() != null)
			orderSx.setGsglfbl(orderSx.getGsglfbl().multiply(new BigDecimal(charge)));
		if (orderSx.getBxgskpyj() != null)
			orderSx.setBxgskpyj(orderSx.getBxgskpyj().multiply(new BigDecimal(charge)));
		if (orderSx.getBxgskpyjbl() != null)
			orderSx.setBxgskpyjbl(orderSx.getBxgskpyjbl().multiply(new BigDecimal(charge)));
		if (orderSx.getBxgsfx1sqyjbl() != null)
			orderSx.setBxgsfx1sqyjbl(orderSx.getBxgsfx1sqyjbl().multiply(new BigDecimal(charge)));
		if (orderSx.getBxgsfx1sqyj() != null)
			orderSx.setBxgsfx1sqyj(orderSx.getBxgsfx1sqyj().multiply(new BigDecimal(charge)));
		if (orderSx.getBxgsfx2sqyjbl() != null)
			orderSx.setBxgsfx2sqyjbl(orderSx.getBxgsfx2sqyjbl().multiply(new BigDecimal(charge)));
		if (orderSx.getBxgsfx2sqyj() != null)
			orderSx.setBxgsfx2sqyj(orderSx.getBxgsfx2sqyj().multiply(new BigDecimal(charge)));
		if (orderSx.getBfhj() != null)
			orderSx.setBfhj(orderSx.getBfhj().multiply(new BigDecimal(charge)));
		if (orderSx.getTbrnsr() != null)
			orderSx.setTbrnsr(orderSx.getTbrnsr().multiply(new BigDecimal(charge)));
		if (orderSx.getBbrnsr() != null)
			orderSx.setBbrnsr(orderSx.getBbrnsr().multiply(new BigDecimal(charge)));
		if (orderSx.getJzx() != null)
			orderSx.setJzx(orderSx.getJzx().multiply(new BigDecimal(charge)));
		if (orderSx.getZxs() != null)
			orderSx.setZxs(orderSx.getZxs().multiply(new BigDecimal(charge)));
		if (orderSx.getJfx1() != null)
			orderSx.setJfx1(orderSx.getJfx1().multiply(new BigDecimal(charge)));
		if (orderSx.getFxs1() != null)
			orderSx.setFxs1(orderSx.getFxs1().multiply(new BigDecimal(charge)));
		if (orderSx.getJfx2() != null)
			orderSx.setJfx2(orderSx.getJfx2().multiply(new BigDecimal(charge)));
		if (orderSx.getFxs2() != null)
			orderSx.setFxs2(orderSx.getFxs2().multiply(new BigDecimal(charge)));
		if (orderSx.getSalesManCommission() != null)
			orderSx.setSalesManCommission(orderSx.getSalesManCommission().multiply(new BigDecimal(charge)));
		if (orderSx.getZxyjbl() != null)
			orderSx.setZxyjbl(orderSx.getZxyjbl().multiply(new BigDecimal(charge)));
		if (orderSx.getZxyjje() != null)
			orderSx.setZxyjje(orderSx.getZxyjje().multiply(new BigDecimal(charge)));
		if (orderSx.getFxyjbl1() != null)
			orderSx.setFxyjbl1(orderSx.getFxyjbl1().multiply(new BigDecimal(charge)));
		if (orderSx.getFxyjje1() != null)
			orderSx.setFxyjje1(orderSx.getFxyjje1().multiply(new BigDecimal(charge)));
		if (orderSx.getFxyjbl2() != null)
			orderSx.setFxyjbl2(orderSx.getFxyjbl2().multiply(new BigDecimal(charge)));
		if (orderSx.getFxyjje2() != null)
			orderSx.setFxyjje2(orderSx.getFxyjje2().multiply(new BigDecimal(charge)));
		if (orderSx.getZhjrjbl() != null)
			orderSx.setZhjrjbl(orderSx.getZhjrjbl().multiply(new BigDecimal(charge)));
		if (orderSx.getZhjrjje() != null)
			orderSx.setZhjrjje(orderSx.getZhjrjje().multiply(new BigDecimal(charge)));
		if (orderSx.getBxgsdqjl() != null)
			orderSx.setBxgsdqjl(orderSx.getBxgsdqjl().multiply(new BigDecimal(charge)));
		if (orderSx.getRecordertc() != null)
			orderSx.setRecordertc(orderSx.getRecordertc().multiply(new BigDecimal(charge)));
		if (orderSx.getBgsml() != null)
			orderSx.setBgsml(orderSx.getBgsml().multiply(new BigDecimal(charge)));
		return orderSx;
	}
}
