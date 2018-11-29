package org.lxc.mall.model.common;

import java.io.Serializable;

public class PageSerialization implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer pageNo = 1;
	
	public Integer pageSize = 20;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
