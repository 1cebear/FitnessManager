package ru.fitnessmanager.controller.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fitnessmanager.model.Category;
import ru.fitnessmanager.service.CategoryService;

import java.util.List;

import static ru.fitnessmanager.util.ValidationUtil.checkIdConsistent;
import static ru.fitnessmanager.util.ValidationUtil.checkNew;

public abstract class AbstractCategoryController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryService service;

    public List<Category> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Category get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Category create(Category category) {
        log.info("create {}", category);
        checkNew(category);
        return service.save(category);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Category category, int id) {
        log.info("update {}", category);
        checkIdConsistent(category, id);
        service.update(category);
    }
}
