package com.gleb.springintrodiction.controller;

import com.gleb.springintrodiction.dto.CarDto;
import com.gleb.springintrodiction.dto.CarsListDto;
import com.gleb.springintrodiction.dto.ErrorDto;
import com.gleb.springintrodiction.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@RestController
public class CarsController {
    @Autowired
    private CarsService carsService;

    @GetMapping("/cars")
    public ResponseEntity getCarsByYearAndModel(HttpServletRequest request, @RequestBody CarDto carDto) {
        String accept = request.getHeader(HttpHeaders.ACCEPT);
        CarsListDto content = new CarsListDto();
        if (carDto.getYear() == null & carDto.getModel() == null) {
            content.setCarDB(carsService.getCarsDtoDB());
        } else {
            if (carDto.getYear() != null & carDto.getModel() != null) {
                content.setCarDB(carsService.getCarsByModelAndYear(carDto));
            }
        }
        if (accept.equals("application/xml")) {
            StringWriter stringWriter = new StringWriter();
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(CarsListDto.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                jaxbMarshaller.marshal(content, stringWriter);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok(stringWriter.toString());
        }
        return ResponseEntity.ok(content);
    }

    @PostMapping("/cars")
    public ResponseEntity addCar(@RequestBody CarDto carDto) {
        boolean isSucceed = carsService.addCar(carDto);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/cars")
    public ResponseEntity updateCarYear(@RequestParam(name = "id") Integer id,
                                        @RequestBody CarDto carDto) {
        boolean isSucceed = carsService.updateCar(id, carDto);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/cars")
    public ResponseEntity removeCar(@RequestParam(name = "id") Integer id) {
        boolean isSucceed = carsService.removeCar(id);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
