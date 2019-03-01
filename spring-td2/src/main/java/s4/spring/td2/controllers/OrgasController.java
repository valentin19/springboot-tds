package s4.spring.td2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

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
	
	@PostMapping("new")
	public RedirectView addNew(
			@RequestParam("name") String name, 
			@RequestParam("domain") String domain, 
			@RequestParam("aliases") String aliases) {
		
		if(name != "" && domain != "" && aliases != "")
		{
			Organisation org = new Organisation();
			org.setName(name);
			org.setDomain(domain);
			org.setAliases(aliases);
			orgasRepo.save(org);
		}
		
		return new RedirectView("/orgas/index");
	}
	
	@RequestMapping("new")
	public String newOrg()
	{
		return "newOrg";
	}
	
	@RequestMapping("edit/{id}")
	public String editOrg(ModelMap model, @PathVariable int id) throws IllegalAccessException
	{
		Organisation org = orgasRepo.findOneById(id);
		if(org == null)
		{
			throw new IllegalAccessException("id incorrect");
		}
		
		model.addAttribute("org", org);
		return "editOrg";
	}
	
	@PostMapping("edit/{id}")
	public RedirectView editOrg(
			@RequestParam("name") String name, 
			@RequestParam("domain") String domain, 
			@RequestParam("aliases") String aliases, @PathVariable int id) {
		
		if(name != "" && domain != "" && aliases != "")
		{
			Organisation org = orgasRepo.findOneById(id);
			org.setName(name);
			org.setDomain(domain);
			org.setAliases(aliases);
			orgasRepo.save(org);
		}
		
		return new RedirectView("/orgas/index");
	}
	
	@RequestMapping("display/{id}")
	public String displayOrg(ModelMap model, @PathVariable int id) throws IllegalAccessException
	{
		Organisation org = orgasRepo.findOneById(id);
		if(org == null)
		{
			throw new IllegalAccessException("id incorrect");
		}
		
		model.addAttribute("org", org);
		return "displayOrg";
	}
	
	@RequestMapping("delete/{id}")
	public String deleteOrg(ModelMap model, @PathVariable int id) throws IllegalAccessException
	{
		Organisation org = orgasRepo.findOneById(id);
		if(org == null)
		{
			throw new IllegalAccessException("id incorrect");
		}
		
		model.addAttribute("orgas", orgasRepo.findAll());
		model.addAttribute("org", org);
		return "index";
	}
	
	@RequestMapping("confirmDelete/{id}")
	public RedirectView confirmDeleteOrg(ModelMap model, @PathVariable int id) throws IllegalAccessException
	{
		Organisation org = orgasRepo.findOneById(id);
		if(org == null)
		{
			throw new IllegalAccessException("id incorrect");
		}
		
		orgasRepo.delete(org);
		return new RedirectView("/orgas/index");
	}
	
	@RequestMapping("search")
	public String search()
	{
		return "index";
	}
	
}
