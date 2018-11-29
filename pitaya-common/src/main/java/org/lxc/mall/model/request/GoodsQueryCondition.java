package org.lxc.mall.model.request;

import org.lxc.mall.model.common.PageSerialization;

public class GoodsQueryCondition extends PageSerialization{
	
	public String name;
	
	public String category;

	public String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
