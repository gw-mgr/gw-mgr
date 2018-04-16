package com.gewei.mapper;

import com.gewei.model.TProductManage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * <p>
  * 产品基本信息表 Mapper 接口
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-05
 */
public interface TProductManageMapper extends BaseMapper<TProductManage> {
	List<Map<String, Object>> selectPage(Pagination page, Map<String, Object> params);
	TProductManage selectProductByProductId(@Param("id") String id);
	boolean updateByProductId(TProductManage tProductManage);
}