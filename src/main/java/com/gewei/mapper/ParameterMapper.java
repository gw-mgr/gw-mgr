package com.gewei.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gewei.model.Parameter;

/**
 * <p>
  * 资源表 Mapper 接口
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-30
 */
public interface ParameterMapper extends BaseMapper<Parameter> {
	List<Map<String, Object>> selectPage(Pagination page, Map<String, Object> params);
}