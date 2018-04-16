package com.gewei.model.vo;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

@TableName("yong_jin_zheng_ce")
public class YongJinZhengCeVo extends Model<YongJinZhengCeVo> {

	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	private Integer id;
	/**
	 * 保险公司名称
	 */
	private String insurerName;
	/**
	 * 基础佣金
	 */
	private Float jichuYongj;
	/**
	 * 综合金融奖
	 */
	private Float zongheJr;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 修改时间
	 */
	private String createTime;
	private String province;
	private String city;
	private String country;
	private String district;

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInsurerName() {
		return insurerName;
	}

	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}

	public Float getJichuYongj() {
		return jichuYongj;
	}

	public void setJichuYongj(Float jichuYongj) {
		this.jichuYongj = jichuYongj;
	}

	public Float getZongheJr() {
		return zongheJr;
	}

	public void setZongheJr(Float zongheJr) {
		this.zongheJr = zongheJr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
