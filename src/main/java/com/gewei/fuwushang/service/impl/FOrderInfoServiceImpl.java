package com.gewei.fuwushang.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.commons.result.PageInfo;
import com.gewei.fuwushang.service.IOrderInfoService;
import com.gewei.mapper.FOrderInfoMapper;
import com.gewei.mapper.OrderInfoMapper;
import com.gewei.model.OrderInfo;

/**
 * <p>
 * 订单表，包括贷款，理财和车 服务实现类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-06
 */
@Service
public class FOrderInfoServiceImpl extends ServiceImpl<FOrderInfoMapper, OrderInfo> implements IOrderInfoService {
	@Autowired
	private FOrderInfoMapper mapper;
	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		page.setOrderByField(pageInfo.getSort());
		page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
		List<Map<String, Object>> list = mapper.selectPage(page, pageInfo.getCondition());
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal()); 
	}
}
