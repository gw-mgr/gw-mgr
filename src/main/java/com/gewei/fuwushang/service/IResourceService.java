package com.gewei.fuwushang.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.gewei.commons.result.ZTree;
import com.gewei.commons.shiro.ShiroUser;
import com.gewei.model.Resource;

/**
 *
 * Resource 表数据服务层接口
 *
 */
public interface IResourceService extends IService<Resource> {

    List<Resource> selectAll();

    List<ZTree> selectAllMenu();

    List<ZTree> selectAllTree();

    List<ZTree> selectTree(ShiroUser shiroUser);

}