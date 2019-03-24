package org.lxc.mall.model.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.model.Goods;
import org.lxc.mall.model.GoodsSpecification;
import org.springframework.util.StringUtils;

public class GoodsWriteCondition implements Serializable{
	
    private Long id;

    private String name;

    private Integer categoryId;

    private String producingArea;

    private String description;
    
    private Long supplierId;
    
    private List<Long> pictureIds;
    
    private String listPicUrl;
    
    private List<StockWriteCondition> stocks;
    
    private List<GoodsSpecification> specifications;
    
	public List<GoodsSpecification> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(List<GoodsSpecification> specifications) {
		this.specifications = specifications;
	}

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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public String getListPicUrl() {
		return listPicUrl;
	}

	public void setListPicUrl(String listPicUrl) {
		this.listPicUrl = listPicUrl;
	}

	public Goods parseModel() {
		Goods g = new Goods();
		g.setIsDelete(Byte.valueOf("0"));
		g.setId(id);
		g.setSupplierId(supplierId);
		g.setCategoryId(categoryId);
		g.setName(name);
		g.setDescription(description);
		g.setListPicUrl(listPicUrl);
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
		if (categoryId == null || categoryId == 0) {
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
			if (stock.getSpecifications() == null || stock.getSpecifications().size() == 0 ) {
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
	
	public List<GoodsSpecification> parseGoodsSpecifications(Long goodsId){
		for (GoodsSpecification spec : specifications) {
			spec.setGoodsId(goodsId);
		}
		return specifications;
	}
	
}
