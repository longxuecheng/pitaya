package org.lxc.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Payment implements Serializable {
    private Long id;

    private Long saleOrderId;

    private String saleOrderNo;

    private BigDecimal amount;

    private String status;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    private String method;

    private String description;

    private Long transactionId;

    private String merchantOrderNo;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(Long saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public String getSaleOrderNo() {
        return saleOrderNo;
    }

    public void setSaleOrderNo(String saleOrderNo) {
        this.saleOrderNo = saleOrderNo == null ? null : saleOrderNo.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo == null ? null : merchantOrderNo.trim();
    }
    
    public static enum PayMethod {
    	
    	Wechat("wechat","微信支付"),
    	Offline("offline","线下支付");
    	
    	private String code;
    	
    	private String desc;
    	
    	private PayMethod(String code,String desc) {
    		this.code = code;
    		this.desc = desc;
    	}
    	
    	public String code() {
    		return this.code;
    	}
    	
    	public String desc() {
    		return this.desc;
    	} 
    }
    
    public static enum PayStatus {
    	
    	Paying("paying","正在支付"),
    	Success("success","支付成功"),
    	Failed("failed","支付失败");
    	
    	private String code;
    	
    	private String desc;
    	
    	private PayStatus(String code,String desc) {
    		this.code = code;
    		this.desc = desc;
    	}
    	
    	public String code() {
    		return this.code;
    	}
    	
    	public String desc() {
    		return this.desc;
    	} 
    }
}