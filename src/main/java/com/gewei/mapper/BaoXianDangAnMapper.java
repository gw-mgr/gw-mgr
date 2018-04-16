package com.gewei.mapper;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gewei.model.OrderInfo;

public interface BaoXianDangAnMapper extends BaseMapper<OrderInfo> {
	List<Map<String, Object>> getCXDangAnList(Map<String, Object> condition);

	List<Map<String, Object>> getSXDangAnList(Map<String, Object> condition);

	long getSXDangAnCount(Map<String, Object> condition);

	long getCXDangAnCount(Map<String, Object> condition);
}