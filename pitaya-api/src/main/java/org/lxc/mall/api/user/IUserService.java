package org.lxc.mall.api.user;

import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.UserWriteCondition;
import org.lxc.mall.model.request.UserQueryCondition;
import org.lxc.mall.model.response.User_DTO;

public interface IUserService {
	
	public PaginationInfo<User_DTO> queryByCondition(UserQueryCondition query);
	
	public User_DTO queryById(Integer id);
	
	public Integer add(UserWriteCondition query) throws ProcessException, Exception;
	
	public Integer update(UserWriteCondition query) throws ProcessException, Exception;
}