package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * 商品更新情報のリクエストパラメータを受け取るフォーム.
 * 
 * @author masashi.nose
 *
 */
public class UpdateItemForm {

	/** 商品名 */
	@NotBlank
	private String name;
	/** 価格 */
	@NotBlank
	private String price;
	/** ブランド名 */
	@NotBlank
	private String brand;
	/** 状況 */
	@NotEmpty
	private String condition;
	/** 詳細 */
	@NotBlank
	private String description;

	@Override
	public String toString() {
		return "UpdateItemForm [name=" + name + ", price=" + price + ", brand=" + brand + ", condition=" + condition
				+ ", description=" + description + "]";
	}

	/**
	 * 
	 * priceをInteger型にして返します.
	 * 
	 * @return
	 */
	public Integer getIntprice() {
		return Integer.parseInt(this.price);

	}

	/**
	 * conditionをInteger型にして返します.
	 * 
	 * @return
	 */
	public Integer getIntCondition() {
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
