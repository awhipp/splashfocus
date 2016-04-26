package com.jsearch.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsearch.bean.User;

@Controller
public class LoginController {

	@RequestMapping("login")
	public ModelAndView getLoginForm(Principal principal, @RequestParam(required = false) String authfailed) {
		if (principal == null) {
			String message = "";
			if (authfailed != null) {
				if (authfailed.length() >= 1) {
					message = authfailed;
				} else {
					message = "Invalid username of password, try again!";
				}
			}
			return new ModelAndView("login", "message", message);
		} else {
			User u = new User(principal.getName());
			return new ModelAndView("redirect:/dashboard", "user", u);
		}
	}

}
