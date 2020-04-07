package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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

	/** 自動採番されたID情報を持つオブジェクトが返ってくるinsertの実行準備. */
	private SimpleJdbcInsert insert;

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
		sql.append("FROM items i left join category c on i.category = c.id WHERE i.id = :id");
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
		sql.append(
				"SELECT i.id item_id, i.name item_name, i.category item_category, i.condition item_condition, i.brand item_brand, i.price item_price, i.shipping item_shipping, i.description item_description, ");
		sql.append(
				"c.id category_id, c.parent category_parent, c.name category_name, c,name_all category_name_all from items i left join category c ");
		sql.append("on i.category = c.id WHERE i.id <= 30 ORDER BY i.name");
		List<Item> itemList = template.query(sql.toString(), ITEM_RESULT_SET_EXTRACTOR);
		return itemList;

	}

	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withtableName = simpleJdbcInsert.withTableName("items");
		insert = withtableName.usingColumns("id");

	}

	/**
	 * itemsテーブルに商品情報を追加します.
	 * 
	 * @param item 商品情報が詰まったオブジェクト
	 * @return IDが自動採番されたitemオブジェクト
	 */
	public Item insert(Item item) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);

		if (item.getId() == null) {
			Number key = insert.executeAndReturnKey(param);
			item.setId(key.intValue());

		}

		return item;

	}

	/**
	 * 商品名で曖昧検索します.
	 * 
	 * @param name  商品名
	 * @param brand ブランド名
	 * @return 商品情報が詰まったオブジェクトのリスト
	 */
	public List<Item> findByItemName(String name, String brand) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT i.id item_id, i.name item_name, i.category item_category, i.condition item_condition, i.brand item_brand, i.price item_price, i.shipping item_shipping, i.description item_description, ");
		sql.append(
				"c.id category_id, c.parent category_parent, c.name category_name, c,name_all category_name_all from items i left join category c ");
		sql.append(
				"on i.category = c.id WHERE i.id <= 30 AND i.name ILIKE :name AND i.brand ILIKE :brand IS NULL ORDER BY i.name");
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%").addValue("brand",
				"%" + brand + "%");
		List<Item> itemList = template.query(sql.toString(), param, ITEM_RESULT_SET_EXTRACTOR);
		return itemList;

	}

}
