package s4.spring.td2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import s4.spring.td2.entities.Groupe;
import s4.spring.td2.entities.Organisation;
import s4.spring.td2.repositories.GroupeRepository;
import s4.spring.td2.repositories.OrgasRepository;

@Controller
@RequestMapping("/orgas/")
public class OrgasController {

	@Autowired
	private OrgasRepository orgasRepo;
	
	@RequestMapping("create")
	@ResponseBody
	public String createOrg()
	{
		Organisation org = new Organisation();
		org.setName("IUT Ifs");
		org.setDomain("unicaen.Fr");
		org.setAliases("iutc3.unicaen.Fr");
		orgasRepo.save(org);
		
		return org.getName() + " à bien été ajouté dans la bdd";
	}
	
	@RequestMapping("create/groupes")
	@ResponseBody
	public String createOrgaWithGroupes()
	{
		Organisation org = new Organisation();
		org.setName("IUT Ifs");
		org.setDomain("unicaen.Fr");
		org.setAliases("iutc3.unicaen.Fr");
		
		Groupe groupe = new Groupe();
		groupe.setName("etudiant");
		
		org.addGroupe(groupe);
		
		orgasRepo.save(org);
		
		return org.getName() + " à bien été ajouté dans la bdd";
	}
	
	@RequestMapping("create/groupe")
	@ResponseBody
	public String createGroupe(GroupeRepository groupesRepo)
	{
		Groupe groupe = new Groupe();
		groupesRepo.save(groupe);
		
		return "groupe crée";
	}
	
	@RequestMapping("index")
	public String index(ModelMap model)
	{
		model.addAttribute("orgas", orgasRepo.findAll());
		return "index";
	}
	
	@RequestMapping("new")
	public String newOrg()
	{
		return "newOrg";
	}
	
	@RequestMapping("edit/{id}")
	public String editOrg(ModelMap model, @PathVariable int id) throws IllegalAccessException
	{
		if(orgasRepo.findById(id) == null)
		{
			throw new IllegalAccessException("id incorrect");
		}
		
		model.addAttribute("org", orgasRepo.findById(id).get());
		return "editOrg";
	}
	
}
