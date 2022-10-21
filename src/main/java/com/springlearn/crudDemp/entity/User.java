package com.springlearn.crudDemp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String eamil;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "user_password_id")
	private UsernamePassword usernamePasswordId;
	
	@Column(name = "role_id")
	private int userRoleId;

	public User() {
	}

	public User(int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.eamil = email;
	}

	public User(String name, String eamil, UsernamePassword usernamePasswordId, int userRoleId) {
		this.name = name;
		this.eamil = eamil;
		this.usernamePasswordId = usernamePasswordId;
		this.userRoleId = userRoleId;
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

	public UsernamePassword getUsernamePasswordId() {
		return usernamePasswordId;
	}

	public void setUsernamePasswordId(UsernamePassword usernamePasswordId) {
		this.usernamePasswordId = usernamePasswordId;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", eamil=" + eamil + ", usernamePasswordId=" + usernamePasswordId
				+ ", userRoleId=" + userRoleId + "]";
	}
	
}
