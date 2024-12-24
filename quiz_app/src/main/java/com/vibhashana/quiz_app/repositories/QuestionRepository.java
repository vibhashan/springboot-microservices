package com.vibhashana.quiz_app.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.vibhashana.quiz_app.models.Question;

public interface QuestionRepository extends MongoRepository<Question, String> {
    @Aggregation(pipeline = {
            "{'$match': {'category': ?0}}", // Filters by the 'category' field
            "{'$sample': {'size': ?1}}", // Randomly samples 'size' number of documents
    })
    List<Question> findRandomQuestionsByCategory(String category, int noOfQuestions);
}
