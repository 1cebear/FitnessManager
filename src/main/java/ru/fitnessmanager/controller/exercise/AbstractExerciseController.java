package ru.fitnessmanager.controller.exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fitnessmanager.model.Exercise;
import ru.fitnessmanager.service.ExerciseService;

import java.util.List;

import static ru.fitnessmanager.util.ValidationUtil.checkIdConsistent;
import static ru.fitnessmanager.util.ValidationUtil.checkNew;

public class AbstractExerciseController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExerciseService service;

    public List<Exercise> getAll(int categoryId) {
        log.info("getAll");
        return service.getAll(categoryId);
    }

    public Exercise get(int id, int categoryId) {
        log.info("get {}", id);
        return service.get(id, categoryId);
    }

    public Exercise create(Exercise exercise, int categoryId) {
        log.info("create {}", exercise);
        checkNew(exercise);
        return service.save(exercise, categoryId);
    }

    public void delete(int id, int categoryId) {
        log.info("delete {}", id);
        service.delete(id, categoryId);
    }

    public void update(Exercise exercise, int id, int categoryId) {
        log.info("update {}", exercise);
        checkIdConsistent(exercise, id);
        service.update(exercise, categoryId);
    }
}
