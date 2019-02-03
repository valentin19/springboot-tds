package s4.spring.td0.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td0.models.Item;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("/items")
public class ItemsController {
	List<Item> items = new ArrayList<>();
    
	@ModelAttribute("items")
    public List<Item> getItems()
    {
        return items;
    }

    @RequestMapping("items/")
    public String index(ModelMap model)
    {
    	model.addAttribute("items", getItems());
        return "index";
    }

    @RequestMapping("items/new")
    public String newItem()
    {
        return "newItem";
    }

    @PostMapping("items/addNew")
    public RedirectView addNew(@RequestParam("nom") String nom, @RequestParam("evaluation") int evaluation) {
        Item item = new Item();
        item.setNom(nom);
        item.setEvaluation(evaluation);
        getItems().add(item);
        return new RedirectView("/td0/items/");
    }

    @PostMapping("addNewBis")
    public RedirectView addNewBis(HttpServletRequest request) {
        String nom = request.getParameter("nom");
        int eval = Integer.valueOf(request.getParameter("evaluation"));

        Item item = new Item();
        item.setNom(nom);
        item.setEvaluation(eval);
        getItems().add(item);
        return new RedirectView("/items");
    }
    
    @RequestMapping("items/inc/{nom}")
    public RedirectView incrementEvaluation(@PathVariable String nom)
    {
    	for(Item item : getItems())
    	{
    		if(item.getNom().equals(nom))
    		{
    			item.setEvaluation(item.getEvaluation()+1);
    		}
    	}
    	return new RedirectView("/td0/items/");
    }
    
    @RequestMapping("items/dec/{nom}")
    public RedirectView decrementetEvaluation(@PathVariable String nom)
    {
    	for(Item item : getItems())
    	{
    		if(item.getNom().equals(nom))
    		{
    			item.setEvaluation(item.getEvaluation()-1);
    		}
    	}
    	return new RedirectView("/td0/items/");
    }
    
    @RequestMapping("items/delete/{index}")
    public RedirectView deleteItem(@PathVariable int index)
    {
    	getItems().remove(getItems().get(index));
    	return new RedirectView("/td0/items/");
    }

	/*@RequestMapping("/new")
	public String (@ModelAttribute("items") List<Item> items,ModelMap model)
	{
		Item elm = new Item();
		elm.setNom("Test");
		items.add(elm);
		model.put("tt", 55);
		return "index";
	}*/
}
