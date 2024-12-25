package com.vibhashana.quiz_service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vibhashana.quiz_service.models.Quiz;

public interface QuizRepository extends MongoRepository<Quiz, String> {
}
