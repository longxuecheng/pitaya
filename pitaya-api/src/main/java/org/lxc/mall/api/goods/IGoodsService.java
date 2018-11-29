package org.lxc.mall.api.goods;

import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.model.Goods;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.GoodsQueryCondition;
import org.lxc.mall.model.request.GoodsWriteCondition;

public interface IGoodsService {
	
	public PaginationInfo<Goods> queryByCondition(GoodsQueryCondition query);
	
	public Goods queryById(Long id);
	
	public Long add(GoodsWriteCondition query) throws ProcessException;
	
	public Long update(GoodsWriteCondition query) throws ProcessException;
}
