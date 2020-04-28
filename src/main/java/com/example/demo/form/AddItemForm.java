package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * 商品追加画面からリクエストパラメータを受け取るフォーム.
 * 
 * @author masashi.nose
 *
 */
public class AddItemForm {

	/** 商品名 */
	@NotBlank(message = "error: may not be empty")
	private String name;
	/** 価格 */
	@NotBlank(message = "error: may not be empty")
	private String price;
	/** ブランド */
	@NotBlank(message = "error: may not be empty")
	private String brand;
	/** 親カテゴリ */
	@NotBlank(message = "error: may not be empty")
	private String parentCategory;
	/** 子カテゴリ */
	@NotBlank(message = "error: may not be empty")
	private String childCategory;
	/** 孫カテゴリ */
	@NotBlank(message = "error: may not be empty")
	private String grandChild;
	/** 状況 */
	@NotEmpty(message = "error: may not be empty")
	private String condition;
	/** 商品詳細 */
	@NotBlank(message = "error: may not be empty")
	private String description;

	@Override
	public String toString() {
		return "AddItemForm [name=" + name + ", price=" + price + ", brand=" + brand + ", parentCategory="
				+ parentCategory + ", childCategory=" + childCategory + ", grandChild=" + grandChild + ", condition="
				+ condition + ", description=" + description + "]";
	}

	/**
	 * String型のpriceをInteger型にして返します.
	 * 
	 * @return Integer型のpriceデータ
	 */
	public Integer getIntPrice() {
		if(price.equals("")) {
			return null;
		}
		return Integer.parseInt(this.price);
	}
	
	public Integer getIntCondition() {
		if(condition.equals("")) {
			return null;
		}
		
		return Integer.parseInt(this.condition);
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

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
