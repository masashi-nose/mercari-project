package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

/**
 * itemsテーブルに商品情報を追加する業務処理を行うサービスクラス.
 * 
 * @author masashi.nose
 *
 */
@Service
@Transactional
public class AddItemService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * DBのitemsテーブルに商品情報を追加します.
	 * 
	 * @param item 商品情報が詰まったオブジェクト
	 * @return　IDが自動採番されたitemオブジェクト
	 */
	public Item addItemToDb(Item item) {
		return itemRepository.insert(item);

	}

}
