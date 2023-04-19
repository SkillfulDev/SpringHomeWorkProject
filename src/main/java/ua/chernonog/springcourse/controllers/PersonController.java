package ua.chernonog.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.chernonog.springcourse.models.Person;
import ua.chernonog.springcourse.services.PeopleService;

@Controller
@RequestMapping("/people")
public class   PersonController {
  private final PeopleService peopleService;
@Autowired
    public PersonController(PeopleService peopleService) {
        this.peopleService = peopleService;

    }

    @GetMapping()
    public String showPeople(Model model){
        model.addAttribute("people",peopleService.findAll());
        peopleService.findByName("Kate");
        return "people/hello";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person",peopleService.findOne(id));
        return "/people/edit";
    }
    @GetMapping("/new")
    public String newPersonPage(Model model){
        model.addAttribute("person", new Person());
        return "/people/new";
    }

    @PostMapping()
    public String createNewPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "/people/new";
        peopleService.save(person);
        return "redirect:/people";

    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person",peopleService.findOne(id));
        return "/people/editPage";
    }

    @PatchMapping("/{id}")
    public String changePerson (@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "/people/editPage";
        peopleService.update(person,id);
        return "redirect:/people";
    }


}
