package ru.fitnessmanager.controller.userexercises;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fitnessmanager.model.UserExercises;
import ru.fitnessmanager.service.UserExercisesService;

import java.util.List;

import static ru.fitnessmanager.util.ValidationUtil.checkIdConsistent;
import static ru.fitnessmanager.util.ValidationUtil.checkNew;

public abstract class AbstractUserExercisesController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserExercisesService service;

    public List<UserExercises> getAll(int userId, int exerciseId) {
        log.info("getAll");
        return service.getAll(userId, exerciseId);
    }

    public UserExercises get(int id, int userId, int exerciseId) {
        log.info("get {}", id);
        return service.get(id, userId, exerciseId);
    }

    public UserExercises create(UserExercises userExercises, int userId, int exerciseId) {
        log.info("create {}", userExercises);
        checkNew(userExercises);
        return service.save(userExercises, userId, exerciseId);
    }

    public void delete(int id, int userId, int exerciseId) {
        log.info("delete {}", id);
        service.delete(id, userId, exerciseId);
    }

    public void update(UserExercises userExercises, int id, int userId, int exerciseId) {
        log.info("update {}", userExercises);
        checkIdConsistent(userExercises, id);
        service.update(userExercises, userId, exerciseId);
    }

    public List<UserExercises> getAllForUser(int userId) {
        log.info("getAllForUser");
        return service.getAllForUser(userId);
    }
}
