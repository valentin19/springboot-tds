package s4.spring.td5.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import s4.spring.td5.entities.History;
import s4.spring.td5.entities.Script;
import s4.spring.td5.entities.User;
import s4.spring.td5.repositoties.CategoryRepository;
import s4.spring.td5.repositoties.HistoryRepository;
import s4.spring.td5.repositoties.LanguageRepository;
import s4.spring.td5.repositoties.ScriptRepository;

@Controller
@RequestMapping("/script/")
public class ScriptController {
	
	@Autowired
	private LanguageRepository languageRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ScriptRepository scriptRepo;
	@Autowired
	private HistoryRepository historyRepo;
	
	@RequestMapping("new")
	public String newScript(HttpSession session, ModelMap model)
	{
		model.put("categories", categoryRepo.findAll());
		model.put("languages", languageRepo.findAll());
		model.put("user", session.getAttribute("user"));
		return "script/new";
	}
	
	@PostMapping("submit")
	public RedirectView submitScript(HttpSession session, 
			@RequestParam("id") int id, 
			@RequestParam("title") String title, 
			@RequestParam("description") String description, 
			@RequestParam("content") String content, 
			@RequestParam("categorie") int categorie, 
			@RequestParam("language") int language)
	{
		User user = (User) session.getAttribute("user");
		
		Script script;
		if(id == 0)
		{
			script = new Script();
			script.setUser(user);
			script.setCreationDate(new Date());
		}
		else
		{
			script = scriptRepo.findOneById(id);
			
			History history = new History();
			history.setContent(content);
			history.setDate(new Date());
			history.setScript(script);
			
			historyRepo.save(history);
		}
		
		script.setTitle(title);
		script.setDescription(description);
		script.setContent(content);
		script.setCategory(categoryRepo.findOneById(categorie));
		script.setLanguage(languageRepo.findOneById(language));
		
		scriptRepo.save(script);
		
		return new RedirectView("/index");
	}
	
	@RequestMapping("{id}")
	public String editScript(HttpSession session, ModelMap model, @PathVariable int id)
	{	
		model.put("categories", categoryRepo.findAll());
		model.put("languages", languageRepo.findAll());
		model.put("user", session.getAttribute("user"));
		
		Script script = scriptRepo.findOneById(id);
		
		model.put("script", script);
		
		return "script/edit";
	}
	
	@RequestMapping("delete/{id}")
	public RedirectView delete(@PathVariable int id)
	{
		Script script = scriptRepo.findOneById(id);
		scriptRepo.delete(script);
		return new RedirectView("/index");
	}

}
