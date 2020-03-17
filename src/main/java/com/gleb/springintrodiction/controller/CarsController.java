package com.gleb.springintrodiction.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gleb.springintrodiction.dto.CarDto;
import com.gleb.springintrodiction.dto.ErrorDto;
import com.gleb.springintrodiction.service.CarsService;
import com.gleb.springintrodiction.util.HttpUtils;
import com.gleb.springintrodiction.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarsController {
    @Autowired
    private CarsService carsService;



    // TODO Два варианта с получением Дто, только в первом(закомментированный)
    //  в строке url через браузер она не обрабатывается приложением, только в postman все нормально. А второй вариант
    //  без аннотатации. Какой лучше?

    /*    @GetMapping("/cars")
        public ResponseEntity getCarsByYearAndModel(@RequestParam(value = "carDto", required = false) String carDto)
                throws JsonProcessingException {
            CarDto carDto1 = new ObjectMapper().readValue(carDto, CarDto.class);*/

    @GetMapping("/cars")
    public ResponseEntity getCarsByYearAndModel(CarDto carDto)
            throws JsonProcessingException {
        String accept = HttpUtils.getHttpHeader(HttpHeaders.ACCEPT);
        if (accept.contains("application/xml")) {
            String content = XmlUtil.convertToXml(carsService.getCarsByModelAndYear(carDto));
            return ResponseEntity.ok(content);
        }
        return ResponseEntity.ok(carsService.getCarsByModelAndYear(carDto));
    }

    @PostMapping("/cars")
    public ResponseEntity addCar(@RequestBody CarDto carDto) {
        carsService.addCar(carDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/cars")
    public ResponseEntity updateCarYear(@RequestParam(name = "id") Long id,
                                        @RequestBody CarDto carDto) {
        boolean isSucceed = carsService.updateCar(id, carDto);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PutMapping("/cars/owner/")
    ResponseEntity addOwner(@RequestParam(name = "ownerId") Long ownerId, @RequestParam(name = "carId") Long carId) {
        boolean isExistOwner = carsService.addOwnerToCarById(ownerId, carId);
        if (isExistOwner) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PutMapping("/cars/motor-show/")
    ResponseEntity addMotorShow(@RequestParam(name = "ownerId") Long motorShowId,
                                @RequestParam(name = "carId") Long carId) {
        boolean isExistMotorShow = carsService.addMotorShowToCarById(motorShowId, carId);
        if (isExistMotorShow) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/cars")
    public ResponseEntity removeCar(@RequestParam(name = "id") Long id) {
        boolean isSucceed = carsService.removeCar(id);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
