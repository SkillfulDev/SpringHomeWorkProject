package ua.chernonog.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.chernonog.springcourse.dao.PersonDAO;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;

    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String helloPage(){
        return "people/hello";
    }
    @GetMapping("/test")
    public String getPerson(Model model){
        model.addAttribute("person",personDAO.getPerson());
        return "/people/test";
    }

}
