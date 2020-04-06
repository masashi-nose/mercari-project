package com.example.demo.form;

/**
 * 商品追加画面からリクエストパラメータを受け取るフォーム.
 * 
 * @author masashi.nose
 *
 */
public class AddItemForm {

	/** 商品名 */
	private String name;
	/** 価格 */
	private String price;
	/** ブランド */
	private String brand;
	/** 親カテゴリ */
	private String parentCategory;
	/** 子カテゴリ */
	private String childCategory;
	/** 孫カテゴリ */
	private String grandChild;
	/** 状況 */
	private Integer condition;
	/** 商品詳細 */
	private String description;

	@Override
	public String toString() {
		return "AddItemForm [name=" + name + ", price=" + price + ", brand=" + brand + ", parentCategory="
				+ parentCategory + ", childCategory=" + childCategory + ", grandChild=" + grandChild + ", condition="
				+ condition + ", description=" + description + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getChildCategory() {
		return childCategory;
	}

	public void setChildCategory(String childCategory) {
		this.childCategory = childCategory;
	}

	public String getGrandChild() {
		return grandChild;
	}

	public void setGrandChild(String grandChild) {
		this.grandChild = grandChild;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
