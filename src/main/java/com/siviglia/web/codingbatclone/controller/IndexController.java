package com.siviglia.web.codingbatclone.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController{
    
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value={"/", "/index"}, method= RequestMethod.GET)
    public String index(Model model){
        
        model.addAttribute("message", message);
        return "index";
    }

    @RequestMapping(value={"/about"}, method= RequestMethod.GET)
    public String about(Model model){
         
        return "about";
    }
}
