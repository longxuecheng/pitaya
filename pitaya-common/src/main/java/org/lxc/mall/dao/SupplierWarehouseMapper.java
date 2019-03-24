package org.lxc.mall.dao;

import java.util.List;

import org.lxc.mall.model.SupplierWarehouse;

public interface SupplierWarehouseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SupplierWarehouse record);

    int insertSelective(SupplierWarehouse record);

    SupplierWarehouse selectByPrimaryKey(Integer id);
    
    /**
     * Get warehouses for a given supplier
     * @param id
     * @return
     */
    List<SupplierWarehouse> selectBySupplier(Long id);

    int updateByPrimaryKeySelective(SupplierWarehouse record);

    int updateByPrimaryKey(SupplierWarehouse record);
}