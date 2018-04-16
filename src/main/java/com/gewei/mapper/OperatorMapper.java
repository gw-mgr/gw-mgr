package com.gewei.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gewei.model.Operator;
import com.gewei.model.OperatorRes;
import com.gewei.model.TCustomerBasicinfo;
import com.gewei.model.TMemberBasicinfo;

public interface OperatorMapper extends BaseMapper<Operator> {
	List<Map<String, Object>> operatorDataGrid(Page<Map<String, Object>> page, Map<String, Object> condition);

	List<Map<String, Object>> memberListDataGrid(Page<Map<String, Object>> page, Map<String, Object> condition);

	@Update("UPDATE operator SET UPDATE_TIME = #{updateTime} WHERE OPER_ID = #{operId}")
	void updateStatus(Operator operator);

	@Select("SELECT id as roleId, name as roleName FROM role WHERE name like '%中枢系统%'")
	List<Map<String, String>> roleList();

	@Insert("INSERT INTO user_role (user_id,role_id) VALUE (#{operatorId},#{roleId})")
	void insertRoleOfOperator(@Param("operatorId") String operatorId, @Param("roleId") String roleId);

	void insertOperator(Operator operator);

	OperatorRes getOperatorById(String operatorId);

	void updateOperator(Operator operator);

	@Update("UPDATE user_role SET role_id = #{roleId} WHERE user_id = #{operatorId}")
	void updateRoleOfOperator(@Param("operatorId") String operatorId, @Param("roleId") String roleId);

	TMemberBasicinfo getMemberInfoById(String memberId);

	@Update("UPDATE t_member_basicinfo SET STATUS = #{status}, UPDATE_TIME = #{updateTime} WHERE USER_ID = #{userId}")
	void updateMemberStatus(TMemberBasicinfo tMemberBasicinfo);

	TCustomerBasicinfo getCustomerIdInfoById(String customerId);

	@Update("UPDATE t_customer_basicinfo SET STATUS = #{status}, UPDATE_TIME = #{updateTime} WHERE USER_ID = #{userId}")
	void updateCustomerStatus(TCustomerBasicinfo tCustomerBasicinfo);

	void insertOrUpdate(TCustomerBasicinfo tCustomerBasicinfo);
}