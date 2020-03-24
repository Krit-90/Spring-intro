package com.gleb.springintrodiction.controller;

import com.gleb.springintrodiction.dto.ErrorDto;
import com.gleb.springintrodiction.dto.MotorShowDto;
import com.gleb.springintrodiction.service.MotorShowService;
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
public class MotorShowController {
    @Autowired
    private MotorShowService motorShowService;

    @GetMapping("/motor-show")
    public ResponseEntity getOwnersByFirstNameAndLastName(MotorShowDto motorShowDto) {
        String accept = HttpUtils.getHttpHeader(HttpHeaders.ACCEPT);
        if (accept.equals("application/xml")) {
            String content = XmlUtil.convertToXml(motorShowService.getMotorShowByTitleAndCity(motorShowDto));
            return ResponseEntity.ok(content);
        }
        return ResponseEntity.ok(motorShowService.getMotorShowByTitleAndCity(motorShowDto));
    }

    @PostMapping("/motor-show")
    public ResponseEntity addMotorShow(@RequestBody MotorShowDto motorShowDto) {
        motorShowService.addMotorShow(motorShowDto);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/motor-show")
    public ResponseEntity updateMotorShow(@RequestParam(name = "id") Long id,
                                          @RequestBody MotorShowDto motorShowDto) {
        boolean isSucceed = motorShowService.updateMotorShow(id, motorShowDto);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        }
        String message = MessageSourceUtil.getMessage("error.server", LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(message));
    }

    @DeleteMapping("/motor-show")
    public ResponseEntity removeMotorShow(@RequestParam(name = "id") Long id) {
        boolean isSucceed = motorShowService.removeMotorShow(id);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        } else {
            String message = MessageSourceUtil.getMessage("error.server", LocaleContextHolder.getLocale());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(message));
        }
    }
}
