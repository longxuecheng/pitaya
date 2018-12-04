package org.lxc.mall.model.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.lxc.mall.model.Goods;
import org.lxc.mall.model.Stock;

public class GoodsWriteCondition implements Serializable{
	
    private Long id;

    private String name;

    private String category;

    private String producingArea;

    private String description;
    
    List<StockWriteCondition> stocks;
    
	public List<StockWriteCondition> getStocks() {
		return stocks;
	}

	public void setStocks(List<StockWriteCondition> stocks) {
		this.stocks = stocks;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
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

	public Goods parseModel() {
		Goods g = new Goods();
		g.setIsDelete(Byte.valueOf("0"));
		g.setId(id);
		g.setCategory(category);
		g.setName(name);
		g.setDescription(description);
		g.setProducingArea(producingArea);
		g.setUpdateTime(new Date());
		return g;
	}
	
	public List<StockWriteCondition> parseStockModels(Long goodsId) {
		for (StockWriteCondition stock : stocks) {
			if (null == stock.getId() || stock.getId().longValue() == 0) {
				stock.setGoodsId(goodsId);
			}
		}
		return stocks;
	}
	
}
