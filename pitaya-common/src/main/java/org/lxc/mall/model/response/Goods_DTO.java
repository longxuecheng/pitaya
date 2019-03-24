package org.lxc.mall.model.response;

import java.io.Serializable;
import java.util.List;

import org.lxc.mall.model.GoodsSpecification;

public class Goods_DTO implements Serializable {
	private Long id;

    private String name;

    private String status;
    
    private Integer category;

    private String producingArea;

    private String description;

    private String createTime;

    private String updateTime;

    private Long supplierId;
    
    private String supplierName;
    
    private String listPicUrl;
    
    private List<GoodsSpecification> specifications;
    
	public List<GoodsSpecification> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(List<GoodsSpecification> specifications) {
		this.specifications = specifications;
	}

	public String getListPicUrl() {
		return listPicUrl;
	}

	public void setListPicUrl(String listPicUrl) {
		this.listPicUrl = listPicUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getProducingArea() {
		return producingArea;
	}

	public void setProducingArea(String producingArea) {
		this.producingArea = producingArea;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}
