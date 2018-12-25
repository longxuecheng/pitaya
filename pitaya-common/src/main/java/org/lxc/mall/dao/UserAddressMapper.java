package org.lxc.mall.dao;

import java.util.List;

import org.lxc.mall.model.UserAddress;

public interface UserAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Integer id);
    
    List<UserAddress> selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);
}