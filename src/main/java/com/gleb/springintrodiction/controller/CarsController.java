package com.gleb.springintrodiction.controller;

import com.gleb.springintrodiction.dto.CarDto;
import com.gleb.springintrodiction.dto.CarsListDto;
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
            // TODO: У тебя должен быть в сервисе один метод, принимающий на вход один объект - carDto и дальше там уже должна быть логика по фильтрации
            if (carDto.getYear() != null & carDto.getModel() != null) {
                content.setCarDB(carsService.getCarsByModelAndYear(carDto.getModel(), carDto.getYear()));
            }
            if (carDto.getModel() == null) {
                content.setCarDB(carsService.getCarsByYear(carDto.getYear()));
            } else {
                content.setCarDB(carsService.getCarsByModel(carDto.getModel()));
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
        // TODO: Здесь можно добавить еще одну DTO с одним полем - текстом ошибки, его возвращаем телом вместе с кодом ошибки
        //  Можно пояснить, еще одну CarDto или объект нового класса,допустим, ErrorDto, с полем String текстОшибки?
        // TODO: ErrorDto с полем errorMessage вполне подойдет
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
