package com.gleb.springintrodiction.controller;

import com.gleb.springintrodiction.dto.ContentXml;
import com.gleb.springintrodiction.dto.ErrorDto;
import com.gleb.springintrodiction.dto.OwnerDto;
import com.gleb.springintrodiction.service.OwnerService;
import com.gleb.springintrodiction.util.HttpUtils;
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
public class OwnersController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping("/owners")
    public ResponseEntity getOwnersByFirstNameAndLastName(HttpServletRequest request,
                                                          @RequestParam(required = false) String firstName,
                                                          @RequestParam(required = false) String lastName) {
        if (accept.equals("application/xml")) {
            ContentXml content = new ContentXml();
            content.setContent(ownerService.getOwnerByFirstNameAndLastName(firstName, lastName));
            StringWriter stringWriter = new StringWriter();
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(ContentXml.class, OwnerDto.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                jaxbMarshaller.marshal(content, stringWriter);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok(stringWriter.toString());
        }
        return ResponseEntity.ok(ownerService.getOwnerByFirstNameAndLastName(firstName, lastName));
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

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/owners")
    public ResponseEntity removeOwner(@RequestParam(name = "id") Long id) {
        boolean isSucceed = ownerService.removeOwner(id);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
