package ru.fitnessmanager.service;

import ru.fitnessmanager.model.Training;
import ru.fitnessmanager.to.TrainingTo;
import ru.fitnessmanager.util.exception.NotFoundException;

import java.util.Date;
import java.util.List;

public interface TrainingService {

    Training save(Training training, int userId, int exerciseId);

    void delete(int id, int userId, int exerciseId) throws NotFoundException;

    Training get(int id, int userId, int exerciseId) throws NotFoundException;

    List<Training> getAll(int userId, int exerciseId);

    void update(Training training, int userId, int exerciseId);

    List<Training> getBetween(Date startDate, Date endDate, int userId, int exerciseId);

    List<TrainingTo> getTrainingsForUser(Date startDate, Date endDate, int userId);
}
