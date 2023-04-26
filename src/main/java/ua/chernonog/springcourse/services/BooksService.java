package ua.chernonog.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.chernonog.springcourse.models.Book;
import ua.chernonog.springcourse.repositories.BooksRepository;

import java.util.List;

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

    @Transactional
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
}