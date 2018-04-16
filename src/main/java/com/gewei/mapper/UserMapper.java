package com.gewei.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gewei.model.User;
import com.gewei.model.vo.UserVo;

/**
 *
 * User 表数据库控制层接口
 *
 */
public interface UserMapper extends BaseMapper<User> {
	UserVo selectUserVoById(@Param("id") String id);

	UserVo selectUserVoByLoginName(@Param("loginName") String loginName);

	List<Map<String, Object>> selectUserPage(Pagination page, Map<String, Object> params);

	@Select("select id operateId from user where login_name = #{loginName}")
	String getUserIdByLoginName(String loginName);

	void updateUser(User user);

	void addUser(User user);

	@Update("UPDATE user SET status = #{status} WHERE id = #{id}")
	void updateStatus(User user);
}