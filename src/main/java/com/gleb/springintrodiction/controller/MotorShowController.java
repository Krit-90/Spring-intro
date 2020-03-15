package com.gleb.springintrodiction.controller;

import com.gleb.springintrodiction.dto.ContentXml;
import com.gleb.springintrodiction.dto.ErrorDto;
import com.gleb.springintrodiction.dto.MotorShowDto;
import com.gleb.springintrodiction.service.MotorShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@RestController
public class MotorShowController {
    @Autowired
    private MotorShowService motorShowService;

    @GetMapping("/motor-show")
    public ResponseEntity getOwnersByFirstNameAndLastName(HttpServletRequest request,
                                                          @RequestParam(required = false) String title,
                                                          @RequestParam(required = false) String city) {
        String accept = request.getHeader(HttpHeaders.ACCEPT);
        if (accept.equals("application/xml")) {
            ContentXml content = new ContentXml();
            content.setContent(motorShowService.getMotorShowByTitleAndCity(title, city));
            StringWriter stringWriter = new StringWriter();
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(ContentXml.class, MotorShowDto.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                jaxbMarshaller.marshal(content, stringWriter);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok(stringWriter.toString());
        }
        return ResponseEntity.ok(motorShowService.getMotorShowByTitleAndCity(title, city));
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

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/motor-show")
    public ResponseEntity removeMotorShow(@RequestParam(name = "id") Long id) {
        boolean isSucceed = motorShowService.removeMotorShow(id);
        if (isSucceed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
