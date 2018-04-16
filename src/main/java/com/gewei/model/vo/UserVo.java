package com.gewei.model.vo;

import java.io.Serializable;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gewei.commons.utils.JsonUtils;
import com.gewei.model.Role;
import com.gewei.model.User;

/**
 * @description：UserVo
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public class UserVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	@NotBlank
	@Length(min = 4, max = 64)
	private String loginName;
	private String name;
	@JsonIgnore
	private String password;
	@JsonIgnore
	private String salt; // 密码加密盐
	private Integer sex;
	private Integer age;
	private String userType;
	private String status;
	private String organizationId;
	private String createTime;
	private String phone;
	private List<Role> rolesList;
	private String organizationName;
	private String roleIds;
	private String createdateStart;
	private String createdateEnd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName == null ? null : loginName.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public List<Role> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<Role> rolesList) {
		this.rolesList = rolesList;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getCreatedateStart() {
		return createdateStart;
	}

	public void setCreatedateStart(String createdateStart) {
		this.createdateStart = createdateStart;
	}

	public String getCreatedateEnd() {
		return createdateEnd;
	}

	public void setCreatedateEnd(String createdateEnd) {
		this.createdateEnd = createdateEnd;
	}

	/**
	 * 比较vo和数据库中的用户是否同一个user，采用id比较
	 * @param user 用户
	 * @return 是否同一个人
	 */
	public boolean equalsUser(User user) {
		if (user == null) {
			return false;
		}
		String userId = user.getId();
		if (id == null || userId == null) {
			return false;
		}
		return id.equals(userId);
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}