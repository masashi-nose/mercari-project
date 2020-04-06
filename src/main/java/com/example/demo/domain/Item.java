package com.example.demo.domain;

import java.util.List;

/**
 * itemsテーブルのカラム情報を持つドメインです.
 * 
 * @author masashi.nose
 *
 */
public class Item {
	/** ID */
	private Integer id;
	/** 商品名 */
	private String name;
	/** 状況 */
	private Integer condition;
	/** カテゴリー（ID） */
	private Integer category;
	/** ブランド名 */
	private String brand;
	/** 価格 */
	private Integer price;
	/** 配送 */
	private Integer shipping;
	/** 詳細 */
	private String description;
	/** カテゴリーリスト */
	private List<Category> categoryList;

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", condition=" + condition + ", category=" + category + ", brand="
				+ brand + ", price=" + price + ", shipping=" + shipping + ", description=" + description
				+ ", categoryList=" + categoryList + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

}
