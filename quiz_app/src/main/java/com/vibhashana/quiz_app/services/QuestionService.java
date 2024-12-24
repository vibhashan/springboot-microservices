package com.vibhashana.quiz_app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vibhashana.quiz_app.models.Question;
import com.vibhashana.quiz_app.repositories.QuestionRepository;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getRandomQuestionsByCategory(String category, int noOfQuestions) {
        return questionRepository.findRandomQuestionsByCategory(category, noOfQuestions);
    }

    public Question getQuestionById(String questionId) {
        return questionRepository.findById(questionId).orElseThrow();
    }
}
