package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

/**
 * ログインに関するサービスクラス.
 * 
 * @author masashi.nose
 *
 */
@Service
@Transactional
public class UserLoginService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * 
	 * メールアドレスからユーザーを検索します.
	 * 
	 * @param mailAddress メールアドレス  
	 * @return ユーザー情報
	 */
	public User findByMaiAddress(String mailAddress) {
		return userRepository.findByMailAddress(mailAddress);

	}
}
