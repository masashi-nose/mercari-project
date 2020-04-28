package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

/**
 * 
 * 商品情報を更新する業務処理を行うサービスクラス.
 * 
 * @author masashi.nose
 *
 */
@Service
@Transactional
public class UpdateItemService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * itemsテーブルの商品情報を更新します.
	 * 
	 * @param item 商品情報オブジェクト
	 */
	public void update(Item item) {
		itemRepository.update(item);
	}
}
