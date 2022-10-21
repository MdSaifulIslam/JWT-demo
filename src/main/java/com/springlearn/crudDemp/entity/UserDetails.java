package com.springlearn.crudDemp.entity;

public class UserDetails {

	private int id;

	private String name;

	private String eamil;

	private String userName;

	private String password;

	private int roleId;

	public UserDetails() {
	}


	public UserDetails(String name, String eamil, String userName, String password, int roleId) {
		this.name = name;
		this.eamil = eamil;
		this.userName = userName;
		this.password = password;
		this.roleId = roleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEamil() {
		return eamil;
	}

	public void setEamil(String eamil) {
		this.eamil = eamil;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", name=" + name + ", eamil=" + eamil + ", userName=" + userName
				+ ", password=" + password + ", roleId=" + roleId + "]";
	}


}
