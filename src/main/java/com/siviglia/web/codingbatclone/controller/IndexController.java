package com.siviglia.web.codingbatclone.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import com.siviglia.web.codingbatclone.repository.QuestionRepository;
import com.siviglia.web.codingbatclone.model.Question;
import com.siviglia.web.codingbatclone.model.QuestionTitle;
import com.siviglia.web.codingbatclone.exception.NotFoundException;

import java.util.List;

@Controller
public class IndexController{
    
    @Value("${about.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping(value={"/", "/index"}, method= RequestMethod.GET)
    public String index(Model model){
        
        List<Question> questions = questionRepository
            .getFirst10ByVisibleIsTrue();

        model.addAttribute("questions", questions);
        return "index";
    }

    @RequestMapping(value={"/about"}, method= RequestMethod.GET)
    public String about(Model model){
        
        model.addAttribute("message", message);
        return "about";
    }
}
