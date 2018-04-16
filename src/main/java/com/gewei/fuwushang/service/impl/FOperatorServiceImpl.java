package com.gewei.fuwushang.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.commons.result.PageInfo;
import com.gewei.fuwushang.service.IOperatorService;
import com.gewei.mapper.FOperatorMapper;
import com.gewei.model.Operator;
import com.gewei.model.OperatorRes;
import com.gewei.model.vo.FOperatorVo;

/**
 * <p>
 * 操作员表 服务实现类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-01-25
 */
@Service
public class FOperatorServiceImpl extends ServiceImpl<FOperatorMapper, Operator> implements IOperatorService {
	@Autowired
    private FOperatorMapper mapper;
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
	public FOperatorVo selectByOpId(String id) {
		return mapper.selectByOpId(id);
	}
}
