package org.lxc.mall.service;

import java.util.List;

import org.lxc.mall.api.category.ICategoryService;
import org.lxc.mall.dao.GoodsCategoryMapper;
import org.lxc.mall.model.GoodsCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CategoryService implements ICategoryService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GoodsCategoryMapper goodsCategoryDao;

	@Override
	public List<GoodsCategory> allTop() {
		return goodsCategoryDao.selectAllTop();
	}

	@Override
	public GoodsCategory info(Integer id) {
		return goodsCategoryDao.selectByPrimaryKey(id);
	}

	@Override
	public List<GoodsCategory> children(Integer parentId) {
		return goodsCategoryDao.selectChildren(parentId);
	}

	@Override
	public Integer create(GoodsCategory category) {
		goodsCategoryDao.insertSelective(category);
		return category.getId();
	}

}
