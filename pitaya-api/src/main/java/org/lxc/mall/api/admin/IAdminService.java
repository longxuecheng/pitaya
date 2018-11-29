package org.lxc.mall.api.admin;

import org.lxc.mall.model.Admin;
import org.lxc.mall.model.request.AdminWriteCondition;
import org.lxc.mall.model.request.LoginRequest;

public interface IAdminService {

	Long addAdmin(AdminWriteCondition query) throws Exception;
	
	Long updateAdmin(AdminWriteCondition query) throws Exception;
	
	Admin queryForLogin(LoginRequest query);
	
	Admin queryById(Long id);
	
}
