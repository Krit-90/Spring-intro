package com.gleb.springintrodiction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class StaticController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index.html");
    }
}
