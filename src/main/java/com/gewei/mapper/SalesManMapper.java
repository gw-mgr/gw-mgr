package com.gewei.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gewei.model.SalesMan;

/**
 *
 * SalesMan 表数据库控制层接口
 *
 */
public interface SalesManMapper extends BaseMapper<SalesMan> {

	List<Map<String, Object>> selectPage(Pagination page, Map<String, Object> params);

}