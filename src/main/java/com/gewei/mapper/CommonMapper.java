package com.gewei.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gewei.model.SmsCode;
import com.gewei.model.TChinaArea;
import com.gewei.model.TCustomerBasicinfo;

public interface CommonMapper extends BaseMapper<TChinaArea> {
	@Select("select * from t_china_area where PID = #{parentId}")
	@ResultType(TChinaArea.class)
	List<TChinaArea> findAreaNodeByParentId(String parentId);

	TCustomerBasicinfo queryCustomer(HashMap<String, String> condition);

	void insertCustomer(TCustomerBasicinfo tCustomerBasicinfo);

	@Select("select addr,smscode,dead_time deadTime,flag from t_smscode where addr = #{telephone}")
	@ResultType(SmsCode.class)
	SmsCode getSMSCodeByTelephone(String telephone);

	@Insert("REPLACE INTO t_smscode (addr,smscode,create_time,dead_time,flag) values (#{addr},#{smsCode},#{createTime},#{deadTime},#{flag})")
	void saveOrUpdateSmsCode(SmsCode smsCode);

	@Select("select * from t_china_area")
	@ResultType(TChinaArea.class)
	List<TChinaArea> findAreaInfo();

	@Select("select BANK_HOST_NAME bankHostName,BANK_CARD_ID bankCardId from t_account where PERSON_ID = #{personId} limit 1")
	Map<String, Object> getLastBankInfoByPersonId(String personId);
	@Update("update t_smscode set flag = #{flag} where addr = #{addr}")
	void expiredSmscode(SmsCode code);
}
