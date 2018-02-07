package ru.fitnessmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.fitnessmanager.model.Parameter;
import ru.fitnessmanager.repository.ParameterRepository;
import ru.fitnessmanager.util.exception.NotFoundException;

import java.util.List;

import static ru.fitnessmanager.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ParameterServiceImpl implements ParameterService {

    private final ParameterRepository repository;

    @Autowired
    public ParameterServiceImpl(ParameterRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "parameters", allEntries = true)
    public Parameter save(Parameter parameter) {
        Assert.notNull(parameter, "parameter must not be null");
        return repository.save(parameter);
    }

    @CacheEvict(value = "parameters", allEntries = true)
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }


    public Parameter get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }


    @Cacheable("parameters")
    public List<Parameter> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "parameters", allEntries = true)
    public void update(Parameter parameter) {
        Assert.notNull(parameter, "parameter must not be null");
        repository.save(parameter);
    }
}
