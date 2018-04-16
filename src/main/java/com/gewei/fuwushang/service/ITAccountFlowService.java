package com.gewei.fuwushang.service;

import com.baomidou.mybatisplus.service.IService;
import com.gewei.commons.result.PageInfo;
import com.gewei.model.TAccountFlow;

/**
 * <p>
 * 服务商、会员结算流水表 服务类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-04-16
 */
public interface ITAccountFlowService extends IService<TAccountFlow> {

	void selectDataGrid(PageInfo pageInfo);
	
}
