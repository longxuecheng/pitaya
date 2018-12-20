package org.lxc.mall.model.status;

public enum GoodsStatus {

	ON_SALE("ON_SALE","在售"),
	OUT_SALE("OUT_SALE","下架");
	
	private String code;
	
	private String desc;
	
	private GoodsStatus(String code,String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public String code() {
		return this.code;
	}
	
	public String desc() {
		return this.desc;
	}
}
