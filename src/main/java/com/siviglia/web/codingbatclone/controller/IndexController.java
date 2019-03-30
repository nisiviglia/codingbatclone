package com.siviglia.web.codingbatclone.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;

import com.siviglia.web.codingbatclone.repository.QuestionRepository;
import com.siviglia.web.codingbatclone.model.Question;
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
        
        Page<Question> questions = questionRepository
            .getAllByVisibleIsTrue(PageRequest.of(0, 10));

        model.addAttribute("questions", questions);
        return "index";
    }

    @RequestMapping(value={"/index/{page}"}, method= RequestMethod.GET)
    public String indexPage(Model model, @PathVariable(value= "page") int page){
        
        Page<Question> questions = questionRepository
            .getAllByVisibleIsTrue(PageRequest.of(page, 10));

        model.addAttribute("questions", questions);
        return "index";
    }

    @RequestMapping(value={"/about"}, method= RequestMethod.GET)
    public String about(Model model){
        
        model.addAttribute("message", message);
        return "about";
    }

    @RequestMapping(value={"/admin"}, method= RequestMethod.GET)
    public String admin(Model model){
		
		Page<Question> questions = questionRepository
            .findAll( PageRequest.of(0, 10) );

		model.addAttribute("questions", questions);        
        model.addAttribute("page", 0);
        return "admin";
    }

    @RequestMapping(value={"/admin/{page}"}, method= RequestMethod.GET)
    public String adminPage(Model model, @PathVariable(value= "page") int page){

		Page<Question> questions = questionRepository
            .findAll( PageRequest.of(page, 10) );

		model.addAttribute("questions", questions);        
        model.addAttribute("page", page);
        return "admin";
    }
}
