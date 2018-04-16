package com.gewei.fuwushang.service;

import com.gewei.model.Policyholder;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 投保人表 服务类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-01-26
 */
public interface IPolicyholderService extends IService<Policyholder> {
	Policyholder selectPolicyholder(Policyholder policyholder);
}
