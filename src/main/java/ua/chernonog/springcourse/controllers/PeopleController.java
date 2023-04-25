package ua.chernonog.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.chernonog.springcourse.models.Person;
import ua.chernonog.springcourse.services.PeopleService;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("people", peopleService.findAll());

        return "people/hello";

    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());

        return "people/newPerson";
    }

    @PostMapping()
    public String savePerson(@ModelAttribute("person") Person person) {
        peopleService.save(person);

        return "redirect:people";
    }
    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") int id , Model model){
        model.addAttribute("person", peopleService.getPersonById(id));

        return "people/edit";
    }
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        peopleService.deletePersonById(id);

        return "redirect:/people";
    }

    @PatchMapping("/{id}")
    public String changePerson(@PathVariable("id") int id,@ModelAttribute("person") Person person){
        peopleService.changePerson(id,person);

        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String changePage(@PathVariable("id") int id,Model model){
        model.addAttribute("person",peopleService.getPersonById(id));

        return "people/editPage";
    }
}
