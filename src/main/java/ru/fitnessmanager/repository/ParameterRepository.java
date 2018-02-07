package ru.fitnessmanager.repository;

import ru.fitnessmanager.model.Parameter;

import java.util.List;

public interface ParameterRepository {
    Parameter save(Parameter parameter);

    // false if not found
    boolean delete(int id);

    // null if not found
    Parameter get(int id);

    List<Parameter> getAll();
}
