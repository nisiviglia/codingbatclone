package com.siviglia.web.codingbatclone.repository;

import org.springframework.data.repository.CrudRepository;

import com.siviglia.web.codingbatclone.model.Question;
import com.siviglia.web.codingbatclone.model.QuestionTitle;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends CrudRepository<Question, String>{
    
    public Optional<Question> findByTitleAndVisibleIsTrue(String title);    

    public List<Question> getFirst10ByVisibleIsTrue();
}
