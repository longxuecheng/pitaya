package org.lxc.mall.model.response;

import java.io.Serializable;

public class GoodsPhoto_DTO implements Serializable {
    private Long id;

    private String name;

    private String path;

    private Short displayOrder;

    private String createTime;

    private static final long serialVersionUID = 1L;

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Short getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Short displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
