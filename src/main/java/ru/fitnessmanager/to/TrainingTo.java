package ru.fitnessmanager.to;

import ru.fitnessmanager.model.Category;
import ru.fitnessmanager.model.Exercise;
import ru.fitnessmanager.model.Training;

import javax.validation.constraints.NotNull;
import java.util.List;

public class TrainingTo {

    @NotNull
    private Category category;

    @NotNull
    private Exercise exercise;

    @NotNull
    private List<Training> trainings;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public TrainingTo(Category category, Exercise exercise, List<Training> trainings) {
        this.category = category;
        this.exercise = exercise;
        this.trainings = trainings;
    }
}
