package ru.fitnessmanager.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.fitnessmanager.model.Training;
import ru.fitnessmanager.repository.TrainingRepository;

import java.util.List;

@Repository
public class DataJpaTrainingRepository implements TrainingRepository {

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Autowired
    private CrudExerciseRepository crudExerciseRepository;

    @Autowired
    private CrudTrainingRepository crudTrainingRepository;

    @Override
    public Training save(Training training, int userId, int exerciseId) {
        if (training != null && get(training.getId(), userId, exerciseId) == null) {
            return null;
        }
        training.setExercise(crudExerciseRepository.getOne(exerciseId));
        training.setUser(crudUserRepository.getOne(userId));
        return crudTrainingRepository.save(training);
    }

    @Override
    public boolean delete(int id, int userId, int exerciseId) {
        return crudTrainingRepository.delete(id, userId, exerciseId) != 0;
    }

    @Override
    public Training get(int id, int userId, int exerciseId) {
        Training training = crudTrainingRepository.findOne(id);
        return training != null && training.getUser().getId() == userId && training.getExercise().getId() == exerciseId ? training : null;
    }

    @Override
    public List<Training> getAll(int userId, int exerciseId) {
        return crudTrainingRepository.getAll(userId, exerciseId);
    }
}
