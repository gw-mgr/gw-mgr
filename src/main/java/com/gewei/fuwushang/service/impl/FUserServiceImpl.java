package com.gewei.fuwushang.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.commons.result.PageInfo;
import com.gewei.commons.utils.BeanUtils;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.StringUtils;
import com.gewei.fuwushang.service.IUserService;
import com.gewei.mapper.UserMapper;
import com.gewei.mapper.UserRoleMapper;
import com.gewei.model.User;
import com.gewei.model.UserRole;
import com.gewei.model.vo.UserVo;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class FUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public List<User> selectByLoginName(UserVo userVo) {
		User user = new User();
		user.setLoginName(userVo.getLoginName());
		EntityWrapper<User> wrapper = new EntityWrapper<User>(user);
		if (null != userVo.getId()) {
			wrapper.where("id != {0}", userVo.getId());
		}
		return this.selectList(wrapper);
	}

	@Override
	public void insertByVo(UserVo userVo) {
		User user = BeanUtils.copy(userVo, User.class);
		user.setCreateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
		this.insert(user);
		String id = user.getId();
		String[] roles = userVo.getRoleIds().split(",");
		UserRole userRole = new UserRole();
		for (String string : roles) {
			userRole.setUserId(id);
			userRole.setRoleId(Long.valueOf(string));
			userRoleMapper.insert(userRole);
		}
	}

	@Override
	public UserVo selectVoById(String id) {
		return userMapper.selectUserVoById(id);
	}

	@Override
	public void updateByVo(UserVo userVo) {
		User user = BeanUtils.copy(userVo, User.class);
		if (StringUtils.isBlank(user.getPassword())) {
			user.setPassword(null);
		}
		this.updateById(user);
		String id = userVo.getId();
		List<UserRole> userRoles = userRoleMapper.selectByUserId(id);
		if (userRoles != null && !userRoles.isEmpty()) {
			for (UserRole userRole : userRoles) {
				userRoleMapper.deleteById(userRole.getId());
			}
		}
		String[] roles = userVo.getRoleIds().split(",");
		UserRole userRole = new UserRole();
		for (String string : roles) {
			userRole.setUserId(id);
			userRole.setRoleId(Long.valueOf(string));
			userRoleMapper.insert(userRole);
		}
	}

	@Override
	public void updatePwdByUserId(String userId, String md5Hex) {
		User user = new User();
		user.setId(userId);
		user.setPassword(md5Hex);
		this.updateById(user);
	}

	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		page.setOrderByField(pageInfo.getSort());
		page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
		List<Map<String, Object>> list = userMapper.selectUserPage(page, pageInfo.getCondition());
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public void deleteUserById(String id) {
		this.deleteById(id);
		userRoleMapper.deleteByUserId(id);
	}

	@Override
	public UserVo selectUserVoByLoginName(String loginName) {
		return userMapper.selectUserVoByLoginName(loginName);
	}
}