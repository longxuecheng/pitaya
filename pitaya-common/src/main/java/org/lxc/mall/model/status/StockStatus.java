package org.lxc.mall.model.status;

public enum StockStatus {

	IN_STOCK("IN_STOCK","有货"),
	SUFFICIENT("SUFFICIENT","充足"),
	SELL_OUT("SELL_OUT","售罄"),
	PREPARING("PREPARING","备货中");
	
	private String code;
	private String desc;
	
	private StockStatus(String code,String desc) {
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
