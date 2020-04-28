package com.example.demo.domain;

import java.util.Date;

/**
 * usersテーブルのエンティティ
 * 
 * @author masashi.nose
 *
 */
public class User {
	/** ID */
	private Integer id;
	/** 名前 */
	private String name;
	/** パスワード */
	private String password;
	/** 権限 */
	private Integer authority;
	/** UUID */
	private String uuid;
	/** 登録日 */
	private Date registerDate;
	/** メールアドレス */
	private String mailAddress;

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", authority=" + authority + ", uuid="
				+ uuid + ", registerDate=" + registerDate + ", mailAddress=" + mailAddress + "]";
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
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

}
