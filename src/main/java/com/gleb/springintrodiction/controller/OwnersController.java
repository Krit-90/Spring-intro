package com.gleb.springintrodiction.controller;

import com.gleb.springintrodiction.dto.ErrorDto;
import com.gleb.springintrodiction.dto.OwnerDto;
import com.gleb.springintrodiction.service.OwnerService;
import com.gleb.springintrodiction.util.HttpUtils;
import com.gleb.springintrodiction.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class OwnersController {
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/owners")
    public ResponseEntity getOwnersByFirstNameAndLastName(@RequestParam(required = false) String firstName,
                                                          @RequestParam(required = false) String lastName) {
        String accept = HttpUtils.getHttpHeader(HttpHeaders.ACCEPT);
        if (accept.equals("application/xml")) {
            String content = XmlUtil.convertToXml(ownerService.getOwnerByFirstNameAndLastName(firstName, lastName));
            return ResponseEntity.ok(content);
        }
        return ResponseEntity.ok(ownerService.getOwnerByFirstNameAndLastName(firstName, lastName));
    }

    @PostMapping("/owners")
    public ResponseEntity addOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.addOwner(ownerDto);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/owners")
    public ResponseEntity updateOwner(Locale locale, @RequestParam(name = "id") Long id,
                                      @RequestBody OwnerDto ownerDto) {
        boolean isSucceed = ownerService.updateOwner(id, ownerDto);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        }
        String message = messageSource.getMessage("error.server", null, locale);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(message));
    }

    @DeleteMapping("/owners")
    public ResponseEntity removeOwner(Locale locale, @RequestParam(name = "id") Long id) {
        boolean isSucceed = ownerService.removeOwner(id);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        } else {
            String message = messageSource.getMessage("error.server", null, locale);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(message));        }
    }
}
