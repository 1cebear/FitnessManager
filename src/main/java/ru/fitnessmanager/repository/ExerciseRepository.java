package ru.fitnessmanager.repository;

import ru.fitnessmanager.model.Exercise;

import java.util.List;

public interface ExerciseRepository {
    Exercise save(Exercise exercise, int categoryId);

    // false if not found
    boolean delete(int id, int categoryId);

    // null if not found
    Exercise get(int id, int categoryId);

    List<Exercise> getAll(int categoryId);
}
