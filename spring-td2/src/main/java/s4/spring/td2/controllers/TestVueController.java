package s4.spring.td2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import s4.spring.td2.entities.Organisation;
import s4.spring.td2.repositories.OrgasRepository;

@Controller
@RequestMapping("/vue/")
public class TestVueController {

	@Autowired
	private VueJS vue;
	private OrgasRepository orgasRepo;
	
	@RequestMapping("test")
	public String index(Model model)
	{
		model.addAttribute("vue", vue);
		vue.addData("message", "Hello World");
		vue.addMethod("update", "this.message='Message modifié';");
		vue.addMethod("testAjax", "var self=this;" + Http.post("/vue/test/ajax", "{v:self.inputValue}", "self.ajaxMessage=response.data;self.alertVisible=true;"));
		vue.addData("alertVisible", false);
		vue.addData("ajaxMessage");
		vue.addData("inputValue");
		return "vueJs/test";
	}
	
	@PostMapping("test/ajax")
	@ResponseBody
	public String testAjax(@RequestBody String v)
	{
		return "test ok " + v;
	}
	
	@RequestMapping("orgas")
	public String geneSpaOrgas(Model model)
	{
		model.addAttribute("vue", vue);
		List<Organisation> orgas = orgasRepo.findAll();
		
		vue.addData("orgas", orgas);
		vue.addData("headers", "[{text:'Name', value:'name'},{text:'Domain', value:'domain'},{text:'Aliases', value:'aliases'}]");
		vue.addDataRaw("dialog", "false");
		vue.addDataRaw("editedItem", "{}");
		vue.addDataRaw("editedIndex", "-1");
		vue.addComputed("formTitle", "(this.itemIndex==-1)?'Nouvelle orga':'Modification orga'");
		vue.addMethod("close", "this.dialog=false;");
		vue.addMethod("save", "", "orga");

		return "vueJs/index";
	}
}
