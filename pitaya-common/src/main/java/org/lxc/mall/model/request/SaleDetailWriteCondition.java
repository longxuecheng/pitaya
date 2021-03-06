package org.lxc.mall.model.request;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaleDetailWriteCondition implements Serializable {
	
	  	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
  	
  	private Long stockId;
  	
  	private BigDecimal quantity = BigDecimal.ZERO;
  	
  	private BigDecimal costUnitPrice = BigDecimal.ZERO;
  	
  	private BigDecimal SaleUnitPrice = BigDecimal.ZERO;
  	
  	private String goodsName;
  	
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getCostUnitPrice() {
		return costUnitPrice;
	}

	public void setCostUnitPrice(BigDecimal costUnitPrice) {
		this.costUnitPrice = costUnitPrice;
	}

	public BigDecimal getSaleUnitPrice() {
		return SaleUnitPrice;
	}

	public void setSaleUnitPrice(BigDecimal saleUnitPrice) {
		SaleUnitPrice = saleUnitPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

}
