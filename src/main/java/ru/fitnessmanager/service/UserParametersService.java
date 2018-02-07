package ru.fitnessmanager.service;

import ru.fitnessmanager.model.UserParameters;
import ru.fitnessmanager.util.exception.NotFoundException;

import java.util.List;

public interface UserParametersService {

    UserParameters save(UserParameters userParameters, int userId, int parameterId);

    void delete(int id, int userId, int parameterId) throws NotFoundException;

    UserParameters get(int id, int userId, int parameterId) throws NotFoundException;

    List<UserParameters> getAll(int userId, int parameterId);

    void update(UserParameters userParameters, int userId, int parameterId);
}
