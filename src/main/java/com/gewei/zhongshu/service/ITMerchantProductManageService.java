package com.gewei.zhongshu.service;

import java.util.Map;
import com.baomidou.mybatisplus.service.IService;
import com.gewei.commons.result.PageInfo;
import com.gewei.model.TMerchantProductManage;
import com.gewei.model.TProductManage;

public interface ITMerchantProductManageService extends IService<TMerchantProductManage> {
	void selectDataGrid(PageInfo pageInfo);

	PageInfo recommendAwardDataGrid();

	void recommendAwardEdit(String firstGeneration, String secondGeneration);

	PageInfo connectQueryFeeDataGrid();

	void connectQueryFeeEdit(String rOOTDK, String rOOTQC, String rOOTCD, String rOOTCM);

	void updateProductStatus(TProductManage tProductManage);

	Map<String, Object> getProductInfoById(String productId);

	void settleDataGrid(PageInfo pageInfo);

	void cashDataGrid(PageInfo pageInfo);

	long getExamineCash();

	long getExamineSettle();

	void passSettleOrCash(String flowId, String orderType, String operatorId);
}
