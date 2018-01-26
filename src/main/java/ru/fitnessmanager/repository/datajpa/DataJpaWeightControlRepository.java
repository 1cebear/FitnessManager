package ru.fitnessmanager.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.fitnessmanager.model.WeightControl;
import ru.fitnessmanager.repository.WeightControlRepository;

import java.util.List;

@Repository
public class DataJpaWeightControlRepository implements WeightControlRepository {

    @Autowired
    private CrudWeightControlRepository crudWeightControlRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public WeightControl save(WeightControl weightControl, int userId) {
        if (!weightControl.isNew() && get(weightControl.getId(), userId) == null) {
            return null;
        }
        weightControl.setUser(crudUserRepository.getOne(userId));
        return crudWeightControlRepository.save(weightControl);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudWeightControlRepository.delete(id, userId) != 0;
    }

    @Override
    public WeightControl get(int id, int userId) {
        WeightControl weightControl = crudWeightControlRepository.findOne(id);
        return weightControl != null && weightControl.getUser().getId() == userId ? weightControl : null;
    }

    @Override
    public List<WeightControl> getAll(int userId) {
        return crudWeightControlRepository.getAll(userId);
    }
}
