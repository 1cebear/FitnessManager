package ru.fitnessmanager.repository;

import ru.fitnessmanager.model.UserParameters;
import ru.fitnessmanager.to.UserParametersTo;

import java.util.Date;
import java.util.List;

public interface UserParametersRepository {
    UserParameters save(UserParameters userParameters, int userId, int parameterId);

    // false if not found
    boolean delete(int id, int userId, int parameterId);

    // null if not found
    UserParameters get(int id, int userId, int parameterId);

    List<UserParameters> getAll(int userId, int parameterId);

    List<UserParametersTo> getForUser(Date startDate, Date endDate, int userId);
}
