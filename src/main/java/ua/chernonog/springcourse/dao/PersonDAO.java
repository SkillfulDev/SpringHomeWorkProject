package ua.chernonog.springcourse.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.chernonog.springcourse.models.Person;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Person getPerson() {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, 3);
        return person;
    }
}
