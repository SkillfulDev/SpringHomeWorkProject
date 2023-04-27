package ua.chernonog.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.chernonog.springcourse.models.Book;
import ua.chernonog.springcourse.models.Person;
import ua.chernonog.springcourse.services.BooksService;
import ua.chernonog.springcourse.services.PeopleService;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String showAllBooks(Model model) {

        model.addAttribute("books", booksService.showAllBooks());

        return "books/showAllBooks";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());

        return "books/newBook";
    }

    @PostMapping()
    public String saveBook(@ModelAttribute("book") Book book) {
        booksService.saveBook(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.showBook(id));
        model.addAttribute("people",peopleService.findAll());
        if (booksService.find(id) == null) {
            model.addAttribute("person", new Person());

        } else {
            model.addAttribute("person", booksService.find(id));
        }


        return "books/showBook";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        booksService.deleteBook(id);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBookPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.showBook(id));

        return "books/editBookPage";

    }

    @PatchMapping("/{id}")
    public String editBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        booksService.changeBook(id, book);

        return "redirect:/books";
    }

}
