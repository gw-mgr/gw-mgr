package com.gewei.model;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
@TableName("t_merchant_product_manage")
public class TMerchantProductManage extends Model<TMerchantProductManage> {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String productId;
	private String merchantId;
	/**
	 * 数据状态（01上架，02下架，03-强制下架），默认02
	 */
	private String status;
	private String remark1;
	private String remark2;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
