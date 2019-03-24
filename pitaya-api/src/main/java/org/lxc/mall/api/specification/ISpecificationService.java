package org.lxc.mall.api.specification;

import java.util.List;

import org.lxc.mall.model.Specification;
import org.lxc.mall.model.common.PageSerialization;
import org.lxc.mall.model.common.PaginationInfo;

public interface ISpecificationService {
	
	public PaginationInfo<Specification> getByPagination(PageSerialization pagination);
	
	public List<Specification> getAll();
	
	public Specification specificationInfo(Integer id);
	
	public void createSpecification(Specification spec);
	
	public void updateSpecification(Specification spec);

}
