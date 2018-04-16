package com.gewei.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gewei.model.TMerchantProductManage;
import com.gewei.model.TProductManage;

/**
 * <p>
  * 服务商的产品管理 Mapper 接口
 * </p>
 *
 * @author zhixuan.wang
 * @since 2018-01-29
 */
public interface TMerchantProductManageMapper extends BaseMapper<TMerchantProductManage> {
	List<Map<String, Object>> selectProductManagePage(Page<Map<String, Object>> page, Map<String, Object> condition);

	@Select("select * from t_recommend_award")
	List<Map<String, Object>> recommendAwardDataGrid();

	@Update("update t_recommend_award set `RATE` = #{rate} where `GENERATION` = #{generation}")
	void recommendAwardEdit(@Param("generation") Integer generation, @Param("rate") Float rate);

	@Select("select * from t_connet_query_fee")
	List<Map<String, Object>> connectQueryFeeDataGrid();

	@Update("update t_connet_query_fee set MONEY = #{money} where ORDER_TYPE = #{orderType}")
	void connectQueryFeeEdit(@Param("orderType") String orderType, @Param("money") Double money);

	@Update("update t_product_manage set STATUS = #{status}, HANDLE_ID = #{handleId}, UPDATE_TIME = #{updateTime} where PRODUCT_ID = #{productId}")
	void updateProductStatus(TProductManage tProductManage);

	@Select("select * from t_product_manage where PRODUCT_ID = #{productId}")
	Map<String, Object> getProductInfoById(String productId);

	List<Map<String, Object>> settleDataGrid(Page<Map<String, Object>> page, Map<String, Object> condition);

	List<Map<String, Object>> cashDataGrid(Page<Map<String, Object>> page, Map<String, Object> condition);

	@Select("select count(*) from t_account_flow where STATUS = '01' AND PERSON_ID IN (SELECT USER_ID FROM t_member_basicinfo)")
	long getExamineCash();

	@Select("select count(*) from t_account_flow where STATUS = '01' AND PERSON_ID IN (SELECT MERCHANT_ID FROM t_merchant_manage)")
	long getExamineSettle();

	void updateAccount(@Param("flowId") String flowId, @Param("updateTime") String updateTime);

	void updateAccountFlow(@Param("flowId") String flowId, @Param("updateTime") String updateTime);
}