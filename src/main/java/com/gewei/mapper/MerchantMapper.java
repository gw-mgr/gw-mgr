package com.gewei.mapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gewei.model.TMerchantManage;
public interface MerchantMapper extends BaseMapper<TMerchantManage> {

	List<Map<String, Object>> selectMerchantPage(Page<Map<String, Object>> page, Map<String, Object> condition);
}
