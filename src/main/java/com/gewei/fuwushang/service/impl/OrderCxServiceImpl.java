package com.gewei.fuwushang.service.impl;

import com.gewei.model.OrderCx;
import com.gewei.model.vo.OrderCxVo;
import com.gewei.mapper.OrderCxMapper;
import com.gewei.commons.result.PageInfo;
import com.gewei.fuwushang.service.IOrderCxService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 财产保险订单表 服务实现类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-14
 */
@Service
public class OrderCxServiceImpl extends ServiceImpl<OrderCxMapper, OrderCx> implements IOrderCxService {
	@Autowired
	private OrderCxMapper mapper;

	public void selectDataGrid(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		page.setOrderByField(pageInfo.getSort());
		page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
		List<Map<String, Object>> list = mapper.selectPage(page, pageInfo.getCondition());
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public OrderCxVo selectOrderByOrderId(String id) {
		return mapper.selectOrderByOrderId(id);
	}

	// 中枢系统接口
	@Override
	public List<Map<String,Object>> selectCXList(HashMap<String, Object> condition) {
		return mapper.selectCXList(condition);
	}

	// 中枢系统接口
	@Override
	public int getCXOrderCount(HashMap<String, Object> condition) {
		return mapper.getCXOrderCount(condition);
	}
}
