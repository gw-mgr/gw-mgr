package com.gewei.fuwushang.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.gewei.commons.result.PageInfo;
import com.gewei.model.User;
import com.gewei.model.vo.UserVo;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends IService<User> {

    List<User> selectByLoginName(UserVo userVo);

    void insertByVo(UserVo userVo);

    UserVo selectVoById(String id);
    
    UserVo selectUserVoByLoginName(String loginName);

    void updateByVo(UserVo userVo);

    void updatePwdByUserId(String userId, String md5Hex);

    void selectDataGrid(PageInfo pageInfo);

    void deleteUserById(String id);
}