package ru.fitnessmanager.repository;

import ru.fitnessmanager.model.Training;

import java.util.List;

public interface TrainingRepository {
    Training save(Training training, int userId, int exerciseId);

    // false if not found
    boolean delete(int id, int userId, int exerciseId);

    // null if not found
    Training get(int id, int userId, int exerciseId);

    List<Training> getAll(int userId, int exerciseId);
}
