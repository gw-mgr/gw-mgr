package com.gewei.fuwushang.service;

import com.gewei.commons.result.PageInfo;
import com.gewei.model.Operator;
import com.gewei.model.vo.FOperatorVo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 操作员表 服务类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-01-25
 */
public interface IOperatorService extends IService<Operator> {

	void selectDataGrid(PageInfo pageInfo);
	FOperatorVo selectByOpId(String id);
}
