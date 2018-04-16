package com.gewei.zhongshu.service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.IService;
import com.gewei.commons.result.PageInfo;
import com.gewei.commons.result.ZTree2;
import com.gewei.model.TChinaArea;
import com.gewei.model.TMerchantManage;

/**
 * <p>
 * 服务商基本信息 服务类
 * </p>
 *
 * @author zhixuan.wang
 * @since 2018-01-26
 */
public interface ITMerchantManageService extends IService<TMerchantManage> {
	List<TMerchantManage> selectAll();

	public List<ZTree2> selectTree();

	public List<TMerchantManage> selectTreeGrid();

	List<Map<String, String>> selectMerchantTypeList();

	List<Map<String, String>> selectProvinceList(String pId);

	void selectDataGrid(PageInfo pageInfo);

	Object selectAllTree();

	void updateStatus(String merchantId, String status);

	String getParentAreaIdByAreaId(String grantAreaId);

	List<TChinaArea> getChildAreaIdByAreaId(String grantAreaId);

	List<Long> findGrantIdsByMerchantId(String merchantId);

	long getCountExamineMerchant();

	List<Map<String, String>> getMerchantTypeList();

	TMerchantManage getMerchantInfoById(String merchantId);

	List<Map<String, String>> selectMerchantTypeList(String pid);

	Object selectTree2();
}
