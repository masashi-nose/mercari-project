package com.example.demo.form;

/**
 * 
 * 商品名の曖昧検索画面からリクエストパラメータを受け取るフォーム.
 * 
 * @author masashi.nose
 *
 */
public class SearchByItemNameForm {

	/** 商品名 */
	private String name;
	/** ブランド */
	private String brand;

	@Override
	public String toString() {
		return "SearchByItemNameForm [name=" + name + ", brand=" + brand + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
