package com.stacksimplify.restservices.hello;

import java.util.Locale;

import org.apache.tomcat.util.http.parser.AcceptLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloController {
	
	@Autowired
	ResourceBundleMessageSource messageSource;
	
	//URI - /helloworld
	//Method- Get
	//@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld() {
		return "Hello World1";
	}
	
	// returns a bean
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("John", "Scaria", "Bangalore");
	}
	
	@GetMapping("/hello-int")
	public String helloI18NFormat(@RequestHeader(name="Accept-Language", required=false) String locale) {
		//return "Hello World1 I18N";
		return messageSource.getMessage("label.hello", null, new Locale(locale));
	}

	@GetMapping("/hello-int2")
	public String helloI18NFormat() {
		//return "Hello World1 I18N";
		return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
	}
}
