package com.gewei.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gewei.model.TAccountFlow;

/**
 * <p>
  * 服务商、会员结算流水表 Mapper 接口
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-04-16
 */
public interface TAccountFlowMapper extends BaseMapper<TAccountFlow> {

	List<Map<String, Object>> selectPage(Pagination page, Map<String, Object> params);
}