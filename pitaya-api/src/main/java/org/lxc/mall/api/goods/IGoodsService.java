package org.lxc.mall.api.goods;

import java.util.List;

import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.GoodsQueryCondition;
import org.lxc.mall.model.request.GoodsWriteCondition;
import org.lxc.mall.model.response.GoodsPhoto_DTO;
import org.lxc.mall.model.response.Goods_DTO;

public interface IGoodsService {
	
	public PaginationInfo<Goods_DTO> queryByCondition(GoodsQueryCondition query);
	
	public Goods_DTO queryById(Long id);
	
	public Long add(GoodsWriteCondition query) throws ProcessException, Exception;
	
	public Long update(GoodsWriteCondition query) throws ProcessException, Exception;
	
	public GoodsPhoto_DTO savePictures(String name,String path);
	
	public List<GoodsPhoto_DTO> getPictures(Long goodsId);
	
	public int deletePicture(Long pictureId) throws Exception;
	
	public void bindPictures2Goods(Long goodsId,List<Long> pictureIds) throws Exception;
}
