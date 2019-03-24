package org.lxc.mall.model.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Stock_DTO implements Serializable {
    private Long id;

    private BigDecimal costUnitPrice;

    private BigDecimal saleUnitPrice;

    private Short discount;

    private String name;

    private BigDecimal totalQuantity;

    private BigDecimal availableQuantity;

    private String specification;

    private BigDecimal shippingFee;

    private Byte isRush;

    private String status;
    
    private TimeGroup_DTO timeGroup;
    
    private Long adminId;

    private String adminName;

    private Long goodsId;
    
    private Integer warehouseId;
    
    public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	private static final long serialVersionUID = 1L;

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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
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

    public TimeGroup_DTO getTimeGroup() {
		return timeGroup;
	}

	public void setTimeGroup(TimeGroup_DTO timeGroup) {
		this.timeGroup = timeGroup;
	}

	public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}