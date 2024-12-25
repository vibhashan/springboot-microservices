package com.vibhashana.question_service.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.vibhashana.question_service.models.Question;

public interface QuestionRepository extends MongoRepository<Question, String> {
    @Aggregation(pipeline = {
            "{'$match': {'category': ?0}}", // Filters by the 'category' field
            "{'$sample': {'size': ?1}}", // Randomly samples 'size' number of documents
    })
    List<Question> findRandomQuestionsByCategory(String category, int noOfQuestions);

    @Aggregation(pipeline = {
            "{'$match': {'category': ?0}}", // Filters by the 'category' field
            "{'$sample': {'size': ?1}}", // Randomly samples 'size' number of documents
            "{'$project': {'_id': 1}}"
    })
    List<String> getQuestionsForQuiz(String category, int noOfQuestions);
}
