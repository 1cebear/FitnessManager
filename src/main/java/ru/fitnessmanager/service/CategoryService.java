package ru.fitnessmanager.service;

import ru.fitnessmanager.model.Category;
import ru.fitnessmanager.util.exception.NotFoundException;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    void delete(int id) throws NotFoundException;

    Category get(int id) throws NotFoundException;

    List<Category> getAll();

    void update(Category category);
}
