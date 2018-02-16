package ru.fitnessmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.fitnessmanager.model.UserExercises;
import ru.fitnessmanager.repository.UserExercisesRepository;
import ru.fitnessmanager.util.exception.NotFoundException;

import java.util.List;

import static ru.fitnessmanager.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserExercisesServiceImpl implements UserExercisesService {

    private final UserExercisesRepository repository;

    @Autowired
    public UserExercisesServiceImpl(UserExercisesRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "userExercises", allEntries = true)
    public UserExercises save(UserExercises userExercises, int userId, int exerciseId) {
        Assert.notNull(userExercises, "userExercises must not be null");
        return repository.save(userExercises, userId, exerciseId);
    }

    @CacheEvict(value = "userExercises", allEntries = true)
    public void delete(int id, int userId, int exerciseId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId, exerciseId), id);
    }


    public UserExercises get(int id, int userId, int exerciseId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId, exerciseId), id);
    }


    @Cacheable("userExercises")
    public List<UserExercises> getAll(int userId, int exerciseId) {
        return repository.getAll(userId, exerciseId);
    }

    @CacheEvict(value = "userExercises", allEntries = true)
    public void update(UserExercises userExercises, int userId, int exerciseId) {
        Assert.notNull(userExercises, "userExercises must not be null");
        repository.save(userExercises, userId, exerciseId);
    }

    @Override
    @Cacheable("userExercises")
    public List<UserExercises> getAllForUser(int userId) {
        return repository.getAllForUser(userId);
    }
}
