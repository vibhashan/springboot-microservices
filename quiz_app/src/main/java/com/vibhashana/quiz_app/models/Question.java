package com.vibhashana.quiz_app.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NonNull;

@Document(collection = "questions")
@Data // Removes the need for getters/setters and gets rid of boilerplate code.
public class Question {
    @Data
    public static class Option {
        @NonNull
        private String option;

        @Field(name = "is_correct")
        @JsonProperty
        private Boolean isCorrect;
    }

    @Id // Optional when property name is "id", since _id is implicitly mapped.
    private ObjectId id;

    @NonNull
    private String title;

    @NonNull
    private String category;

    @NonNull
    @Field(name = "difficulty_level")
    private String difficultyLevel;

    @NonNull
    private List<Option> options;
}
