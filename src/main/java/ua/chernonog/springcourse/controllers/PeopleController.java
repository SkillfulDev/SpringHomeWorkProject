package ua.chernonog.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.chernonog.springcourse.models.Book;
import ua.chernonog.springcourse.models.Person;
import ua.chernonog.springcourse.services.BooksService;
import ua.chernonog.springcourse.services.PeopleService;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final BooksService booksService;

    @Autowired
    public PeopleController(PeopleService peopleService, BooksService booksService) {
        this.peopleService = peopleService;
        this.booksService = booksService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("people", peopleService.findAll());

        return "people/showAllPeople";

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
    public String editPage(@PathVariable("id") int id, Model model) {

        model.addAttribute("person", peopleService.getPersonById(id));
        model.addAttribute("books", booksService.findByOwner(peopleService.getPersonById(id)));
        return "people/showPerson";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        peopleService.deletePersonById(id);

        return "redirect:/people";
    }

    @PatchMapping("/{id}")
    public String changePerson(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        peopleService.changePerson(id, person);

        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String changePage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.getPersonById(id));

        return "people/editPage";
    }

    @PostMapping("/{id}/add")
    public String addBookToPerson(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        Person personNew = peopleService.getPersonById(person.getId());
        booksService.addPersonToBook(id,personNew);


        return "redirect:/people";
    }

    @PostMapping("/{id}/remove")
    public String removeBookFromPerson(@PathVariable("id") int id){
        booksService.removePersonFromBook(id);
        return "redirect:/books/{id}";
    }
}
