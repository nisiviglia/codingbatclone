package com.siviglia.web.codingbatclone.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Arrays;
import java.util.ArrayList;

import com.siviglia.web.codingbatclone.model.Question;
import com.siviglia.web.codingbatclone.repository.QuestionRepository;
import com.siviglia.web.codingbatclone.exception.NotFoundException;
import com.siviglia.web.codingbatclone.util.RunPython;

@Controller
public class QuestionController{
    
    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping(value={"/question/title/{title}"}, method= RequestMethod.GET)
    public String question(Model model, @PathVariable(value= "title") String title){
        
        Question question = questionRepository
            .findByTitleAndVisibleIsTrue( title )
            .orElseThrow( NotFoundException::new );

        model.addAttribute("question", question);
        return("question");
    }

    @RequestMapping(value={"/question/insert"}, method= RequestMethod.GET)
    public String insertQuestion(Model model, Question question){
        
        question.setDate( new Date().getTime() );

        try{
            questionRepository.save(question);
        } catch(DataIntegrityViolationException e){
            return("redirect:/admin"); 
        }
        
        return ("redirect:/admin");
    }

    @RequestMapping(value={"/question/modify"}, method= RequestMethod.GET)
    public String modiftyQuestion(Model model, Question question){
        
        //Get old question from db
        Question oldQuestion = questionRepository
            .findFirstById( question.getId() )
            .orElseThrow( NotFoundException::new );

        //Set data that should not change 
        question.setDate( oldQuestion.getDate() );

        //Update database
        try{
            questionRepository.save(question);
        } catch(DataIntegrityViolationException e){
            return("redirect:/admin"); 
        }
        
        return ("redirect:/admin");
    }

    @Transactional
    @RequestMapping(value={"/question/delete"}, method= RequestMethod.GET)
    public String deleteQuestion(Model model, @RequestParam(value= "title") String title){
        
        questionRepository.deleteByTitle( title );
        return("redirect:/admin"); 
    }

    @RequestMapping(value={"/question/toggleVisible"}, method= RequestMethod.GET)
    public String toggleVisibility(@RequestParam(value= "title") String title){
    
        Question question = questionRepository
            .findFirstByTitle( title )
            .orElseThrow( NotFoundException::new );

        question.setVisible( !question.getVisible() );
    
        questionRepository.save(question);

        return("redirect:/admin"); 
    }

    @RequestMapping(value={"/question/run/{title}"}, method= RequestMethod.GET)
    public String runQuestion(Model model, 
            @PathVariable(value= "title") String title, 
            @RequestParam(value= "QuestionText") String text){
    
        Question question = questionRepository
            .findFirstByTitle( title )
            .orElseThrow( NotFoundException::new );
        
        question.setText(text);

        RunPython python = new RunPython();
        python.run(question); 

        model.addAttribute("question", question);
        return("question");    
    }

}
