package s4.spring.td2.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
 
import s4.spring.td2.entities.Organisation;
import s4.spring.td2.repositories.OrgasRepository;

@RestController
@RequestMapping("/rest/orgas")
public class RestTestController {
	
	@Autowired
	private OrgasRepository orgasRepo;
	
	@ResponseBody
	@GetMapping("/")
	public List<Organisation> read(){
		return orgasRepo.findAll();
	}
	
	@ResponseBody
	@GetMapping("/orgas/{id}")
	public Organisation read(@PathVariable int id){
		return orgasRepo.findOneById(id);
	}
	
	@PostMapping("/create")
	public Organisation create(@RequestBody Organisation orga) {
		return orgasRepo.saveAndFlush(orga);
	}
	
	@PutMapping("/update")
	public Organisation update(@RequestBody Organisation orga) {
		return orgasRepo.saveAndFlush(orga);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestBody Organisation orga) {
		orgasRepo.delete(orga);
	}
	
}
