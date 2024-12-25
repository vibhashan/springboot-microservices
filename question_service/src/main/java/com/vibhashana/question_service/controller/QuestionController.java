package com.vibhashana.question_service.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vibhashana.question_service.models.Question;
import com.vibhashana.question_service.records.QuizSubmission;
import com.vibhashana.question_service.services.QuestionService;

@RestController
public class QuestionController {
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // Generate
    @GetMapping("/generate")
    public ResponseEntity<List<String>> getQuestionsForQuiz(@RequestParam String category,
            @RequestParam int noOfQuestions) {
        return new ResponseEntity<>(questionService.getQuestionsForQuiz(category, noOfQuestions), HttpStatus.OK);
    }

    @PostMapping("/get-questions")
    public ResponseEntity<List<Question>> getRandomQuestionsByCategory(@RequestBody List<String> questionIds) {
        return new ResponseEntity<>(questionService.getQuestionsFromIds(questionIds), HttpStatus.OK);
    }

    @PostMapping("/get-score")
    public ResponseEntity<AtomicInteger> getScore(@RequestBody List<QuizSubmission> quizSubmission) {
        return new ResponseEntity<>(questionService.getScore(quizSubmission), HttpStatus.OK);
    }
}
