package com.example.entities;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;

public class UserTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldCreatePost() throws Exception {
        // given
        User user = new User("name");

        // when
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        // then
        assertThat(constraintViolations).hasSize(0);
    }

    @Test
    public void shouldNotCreateUserWithEmptyName() throws Exception {
        // given
        User user = new User("");

        // when
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        // then
        assertThat(constraintViolations).hasSize(1);
        assertThat(constraintViolations.iterator().next().getMessage()).contains("may not be empty");
    }
}