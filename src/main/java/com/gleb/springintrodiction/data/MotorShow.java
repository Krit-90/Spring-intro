package com.gleb.springintrodiction.data;

import javax.persistence.*;
import java.util.List;

@Table(name = "motorShow")
@Entity
public class MotorShow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String city;
    @OneToMany(mappedBy = "motorShow", orphanRemoval = true)
    private List<Car> cars;

    public MotorShow(String title, String city, List<Car> cars) {
        this.title = title;
        this.city = city;
        this.cars = cars;
    }

    public MotorShow(String title, String city) {
        this.title = title;
        this.city = city;
    }

    public MotorShow() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
