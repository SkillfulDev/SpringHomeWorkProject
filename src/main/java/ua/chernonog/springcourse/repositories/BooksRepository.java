package ua.chernonog.springcourse.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.chernonog.springcourse.models.Book;
import ua.chernonog.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    public List<Book> findAllByOwner(Person person);


    @Query(value = "SELECT o.owner FROM Book o where o.id=:id")
    public Optional<Person> findOwnerById(@Param("id") int id);

    @Modifying
    @Query(value = "update Book  set owner=:owner where id=:id")
    public void addPersonToBook(@Param("owner") Person person, @Param("id") int id);

    @Modifying
    @Query(value = "update Book  set owner=null where id=:id")
    public void removePersonFromBoor(@Param("id") int id);

    Page<Book> findAll(Pageable page);

    List<Book> findAll(Sort var);
}
