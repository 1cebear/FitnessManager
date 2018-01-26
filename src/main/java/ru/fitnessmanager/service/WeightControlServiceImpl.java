package ru.fitnessmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.fitnessmanager.model.WeightControl;
import ru.fitnessmanager.repository.WeightControlRepository;
import ru.fitnessmanager.util.exception.NotFoundException;

import java.util.List;

import static ru.fitnessmanager.util.ValidationUtil.checkNotFoundWithId;

@Service
public class WeightControlServiceImpl implements WeightControlService {

    private final WeightControlRepository repository;

    @Autowired
    public WeightControlServiceImpl(WeightControlRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "weightControl", allEntries = true)
    public WeightControl save(WeightControl weightControl, int userId) {
        Assert.notNull(weightControl, "weightControl must not be null");
        return repository.save(weightControl, userId);
    }

    @CacheEvict(value = "weightControl", allEntries = true)
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }


    public WeightControl get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }


    @Cacheable("weightControl")
    public List<WeightControl> getAll(int userId) {
        return repository.getAll(userId);
    }

    @CacheEvict(value = "weightControl", allEntries = true)
    public void update(WeightControl weightControl, int userId) {
        Assert.notNull(weightControl, "weightControl must not be null");
        repository.save(weightControl, userId);
    }
}
