package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Category;
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
	 * itemsテーブルとcategoryテーブルを結合し、両テーブルのカラム情報を取得するResultSetExtractor.
	 * 
	 */
	private static final ResultSetExtractor<List<Item>> ITEM_RESULT_SET_EXTRACTOR = (rs) -> {
		List<Item> itemList = new ArrayList<>();
		List<Category> categoryList = null;

		int itemIdToCheck = -1;

		while (rs.next()) {
			int currentItemId = rs.getInt("item_id");
			if (currentItemId != itemIdToCheck) {
				Item item = new Item();
				item.setId(currentItemId);
				item.setName(rs.getString("item_name"));
				item.setCondition(rs.getInt("item_condition"));
				item.setCategory(rs.getInt("item_category"));
				item.setBrand(rs.getString("item_brand"));
				item.setPrice(rs.getInt("item_price"));
				item.setShipping(rs.getInt("item_shipping"));
				item.setDescription(rs.getString("item_description"));
				categoryList = new ArrayList<Category>();
				item.setCategoryList(categoryList);
				itemList.add(item);
			}

			if (rs.getInt("category_id") != 0) {
				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				category.setParent(rs.getInt("category_parent"));
				category.setName(rs.getString("category_name"));
				category.setNameAll(rs.getString("category_name_all"));
				categoryList.add(category);

			}

			itemIdToCheck = currentItemId;

		}
		return itemList;

	};

//	/**
//	 * itemsテーブル１行分の情報を保持するローマッパー.
//	 * 
//	 */
//	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
//		Item item = new Item();
//		item.setId(rs.getInt("id"));
//		item.setName(rs.getString("name"));
//		item.setCategory(rs.getInt("category"));
//		item.setCondition(rs.getInt("condition"));
//		item.setBrand(rs.getString("brand"));
//		item.setPrice(rs.getInt("price"));
//		item.setShipping(rs.getInt("shipping"));
//		item.setDescription(rs.getString("description"));
//		return item;
//
//	};

	/**
	 * itemsテーブルからidを用いて商品情報を１件検索します.
	 * 
	 * @param id ID
	 * @return 商品情報を詰まったオブジェクト
	 */
	public Item load(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT i.id item_id, i.name item_name, i.category item_category, i.condition item_condition, i.brand item_brand, i.price item_price, i.shipping item_shipping, i.description item_description, ");
		sql.append("c.id category_id, c.parent category_parent, c.name category_name, c,name_all category_name_all ");
		sql.append("FROM items i left join category c on i.category = c.id WHERE id = :id");
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Item> itemList = template.query(sql.toString(), param, ITEM_RESULT_SET_EXTRACTOR);
		return itemList.get(0);
	}

	/**
	 * itemsテーブルから商品情報を全件検索します.
	 * 
	 * @return 商品情報を詰まったオブジェクト
	 */
	public List<Item> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT i.id item_id, i.name item_name, i.category item_category, i.condition item_condition, i.brand item_brand, i.price item_price, i.shipping item_shipping, i.description item_description, ");
		sql.append("c.id category_id, c.parent category_parent, c.name category_name, c,name_all category_name_all from items i left join category c ");
		sql.append("on i.category = c.id WHERE i.id <= 30 ORDER BY i.name");
		List<Item> itemList = template.query(sql.toString(), ITEM_RESULT_SET_EXTRACTOR);
		return itemList;

	}

}
