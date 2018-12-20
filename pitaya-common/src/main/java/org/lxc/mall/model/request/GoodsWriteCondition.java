package org.lxc.mall.model.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.model.Goods;
import org.springframework.util.StringUtils;

public class GoodsWriteCondition implements Serializable{
	
    private Long id;

    private String name;

    private String category;

    private String producingArea;

    private String description;
    
    private Long supplierId;
    
    private List<Long> pictureIds;
    
    private List<StockWriteCondition> stocks;
    
	public List<StockWriteCondition> getStocks() {
		return stocks;
	}

	public void setStocks(List<StockWriteCondition> stocks) {
		this.stocks = stocks;
	}

	public List<Long> getPictureIds() {
		return pictureIds;
	}

	public void setPictureIds(List<Long> pictureIds) {
		this.pictureIds = pictureIds;
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

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Goods parseModel() {
		Goods g = new Goods();
		g.setIsDelete(Byte.valueOf("0"));
		g.setId(id);
		g.setSupplierId(supplierId);
		g.setCategory(category);
		g.setName(name);
		g.setDescription(description);
		g.setProducingArea(producingArea);
		g.setUpdateTime(new Date());
		return g;
	}
	
	public void validate() throws Exception {
		if (supplierId == null || supplierId.longValue() == 0) {
			throw new ProcessException("请输入商品的供应商");
		}
		if (StringUtils.isEmpty(name)) {
			throw new ProcessException("请输入商品名称");
		}
		if (StringUtils.isEmpty(category)) {
			throw new ProcessException("请输入商品类别");
		}
		if (StringUtils.isEmpty(producingArea)) {
			throw new ProcessException("请输入商品产地");
		}
		if (stocks != null && !stocks.isEmpty()) {
			validateStocks();
		}
	}
	
	private void validateStocks() throws Exception {
		for (StockWriteCondition stock : stocks) {
			if (StringUtils.isEmpty(stock.getSpecification())) {
				throw new ProcessException("请定义库存的规格");
			}
			
			if (stock.getCostUnitPrice() == null || 
					stock.getSaleUnitPrice() == null ||
					stock.getTotalQuantity() == null ) {
				throw new ProcessException("请定义库存的价格和数量");
			}
			
		}
	}
	
	public List<StockWriteCondition> parseStockModels(Long goodsId) {
		for (StockWriteCondition stock : stocks) {
			if (null == stock.getId() || stock.getId().longValue() == 0) {
				stock.setGoodsId(goodsId);
			}
		}
		return stocks;
	}
	
	public static void main(String[] args) {
		List<StockWriteCondition> cs = new ArrayList<>();
		StockWriteCondition c1 = new StockWriteCondition();
		c1.setGoodsId(1l);
		cs.add(c1);
		cs.stream().map((c) -> {c.setName("test");return c;}).forEach((c) -> c.setGoodsId(120l));
		cs.forEach((c) -> System.out.println(c.getGoodsId()+c.getName()));
	}
}
