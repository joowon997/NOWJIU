package com.nowjoo.nowjiu.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nowjoo.nowjiu.main.dto.MainDto;
import com.nowjoo.nowjiu.main.service.MainService;

@Controller
@RequestMapping("/nowjiu")
public class MainController {
	
	private MainService mainService;
	
	public MainController(MainService mainService) {
		this.mainService = mainService;
	}
	
	@GetMapping("/main")
	public String main(
			Model model){
		
		MainDto mainDto = mainService.mainview();
		
		model.addAttribute("main", mainDto);
		
		return "user/main";
	}
}
