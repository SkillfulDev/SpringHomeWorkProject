package ua.chernonog.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.chernonog.springcourse.models.Book;
@Repository
public interface BooksRepository extends JpaRepository<Book,Integer> {
}
