package ua.chernonog.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.chernonog.springcourse.models.Book;
import ua.chernonog.springcourse.models.Person;
import ua.chernonog.springcourse.repositories.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }


    public List<Book> showAllBooks() {
        return booksRepository.findAll();
    }

    @Transactional
    public void saveBook(Book book) {
        booksRepository.save(book);
    }


    public Book showBook(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteBook(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void changeBook(int id, Book book) {
        book.setId(id);
        booksRepository.save(book);
    }

    public List<Book> findByOwner(Person person) {
        return booksRepository.findAllByOwner(person);

    }

    public Person find(int id) {
        return booksRepository.findOwnerById(id).orElse(null);
    }

    @Transactional
    public void addPersonToBook(int id, Person person) {
        booksRepository.addPersonToBook(person, id);
    }

    @Transactional
    public void removePersonFromBook(int id){
        booksRepository.removePersonFromBoor(id);
    }

    public List<Book> pageMethod(int page, int itemsPerPage){
       List<Book> books = booksRepository.findAll(PageRequest.of(page,itemsPerPage)).getContent();
        for (Book book : books) {
            System.out.println(book.getId());
        }
        return  booksRepository.findAll(PageRequest.of(page,itemsPerPage)).getContent();
    }

}
