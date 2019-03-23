package s4.spring.td5.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/script/")
public class ScriptController {
	
	@RequestMapping("new")
	public String newScript()
	{
		return "script";
	}

}
