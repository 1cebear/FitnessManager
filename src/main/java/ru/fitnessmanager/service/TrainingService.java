package ru.fitnessmanager.service;

import ru.fitnessmanager.model.Training;
import ru.fitnessmanager.util.exception.NotFoundException;

import java.util.List;

public interface TrainingService {

    Training save(Training training, int userId, int exerciseId);

    void delete(int id, int userId, int exerciseId) throws NotFoundException;

    Training get(int id, int userId, int exerciseId) throws NotFoundException;

    List<Training> getAll(int userId, int exerciseId);

    void update(Training training, int userId, int exerciseId);
}
