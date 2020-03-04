package com.gleb.springintrodiction.controller;

import com.gleb.springintrodiction.dto.CarsDto;
import com.gleb.springintrodiction.service.CarsService;
import com.gleb.springintrodiction.service.CarsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CarsController {

    @Autowired
    private CarsService carsService;

    @RequestMapping(value = "/carXml", method = RequestMethod.GET)
    public CarsDto getXml() {
        return new CarsDto("testJsonXml", 1);
    }

    @GetMapping("/cars")
    public ResponseEntity getCarsByYear(@RequestParam (name = "year", required = false) Integer year) {
            if (year == null) {
                return ResponseEntity.ok(CarsServiceImpl.getCarsDB());
            } else {
                return ResponseEntity.ok(carsService.getCarByYear(year));
            }
    }

    @PostMapping("/cars")
    public ResponseEntity addCar(@RequestBody CarsDto carsDto) {
        boolean isSucceed = carsService.addCar(carsDto);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/cars")
    public ResponseEntity updateCarYear(@RequestParam(name = "model") String model,
                                        @RequestParam(name = "year") Integer year) {
        boolean isSucceed = (carsService.updateCar(model, year));
        if (isSucceed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/cars")
    public ResponseEntity removeCar(@RequestParam(name = "model") String model) {
        boolean isSucceed = (carsService.removeCar(model));
        if (isSucceed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
