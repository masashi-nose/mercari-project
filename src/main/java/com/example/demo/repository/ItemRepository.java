package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;

/**
 * itemsテーブルを操作するリポジトリ.
 * 
 * @author masashi.nose
 *
 */
@Repository
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * itemsテーブル１行分の情報を保持するローマッパー.
	 * 
	 */
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name;"));
		item.setCategory(rs.getInt("category"));
		item.setCondition(rs.getInt("condition"));
		item.setBrand(rs.getString("brand"));
		item.setPrice(rs.getInt("price"));
		item.setShipping(rs.getInt("shipping"));
		item.setDescription(rs.getString("description"));
		return item;

	};

	/**
	 * itemsテーブルからidを用いて商品情報を１件検索します.
	 * 
	 * @param id ID
	 * @return 商品情報を詰まったオブジェクト
	 */
	public Item load(Integer id) {
		String sql = "SELECT id, name, category, condition, brand, price, shipping, description FROM items WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;
	}

	/**
	 * itemsテーブルから商品情報を全件検索します.
	 * 
	 * @return 商品情報を詰まったオブジェクト
	 */
	public List<Item> findAll() {
		String sql = "SELECT id, name, category, condition, brand, price, shipping, description FROM items ORDER BY name";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}

}
