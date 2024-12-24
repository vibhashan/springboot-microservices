package com.vibhashana.quiz_app.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vibhashana.quiz_app.models.Question;
import com.vibhashana.quiz_app.models.Quiz;
import com.vibhashana.quiz_app.records.QuizSubmission;
import com.vibhashana.quiz_app.repositories.QuizRepository;

@Service
public class QuizService {
    private QuizRepository quizRepository;
    private QuestionService questionService;

    public QuizService(QuizRepository quizRepository, QuestionService questionService) {
        this.questionService = questionService;
        this.quizRepository = quizRepository;
    }

    @Transactional
    public Quiz createQuiz(String category, int noOfQuestions, String title) {
        Quiz quiz = new Quiz();
        List<Question> questions = questionService.getRandomQuestionsByCategory(category, noOfQuestions);
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return quiz;
    }

    public AtomicInteger submitQuiz(List<QuizSubmission> quizSubmission) {
        AtomicInteger quizScore = new AtomicInteger(0);
        for (QuizSubmission submission : quizSubmission) {
            Question question = questionService.getQuestionById(submission.questionId());
            question.getOptions().forEach(option -> {
                if (option.getOption().equals(submission.studentAnswer()) && option.getIsCorrect().booleanValue()) {
                    quizScore.set(quizScore.get() + 1);
                }
            });
        }
        return quizScore;
    }
}
