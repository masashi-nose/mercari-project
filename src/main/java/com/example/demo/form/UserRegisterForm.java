package com.example.demo.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 
 * register画面からリクエストパラメータを受け取るフォーム.
 * 
 * @author masashi.nose
 *
 */
public class UserRegisterForm {
	/** 名前 */
	@NotBlank
	private String name;
	/** メールアドレス */
	@Email
	@NotBlank
	private String mailAddress;
	/** パスワード */
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$", message = "8-16 using 'A-Z/a-z/1-9'")
	private String password;
	/** 確認用パスワード */
	@NotBlank
	private String confirmationPassword;

	@Override
	public String toString() {
		return "UserRegisterForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ ", confirmationPassword=" + confirmationPassword + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

}
