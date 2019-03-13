package s4.spring.td2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.jeemv.springboot.vuejs.VueJS;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class TestCompo {

	@Autowired
	private VueJS vue;
	
	@GetMapping("testCompo")
	public String index(Model model) {
		model.addAttribute("vue", vue);
		vue.addData("msg", "Voulez-vous afficher une alerte ?");
		vue.addMethod("validate", "alert('validation !');");
		
		vue.addDataRaw("items", "[{text:'Nom',value:'nom'}]");
		vue.addDataRaw("headers", "[{text:'Nom',value:'nom'}]");
		return "vueJs/index2";
	}
	
}
