package ru.fitnessmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.fitnessmanager.model.UserParameters;
import ru.fitnessmanager.repository.UserParametersRepository;
import ru.fitnessmanager.util.exception.NotFoundException;

import java.util.List;

import static ru.fitnessmanager.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserParametersServiceImpl implements UserParametersService {

    private final UserParametersRepository repository;

    @Autowired
    public UserParametersServiceImpl(UserParametersRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "userParameters", allEntries = true)
    public UserParameters save(UserParameters userParameters, int userId, int parameterId) {
        Assert.notNull(userParameters, "userParameters must not be null");
        return repository.save(userParameters, userId, parameterId);
    }

    @CacheEvict(value = "userParameters", allEntries = true)
    public void delete(int id, int userId, int parameterId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId, parameterId), id);
    }


    public UserParameters get(int id, int userId, int parameterId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId, parameterId), id);
    }


    @Cacheable("userParameters")
    public List<UserParameters> getAll(int userId, int parameterId) {
        return repository.getAll(userId, parameterId);
    }

    @CacheEvict(value = "userParameters", allEntries = true)
    public void update(UserParameters userParameters, int userId, int parameterId) {
        Assert.notNull(userParameters, "userParameters must not be null");
        repository.save(userParameters, userId, parameterId);
    }
}
