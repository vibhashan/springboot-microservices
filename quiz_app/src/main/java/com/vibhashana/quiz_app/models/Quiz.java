package com.vibhashana.quiz_app.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Data;

@Document("quizzes")
@Data
public class Quiz {
    private ObjectId id;
    private String title;
    @DocumentReference
    private List<Question> questions;
}
