package com.gewei.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gewei.model.Beneficial;

/**
 * <p>
  * 人寿保险-受益人表 Mapper 接口
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-04-02
 */
public interface BeneficialMapper extends BaseMapper<Beneficial> {
	Beneficial selectBeneficial(Beneficial beneficial);
}