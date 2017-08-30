package com.blueocean.web.loginmanage.entity;

public class LoginPara {

	private String clientId;

	private String userName;

	private String password;

	private String captchaCode;

	private String captchaValue;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
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

	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}

	public String getCaptchaValue() {
		return captchaValue;
	}

	public void setCaptchaValue(String captchaValue) {
		this.captchaValue = captchaValue;
	}

	@Override
	public String toString() {
		return "LoginPara [clientId=" + clientId + ", userName=" + userName + ", password=" + password
				+ ", captchaCode=" + captchaCode + ", captchaValue=" + captchaValue + "]";
	}
	
}
