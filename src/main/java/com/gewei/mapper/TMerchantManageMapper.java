package com.gewei.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gewei.model.TChinaArea;
import com.gewei.model.TMerchantManage;

/**
 * <p>
  * 服务商基本信息 Mapper 接口
 * </p>
 *
 * @author zhixuan.wang
 * @since 2018-01-26
 */
public interface TMerchantManageMapper extends BaseMapper<TMerchantManage> {
	List<Map<String, String>> selectMerchantTypeList();

	@Select("SELECT ID,NAME FROM t_china_area WHERE PID = #{pId} and ID != '0'")
	List<Map<String, String>> selectProvinceList(String pId);

	List<Map<String, Object>> selectMerchantPage(Pagination page, Map<String, Object> params);

	@Select("SELECT ID as id,NAME as name,PID as pid FROM t_china_area")
	List<TChinaArea> selectChinaAreaAll(String pId);

//	@Update("UPDATE t_merchant_manage set STATUS = #{status}, UPDATE_TIME = #{updateTime}, OPERATOR_ID = #{operatorId} where MERCHANT_ID = #{merchantId}")
//	void updateStatus(TMerchantManage tMerchantManage);

	@Select("SELECT PID FROM t_china_area where id = #{grantAreaId}")
	String getParentAreaIdByAreaId(String grantAreaId);

	@Select("SELECT ID as id,NAME as name,PID as pid from t_china_area where PID = #{grantAreaId}")
	List<TChinaArea> getChildAreaIdByAreaId(String grantAreaId);

	TMerchantManage selectByMerchantId(String merchantId);

	@Select("SELECT GRANT_AREA as grantArea FROM t_merchant_manage WHERE MERCHANT_ID = #{merchantId}")
	String findGrantIdsByMerchantId(String merchantId);

	@Select("SELECT count(*) FROM t_merchant_manage WHERE STATUS = '01'")
	Long getCountExamineMerchant();

	List<Map<String, String>> getMerchantTypeList();

	TMerchantManage getMerchantInfoById(String merchantId);

	List<Map<String, String>> getMerchantTypeList(String pid);
}