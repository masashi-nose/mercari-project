package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Item;
import com.example.demo.form.SearchByItemNameForm;
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

	/**
	 * 商品名で曖昧検索し、検索結果を表示します.
	 * 
	 * @param form   リクエストパラメータ
	 * @param result
	 * @param model  リクエストスコープ作成
	 * @return 検索結果画面
	 */
	public String SearchByItemName(SearchByItemNameForm form, BindingResult result, Model model) {
		List<Item> itemList = showItemListService.findByItemName(form.getName());

		if (itemList == null) {
			String itemName = form.getName();
			result.rejectValue("name", "", "No results for");

			model.addAttribute("itemName", itemName);
			return showItemList(model);

		}

		model.addAttribute("itemList", itemList);

		return "item_list";

	}
}
