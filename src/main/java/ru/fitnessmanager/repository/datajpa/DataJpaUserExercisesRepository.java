package ru.fitnessmanager.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.fitnessmanager.model.UserExercises;
import ru.fitnessmanager.repository.UserExercisesRepository;

import java.util.List;

@Repository
public class DataJpaUserExercisesRepository implements UserExercisesRepository {

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Autowired
    private CrudExerciseRepository crudExerciseRepository;

    @Autowired
    private CrudUserExercisesRepository crudUserExercisesRepository;

    @Override
    public UserExercises save(UserExercises userExercises, int userId, int exerciseId) {
        if (!userExercises.isNew() && get(userExercises.getId(), userId, exerciseId) == null) {
            return null;
        }
        userExercises.setExercise(crudExerciseRepository.getOne(exerciseId));
        userExercises.setUser(crudUserRepository.getOne(userId));
        return crudUserExercisesRepository.save(userExercises);
    }

    @Override
    public boolean delete(int id, int userId, int exerciseId) {
        return crudUserExercisesRepository.delete(id, userId, exerciseId) != 0;
    }

    @Override
    public UserExercises get(int id, int userId, int exerciseId) {
        UserExercises userExercises = crudUserExercisesRepository.findOne(id);
        return userExercises != null && userExercises.getUser().getId() == userId && userExercises.getExercise().getId() == exerciseId ? userExercises : null;
    }

    @Override
    public List<UserExercises> getAll(int userId, int exerciseId) {
        return crudUserExercisesRepository.getAll(userId, exerciseId);
    }

    @Override
    public List<UserExercises> getAllForUser(int userId) {
        return crudUserExercisesRepository.getAllForUser(userId);
    }
}
