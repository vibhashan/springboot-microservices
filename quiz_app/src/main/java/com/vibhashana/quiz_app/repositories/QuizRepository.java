package com.vibhashana.quiz_app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vibhashana.quiz_app.models.Quiz;

public interface QuizRepository extends MongoRepository<Quiz, String> {
}
