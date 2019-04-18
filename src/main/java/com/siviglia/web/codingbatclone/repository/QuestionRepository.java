package com.siviglia.web.codingbatclone.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.siviglia.web.codingbatclone.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends CrudRepository<Question, String>{
    
    public Optional<Question> findFirstById(long id);

    public Optional<Question> findFirstByTitle(String title);

    public Optional<Question> findByTitleAndVisibleIsTrue(String title);    

    public Page<Question> getAllByVisibleIsTrue(Pageable pageable);

	public Page<Question> findAll(Pageable pageable);

    public void deleteByTitle(String title);
}
