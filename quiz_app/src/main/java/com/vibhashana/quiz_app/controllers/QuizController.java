package com.vibhashana.quiz_app.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vibhashana.quiz_app.models.Quiz;
import com.vibhashana.quiz_app.records.QuizSubmission;
import com.vibhashana.quiz_app.services.QuizService;

@RestController
public class QuizController {
    private QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/create-quiz")
    public ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam int noOfQuestions,
            @RequestParam String title) {
        return new ResponseEntity<>(quizService.createQuiz(category, noOfQuestions, title), HttpStatus.CREATED);
    }

    @PostMapping("/submit-quiz")
    public ResponseEntity<?> submitQuiz(@RequestBody List<QuizSubmission> quizSubmission) {
        return new ResponseEntity<>(quizService.submitQuiz(quizSubmission), HttpStatus.OK);
    }
}
