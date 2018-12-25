package org.lxc.mall.api.userAddress;

import java.util.List;

import org.lxc.mall.model.UserAddress;
import org.lxc.mall.model.request.UserAddressWriteCondition;

public interface IUserAddressService {

	public Integer add(UserAddressWriteCondition query) throws Exception;
	
	public Integer update(UserAddressWriteCondition query) throws Exception;

	List<UserAddress> queryListByUserId(Integer userId);
	
	
}
