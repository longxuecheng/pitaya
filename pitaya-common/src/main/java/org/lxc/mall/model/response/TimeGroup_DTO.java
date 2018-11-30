package org.lxc.mall.model.response;

import java.util.Date;

import org.lxc.mall.common.utils.time.TimeFormatter;

public class TimeGroup_DTO {
	
	public TimeGroup_DTO(Date createTime,Date updateTime,Date deleteTime) {
		this.createTime = TimeFormatter.formatDefault(createTime);
		this.updateTIme = TimeFormatter.formatDefault(updateTime);
		this.deleteTime = TimeFormatter.formatDefault(deleteTime);
	}

	private String createTime;
	
	private String updateTIme;
	
	private String deleteTime;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTIme() {
		return updateTIme;
	}

	public void setUpdateTIme(String updateTIme) {
		this.updateTIme = updateTIme;
	}

	public String getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
	}
	
	
	
}
