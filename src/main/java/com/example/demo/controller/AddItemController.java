package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.form.AddItemForm;
import com.example.demo.form.SearchByItemNameForm;
import com.example.demo.service.AddItemService;
import com.example.demo.service.ShowItemListService;

/**
 * 
 * 商品追加に関するコントローラ.
 * 
 * @author masashi.nose
 *
 */
@Controller
@RequestMapping("")
public class AddItemController {

	@Autowired
	private AddItemService addItemService;

	@Autowired
	private ShowItemListService showItemListService;

	@Autowired
	private ShowItemListController showItemListController;

	@ModelAttribute
	public AddItemForm setUpForm() {
		return new AddItemForm();

	}

	@ModelAttribute
	public SearchByItemNameForm setUpNameForm() {
		return new SearchByItemNameForm();

	}

	/**
	 * 商品追加画面へ遷移します.
	 * 
	 * @param model リクエストスコープ作成
	 * @return 商品追加画面
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Model model) {
		List<Category> parentCategoryList = showItemListService.findParentCategoryList();
		List<Category> childCategoryList = showItemListService.findGrandChildCategoryList();
		List<Category> grandChildCategoryList = showItemListService.findGrandChildCategoryList();

		model.addAttribute("parentList", parentCategoryList);
		model.addAttribute("childList", childCategoryList);
		model.addAttribute("grandChildList", grandChildCategoryList);

		return "add";
	}

	/**
	 * 
	 * 商品を追加します.
	 * 
	 * @param form   リクエストパラメータ
	 * @param result
	 * @param model  リクエストスコープ作成
	 * @return 商品一覧画面
	 */
	@RequestMapping("/add")
	public String add(@Validated AddItemForm form, BindingResult result, Model model) {
//		
//		if (result.hasErrors()) {
//			return toAdd(model);
//		}

		Item item = new Item();

		item.setName(form.getName());
		item.setPrice(form.getIntPrice());
		item.setBrand(form.getBrand());
		item.setCondition(form.getIntCondition());
		item.setDescription(form.getDescription());

		addItemService.addItem(item);

		return "redirect:/finish";

	}
	
	@RequestMapping("/finish")
	public String finish() {
		return "add_finish";
	}

}
