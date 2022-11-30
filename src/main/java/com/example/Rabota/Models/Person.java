package com.example.Rabota.Models;


import javax.persistence.*;

@Entity
@Table (name = "personal")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="pasport_id")
    private Pasport pasport;

    public Person() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pasport getPasport() {
        return pasport;
    }

    public void setPasport(Pasport pasport) {
        this.pasport = pasport;
    }

    public Person(String name, Pasport pasport) {
        this.name = name;
        this.pasport = pasport;
    }

    public Person(String name, Address address) {
    }
}
