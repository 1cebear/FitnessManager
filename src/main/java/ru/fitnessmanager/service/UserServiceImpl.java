package ru.fitnessmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.fitnessmanager.model.User;
import ru.fitnessmanager.repository.UserRepository;
import ru.fitnessmanager.util.exception.NotFoundException;

import java.util.List;

import static ru.fitnessmanager.util.ValidationUtil.checkNotFound;
import static ru.fitnessmanager.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "users", allEntries = true)
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }


    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }


    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    public List<User> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        repository.save(user);
    }
}
