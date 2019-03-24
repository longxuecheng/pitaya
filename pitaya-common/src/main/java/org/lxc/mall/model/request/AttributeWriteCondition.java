package org.lxc.mall.model.request;

import java.io.Serializable;

public class AttributeWriteCondition implements Serializable{
	
	private Integer categoryId;
	
	private String name;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
