package org.lxc.mall.sys.auth;

public class UserPrincipal {

	private String name;
	
	private Long id;
	
	private String[] roles = {};
	
	private String[] permissions = {};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public String[] getPermissions() {
		return permissions;
	}

	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}
	
	public boolean hasPermissions(String ... perms) {
		for (String p : perms) {
			for (String permission : permissions) {
				if (p.equals(permission)) {
					return true;
				}
			}
		}
		return false;
	}
	
}
