package fr.univrouen.stb23v1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
     @GetMapping("/")
    public String index() {
         return "Hello stb23 !";
     }
}
