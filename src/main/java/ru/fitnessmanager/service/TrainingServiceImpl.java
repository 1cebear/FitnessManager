package ru.fitnessmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.fitnessmanager.model.Training;
import ru.fitnessmanager.repository.TrainingRepository;
import ru.fitnessmanager.util.exception.NotFoundException;

import java.util.List;

import static ru.fitnessmanager.util.ValidationUtil.checkNotFoundWithId;

@Service
public class TrainingServiceImpl implements TrainingService{

    private final TrainingRepository repository;

    @Autowired
    public TrainingServiceImpl(TrainingRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "trainings", allEntries = true)
    public Training save(Training training, int userId, int exerciseId) {
        Assert.notNull(training, "training must not be null");
        return repository.save(training, userId, exerciseId);
    }

    @CacheEvict(value = "trainings", allEntries = true)
    public void delete(int id, int userId, int exerciseId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId, exerciseId), id);
    }


    public Training get(int id, int userId, int exerciseId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId, exerciseId), id);
    }


    @Cacheable("trainings")
    public List<Training> getAll(int userId, int exerciseId) {
        return repository.getAll(userId, exerciseId);
    }

    @CacheEvict(value = "trainings", allEntries = true)
    public void update(Training training, int userId, int exerciseId) {
        Assert.notNull(training, "training must not be null");
        repository.save(training, userId, exerciseId);
    }
}
