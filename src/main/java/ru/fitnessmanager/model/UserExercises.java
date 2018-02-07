package ru.fitnessmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "user_exercises")
public class UserExercises extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "b_name")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exercise_id", nullable = false)
    @JsonBackReference(value = "c_name")
    private Exercise exercise;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }



    public UserExercises(Integer id, User user, Exercise exercise) {
        super(id);
        this.user = user;
        this.exercise = exercise;
    }

    public UserExercises() {
    }

    @Override
    public String toString() {
        return "UserExercises{" +
                "user=" + user +
                ", exercise=" + exercise +
                '}';
    }
}
