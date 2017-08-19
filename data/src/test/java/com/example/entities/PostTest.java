package com.example.entities;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;

public class PostTest {

    private static final int MAX_MESSAGE_LENGTH = 140;

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldCreatePost() throws Exception {
        // given
        Post post = new Post("message");

        // when
        Set<ConstraintViolation<Post>> constraintViolations = validator.validate(post);

        // then
        assertThat(constraintViolations).hasSize(0);
    }

    @Test
    public void shouldNotCreatePostWithMessageTooLong() throws Exception {
        // given
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < MAX_MESSAGE_LENGTH + 1; i++) {
            message.append("*");
        }
        Post post = new Post(message.toString());

        // when
        Set<ConstraintViolation<Post>> constraintViolations = validator.validate(post);

        // then
        assertThat(constraintViolations).hasSize(1);
        assertThat(constraintViolations.iterator().next().getMessage()).contains("length must be between 0 and 140");
    }
}