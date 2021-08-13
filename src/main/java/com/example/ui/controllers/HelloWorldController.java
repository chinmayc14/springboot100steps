package com.example.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ui.models.UserDetails;

@RestController
@RequestMapping("/")
public class HelloWorldController {

	@Autowired
	private ResourceBundleMessageSource resourceBundleMessageSource;

	@GetMapping
	public String sayHello() {
		return "Hello world";
	}

	@GetMapping("/bean")
	public UserDetails sayHelloBean() {
		UserDetails bean = new UserDetails();
		bean.setFirstName("chin");
		bean.setLastName("chau");
		bean.setCity("faiz");
		return bean;
	}

	@GetMapping("/locale")
	public String getLocale() {
		return resourceBundleMessageSource.getMessage("locale.hello", null, LocaleContextHolder.getLocale());
	}

}
