package ru.fitnessmanager.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.fitnessmanager.model.Category;
import ru.fitnessmanager.repository.CategoryRepository;

import java.util.List;

@Repository
public class DataJpaCategoryRepository implements CategoryRepository {

    @Autowired
    private CrudCategoryRepository crudCategoryRepository;

    @Override
    public Category save(Category category) {
        return crudCategoryRepository.save(category);
    }

    @Override
    public boolean delete(int id) {
        return crudCategoryRepository.delete(id) != 0;
    }

    @Override
    public Category get(int id) {
        return crudCategoryRepository.findOne(id);
    }

    @Override
    public List<Category> getAll() {
        return crudCategoryRepository.findAll();
    }
}
