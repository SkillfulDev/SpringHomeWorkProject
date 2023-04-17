package ua.chernonog.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.chernonog.springcourse.dao.PersonDAO;
import ua.chernonog.springcourse.models.Person;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;

    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String showPeople(Model model){
        model.addAttribute("people",personDAO.showPeople());
        return "people/hello";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDAO.getPerson(id));
        return "/people/person";
    }
    @GetMapping("/new")
    public String newPersonPage(Model model){
        model.addAttribute("person", new Person());
        return "/people/new";
    }


}
