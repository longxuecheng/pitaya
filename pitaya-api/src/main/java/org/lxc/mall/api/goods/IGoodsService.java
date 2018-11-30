package org.lxc.mall.api.goods;

import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.GoodsQueryCondition;
import org.lxc.mall.model.request.GoodsWriteCondition;
import org.lxc.mall.model.response.Goods_DTO;

public interface IGoodsService {
	
	public PaginationInfo<Goods_DTO> queryByCondition(GoodsQueryCondition query);
	
	public Goods_DTO queryById(Long id);
	
	public Long add(GoodsWriteCondition query) throws ProcessException;
	
	public Long update(GoodsWriteCondition query) throws ProcessException;
	
	public void savePictures();
}
