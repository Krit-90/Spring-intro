package com.gleb.springintrodiction.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "owners")
@Entity
public class Owner {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "owners_cars",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    // TODO: Зачем инициализация?
    private List<Car> cars = new ArrayList();


    public List<Car> getCars () {
        return cars;
    }

    public Owner(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Owner() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
