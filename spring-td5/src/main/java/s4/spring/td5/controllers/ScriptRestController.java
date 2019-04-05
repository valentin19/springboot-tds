package s4.spring.td5.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.td5.entities.Script;
import s4.spring.td5.repositoties.ScriptRepository;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/rest/")
public class ScriptRestController {
	
	@Autowired
	private ScriptRepository scriptRepo;
	
	@ResponseBody
	@GetMapping("search")
	public List<Script> search(@RequestBody int idUser, @RequestBody String champs)
	{
		List<Script> scripts = scriptRepo.findAll();
		List<Script> scriptsFinaux = null;
		
		for(Script script : scripts)
		{
			if(script.getUser().getId() == idUser)
			{
				if(script.getTitle() == champs)
				{
					scriptsFinaux.add(script);
				}
				else if(script.getDescription() == champs)
				{
					scriptsFinaux.add(script);
				}
				else if(script.getContent() == champs)
				{
					scriptsFinaux.add(script);
				}
			}
		}
		
		return scriptsFinaux;
	}

}
