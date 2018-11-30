package org.lxc.mall.sys.auth;

import java.util.UUID;

import org.lxc.mall.api.admin.IAdminService;
import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.model.Admin;
import org.lxc.mall.model.request.LoginRequest;
import org.lxc.mall.sys.SessionCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAuthorizationRealm {

	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private SessionCacheManager sessionCacheManager;
	
	public UserPrincipal getPrincipal(String accessToken) {
		Object o = sessionCacheManager.get(accessToken);
		if (o instanceof UserPrincipal) {
			UserPrincipal principal = (UserPrincipal)o ;
			return principal;
		}
		return null;
	}
	
	public String authenticate(LoginRequest query) {
		Admin a = adminService.queryForLogin(query);
		if (a == null) {
			throw new ProcessException("用户名或密码错误");
		}
		UserPrincipal p = new UserPrincipal();
		p.setId(a.getId());
		p.setName(a.getName());
		UUID uuId = UUID.randomUUID();
		sessionCacheManager.put(uuId.toString(), p);
		return uuId.toString();
	}
	
}