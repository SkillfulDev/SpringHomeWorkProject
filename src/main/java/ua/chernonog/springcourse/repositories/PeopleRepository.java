package ua.chernonog.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.chernonog.springcourse.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {
}
