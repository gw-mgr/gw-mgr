package com.gewei.zhongshu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.commons.result.PageInfo;
import com.gewei.commons.utils.DateUtil;
import com.gewei.fuwushang.service.IMessageService;
import com.gewei.mapper.TMerchantProductManageMapper;
import com.gewei.model.Message;
import com.gewei.model.TAccountFlow;
import com.gewei.model.TChinaArea;
import com.gewei.model.TMerchantProductManage;
import com.gewei.model.TProductManage;
import com.gewei.wx.service.ITAccountFlowService;
import com.gewei.zhongshu.service.ITChinaAreaService;
import com.gewei.zhongshu.service.ITMerchantProductManageService;

/**
 * <p>
 * 服务商的产品管理 服务实现类
 * </p>
 *
 * @author zhixuan.wang
 * @since 2018-01-29
 */
@Service
public class TMerchantProductManageServiceImpl extends ServiceImpl<TMerchantProductManageMapper, TMerchantProductManage> implements ITMerchantProductManageService {
	@Autowired
	private TMerchantProductManageMapper tMerchantProductManageMapper;
	@Autowired
	private ITChinaAreaService iTChinaAreaServiceImpl;
	@Autowired
	private IMessageService iMessageServiceImpl;
	@Autowired
	private ITAccountFlowService iTAccountFlowServiceImpl;

	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		page.setOrderByField(pageInfo.getSort());
		page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
		List<Map<String, Object>> list = tMerchantProductManageMapper.selectProductManagePage(page, pageInfo.getCondition());
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public PageInfo recommendAwardDataGrid() {
		PageInfo pageInfo = new PageInfo();
		List<Map<String, Object>> list = tMerchantProductManageMapper.recommendAwardDataGrid();
		ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>(1);
		HashMap<String, Object> hashMap = new HashMap<String, Object>(3);
		hashMap.put("ID", 1);
		hashMap.put("firstGeneration", list.get(0).get("RATE"));
		hashMap.put("secondGeneration", list.get(1).get("RATE"));
		arrayList.add(hashMap);
		pageInfo.setRows(arrayList);
		pageInfo.setTotal(1);
		return pageInfo;
	}

	@Override
	public void recommendAwardEdit(String firstGeneration, String secondGeneration) {
		try {
			tMerchantProductManageMapper.recommendAwardEdit(1, Float.parseFloat(firstGeneration));
			tMerchantProductManageMapper.recommendAwardEdit(2, Float.parseFloat(secondGeneration));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据类型转换异常！！！");
		}
	}

	@Override
	public PageInfo connectQueryFeeDataGrid() {
		PageInfo pageInfo = new PageInfo();
		List<Map<String, Object>> list = tMerchantProductManageMapper.connectQueryFeeDataGrid();
		ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>(1);
		HashMap<String, Object> hashMap = new HashMap<String, Object>(5);
		hashMap.put("ID", 1);
		try {
			hashMap.put("ROOTDK", Double.parseDouble(list.get(0).get("MONEY").toString()) / 100);
			hashMap.put("ROOTQC", Double.parseDouble(list.get(1).get("MONEY").toString()) / 100);
			hashMap.put("ROOTCD", Double.parseDouble(list.get(2).get("MONEY").toString()) / 100);
			hashMap.put("ROOTCM", Double.parseDouble(list.get(3).get("MONEY").toString()) / 100);
			arrayList.add(hashMap);
			pageInfo.setRows(arrayList);
			pageInfo.setTotal(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据类型转换异常！！！");
		}
		return pageInfo;
	}

	@Override
	public void connectQueryFeeEdit(String ROOTDK, String ROOTQC, String ROOTCD, String ROOTCM) {
		try {
			tMerchantProductManageMapper.connectQueryFeeEdit("ROOTDK", Double.parseDouble(ROOTDK) * 100);
			tMerchantProductManageMapper.connectQueryFeeEdit("ROOTQC", Double.parseDouble(ROOTQC) * 100);
			tMerchantProductManageMapper.connectQueryFeeEdit("ROOTCD", Double.parseDouble(ROOTCD) * 100);
			tMerchantProductManageMapper.connectQueryFeeEdit("ROOTCM", Double.parseDouble(ROOTCM) * 100);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据类型转换异常！！！");
		}
	}

	@Override
	public void updateProductStatus(TProductManage tProductManage) {
		tMerchantProductManageMapper.updateProductStatus(tProductManage);
	}

	@Override
	public Map<String, Object> getProductInfoById(String productId) {
		return tMerchantProductManageMapper.getProductInfoById(productId);
	}

	@Override
	public void settleDataGrid(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		page.setOrderByField(pageInfo.getSort());
		page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
		List<Map<String, Object>> list = tMerchantProductManageMapper.settleDataGrid(page, pageInfo.getCondition());
		System.out.println("list===" + JSON.toJSONString(list));
		setID2Name(list);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public void cashDataGrid(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		page.setOrderByField(pageInfo.getSort());
		page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
		List<Map<String, Object>> list = tMerchantProductManageMapper.cashDataGrid(page, pageInfo.getCondition());
		System.out.println("list===" + JSON.toJSONString(list));
		setID2Name(list);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	private void setID2Name(List<Map<String, Object>> list) {
		for (Map<String, Object> map : list) {
			// 1.授权范围设置
			Object merchantTypeObj = map.get("merchantType");
			if (merchantTypeObj != null) {
				String merchantTypeStr = merchantTypeObj.toString();
				String[] merchantTypeArray = merchantTypeStr.split(",");
				String merchantTypes = "";
				for (String merchantType : merchantTypeArray) {
					if ("ROOTLC".equals(merchantType)) {
						merchantTypes += "理财产品,";
					} else if ("ROOTDK".equals(merchantType)) {
						merchantTypes += "贷款业务,";
					} else if ("ROOTQC".equals(merchantType)) {
						merchantTypes += "二手汽车,";
					} else if ("ROOTCD".equals(merchantType)) {
						merchantTypes += "车务代办,";
					} else if ("ROOTCM".equals(merchantType)) {
						merchantTypes += "汽车美容,";
					}
				}
				// 去除最后一个逗号
				merchantTypes = "".equals(merchantTypes) ? null : merchantTypes.substring(0, merchantTypes.length() - 1);
				map.put("merchantTypeName", merchantTypes);
			}
			// 2.授权区域设置
			String grantAreaNameRes = "";
			Object grantAreaObj = map.get("grantArea");
			HashMap<String, Object> columnMap = new HashMap<String, Object>(1);
			if (grantAreaObj != null) {
				String grantAreaStr = grantAreaObj.toString();
				String[] grantAreaArray = grantAreaStr.split(",");
				for (String grantArea : grantAreaArray) {
					String grantNameChildRes = "";
					String[] grantAreaIdArray = grantArea.split("-");
					for (String grantAreaId : grantAreaIdArray) {
						columnMap.put("ID", grantAreaId);
						List<TChinaArea> list2 = iTChinaAreaServiceImpl.selectByMap(columnMap);
						if (list2 != null && list2.size() > 0) {
							String grantName = list2.get(0).getName();
							grantNameChildRes += grantName + "-";
						}
					}
					grantNameChildRes = "".equals(grantNameChildRes) ? "" : grantNameChildRes.substring(0, grantNameChildRes.length() - 1);
					grantAreaNameRes += grantNameChildRes + ",";
				}
			}
			grantAreaNameRes = "".equals(grantAreaNameRes) ? "" : grantAreaNameRes.substring(0, grantAreaNameRes.length() - 1);
			map.put("grantAreaName", grantAreaNameRes);
			// 2.服务商地址
			Object province = map.get("province");
			Object city = map.get("city");
			Object country = map.get("country");
			Object merchantAddr = map.get("merchantAddr");
			StringBuffer address = new StringBuffer("");
			if (province != null) {
				address.append(province.toString());
				address.append("-");
			}
			if (city != null) {
				address.append(city.toString());
				address.append("-");
			}
			if (country != null) {
				address.append(country.toString());
				address.append("-");
			}
			if (merchantAddr != null) {
				address.append(merchantAddr.toString());
			}
			map.put("address", address);
		}
	}

	@Override
	public long getExamineCash() {
		return tMerchantProductManageMapper.getExamineCash();
	}

	@Override
	public long getExamineSettle() {
		return tMerchantProductManageMapper.getExamineSettle();
	}

	@Override
	@Transactional
	public void passSettleOrCash(String flowId, String orderType, String operatorId) {
		try {
			Long currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date());
			// 通过结算
			tMerchantProductManageMapper.updateAccount(flowId, currTime.toString());
			tMerchantProductManageMapper.updateAccountFlow(flowId, currTime.toString());
			// 插入消息记录
			EntityWrapper<TAccountFlow> entityWrapper = new EntityWrapper<TAccountFlow>();
			entityWrapper.eq("FLOW_ID", flowId);
			entityWrapper.eq("ORDER_TYPE", orderType);
			TAccountFlow accountFlow = iTAccountFlowServiceImpl.selectOne(entityWrapper);
			String personId = accountFlow.getPersonId();
			if (orderType.equals("TX")) {
				Message message = new Message();
				message.setUserId(personId);
				message.setStatus("01");
				message.setOperatorId(operatorId);
				message.setContent("您的提现申请已通过审核~");
				message.setType("31");// 服务商申请成功
				message.setCreateTime(currTime.toString());
				message.setUpdateTime(currTime.toString());
				iMessageServiceImpl.insert(message);
			} else if (orderType.equals("JS")) {
				Message message = new Message();
				message.setOperatorId(personId);
				message.setUserId(personId);
				message.setStatus("01");
				message.setContent("您的结算申请已通过审核~");
				message.setType("30");// 服务商申请成功
				message.setOperatorId(operatorId);
				message.setCreateTime(currTime.toString());
				message.setUpdateTime(currTime.toString());
				iMessageServiceImpl.insert(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
