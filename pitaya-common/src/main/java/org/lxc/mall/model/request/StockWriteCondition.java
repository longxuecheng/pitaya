package org.lxc.mall.model.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.lxc.mall.model.Stock;

public class StockWriteCondition implements Serializable {
    private Long id;
    
    private String name;

    private String status;
    
    private List<String> specifications;

    private Long goodsId;
    
    private BigDecimal costUnitPrice;

    private BigDecimal saleUnitPrice;

    private Short discount;

    private BigDecimal totalQuantity;

    private BigDecimal availableQuantity;

    private BigDecimal shippingFee;

    private Byte isRush;
    
    private Integer warehouseId;
    
    public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

    private static final long serialVersionUID = 1L;
    
    public Stock parseModel() {
    	Stock s = new Stock();
    	s.setId(id);
    	s.setGoodsId(goodsId);
    	s.setCostUnitPrice(costUnitPrice);
    	s.setSaleUnitPrice(saleUnitPrice);
    	s.setDiscount(discount);
    	s.setName(name);
    	s.setTotalQuantity(availableQuantity);
    	s.setAvailableQuantity(availableQuantity);
    	s.setSpecification(String.join("_", specifications));
    	s.setShippingFee(shippingFee);
    	s.setIsRush(isRush);
    	s.setStatus(status);
    	s.setWarehouseId(warehouseId);
    	return s;
    }

    
    public List<String> getSpecifications() {
		return specifications;
	}

    public void setSpecifications(List<String> specifications) {
		this.specifications = specifications;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCostUnitPrice() {
        return costUnitPrice;
    }

    public void setCostUnitPrice(BigDecimal costUnitPrice) {
        this.costUnitPrice = costUnitPrice;
    }

    public BigDecimal getSaleUnitPrice() {
        return saleUnitPrice;
    }

    public void setSaleUnitPrice(BigDecimal saleUnitPrice) {
        this.saleUnitPrice = saleUnitPrice;
    }

    public Short getDiscount() {
        return discount;
    }

    public void setDiscount(Short discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(BigDecimal totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(BigDecimal availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Byte getIsRush() {
        return isRush;
    }

    public void setIsRush(Byte isRush) {
        this.isRush = isRush;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}