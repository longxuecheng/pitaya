package org.lxc.mall.model.request;

import org.lxc.mall.model.common.PageSerialization;

public class UserQueryCondition extends PageSerialization{
	
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
