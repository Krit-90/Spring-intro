package com.gleb.springintrodiction.controller;

import com.gleb.springintrodiction.dto.ErrorDto;
import com.gleb.springintrodiction.dto.MotorShowDto;
import com.gleb.springintrodiction.service.MotorShowService;
import com.gleb.springintrodiction.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@RestController
public class MotorShowController {
    @Autowired
    private MotorShowService motorShowService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/motor-show")
    public ResponseEntity getOwnersByFirstNameAndLastName(HttpServletRequest request,
                                                          @RequestParam(required = false) String title,
                                                          @RequestParam(required = false) String city) {
        String accept = request.getHeader(HttpHeaders.ACCEPT);
        if (accept.equals("application/xml")) {
            String content = XmlUtil.convertToXml(motorShowService.getMotorShowByTitleAndCity(title, city));
            return ResponseEntity.ok(content);
        }
        return ResponseEntity.ok(motorShowService.getMotorShowByTitleAndCity(title, city));
    }

    @PostMapping("/motor-show")
    public ResponseEntity addMotorShow(@RequestBody MotorShowDto motorShowDto) {
        motorShowService.addMotorShow(motorShowDto);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/motor-show")
    public ResponseEntity updateMotorShow(Locale locale, @RequestParam(name = "id") Long id,
                                          @RequestBody MotorShowDto motorShowDto) {
        boolean isSucceed = motorShowService.updateMotorShow(id, motorShowDto);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        }
        String message = messageSource.getMessage("error.server", null, locale);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(message));
    }

    @DeleteMapping("/motor-show")
    public ResponseEntity removeMotorShow(Locale locale, @RequestParam(name = "id") Long id) {
        boolean isSucceed = motorShowService.removeMotorShow(id);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        } else {
            String message = messageSource.getMessage("error.server", null, locale);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(message));        }
    }
}
