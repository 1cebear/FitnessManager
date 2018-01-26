package ru.fitnessmanager.repository;

import ru.fitnessmanager.model.WeightControl;

import java.util.List;

public interface WeightControlRepository {
    WeightControl save(WeightControl weightControl, int userId);

    // false if not found
    boolean delete(int id, int userId);

    // null if not found
    WeightControl get(int id, int userId);

    List<WeightControl> getAll(int userId);
}
