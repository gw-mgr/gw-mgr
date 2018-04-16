package com.gewei.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.gewei.commons.utils.JsonUtils;

/**
 *
 * 用户
 *
 */
@TableName("user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 主键id */
	@TableId
	private String id;
	/** 登陆名 */
	private String loginName;
	/** 用户名 */
	private String name;
	/** 密码 */
	private String password;
	/** 密码加密盐 */
	private String salt;
	/** 性别 */
	private Integer sex;
	/** 年龄 */
	private Integer age;
	/** 手机号 */
	private String phone;
	/** 用户类别 */
	private String userType;
	/** 用户状态 */
	private String status;
	/** 所属机构 */
	private String organizationId;
	/** 创建时间 */
	private String createTime;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
