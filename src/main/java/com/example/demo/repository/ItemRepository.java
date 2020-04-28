package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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

//	@PostConstruct
//	public void init() {
//		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
//		SimpleJdbcInsert withtableName = simpleJdbcInsert.withTableName("items");
//		insert = withtableName.usingColumns("id");
//
//	}

	/**
	 * itemsテーブルに商品情報を追加します.
	 * 
	 * @param item 商品情報が詰まったオブジェクト
	 * @return IDが自動採番されたitemオブジェクト
	 */
//	public Item insert(Item item) {
//		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
//
//		if (item.getId() == null) {
//			Number key = insert.executeAndReturnKey(param);
//			item.setId(key.intValue());
//
//		}
//
//		return item;
//
//	}

	/**
	 * itemsテーブルにインサートします.
	 * 
	 * @param item 商品情報
	 */
	public void insert(Item item) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		String sql = "INSERT INTO items (name, condition, category, brand, price, description) VALUES (:name, :condition, :category, :brand, :price, :description)";
		template.update(sql, param);

	}

	/**
	 * 商品名、親カテゴリ、子カテゴリ、孫カテゴリ、ブランド名で曖昧検索します.
	 * 
	 * @param name       商品名
	 * @param parent     親カテゴリ
	 * @param child      子カテゴリ
	 * @param grandChild 孫カテゴリ
	 * @param brand      ブランド名
	 * @return 商品情報が詰まったオブジェクトのリスト
	 */
	public List<Item> findItem(String name, String parent, String child, String grandChild, String brand) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT i.id item_id, i.name item_name, i.category item_category, i.condition item_condition, i.brand item_brand, i.price item_price, i.shipping item_shipping, i.description item_description, ");
		sql.append(
				"c.id category_id, c.parent category_parent, c.name category_name, c.name_all category_name_all from items i left join category c ");
		sql.append("on i.category = c.id WHERE i.id <= 30 AND i.name ILIKE :name ");
		sql.append(
				"AND :parent IN (SELECT SPLIT_PART(c.name_all,'/', 1) FROM category) AND :child IN (SELECT SPLIT_PART(c.name_all,'/', 2) FROM category) AND :grandChild IN (SELECT SPLIT_PART(c.name_all ,'/', 3) FROM category) AND i.brand ILIKE :brand ORDER BY i.name");
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%")
				.addValue("parent", "%" + parent + "%").addValue("child", "%" + child + "%")
				.addValue("grandChild", "%" + grandChild + "%").addValue("brand", "%" + brand + "%");
		List<Item> itemList = template.query(sql.toString(), param, ITEM_RESULT_SET_EXTRACTOR);
		return itemList;

	}

	/**
	 * 商品名とブランド名から商品を検索します.
	 * 
	 * @param name  商品名
	 * @param brand ブランド
	 * @return 検索結果が詰まったリスト
	 */
	public List<Item> findItemByNameAndBrand(String name, String brand) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT i.id item_id, i.name item_name, i.category item_category, i.condition item_condition, i.brand item_brand, i.price item_price, i.shipping item_shipping, i.description item_description, ");
		sql.append(
				"c.id category_id, c.parent category_parent, c.name category_name, c.name_all category_name_all from items i left join category c ");
		sql.append("on i.category = c.id WHERE i.id <= 30 AND i.name ILIKE :name AND i.brand ILIKE :brand IS NULL");
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%").addValue("brand",
				"%" + brand + "%");
		List<Item> itemList = template.query(sql.toString(), param, ITEM_RESULT_SET_EXTRACTOR);
		return itemList;

	}

	/**
	 * itemsテーブルの商品情報を更新します.
	 * 
	 * @param item 商品情報オブジェクト
	 */
	public void update(Item item) {
		StringBuilder sql = new StringBuilder();
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		sql.append(
				"UPDATE items SET name = :name, condition = :condition, brand = :brand, price = :price, description = :description WHERE id = :id");
		template.update(sql.toString(), param);
	}

}
