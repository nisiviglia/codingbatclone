package com.siviglia.web.codingbatclone.repository;

import org.springframework.data.repository.CrudRepository;

import com.siviglia.web.codingbatclone.model.Question;

public interface QuestionRepository extends CrudRepository<Question, String>{
    
    
}
