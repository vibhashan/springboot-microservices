package com.vibhashana.quiz_service.open_feign;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.vibhashana.quiz_service.models.Question;
import com.vibhashana.quiz_service.records.QuizSubmission;

@FeignClient(name = "QUESTION-SERVICE")
public interface QuestionService {

    @GetMapping("/generate")
    public ResponseEntity<List<String>> getQuestionsForQuiz(@RequestParam String category,
            @RequestParam int noOfQuestions);

    @PostMapping("/get-questions")
    public ResponseEntity<List<Question>> getRandomQuestionsByCategory(@RequestBody List<String> questionIds);

    @PostMapping("/get-score")
    public ResponseEntity<AtomicInteger> getScore(@RequestBody List<QuizSubmission> quizSubmission);
}
