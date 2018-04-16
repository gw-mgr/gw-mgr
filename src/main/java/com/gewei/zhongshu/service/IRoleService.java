package com.gewei.zhongshu.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.baomidou.mybatisplus.service.IService;
import com.gewei.commons.result.PageInfo;
import com.gewei.model.Role;

/**
 *
 * Role 表数据服务层接口
 *
 */
public interface IRoleService extends IService<Role> {
	void selectDataGrid(PageInfo pageInfo);

	Object selectTree();

	List<Long> selectResourceIdListByRoleId(Long id);

	void updateRoleResource(Long id, String resourceIds);

	Map<String, Set<String>> selectResourceMapByUserId(String userId);
}