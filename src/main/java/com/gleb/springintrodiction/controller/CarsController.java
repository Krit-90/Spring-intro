package com.gleb.springintrodiction.controller;

import com.gleb.springintrodiction.dto.Cars;
import com.gleb.springintrodiction.dto.CarsDto;
import com.gleb.springintrodiction.service.CarsService;
import com.gleb.springintrodiction.service.CarsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarsController {

    @Autowired
    private CarsService carsService;

    // TODO: Либо делай все через @RequestMapping, либо все через @Get/Post/Put/DeleteMapping, но не вперемешку
    // TODO: Никаких "xml" не должно быть в URL, должен быть один эндпоинт на все, разница лишь в заголовке Accept
    @RequestMapping(value = "/carXml", method = RequestMethod.GET)
    public List<Cars> getXml() {
        // TODO: Зачем пользоваться статичным методом из класса если у тебя сверху заавтовайрен готовый инстанс?
        return CarsServiceImpl.getCarsDB();
       }

       // TODO: Здесь хорошо бы добавить фильтрацию по ряду признаков, а не только по годам. Пускай на вход приходит такая же DTO и по всем не null полям делай фильтрацию
    @GetMapping("/cars")
    public ResponseEntity getCarsByYear(@RequestParam (name = "year", required = false) Integer year) {
        // TODO: Форматирование сломалось чутка. Юзай Ctrl + Alt + L
            if (year == null) {
                // TODO: Опять статический вызов, хотя ниже обращение к инстансу
                return ResponseEntity.ok(CarsServiceImpl.getCarsDB());
            } else {
                return ResponseEntity.ok(carsService.getCarByYear(year));
            }
    }

    @PostMapping("/cars")
    public ResponseEntity addCar(@RequestBody Cars cars) {
        boolean isSucceed = carsService.addCar(cars);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/cars")
    public ResponseEntity updateCarYear(@RequestParam(name = "model") String model,
                                        @RequestParam(name = "year") Integer year) {
        // TODO: Лишние скобки
        boolean isSucceed = (carsService.updateCar(model, year));
        if (isSucceed) {
            return ResponseEntity.ok().build();
        } else {
            // TODO: Здесь можно добавить еще одну DTO с одним полем - текстом ошибки, его возвращаем телом вместе с кодом ошибки
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/cars")
    public ResponseEntity removeCar(@RequestParam(name = "model") String model) {
        // TODO: Лишние скобки
        boolean isSucceed = (carsService.removeCar(model));
        if (isSucceed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
