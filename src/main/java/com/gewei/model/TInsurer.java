package com.gewei.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 保险公司列表
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-16
 */
@TableName("t_insurer")
public class TInsurer extends Model<TInsurer> {

    private static final long serialVersionUID = 1L;

    /**
     * 标识表的自增序列，没有业务作用
     */
	private Long id;
    /**
     * 保险公司名称
     */
	private String insurerName;
    /**
     * 保险公司电话
     */
	private String insurerPhone;
    /**
     * 备用字段1
     */
	private String remark1;
    /**
     * 备用字段2
     */
	private String remark2;
    /**
     * 备用字段3
     */
	private String remark3;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInsurerName() {
		return insurerName;
	}

	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}

	public String getInsurerPhone() {
		return insurerPhone;
	}

	public void setInsurerPhone(String insurerPhone) {
		this.insurerPhone = insurerPhone;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
