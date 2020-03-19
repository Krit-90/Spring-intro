package com.gleb.springintrodiction.dto;

public class CarDto {

    private String model;
    private Integer year;
    private String motorShowTitle;

    public CarDto(String model, Integer year, String motorShowTitle) {
        this.model = model;
        this.year = year;
        this.motorShowTitle = motorShowTitle;
    }

    public CarDto(String model, Integer year) {
        this.model = model;
        this.year = year;
    }

    public CarDto() {
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

    public String getMotorShowTitle() {
        return motorShowTitle;
    }

    public void setMotorShowTitle(String motorShowTitle) {
        this.motorShowTitle = motorShowTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarDto)) return false;

        CarDto carDto = (CarDto) o;

        if (getModel() != null ? !getModel().equals(carDto.getModel()) : carDto.getModel() != null) return false;
        if (getYear() != null ? !getYear().equals(carDto.getYear()) : carDto.getYear() != null) return false;
        return getMotorShowTitle() != null ? getMotorShowTitle().equals(carDto.getMotorShowTitle()) : carDto.getMotorShowTitle() == null;
    }

    @Override
    public int hashCode() {
        int result = getModel() != null ? getModel().hashCode() : 0;
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getMotorShowTitle() != null ? getMotorShowTitle().hashCode() : 0);
        return result;
    }
}
