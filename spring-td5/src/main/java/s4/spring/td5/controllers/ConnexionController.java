package s4.spring.td5.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.PostMapping;

import s4.spring.td5.entities.Script;
import s4.spring.td5.entities.User;
import s4.spring.td5.repositoties.ScriptRepository;
import s4.spring.td5.repositoties.UserRepository;

@Controller
@RequestMapping("/")
public class ConnexionController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ScriptRepository scriptRepo;
	
	@RequestMapping("index")
	public String index(ModelMap model, HttpSession session)
	{
		User user = (User)session.getAttribute("user");
		model.put("user", user);
		
		List<Script> lesScripts = scriptRepo.findByUser(user);
		
		model.put("scripts", lesScripts);
		
		return "index";
	}

	@PostMapping("login")
	public RedirectView login(HttpSession session, @RequestParam("login") String login, @RequestParam("password") String password)
	{
		User user = userRepo.findOneByLogin(login);
		
		if(user != null && user.getPassword().equals(password))
		{
			session.setAttribute("user", user);
		}
		
		return new RedirectView("index");
	}
	
	@RequestMapping("logout")
	public RedirectView logout(HttpSession session)
	{
		session.setAttribute("user", null);
		return new RedirectView("index");
	}

}
