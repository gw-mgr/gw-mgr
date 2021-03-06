package com.gewei.fuwushang.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.commons.result.PageInfo;
import com.gewei.fuwushang.service.IParameterService;
import com.gewei.mapper.ParameterMapper;
import com.gewei.model.Parameter;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-30
 */
@Service
public class ParameterServiceImpl extends ServiceImpl<ParameterMapper, Parameter> implements IParameterService {
	@Autowired
	private ParameterMapper mapper;
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
