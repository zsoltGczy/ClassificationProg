package com.classproject.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.classproject.service.UserService;
import com.classproject.usersandroles.User;

@Controller
public class RegistrationController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	
	@RequestMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "auth/registration";
	}

			@PostMapping("/reg")
			public String registrate(@Valid @ModelAttribute User user, BindingResult result, Model model) {
				
				String registerUser = userService.registerUser(user);
				if(result.hasErrors() || !registerUser.equals("ok")) {
					model.addAttribute("registerUser", registerUser);
					return "auth/registration";
				}
				
				log.debug(user.getEmail());
				return "auth/login";
			}

	
			
	@RequestMapping("/activation/{code}")
	public String activation(@PathVariable("code") String code, HttpServletResponse response, Model model) {
		String activation = userService.userActivation(code);
		if(activation.equals("noresult")) {
			model.addAttribute("activation", activation);
		}
		return "auth/login";
	}

}