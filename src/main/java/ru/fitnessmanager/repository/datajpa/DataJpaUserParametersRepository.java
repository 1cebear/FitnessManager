package ru.fitnessmanager.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.fitnessmanager.model.UserParameters;
import ru.fitnessmanager.repository.UserParametersRepository;

import java.util.List;

@Repository
public class DataJpaUserParametersRepository implements UserParametersRepository {

    @Autowired
    private CrudUserParametersRepository crudWeightControlRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Autowired
    private CrudParameterRepository crudParameterRepository;

    @Override
    public UserParameters save(UserParameters userParameters, int userId, int parameterId) {
        if (!userParameters.isNew() && get(userParameters.getId(), userId, parameterId) == null) {
            return null;
        }
        userParameters.setUser(crudUserRepository.getOne(userId));
        userParameters.setParameter(crudParameterRepository.getOne(parameterId));
        return crudWeightControlRepository.save(userParameters);
    }

    @Override
    public boolean delete(int id, int userId, int parameterId) {
        return crudWeightControlRepository.delete(id, userId, parameterId) != 0;
    }

    @Override
    public UserParameters get(int id, int userId, int parameterId) {
        UserParameters userParameters = crudWeightControlRepository.findOne(id);
        return userParameters != null && userParameters.getUser().getId() == userId && userParameters.getParameter().getId() == parameterId ? userParameters : null;
    }

    @Override
    public List<UserParameters> getAll(int userId, int parameterId) {
        return crudWeightControlRepository.getAll(userId, parameterId);
    }
}
