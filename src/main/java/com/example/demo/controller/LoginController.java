package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.form.LoginUserForm;

/**
 * @author masashi.nose
 *
 */
@Controller
@RequestMapping("")
public class LoginController {

	@ModelAttribute
	public LoginUserForm setUpForm() {
		return new LoginUserForm();
	}

	@Autowired
	private HttpSession session;

	@RequestMapping("/toLogin")
	public String toLogin(Model model, @RequestParam(required = false) String error) {

		if (error != null) {
			model.addAttribute("errorMessage", "メールアドレス、またはパスワードが違います。");
		}
		return "login";
	}

	/**
	 * 
	 * ログアウトします.
	 * 
	 * @return 
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/toLogin";
	}

}
