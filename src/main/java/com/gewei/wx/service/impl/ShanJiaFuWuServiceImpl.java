package com.gewei.wx.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.fuwushang.service.IBeneficialService;
import com.gewei.fuwushang.service.IParameterService;
import com.gewei.mapper.BaoXianDingDanMapper;
import com.gewei.mapper.DingDanZhuiZongMapper;
import com.gewei.mapper.ShanJiaFuWuMapper;
import com.gewei.model.Beneficial;
import com.gewei.model.OrderInfo;
import com.gewei.model.OrderSxBxsx;
import com.gewei.model.Parameter;
import com.gewei.model.TAccount;
import com.gewei.model.vo.OrderInfoVo;
import com.gewei.wx.service.IShanJiaFuWuService;
import com.gewei.zhongshu.service.IOrderSxBxsxService;

@Service
public class ShanJiaFuWuServiceImpl extends ServiceImpl<BaoXianDingDanMapper, OrderInfo> implements IShanJiaFuWuService {
	@Autowired
	private ShanJiaFuWuMapper shanJiaFuWuMapper;
	@Autowired
	private DingDanZhuiZongMapper dingDanZhuiZongMapper;
	@Autowired
	private IBeneficialService iBeneficialServiceImpl;
	@Autowired
	private IOrderSxBxsxService iOrderSxBxsxServiceImpl;
	@Autowired
	private IParameterService iParameterServiceImpl;

	@Override
	public List<Map<String, String>> getMerchantOrderList(OrderInfoVo orderInfoVo, long start, long rows) {
		String createTime = orderInfoVo.getCreateTime();
		String orderType = orderInfoVo.getOrderType();
		String status = orderInfoVo.getStatus();
		String businessType = orderInfoVo.getBusinessType();
		HashMap<String, Object> condition = new HashMap<String, Object>();
		condition.put("merchantId", orderInfoVo.getMerchantId());
		condition.put("start", start);
		condition.put("rows", rows);
		List<Map<String, String>> resutlList = null;
		if (!StringUtils.isEmpty(createTime)) {
			condition.put("createTime", createTime);
		}
		if (!StringUtils.isEmpty(status)) {
			condition.put("status", status);
		}
		if (!StringUtils.isEmpty(businessType)) {
			condition.put("businessType", businessType);
		}
		if (!StringUtils.isEmpty(orderType)) {
			condition.put("orderType", orderType);
			if ("ROOTCC".equals(orderType)) {
				resutlList = shanJiaFuWuMapper.getMerchantOrderListOfROOTCC(condition);
				if (resutlList != null) {
					for (Map<String, String> map : resutlList) {
						String s1 = map.get("status");
						int progress = "01".equals(s1) ? 50 : 100;
						map.put("progress", progress + "");
					}
				}
			} else if ("ROOTRS".equals(orderType)) {
				resutlList = shanJiaFuWuMapper.getMerchantOrderListOfROOTRS(condition);
				if (resutlList != null) {
					for (Map<String, String> map : resutlList) {
						String s1 = map.get("status");
						int progress = "01".equals(s1) ? 50 : 100;
						map.put("progress", progress + "");
					}
				}
			} else {
				resutlList = shanJiaFuWuMapper.getMerchantOrderListOfOthers(condition);
				if (resutlList != null) {
					for (Map<String, String> map : resutlList) {
						String orderTypeS = map.get("orderType");
						String statusS = map.get("status");
						map.put("progress", formatProgress(orderTypeS, statusS));
					}
				}
			}
			return resutlList;
		} else {
			throw new RuntimeException("查询参数错误！！！");
		}
	}

	// 订单列表进度转化
	private String formatProgress(String orderType, String status) {
		if (orderType == null || orderType.equals("") || status == null || status.equals("")) {
			throw new RuntimeException("参数有误");
		}
		String progress = "0";
		if (("ROOTDK").equals(orderType)) {
			// 贷款
			if (status.equals("01")) {
				progress = "25";
			} else if (status.equals("02")) {
				progress = "50";
			} else if (status.equals("03")) {
				progress = "75";
			} else if (status.equals("04")) {
				progress = "100";
			}
		} else if (("ROOTQCM").equals(orderType) || ("ROOTQCS").equals(orderType)) {
			// 二手车
			if (status.equals("01")) {
				progress = "25";
			} else if (status.equals("08")) {
				progress = "50";
			} else if (status.equals("09")) {
				progress = "75";
			} else {
				progress = "100";
			}
		} else if (("ROOTLC").equals(orderType)) {
			// 理财业务
			if (status.equals("05")) {
				progress = "33";
			} else if (status.equals("06")) {
				progress = "66";
			} else {
				progress = "100";
			}
		} else if (orderType.contains("ROOTCD")) {
			// 车务代办
			if (status.equals("01")) {
				progress = "33";
			} else if (status.equals("08")) {
				progress = "66";
			} else {
				progress = "100";
			}
		} else if (("ROOTCM").equals(orderType)) {
			// 汽车美容
			if (status.equals("01")) {
				progress = "33";
			} else if (status.equals("08")) {
				progress = "66";
			} else {
				progress = "100";
			}
		}
		return progress;
	}

	@Override
	public Map<String, Object> getMerchantOrderSummaryById(String merchantId) {
		return shanJiaFuWuMapper.getMerchantOrderSummaryById(merchantId);
	}

	@Override
	public Map<String, Object> getCCOrderDetailInfoByOrderId(String orderId) {
		return shanJiaFuWuMapper.getCCOrderDetailInfoByOrderId(orderId);
	}

	@Override
	public Map<String, Object> getRSOrderDetailInfoByOrderId(String orderId) {
		Map<String, Object> rsOrderDetailInfoByOrderId = shanJiaFuWuMapper.getRSOrderDetailInfoByOrderId(orderId);
		// 封装身故受益人
		String BENEFICIARY_ID = (String) rsOrderDetailInfoByOrderId.get("BENEFICIARY_ID");
		System.out.println("BENEFICIARY_ID:" + BENEFICIARY_ID);
		if (BENEFICIARY_ID != null) {
			String[] BENEFICIARY_ID_Array = BENEFICIARY_ID.split(",");
			if (BENEFICIARY_ID_Array.length >= 1) {
				// 按照收益顺序排序
				Set<Beneficial> beneficialArray = new TreeSet<Beneficial>(new Comparator<Beneficial>() {
					@Override
					public int compare(Beneficial o1, Beneficial o2) {
						return o1.getBeneficiaryOrder().compareTo(o2.getBeneficiaryOrder());
					}
				});
				for (int j = 0; j < BENEFICIARY_ID_Array.length; j++) {
					EntityWrapper<Beneficial> entityWrapper = new EntityWrapper<Beneficial>();
					String PERSON_ID = BENEFICIARY_ID_Array[j];
					entityWrapper.eq("PERSON_ID", PERSON_ID);
					entityWrapper.eq("ORDER_ID", orderId);
					Beneficial beneficial = iBeneficialServiceImpl.selectOne(entityWrapper);
					beneficialArray.add(beneficial);
				}
				rsOrderDetailInfoByOrderId.put("syr", beneficialArray);
			}
		}
		// 封装：投保事项-保险事项
		HashMap<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("ORDER_ID", orderId);
		List<OrderSxBxsx> list = iOrderSxBxsxServiceImpl.selectByMap(columnMap);
		rsOrderDetailInfoByOrderId.put("tbsx_bxsx", list);
		// 投资连结保险填写
		List<HashMap<String, Object>> list2 = new ArrayList<HashMap<String, Object>>();
		String TZLJBXTX = (String) rsOrderDetailInfoByOrderId.get("TZLJBXTX");
		if (TZLJBXTX != null) {
			String[] split = TZLJBXTX.split("@");
			if (split != null && split.length >= 1) {
				for (String tz : split) {
					HashMap<String, Object> hashMap = new HashMap<String, Object>(3);
					String[] split2 = tz.split("#");
					hashMap.put("name", split2[0]);
					hashMap.put("rate", split2[1]);
					hashMap.put("zRate", split2[2]);
					list2.add(hashMap);
				}
			}
		}
		rsOrderDetailInfoByOrderId.put("TZLJBXTX", list2);
		// 封装说明栏
		EntityWrapper<Parameter> entityWrapper = new EntityWrapper<Parameter>();
		entityWrapper.eq("RELATION_ID", orderId);
		List<Parameter> selectList = iParameterServiceImpl.selectList(entityWrapper);
		rsOrderDetailInfoByOrderId.put("GZSXJSML_CSML", selectList);
		return rsOrderDetailInfoByOrderId;
	}

	@Override
	public Map<String, Object> getMerchantSettleSummary(String merchantId, String createTime) {
		Map<String, Object> merchantSettleSummary = shanJiaFuWuMapper.getMerchantSettleSummary(merchantId, createTime);
		long totalIncome = Long.parseLong(merchantSettleSummary.get("totalIncome").toString());// 总收入
		long object = Long.parseLong(merchantSettleSummary.get("settled").toString());// 已结算金额
		return null;
	}

	@Override
	public Map<String, Object> getMerchantSettleOrderByOrderType(String merchantId, String createTime) {
		return shanJiaFuWuMapper.getMerchantSettleOrderByOrderType(merchantId, createTime);
	}

	@Override
	public List<Map<String, Object>> getMerchantSettleFlow(String merchantId, long start, long rows) {
		return shanJiaFuWuMapper.getMerchantSettleFlow(merchantId, start, rows);
	}

	@Override
	public long getMerchantSettleFlowCount(String merchantId) {
		return shanJiaFuWuMapper.getMerchantSettleFlowCount(merchantId);
	}

	@Override
	public TAccount getAccountByPersonId(String merchantId) {
		return shanJiaFuWuMapper.getAccountByPersonId(merchantId);
	}

	@Override
	public long getMerchantOrderCount(OrderInfoVo orderInfoVo) {
		String createTime = orderInfoVo.getCreateTime();
		String orderType = orderInfoVo.getOrderType();
		String status = orderInfoVo.getStatus();
		String businessType = orderInfoVo.getBusinessType();
		HashMap<String, String> condition = new HashMap<String, String>();
		condition.put("merchantId", orderInfoVo.getMerchantId());
		if (!StringUtils.isEmpty(createTime)) {
			condition.put("createTime", createTime);
		}
		if (!StringUtils.isEmpty(status)) {
			condition.put("status", status);
		}
		if (!StringUtils.isEmpty(businessType)) {
			condition.put("businessType", businessType);
		}
		if (!StringUtils.isEmpty(orderType)) {
			condition.put("orderType", orderType);
			if ("ROOTCC".equals(orderType)) {
				return shanJiaFuWuMapper.getMerchantOrderCountOfROOTCC(condition);
			} else if ("ROOTRS".equals(orderType)) {
				return shanJiaFuWuMapper.getMerchantOrderCountOfROOTRS(condition);
			} else {
				return shanJiaFuWuMapper.getMerchantOrderCountOfOthers(condition);
			}
		} else {
			throw new RuntimeException("查询参数错误！！！");
		}
	}

	@Override
	public void editCCMerchantOrderInfo(String orderId, String orderNo) {
		dingDanZhuiZongMapper.editCCMerchantOrderInfo(orderId, orderNo);
	}

	@Override
	public void editRSMerchantOrderInfo(String orderId, String orderNo) {
		dingDanZhuiZongMapper.editRSMerchantOrderInfo(orderId, orderNo);
	}
}
