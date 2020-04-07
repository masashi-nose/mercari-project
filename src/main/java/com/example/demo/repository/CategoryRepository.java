package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Category;

/**
 * 
 * categoryテーブルを操作するリポジトリ.
 * 
 * @author masashi.nose
 *
 */
@Repository
public class CategoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * categoryテーブルの１行分の情報を保持するローマッパー.
	 * 
	 */
	private static final RowMapper<Category> CATEGORY_ROW_MAPPER = (rs, i) -> {
		Category category = new Category();
		category.setId(rs.getInt("id"));
		category.setParent(rs.getInt("parent"));
		category.setName(rs.getString("name"));
		category.setNameAll(rs.getString("name_all"));
		return category;
	};
	
	/**
	 * categoryテーブルから親カテゴリ情報を取得します.
	 * 
	 * @return 親カテゴリ情報が詰まったオブジェクトリスト
	 */
	public List<Category> parentCategoryList() {
		String sql = "SELECT id, parent, name, name_all FROM category WHERE parent IS NULL AND name_all IS NULL";
		List<Category> parentCategoryList = template.query(sql, CATEGORY_ROW_MAPPER);
		return parentCategoryList;
	}
	
	/**
	 * categoryテーブルから子カテゴリの商品名を取得します.
	 * 
	 * @return 子カテゴリ情報が詰まったオブジェクトリスト
	 */
	public List<Category> childCategoryList() {
		String sql = "SELECT DISTINCT id, parent, name, name_all FROM category WHERE parent IS NOT NULL AND name_all IS NULL";
		List<Category> childCategoryList = template.query(sql, CATEGORY_ROW_MAPPER);
		return childCategoryList;
	}
	
	/**
	 * categoryテーブルから親カテゴリ情報を取得します.
	 * 
	 * @return 親カテゴリ情報が詰まったオブジェクトリスト
	 */
	public List<Category> grandChildCategoryList() {
		String sql = "SELECT DISTINCT id, parent, name, name_all FROM category WHERE parent IS NOT NULL AND name_all IS NOT NULL";
		List<Category> grandChildCategoryList = template.query(sql, CATEGORY_ROW_MAPPER);
		return grandChildCategoryList;
	}
	
	
}
