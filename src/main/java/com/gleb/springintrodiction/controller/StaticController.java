package com.gleb.springintrodiction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {

    @GetMapping("/index")
    public String index() {
        return "index.html";
    }
}
