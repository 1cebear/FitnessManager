package ru.fitnessmanager.service;

import ru.fitnessmanager.model.Exercise;
import ru.fitnessmanager.util.exception.NotFoundException;

import java.util.List;

public interface ExerciseService {

    Exercise save(Exercise exercise, int categoryId);

    void delete(int id, int categoryId) throws NotFoundException;

    Exercise get(int id, int categoryId) throws NotFoundException;

    List<Exercise> getAll(int categoryId);

    void update(Exercise exercise, int categoryId);
}
