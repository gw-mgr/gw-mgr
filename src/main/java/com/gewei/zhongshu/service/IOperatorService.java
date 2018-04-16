package com.gewei.zhongshu.service;
import com.gewei.commons.result.PageInfo;
import com.gewei.model.Operator;
import com.gewei.model.OperatorRes;
import com.gewei.model.TCustomerBasicinfo;
import com.gewei.model.TMemberBasicinfo;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.IService;
public interface IOperatorService extends IService<Operator> {
	void operatorDataGrid(PageInfo pageInfo);
	void updateStatus(Operator operator);
	List<Map<String, String>> roleList();
	void insertRoleOfOperator(String id, String roleId);
	void insertOperator(Operator operator);
	OperatorRes getOperatorById(String operatorId);
	void updateOperator(Operator operator);
	void updateRoleOfOperator(String id, String roleId);
	void memberListDataGrid(PageInfo pageInfo);
	TMemberBasicinfo getMemberInfoById(String memberId);
	void updateMemberStatus(TMemberBasicinfo tMemberBasicinfo);
	TCustomerBasicinfo getCustomerIdInfoById(String customerId);
	void customerDataGrid(PageInfo pageInfo);
	void updateCustomerStatus(TCustomerBasicinfo tCustomerBasicinfo);
	void insertOrUpdate(TCustomerBasicinfo tCustomerBasicinfo);
}
