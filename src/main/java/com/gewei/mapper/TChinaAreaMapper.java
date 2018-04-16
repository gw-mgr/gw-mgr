package com.gewei.mapper;

import com.gewei.model.TChinaArea;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 中国区域表 Mapper 接口
 * </p>
 *
 * @author tiger
 * @since 2018-04-04
 */
public interface TChinaAreaMapper extends BaseMapper<TChinaArea> {
	List<Map<String, Object>> selectJson();
}