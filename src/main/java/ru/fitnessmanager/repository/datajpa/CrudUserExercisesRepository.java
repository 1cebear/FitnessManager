package ru.fitnessmanager.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.fitnessmanager.model.UserExercises;

import java.util.List;

@Transactional(readOnly = false)
public interface CrudUserExercisesRepository extends JpaRepository<UserExercises, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM UserExercises e WHERE e.id=:id AND e.exercise.id=:exerciseId AND e.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId, @Param("exerciseId") int exerciseId);

    UserExercises save(UserExercises userExercises);

    @Query("SELECT e FROM UserExercises e WHERE e.exercise.id=:exerciseId AND e.user.id=:userId")
    List<UserExercises> getAll(@Param("userId") int userId, @Param("exerciseId") int exerciseId);

    @Query("SELECT ue.id as id, e.id as exerciseId, e.name as exerciseName FROM UserExercises ue inner JOIN  ue.exercise e WHERE (ue.user.id=:userId)")
    List<UserExercises> getAllForUser(@Param("userId") int userId);
}
