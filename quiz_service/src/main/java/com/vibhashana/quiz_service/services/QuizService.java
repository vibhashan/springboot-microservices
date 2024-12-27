package com.vibhashana.quiz_service.services;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vibhashana.quiz_service.models.Quiz;
import com.vibhashana.quiz_service.open_feign.QuestionService;
import com.vibhashana.quiz_service.records.QuizSubmission;
import com.vibhashana.quiz_service.repositories.QuizRepository;

@Service
public class QuizService {
    private QuizRepository quizRepository;
    private QuestionService questionService;

    public QuizService(QuizRepository quizRepository,
            QuestionService questionService) {
        this.quizRepository = quizRepository;
        this.questionService = questionService;
    }

    @Transactional
    public Quiz createQuiz(String category, int noOfQuestions, String title) {
        Quiz quiz = new Quiz();
        List<String> questionIds = questionService.getQuestionsForQuiz(category, noOfQuestions).getBody();
        quiz.setTitle(title);
        quiz.setQuestions(questionService.getRandomQuestionsByCategory(questionIds).getBody());
        quizRepository.save(quiz);
        return quiz;
    }

    public AtomicInteger submitQuiz(List<QuizSubmission> quizSubmission) {
        AtomicInteger quizScore = new AtomicInteger(0);
        int result = questionService.getScore(quizSubmission).getBody().intValue();
        quizScore.set(result);
        return quizScore;
    }
}
