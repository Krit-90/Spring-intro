package com.gleb.springintrodiction.controller;

import com.gleb.springintrodiction.dto.ErrorDto;
import com.gleb.springintrodiction.dto.OwnerDto;
import com.gleb.springintrodiction.service.OwnerService;
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
public class OwnersController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping("/owners")
    public ResponseEntity getOwnersByFirstNameAndLastName(OwnerDto ownerDto) {
        String accept = HttpUtils.getHttpHeader(HttpHeaders.ACCEPT);
        if (accept.equals("application/xml")) {
            String content = XmlUtil.convertToXml(ownerService.getOwnerByFirstNameAndLastName(ownerDto));
            return ResponseEntity.ok(content);
        }
        return ResponseEntity.ok(ownerService.getOwnerByFirstNameAndLastName(ownerDto));
    }

    @PostMapping("/owners")
    public ResponseEntity addOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.addOwner(ownerDto);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/owners")
    public ResponseEntity updateOwner(@RequestParam(name = "id") Long id,
                                      @RequestBody OwnerDto ownerDto) {
        boolean isSucceed = ownerService.updateOwner(id, ownerDto);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        }
        String message = MessageSourceUtil.getMessage("error.server", LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(message));
    }

    @DeleteMapping("/owners")
    public ResponseEntity removeOwner(@RequestParam(name = "id") Long id) {
        boolean isSucceed = ownerService.removeOwner(id);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        } else {
            String message = MessageSourceUtil.getMessage("error.server", LocaleContextHolder.getLocale());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(message));        }
    }
}
