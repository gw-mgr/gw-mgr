package com.gewei.fuwushang.service;

import com.gewei.commons.result.PageInfo;
import com.gewei.model.TProductManage;


import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 产品基本信息表 服务类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-05
 */
public interface ITProductManageService extends IService<TProductManage> {
	void selectDataGrid(PageInfo pageInfo);
	TProductManage selectProductByProductId(String id);
	boolean updateByProductId(TProductManage tProductManage);
}
