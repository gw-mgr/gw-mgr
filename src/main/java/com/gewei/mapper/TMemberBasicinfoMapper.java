package com.gewei.mapper;

import com.gewei.model.TMemberBasicinfo;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

public interface TMemberBasicinfoMapper extends BaseMapper<TMemberBasicinfo> {
	List<Map<String, Object>> selectPage(Pagination page, Map<String, Object> params);
}