package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Item;
import com.example.demo.service.ShowItemDetailService;

/**
 * 商品詳細ページを操作するコントローラ.
 * 
 * @author masashi.nose
 *
 */
@Controller
@RequestMapping("/detail")
public class ShowItemDetailController {

	@Autowired
	private ShowItemDetailService showItemDetailService;

	/**
	 * 
	 * 商品詳細ページへ遷移します.
	 * 
	 * @param id    商品ID
	 * @param model リクエストスコープ作成
	 * @return 商品詳細ページ
	 */
	@RequestMapping("/showDetail")
	public String showItemDetail(Integer id, Model model) {
		Item item = showItemDetailService.showItemDetail(id);
		model.addAttribute("item", item);
		return "item_detail";

	}

}
