package ua.chernonog.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.chernonog.springcourse.models.Book;
import ua.chernonog.springcourse.models.Person;
import ua.chernonog.springcourse.repositories.BooksRepository;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
        Book book = booksRepository.findById(id).orElse(null);
        book.setDate(new Date());
        booksRepository.addPersonToBook(person, id);
    }

    @Transactional
    public void removePersonFromBook(int id) {
        booksRepository.removePersonFromBoor(id);
    }

    public List<Book> pageMethod(Integer page, Integer itemsPerPage) {
        if (page != null) {
            return booksRepository.findAll(PageRequest.of(page, itemsPerPage, Sort.by("yearOfProduction"))
            ).getContent();
        }
        return booksRepository.findAll(Sort.by("yearOfProduction"));
    }

    public List<Book> findBookByLike(String str) {
        return booksRepository.findAllByTitleContaining(str);
    }


}