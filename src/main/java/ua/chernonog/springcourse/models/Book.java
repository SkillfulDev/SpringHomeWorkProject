package ua.chernonog.springcourse.models;

import jakarta.persistence.*;

@Entity
@Table(name="book")
public class Book {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="year_of_production")
    private int yearOfProduction;

    @ManyToOne
    @JoinColumn(name="id_person",referencedColumnName = "id")
    private Person owner;
}
