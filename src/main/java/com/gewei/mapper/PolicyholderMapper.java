package com.gewei.mapper;

import com.gewei.model.Policyholder;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 投保人表 Mapper 接口
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-01-26
 */
public interface PolicyholderMapper extends BaseMapper<Policyholder> {
	Policyholder selectPolicyholder(Policyholder policyholder);
}