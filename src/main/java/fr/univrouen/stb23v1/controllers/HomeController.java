package fr.univrouen.stb23v1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {


    @RequestMapping("/")
    @ResponseBody
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @RequestMapping("/help")
    @ResponseBody
    public ModelAndView help() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("help.html");
        return modelAndView;
    }
}
