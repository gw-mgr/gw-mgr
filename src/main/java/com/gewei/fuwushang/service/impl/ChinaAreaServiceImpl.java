package com.gewei.fuwushang.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.fuwushang.service.TChinaAreaService;
import com.gewei.mapper.OrderCxMapper;
import com.gewei.mapper.TChinaAreaMapper;
import com.gewei.model.TChinaArea;

/**
 * <p>
 * 中国区域表 服务实现类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-04-09
 */
@Service
public class ChinaAreaServiceImpl extends ServiceImpl<TChinaAreaMapper, TChinaArea> implements TChinaAreaService {
	@Autowired
	private TChinaAreaMapper mapper;
	@Override
	public List<Map<String, Object>> selectJson() {
		return mapper.selectJson();
	}
	
}
