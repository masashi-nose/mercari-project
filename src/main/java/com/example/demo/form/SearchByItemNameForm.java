package com.example.demo.form;

/**
 * 
 * 商品名の曖昧検索画面からリクエストパラメータを受け取るフォーム.
 * 
 * @author masashi.nose
 *
 */
public class SearchByItemNameForm {

	private String name;

	@Override
	public String toString() {
		return "SearchByItemNameForm [name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
