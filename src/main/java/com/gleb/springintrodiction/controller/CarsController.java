package com.gleb.springintrodiction.controller;

import com.gleb.springintrodiction.dto.CarDto;
import com.gleb.springintrodiction.dto.ErrorDto;
import com.gleb.springintrodiction.service.CarsService;
import com.gleb.springintrodiction.util.HttpUtils;
import com.gleb.springintrodiction.util.MessageSourceUtil;
import com.gleb.springintrodiction.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarsController {

    @Autowired
    private CarsService carsService;

    @GetMapping("/cars")
    public ResponseEntity getCarsByYearAndModel(CarDto carDto) {
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
        String message = MessageSourceUtil.getMessage("error.server", LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(message));
    }

    @PutMapping("/cars/owner/")
    ResponseEntity addOwner(@RequestParam(name = "ownerId") Long ownerId,
                            @RequestParam(name = "carId") Long carId) {
        boolean isExistOwner = carsService.addOwnerToCarById(ownerId, carId);
        if (isExistOwner) {
            return ResponseEntity.ok().build();
        }
        String message = MessageSourceUtil.getMessage("error.server", LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(message));
    }

    @PutMapping("/cars/motor-show/")
    ResponseEntity addMotorShow(@RequestParam(name = "ownerId") Long motorShowId,
                                @RequestParam(name = "carId") Long carId) {
        boolean isExistMotorShow = carsService.addMotorShowToCarById(motorShowId, carId);
        if (isExistMotorShow) {
            return ResponseEntity.ok().build();
        }
        String message = MessageSourceUtil.getMessage("error.server", LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(message));
    }

    @DeleteMapping("/cars")
    public ResponseEntity removeCar(@RequestParam(name = "id") Long id) {
        boolean isSucceed = carsService.removeCar(id);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        } else {
            String message = MessageSourceUtil.getMessage("error.server", LocaleContextHolder.getLocale());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(message));
        }
    }
}
