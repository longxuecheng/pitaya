package org.lxc.mall.sys.auth;

import java.util.Date;

import org.lxc.mall.api.admin.IAdminService;
import org.lxc.mall.common.utils.token.JwtTokenMaker;
import org.lxc.mall.common.utils.token.JwtTokenMaker.UserClaim;
import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.model.Admin;
import org.lxc.mall.model.request.LoginRequest;
import org.lxc.mall.model.response.Token_DTO;
import org.lxc.mall.sys.SessionCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class UserAuthorizationRealm {

	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private SessionCacheManager sessionCacheManager;
	
	private long defaultExpirationMilliSeconds = 3600 * 1000;
	
	public UserPrincipal getPrincipal(String accessToken) {
		Object o = sessionCacheManager.get(accessToken);
		if (o instanceof UserPrincipal) {
			UserPrincipal principal = (UserPrincipal)o ;
			return principal;
		}
		return null;
	}
	
	public Token_DTO authenticate(LoginRequest query) {
		Admin a = adminService.queryForLogin(query);
		if (a == null) {
			throw new ProcessException("用户名或密码错误");
		}
		UserPrincipal p = new UserPrincipal();
		p.setId(a.getId());
		p.setName(a.getName());
		String token = JwtTokenMaker.signToken(new JwtTokenMaker.UserClaim(a.getId(), a.getName()), expiresAt());
		sessionCacheManager.put(token, p);
		return installTokenDTO(a,token);
	}
	
	/**
	 * validate is used to validate a signed token
	 * if failed to verify token,will throw {@code JWTVerificationException}
	 * @param token
	 * @return
	 */
	public void validate(String token) {
		JwtTokenMaker.getClaim(token);
	}
	
	private Date expiresAt() {
		Date now = new Date();
		return new Date(now.getTime() + defaultExpirationMilliSeconds);
	}
	
	private Token_DTO installTokenDTO(Admin a,String accessToken) {
		Token_DTO tk = new Token_DTO();
		tk.setAccessToken(accessToken);
		tk.setEmail(a.getEmail());
		tk.setName(a.getName());
		tk.setPhoneNo(a.getPhoneNo());
		return tk;
	}
	
}
