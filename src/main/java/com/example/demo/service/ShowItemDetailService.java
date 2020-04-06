package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

/**
 * 
 * 商品詳細表示の業務処理を行うサービスクラス.
 * 
 * @author masashi.nose
 *
 */
@Service
@Transactional
public class ShowItemDetailService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 商品IDで商品を一件検索します.
	 * 
	 * @param id 商品ID
	 * @return　商品詳細情報の詰まったオブジェクト
	 */
	public Item showItemDetail(Integer id) {
		return itemRepository.load(id);
	}

}
