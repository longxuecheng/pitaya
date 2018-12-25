package org.lxc.mall.model.request;

import java.io.Serializable;

import org.lxc.mall.model.User;

public class UserWriteCondition implements Serializable{
	private Integer id;

    private String name;

    private String phoneNo;

    private String email;
    
    private String wechat_id;
    
    public String getWechat_id() {
		return wechat_id;
	}

	public void setWechat_id(String wechat_id) {
		this.wechat_id = wechat_id;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
    
    public User parseModel() {
		User u = new User();
		u.setId(id);
		u.setName(name);
		u.setPhoneNo(phoneNo);
		u.setEmail(email);
		u.setWechatId(wechat_id);
		return u;
	}

}