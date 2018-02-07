package ru.fitnessmanager.controller.userparameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fitnessmanager.model.UserParameters;
import ru.fitnessmanager.service.UserParametersService;

import java.util.List;

import static ru.fitnessmanager.util.ValidationUtil.checkIdConsistent;
import static ru.fitnessmanager.util.ValidationUtil.checkNew;

public abstract class AbstractUserParametersController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserParametersService service;

    public List<UserParameters> getAll(int userId, int parameterId) {
        log.info("getAll");
        return service.getAll(userId, parameterId);
    }

    public UserParameters get(int id, int userId, int parameterId) {
        log.info("get {}", id);
        return service.get(id, userId, parameterId);
    }

    public UserParameters create(UserParameters userParameters, int userId, int parameterId) {
        log.info("create {}", userParameters);
        checkNew(userParameters);
        return service.save(userParameters, userId, parameterId);
    }

    public void delete(int id, int userId, int parameterId) {
        log.info("delete {}", id);
        service.delete(id, userId, parameterId);
    }

    public void update(UserParameters userParameters, int id, int userId, int parameterId) {
        log.info("update {}", userParameters);
        checkIdConsistent(userParameters, id);
        service.update(userParameters, userId, parameterId);
    }

}
