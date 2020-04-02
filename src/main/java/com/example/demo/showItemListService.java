package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

/**
 * 商品一覧表示の業務処理を行うサービスクラス.
 * 
 * @author masashi.nose
 *
 */
@Service
@Transactional
public class showItemListService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 
	 * 商品一覧を表示します.
	 * 
	 * @return 商品情報が詰まったオブジェクトのリスト
	 */
	public List<Item> itemList() {
		return itemRepository.findAll();

	}
}
