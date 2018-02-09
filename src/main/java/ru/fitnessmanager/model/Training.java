package ru.fitnessmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "trainings")
public class Training extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "g_name")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exercise_id", nullable = false)
    @JsonBackReference(value = "h_name")
    private Exercise exercise;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "done", nullable = false)
    private boolean done;

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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Training() {
    }

    public Training(Integer id, User user, Exercise exercise, double weight, boolean done) {
        super(id);
        this.user = user;
        this.exercise = exercise;
        this.weight = weight;
        this.done = done;
    }
}
