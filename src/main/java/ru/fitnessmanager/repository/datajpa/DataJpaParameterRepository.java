package ru.fitnessmanager.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.fitnessmanager.model.Parameter;
import ru.fitnessmanager.repository.ParameterRepository;

import java.util.List;

@Repository
public class DataJpaParameterRepository implements ParameterRepository {


    @Autowired
    private CrudParameterRepository crudParameterRepository;

    @Override
    public Parameter save(Parameter parameter) {
        return crudParameterRepository.save(parameter);
    }

    @Override
    public boolean delete(int id) {
        return crudParameterRepository.delete(id) != 0;
    }

    @Override
    public Parameter get(int id) {
        return crudParameterRepository.findOne(id);
    }

    @Override
    public List<Parameter> getAll() {
        return crudParameterRepository.findAll();
    }
}
