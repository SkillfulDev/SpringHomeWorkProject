package ua.chernonog.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.chernonog.springcourse.models.Book;
import ua.chernonog.springcourse.models.Person;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book,Integer> {
    public List<Book> findAllByOwner(Person person);

   public Person findOwnerById(int id);
}
