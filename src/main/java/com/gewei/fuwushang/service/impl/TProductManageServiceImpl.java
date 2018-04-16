package com.gewei.fuwushang.service.impl;

import com.gewei.model.TProductManage;
import com.gewei.commons.result.PageInfo;
import com.gewei.mapper.TProductManageMapper;
import com.gewei.fuwushang.service.ITProductManageService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品基本信息表 服务实现类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-05
 */
@Service
public class TProductManageServiceImpl extends ServiceImpl<TProductManageMapper, TProductManage> implements ITProductManageService {
	@Autowired
	private TProductManageMapper mapper;
	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		page.setOrderByField(pageInfo.getSort());
		page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
		List<Map<String, Object>> list = mapper.selectPage(page, pageInfo.getCondition());
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal()); 
	}
	@Override
	public TProductManage selectProductByProductId(String id) {
		return mapper.selectProductByProductId(id);
	}
	@Override
	public boolean updateByProductId(TProductManage tProductManage) {
		return mapper.updateByProductId(tProductManage);
	}
	
}
