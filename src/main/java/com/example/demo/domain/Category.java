package com.example.demo.domain;

/**
 * 
 * categoryテーブルのカラム情報を持つドメイン.
 * 
 * @author masashi.nose
 *
 */
public class Category {

	/** ID */
	private Integer id;
	/** 親 */
	private Integer parent;
	/** カテゴリー名 */
	private String name;
	/** 全カテゴリー */
	private String nameAll;

	@Override
	public String toString() {
		return "Category [id=" + id + ", parent=" + parent + ", name=" + name + ", nameAll=" + nameAll + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameAll() {
		return nameAll;
	}

	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}

}
