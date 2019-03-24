package org.lxc.mall.service;

import java.util.List;

import org.lxc.mall.api.specification.ISpecificationService;
import org.lxc.mall.dao.SpecificationMapper;
import org.lxc.mall.model.Specification;
import org.lxc.mall.model.common.PageSerialization;
import org.lxc.mall.model.common.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service
@Transactional
public class SpecificationService implements ISpecificationService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SpecificationMapper specificationDao;
	
	@Override
	public PaginationInfo<Specification> getByPagination(PageSerialization pagination) {
		PaginationInfo<Specification> page = new PaginationInfo<>();
		Page<Specification> p = PageHelper.startPage(pagination.pageNo, pagination.pageSize, true);
		List<Specification> specs = specificationDao.selectAll();
		page.setItems(specs);
		page.setTotal(p.getTotal());
		page.setPageNo(pagination.pageNo);
		page.setPageSize(pagination.pageSize);
		return page;
	}

	@Override
	public void createSpecification(Specification spec) {
		spec.setId(null);
		specificationDao.insert(spec);
	}

	@Override
	public Specification specificationInfo(Integer id) {
		return specificationDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateSpecification(Specification spec) {
		specificationDao.updateByPrimaryKey(spec);
	}

	@Override
	public List<Specification> getAll() {
		return specificationDao.selectAll();
	}


}
