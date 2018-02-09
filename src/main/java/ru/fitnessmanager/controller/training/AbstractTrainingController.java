package ru.fitnessmanager.controller.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fitnessmanager.model.Training;
import ru.fitnessmanager.service.TrainingService;

import java.util.List;

import static ru.fitnessmanager.util.ValidationUtil.checkIdConsistent;
import static ru.fitnessmanager.util.ValidationUtil.checkNew;

public class AbstractTrainingController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private TrainingService service;

    public List<Training> getAll(int userId, int exerciseId) {
        log.info("getAll");
        return service.getAll(userId, exerciseId);
    }

    public Training get(int id, int userId, int exerciseId) {
        log.info("get {}", id);
        return service.get(id, userId, exerciseId);
    }

    public Training create(Training training, int userId, int exerciseId) {
        log.info("create {}", training);
        checkNew(training);
        return service.save(training, userId, exerciseId);
    }

    public void delete(int id, int userId, int exerciseId) {
        log.info("delete {}", id);
        service.delete(id, userId, exerciseId);
    }

    public void update(Training training, int id, int userId, int exerciseId) {
        log.info("update {}", training);
        checkIdConsistent(training, id);
        service.update(training, userId, exerciseId);
    }
}
