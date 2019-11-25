package org.lxc.mall.api.attribute;

import org.lxc.mall.model.Attribute;
import org.lxc.mall.model.AttributeCategory;
import org.lxc.mall.model.common.PageSerialization;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.AttributeQueryCondition;
import org.lxc.mall.model.request.AttributeWriteCondition;

public interface IAttributeService {
	
	public PaginationInfo<AttributeCategory> getCategoriesByPagination(PageSerialization pagination);
	
	public PaginationInfo<Attribute> getAttributesByPagination(AttributeQueryCondition query);
	
	public void createAttribute(AttributeWriteCondition params);
	
}

