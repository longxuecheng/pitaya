package org.lxc.mall.model.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.lxc.mall.model.Stock;

public class SaleWriteCondition implements Serializable {
	
	  	private Long id;
	  	
	  	private Long supplierId;

	    private String orderNo;

	    private Long userId;

	    private String status;

	    private String receiver;

	    private String address;

	    private String phoneNo;
	    
	    private BigDecimal orderAmt;

	    private BigDecimal goodsAmt;

	    private BigDecimal expressFee;

	    private String expressMethod;

	    private String expressOrderNo;
	    
	    private ArrayList<SaleDetailWriteCondition> details;
	    
	    public List<Long> getStockIds() {
	    	List<Long> stockIds = new ArrayList<>();
	    	for (SaleDetailWriteCondition s : this.details) {
	    		stockIds.add(s.getStockId());
	    	}
	    	return stockIds;
	    }
	    
		public ArrayList<SaleDetailWriteCondition> getDetails() {
			return details;
		}

		public void setDetails(ArrayList<SaleDetailWriteCondition> details) {
			this.details = details;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getSupplierId() {
			return supplierId;
		}

		public void setSupplierId(Long supplierId) {
			this.supplierId = supplierId;
		}

		public String getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getReceiver() {
			return receiver;
		}

		public void setReceiver(String receiver) {
			this.receiver = receiver;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPhoneNo() {
			return phoneNo;
		}

		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}

		public BigDecimal getOrderAmt() {
			return orderAmt;
		}

		public void setOrderAmt(BigDecimal orderAmt) {
			this.orderAmt = orderAmt;
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

	    
}
