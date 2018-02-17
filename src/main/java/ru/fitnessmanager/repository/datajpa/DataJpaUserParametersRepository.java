package ru.fitnessmanager.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.fitnessmanager.model.Parameter;
import ru.fitnessmanager.model.UserParameters;
import ru.fitnessmanager.repository.UserParametersRepository;
import ru.fitnessmanager.to.UserParametersTo;
import ru.fitnessmanager.util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class DataJpaUserParametersRepository implements UserParametersRepository {

    @Autowired
    private CrudUserParametersRepository crudUserParametersRepository;

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
        return crudUserParametersRepository.save(userParameters);
    }

    @Override
    public boolean delete(int id, int userId, int parameterId) {
        return crudUserParametersRepository.delete(id, userId, parameterId) != 0;
    }

    @Override
    public UserParameters get(int id, int userId, int parameterId) {
        UserParameters userParameters = crudUserParametersRepository.findOne(id);
        return userParameters != null && userParameters.getUser().getId() == userId && userParameters.getParameter().getId() == parameterId ? userParameters : null;
    }

    @Override
    public List<UserParameters> getAll(int userId, int parameterId) {
        return crudUserParametersRepository.getAll(userId, parameterId);
    }

    @Override
    public List<UserParametersTo> getForUser(Date startDate, Date endDate, int userId) {
        Calendar c = Calendar.getInstance();
        List<UserParametersTo> result = new ArrayList<UserParametersTo>();
        List<Parameter> parameters = crudParameterRepository.getAll();
        for (Parameter parameter : parameters) {
            List<UserParameters> list = new ArrayList<UserParameters>();
            Date currentDate = new Date(startDate.getTime());
            List<UserParameters> userParametersList = crudUserParametersRepository.getBetween(startDate, endDate, userId, parameter.getId());
            for (UserParameters userParameters : userParametersList) {
                if (userParameters.getDate().equals(currentDate)) {
                    c.setTime(currentDate);
                    c.add(Calendar.DATE, 1);
                    currentDate = c.getTime();
                    list.add(userParameters);
                } else {
                    while (DateUtil.isBefore(currentDate, userParameters.getDate())) {
                        c.setTime(currentDate);
                        c.add(Calendar.DATE, 1);
                        currentDate = c.getTime();
                        list.add(null);
                    }
                    c.setTime(currentDate);
                    c.add(Calendar.DATE, 1);
                    currentDate = c.getTime();
                    list.add(userParameters);
                }
            }
            while (currentDate.before(endDate)) {
                c.setTime(currentDate);
                c.add(Calendar.DATE, 1);
                currentDate = c.getTime();
                list.add(null);
            }
            if (!currentDate.after(endDate)) {
                c.setTime(currentDate);
                c.add(Calendar.DATE, 1);
                currentDate = c.getTime();
                list.add(null);

            }
            result.add(new UserParametersTo(parameter, list));
        }
        return result;
    }
}
