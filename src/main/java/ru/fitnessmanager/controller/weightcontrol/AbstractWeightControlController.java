package ru.fitnessmanager.controller.weightcontrol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fitnessmanager.model.WeightControl;
import ru.fitnessmanager.service.WeightControlService;

import java.util.List;

import static ru.fitnessmanager.util.ValidationUtil.checkIdConsistent;
import static ru.fitnessmanager.util.ValidationUtil.checkNew;

public abstract class AbstractWeightControlController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private WeightControlService service;

    public List<WeightControl> getAll(int userId) {
        log.info("getAll");
        return service.getAll(userId);
    }

    public WeightControl get(int id, int userId) {
        log.info("get {}", id);
        return service.get(id, userId);
    }

    public WeightControl create(WeightControl weightControl, int userId) {
        log.info("create {}", weightControl);
        checkNew(weightControl);
        return service.save(weightControl, userId);
    }

    public void delete(int id, int userId) {
        log.info("delete {}", id);
        service.delete(id, userId);
    }

    public void update(WeightControl weightControl, int id, int userId) {
        log.info("update {}", weightControl);
        checkIdConsistent(weightControl, id);
        service.update(weightControl, userId);
    }

}
