package com.spring.webProject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	///menubar_top
	@RequestMapping(value = "/menubar_top", method = RequestMethod.GET)
	public String menubar_top(Locale locale, Model model) {
			
		return "menubar_top";
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Locale locale, Model model) {
			
		return "/main";
	}
	
	@RequestMapping(value = "/infobar_bottom", method = RequestMethod.GET)
	public String infobar_bottom(Locale locale, Model model) {
		
		return "infobar_bottom";
	}
	//brand_about
	@RequestMapping(value = "/brand_about", method = RequestMethod.GET)
	public String brand_about(Locale locale, Model model) {
		System.out.println("check");
		return "brand/about";
	}
}
