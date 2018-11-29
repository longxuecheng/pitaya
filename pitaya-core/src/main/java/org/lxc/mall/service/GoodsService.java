package org.lxc.mall.service;

import java.util.List;

import org.lxc.mall.api.goods.IGoodsService;
import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.dao.GoodsMapper;
import org.lxc.mall.model.Goods;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.GoodsQueryCondition;
import org.lxc.mall.model.request.GoodsWriteCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class GoodsService implements IGoodsService {

	@Autowired
	private GoodsMapper goodsDao;
	
	@Override
	public PaginationInfo<Goods> queryByCondition(GoodsQueryCondition query) {
		PaginationInfo<Goods> page = new PaginationInfo<Goods>();
		Page<Goods> p = PageHelper.startPage(query.pageNo, query.pageSize, true);
		List<Goods> orders = goodsDao.selectAll();
		page.setItems(orders);
		page.setTotal(p.getTotal());
		page.setPageNo(query.pageNo);
		page.setPageSize(query.pageSize);
		return page;
	}

	@Override
	public Goods queryById(Long id) {
		return goodsDao.selectByPrimaryKey(id);
	}

	@Override
	public Long add(GoodsWriteCondition query) throws ProcessException{
		Goods g = query.parseModel();
		try {
			int affected = goodsDao.insertSelective(g);
			if (affected == 0) {
				throw new ProcessException("新增商品失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new ProcessException("新增商品失败");
		}
		return g.getId();
	}

	@Override
	public Long update(GoodsWriteCondition query) throws ProcessException{
		Goods g = query.parseModel();
		try {
			int affected = goodsDao.updateByPrimaryKey(g);
			if (affected == 0) {
				throw new ProcessException("更新商品信息失败");
			}
		}catch (Exception e) {
			throw new ProcessException("更新商品信息失败");
		}
		return g.getId();
	}

}
