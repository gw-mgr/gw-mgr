package com.gewei.wx.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.mapper.BaoXianYeWuMapper;
import com.gewei.mapper.XianShangYeWuMapper;
import com.gewei.model.OrderInfo;
import com.gewei.model.TMerchantManage;
import com.gewei.wx.service.IXianShangYeWuService;
import com.gewei.zhongshu.service.ITMerchantManageService;

@Service
public class XianShangYeWuServiceImpl extends ServiceImpl<BaoXianYeWuMapper, OrderInfo> implements IXianShangYeWuService {
	@Autowired
	private XianShangYeWuMapper xianShangYeWuMapper;
	@Autowired
	private ITMerchantManageService iTMerchantManageServiceImpl;

	/**
	 * 所有车务代办授权区域查询
	 */
	@Override
	public Object getROOTCDMerchantGrantAreaList() {
		EntityWrapper<TMerchantManage> entityWrapper = new EntityWrapper<TMerchantManage>();
		entityWrapper.eq("STATUS", "02");
		entityWrapper.like("MERCHANT_TYPE", "ROOTCD", SqlLike.DEFAULT);
		List<TMerchantManage> selectList = iTMerchantManageServiceImpl.selectList(entityWrapper);
		HashSet<String> areaSet = new HashSet<String>();
		for (TMerchantManage tMerchantManage : selectList) {
			String grantArea = tMerchantManage.getGrantArea();
			if (grantArea != null) {
				String[] split = grantArea.split(",");
				if (split != null && split.length >= 1) {
					for (String string : split) {
						String[] split2 = string.split("-");
						if (split2 != null && split2.length >= 1) {
							for (int i = 0; i < split2.length; i++) {
								areaSet.add(split2[i]);
							}
						}
					}
				}
			}
		}
		// 区域过滤
		return areaSet;
	}

	@Override
	public List<Map<String, String>> getROOTCDList(Map<String, Object> condition) {
		return xianShangYeWuMapper.getROOTCDList(condition);
	}

	@Override
	public long getROOTCDCount(Map<String, Object> condition) {
		return xianShangYeWuMapper.getROOTCDCount(condition);
	}

	@Override
	public List<Map<String, String>> getROOTCMMerchantList(Integer start, Integer rows) {
		return xianShangYeWuMapper.getROOTCMMerchantList(start, rows);
	}

	@Override
	public long getROOTCMMerchantCount() {
		return xianShangYeWuMapper.getROOTCMMerchantCount();
	}

	@Override
	public List<Map<String, String>> getProductListByMerchantId(Map<String, String> condition) {
		return xianShangYeWuMapper.getProductListByMerchantId(condition);
	}

	@Override
	public Map<String, Object> getProductInfoById(Map<String, Object> condition) {
		Map<String, Object> productInfo = xianShangYeWuMapper.getProductInfoById(condition);
		Double distance = (Double) productInfo.get("distance");
		if (distance > 20000) {
			productInfo.put("distance", "大于20KM");
		}
		return productInfo;
	}

	@Override
	public List<Map<String, String>> getDKProductList(Map<String, Object> condition) {
		return xianShangYeWuMapper.getDKProductList(condition);
	}

	@Override
	public long getDKProductCount(Map<String, Object> condition) {
		return xianShangYeWuMapper.getDKProductCount(condition);
	}

	@Override
	public void createCommonOrder(OrderInfo orderInfo) {
		xianShangYeWuMapper.createCommonOrder(orderInfo);
	}

	@Override
	public void createESCOrder(OrderInfo orderInfo) {
		xianShangYeWuMapper.createESCOrder(orderInfo);
	}
}
