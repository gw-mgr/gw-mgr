package com.gewei.model.vo;

import java.util.List;

import com.gewei.commons.utils.JsonUtils;
import com.gewei.model.OrderSxBxsx;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-30
 */
public class ParameterVo {

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

	private List<OrderSxBxsx> tbsxBxsx;
	

	public List<OrderSxBxsx> getTbsxBxsx() {
		return tbsxBxsx;
	}

	public void setTbsxBxsx(List<OrderSxBxsx> tbsxBxsx) {
		this.tbsxBxsx = tbsxBxsx;
	}

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


	public ParameterVo() {
		super();
	}


	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
