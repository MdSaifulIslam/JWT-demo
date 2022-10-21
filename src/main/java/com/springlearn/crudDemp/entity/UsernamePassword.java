package com.springlearn.crudDemp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "username_password")
public class UsernamePassword {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String password;

	public UsernamePassword() {
	}

	public UsernamePassword(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public UsernamePassword(int id, String userName, String password) {
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "UsernamePassword [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}

}
