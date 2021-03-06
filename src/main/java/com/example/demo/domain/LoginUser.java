package com.example.demo.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * ログイン情報を保持するエンティティ.
 * 
 * @author masashi.nose
 *
 */
public class LoginUser extends org.springframework.security.core.userdetails.User {
	private static final long serialVersionUID = 1L;

	private final User user;

	/**
	 * 
	 * コンストラクタ
	 * 
	 * @param user
	 * @param authorityList
	 */
	public LoginUser(User user, Collection<GrantedAuthority> authorityList) {
		super(user.getMailAddress(), user.getPassword(), authorityList);
		this.user = user;
	}

	/**
	 * ユーザー情報のゲッター
	 * 
	 * @return ユーザー情報
	 */
	public User getUser() {
		return user;
	}

}
