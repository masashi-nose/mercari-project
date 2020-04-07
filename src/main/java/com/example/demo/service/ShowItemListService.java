package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

/**
 * 商品一覧表示の業務処理を行うサービスクラス.
 * 
 * @author masashi.nose
 *
 */
@Service
@Transactional
public class ShowItemListService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * 
	 * 商品一覧を表示します.
	 * 
	 * @return 商品情報が詰まったオブジェクトのリスト
	 */
	public List<Item> showItemList() {
		return itemRepository.findAll();

	}

	/**
	 * 商品名で曖昧検索します.
	 * 
	 * @param name 商品名
	 * @return 商品情報が詰まったオブジェクトのリスト
	 */
	public List<Item> findByItemName(String name, String brand) {
		return itemRepository.findByItemName(name, brand);

	}

	/**
	 * categoryテーブルから親カテゴリ情報を検索します.
	 * 
	 * @return 親カテゴリ情報の詰まったオブジェクトリスト
	 */
	public List<Category> parentCategoryList() {
		return categoryRepository.parentCategoryList();

	}

	/**
	 * categoryテーブルから子カテゴリ情報を検索します.
	 * 
	 * @return 子カテゴリ情報の詰まったオブジェクトリスト
	 */
	public List<Category> childCategoryList() {
		return categoryRepository.childCategoryList();

	}
	
	/**
	 * categoryテーブルから孫カテゴリ情報を検索します.
	 * 
	 * @return　孫カテゴリ情報の詰まったオブジェクトリスト
	 */
	public List<Category> grandChildCategoryList(){
		return categoryRepository.grandChildCategoryList();
		
	}

}
