package com.example.demo.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.User;
import com.example.demo.form.UserRegisterForm;
import com.example.demo.service.UserRegisterService;

/**
 * usersテーブルを操作するコントローラ.
 * 
 * @author masashi.nose
 *
 */
@Controller
@RequestMapping("")
public class UserRegisterController {

	@Autowired
	private UserRegisterService userRegisterService;

	@ModelAttribute
	public UserRegisterForm setUpForm() {
		return new UserRegisterForm();
	}

	/**
	 * 登録画面へ遷移します.
	 * 
	 * @return 登録画面
	 */
	@RequestMapping("/toRegister")
	public String toRegister() {
		return "register";
	}

	/**
	 * ユーザー登録を行います. 登録後、ログイン画面へ遷移します.
	 * 
	 * @param form
	 * @param result
	 * @param model
	 * @return ログイン画面
	 */
	@RequestMapping("/register")
	public String register(@Validated UserRegisterForm form, BindingResult result, Model model) {
		System.out.println("フォームのパスワード" + form.getPassword());
		System.out.println("フォームの確認パスワード" + form.getConfirmationPassword());
		
		User findByMailAddressUser = userRegisterService.findByMailAddress(form.getMailAddress());
		
		if(findByMailAddressUser != null) {
			result.rejectValue("mailAddress", "", "This email-address has been already registered.");
		}
		
		if(!(form.getPassword().equals(form.getConfirmationPassword()))) {
			result.rejectValue("password", "", "Password & confirmation password not match.");
		}
		
		if (result.hasErrors()) {
			return toRegister();
		}

		User user = new User();
		BeanUtils.copyProperties(form, user);

		userRegisterService.register(user);

		return "redirect:/toLogin";
	}

}
