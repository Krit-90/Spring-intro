package com.gleb.springintrodiction.data;

import javax.persistence.*;
import java.util.List;

@Table(name = "cars")
@Entity
public class Car {

    // TODO: Модификатор доступа забыл
    @Id
    @GeneratedValue
    Long id;
    @Column
    private String model;
    @Column
    private Integer year;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private List<Owner> owners;

    public Car(String model, Integer year) {
        this.model = model;
        this.year = year;
    }

    public Car() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }
}

