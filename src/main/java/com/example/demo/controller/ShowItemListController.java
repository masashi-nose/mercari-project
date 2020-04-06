package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Item;
import com.example.demo.service.ShowItemListService;

/**
 * 商品一覧ページを表示させるコントローラ.
 * 
 * @author masashi.nose
 *
 */
@Controller
@RequestMapping("")
public class ShowItemListController {

	@Autowired
	private ShowItemListService showItemListService;

	/**
	 * 商品一覧ページへ遷移します.
	 * 
	 * @param model リクエストスコープ作成
	 * @return 商品一覧ページ
	 */
	@RequestMapping("")
	public String showItemList(Model model) {
		List<Item> itemList = showItemListService.showItemList();
		model.addAttribute("itemList", itemList);
		return "item_list";

	}

}
