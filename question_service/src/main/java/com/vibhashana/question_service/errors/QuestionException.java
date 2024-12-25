package com.vibhashana.question_service.errors;

public class QuestionException extends RuntimeException {
    public QuestionException(String message) {
        super(message);
    }
}
