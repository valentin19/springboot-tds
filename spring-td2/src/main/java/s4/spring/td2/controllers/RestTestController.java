package s4.spring.td2.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import s4.spring.td2.entities.Organisation;
import s4.spring.td2.repositories.OrgasRepository;

@RestController
public class RestTestController {
	
	@Autowired
	private OrgasRepository orgasRepo;
	
	@ResponseBody
	@GetMapping("/orgas")
	public List<Organisation> get(){
		return orgasRepo.findAll();
	}
	
	@ResponseBody
	@PostMapping("/create")
	public Organisation post(@RequestBody Organisation orga) {
		return orgasRepo.saveAndFlush(orga);
	}
	
	@PostMapping("/rest/orgas/create")
	public Organisation create() {
		return null;
	}
}
