package ru.fitnessmanager.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.fitnessmanager.model.Exercise;
import ru.fitnessmanager.repository.ExerciseRepository;

import java.util.List;

@Repository
public class DataJpaExerciseRepository implements ExerciseRepository {

    @Autowired
    private CrudExerciseRepository crudExerciseRepository;

    @Autowired
    private CrudCategoryRepository crudCategoryRepository;

    @Override
    public Exercise save(Exercise exercise, int categoryId) {
        if (!exercise.isNew() && get(exercise.getId(), categoryId) == null) {
            return null;
        }
        exercise.setCategory(crudCategoryRepository.getOne(categoryId));
        return crudExerciseRepository.save(exercise);
    }

    @Override
    public boolean delete(int id, int categoryId) {
        return crudExerciseRepository.delete(id, categoryId) != 0;
    }

    @Override
    public Exercise get(int id, int categoryId) {
        Exercise exercise = crudExerciseRepository.findOne(id);
        return exercise != null && exercise.getCategory().getId() == categoryId ? exercise : null;
    }

    @Override
    public List<Exercise> getAll(int categoryId) {
        return crudExerciseRepository.getAll(categoryId);
    }
}
