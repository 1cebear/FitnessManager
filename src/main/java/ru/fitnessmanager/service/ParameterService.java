package ru.fitnessmanager.service;

import ru.fitnessmanager.model.Parameter;
import ru.fitnessmanager.util.exception.NotFoundException;

import java.util.List;

public interface ParameterService {

    Parameter save(Parameter parameter);

    void delete(int id) throws NotFoundException;

    Parameter get(int id) throws NotFoundException;

    List<Parameter> getAll();

    void update(Parameter parameter);
}
