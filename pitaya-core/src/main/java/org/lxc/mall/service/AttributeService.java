package org.lxc.mall.service;

import java.util.List;

import org.lxc.mall.api.attribute.IAttributeService;
import org.lxc.mall.dao.AttributeCategoryMapper;
import org.lxc.mall.dao.AttributeMapper;
import org.lxc.mall.model.Attribute;
import org.lxc.mall.model.AttributeCategory;
import org.lxc.mall.model.common.PageSerialization;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.AttributeQueryCondition;
import org.lxc.mall.model.request.AttributeWriteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service
@Transactional
public class AttributeService implements IAttributeService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AttributeCategoryMapper attributeCategoryDao;
	
	@Autowired
	private AttributeMapper attributeDao;
	
	@Override
	public PaginationInfo<AttributeCategory> getCategoriesByPagination(PageSerialization pagination) {
		PaginationInfo<AttributeCategory> page = new PaginationInfo<>();
		Page<AttributeCategory> p = PageHelper.startPage(pagination.pageNo, pagination.pageSize, true);
		List<AttributeCategory> specs = attributeCategoryDao.selectAll();
		page.setItems(specs);
		page.setTotal(p.getTotal());
		page.setPageNo(pagination.pageNo);
		page.setPageSize(pagination.pageSize);
		return page;
	}

	@Override
	public PaginationInfo<Attribute> getAttributesByPagination(AttributeQueryCondition query) {
		PaginationInfo<Attribute> page = new PaginationInfo<>();
		Page<Attribute> p = PageHelper.startPage(query.pageNo, query.pageSize, true);
		List<Attribute> specs = attributeDao.selectByCategoryId(query.getCategoryId());
		page.setItems(specs);
		page.setTotal(p.getTotal());
		page.setPageNo(query.pageNo);
		page.setPageSize(query.pageSize);
		return page;
	}

	@Override
	public void createAttribute(AttributeWriteCondition params) {
		Attribute record = new Attribute();
		record.setAttributeCategoryId(params.getCategoryId());
		record.setName(params.getName());
		record.setValue("");
		attributeDao.insertSelective(record);
	}


}
