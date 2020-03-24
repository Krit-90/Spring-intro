package com.gleb.springintrodiction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gleb.springintrodiction.controller.CarsController;
import com.gleb.springintrodiction.dto.CarDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringIntrodictionApplicationTest {

    @Autowired
    private CarsController carsController;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void chainOfDispatcherControllerServiceRepositoryDB() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(carsController).build();
        CarDto carDto = new CarDto("Volvo", 2010);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(carDto))).andExpect(status().isOk()).andReturn();
    }
}
