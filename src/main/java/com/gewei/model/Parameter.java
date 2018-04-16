package com.gewei.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gewei.commons.utils.JsonUtils;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-30
 */
public class Parameter extends Model<Parameter> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	private Long id;
    /**
     * 关联ID
     */
	private String relationId;
    /**
     * 名称
     */
	private String name;
    /**
     * 排序号
     */
	private String seq;
    /**
     * 简介
     */
	private String description;
    /**
     * 类型
     */
	private String type;
    /**
     * 状态
     */
	private String status;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
