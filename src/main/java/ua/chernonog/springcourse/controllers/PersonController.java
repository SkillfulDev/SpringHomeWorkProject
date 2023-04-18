package ua.chernonog.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
        personDAO.saveNewPerson(person);
        return "redirect:/people";

    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDAO.getPerson(id));
        return "/people/editPage";
    }

    @PatchMapping("/{id}")
    public String changePerson (@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "/people/editPage";
        personDAO.changePerson(person,id);
        return "redirect:/people";
    }


}
