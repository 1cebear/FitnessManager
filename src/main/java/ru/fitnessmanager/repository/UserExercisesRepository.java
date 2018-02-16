package ru.fitnessmanager.repository;

import ru.fitnessmanager.model.UserExercises;

import java.util.List;

public interface UserExercisesRepository {
    UserExercises save(UserExercises userExercises, int userId, int exerciseId);

    // false if not found
    boolean delete(int id, int userId, int exerciseId);

    // null if not found
    UserExercises get(int id, int userId, int exerciseId);

    List<UserExercises> getAll(int userId, int exerciseId);

    List<UserExercises> getAllForUser(int userId);
}
