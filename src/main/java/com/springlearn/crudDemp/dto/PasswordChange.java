package com.springlearn.crudDemp.dto;

public class PasswordChange {

	private String newPassword;
	private String oldPassword;

	public PasswordChange() {
	}

	public PasswordChange(String newPassword) {
		this.newPassword = newPassword;
	}

	public PasswordChange(String newPassword, String oldPassword) {
		this.newPassword = newPassword;
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	@Override
	public String toString() {
		return "PasswordChange [newPassword=" + newPassword + ", oldPassword=" + oldPassword + "]";
	}
}
