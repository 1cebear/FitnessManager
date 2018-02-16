package ru.fitnessmanager.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.fitnessmanager.model.Category;
import ru.fitnessmanager.model.Exercise;
import ru.fitnessmanager.model.Training;
import ru.fitnessmanager.model.UserExercises;
import ru.fitnessmanager.repository.TrainingRepository;
import ru.fitnessmanager.to.TrainingTo;
import ru.fitnessmanager.util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class DataJpaTrainingRepository implements TrainingRepository {

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Autowired
    private CrudExerciseRepository crudExerciseRepository;

    @Autowired
    private CrudTrainingRepository crudTrainingRepository;

    @Autowired
    private CrudCategoryRepository crudCategoryRepository;

    @Autowired
    private CrudUserExercisesRepository crudUserExercisesRepository;

    @Override
    public Training save(Training training, int userId, int exerciseId) {
        if (!training.isNew() && get(training.getId(), userId, exerciseId) == null) {
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

    @Override
    public List<Training> getBetween(Date startDate, Date endDate, int userId, int exerciseId) {
        return crudTrainingRepository.getBetween(startDate, endDate, userId, exerciseId);
    }

    @Override
    public List<TrainingTo> getTrainingsForUser(Date startDate, Date endDate, int userId) {
        List<TrainingTo> result = new ArrayList<TrainingTo>();
        Calendar c = Calendar.getInstance();
        List<Category> categories = crudCategoryRepository.getAll();
        for (Category category : categories) {
            List<Exercise> exercises = crudExerciseRepository.getAll(category.getId());
            for (Exercise exercise : exercises) {
                List<UserExercises> userExercises = crudUserExercisesRepository.getAll(userId, exercise.getId());
                if (userExercises.size() == 0) {
                    continue;
                }
                List<Training> trainings = new ArrayList<Training>();
                Date currentDate = new Date(startDate.getTime());
                List<Training> trainingsList = crudTrainingRepository.getBetween(startDate, DateUtil.getEnd(endDate), userId, exercise.getId());
                for (Training training : trainingsList) {
                    if (training.getDate().equals(currentDate)) {
                        c.setTime(currentDate);
                        c.add(Calendar.DATE, 1);
                        currentDate = c.getTime();
                        trainings.add(training);
                    } else {
                        while (DateUtil.isBefore(currentDate, training.getDate())) {
                            c.setTime(currentDate);
                            c.add(Calendar.DATE, 1);
                            currentDate = c.getTime();
                            trainings.add(null);
                        }
                        c.setTime(currentDate);
                        c.add(Calendar.DATE, 1);
                        currentDate = c.getTime();
                        trainings.add(training);
                    }

                }
                while (currentDate.before(endDate)) {
                    c.setTime(currentDate);
                    c.add(Calendar.DATE, 1);
                    currentDate = c.getTime();
                    trainings.add(null);
                }
                if (!currentDate.after(endDate)) {
                    c.setTime(currentDate);
                    c.add(Calendar.DATE, 1);
                    currentDate = c.getTime();
                    trainings.add(null);
                }

                result.add(new TrainingTo(category, exercise, trainings));
            }
        }
        return result;
    }
}

