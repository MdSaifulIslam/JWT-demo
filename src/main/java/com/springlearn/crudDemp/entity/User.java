package com.springlearn.crudDemp.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
@Entity
@Table(name = "user")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "role_id")
	private int userRoleId;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "image_path")
	private String imagePath;

	@Column(name = "address")
	private String address;

	@Column(name = "is_pass_updated")
	@Value("0")
	private int updated;

	@Column(name = "contact")
	private String contact;

	@Column(name = "is_social")
	private int isSocial;

	public User() {
	}

	public User(String name, String email, int userRoleId, String username, String imagePath, String address,
			int updated, String contact, int isSocial) {
		this.name = name;
		this.email = email;
		this.userRoleId = userRoleId;
		this.username = username;
		this.imagePath = imagePath;
		this.address = address;
		this.updated = updated;
		this.contact = contact;
		this.isSocial = isSocial;
	}

	public User(String name, String email, int userRoleId, String username, String password, String imagePath,
			String address, int updated, String contact, int isSocial) {
		this.name = name;
		this.email = email;
		this.userRoleId = userRoleId;
		this.username = username;
		this.password = password;
		this.imagePath = imagePath;
		this.address = address;
		this.updated = updated;
		this.contact = contact;
		this.isSocial = isSocial;
	}

	public User(int id, String name, String email, int userRoleId, String username, String password, String imagePath,
			String address, int updated, String contact, int isSocial) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.userRoleId = userRoleId;
		this.username = username;
		this.password = password;
		this.imagePath = imagePath;
		this.address = address;
		this.updated = updated;
		this.contact = contact;
		this.isSocial = isSocial;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getSocial() {
		return isSocial;
	}

	public void setSocial(int i) {
		this.isSocial = i;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", userRoleId=" + userRoleId + ", username="
				+ username + ", password=" + password + ", imagePath=" + imagePath + ", address=" + address
				+ ", updated=" + updated + ", contact=" + contact + ", isSocial=" + isSocial + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
