package ru.fitnessmanager.service;

import ru.fitnessmanager.model.UserExercises;
import ru.fitnessmanager.util.exception.NotFoundException;

import java.util.List;

public interface UserExercisesService {

    UserExercises save(UserExercises userExercises, int userId, int exerciseId);

    void delete(int id, int userId, int exerciseId) throws NotFoundException;

    UserExercises get(int id, int userId, int exerciseId) throws NotFoundException;

    List<UserExercises> getAll(int userId, int exerciseId);

    void update(UserExercises userExercises, int userId, int exerciseId);
}
