package br.com.devmedia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping("/bemvindo")
	public ModelAndView bemVindo(Model model) {
		model.addAttribute("bemvindo", "Olha só que facil dizer bem vindo");
		return new ModelAndView("bemvindo");
	}
}
