package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Original;

/**
 * 
 * originalテーブルを操作するリポジトリ
 * 
 * @author masashi.nose
 *
 */
@Repository
public class OriginalRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * originalテーブルの１行分の情報を保持するローマッパー.
	 * 
	 */
	private static final RowMapper<Original> ORIGINAL_ROW_MAPPER = (rs, i) -> {
		Original original = new Original();
		original.setTrainId(rs.getInt("train_id"));
		original.setName(rs.getString("name"));
		original.setItemConditionId(rs.getInt("item_condition_id"));
		original.setBrandName(rs.getString("brand_name"));
		original.setCategoryName(rs.getString("category_name"));
		original.setPrice(rs.getInt("price"));
		original.setShipping(rs.getInt("shipping"));
		original.setItemDescription(rs.getString("item_description"));
		return original;
	};

	/**
	 * 
	 * originalテーブルから全件検索します.
	 * 
	 * @return originalテーブルの情報が詰まったオブジェクトのリスト
	 */
	public List<Original> originalFindAll() {
		String sql = "SELECT train_id, name, itemConditionId, categoryName, brandName, price, shipping, itemDescription FROM original ORDER BY train_id";
		List<Original> originalList = template.query(sql, ORIGINAL_ROW_MAPPER);
		return originalList;

	}

}
