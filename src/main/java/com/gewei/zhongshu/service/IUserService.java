package com.gewei.zhongshu.service;

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

	void updateByVo(UserVo userVo);

	void updatePwdByUserId(String userId, String md5Hex);

	void selectDataGrid(PageInfo pageInfo);

	void deleteUserById(String id);

	String getUserIdByLoginName(String loginName);

	List<User> selectByName(String userId, String userName);

	void insertUser(User user);

	void updateUser(User user);

	void addUser(User user);

	void updateStatus(User user);
}