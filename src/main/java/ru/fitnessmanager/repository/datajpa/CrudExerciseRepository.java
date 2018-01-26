package ru.fitnessmanager.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.fitnessmanager.model.Exercise;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudExerciseRepository extends JpaRepository<Exercise, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Exercise e WHERE e.id=:id AND e.category.id=:categoryId")
    int delete(@Param("id") int id, @Param("categoryId") int categoryId);

    Exercise save(Exercise exercise);

    @Query("SELECT e FROM Exercise e WHERE e.category.id=:categoryId")
    List<Exercise> getAll(@Param("categoryId") int categoryId);
}
