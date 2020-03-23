package com.gleb.springintrodiction.service;

import com.gleb.springintrodiction.data.Car;
import com.gleb.springintrodiction.data.Owner;
import com.gleb.springintrodiction.dto.CarDto;
import com.gleb.springintrodiction.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

class CarsServiceImplTest {

    @Mock
    private CarRepository carRepository;
    @InjectMocks
    private CarsServiceImpl carsService = new CarsServiceImpl();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Owner owner1 = new Owner("Igor", "Sidirov");
        Owner owner2 = new Owner("Andrey", "Andreev");
        Owner owner3 = new Owner("Petya", "Petrov");
        Mockito.when(carRepository.findAll()).thenReturn(Arrays.asList(
                new Car("Mustang", 1967, Arrays.asList(owner1, owner2, owner3)),
                new Car("Mustang", 1970, Arrays.asList(owner1, owner2, owner3)),
                new Car("Mustang", 1970, Arrays.asList(owner1, owner2)),
                new Car("Mustang", 1971, Arrays.asList(owner2, owner3)),
                new Car("VAZ 2101", 1967, Arrays.asList(owner1, owner2, owner3)),
                new Car("Mustang", 1971, Arrays.asList(owner1))));
    }


    @Test
    void findOwnersOfCarsByAgeWithNullFields() {
        // TODO: Эта проверка не имеет смысла если вызывать ее в начале кода, т.к. действительно ничего не вызывалось раньше
        Mockito.verify(carRepository, Mockito.times(0))
                .findCarsByModelAndYear(null, null);
        List<Owner> actual = carsService.findOwnersOfCarsByAge(new CarDto(null, null));
        actual.forEach(owner -> Assertions.assertEquals(new Owner("unknown", "car"), owner));
    }

    // TODO: По факту это три одинаковых теста findOwnersOfCardByAgeTest, нет смысла выносить в три разных теста
    @Test
    void findOwnersOfCarsByAge1967() {
        List<Owner> actual = carsService.findOwnersOfCarsByAge(new CarDto("Mustang", 1967));
        actual.forEach(owner -> Assertions.assertEquals(new Owner("Igor", "Sidirov"), owner));
    }

    @Test
    void findOwnersOfCarsByAge1970() {
        List<Owner> actual = carsService.findOwnersOfCarsByAge(new CarDto("Mustang", 1970));
        List<Owner> expected = Arrays.asList(new Owner("Petya", "Petrov"), new Owner("Andrey", "Andreev"));
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void findOwnersOfCarsByAgeOther() {
        List<Owner> actual = carsService.findOwnersOfCarsByAge(new CarDto("Mustang", 1971));
        List<Owner> expected = Arrays.asList(new Owner("Andrey", "Andreev"), new Owner("Petya", "Petrov"),
                new Owner("Igor", "Sidirov"));
        Assertions.assertEquals(actual, expected);
    }
}