package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller()

public class IndexController extends MyController {
	/**
	 * 首页
	 * @param model
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/index")
	public String index(Model model) throws Exception {
		return "public/index";
	}
}
