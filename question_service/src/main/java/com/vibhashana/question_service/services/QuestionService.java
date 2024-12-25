package com.vibhashana.question_service.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.vibhashana.question_service.models.Question;
import com.vibhashana.question_service.records.QuizSubmission;
import com.vibhashana.question_service.repositories.QuestionRepository;

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

    public List<String> getQuestionsForQuiz(String category, int noOfQuestions) {
        return questionRepository.getQuestionsForQuiz(category, noOfQuestions);
    }

    public List<Question> getQuestionsFromIds(List<String> questionIds) {
        List<Question> questions = new ArrayList<>();
        for (String questionId : questionIds) {
            questions.add(questionRepository.findById(questionId).get());
        }
        return questions;
    }

    public AtomicInteger getScore(List<QuizSubmission> quizSubmission) {
        AtomicInteger quizScore = new AtomicInteger(0);
        for (QuizSubmission submission : quizSubmission) {
            Question question = questionRepository.findById(submission.questionId()).get();
            question.getOptions().forEach(option -> {
                if (option.getOption().equals(submission.studentAnswer()) && option.getIsCorrect().booleanValue()) {
                    quizScore.set(quizScore.get() + 1);
                }
            });
        }
        return quizScore;
    }
}
