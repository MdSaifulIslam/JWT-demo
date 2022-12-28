package com.springlearn.crudDemp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "raw_data")
public class RawData {
	
	@Id
	@Column(name = "user_id")
	private int userId;

	@Column(name = "secret")
	private String secret;

	public RawData() {
	}

	public RawData(String secret) {
		this.secret = secret;
	}

	public RawData(int user_id, String secret) {
		this.userId = user_id;
		this.secret = secret;
	}

	public int getUser_id() {
		return userId;
	}

	public void setUser_id(int user_id) {
		this.userId = user_id;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Override
	public String toString() {
		return "RawData [user_id=" + userId + ", secret=" + secret + "]";
	}
}
