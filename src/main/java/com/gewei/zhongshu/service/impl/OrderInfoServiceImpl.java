package com.gewei.zhongshu.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.commons.result.PageInfo;
import com.gewei.commons.utils.DateUtil;
import com.gewei.mapper.OrderInfoMapper;
import com.gewei.model.OrderInfo;
import com.gewei.zhongshu.service.IOrderInfoService;

/**
 * <p>
 * 订单表，包括贷款，理财和车 服务实现类
 * </p>
 *
 * @author tiger
 * @since 2018-02-22
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {
	@Autowired
	private OrderInfoMapper orderInfoMapper;

	@Override
	public long getOrderTotalNum() {
		return orderInfoMapper.getOrderTotalNum();
	}

	@Override
	public long getOrderTotalMoney() {
		return orderInfoMapper.getOrderTotalMoney();
	}

	@SuppressWarnings("static-access")
	@Override
	public Map<String, Long> getYesterdayOrderNumOfAllOrderType() {
		try {
			Long currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = sdf.parse(currTime.toString());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(calendar.DATE, -1);
			Long startTime = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(calendar.getTime()) + "000000");
			Long endTime = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(calendar.getTime()) + "235959");
			// 各类型订单，昨日成交数
			return orderInfoMapper.getYesterdayOrderNumOfAllOrderType(startTime, endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long getYesterdayOrderTotalNum() {
		try {
			Long currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = sdf.parse(currTime.toString());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -1);
			Long startTime = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(calendar.getTime()) + "000000");
			Long endTime = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(calendar.getTime()) + "235959");
			return orderInfoMapper.getYesterdayOrderTotalNum(startTime, endTime);
		} catch (Exception e) {
			e.printStackTrace();
			new RuntimeException();
		}
		return 0;
	}

	@Override
	public long getYesterdayOrderTotalMoney() {
		try {
			Long currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = sdf.parse(currTime.toString());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -1);
			Long startTime = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(calendar.getTime()) + "000000");
			Long endTime = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(calendar.getTime()) + "235959");
			return orderInfoMapper.getYesterdayOrderTotalMoney(startTime, endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void getOrderInsDataGroupByProvince(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		//		List<Map<String, String>> provinceCXList = orderInfoMapper.getOrderCXProvinceList();
		List<Map<String, String>> provinceList = new ArrayList<Map<String, String>>();
		//		provinceList.addAll(provinceCXList);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>(1);
		//		for (Map<String, String> provinceMap : provinceList) {
		//			String provinceId = provinceMap.get("provinceId");
		HashMap<String, Object> hashMap = new HashMap<String, Object>(5);
		//			String provinceName = tMerchantManageMapper.getGrantNameByAreaId(provinceId);
		hashMap.put("provinceName", "重庆");
		//			Integer yesterdayTotalNum = orderInfoMapper.getYesterdayTotalNumByProvinceId(provinceId);
		hashMap.put("yesterdayTotalNum", 2);
		//			Integer yesterdayFinishedNum = orderInfoMapper.getYesterdayFinishedNumByProvinceId(provinceId);
		hashMap.put("yesterdayFinishedNum", 3);
		//			Float dealMoney = orderInfoMapper.getdealMoneyByProvinceId(provinceId);
		hashMap.put("dealMoney", 53 / 100);
		//			Float recommendMoney = orderInfoMapper.getrecommendMoneyByProvinceId(provinceId);
		hashMap.put("recommendMoney", 109 / 100);
		resultList.add(hashMap);
		//		}
		pageInfo.setRows(resultList);
		pageInfo.setTotal(1);
	}


	@Override
	public List<Map<String, String>> getOrderTypeList() {
		return orderInfoMapper.getOrderTypeList();
	}
}
