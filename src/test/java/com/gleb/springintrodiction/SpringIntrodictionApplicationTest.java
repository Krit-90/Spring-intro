package com.gleb.springintrodiction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gleb.springintrodiction.controller.CarsController;
import com.gleb.springintrodiction.data.Car;
import com.gleb.springintrodiction.dto.CarDto;
import com.gleb.springintrodiction.repository.CarRepository;
import com.gleb.springintrodiction.service.CarsService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringIntrodictionApplicationTest {

    @Autowired
    private CarsController carsController;
    @Autowired
    private CarsService carsService;
    @Autowired
    private CarRepository carRepository;

    @Test
    public void chainOfDispatcherControllerServiceRepositoryDB() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(carsController).build();
        CarDto carDto = new CarDto("Volvo", 2010);
        mockMvc.perform(MockMvcRequestBuilders.post("/cars").contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(carDto)));
        List<CarDto> actual = carsService.getCarsByModelAndYear(new CarDto("Volvo", 2010));
        List<CarDto> expected = Arrays.asList(new CarDto("Volvo", 2010));
        Assertions.assertEquals(expected, actual);
        List<Car> actualRepository = carRepository.findAll();
        List<Car> expectedRepository = Arrays.asList(new Car("Volvo", 2010));
        Assertions.assertIterableEquals(expectedRepository, actualRepository);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
