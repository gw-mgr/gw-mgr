package com.gewei.fuwushang.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.commons.result.PageInfo;
import com.gewei.mapper.SalesManMapper;
import com.gewei.model.SalesMan;
import com.gewei.fuwushang.service.ISalesManService;

/**
 *
 * SalesMan 表数据服务层接口实现类
 *
 */
@Service
public class SalesManServiceImpl extends ServiceImpl<SalesManMapper, SalesMan> implements ISalesManService {
    @Autowired
    private SalesManMapper mapper;
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