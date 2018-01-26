package ru.fitnessmanager.service;

import ru.fitnessmanager.model.WeightControl;
import ru.fitnessmanager.util.exception.NotFoundException;

import java.util.List;

public interface WeightControlService {

    WeightControl save(WeightControl weightControl, int userId);

    void delete(int id, int userId) throws NotFoundException;

    WeightControl get(int id, int userId) throws NotFoundException;

    List<WeightControl> getAll(int userId);

    void update(WeightControl weightControl, int userId);
}
