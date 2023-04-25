package ua.chernonog.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.chernonog.springcourse.models.Person;
import ua.chernonog.springcourse.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    public Person getPersonById(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deletePersonById(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void changePerson(int id, Person person) {
        person.setId(id);
        peopleRepository.save(person);
    }
}
