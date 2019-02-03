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

    @ModelAttribute("items")
    public List<Item> getItems()
    {
        List<Item> items = new ArrayList<>();
        return items;
    }

    @RequestMapping("/")
    public String index(ModelMap model)
    {
        model.addAttribute("item", "test");
        return "index";
    }

    @RequestMapping("new")
    public String newItem()
    {
        return "newItem";
    }

    @PostMapping("addNew")
    public RedirectView addNew(@RequestParam("nom") String nom, @RequestParam("evaluation") int evaluation) {
        Item item = new Item();
        item.setNom(nom);
        item.setEvaluation(evaluation);
        getItems().add(item);
        return new RedirectView("/td0/");
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
