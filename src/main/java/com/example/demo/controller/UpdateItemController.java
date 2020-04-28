package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Item;
import com.example.demo.form.SearchByItemNameForm;
import com.example.demo.form.UpdateItemForm;
import com.example.demo.service.ShowItemDetailService;
import com.example.demo.service.ShowItemListService;
import com.example.demo.service.UpdateItemService;

/**
 * itemsテーブルを更新するコントローラ.
 * 
 * @author masashi.nose
 *
 */
@Controller
@RequestMapping("")
public class UpdateItemController {

	@Autowired
	private UpdateItemService updateItemService;

	@Autowired
	private ShowItemListController showItemListController;

	@Autowired
	private ShowItemDetailService ShowItemDetailService;

	@ModelAttribute
	private UpdateItemForm setUpForm() {
		return new UpdateItemForm();
	}

	@ModelAttribute
	private SearchByItemNameForm setUpNameForm() {
		return new SearchByItemNameForm();

	}

	/**
	 * 
	 * 商品情報更新画面に遷移します.
	 * 
	 * @return 
	 */
	@RequestMapping("/toEdit")
	public String toUpdate(Model model, Integer id) {
		Item item = ShowItemDetailService.showItemDetail(id);
		
		model.addAttribute("item", item);
		return "edit";
	}

	/**
	 * 
	 * itemsテーブルの商品情報を更新します. 更新後、商品一覧画面へ遷移します.
	 * 
	 * @param form   リクエストパラメータ
	 * @param result
	 * @param model  リクエストスコープ作成
	 * @return 商品一覧画面
	 */
	@RequestMapping("/edit")
	public String update(@Validated UpdateItemForm form, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return showItemListController.showItemList(model);
//		}

		Item item = new Item();
		item.setName(form.getName());
		item.setPrice(form.getIntprice());
		item.setBrand(form.getBrand());
		item.setCondition(form.getIntCondition());
		item.setDescription(form.getDescription());

		updateItemService.update(item);

		return showItemListController.showItemList(model);
	}

}
