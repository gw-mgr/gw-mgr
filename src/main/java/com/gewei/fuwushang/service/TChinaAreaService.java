package com.gewei.fuwushang.service;

import com.gewei.model.TChinaArea;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 中国区域表 服务类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-04-09
 */
public interface TChinaAreaService extends IService<TChinaArea> {
	List<Map<String, Object>> selectJson();
}
