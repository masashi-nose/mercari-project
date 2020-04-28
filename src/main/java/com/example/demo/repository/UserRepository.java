package com.example.demo.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

/**
 * usersテーブルを操作するリポジトリ
 * 
 * @author masashi.nose
 *
 */
@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private SimpleJdbcInsert insert;

	/**
	 * usersテーブル１行分のデータを保持するローマッパー
	 * 
	 */
	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setMailAddress(rs.getString("mail_address"));
		user.setAuthority(rs.getInt("authority"));
		user.setPassword(rs.getString("password"));
		user.setUuid(rs.getString("uuid"));
		user.setRegisterDate(rs.getDate("register_date"));
		return user;
	};

	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("users");
		insert = withTableName.usingGeneratedKeyColumns("id");

	}

	/**
	 * ID自動採番型インサート
	 * 
	 * @param user ユーザーオブジェクト
	 * @return ID自動採番オブジェクト
	 */
	public User insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);

		if (user.getId() == null) {
			Number key = insert.executeAndReturnKey(param);
			user.setId(key.intValue());

		}

		return user;
	}

	/**
	 * メールアドレスからユーザーを検索します.
	 * 
	 * @param mailAddress メールアドレス
	 * @return　ユーザー情報
	 */
	public User findByMailAddress(String mailAddress) {
		String sql = "SELECT id, name, mail_address, authority, password, uuid, register_date FROM users WHERE mail_address = :mailAddress";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress);

		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);

		if (userList.size() == 0) {
			return null;
		}

		return userList.get(0);
	}

}
