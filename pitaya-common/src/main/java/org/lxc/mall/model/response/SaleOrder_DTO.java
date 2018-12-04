package org.lxc.mall.model.response;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaleOrder_DTO implements Serializable{
	
	private Long id;
	
	private String orderNo;
	
	private String createTime;
	
	private String status;
	
	private String phoneNo;
	
	private String address;
	
	private String receiver;
	
	private BigDecimal orderAmt;
	
	private User_DTO user;
	
	private TimeGroup_DTO timeGroup;
	
	private BigDecimal goodsAmt;

    private BigDecimal expressFee;

    private String expressMethod;

    private String expressOrderNo;

    private String estimatedArrivalDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = orderAmt;
	}

	public User_DTO getUser() {
		return user;
	}

	public void setUser(User_DTO user) {
		this.user = user;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public TimeGroup_DTO getTimeGroup() {
		return timeGroup;
	}

	public void setTimeGroup(TimeGroup_DTO timeGroup) {
		this.timeGroup = timeGroup;
	}

	public BigDecimal getGoodsAmt() {
		return goodsAmt;
	}

	public void setGoodsAmt(BigDecimal goodsAmt) {
		this.goodsAmt = goodsAmt;
	}

	public BigDecimal getExpressFee() {
		return expressFee;
	}

	public void setExpressFee(BigDecimal expressFee) {
		this.expressFee = expressFee;
	}

	public String getExpressMethod() {
		return expressMethod;
	}

	public void setExpressMethod(String expressMethod) {
		this.expressMethod = expressMethod;
	}

	public String getExpressOrderNo() {
		return expressOrderNo;
	}

	public void setExpressOrderNo(String expressOrderNo) {
		this.expressOrderNo = expressOrderNo;
	}

	public String getEstimatedArrivalDate() {
		return estimatedArrivalDate;
	}

	public void setEstimatedArrivalDate(String estimatedArrivalDate) {
		this.estimatedArrivalDate = estimatedArrivalDate;
	}

}
