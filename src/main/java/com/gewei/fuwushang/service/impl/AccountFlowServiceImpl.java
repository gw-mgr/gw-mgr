package com.gewei.fuwushang.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.commons.result.PageInfo;
import com.gewei.fuwushang.service.ITAccountFlowService;
import com.gewei.mapper.TAccountFlowMapper;
import com.gewei.model.TAccountFlow;

/**
 * <p>
 * 服务商、会员结算流水表 服务实现类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-04-16
 */
@Service
public class AccountFlowServiceImpl extends ServiceImpl<TAccountFlowMapper, TAccountFlow> implements ITAccountFlowService {
	@Autowired
	private TAccountFlowMapper mapper;
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
