package com.gewei.wx.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.mapper.BaoXianDangAnMapper;
import com.gewei.model.OrderInfo;
import com.gewei.wx.service.IBaoXianDangAnService;

@Service
public class BaoXianDangAnServiceImpl extends ServiceImpl<BaoXianDangAnMapper, OrderInfo> implements IBaoXianDangAnService {
	@Autowired
	private BaoXianDangAnMapper baoXianDangAnMapper;

	@Override
	public List<Map<String, Object>> getSXDangAnList(Map<String, Object> condition) {
		return baoXianDangAnMapper.getSXDangAnList(condition);
	}

	@Override
	public List<Map<String, Object>> getCXDangAnList(Map<String, Object> condition) {
		return baoXianDangAnMapper.getCXDangAnList(condition);
	}

	@Override
	public long getSXDangAnCount(Map<String, Object> condition) {
		return baoXianDangAnMapper.getSXDangAnCount(condition);
	}

	@Override
	public long getCXDangAnCount(Map<String, Object> condition) {
		return baoXianDangAnMapper.getCXDangAnCount(condition);
	}
}
