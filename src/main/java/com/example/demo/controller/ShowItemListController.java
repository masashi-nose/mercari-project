package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Category;
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

	@ModelAttribute
	public SearchByItemNameForm setUpForm() {
		return new SearchByItemNameForm();
	}

	/**
	 * 商品一覧ページへ遷移します.
	 * 
	 * @param model リクエストスコープ作成
	 * @return 商品一覧ページ
	 */
	@RequestMapping("")
	public String showItemList(Model model) {
		List<Item> itemList = showItemListService.showItemList();

//		カテゴリ検索用に親、子、孫カテゴリをそれぞれ検索し、リクエストスコープに格納します.
		List<Category> parentCategoryList = showItemListService.findParentCategoryList();
		List<Category> childCategoryList = showItemListService.findGrandChildCategoryList();
		List<Category> grandChildCategoryList = showItemListService.findGrandChildCategoryList();
		
		model.addAttribute("parentList", parentCategoryList);
		model.addAttribute("childList", childCategoryList);
		model.addAttribute("grandChildList", grandChildCategoryList);
		
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
	@RequestMapping("/search")
	public String SearchByItemName(SearchByItemNameForm form, Model model) {
		List<Item> itemList = showItemListService.findByItemName(form.getName(), form.getBrand());

		if (itemList.size() == 0) {

			model.addAttribute("message", "NO ITEM FOUND");
			return showItemList(model);

		}

		model.addAttribute("itemList", itemList);

		return "item_list";

	}
}
