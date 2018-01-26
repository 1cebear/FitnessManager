package ru.fitnessmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.fitnessmanager.model.Category;
import ru.fitnessmanager.repository.CategoryRepository;
import ru.fitnessmanager.util.exception.NotFoundException;

import java.util.List;

import static ru.fitnessmanager.util.ValidationUtil.checkNotFoundWithId;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "categories", allEntries = true)
    public Category save(Category category) {
        Assert.notNull(category, "category must not be null");
        return repository.save(category);
    }

    @CacheEvict(value = "categories", allEntries = true)
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }


    public Category get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }


    @Cacheable("categories")
    public List<Category> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "categories", allEntries = true)
    public void update(Category category) {
        Assert.notNull(category, "category must not be null");
        repository.save(category);
    }
}
