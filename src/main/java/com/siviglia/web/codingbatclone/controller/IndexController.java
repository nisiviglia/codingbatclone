package com.siviglia.web.codingbatclone.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String index(Model model, 
            @RequestParam(value= "page", defaultValue= "0", required= false) int page){
        
        Page<Question> questions = questionRepository
            .getAllByVisibleIsTrue(PageRequest.of(page, 10));

        model.addAttribute("questions", questions);
        model.addAttribute("page", page);
        model.addAttribute("isFirstPage", questions.isFirst() );
        model.addAttribute("isLastPage", questions.isLast() );

        return "index";
    }

    @RequestMapping(value={"/about"}, method= RequestMethod.GET)
    public String about(Model model){
        
        model.addAttribute("message", message);
        return "about";
    }

    @RequestMapping(value={"/admin"}, method= RequestMethod.GET)
    public String admin(Model model, 
            @RequestParam(value= "page", defaultValue= "0", required= false) int page){

		Page<Question> questions = questionRepository
            .findAll( PageRequest.of(page, 10) );

		model.addAttribute("questions", questions);        
        model.addAttribute("page", page);
        model.addAttribute("isFirstPage", questions.isFirst() );
        model.addAttribute("isLastPage", questions.isLast() );
        return "admin";
    }

    @RequestMapping(value={"/admin/insert"}, method= RequestMethod.GET)
    public String adminInsert(Model model){
    
        model.addAttribute("pageTitle", "Insert");
        model.addAttribute("pageName", "insert");
        return "insertOrModify";
    }

    @RequestMapping(value={"/admin/modify"}, method= RequestMethod.GET)
    public String adminModify(Model model, @RequestParam(value= "title", required= true) String title){
        
        Question question = questionRepository
            .findFirstByTitle(title)
            .orElseThrow( NotFoundException::new );

        model.addAttribute("pageTitle", "Modify");
        model.addAttribute("pageName", "modify");
        model.addAttribute("question", question);

        return "insertOrModify";
    }
}
