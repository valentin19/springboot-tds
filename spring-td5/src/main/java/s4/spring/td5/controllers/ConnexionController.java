package s4.spring.td5.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.PostMapping;

import s4.spring.td5.entities.User;
import s4.spring.td5.repositoties.UserRepository;

@Controller
@RequestMapping("/")
public class ConnexionController {
	
	private User user;
	
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping("index")
	public String index(ModelMap model)
	{
		if(user == null)
		{
			return "index";
		}
		else
		{
			return "script";
		}
	}
	

	@PostMapping("login")
	public RedirectView login(@RequestParam("email") String email, @RequestParam("password") String password)
	{
		User user1 = userRepo.findOneByEmail(email);
		
		if(user1 != null && user1.getPassword().equals(password))
		{
			user = user1;
			//HttpSession.setAttribute("user", user);
		}
		
		return new RedirectView("index");
	}
	
	@RequestMapping("logout")
	public RedirectView logout()
	{
		user = null;
		return new RedirectView("index");
	}

}
