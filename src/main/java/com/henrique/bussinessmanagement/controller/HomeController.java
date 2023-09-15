package com.henrique.bussinessmanagement.controller;

import ch.qos.logback.core.model.processor.ModelHandlerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()
public class HomeController {

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Home");
        return mv;
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "Home";
    }
}
