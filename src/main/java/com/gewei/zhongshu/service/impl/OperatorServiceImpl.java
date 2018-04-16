package com.gewei.zhongshu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.commons.result.PageInfo;
import com.gewei.mapper.OperatorMapper;
import com.gewei.model.Operator;
import com.gewei.model.OperatorRes;
import com.gewei.model.TCustomerBasicinfo;
import com.gewei.model.TMemberBasicinfo;
import com.gewei.zhongshu.service.IOperatorService;

/**
 * <p>
 * 操作员表 服务实现类
 * </p>
 *
 * @author tiger
 * @since 2018-02-27
 */
@Service
public class OperatorServiceImpl extends ServiceImpl<OperatorMapper, Operator> implements IOperatorService {
	@Autowired
	private OperatorMapper operatorMapper;

	@Override
	public void operatorDataGrid(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		page.setOrderByField(pageInfo.getSort());
		page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
		List<Map<String, Object>> resList = operatorMapper.operatorDataGrid(page, pageInfo.getCondition());
		pageInfo.setRows(resList);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public void updateStatus(Operator operator) {
		operatorMapper.updateStatus(operator);
	}

	@Override
	public List<Map<String, String>> roleList() {
		return operatorMapper.roleList();
	}

	@Override
	public void insertRoleOfOperator(String operatorId, String roleId) {
		operatorMapper.insertRoleOfOperator(operatorId, roleId);
	}

	@Override
	public void insertOperator(Operator operator) {
		operatorMapper.insertOperator(operator);
	}

	@Override
	public OperatorRes getOperatorById(String operatorId) {
		return operatorMapper.getOperatorById(operatorId);
	}

	@Override
	public void updateOperator(Operator operator) {
		operatorMapper.updateOperator(operator);
	}

	@Override
	public void updateRoleOfOperator(String operatorId, String roleId) {
		operatorMapper.updateRoleOfOperator(operatorId, roleId);
	}

	@Override
	public void memberListDataGrid(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		page.setOrderByField(pageInfo.getSort());
		page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
		List<Map<String, Object>> resList = operatorMapper.memberListDataGrid(page, pageInfo.getCondition());
		pageInfo.setRows(resList);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public TMemberBasicinfo getMemberInfoById(String memberId) {
		return operatorMapper.getMemberInfoById(memberId);
	}

	@Override
	public void updateMemberStatus(TMemberBasicinfo tMemberBasicinfo) {
		operatorMapper.updateMemberStatus(tMemberBasicinfo);
	}

	@Override
	public TCustomerBasicinfo getCustomerIdInfoById(String customerId) {
		return operatorMapper.getCustomerIdInfoById(customerId);
	}

	@Override
	@Transactional
	public void updateCustomerStatus(TCustomerBasicinfo tCustomerBasicinfo) {
		operatorMapper.updateCustomerStatus(tCustomerBasicinfo);
	}

	@Override
	public void insertOrUpdate(TCustomerBasicinfo tCustomerBasicinfo) {
		operatorMapper.insertOrUpdate(tCustomerBasicinfo);
	}
}
