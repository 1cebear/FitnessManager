package ru.fitnessmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.fitnessmanager.model.Exercise;
import ru.fitnessmanager.repository.ExerciseRepository;
import ru.fitnessmanager.util.exception.NotFoundException;

import java.util.List;

import static ru.fitnessmanager.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository repository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "exercises", allEntries = true)
    public Exercise save(Exercise exercise, int categoryId) {
        Assert.notNull(exercise, "exercise must not be null");
        return repository.save(exercise, categoryId);
    }

    @CacheEvict(value = "exercises", allEntries = true)
    public void delete(int id, int categoryId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, categoryId), id);
    }


    public Exercise get(int id, int categoryId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, categoryId), id);
    }


    @Cacheable("exercises")
    public List<Exercise> getAll(int categoryId) {
        return repository.getAll(categoryId);
    }

    @CacheEvict(value = "exercises", allEntries = true)
    public void update(Exercise exercise, int categoryId) {
        Assert.notNull(exercise, "exercise must not be null");
        repository.save(exercise, categoryId);
    }
}
