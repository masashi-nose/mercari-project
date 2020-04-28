package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

/**
 * ユーザー登録用サービスクラス.
 * 
 * @author masashi.nose
 *
 */
@Service
@Transactional
public class UserRegisterService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * ユーザー登録を行います.パスワードがハッシュ化されます.
	 * 
	 * @param user ユーザーオブジェクト
	 * @return ID自動採番オブジェクト
	 */
	public User register(User user) {
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);

		return userRepository.insert(user);
	}

	/**
	 * メールアドレスからユーザーを検索します.情報がない場合はnullを返します.
	 * 
	 * @param mailAddress
	 * @return ユーザー情報 or null
	 */
	public User findByMailAddress(String mailAddress) {
		return userRepository.findByMailAddress(mailAddress);
	}
}
