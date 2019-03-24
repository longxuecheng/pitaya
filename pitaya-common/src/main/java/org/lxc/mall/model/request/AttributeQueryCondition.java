package org.lxc.mall.model.request;

import org.lxc.mall.model.common.PageSerialization;

public class AttributeQueryCondition extends PageSerialization{
	
	public Integer categoryId;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
