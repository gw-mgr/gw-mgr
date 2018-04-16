package com.gewei.mapper;

import com.gewei.model.Operator;
import com.gewei.model.vo.FOperatorVo;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * <p>
  * 操作员表 Mapper 接口
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-01-25
 */
public interface FOperatorMapper extends BaseMapper<Operator> {

	List<Map<String, Object>> selectPage(Pagination page, Map<String, Object> params);
	FOperatorVo selectByOpId(String id);
}