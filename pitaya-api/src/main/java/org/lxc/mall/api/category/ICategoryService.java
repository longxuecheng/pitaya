package org.lxc.mall.api.category;

import java.util.List;

import org.lxc.mall.model.GoodsCategory;

public interface ICategoryService {

	public List<GoodsCategory> allTop();
	
	public GoodsCategory info(Integer id);
	
	public List<GoodsCategory> children(Integer parentId);
	
	public Integer create(GoodsCategory category);
}
