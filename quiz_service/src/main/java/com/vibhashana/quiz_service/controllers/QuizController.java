package com.vibhashana.quiz_service.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vibhashana.quiz_service.models.Quiz;
import com.vibhashana.quiz_service.models.QuizDTO;
import com.vibhashana.quiz_service.records.QuizSubmission;
import com.vibhashana.quiz_service.services.QuizService;

@RestController
public class QuizController {
    private QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/create-quiz")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDTO quizDTO) {
        return new ResponseEntity<>(
                quizService.createQuiz(quizDTO.categoryName(), quizDTO.noOfQuestions(), quizDTO.title()),
                HttpStatus.CREATED);
    }

    @PostMapping("/submit-quiz")
    public ResponseEntity<?> submitQuiz(@RequestBody List<QuizSubmission> quizSubmission) {
        return new ResponseEntity<>(quizService.submitQuiz(quizSubmission), HttpStatus.OK);
    }
}
