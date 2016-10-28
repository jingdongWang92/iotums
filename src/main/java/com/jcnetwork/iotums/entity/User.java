package com.jcnetwork.iotums.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jcnetwork.iotums.common.Privilege;

/**
 * 用户
 *
 * @author Jingdong Wang
 */
public class User extends AbstractEntity {

    private static final long serialVersionUID = -2921689304753120556L;

    private String username;
    private String password;
    private String phone;
    private String email;
    //Default user is initial when create database, do not delete
    private boolean defaultUser = false;
    private Date lastLoginTime;
    private List<Privilege> privileges = new ArrayList<>();
    
    public User() {
    }

    public User(String username, String password, String phone, String email) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getDefaultUser() {
		return defaultUser;
	}

	public void setDefaultUser(boolean defaultUser) {
		this.defaultUser = defaultUser;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{username='").append(username).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", guid='").append(guid).append('\'');
        sb.append(", defaultUser='").append(defaultUser).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    public User phone(String phone) {
        this.phone = phone;
        return this;
    }


    public User username(String username) {
        this.username = username;
        return this;
    }


    public Date lastLoginTime() {
        return lastLoginTime;
    }

    public User lastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
        return this;
    }

    public User createTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }
}