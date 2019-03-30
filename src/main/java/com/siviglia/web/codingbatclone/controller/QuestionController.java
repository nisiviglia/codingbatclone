package com.siviglia.web.codingbatclone.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.siviglia.web.codingbatclone.model.Question;
import com.siviglia.web.codingbatclone.repository.QuestionRepository;
import com.siviglia.web.codingbatclone.exception.NotFoundException;

import java.util.Date;

@Controller
public class QuestionController{
    
    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping(value={"/quest/{title}"}, method= RequestMethod.GET)
    public String question(Model model, @PathVariable(value= "title") String title){
        
        Question question = questionRepository
            .findByTitleAndVisibleIsTrue( title )
            .orElseThrow( NotFoundException::new );

        model.addAttribute("question", question);
        return("question");
    }

    @RequestMapping(value={"/quest"}, method= RequestMethod.PUT)
    public String addQuestion(Model model, @RequestBody Question question){
        
        question.setDate( new Date().getTime() );
        try{
            questionRepository.save(question);
        } catch(DataIntegrityViolationException e){
            return("index"); 
        }

        return("index"); 
    }
}
