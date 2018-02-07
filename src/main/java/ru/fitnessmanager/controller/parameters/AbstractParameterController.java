package ru.fitnessmanager.controller.parameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fitnessmanager.model.Parameter;
import ru.fitnessmanager.service.ParameterService;

import java.util.List;

import static ru.fitnessmanager.util.ValidationUtil.checkIdConsistent;
import static ru.fitnessmanager.util.ValidationUtil.checkNew;

public abstract class AbstractParameterController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ParameterService service;

    public List<Parameter> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Parameter get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Parameter create(Parameter parameter) {
        log.info("create {}", parameter);
        checkNew(parameter);
        return service.save(parameter);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Parameter parameter, int id) {
        log.info("update {}", parameter);
        checkIdConsistent(parameter, id);
        service.update(parameter);
    }
}
