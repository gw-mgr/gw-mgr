package com.gewei.fuwushang.service.impl;

import com.gewei.model.OrderSx;
import com.gewei.model.vo.OrderCxVo;
import com.gewei.model.vo.OrderSxVo;
import com.gewei.mapper.OrderCxMapper;
import com.gewei.mapper.OrderSxMapper;
import com.gewei.commons.result.PageInfo;
import com.gewei.fuwushang.service.IOrderSxService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 人寿保险订单表 服务实现类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-01
 */
@Service
public class OrderSxServiceImpl extends ServiceImpl<OrderSxMapper, OrderSx> implements IOrderSxService {
	@Autowired
	private OrderSxMapper mapper;
	public void selectDataGrid(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		page.setOrderByField(pageInfo.getSort());
		page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
		List<Map<String, Object>> list = mapper.selectPage(page, pageInfo.getCondition());
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal()); 
	}
	@Override
	public OrderSxVo selectOrderByOrderId(String id) {
		return mapper.selectOrderByOrderId(id);
	}
}
