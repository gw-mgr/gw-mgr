package com.gewei.fuwushang.service;

import com.baomidou.mybatisplus.service.IService;
import com.gewei.model.Beneficial;

/**
 * <p>
 * 人寿保险-受益人表 服务类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-04-02
 */
public interface IBeneficialService extends IService<Beneficial> {
	Beneficial selectBeneficial(Beneficial beneficial);
}
