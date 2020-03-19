package com.gleb.springintrodiction.data;

import javax.persistence.*;
import java.util.List;

@Table(name = "cars")
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String model;
    @Column
    private Integer year;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cars")
    private List<Owner> owners;
    @ManyToOne
    @JoinColumn(name = "motorShow_id")
    private MotorShow motorShow;

    public Car(String model, Integer year, MotorShow motorShow) {
        this.model = model;
        this.year = year;
        this.motorShow = motorShow;
    }

    public Car(String model, Integer year) {
        this.model = model;
        this.year = year;
    }

    public Car(String model, Integer year, List<Owner> owners) {
        this.model = model;
        this.year = year;
        this.owners = owners;
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

    public MotorShow getMotorShow() {
        return motorShow;
    }

    public void setMotorShow(MotorShow motorShow) {
        this.motorShow = motorShow;
    }

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (getId() != null ? !getId().equals(car.getId()) : car.getId() != null) return false;
        if (getModel() != null ? !getModel().equals(car.getModel()) : car.getModel() != null) return false;
        if (getYear() != null ? !getYear().equals(car.getYear()) : car.getYear() != null) return false;
        if (getOwners() != null ? !getOwners().equals(car.getOwners()) : car.getOwners() != null) return false;
        return getMotorShow() != null ? getMotorShow().equals(car.getMotorShow()) : car.getMotorShow() == null;
    }

    @Override
    public int hashCode() {
        int result = getModel() != null ? getModel().hashCode() : 0;
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getMotorShow() != null ? getMotorShow().hashCode() : 0);
        return result;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (getModel() != null ? !getModel().equals(car.getModel()) : car.getModel() != null) return false;
        if (getYear() != null ? !getYear().equals(car.getYear()) : car.getYear() != null) return false;
        return getMotorShow() != null ? getMotorShow().equals(car.getMotorShow()) : car.getMotorShow() == null;
    }

    @Override
    public int hashCode() {
        int result = getModel() != null ? getModel().hashCode() : 0;
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getMotorShow() != null ? getMotorShow().hashCode() : 0);
        return result;
    }
}

