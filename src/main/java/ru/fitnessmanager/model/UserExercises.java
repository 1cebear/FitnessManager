package ru.fitnessmanager.model;

import java.util.Date;

public class UserExercises extends BaseEntity {

    private User user;

    private Exercise exercise;

    private Date date;

    private double value;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public UserExercises(Integer id, User user, Exercise exercise, Date date, double value) {
        super(id);
        this.user = user;
        this.exercise = exercise;
        this.date = date;
        this.value = value;
    }
}
