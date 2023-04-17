package ua.chernonog.springcourse.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.chernonog.springcourse.models.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional
    public List<Person> showPeople() {
        Session session = sessionFactory.getCurrentSession();
        List<Person> people = session.createQuery("Select p from Person p", Person.class).getResultList();

        return people;

    }

    @Transactional
    public Person getPerson(int id) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.get(Person.class, id);

        return person;

    }

    @Transactional
    public void saveNewPerson(Person person) {
        Session session = sessionFactory.getCurrentSession();

        session.persist(person);
    }
}
