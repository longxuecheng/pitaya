package org.lxc.mall.dao;

import org.lxc.mall.model.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Long id);

    Admin selectForLogin(Admin record);
    
    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}